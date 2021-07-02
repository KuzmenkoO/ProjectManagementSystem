package net.ukr.sawkone.jdbc.dao.repositories;

import net.ukr.sawkone.jdbc.config.DatabaseConnectionManager;
import net.ukr.sawkone.jdbc.dao.entity.CustomersDAO;
import net.ukr.sawkone.jdbc.servise.CustomersConverter;
import net.ukr.sawkone.view.View;

import java.sql.*;
import java.util.Collections;
import java.util.List;

public class CustomersRepositories implements Repository<CustomersDAO> {
    private static final String SELECT_COMPANY_BY_ID = "SELECT id_customer, name_customer, city FROM customers " +
            "WHERE id_customer = ?;";
    private static final String INSERT = "INSERT INTO customers(name_customer, city) VALUES (?, ?);";
    private static final String FIND_OLL = "SELECT id_customer, name_customer, city FROM customers;";
    private static final String UPDATE = "UPDATE customers SET name_customer=?, city=? WHERE id_customer=?;";
    private static final String DELETE = "DELETE FROM customers WHERE id_customer=?;";
    private final DatabaseConnectionManager connectionManager;
    private View view;

    public CustomersRepositories(DatabaseConnectionManager connectionManager, View view) {
        this.connectionManager = connectionManager;
        this.view = view;
    }

    public CustomersDAO findById(long id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COMPANY_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return CustomersConverter.toCustomers(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public CustomersDAO create(CustomersDAO entity) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getNameCustomer());
            preparedStatement.setString(2, entity.getCity());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            return CustomersConverter.toCustomers(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(CustomersDAO entity) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, entity.getNameCustomer());
            preparedStatement.setString(2, entity.getCity());
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
            view.write("customer is delete");
        } catch (SQLException ex) {
            System.err.println("Error. You cannot uninstall the customer right now");
        }
    }

    @Override
    public List<CustomersDAO> findAll() {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_OLL);
            ResultSet resultSet = preparedStatement.executeQuery();
            return CustomersConverter.toCustomersDAOCollection(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
