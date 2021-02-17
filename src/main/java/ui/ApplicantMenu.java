package ui;

import dao.implementations.ApplicantImpl;
import dao.implementations.FacultyImpl;
import dao.model.Applicant;

import java.util.Scanner;

public class ApplicantMenu {
    private final static ApplicantImpl appService = new ApplicantImpl();
    private final static FacultyImpl facService = new FacultyImpl();
    private final static Scanner scan = new Scanner(System.in);

    /**
     * simple login. if email/password pair is correct - go to next func
     */
    public void studentLogIn() {
        Applicant applicant = new Applicant();
        System.out.println("Insert your email:");
        applicant = appService.getByEmail(scan.nextLine());
        if (applicant != null) {
            System.out.println("Insert your PIN");
            String pin = scan.nextLine();
            if (pin.equals(applicant.getPassword())) {
                start(applicant);
            } else {
                System.out.println("User not found or incorrect password/pin");
            }
        } else {
            System.out.println("User not found.");
        }
    }

    private void start(Applicant applicant) {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("1. Show my status\n" +
                    "0. Log out and back to previous menu");
            switch (scan.nextInt()) {
                case 1:
                    showStudentStatus(applicant);
                    break;
                case 0:
                    inMenu = false;
                    break;
                default:
                    System.out.println("Wrong choice. Try again");
            }
        }
    }

    private void showStudentStatus(Applicant applicant) {
        System.out.printf("Dear, %s %s!\n", applicant.getFirstName(), applicant.getLastName());
        System.out.printf("Your total mark is %d \n", appService.getTotalMark(applicant.getId()));
        if ("Y".equals(applicant.getEnrolled())) {
            System.out.printf("\nWe look forward to seeing you on the " +
                    "faculty %s at " +
                    "the beginning of the academic year.\n", facService.getNameById(applicant.getId()));
        } else {
            System.out.println("\nYou didn't pass your exams properly. Try next year");
        }
    }

}
