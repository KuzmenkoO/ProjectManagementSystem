package net.ukr.sawkone.command;

import net.ukr.sawkone.jdbc.config.DatabaseConnectionManager;
import net.ukr.sawkone.jdbc.dao.entity.CustomersDAO;
import net.ukr.sawkone.jdbc.dao.repositories.CustomersRepositories;
import net.ukr.sawkone.jdbc.dao.repositories.Repository;
import net.ukr.sawkone.view.View;

public class Customers implements Command {
    private Repository<CustomersDAO> customersDAORepository;
    private View view;

    public Customers(View view, DatabaseConnectionManager cm) {
        this.view = view;
        this.customersDAORepository = new CustomersRepositories(cm);
    }

    @Override
    public void process() {
        boolean isNotExit = true;
        while (isNotExit) {
            view.write("Enter number command:\n1 - create;\n2 - delete by id;\n3 - update by id;\n4 - list Customers;" +
                    "\n5 - find by id\n6 - exit the customers menu");
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
                    CustomersDAO customersDAO = new CustomersDAO();
                    view.write("Enter name customer");
                    customersDAO.setNameCustomer(view.read());
                    view.write("Enter city customer");
                    customersDAO.setCity(view.read());
                    System.out.println(customersDAORepository.create(customersDAO));
                }
                case 2 -> {
                    view.write("Enter number id customer for delete");
                    customersDAORepository.deleteById(Integer.parseInt(view.read()));
                    view.write("customer is delete");
                }
                case 3 -> {
                    CustomersDAO customersUpdate = new CustomersDAO();
                    view.write("Enter number id customer for update");
                    customersUpdate.setId(Long.parseLong(view.read()));
                    view.write("Enter new name customer");
                    customersUpdate.setNameCustomer(view.read());
                    view.write("Enter new city customer");
                    customersUpdate.setCity(view.read());
                    customersDAORepository.update(customersUpdate);
                    view.write("customer is update");
                }
                case 4 -> view.write(customersDAORepository.findAll().toString());
                case 5 -> {
                    view.write("Enter number id customer for find");
                    long idCustomer = Long.parseLong(view.read());
                    view.write(customersDAORepository.findById(idCustomer).toString());
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
        return "customers";
    }
}
