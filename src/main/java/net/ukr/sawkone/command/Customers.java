package net.ukr.sawkone.command;

import net.ukr.sawkone.jdbc.config.DatabaseConnectionManager;
import net.ukr.sawkone.jdbc.dao.entity.CustomersDAO;
import net.ukr.sawkone.jdbc.dao.repositories.CustomersRepositories;
import net.ukr.sawkone.jdbc.dao.repositories.Repository;
import net.ukr.sawkone.jdbc.servise.CheckEnteredData;
import net.ukr.sawkone.view.View;

public class Customers implements Command {
    private Repository<CustomersDAO> customersDAORepository;
    private View view;
    private CheckEnteredData check;

    public Customers(View view, DatabaseConnectionManager cm, CheckEnteredData check) {
        this.view = view;
        this.customersDAORepository = new CustomersRepositories(cm, view);
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
                    4 - list Customers;
                    5 - find by id
                    6 - exit the customers menu""");
            int numberCommand = 0;
            try {
                numberCommand = Integer.parseInt(view.read());
            } catch (Exception e) {
                view.write("Wrong command is entered");
            }
            switch (numberCommand) {
                case 1 -> {
                    CustomersDAO customersDAO = new CustomersDAO();
                    customersDAO.setNameCustomer(check.orLineIsEmpty("Enter name customer"));
                    customersDAO.setCity(check.orLineIsEmpty("Enter city customer"));
                    System.out.println(customersDAORepository.create(customersDAO));
                }
                case 2 -> customersDAORepository.deleteById(check.orReallyIsIdCustomers("Enter number id customer for delete"));
                case 3 -> {
                    CustomersDAO customersUpdate = new CustomersDAO();
                    customersUpdate.setId(check.orReallyIsIdCustomers("Enter number id customer for update"));
                    customersUpdate.setNameCustomer(check.orLineIsEmpty("Enter new name customer"));
                    customersUpdate.setCity(check.orLineIsEmpty("Enter new city customer"));
                    customersDAORepository.update(customersUpdate);
                    view.write("customer is update");
                }
                case 4 -> view.write(customersDAORepository.findAll().toString());
                case 5 -> {
                    long idCustomer = check.orReallyIsIdCustomers("Enter number id customer for find");
                    view.write(customersDAORepository.findById(idCustomer).toString());
                }
                case 6 -> isNotExit = false;
                default -> view.write("Select another command");
            }
        }
    }

    @Override
    public String commandName() {
        return "customers";
    }
}