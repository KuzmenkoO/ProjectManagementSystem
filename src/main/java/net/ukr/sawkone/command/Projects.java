package net.ukr.sawkone.command;

import net.ukr.sawkone.jdbc.config.DatabaseConnectionManager;
import net.ukr.sawkone.jdbc.dao.entity.ProjectsDAO;
import net.ukr.sawkone.jdbc.dao.repositories.ProjectsRepositories;
import net.ukr.sawkone.jdbc.dao.repositories.Repository;
import net.ukr.sawkone.view.View;

import java.time.LocalDate;

public class Projects implements Command {
    private View view;
    private Repository<ProjectsDAO> projectsDAORepository;

    public Projects(View view, DatabaseConnectionManager cm) {
        this.view = view;
        projectsDAORepository = new ProjectsRepositories(cm);
    }

    @Override
    public void process() {
        boolean isNotExit = true;
        while (isNotExit) {
            view.write("Enter number command:\n1 - create;\n2 - delete by id;\n3 - update by id;\n4 - list projects;" +
                    "\n5 - find by id\n6 - exit the Projects menu");
            int numberCommand = 0;
            try {
                numberCommand = Integer.parseInt(view.read());
            } catch (Exception e) {
                e.printStackTrace();
                view.write("Enter number command");
                numberCommand = Integer.parseInt(view.read());
            }
            switch (numberCommand) {
                case 1 -> {
                    ProjectsDAO projectsDAO = new ProjectsDAO();
                    view.write("Enter name project");
                    projectsDAO.setNameProject(view.read());
                    view.write("Enter cost project");
                    projectsDAO.setCost(Double.parseDouble(view.read()));
                    view.write("Enter id customer project");
                    projectsDAO.setIdCustomer(Long.parseLong(view.read()));
                    view.write("Enter id company project");
                    projectsDAO.setIdCompany(Long.parseLong(view.read()));
                    projectsDAO.setDate(LocalDate.now());
                    System.out.println(projectsDAORepository.create(projectsDAO));
                }
                case 2 -> {
                    view.write("Enter number id project for delete");
                    projectsDAORepository.deleteById(Integer.parseInt(view.read()));
                    view.write("project is delete");
                }
                case 3 -> {
                    ProjectsDAO projectsUpdate = new ProjectsDAO();
                    view.write("Enter number id project for update");
                    projectsUpdate.setId(Long.parseLong(view.read()));
                    view.write("Enter new name project");
                    projectsUpdate.setNameProject(view.read());
                    view.write("Enter new cost project");
                    projectsUpdate.setCost(Double.parseDouble(view.read()));
                    view.write("Enter new id customer project");
                    projectsUpdate.setIdCustomer(Long.parseLong(view.read()));
                    view.write("Enter new id company project");
                    projectsUpdate.setIdCompany(Long.parseLong(view.read()));
                    projectsUpdate.setDate(LocalDate.now());
                    projectsDAORepository.update(projectsUpdate);
                    view.write("project is update");
                }
                case 4 -> view.write(projectsDAORepository.findAll().toString());
                case 5 -> {
                    view.write("Enter number id project for find");
                    long idProject = Long.parseLong(view.read());
                    view.write(projectsDAORepository.findById(idProject).toString());
                }
                case 6 -> {
                    isNotExit = false;
                }
                default -> view.write("select another command");
            }
        }

    }

    @Override
    public String commandName() {
        return "projects";
    }
}
