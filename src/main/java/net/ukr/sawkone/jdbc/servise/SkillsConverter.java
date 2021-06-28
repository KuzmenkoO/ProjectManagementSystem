package net.ukr.sawkone.jdbc.servise;

import net.ukr.sawkone.jdbc.dao.entity.SkillsDAO;
import net.ukr.sawkone.jdbc.dto.Branch;
import net.ukr.sawkone.jdbc.dto.Level;
import net.ukr.sawkone.jdbc.dto.SkillsDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SkillsConverter {
    public static SkillsDAO toSkills(SkillsDTO skillsDTO) {
        return new SkillsDAO(skillsDTO.getId(), skillsDTO.getBranch(), skillsDTO.getLevel());
    }

    public static SkillsDTO fromSkills(SkillsDAO skillsDAO) {
        return new SkillsDTO(skillsDAO.getBranch(), skillsDAO.getLevel());
    }

    public static SkillsDAO toSkills(ResultSet resultSet) throws SQLException {
        SkillsDAO skillsDAO = new SkillsDAO();
        try {
            while (resultSet.next()) {
                skillsDAO.setId(resultSet.getLong("id_skill"));
                skillsDAO.setBranch(Branch.findByName(resultSet.getString("branch")));
                skillsDAO.setLevel(Level.findByName(resultSet.getString("level")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return skillsDAO;
    }

    public static List<SkillsDAO> toSkillsDAOCollection(ResultSet resultSet) throws SQLException {
        List<SkillsDAO> skillsDAOList = new ArrayList<>();
        while (resultSet.next()) {
            skillsDAOList.add(prepareSkillsDAO(resultSet));
        }
        return skillsDAOList;
    }

    private static SkillsDAO prepareSkillsDAO(ResultSet resultSet) throws SQLException {
        SkillsDAO skillsDAO = new SkillsDAO();
        try {
            skillsDAO.setId(resultSet.getLong("id_skill"));
            skillsDAO.setBranch(Branch.findByName(resultSet.getString("branch")));
            skillsDAO.setLevel(Level.findByName(resultSet.getString("level")));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return skillsDAO;
    }
}
