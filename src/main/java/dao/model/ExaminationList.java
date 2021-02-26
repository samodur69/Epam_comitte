package dao.model;

import java.util.Objects;

public class ExaminationList {
    private int recordId;
    private int studentId;
    private int examId;
    private int grade;

    public ExaminationList() {
    }

    public ExaminationList(int studentId, int examId, int grade) {
        this.studentId = studentId;
        this.examId = examId;
        this.grade = grade;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExaminationList that = (ExaminationList) o;
        return recordId == that.recordId &&
                studentId == that.studentId &&
                examId == that.examId &&
                grade == that.grade;
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordId , studentId , examId , grade);
    }

    @Override
    public String toString() {
        return "ExaminationList " +
                "# " + recordId +
                ". Applicant" + studentId +
                ", examId - " + examId +
                ", grade - " + grade;
    }
}
