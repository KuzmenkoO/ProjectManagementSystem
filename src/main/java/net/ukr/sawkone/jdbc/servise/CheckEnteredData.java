package net.ukr.sawkone.jdbc.servise;

import net.ukr.sawkone.jdbc.dto.Sex;
import net.ukr.sawkone.view.View;

public class CheckEnteredData {
    private final String errorMessage = "Error. The data entered is incorrect";
    private View view;

    public CheckEnteredData(View view) {
        this.view = view;
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
}