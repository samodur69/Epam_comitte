package service;

import data.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.AppException;

import java.sql.*;
import java.util.Optional;

public class ApplicantService {

    private static final Logger logger = LoggerFactory.getLogger(ApplicantService.class);

    public int enrollAllApplicants() {
        String sqlEnroll = "" +
                "UPDATE " +
                "APPLICANTS " +
                "SET ENROLLED = 'Y' " +
                "WHERE ID IN ( " +
                "SELECT ID FROM TOTAL_MARK a " +
                "WHERE TOTALMARK  BETWEEN ( " +
                "SELECT " +
                "FACULTY_MIN_GRADE " +
                "FROM FACULTY_LIST b " +
                "WHERE " +
                "b.FACULTY_ID = a.FACULTET) " +
                "AND 400)";
        Connection conn;
        Statement st;
        int rowsUpdated = -1;
        try {
            conn = Optional
                    .ofNullable(DBConnection.getConnection())
                    .orElseThrow(() -> new AppException("Connection is null"));
            if (conn != null) {
                st = conn.createStatement();
                rowsUpdated = st.executeUpdate(sqlEnroll);
            }
            return rowsUpdated;
        } catch (SQLException e) {
            logger.warn("Error in the Enrolling all Applicants process");
            e.printStackTrace ();
        }
        return 0;
    }

    public int getTotalMark(int id) {
        String sqlTotalById = "SELECT TotalMark " +
                "FROM " +
                "(select " +
                "a.student_id as id, " +
                "b.faculty_id as facultet, " +
                "sum(a.grade) + b.school_avg_scores as TotalMark " +
                "from " +
                "examination_records a, " +
                "applicants b " +
                "where " +
                "a.student_id = b.id " +
                "group by " +
                "a.student_id, b.school_avg_scores, b.faculty_id) " +
                "WHERE " +
                "id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int totalMark = 0;
        try {
            conn = Optional
                    .ofNullable(DBConnection.getConnection())
                    .orElseThrow(() -> new AppException("Connection is null"));
            ps = conn.prepareStatement(sqlTotalById);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                totalMark = rs.getInt("TOTALMARK");
            }
            return totalMark;
        } catch (SQLException e) {
            logger.info("Troubles with getting total mark by Student`s id");
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, ps, conn);
        }
        return 0;
    }

    public void showTopTenStudents() {
        String sqlTopTen = "SELECT " +
                "b.last_name, b.first_name, a.totalmark " +
                "FROM " +
                "total_mark a, applicants b " +
                "WHERE " +
                "a.id = b.id " +
                "GROUP BY " +
                "b.last_name, b.first_name, a.totalmark " +
                "ORDER BY a.totalmark DESC " +
                "FETCH FIRST 10 ROWS ONLY";
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = Optional
                    .ofNullable(DBConnection.getConnection())
                    .orElseThrow(() -> new AppException("Connection is null"));
            st = conn.createStatement();
            rs = st.executeQuery(sqlTopTen);
            while (rs.next()) {
                String lastName = rs.getString("LAST_NAME");
                String name = rs.getString("FIRST_NAME");
                int totalMark = rs.getInt("TOTALMARK");
                System.out.printf("%-12s %-12s %3d\n", lastName, name, totalMark);
            }
        } catch (SQLException e) {
            logger.warn("Error when get top 10 students");
            e.printStackTrace();
        } finally {
            System.out.println(" ");
            DBConnection.close(rs, st,conn);
        }
    }
}
