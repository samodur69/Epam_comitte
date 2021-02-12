package ui;

import dao.implementations.ApplicantImpl;
import dao.implementations.FacultyImpl;
import dao.model.Applicant;
import dao.model.Faculty;

import java.util.*;

public class NewApplicant {
    public static void start() {
        Scanner scan = new Scanner(System.in);
        Applicant applicant= new Applicant();
        ApplicantImpl applicantDb = new ApplicantImpl();
        FacultyImpl fac = new FacultyImpl();
        List<Faculty> fac_list;

        applicant.setEnrolled("N"); // posle podscheta - change to Y
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
        System.out.println("Let`s choice faculty (type Faculty ID):");
        fac_list = fac.getAll();
        // Create HashMap
        Map<Integer, String> course_map = new LinkedHashMap<>();
        for (Faculty faculty: fac_list) {
            course_map.put(faculty.getFacultyId(), faculty.getFacultyName());
            System.out.println(faculty.getFacultyId() + " - " + faculty.getFacultyName());
        }
        int choice;
        while (true) {
            choice = scan.nextInt();
            if (course_map.containsKey(choice)) {
                applicant.setFacultyId(choice);
                break;
            }
            System.out.println("Please try again");
        }
        applicantDb.create(applicant);
    }
}
