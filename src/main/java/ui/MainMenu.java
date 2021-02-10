package ui;

import dao.implementations.ExamImpl;
import java.util.Scanner;

public class MainMenu {
    public static void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("" +
                    "1. Sign up as new Applicant\n" +
                    "2. Applicant Sign in\n" +
                    "3. Test Area\n" +
                    "4. Admin login\n" +
                    "0. Exit");
            switch (scanner.nextInt()) {
                case 1:
                    System.out.println("Sign up as new Applicant");
                    break;
                case 2:
                    System.out.println("Applicant sign in window");
                    break;
                case 3:
                    System.out.println("test-area");
                    ExamImpl ex = new ExamImpl();
                    System.out.println(ex.getAll());
                    System.out.println(ex.getById(10));
                    break;
                case 4:
                    break;
                case 0:
                    System.out.println("Good bye! See you later!");
                    System.exit(0);
                default:
                    System.out.println("Wrong choice");
            }
        }
    }
}
