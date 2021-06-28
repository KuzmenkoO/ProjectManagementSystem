package net.ukr.sawkone.jdbc.dto;

import net.ukr.sawkone.jdbc.dao.entity.ProjectsDAO;
import net.ukr.sawkone.jdbc.dao.entity.SkillsDAO;

import java.util.List;
import java.util.Objects;

public class DevelopersDTO {
    private long id;
    private String name;
    private int age;
    private Sex sex;
    private long idCompany;
    private double salary;
    private List<SkillsDAO> skills;
    private List<ProjectsDAO> projects;

    public DevelopersDTO() {
    }

    public DevelopersDTO(String name, int age, Sex sex, long idCompany, double salary, List<SkillsDAO> skills,
                         List<ProjectsDAO> projects) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.idCompany = idCompany;
        this.salary = salary;
        this.skills = skills;
        this.projects = projects;

    }

    public DevelopersDTO(long id, String name, int age, Sex sex, long idCompany, double salary,
                         List<SkillsDAO> skills, List<ProjectsDAO> projects) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.idCompany = idCompany;
        this.salary = salary;
        this.skills = skills;
        this.projects = projects;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public long getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(long idCompany) {
        this.idCompany = idCompany;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public List<SkillsDAO> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillsDAO> skills) {
        this.skills = skills;
    }

    public List<ProjectsDAO> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectsDAO> projects) {
        this.projects = projects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DevelopersDTO that = (DevelopersDTO) o;
        return id == that.id && age == that.age && idCompany == that.idCompany && Double.compare(that.salary, salary) == 0 && Objects.equals(name, that.name) && sex == that.sex && Objects.equals(skills, that.skills) && Objects.equals(projects, that.projects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, sex, idCompany, salary, skills, projects);
    }

    @Override
    public String toString() {
        return "Developers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", idCompany=" + idCompany +
                ", salary=" + salary +
                ", skills=" + skills +
                ", projects=" + projects +
                '}';
    }
}
