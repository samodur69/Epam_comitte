package ui;

import dao.implementations.ApplicantImpl;
import dao.implementations.ExamImpl;
import dao.implementations.FacultyImpl;
import dao.model.Applicant;
import dao.model.Exam;
import dao.model.Faculty;

import java.util.*;

public class NewApplicant {


    public static void start() {
        Scanner scan = new Scanner(System.in);
        Applicant applicant = new Applicant();
        // applicant.storeToDB() add method;
//        ApplicantImpl applicantDb = new ApplicantImpl();
        FacultyImpl facultyImpl = new FacultyImpl();
        ExamImpl exam = new ExamImpl();
        List<Faculty> facultyList;

        applicant.setEnrolled("N"); // if student will be enrolled - change to Y
        System.out.println("Welcome to our university!\n" +
                "Enter your First name:");
        applicant.setFirstName(scan.nextLine());
        System.out.println("Enter your Last Name:");
        applicant.setLastName(scan.nextLine());
        System.out.println("Enter your email. It will use for login later");
//        TODO regex check email rebuild
        while (!scan.hasNext("\\S+@\\S+\\.\\S+")) {
            System.out.println("wrong email format. Please try again");
            scan.nextLine();
        }
        applicant.setEmail(scan.nextLine());
        applicant.setPassword(Applicant.generatePin());
        System.out.println("Your PIN! Use for login later " + applicant.getPassword());
        System.out.println("Enter your school average score (1....100):");
        applicant.setSchoolAverage(scan.nextInt());
        // start func
        System.out.println("Let`s choice faculty (type Faculty ID):");
        facultyList = facultyImpl.getAll();
        Map<Integer, String> course_map = new LinkedHashMap<>();
        for (Faculty faculty: facultyList) {
            course_map.put(faculty.getFacultyId(), faculty.getFacultyName());
            System.out.println(faculty.getFacultyId() + " - " + faculty.getFacultyName());
        }

//        for (Faculty el: facultyList) {
//            if (choice == el.getFacultyId()) break;
//        }


        while (true) {
            int choice = scan.nextInt();
            if (course_map.containsKey(choice)) {
                applicant.setFacultyId(choice);
                break;
            }
            System.out.println("There is no faculty with entered id. Please try again");
        }
        /// end func
        applicant.create(applicant);
        System.out.println("You must enter exams Grades");
        // get_exams_for_chosen_faculty
        List<Integer> exams = new ArrayList<>();
        exams = exam.getByFaculty(applicant.getFacultyId());
        for (Integer el : exams) {
            System.out.println("Enter mark for " + exam.getNameById(el));
            int mark = scan.nextInt();
            System.out.println(mark);
//            System.out.println(exam.getById()
//            System.out.println(integer);
        }


    }
}
