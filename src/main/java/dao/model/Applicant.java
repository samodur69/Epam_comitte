package dao.model;

import java.util.Random;

public class Applicant {
    private int id;
    private String firstName;
    private String lastName;
    private int schoolAverage;
    private int facultyId;
    private String password;
    private String enrolled;
    private String email;

    public Applicant () {
    }

    public Applicant (String firstName, String lastName, int schoolAverage, int facultyId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.schoolAverage = schoolAverage;
        this.facultyId = facultyId;
        this.password = generatePin();
    }


    public static String generatePin() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSchoolAverage() {
        return schoolAverage;
    }

    public void setSchoolAverage(int schoolAverage) {
        this.schoolAverage = schoolAverage;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEnrolled() {
        return enrolled;
    }

    public void setEnrolled(String enrolled) {
        this.enrolled = enrolled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Applicant{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", schoolAverage=" + schoolAverage +
                ", facultyId=" + facultyId +
                ", password='" + password + '\'' +
                ", enrolled='" + enrolled + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
