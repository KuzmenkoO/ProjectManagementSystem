package net.ukr.sawkone.jdbc.dao.entity;

import java.util.Objects;

public class CompaniesDAO {
    private long id;
    private String nameCompany;
    private String city;

    public CompaniesDAO() {
    }

    public CompaniesDAO(long id, String nameCompany, String city) {
        this.id = id;
        this.nameCompany = nameCompany;
        this.city = city;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompaniesDAO that = (CompaniesDAO) o;
        return id == that.id && Objects.equals(nameCompany, that.nameCompany) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameCompany, city);
    }

    @Override
    public String toString() {
        return "CompaniesDAO{" +
                "id=" + id +
                ", nameCompany='" + nameCompany + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
