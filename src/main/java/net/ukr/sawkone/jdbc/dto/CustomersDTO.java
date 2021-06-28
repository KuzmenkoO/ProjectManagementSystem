package net.ukr.sawkone.jdbc.dto;

import java.util.Objects;

public class CustomersDTO {
    private long id;
    private String nameCustomer;
    private String city;

    public CustomersDTO(String nameCustomer, String city) {
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
        CustomersDTO that = (CustomersDTO) o;
        return id == that.id && Objects.equals(nameCustomer, that.nameCustomer) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameCustomer, city);
    }

    @Override
    public String toString() {
        return "CustomersDTO{" +
                "id=" + id +
                ", nameCustomer='" + nameCustomer + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
