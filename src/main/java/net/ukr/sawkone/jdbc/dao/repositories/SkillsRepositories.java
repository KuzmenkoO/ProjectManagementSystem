package net.ukr.sawkone.jdbc.dao.repositories;

import net.ukr.sawkone.jdbc.config.DatabaseConnectionManager;
import net.ukr.sawkone.jdbc.dao.entity.SkillsDAO;
import net.ukr.sawkone.jdbc.servise.SkillsConverter;

import java.sql.*;
import java.util.Collections;
import java.util.List;

public class SkillsRepositories implements Repository<SkillsDAO> {

    private static final String SELECT_SKILL_BY_ID = "SELECT id_skill, level, branch FROM skills " +
            "WHERE id_skill = ?;";
    private static final String INSERT = "INSERT INTO skills(level, branch) VALUES (?, ?);";
    private static final String FIND_OLL = "SELECT id_skill, level, branch FROM skills;";
    private static final String UPDATE = "UPDATE skills SET level=?, branch=? WHERE id_skill=?;";
    private static final String DELETE = "DELETE FROM skills WHERE id_skill=?;";
    private final DatabaseConnectionManager connectionManager;

    public SkillsRepositories(DatabaseConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public SkillsDAO findById(long id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SKILL_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return SkillsConverter.toSkills(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public SkillsDAO create(SkillsDAO entity) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getBranch().getName());
            preparedStatement.setString(2, entity.getLevel().getName());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            return SkillsConverter.toSkills(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(SkillsDAO entity) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, entity.getBranch().getName());
            preparedStatement.setString(2, entity.getLevel().getName());
            preparedStatement.setLong(3, entity.getId());
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
    public List<SkillsDAO> findAll() {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_OLL);
            ResultSet resultSet = preparedStatement.executeQuery();
            return SkillsConverter.toSkillsDAOCollection(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
