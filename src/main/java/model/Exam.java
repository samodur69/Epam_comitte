package model;

import java.util.Objects;

public class Exam {
    private int examId;
    private String examName;

    public Exam() {
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exam exam = (Exam) o;
        return examId == exam.examId && Objects.equals(examName, exam.examName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(examId, examName);
    }
}
