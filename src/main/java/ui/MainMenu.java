package ui;

import dao.implementations.ApplicantImpl;
import dao.implementations.ExamImpl;
import dao.implementations.FacultyImpl;
import dao.model.Applicant;
import dao.model.Faculty;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    public static void start() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
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
                    NewApplicant createApplicant = new NewApplicant();
                    createApplicant.start();
                    break;
                case 2:
                    System.out.println("Applicant sign in window");
                    break;
                case 3:
                    System.out.println("test-area");
//                    ExamImpl exam = new ExamImpl();
//                    System.out.println(exam.getIdByName("Java Basic"));
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
