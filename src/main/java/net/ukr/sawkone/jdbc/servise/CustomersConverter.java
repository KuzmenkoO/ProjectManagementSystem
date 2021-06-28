package net.ukr.sawkone.jdbc.servise;

import net.ukr.sawkone.jdbc.dao.entity.CustomersDAO;
import net.ukr.sawkone.jdbc.dto.CustomersDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomersConverter {
    public static CustomersDAO toCustomers(CustomersDTO customersDTO) {
        return new CustomersDAO(customersDTO.getId(), customersDTO.getNameCustomer(), customersDTO.getCity());
    }

    public static CustomersDTO fromCustomers(CustomersDAO customersDAO) {
        return new CustomersDTO(customersDAO.getNameCustomer(), customersDAO.getCity());
    }

    public static CustomersDAO toCustomers(ResultSet resultSet) throws SQLException {
        CustomersDAO customersDAO = new CustomersDAO();
        while (resultSet.next()) {
            customersDAO.setId(resultSet.getLong("id_customer"));
            customersDAO.setNameCustomer(resultSet.getString("name_customer"));
            customersDAO.setCity(resultSet.getString("city"));
        }
        return customersDAO;
    }

    public static List<CustomersDAO> toCustomersDAOCollection(ResultSet resultSet) throws SQLException {
        List<CustomersDAO> customersDAOList = new ArrayList<>();
        while (resultSet.next()) {
            customersDAOList.add(prepareCustomersDAO(resultSet));
        }
        return customersDAOList;
    }

    private static CustomersDAO prepareCustomersDAO(ResultSet resultSet) throws SQLException {
        CustomersDAO customersDAO = new CustomersDAO();
        customersDAO.setId(resultSet.getLong("id_customer"));
        customersDAO.setNameCustomer(resultSet.getString("name_customer"));
        customersDAO.setCity(resultSet.getString("city"));
        return customersDAO;
    }
}
