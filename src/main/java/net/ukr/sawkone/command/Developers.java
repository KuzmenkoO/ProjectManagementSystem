package net.ukr.sawkone.command;

import net.ukr.sawkone.jdbc.config.DatabaseConnectionManager;
import net.ukr.sawkone.jdbc.dao.entity.DevelopersDAO;
import net.ukr.sawkone.jdbc.dao.repositories.DevelopersRepositories;
import net.ukr.sawkone.jdbc.dao.repositories.Repository;
import net.ukr.sawkone.jdbc.servise.CheckEnteredData;
import net.ukr.sawkone.view.View;

public class Developers implements Command {
    private View view;
    private Repository<DevelopersDAO> developersDAORepository;
    private CheckEnteredData check;

    public Developers(View view, DatabaseConnectionManager cm, CheckEnteredData check) {
        this.view = view;
        this.developersDAORepository = new DevelopersRepositories(cm, view);
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
                    4 - list developers;
                    5 - find by id
                    6 - exit the developers menu""");
            int numberCommand = 0;
            try {
                numberCommand = Integer.parseInt(view.read());
            } catch (Exception e) {
                view.write("Wrong command is entered");
            }
            switch (numberCommand) {
                case 1 -> {
                    DevelopersDAO developersDAO = new DevelopersDAO();
                    developersDAO.setName(check.orLineIsEmpty("Enter the developer name"));
                    developersDAO.setAge(check.orNumberInt("Enter the age of the developer"));
                    developersDAO.setSex(check.orGivenGender("Enter the developers sex - male or female"));
                    developersDAO.setIdCompany(check.orReallyIsIdCompanies("Enter the id of the company where the developer works"));
                    developersDAO.setSalary(check.orNumberDouble("Enter the developer's salary"));
                    System.out.println(developersDAORepository.create(developersDAO));
                }
                case 2 -> developersDAORepository.deleteById(check.orReallyIsIdDevelopers("Enter number id developers for delete"));
                case 3 -> {
                    DevelopersDAO developersUpdate = new DevelopersDAO();
                    developersUpdate.setId(check.orReallyIsIdDevelopers("Enter number id developers for update"));
                    developersUpdate.setName(check.orLineIsEmpty("Enter a new developer name"));
                    developersUpdate.setAge(check.orNumberInt("Enter a new age of the developer"));
                    developersUpdate.setSex(check.orGivenGender("Enter a new developers sex - male or female"));
                    developersUpdate.setIdCompany(check.orReallyIsIdCompanies("Enter a new id of the company where the developer works"));
                    developersUpdate.setSalary(check.orNumberDouble("Enter a new developer's salary"));
                    developersDAORepository.update(developersUpdate);
                    view.write("Developers is update");
                }
                case 4 -> view.write(developersDAORepository.findAll().toString());
                case 5 -> {
                    long idDeveloper = check.orReallyIsIdDevelopers("Enter the developer id number to search");
                    view.write(developersDAORepository.findById(idDeveloper).toString());
                }
                case 6 -> isNotExit = false;
                default -> view.write("Select another command");
            }
        }
    }

    @Override
    public String commandName() {
        return "developers";
    }
}