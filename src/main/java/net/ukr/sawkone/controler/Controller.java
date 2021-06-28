package net.ukr.sawkone.controler;

import net.ukr.sawkone.command.*;
import net.ukr.sawkone.jdbc.config.DatabaseConnectionManager;
import net.ukr.sawkone.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {
    private View view;
    private List<Command> commands;
    private DatabaseConnectionManager cm;


    public Controller(View view, DatabaseConnectionManager cm) {
        this.view = view;
        this.cm = cm;
        this.commands = new ArrayList<>(Arrays.asList(
                new Help(view),
                new QueriesDefault(view, cm),
                new Company(view, cm),
                new Customers(view, cm),
                new Projects(view, cm),
                new Developers(view, cm)
        ));
    }

    public void run() {
        view.write("Welcome to the Projects management");
        doCommand();
    }

    private void doCommand() {
        boolean isNotExit = true;
        while (isNotExit) {
            view.write("Please enter a command or help to retrieve command list");
            String inputCommand = view.read();
            for (Command command : commands) {
                if (command.canProcess(inputCommand)) {
                    command.process();
                    break;
                } else if (inputCommand.equalsIgnoreCase("exit")) {
                    view.write("Good Bye!");
                    isNotExit = false;
                    break;
                }
            }
        }
    }
}
