package ui;

import dao.implementations.ExamImpl;
import dao.implementations.FacultyImpl;
import dao.model.Applicant;
import dao.model.ExaminationList;
import dao.model.Faculty;

import java.util.*;

public class NewApplicant {

    Scanner scan = new Scanner(System.in);
    private Applicant applicant = new Applicant();
    private ExamImpl exam = new ExamImpl();
    private ExaminationList record = new ExaminationList();

    public void start() throws InterruptedException {

        //////////// applicant.storeToDB() add method;
//        ApplicantImpl applicantDb = new ApplicantImpl();
        applicant.setEnrolled("N"); // if student will be enrolled - change to Y
        System.out.println("Welcome to our university!\n" +
                "Enter your First name:");
        applicant.setFirstName(scan.nextLine());
        System.out.println("Enter your Last Name:");
        applicant.setLastName(scan.nextLine());
        System.out.println("Enter your email. It will use for login later");
//        TODO regex check email rebuild and check email for unique
        while (!scan.hasNext("\\S+@\\S+\\.\\S+")) {
            System.out.println("wrong email format. Please try again");
            scan.nextLine();
        }
        applicant.setEmail(scan.nextLine());
        applicant.setPassword(Applicant.generatePin());
        System.out.println("Your PIN! Use for login later " + applicant.getPassword());
        System.out.println("Enter your school average grade (1....100):");
        applicant.setSchoolAverage(inputGrade());
        applicant.setFacultyId(this.choiceFaculty());
        applicant.create(applicant);
        enterExamGrades(applicant.getIdByEmail(applicant.getEmail()));
        System.out.println("\nGood luck! Hope to see you at the university! \n");
        Thread.sleep(100);
    }

    private void enterExamGrades(int studentId) {
        System.out.println("You must enter exams Grades");
        List<Integer> exams = exam.getByFaculty(applicant.getFacultyId());
        for (Integer el : exams) {
            System.out.println("Enter mark for " + exam.getNameById(el));
            record.setStudentId(applicant.getId());
            record.setExamId(el);
            record.setGrade(inputGrade());
            record.create(record);
        }
    }

    /**
     * func to choice faculty. Select from DB table FACULTY_LIST all faculties
     * and read user input
     * @return int id of chosen faculty
     */
    private int choiceFaculty() {
        Scanner scan = new Scanner(System.in);
        FacultyImpl facultyImpl = new FacultyImpl();
        List<Faculty> facultyList;

        System.out.println("Let`s choice faculty (input Faculty ID):");
        facultyList = facultyImpl.getAll();
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
//        Scanner scan = new Scanner(System.in);
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
