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
//        String sqlCreate = "INSERT INTO STUDENTS " +
//                "(FIRST_NAME, LAST_NAME, SCHOOL_AVG_SCORES, FACULTY_ID, ST_PASSWORD, ST_EMAIL) VALUES (?, ?, ?, (" +
//                "SELECT FACULTY_ID FROM FACULTY_LIST WHERE FACULTY_NAME = ?), ?, ?)";
        String sqlCreate = "INSERT INTO STUDENTS " +
                "(FIRST_NAME, LAST_NAME, SCHOOL_AVG_SCORES, FACULTY_ID, ST_PASSWORD, ST_EMAIL) VALUES (?, ?, ?, ?, ?, ?)";


        Connection conn = null;
        PreparedStatement ps = null;
        System.out.println(applicant.getPassword());
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sqlCreate);
            ps.setString(1, applicant.getFirstName());
            ps.setString(2, applicant.getLastName());
            ps.setInt(3, applicant.getSchoolAverage());
//            ps.setString(4, "Mathematics"); // TODO insert id. set faculty id in model
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
//                System.out.println(applicant.toString());
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
