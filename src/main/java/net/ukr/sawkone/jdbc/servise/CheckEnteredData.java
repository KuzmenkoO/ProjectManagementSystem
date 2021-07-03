package net.ukr.sawkone.jdbc.servise;

import net.ukr.sawkone.jdbc.config.DatabaseConnectionManager;
import net.ukr.sawkone.jdbc.dao.entity.CompaniesDAO;
import net.ukr.sawkone.jdbc.dao.entity.CustomersDAO;
import net.ukr.sawkone.jdbc.dao.entity.DevelopersDAO;
import net.ukr.sawkone.jdbc.dao.entity.ProjectsDAO;
import net.ukr.sawkone.jdbc.dao.repositories.*;
import net.ukr.sawkone.jdbc.dto.Sex;
import net.ukr.sawkone.view.View;

public class CheckEnteredData {
    private final String errorMessage = "Error. The data entered is incorrect";
    private View view;
    private Repository<DevelopersDAO> developersDAORepository;
    private Repository<CompaniesDAO> companiesDAORepository;
    private Repository<CustomersDAO> customersDAORepository;
    private Repository<ProjectsDAO> projectsDAORepository;

    public CheckEnteredData(DatabaseConnectionManager cm, View view) {
        this.view = view;
        this.developersDAORepository = new DevelopersRepositories(cm, view);
        this.companiesDAORepository = new CompanyRepositories(cm, view);
        this.customersDAORepository = new CustomersRepositories(cm, view);
        this.projectsDAORepository = new ProjectsRepositories(cm, view);
    }

    public Long orNumberLong(String message) {
        boolean isNotNumber = true;
        long number = 0;
        while (isNotNumber) {
            try {
                view.write(message);
                number = Long.parseLong(view.read());
                if (number > 0) {
                    isNotNumber = false;
                }
            } catch (Exception e) {
                view.write(errorMessage);
            }
        }
        return number;
    }

    public int orNumberInt(String message) {
        boolean isNotNumber = true;
        int number = 0;
        while (isNotNumber) {
            try {
                view.write(message);
                number = Integer.parseInt(view.read());
                if (number > 0) {
                    isNotNumber = false;
                }
            } catch (Exception e) {
                view.write(errorMessage);
            }
        }
        return number;
    }

    public double orNumberDouble(String message) {
        boolean isNotNumber = true;
        double number = 0;
        while (isNotNumber) {
            try {
                view.write(message);
                number = Double.parseDouble(view.read());
                if (number > 0) {
                    isNotNumber = false;
                }
            } catch (Exception e) {
                view.write(errorMessage);
            }
        }
        return number;
    }

    public String orLineIsEmpty(String message) {
        boolean isNotString = true;
        String result = "";
        while (isNotString) {
            try {
                view.write(message);
                result = view.read();
                if (!result.isEmpty()) {
                    isNotString = false;
                }
            } catch (Exception e) {
                view.write(errorMessage);
            }
        }
        return result;
    }

    public Sex orGivenGender(String message) {
        boolean isNotSex = true;
        Sex sex = Sex.MALE;
        while (isNotSex) {
            view.write(message);
            try {
                sex = Sex.findByName(view.read().toLowerCase());
                isNotSex = false;
            } catch (Throwable throwable) {
                view.write(errorMessage);
            }
        }
        return sex;
    }

    public long orReallyIsIdCompanies(String message) {
        boolean isId = true;
        long id = 0;
        while (isId) {
            id = companiesDAORepository.findById(orNumberLong(message)).getId();
            if (id != 0) {
                isId = false;
            } else {
                view.write("This id is not in the database Companies");
            }
        }
        return id;
    }

    public long orReallyIsIdDevelopers(String message) {
        boolean isId = true;
        long id = 0;
        while (isId) {
            id = developersDAORepository.findById(orNumberLong(message)).getId();
            if (id != 0) {
                isId = false;
            } else {
                view.write("This id is not in the database Developers");
            }
        }
        return id;
    }

    public long orReallyIsIdCustomers(String message) {
        boolean isId = true;
        long id = 0;
        while (isId) {
            id = customersDAORepository.findById(orNumberLong(message)).getId();
            if (id != 0) {
                isId = false;
            } else {
                view.write("This id is not in the database Customers");
            }
        }
        return id;
    }

    public long orReallyIsIdProjects(String message) {
        boolean isId = true;
        long id = 0;
        while (isId) {
            id = projectsDAORepository.findById(orNumberLong(message)).getId();
            if (id != 0) {
                isId = false;
            } else {
                view.write("This id is not in the database Projects");
            }
        }
        return id;
    }
}