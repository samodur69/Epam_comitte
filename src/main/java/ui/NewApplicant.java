package ui;

import dao.implementations.ApplicantImpl;
import dao.implementations.ExamImpl;
import dao.implementations.ExaminationListImpl;
import dao.implementations.FacultyImpl;
import dao.model.Applicant;
import dao.model.ExaminationList;
import dao.model.Faculty;

import java.util.*;

public class NewApplicant {

    static Scanner scan = new Scanner(System.in);
    private final ApplicantImpl applicantService = new ApplicantImpl();
    private final ExaminationListImpl examRecordService = new ExaminationListImpl();
    private final ExamImpl examService = new ExamImpl();
    private final FacultyImpl facultyService = new FacultyImpl();
    private final Applicant applicant = new Applicant();



    public void start() throws InterruptedException {

//        Applicant applicant = new Applicant();

        System.out.println("Welcome to our university!\n" +
                "Enter your First name:");
        applicant.setFirstName(scan.nextLine());
        System.out.println("Enter your Last Name:");
        applicant.setLastName(scan.nextLine());
        applicant.setEmail(emailInputValidate());


        System.out.println("Your PIN! Use for login later " + applicant.getPassword());



        System.out.println("Enter your school average grade (1....100):");
        applicant.setSchoolAverage(inputGrade());

        applicant.setFacultyId(choiceFaculty());
        applicantService.create(applicant);
        enterExamGrades();
        System.out.println("\nGood luck! Hope to see you at the university! \n");
        Thread.sleep(100);
    }

    private void enterExamGrades() {
        ExaminationList record;
        System.out.println("You must enter exams Grades");
        List<Integer> exams = examService.getByFaculty(this.applicant.getFacultyId());
        for (Integer el : exams) {
            record = new ExaminationList();
            System.out.println("Enter mark for " + examService.getNameById(el));
            record.setStudentId(this.applicant.getId());
            record.setExamId(el);
            record.setGrade(inputGrade());
            examRecordService.create(record);
        }
    }

    /**
     * first -  validate email input
     * second - check email for unique in DB
     * @return user email
     */
    private String emailInputValidate() {
// TODO
        String email;
        System.out.println("Enter your email. It will use for login later");
//        while (true) {
//            email = scan.nextLine();
//            if ( pattern equals input && mail is unique)
//
//        }
//        while (!scan.hasNext("\\S+@\\S+\\.\\S+")) {
//            System.out.println("wrong email format. Please try again");
//            scan.next();
//        }
        email = scan.nextLine();
        return email;
    }

    /**
     * func to choice faculty. Select from DB table FACULTY_LIST all faculties
     * and read user input
     * @return int id of chosen faculty
     */
    private int choiceFaculty() {
        List<Faculty> facultyList;

        System.out.println("Let`s choice faculty (input Faculty ID):");
        facultyList = facultyService.getAll();
        for (Faculty el: facultyList)
            System.out.println(el.getFacultyId() + " - " + el.getFacultyName());
        int choice;
        while (true) {
            choice = scan.nextInt();
            for (Faculty el : facultyList) {
                if (choice == el.getFacultyId()) {
                    return choice;
                }
            }
            System.out.println("There is no faculty with entered id. Please try again");
        }
    }

    /**
     * check user input for range 1...100
     * @return correct input
     */
    private int inputGrade() {

        int grade;
        while (true) {
            grade = scan.nextInt();
            if (1 <= grade && grade <= 100) {
                return grade;
            }
            System.out.println("Wrong input. Grade must be in range 1 ... 100");
        }
    }
}
