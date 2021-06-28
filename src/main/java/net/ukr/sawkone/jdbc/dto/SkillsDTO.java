package net.ukr.sawkone.jdbc.dto;

import java.util.Objects;

public class SkillsDTO {
    private long id;
    private Branch branch;
    private Level level;

    public SkillsDTO(Branch branch, Level level) {
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
        SkillsDTO skillsDTO = (SkillsDTO) o;
        return id == skillsDTO.id && branch == skillsDTO.branch && level == skillsDTO.level;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, branch, level);
    }

    @Override
    public String toString() {
        return "SkillsDTO{" +
                "id=" + id +
                ", branch=" + branch +
                ", level=" + level +
                '}';
    }
}
