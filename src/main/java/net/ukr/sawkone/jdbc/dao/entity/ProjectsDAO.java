package net.ukr.sawkone.jdbc.dao.entity;

import java.time.LocalDate;
import java.util.Objects;

public class ProjectsDAO {
    private long id;
    private String nameProject;
    private LocalDate date;
    private long idCustomer;
    private long idCompany;
    private double cost;

    public ProjectsDAO() {
    }

    public ProjectsDAO(long id, String nameProject, LocalDate date, long idCustomer, long idCompany, double cost) {
        this.id = id;
        this.nameProject = nameProject;
        this.date = date;
        this.idCustomer = idCustomer;
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

    public long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(long idCustomer) {
        this.idCustomer = idCustomer;
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
        ProjectsDAO that = (ProjectsDAO) o;
        return id == that.id && idCustomer == that.idCustomer && idCompany == that.idCompany && Double.compare(that.cost, cost) == 0 && Objects.equals(nameProject, that.nameProject) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameProject, date, idCustomer, idCompany, cost);
    }

    @Override
    public String toString() {
        return "ProjectsDAO{" +
                "id=" + id +
                ", nameProject='" + nameProject + '\'' +
                ", date=" + date +
                ", idCostumer=" + idCustomer +
                ", idCompany=" + idCompany +
                ", cost=" + cost +
                '}';
    }
}
