package ui;

import dao.implementations.ApplicantImpl;
import dao.implementations.ExamImpl;
import dao.implementations.ExaminationListImpl;
import dao.model.ExaminationList;
import util.Utils;
import java.util.List;
import java.util.Scanner;

public class UniversityAdministration {

    private final static Scanner scan = new Scanner(System.in);
    private final ExaminationListImpl recordService = new ExaminationListImpl();
    private final ExamImpl examService = new ExamImpl();
    private final ApplicantImpl appService = new ApplicantImpl ();

    public void start() {
        System.out.println("Administration");
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("1. Enroll applicants\n" +
                    "2. Show examination records\n" +
                    "3. Show students by faculty\n" +
                    "4. Show best students (max sum of grades)\n" +
                    "5. something else....\n" +
                    "69. Go to previous menu\n" +
                    "0. Exit");
            switch (scan.nextInt()) {
                case 1:
                    System.out.println (appService.enrollAllApplicants() + " students enrolled");
                    break;
                case 2:
                    showExamRecordsService();
                    break;
                case 3:
                    System.out.println("It`ll work in release version!");
                    break;
                case 4:
                    System.out.println("It`ll work in release version");
                    break;
                case 5:
                    System.out.println("Random create records");
                    Utils.createExamRecords();
                    break;
                case 69:
                    inMenu = false;
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Wrong choice. Please try again");
            }
        }
    }

    private void showExamRecordsService() {
        System.out.println("Choice option:\n" +
                "1. Show all records\n" +
                "2. Show by faculty\n" +
                "3. Show in descending order by grade, group by faculties");
        switch (scan.nextInt()) {
            case 1:
                List<ExaminationList> recordsList = recordService.getAll();
                for (ExaminationList el: recordsList) {
                    System.out.println(el.toString());
                }
                break;
            case 2:
                System.out.println("It`ll work in release version!");
                break;
            case 3:
                System.out.println("It`ll work in release version");
                break;
            default:
                System.out.println("Wrong choice");
        }
    }
}
