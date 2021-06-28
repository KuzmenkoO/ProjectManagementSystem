package net.ukr.sawkone.jdbc.servise;

import net.ukr.sawkone.jdbc.dao.entity.DevelopersDAO;
import net.ukr.sawkone.jdbc.dao.repositories.relations.RelationshipsTables;
import net.ukr.sawkone.jdbc.dto.DevelopersDTO;
import net.ukr.sawkone.jdbc.dto.Sex;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DevelopersConverter {

    public static DevelopersDAO toDevelopers(DevelopersDTO developersDTO) {
        return new DevelopersDAO(developersDTO.getName(), developersDTO.getAge(), developersDTO.getSex(),
                developersDTO.getIdCompany(), developersDTO.getSalary(),
                developersDTO.getSkills(), developersDTO.getProjects());
    }

    public static DevelopersDTO fromDevelopers(DevelopersDAO developersDAO) {
        return new DevelopersDTO(developersDAO.getName(), developersDAO.getAge(), developersDAO.getSex(), developersDAO.getIdCompany(), developersDAO.getSalary(), developersDAO.getSkills(), developersDAO.getProjects());
    }

    public static DevelopersDAO toDevelopers(ResultSet resultSet) throws SQLException {
        DevelopersDAO developersDAO = new DevelopersDAO();
        try {
            while (resultSet.next()) {
                developersDAO.setId(resultSet.getLong("id_developers"));
                developersDAO.setName(resultSet.getString("name"));
                developersDAO.setAge(resultSet.getInt("age"));
                developersDAO.setSex(Sex.findByName(resultSet.getString("sex")));
                developersDAO.setIdCompany(resultSet.getLong("id_company"));
                developersDAO.setSalary(resultSet.getDouble("salary"));
                developersDAO.setSkills(RelationshipsTables.getSkillsDeveloper(developersDAO.getId()));
                developersDAO.setProjects(RelationshipsTables.getProjectDeveloper(developersDAO.getId()));
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return developersDAO;
    }

    public static List<DevelopersDAO> toDevelopersDAOCollection(ResultSet resultSet) throws SQLException {
        List<DevelopersDAO> books = new ArrayList<>();
        while (resultSet.next()) {
            books.add(prepareDevelopersDAO(resultSet));
        }
        return books;
    }

    private static DevelopersDAO prepareDevelopersDAO(ResultSet resultSet) throws SQLException {
        DevelopersDAO developersDAO = new DevelopersDAO();
        try {
            developersDAO.setId(resultSet.getLong("id_developers"));
            developersDAO.setName(resultSet.getString("name"));
            developersDAO.setAge(resultSet.getInt("age"));
            developersDAO.setSex(Sex.findByName(resultSet.getString("sex")));
            developersDAO.setIdCompany(resultSet.getLong("id_company"));
            developersDAO.setSalary(resultSet.getDouble("salary"));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return developersDAO;
    }


}
