package net.ukr.sawkone.jdbc.servise;

import net.ukr.sawkone.jdbc.dao.entity.CompaniesDAO;
import net.ukr.sawkone.jdbc.dto.CompaniesDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyConverter {
    public static CompaniesDAO toCompany(CompaniesDTO companiesDTO) {
        return new CompaniesDAO(companiesDTO.getId(), companiesDTO.getNameCompany(), companiesDTO.getCity());
    }

    public static CompaniesDTO fromCompany(CompaniesDAO companiesDAO) {
        return new CompaniesDTO(companiesDAO.getNameCompany(), companiesDAO.getCity());
    }

    public static CompaniesDAO toCompany(ResultSet resultSet) throws SQLException {
        CompaniesDAO company = new CompaniesDAO();
        while (resultSet.next()) {
            getCompanyWithResultSet(resultSet, company);
        }
        return company;
    }

    public static List<CompaniesDAO> toCompanyDAOCollection(ResultSet resultSet) throws SQLException {
        List<CompaniesDAO> companiesDAOList = new ArrayList<>();
        while (resultSet.next()) {
            companiesDAOList.add(prepareCompanyDAO(resultSet));
        }
        return companiesDAOList;
    }

    private static void getCompanyWithResultSet(ResultSet resultSet, CompaniesDAO company) throws SQLException {
        company.setId(resultSet.getLong("id_company"));
        company.setNameCompany(resultSet.getString("name_company"));
        company.setCity(resultSet.getString("city"));
    }

    private static CompaniesDAO prepareCompanyDAO(ResultSet resultSet) throws SQLException {
        CompaniesDAO companiesDAO = new CompaniesDAO();
        getCompanyWithResultSet(resultSet, companiesDAO);
        return companiesDAO;
    }
}
