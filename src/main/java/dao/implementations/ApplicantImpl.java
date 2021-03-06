package dao.implementations;

import dao.interfaces.ApplicantDao;
import dao.model.Applicant;
import data.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.AppException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ApplicantImpl implements ApplicantDao {

    private static final Logger logger = LoggerFactory.getLogger(ApplicantImpl.class);

    @Override
    public int create(Applicant applicant) {
        String sqlCreate = "INSERT " +
                "INTO APPLICANTS " +
                "(FIRST_NAME, LAST_NAME, SCHOOL_AVG_SCORES, FACULTY_ID, ST_PASSWORD, ST_EMAIL, ENROLLED) " +
                "VALUES " +
                "(?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        try {
            conn = Optional
                    .ofNullable(DBConnection.getConnection())
                    .orElseThrow(() -> new AppException("Connection is null"));
            ps = conn.prepareStatement(sqlCreate);
            ps.setString(1 , applicant.getFirstName());
            ps.setString(2 , applicant.getLastName());
            ps.setInt(3 , applicant.getSchoolAverage());
            ps.setInt(4 , applicant.getFacultyId());
            ps.setString(5 , applicant.getPassword());
            ps.setString(6 , applicant.getEmail());
            ps.setString(7 , applicant.getEnrolled());
            ps.executeUpdate();
            int id = -1;
            rs = ps.executeQuery("SELECT SQ_STUDENT_ID.CURRVAL FROM DUAL");
            if (rs.next()) {
                id = (int) rs.getLong(1);
            }
            return id;
        } catch (SQLException e) {
            logger.warn("Can`t add new applicant to DB");
            e.printStackTrace();
        } finally {
            DBConnection.close(ps, conn);
        }
        return 0;
    }

    @Override
    public int update(Applicant applicant) {
        String sqlCreate = "UPDATE APPLICANTS " +
                "SET " +
                "FIRST_NAME = ?, " +
                "LAST_NAME = ?, " +
                "SCHOOL_AVG_SCORES = ?, " +
                "FACULTY_ID = ?, " +
                "ST_PASSWORD = ?, " +
                "ST_EMAIL = ?, " +
                "ENROLLED = ? " +
                "WHERE ID = ?";

        Connection conn = null;
        PreparedStatement ps = null;
        int rows;
        try {
            conn = Optional
                    .ofNullable(DBConnection.getConnection())
                    .orElseThrow(() -> new AppException("Connection is null"));
            ps = conn.prepareStatement(sqlCreate);
            ps.setString(1 , applicant.getFirstName());
            ps.setString(2 , applicant.getLastName());
            ps.setInt(3 , applicant.getSchoolAverage());
            ps.setInt(4 , applicant.getFacultyId());
            ps.setString(5 , applicant.getPassword());
            ps.setString(6 , applicant.getEmail());
            ps.setString(7 , applicant.getEnrolled());
            ps.setInt(8 , applicant.getId());
            rows = ps.executeUpdate();
            return rows;
        } catch (SQLException e) {
            logger.warn("Can`t add new applicant to DB");
            e.printStackTrace();
        } finally {
            DBConnection.close(ps, conn);
        }
        return 0;
    }

    @Override
    public int delete(int id) {
        String sqlDelete = "DELETE " +
                "FROM APPLICANTS " +
                "WHERE ID = ?";
        int rows;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = Optional
                    .ofNullable(DBConnection.getConnection())
                    .orElseThrow(() -> new AppException("Connection is null"));
            ps = conn.prepareStatement (sqlDelete);
            ps.setInt (1 , id);
            rows = ps.executeUpdate();
            return rows;
        } catch (SQLException e) {
            logger.warn("Error when try to delete record with applicant by id");
            e.printStackTrace();
        } finally {
            DBConnection.close (ps, conn);
        }
        return 0;
    }

    @Override
    public Applicant getById(int id) {
        Applicant applicant;
        String sqlGetById = "SELECT * FROM APPLICANTS WHERE ID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = Optional
                    .ofNullable(DBConnection.getConnection())
                    .orElseThrow(() -> new AppException("Connection is null"));
            ps = conn.prepareStatement (sqlGetById);
            ps.setInt (1, id);
            rs = ps.executeQuery ();
            if (rs.next()) {
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
            logger.warn("Troubles with getting applicant by his ID");
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, ps, conn);
        }
        return null;
    }

    @Override
    public List<Applicant> getAll() {
        List<Applicant> applicants = new ArrayList<>();
        Applicant applicant;
        String sqlGetAll = "SELECT * FROM APPLICANTS";
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = Optional
                    .ofNullable(DBConnection.getConnection())
                    .orElseThrow(() -> new AppException("Connection is null"));
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
            logger.warn("Troubles with getting list of all Applicants");
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, st, conn);
        }
        return applicants;
    }

    @Override
    public Applicant getByEmail(String email) {
        Applicant applicant;
        String sqlGetByEmail = "SELECT * FROM APPLICANTS WHERE ST_EMAIL = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = Optional
                    .ofNullable(DBConnection.getConnection())
                    .orElseThrow(() -> new AppException("Connection is null"));
            ps = conn.prepareStatement(sqlGetByEmail);
            ps.setString(1, email);
            rs = ps.executeQuery();
            applicant = new Applicant();
            if (rs.next()) {
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
            logger.warn("Troubles with getting Applicant by his ID");
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, ps, conn);
        }
        return null;
    }

    @Override
    public int getIdByEmail(String email) {
        String sqlGetByEmail = "SELECT ID FROM APPLICANTS WHERE ST_EMAIL = ?";
        int studentId = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = Optional
                    .ofNullable(DBConnection.getConnection())
                    .orElseThrow(() -> new AppException("Connection is null"));
            ps = conn.prepareStatement(sqlGetByEmail);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                studentId = rs.getInt("ID");
            }
            return studentId;
        } catch (SQLException e) {
            logger.info("Troubles with getting Applicant ID by his Email");
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, ps, conn);
        }
        return -1;
    }

    @Override
    public boolean checkEmailUnique(String email) {
        String sqlGetByEmail = "SELECT * FROM APPLICANTS WHERE ST_EMAIL = ?";

        String emailDb = "";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = Optional
                    .ofNullable(DBConnection.getConnection())
                    .orElseThrow(() -> new AppException("Connection is null"));
            ps = conn.prepareStatement(sqlGetByEmail);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                emailDb = rs.getString("ST_EMAIL");
            }
            return !email.equals(emailDb);
        } catch (SQLException e) {
            logger.info("Troubles with check email unique");
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, ps, conn);
        }
        return false;
    }

    @Override
    public List<Applicant> getByEnrolled() {
        List<Applicant> applicants = new ArrayList<>();
        Applicant applicant;
        String sqlGetAll = "SELECT * FROM APPLICANTS WHERE ENROLLED = 'Y'";
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = Optional
                    .ofNullable(DBConnection.getConnection())
                    .orElseThrow(() -> new AppException("Connection is null"));
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
            logger.warn("Troubles with getting list of all Enrolled Applicants");
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, st, conn);
        }
        return applicants;
    }
}
