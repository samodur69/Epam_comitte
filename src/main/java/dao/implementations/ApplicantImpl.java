package dao.implementations;

import dao.interfaces.ApplicantDao;
import dao.model.Applicant;
import data.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ApplicantImpl implements ApplicantDao {

    @Override
    public void create(Applicant applicant) {
        String sqlCreate = "INSERT INTO STUDENTS " +
                "(FIRST_NAME, LAST_NAME, SCHOOL_AVERAGE, FACULTY_ID, ST_PASSWORD) VALUES (?, ?, ?, " +
                "SELECT FACULTY_ID FROM FACULTY_LIST WHERE FACULTY_NAME = ?), ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        applicant.setPassword(Applicant.generatePin());
        System.out.println(applicant.getPassword());
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sqlCreate);
            ps.setString(1, applicant.getFirstName());
            ps.setString(2, applicant.getLastName());
            ps.setInt(3, applicant.getSchoolAverage());
            ps.setString(4, "Mathematics"); // TODO insert id. set faculty id in model
            ps.setString(5, applicant.getPassword());
        } catch (SQLException e) {
            System.out.println("Can`t add new applicant to DB");
            e.printStackTrace();
        }
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
    public List<Applicant> getAll() {
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
