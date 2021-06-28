package net.ukr.sawkone;

import net.ukr.sawkone.controler.Controller;
import net.ukr.sawkone.jdbc.config.DatabaseConnectionManager;
import net.ukr.sawkone.jdbc.util.PropertiesConfig;
import net.ukr.sawkone.view.Console;
import net.ukr.sawkone.view.View;

public class Main {
    public static void main(String[] args) {
        View view = new Console();
        PropertiesConfig propertiesConfig = new PropertiesConfig("application.properties");
        DatabaseConnectionManager cm = new DatabaseConnectionManager(propertiesConfig);
        Controller controller = new Controller(view, cm);
        controller.run();

    }
}
