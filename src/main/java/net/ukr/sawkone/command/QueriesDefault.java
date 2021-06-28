package net.ukr.sawkone.command;

import net.ukr.sawkone.jdbc.config.DatabaseConnectionManager;
import net.ukr.sawkone.jdbc.dao.repositories.relations.RelationshipsTables;
import net.ukr.sawkone.jdbc.dto.Branch;
import net.ukr.sawkone.jdbc.dto.Level;
import net.ukr.sawkone.view.View;

public class QueriesDefault implements Command {
    private final DatabaseConnectionManager cm;
    private View view;

    public QueriesDefault(View view, DatabaseConnectionManager cm) {
        this.view = view;
        this.cm = cm;

    }

    @Override
    public void process() {
        boolean isFieldBlank = true;
        long idProject;

        while (isFieldBlank) {
            try {
                view.write("salary (amount) of all developers of a separate project\nenter the project number from 1 to 9");
                idProject = Integer.parseInt(view.read());
                System.out.println(RelationshipsTables.sumSalaryDevelopersByProject(idProject)); // Зарплата усіх розробників по проекту
                view.write("\nList of developers of a separate project by number - " + idProject);
                System.out.println(RelationshipsTables.listDevelopersByProject(idProject)); // список разработчиков отдельного проекта;
                isFieldBlank = false;
            } catch (Exception e) {
                System.out.println("error is not a number, enter the number");
            }
        }
        view.write("\nList of all Java developers");
        System.out.println(RelationshipsTables.listDevelopersByBranch(Branch.JAVA)); //список всех Java разработчиков;
        view.write("\nList of all middle developers");
        System.out.println(RelationshipsTables.listDevelopersByLevel(Level.MIDDLE)); //список всех middle разработчиков
        view.write("\nlist of projects");
        System.out.println(RelationshipsTables.listProjectWithCountDeveloper()); //список проектов в следующем формате: дата создания - название проекта - количество разработчиков на этом проекте.
    }

    @Override
    public String commandName() {
        return "queries";
    }
}
