package controller;

import constants.CommonConstants;
import dao.UserDAOImpl;
import datasource.DatasourceConfig;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Assignment1 {
    private MainController mainController;

    public Assignment1(MainController mainController) {
        this.mainController = mainController;
    }

    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public static void main(String[] args) {
        try {
            UserDAOImpl userDAO = new UserDAOImpl(DatasourceConfig.mysqlDataSource(
                    CommonConstants.MYSQL_DRIVER_CLASS,
                    CommonConstants.HOST,
                    CommonConstants.PORT,
                    CommonConstants.DATABASE,
                    CommonConstants.USERNAME,
                    CommonConstants.PASSWORD).getConnection());
            InputReader inputUser = new InputReader(new Scanner(System.in));
            MainController mainController = new MainController(userDAO, inputUser);
            Assignment1 assignment1 = new Assignment1(mainController);
            while (true) {
                assignment1.run(assignment1.getMainController());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void run(MainController mainController){
        System.out.print("===========Assignment 1===========\n" +
                "========== Login Program ==========\n" +
                "Press the number corresponding to the option !!!\n" +
                "Option 1 (press 1): Add Account \n" +
                "Option 2 (press 2): Login\n" +
                "Option 3 (press 3): Exit the program\n" +
                "You press the number: ");
        try {
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("case 1");
                    mainController.addAccount();
                    break;
                case 2:
                    System.out.println("case 2");
                    mainController.login();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("You do not press one of three numbers: 1, 2 or 3".toUpperCase());
            }
        } catch (InputMismatchException e) {
            System.out.println("you must press a number".toUpperCase()+"\t"+e.getMessage());
        }
    }
}
