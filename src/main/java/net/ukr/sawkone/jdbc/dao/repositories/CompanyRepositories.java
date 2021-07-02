package net.ukr.sawkone.jdbc.dao.repositories;

import net.ukr.sawkone.jdbc.config.DatabaseConnectionManager;
import net.ukr.sawkone.jdbc.dao.entity.CompaniesDAO;
import net.ukr.sawkone.jdbc.servise.CompanyConverter;

import java.sql.*;
import java.util.Collections;
import java.util.List;

public class CompanyRepositories implements Repository<CompaniesDAO> {
    private static final String SELECT_COMPANY_BY_ID = "SELECT id_company, name_company, city FROM companies " +
            "WHERE id_company = ?;";
    private static final String INSERT = "INSERT INTO companies(name_company, city) VALUES (?, ?);";
    private static final String FIND_OLL = "SELECT id_company, name_company, city FROM companies;";
    private static final String UPDATE = "UPDATE companies SET name_company=?, city=? WHERE id_company=?;";
    private static final String DELETE = "DELETE FROM companies WHERE id_company=?;";
    private final DatabaseConnectionManager connectionManager;

    public CompanyRepositories(DatabaseConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public CompaniesDAO findById(long id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COMPANY_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return CompanyConverter.toCompany(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public CompaniesDAO create(CompaniesDAO entity) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getNameCompany());
            preparedStatement.setString(2, entity.getCity());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            return CompanyConverter.toCompany(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(CompaniesDAO entity) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, entity.getNameCompany());
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
        } catch (SQLException ex) {
            System.err.println("Error. You cannot uninstall the company right now");
        }
    }

    @Override
    public List<CompaniesDAO> findAll() {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_OLL);
            ResultSet resultSet = preparedStatement.executeQuery();
            return CompanyConverter.toCompanyDAOCollection(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
