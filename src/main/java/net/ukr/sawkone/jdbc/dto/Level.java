package net.ukr.sawkone.jdbc.dto;

import java.util.Arrays;

public enum Level {
    JUNIOR("Junior"),
    MIDDLE("Middle"),
    SENIOR("Senior");

    private String title;

    Level(String title) {
        this.title = title;
    }

    public static Level findByName(String name) throws Throwable {
        return Arrays.stream(Level.values())
                .filter(level -> level.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new Throwable("Level with name " + name + " doesn't exists"));
    }

    public String getName() {
        return title;
    }
}
