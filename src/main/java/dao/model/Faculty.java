package dao.model;

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

    public String idAndName() {
        return facultyId + " " + facultyName;
    }
}
