package net.ukr.sawkone.jdbc.dao.entity;

import net.ukr.sawkone.jdbc.dto.Branch;
import net.ukr.sawkone.jdbc.dto.Level;

import java.util.Objects;

public class SkillsDAO {
    private long id;
    private Branch branch;
    private Level level;

    public SkillsDAO() {
    }

    public SkillsDAO(long id, Branch branch, Level level) {
        this.id = id;
        this.branch = branch;
        this.level = level;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillsDAO skillsDAO = (SkillsDAO) o;
        return id == skillsDAO.id && branch == skillsDAO.branch && level == skillsDAO.level;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, branch, level);
    }

    @Override
    public String toString() {
        return "SkillsDAO{" +
                "id=" + id +
                ", branch=" + branch +
                ", level=" + level +
                '}';
    }
}
