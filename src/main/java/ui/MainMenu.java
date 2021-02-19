package ui;

import dao.implementations.ApplicantImpl;
import dao.model.Applicant;

import java.awt.*;
import java.util.List;
import java.util.Scanner;

public class MainMenu {

    static Scanner scanner = new Scanner(System.in);
    private final static ApplicantImpl appService = new ApplicantImpl();

    public static void start() throws InterruptedException {
        while (true) {
            System.out.println("" +
                    "1. Sign up as new Applicant\n" +
                    "2. Applicant Sign in\n" +
                    "3. Admin login\n" +
                    "4. Show enrolled students list\n" +
                    "0. Exit");
            switch (scanner.nextInt()) {
                case 1:
                    new NewApplicant().start();
                    break;
                case 2:
                    new ApplicantMenu().studentLogIn();
                    break;
                case 3:
                    System.out.println("Admin menu");
                    new UniversityAdministration().start();
                    break;
                case 4:
                    System.out.println("Enrolled Students list");
                    List<Applicant> applicants = appService.getByEnrolled();
                    for (Applicant el: applicants) {
                        System.out.println (el.toString());
                    }
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
