package dao.model;

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
    public String toString() {
        return "ExaminationList " +
                "# " + recordId +
                ". Applicant" + studentId +
                ", examId - " + examId +
                ", grade - " + grade;
    }
}
