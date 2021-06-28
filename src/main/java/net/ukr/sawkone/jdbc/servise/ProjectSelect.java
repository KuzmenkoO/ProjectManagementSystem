package net.ukr.sawkone.jdbc.servise;

import java.util.Date;

public class ProjectSelect {
    private Date date;
    private String name;
    private Integer count;

    public ProjectSelect(Date date, String name, Integer count) {
        this.date = date;
        this.name = name;
        this.count = count;
    }

    @Override
    public String toString() {
        return "Projects{" +
                "date=" + date +
                ", name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
