package dao.model;

import java.util.Objects;

public class Faculty {
    private int facultyId;
    private String facultyName;
    private int facultyCapacity;
    private int minGrade;

    public Faculty() {
    }

    public Faculty(String facultyName, int facultyCapacity, int minGrade) {
        this.facultyName = facultyName;
        this.facultyCapacity = facultyCapacity;
        this.minGrade = minGrade;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public int getFacultyCapacity() {
        return facultyCapacity;
    }

    public void setFacultyCapacity(int facultyCapacity) {
        this.facultyCapacity = facultyCapacity;
    }

    public int getMinGrade() {
        return minGrade;
    }

    public void setMinGrade(int minGrade) {
        this.minGrade = minGrade;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "facultyId=" + facultyId +
                ", facultyName='" + facultyName + '\'' +
                ", facultyCapacity=" + facultyCapacity +
                ", minGrade=" + minGrade +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return facultyId == faculty.facultyId &&
                facultyCapacity == faculty.facultyCapacity &&
                minGrade == faculty.minGrade &&
                facultyName.equals(faculty.facultyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(facultyId , facultyName , facultyCapacity , minGrade);
    }
}
