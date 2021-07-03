package net.ukr.sawkone.command;

import net.ukr.sawkone.jdbc.config.DatabaseConnectionManager;
import net.ukr.sawkone.jdbc.dao.entity.ProjectsDAO;
import net.ukr.sawkone.jdbc.dao.repositories.ProjectsRepositories;
import net.ukr.sawkone.jdbc.dao.repositories.Repository;
import net.ukr.sawkone.jdbc.servise.CheckEnteredData;
import net.ukr.sawkone.view.View;

import java.time.LocalDate;

public class Projects implements Command {
    private View view;
    private Repository<ProjectsDAO> projectsDAORepository;
    private CheckEnteredData check;

    public Projects(View view, DatabaseConnectionManager cm, CheckEnteredData check) {
        this.view = view;
        this.projectsDAORepository = new ProjectsRepositories(cm, view);
        this.check = check;
    }

    @Override
    public void process() {
        boolean isNotExit = true;
        while (isNotExit) {
            view.write("""
                    Enter number command:
                    1 - create;
                    2 - delete by id;
                    3 - update by id;
                    4 - list projects;
                    5 - find by id
                    6 - exit the Projects menu""");
            int numberCommand = 0;
            try {
                numberCommand = Integer.parseInt(view.read());
            } catch (Exception e) {
                view.write("Wrong command is entered");
            }
            switch (numberCommand) {
                case 1 -> {
                    ProjectsDAO projectsDAO = new ProjectsDAO();
                    projectsDAO.setNameProject(check.orLineIsEmpty("Enter name project"));
                    projectsDAO.setCost(check.orNumberDouble("Enter cost project"));
                    projectsDAO.setIdCustomer(check.orReallyIsIdCustomers("Enter the customer id for the project"));
                    projectsDAO.setIdCompany(check.orReallyIsIdCompanies("Enter the company id for the project"));
                    projectsDAO.setDate(LocalDate.now());
                    System.out.println(projectsDAORepository.create(projectsDAO));
                }
                case 2 -> projectsDAORepository.deleteById(check.orReallyIsIdProjects("Enter number id project for delete"));
                case 3 -> {
                    ProjectsDAO projectsUpdate = new ProjectsDAO();
                    projectsUpdate.setId(check.orReallyIsIdProjects("Enter number id project for update"));
                    projectsUpdate.setNameProject(check.orLineIsEmpty("Enter new name project"));
                    projectsUpdate.setCost(check.orNumberDouble("Enter new cost project"));
                    projectsUpdate.setIdCustomer(check.orReallyIsIdCustomers("Enter a new customer id for the project"));
                    projectsUpdate.setIdCompany(check.orReallyIsIdCompanies("Enter a new company id for the project"));
                    projectsUpdate.setDate(LocalDate.now());
                    projectsDAORepository.update(projectsUpdate);
                    view.write("project is update");
                }
                case 4 -> view.write(projectsDAORepository.findAll().toString());
                case 5 -> {
                    long idProject = check.orReallyIsIdProjects("Enter number id project for find");
                    view.write(projectsDAORepository.findById(idProject).toString());
                }
                case 6 -> isNotExit = false;
                default -> view.write("select another command");
            }
        }
    }

    @Override
    public String commandName() {
        return "projects";
    }
}
