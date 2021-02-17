package ui;

import util.Utils;

import java.util.Scanner;

public class UniversityAdministration {

    private static Scanner scan = new Scanner(System.in);

    public void start() {
        System.out.println("Administration");
        while (true) {
            System.out.println("1. Enroll applicants\n" +
                    "2. Show examination records\n" +
                    "3. Show students by faculty\n" +
                    "4. Show best students (max sum of grades)\n" +
                    "5. something else....");
            switch (scan.nextInt()) {
                case 1:
                    System.out.println("smth");
                    break;
                case 2:
                    System.out.println("smta");
                    break;
                case 3:
                    System.out.println("smthwq");
                    break;
                case 4:
                    System.out.println("sas");
                    break;
                case 5:
                    System.out.println("Random create records");
                    Utils.createExamRecords();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Wrong choice. Please try again");
            }
        }
    }
}
