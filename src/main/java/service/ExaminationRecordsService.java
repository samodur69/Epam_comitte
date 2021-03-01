package service;

import dao.implementations.ApplicantImpl;
import dao.implementations.ExamImpl;
import dao.implementations.ExaminationListImpl;
import dao.model.Applicant;
import dao.model.Exam;
import dao.model.ExaminationList;
import data.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.AppException;

import java.security.SecureRandom;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExaminationRecordsService {

    private static final Logger logger = LoggerFactory.getLogger(ExaminationRecordsService.class);
    private final static ApplicantImpl aplService = new ApplicantImpl();

    public static void showAverageMarkByExams() {
        String sqlAvg = "SELECT EXAM_ID, AVG(GRADE) FROM EXAMINATION_RECORDS GROUP BY EXAM_ID";
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = Optional
                    .ofNullable(DBConnection.getConnection())
                    .orElseThrow(() -> new AppException("Connection is null"));
            st = conn.createStatement();
            rs = st.executeQuery(sqlAvg);
            ExamImpl exam = new ExamImpl();
            while (rs.next()) {
                String examName = exam.getNameById(rs.getInt("EXAM_ID"));
                double grade = rs.getDouble("AVG(GRADE)");
                System.out.printf("In %s, average grade is %.2f\n", examName, grade);
            }

        } catch (SQLException e) {
            logger.warn("Error when show average mark group by exams");
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, st, conn);
        }
    }

    public static void createRandomExamRecordsForAll() {
        List<Applicant> applicantList = aplService.getAll();
        for (Applicant el : applicantList) {
            createRandomExamRecords(el.getId());
        }
        System.out.println("\n Create samples records done\n");
    }

    public static void createRandomExamRecords(int studentId) {
        SecureRandom random = new SecureRandom();
        ExaminationListImpl recordService = new ExaminationListImpl();
        Applicant applicant = aplService.getById(studentId);
        List<Exam> examList = getExamsByFaculty(applicant.getFacultyId());
        for (Exam el : examList) {
            ExaminationList record = new ExaminationList(applicant.getId() , el.getExamId() , random.nextInt(101));
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
            conn = Optional
                    .ofNullable(DBConnection.getConnection())
                    .orElseThrow(() -> new AppException("Connection is null"));
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
