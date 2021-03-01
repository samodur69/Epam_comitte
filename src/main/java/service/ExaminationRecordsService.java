package service;

import dao.implementations.ApplicantImpl;
import dao.implementations.ExamImpl;
import dao.implementations.ExaminationListImpl;
import dao.model.Applicant;
import dao.model.Exam;
import dao.model.ExaminationList;
import data.DBConnection;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExaminationRecordsService {

    private final static ApplicantImpl aplService = new ApplicantImpl();

    public static void createRandomExamRecordsForAll() {
        List<Applicant> applicantList = aplService.getAll();
        for (Applicant el: applicantList) {
            createRandomExamRecords(el.getId());
        }
        System.out.println("\n Create samples records done\n");
    }

    public static void createRandomExamRecords(int studentId) {
        SecureRandom random = new SecureRandom();
        ExaminationListImpl recordService = new ExaminationListImpl();
        Applicant applicant = aplService.getById(studentId);
        List<Exam> examList = getExamsByFaculty(applicant.getFacultyId());
        for (Exam el: examList) {
            ExaminationList record = new ExaminationList(applicant.getId() , el.getExamId(), random.nextInt(101));
            recordService.create(record);
        }
    }

    public static List<Exam> getExamsByFaculty(int facultyId) {
        List<Exam> exams = new ArrayList<>();
        ExamImpl examService = new ExamImpl();
        String sqlGetExams = "SELECT EXAM_ID FROM EXAMS_FACULTY WHERE FACULTY_ID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            assert conn != null;
            ps = conn.prepareStatement(sqlGetExams);
            ps.setInt(1, facultyId);
            rs = ps.executeQuery();
            while (rs.next()) {
                int examId = rs.getInt("EXAM_ID");
                Exam exam = examService.getById(examId);
                exams.add(exam);
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, ps, conn);
        }
        return exams;
    }
}
