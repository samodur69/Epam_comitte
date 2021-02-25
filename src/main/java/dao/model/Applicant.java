package dao.model;

import util.Utils;

import java.util.Objects;

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
        this.enrolled = "N";
        this.password = Utils.generatePin();
    }


    public Applicant (String firstName, String lastName, int schoolAverage, int facultyId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.schoolAverage = schoolAverage;
        this.facultyId = facultyId;
        this.enrolled = "N";
        this.password = Utils.generatePin();
    }

    public Applicant(String firstName, String lastName, String email, int schoolAverage, int facultyId) {
        this(firstName, lastName, schoolAverage, facultyId);
        this.email = email;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Applicant applicant = (Applicant) o;
        return id == applicant.id && schoolAverage == applicant.schoolAverage &&
                facultyId == applicant.facultyId &&
                Objects.equals(firstName , applicant.firstName) &&
                Objects.equals(lastName , applicant.lastName) &&
                Objects.equals(password , applicant.password) &&
                Objects.equals(enrolled , applicant.enrolled) &&
                Objects.equals(email , applicant.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id , firstName , lastName , schoolAverage , facultyId , password , enrolled , email);
    }

    @Override
    public String toString() {
        return "Applicant{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", school Average Grade " + schoolAverage +
                ", Faculty " + facultyId +
                ", password='" + password + '\'' +
                ", enrolled='" + enrolled + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
