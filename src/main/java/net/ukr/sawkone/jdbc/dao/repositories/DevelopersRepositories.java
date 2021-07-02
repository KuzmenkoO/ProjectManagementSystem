package net.ukr.sawkone.jdbc.dao.repositories;

import net.ukr.sawkone.jdbc.config.DatabaseConnectionManager;
import net.ukr.sawkone.jdbc.dao.entity.DevelopersDAO;
import net.ukr.sawkone.jdbc.servise.DevelopersConverter;
import net.ukr.sawkone.view.View;

import java.sql.*;
import java.util.Collections;
import java.util.List;

public class DevelopersRepositories implements Repository<DevelopersDAO> {
    private static final String SELECT_DEVELOPERS_BY_ID = "SELECT id_developers, name, age, sex, id_company, " +
            "salary FROM developers WHERE id_developers = ?;";
    private static final String INSERT = "INSERT INTO developers(name, age, sex, id_company, salary) " +
            "VALUES (?, ?, CAST(? AS sex_request), ?, ?);";
    private static final String FIND_OLL = "SELECT id_developers, name, age, sex, id_company, salary FROM developers;";
    private static final String UPDATE = "UPDATE developers SET name=?, age=?, sex=CAST(? AS sex_request), id_company=?, " +
            "salary=? WHERE id_developers=?;";
    private static final String DELETE = "DELETE FROM developers WHERE id_developers=?;";
    private final DatabaseConnectionManager connectionManager;
    private View view;

    public DevelopersRepositories(DatabaseConnectionManager connectionManager, View view) {
        this.connectionManager = connectionManager;
        this.view = view;
    }

    public DevelopersDAO findById(long id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEVELOPERS_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return DevelopersConverter.toDevelopers(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public DevelopersDAO create(DevelopersDAO entity) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getAge());
            preparedStatement.setString(3, entity.getSex().getName());
            preparedStatement.setLong(4, entity.getIdCompany());
            preparedStatement.setDouble(5, entity.getSalary());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            return DevelopersConverter.toDevelopers(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(DevelopersDAO entity) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getAge());
            preparedStatement.setString(3, entity.getSex().getName());
            preparedStatement.setLong(4, entity.getIdCompany());
            preparedStatement.setDouble(5, entity.getSalary());
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
            view.write("Developer is delete");
        } catch (SQLException ex) {
            System.err.println("Error. You cannot uninstall the developer right now");
        }
    }

    @Override
    public List<DevelopersDAO> findAll() {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_OLL);
            ResultSet resultSet = preparedStatement.executeQuery();
            return DevelopersConverter.toDevelopersDAOCollection(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

}
