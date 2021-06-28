package net.ukr.sawkone.jdbc.dao.repositories.relations;

import net.ukr.sawkone.jdbc.config.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RelationshipsBetweenTables {
    private final DatabaseConnectionManager connectionManager;

    public RelationshipsBetweenTables(DatabaseConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public void createDevelopersProjects(long idDevelopers, long idProjects) {
        String query = "INSERT INTO developers_projects(id_developers, id_project) VALUES (?,?);";
        createDeleteById(idDevelopers, idProjects, query);
    }

    public void deleteDevelopersProjects(long idDevelopers, long idProjects) {
        String query = "DELETE FROM developers_projects WHERE id_developers=? AND id_project=?";
        createDeleteById(idDevelopers, idProjects, query);
    }

    public void createDevelopersSkills(long idDevelopers, long idSkills) {
        String query = "INSERT INTO developers_skills(id_developers, id_skill) VALUES (?,?);";
        createDeleteById(idDevelopers, idSkills, query);
    }

    public void deleteDevelopersSkills(long idDevelopers, long idSkills) {
        String query = "DELETE FROM developers_skills WHERE id_developers=? AND id_skill=?";
        createDeleteById(idDevelopers, idSkills, query);
    }


    private void createDeleteById(long idOne, long idTwo, String query) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, idOne);
            preparedStatement.setLong(2, idTwo);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
