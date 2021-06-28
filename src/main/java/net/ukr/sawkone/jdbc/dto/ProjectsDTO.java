package net.ukr.sawkone.jdbc.dto;

import java.time.LocalDate;
import java.util.Objects;

public class ProjectsDTO {
    private LocalDate date;
    private long id;
    private String nameProject;
    private long idCostumer;
    private long idCompany;
    private double cost;

    public ProjectsDTO(String nameProject, LocalDate date, long idCostumer, long idCompany, double cost) {
        this.nameProject = nameProject;
        this.date = date;
        this.idCostumer = idCostumer;
        this.idCompany = idCompany;
        this.cost = cost;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameProject() {
        return nameProject;
    }

    public void setNameProject(String nameProject) {
        this.nameProject = nameProject;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public long getIdCostumer() {
        return idCostumer;
    }

    public void setIdCostumer(long idCostumer) {
        this.idCostumer = idCostumer;
    }

    public long getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(long idCompany) {
        this.idCompany = idCompany;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectsDTO that = (ProjectsDTO) o;
        return id == that.id && idCostumer == that.idCostumer && idCompany == that.idCompany && Double.compare(that.cost, cost) == 0 && Objects.equals(date, that.date) && Objects.equals(nameProject, that.nameProject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, id, nameProject, idCostumer, idCompany, cost);
    }

    @Override
    public String toString() {
        return "ProjectsDTO{" +
                "date=" + date +
                ", id=" + id +
                ", nameProject='" + nameProject + '\'' +
                ", idCostumer=" + idCostumer +
                ", idCompany=" + idCompany +
                ", cost=" + cost +
                '}';
    }
}
