package net.ukr.sawkone.command;

import net.ukr.sawkone.jdbc.config.DatabaseConnectionManager;
import net.ukr.sawkone.jdbc.dao.entity.CompaniesDAO;
import net.ukr.sawkone.jdbc.dao.repositories.CompanyRepositories;
import net.ukr.sawkone.jdbc.dao.repositories.Repository;
import net.ukr.sawkone.jdbc.servise.CheckEnteredData;
import net.ukr.sawkone.view.View;

public class Company implements Command {
    private Repository<CompaniesDAO> companiesDAORepository;
    private View view;
    private CheckEnteredData check;

    public Company(View view, DatabaseConnectionManager cm) {
        this.view = view;
        this.companiesDAORepository = new CompanyRepositories(cm);
        this.check = new CheckEnteredData(view);
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
                    4 - list companies;
                    5 - find by id
                    6 - exit the company menu""");
            int numberCommand = 0;
            try {
                numberCommand = Integer.parseInt(view.read());
            } catch (Exception e) {
                view.write("Wrong command is entered");
            }
            switch (numberCommand) {
                case 1 -> {
                    CompaniesDAO companiesDAO = new CompaniesDAO();
                    companiesDAO.setNameCompany(check.orLineIsEmpty("Enter name company"));
                    companiesDAO.setCity(check.orLineIsEmpty("Enter city company"));
                    System.out.println(companiesDAORepository.create(companiesDAO));
                }
                case 2 -> {
                    companiesDAORepository.deleteById(check.orNumberLong("Enter number id company for delete"));
                    view.write("company is delete");
                }
                case 3 -> {
                    CompaniesDAO companiesUpdate = new CompaniesDAO();
                    companiesUpdate.setId(check.orNumberLong("Enter number id company for update"));
                    companiesUpdate.setNameCompany(check.orLineIsEmpty("Enter new name company"));
                    companiesUpdate.setCity(check.orLineIsEmpty("Enter new city company"));
                    companiesDAORepository.update(companiesUpdate);
                    view.write("company is update");
                }
                case 4 -> view.write(companiesDAORepository.findAll().toString());
                case 5 -> {
                    long idCompany = check.orNumberLong("Enter number id company for find");
                    view.write(companiesDAORepository.findById(idCompany).toString());
                }
                case 6 -> isNotExit = false;
                default -> view.write("select another command");
            }
        }
    }

    @Override
    public String commandName() {
        return "company";
    }
}
