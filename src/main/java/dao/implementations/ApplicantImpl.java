package dao.implementations;

import dao.interfaces.ApplicantDao;
import dao.model.Applicant;
import data.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicantImpl implements ApplicantDao {

    private static final Logger logger = LoggerFactory.getLogger(ApplicantImpl.class);

    @Override
    public void create(Applicant applicant) {
        String sqlCreate = "INSERT INTO STUDENTS " +
                "(FIRST_NAME, LAST_NAME, SCHOOL_AVG_SCORES, FACULTY_ID, ST_PASSWORD, ST_EMAIL) VALUES (?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sqlCreate);
            ps.setString(1, applicant.getFirstName());
            ps.setString(2, applicant.getLastName());
            ps.setInt(3, applicant.getSchoolAverage());
            ps.setInt(4, applicant.getFacultyId());
            ps.setString(5, applicant.getPassword());
            ps.setString(6, applicant.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.info("Can`t add new applicant to DB");
            e.printStackTrace();
        } finally {
            DBConnection.close(ps, conn);
        }
    }

    @Override
    public List<Applicant> getAll() {
        List<Applicant> applicants = new ArrayList<>();
        Applicant applicant;
        String sqlGetAll = "SELECT * FROM STUDENTS";
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            st = conn.createStatement();
            st.execute(sqlGetAll);
            rs = st.getResultSet();
            while (rs.next()) {
                applicant = new Applicant();
                applicant.setId(rs.getInt("ID"));
                applicant.setFirstName(rs.getString("FIRST_NAME"));
                applicant.setLastName(rs.getString("LAST_NAME"));
                applicant.setSchoolAverage(rs.getInt("SCHOOL_AVG_SCORES"));
                applicant.setFacultyId(rs.getInt("FACULTY_ID"));
                applicant.setEnrolled(rs.getString("ENROLLED"));
                applicant.setPassword(rs.getString("ST_PASSWORD"));
                applicant.setEmail(rs.getString("ST_EMAIL"));
                applicants.add(applicant);
            }
        } catch (SQLException e) {
            logger.info("Troubles with getting list of all Applicants");
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, st, conn);
        }
        return applicants;
    }

    @Override
    public Applicant getByEmail(String email) {
        Applicant applicant;
        String sqlGetByEmail = "SELECT * FROM STUDENTS WHERE ST_EMAIL = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sqlGetByEmail);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                applicant = new Applicant();
                applicant.setId(rs.getInt("ID"));
                applicant.setFirstName(rs.getString("FIRST_NAME"));
                applicant.setLastName(rs.getString("LAST_NAME"));
                applicant.setSchoolAverage(rs.getInt("SCHOOL_AVG_SCORES"));
                applicant.setFacultyId(rs.getInt("FACULTY_ID"));
                applicant.setEnrolled(rs.getString("ENROLLED"));
                applicant.setPassword(rs.getString("ST_PASSWORD"));
                applicant.setEmail(rs.getString("ST_EMAIL"));
                return applicant;
            }
        } catch (SQLException e) {
            logger.info("Troubles with getting Applicant by his ID");
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, ps, conn);
        }
        return null;
    }

    @Override
    public int getIdByEmail(String email) {
        String sqlGetByEmail = "SELECT ID FROM STUDENTS WHERE ST_EMAIL = ?";

        int studentId;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sqlGetByEmail);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                studentId = rs.getInt("ID");
                return studentId;
            }
        } catch (SQLException e) {
            logger.info("Troubles with getting Applicant ID by his Email");
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, ps, conn);
        }
        return -1;
    }

    @Override
    public List<Applicant> getByFaculty(String faculty) {
        return null;
    }

    @Override
    public List<Applicant> getByEnrolled(boolean isStudent) {
        return null;
    }


    @Override
    public Applicant getById(int id) {
        return null;
    }

    @Override
    public void update(Applicant obj) {

    }

    @Override
    public void delete(int id) {

    }



}
