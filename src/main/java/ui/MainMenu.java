package ui;

import daoimpl.ExamImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class MainMenu {
    public static void start() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Sign up as new student\n2. Log in student\n3. System menu");
            switch (scanner.nextInt()) {
                case 1:
                    System.out.println("create new stud");
                    break;
                case 2:
                    System.out.println("login stud");
                    break;
                case 3:
                    ExamImpl ex = new ExamImpl();
                    System.out.println(ex.getAll());
                    System.out.println(ex.getById(10));
                    System.out.println("system menu");
                    break;
                default:
                    System.out.println("Wrong choice");
            }
        }


    }
}
