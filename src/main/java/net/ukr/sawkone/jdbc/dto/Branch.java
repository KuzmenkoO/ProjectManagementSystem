package net.ukr.sawkone.jdbc.dto;

import java.util.Arrays;

public enum Branch {
    JAVA("Java"),
    C_PLUS("C++"),
    C_SHARP("C#"),
    JAVA_SCRIPT("JS");

    private String title;

    Branch(String title) {
        this.title = title;
    }

    public static Branch findByName(String name) throws Throwable {
        return Arrays.stream(Branch.values())
                .filter(branch -> branch.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new Throwable("Branch with name " + name + " doesn't exists"));
    }

    public String getName() {
        return title;
    }
}
