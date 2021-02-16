package dao.model;

import dao.implementations.ExaminationListImpl;

public class ExaminationList extends ExaminationListImpl {
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

    @Override
    public String toString() {
        return "ExaminationList for student " + studentId +
                ", examId=" + examId +
                ", grade=" + grade;
    }
}
