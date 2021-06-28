package net.ukr.sawkone.command;

import net.ukr.sawkone.jdbc.config.DatabaseConnectionManager;
import net.ukr.sawkone.jdbc.dao.entity.DevelopersDAO;
import net.ukr.sawkone.jdbc.dao.repositories.DevelopersRepositories;
import net.ukr.sawkone.jdbc.dao.repositories.Repository;
import net.ukr.sawkone.jdbc.dto.Sex;
import net.ukr.sawkone.view.View;

public class Developers implements Command {
    private View view;
    private Repository<DevelopersDAO> developersDAORepository;

    public Developers(View view, DatabaseConnectionManager cm) {
        this.view = view;
        this.developersDAORepository = new DevelopersRepositories(cm);
    }

    @Override
    public void process() {
        boolean isNotExit = true;
        while (isNotExit) {
            view.write("Enter number command:\n1 - create;\n2 - delete by id;\n3 - update by id;\n4 - list developers;" +
                    "\n5 - find by id\n6 - exit the developers menu");
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
                    DevelopersDAO developersDAO = new DevelopersDAO();
                    view.write("Enter the developer name");
                    developersDAO.setName(view.read());
                    view.write("Enter the age of the developer");
                    developersDAO.setAge(Integer.parseInt(view.read()));
                    view.write("Enter the developers sex - male or female");
                    try {
                        developersDAO.setSex(Sex.findByName(view.read().toLowerCase()));
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                        view.write("Enter - male  or - female");
                    }
                    view.write("Enter the id of the company where the developer works");
                    developersDAO.setIdCompany(Long.parseLong(view.read()));
                    view.write("Enter the developer's salary");
                    developersDAO.setSalary(Double.parseDouble(view.read()));
                    System.out.println(developersDAORepository.create(developersDAO));
                }
                case 2 -> {
                    view.write("Enter number id developers for delete");
                    developersDAORepository.deleteById(Integer.parseInt(view.read()));
                    view.write("Developer is delete");
                }
                case 3 -> {
                    DevelopersDAO developersUpdate = new DevelopersDAO();
                    view.write("Enter number id developers for update");
                    developersUpdate.setId(Long.parseLong(view.read()));
                    view.write("Enter a new developer name");
                    developersUpdate.setName(view.read());
                    view.write("Enter a new age of the developer");
                    developersUpdate.setAge(Integer.parseInt(view.read()));
                    view.write("Enter a new developers sex - male or female");
                    try {
                        developersUpdate.setSex(Sex.findByName(view.read()));
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                        view.write("Enter - male  or - female");
                    }
                    view.write("Enter a new id of the company where the developer works");
                    developersUpdate.setIdCompany(Long.parseLong(view.read()));
                    view.write("Enter a new developer's salary");
                    developersUpdate.setSalary(Double.parseDouble(view.read()));
                    developersDAORepository.update(developersUpdate);
                    view.write("Developers is update");
                }
                case 4 -> view.write(developersDAORepository.findAll().toString());
                case 5 -> {
                    view.write("Enter the developer id number to search");
                    long idCustomer = Long.parseLong(view.read());
                    view.write(developersDAORepository.findById(idCustomer).toString());
                }
                case 6 -> {
                    isNotExit = false;
                }
                default -> view.write("Select another command");
            }
        }
    }

    @Override
    public String commandName() {
        return "developers";
    }
}
