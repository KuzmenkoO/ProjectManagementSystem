package net.ukr.sawkone.jdbc.dto;

import java.util.Arrays;

public enum Sex {
    MALE("male"),
    FEMALE("female");

    private String title;

    Sex(String title) {
        this.title = title;
    }

    public static Sex findByName(String name) throws Throwable {
        return Arrays.stream(Sex.values())
                .filter(sex -> sex.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new Throwable("Sex with name " + name + " doesn't exists"));
    }

    public String getName() {
        return title;
    }
}
