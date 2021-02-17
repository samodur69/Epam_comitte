package ui;

import dao.implementations.ApplicantImpl;

import java.util.Scanner;

public class MainMenu {

    static Scanner scanner = new Scanner(System.in);

    public static void start() throws InterruptedException {
        while (true) {
            System.out.println("" +
                    "1. Sign up as new Applicant\n" +
                    "2. Applicant Sign in\n" +
                    "3. Test Area\n" +
                    "4. Admin login\n" +
                    "5. Show enrolled students list\n" +
                    "0. Exit");
            switch (scanner.nextInt()) {
                case 1:
                    new NewApplicant().start();
                    break;
                case 2:
                    System.out.println("Applicant sign in window");
                    break;
                case 3:
                    System.out.println("test-area");
//                    ExamImpl exam = new ExamImpl();
//                    System.out.println(exam.getIdByName("Java Basic"));
                    System.out.println();
                    break;
                case 4:
                    System.out.println("Admin menu");
                    break;
                case 5:
                    System.out.println("Enrolled Students list");
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
