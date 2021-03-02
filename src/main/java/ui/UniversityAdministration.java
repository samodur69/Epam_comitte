package ui;

import service.ApplicantService;
import service.ExaminationRecordsService;

import java.util.Scanner;

public class UniversityAdministration {

    private final static Scanner scan = new Scanner(System.in);
    private final ApplicantService applicantService = new ApplicantService();
    private final ExaminationRecordsService examService = new ExaminationRecordsService();

    /**
     * Most services and statistic in console ui
     */
    public void start() {
        System.out.println("Administration Services and Statistics\n");
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("1. Enroll applicants\n" +
                    "2. Show top 20 marks for the exams \n" +
                    "3. Show 10 best students (max sum of grades)\n" +
                    "4. Show average mark by exams\n" +
                    "5. Create Random Exam Records for All Students\n" +
                    "9. Go to previous menu\n" +
                    "0. Exit");
            switch (scan.nextInt()) {
                case 1:
                    System.out.println (applicantService.enrollAllApplicants() + " students enrolled" +
                            "to university");
                    break;
                case 2:
                    examService.showTopTwentyRecords();
                case 3:
                    applicantService.showTopTenStudents();
                    break;
                case 4:
                    examService.showAverageMarkByExams();
                    break;
                case 5:
                    System.out.println("Random create records");
                    examService.createRandomExamRecordsForAll();
                    break;
                case 9:
                    inMenu = false;
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Wrong choice. Please try again");
            }
        }
    }
}
