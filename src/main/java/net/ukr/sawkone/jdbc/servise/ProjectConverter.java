package net.ukr.sawkone.jdbc.servise;

import net.ukr.sawkone.jdbc.dao.entity.ProjectsDAO;
import net.ukr.sawkone.jdbc.dto.ProjectsDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjectConverter {

    public static ProjectsDAO toProjects(ProjectsDTO projectsDTO) {
        return new ProjectsDAO(projectsDTO.getId(), projectsDTO.getNameProject(), projectsDTO.getDate(), projectsDTO.getIdCostumer(),
                projectsDTO.getIdCompany(), projectsDTO.getCost());
    }

    public static ProjectsDTO fromProjects(ProjectsDAO projectsDAO) {
        return new ProjectsDTO(projectsDAO.getNameProject(), projectsDAO.getDate(), projectsDAO.getIdCustomer(), projectsDAO.getIdCompany(),
                projectsDAO.getCost());
    }

    public static ProjectsDAO toProjects(ResultSet resultSet) throws SQLException {
        ProjectsDAO projectsDAO = new ProjectsDAO();
        while (resultSet.next()) {
            projectsDAO.setId(resultSet.getLong("id_project"));
            projectsDAO.setNameProject(resultSet.getString("name_project"));
            projectsDAO.setDate(resultSet.getObject("date_create", LocalDate.class));
            projectsDAO.setIdCompany(resultSet.getLong("id_company"));
            projectsDAO.setIdCustomer(resultSet.getLong("id_customer"));
            projectsDAO.setCost(resultSet.getDouble("cost"));
        }
        return projectsDAO;
    }

    public static List<ProjectsDAO> toProjectsDAOCollection(ResultSet resultSet) throws SQLException {
        List<ProjectsDAO> projects = new ArrayList<>();
        while (resultSet.next()) {
            projects.add(prepareProjectsDAO(resultSet));
        }
        return projects;
    }

    private static ProjectsDAO prepareProjectsDAO(ResultSet resultSet) throws SQLException {
        ProjectsDAO projectsDAO = new ProjectsDAO();
        projectsDAO.setId(resultSet.getLong("id_project"));
        projectsDAO.setNameProject(resultSet.getString("name_project"));
        projectsDAO.setDate(resultSet.getObject("date_create", LocalDate.class));
        projectsDAO.setIdCompany(resultSet.getLong("id_company"));
        projectsDAO.setIdCustomer(resultSet.getLong("id_customer"));
        projectsDAO.setCost(resultSet.getDouble("cost"));
        return projectsDAO;
    }
}
