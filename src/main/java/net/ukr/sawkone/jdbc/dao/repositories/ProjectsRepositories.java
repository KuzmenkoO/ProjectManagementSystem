package net.ukr.sawkone.jdbc.dao.repositories;

import net.ukr.sawkone.jdbc.config.DatabaseConnectionManager;
import net.ukr.sawkone.jdbc.dao.entity.ProjectsDAO;
import net.ukr.sawkone.jdbc.servise.ProjectConverter;

import java.sql.*;
import java.util.Collections;
import java.util.List;

public class ProjectsRepositories implements Repository<ProjectsDAO> {
    private static final String SELECT_PROJECT_BY_ID = "SELECT id_project, name_project, date_create, id_customer, " +
            "id_company, cost FROM projects WHERE id_project = ?;";
    private static final String INSERT = "INSERT INTO projects(name_project, date_create, id_customer, id_company, cost) " +
            "VALUES (?, ?, ?, ?, ?);";
    private static final String FIND_OLL = "SELECT id_project, name_project, date_create, id_customer, id_company, cost " +
            "FROM projects;";
    private static final String UPDATE = "UPDATE projects SET name_project=?, date_create=?, id_customer=?, id_company=?, cost=? " +
            "WHERE id_project=?;";
    private static final String DELETE = "DELETE FROM projects WHERE id_project=?;";
    private final DatabaseConnectionManager connectionManager;

    public ProjectsRepositories(DatabaseConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public ProjectsDAO findById(long id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROJECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return ProjectConverter.toProjects(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ProjectsDAO create(ProjectsDAO entity) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getNameProject());
            preparedStatement.setDate(2, Date.valueOf(entity.getDate()));
            preparedStatement.setLong(3, entity.getIdCustomer());
            preparedStatement.setLong(4, entity.getIdCompany());
            preparedStatement.setDouble(5, entity.getCost());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            return ProjectConverter.toProjects(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(ProjectsDAO entity) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, entity.getNameProject());
            preparedStatement.setDate(2, Date.valueOf(entity.getDate()));
            preparedStatement.setLong(3, entity.getIdCustomer());
            preparedStatement.setLong(4, entity.getIdCompany());
            preparedStatement.setDouble(5, entity.getCost());
            preparedStatement.setLong(6, entity.getId());
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteById(long id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<ProjectsDAO> findAll() {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_OLL);
            ResultSet resultSet = preparedStatement.executeQuery();
            return ProjectConverter.toProjectsDAOCollection(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
