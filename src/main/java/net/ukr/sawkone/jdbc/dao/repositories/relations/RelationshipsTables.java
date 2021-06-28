package net.ukr.sawkone.jdbc.dao.repositories.relations;

import net.ukr.sawkone.jdbc.config.DatabaseConnectionManager;
import net.ukr.sawkone.jdbc.dao.entity.DevelopersDAO;
import net.ukr.sawkone.jdbc.dao.entity.ProjectsDAO;
import net.ukr.sawkone.jdbc.dao.entity.SkillsDAO;
import net.ukr.sawkone.jdbc.dto.Branch;
import net.ukr.sawkone.jdbc.dto.Level;
import net.ukr.sawkone.jdbc.servise.DevelopersConverter;
import net.ukr.sawkone.jdbc.servise.ProjectConverter;
import net.ukr.sawkone.jdbc.servise.ProjectSelect;
import net.ukr.sawkone.jdbc.servise.SkillsConverter;
import net.ukr.sawkone.jdbc.util.PropertiesConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RelationshipsTables {
    private static final DatabaseConnectionManager connectionManager;

    static {
        PropertiesConfig propertiesConfig = new PropertiesConfig("application.properties");
        connectionManager = new DatabaseConnectionManager(propertiesConfig);
    }

    public static List<SkillsDAO> getSkillsDeveloper(long id) {
        String findSkills = "SELECT s.id_skill, s.branch, s.level from skills s, developers d, " +
                "developers_skills ds WHERE s.id_skill=ds.id_skill and d.id_developers=ds.id_developers and " +
                "d.id_developers=?;";
        List<SkillsDAO> skillsDAOList = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findSkills)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return SkillsConverter.toSkillsDAOCollection(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public static List<ProjectsDAO> getProjectDeveloper(long id) {
        String findSkills = "SELECT p.id_project, p.name_project, p.date_create, p.id_customer, " +
                "p.id_company, p.cost from projects p, developers d, developers_projects dp WHERE " +
                "p.id_project=dp.id_project and d.id_developers=dp.id_developers and d.id_developers=?;";
        List<ProjectsDAO> projectsDAOList = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findSkills)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return ProjectConverter.toProjectsDAOCollection(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public static double sumSalaryDevelopersByProject(long id) {
        String query = "SELECT sum(d.salary) AS sum_salary FROM developers d, developers_projects dp, projects p WHERE " +
                "d.id_developers=dp.id_developers AND dp.id_project=p.id_project AND p.id_project=?;";
        double sumSalary = 0.0;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) return resultSet.getDouble("sum_salary");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sumSalary;
    }

    public static List<DevelopersDAO> listDevelopersByProject(long id) {
        String query = "SELECT d.id_developers, d.name, d.age, d.sex, d.id_company, d.salary FROM developers_projects dp " +
                "JOIN developers d ON d.id_developers=dp.id_developers JOIN projects p ON dp.id_project=p.id_project " +
                "AND p.id_project=?;";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return DevelopersConverter.toDevelopersDAOCollection(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public static List<DevelopersDAO> listDevelopersByBranch(Branch branch) {
        String query = "SELECT d.id_developers, d.name, d.age, d.sex, d.id_company, d.salary FROM " +
                "developers_skills ds JOIN developers d ON d.id_developers=ds.id_developers JOIN " +
                "skills s ON ds.id_skill=s.id_skill AND s.branch=?;";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setObject(1, branch.getName(), Types.OTHER);
            ResultSet resultSet = preparedStatement.executeQuery();
            return DevelopersConverter.toDevelopersDAOCollection(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public static List<DevelopersDAO> listDevelopersByLevel(Level level) {
        String query = "SELECT d.id_developers, d.name, d.age, d.sex, d.id_company, d.salary FROM " +
                "developers_skills ds JOIN developers d ON d.id_developers=ds.id_developers JOIN " +
                "skills s ON ds.id_skill=s.id_skill AND s.level=?;";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setObject(1, level.getName(), Types.OTHER);
            ResultSet resultSet = preparedStatement.executeQuery();
            return DevelopersConverter.toDevelopersDAOCollection(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }


    public static List<ProjectSelect> listProjectWithCountDeveloper() {
        String query = "SELECT p.date_create, p.name_project, COUNT(dp.id_developers) AS count FROM" +
                " developers_projects dp JOIN projects p ON dp.id_project=p.id_project GROUP BY p.id_project;";
        List<ProjectSelect> result = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(new ProjectSelect(resultSet.getDate("date_create"),
                        resultSet.getString("name_project"), resultSet.getInt("count")));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
