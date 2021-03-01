package service;

import data.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

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
            conn = DBConnection.getConnection ();
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
            conn = DBConnection.getConnection();
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
}
