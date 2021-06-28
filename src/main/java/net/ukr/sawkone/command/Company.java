package net.ukr.sawkone.command;

import net.ukr.sawkone.jdbc.config.DatabaseConnectionManager;
import net.ukr.sawkone.jdbc.dao.entity.CompaniesDAO;
import net.ukr.sawkone.jdbc.dao.repositories.CompanyRepositories;
import net.ukr.sawkone.jdbc.dao.repositories.Repository;
import net.ukr.sawkone.view.View;

public class Company implements Command {
    private Repository<CompaniesDAO> companiesDAORepository;
    private View view;

    public Company(View view, DatabaseConnectionManager cm) {
        this.view = view;
        this.companiesDAORepository = new CompanyRepositories(cm);
    }

    @Override
    public void process() {
        boolean isNotExit = true;
        while (isNotExit) {
            view.write("Enter number command:\n1 - create;\n2 - delete by id;\n3 - update by id;\n4 - list companies;" +
                    "\n5 - find by id\n6 - exit the company menu");
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
                    CompaniesDAO companiesDAO = new CompaniesDAO();
                    view.write("Enter name company");
                    companiesDAO.setNameCompany(view.read());
                    view.write("Enter city company");
                    companiesDAO.setCity(view.read());
                    System.out.println(companiesDAORepository.create(companiesDAO));
                }
                case 2 -> {
                    view.write("Enter number id company for delete");
                    companiesDAORepository.deleteById(Integer.parseInt(view.read()));
                    view.write("company is delete");
                }
                case 3 -> {
                    CompaniesDAO companiesUpdate = new CompaniesDAO();
                    view.write("Enter number id company for update");
                    companiesUpdate.setId(Long.parseLong(view.read()));
                    view.write("Enter new name company");
                    companiesUpdate.setNameCompany(view.read());
                    view.write("Enter new city company");
                    companiesUpdate.setCity(view.read());
                    companiesDAORepository.update(companiesUpdate);
                    view.write("company is update");
                }
                case 4 -> view.write(companiesDAORepository.findAll().toString());
                case 5 -> {
                    view.write("Enter number id company for find");
                    long idCompany = Long.parseLong(view.read());
                    view.write(companiesDAORepository.findById(idCompany).toString());
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
        return "company";
    }
}
