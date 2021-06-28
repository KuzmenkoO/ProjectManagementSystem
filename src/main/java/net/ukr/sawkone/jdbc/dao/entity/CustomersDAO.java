package net.ukr.sawkone.jdbc.dao.entity;

import java.util.Objects;

public class CustomersDAO {
    private long id;
    private String nameCustomer;
    private String city;

    public CustomersDAO() {
    }

    public CustomersDAO(long id, String nameCustomer, String city) {
        this.id = id;
        this.nameCustomer = nameCustomer;
        this.city = city;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
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
        CustomersDAO that = (CustomersDAO) o;
        return id == that.id && Objects.equals(nameCustomer, that.nameCustomer) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameCustomer, city);
    }

    @Override
    public String toString() {
        return "CustomersDAO{" +
                "id=" + id +
                ", nameCustomer='" + nameCustomer + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
