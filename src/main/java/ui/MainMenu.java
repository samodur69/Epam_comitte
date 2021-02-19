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
                    new ApplicantMenu().studentLogIn();
                    break;
                case 3:
                    System.out.println("test-area");
                    ApplicantImpl appService = new ApplicantImpl();
                    System.out.println(appService.getTotalMark(10003));
                    System.out.println (appService.enrollAllApplicants ());
                    System.out.println();
                    break;
                case 4:
                    System.out.println("Admin menu");
                    new UniversityAdministration().start();
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
