package dao.implementations;

import dao.interfaces.FacultyDao;
import dao.model.Faculty;
import data.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacultyImpl implements FacultyDao {

    private static final Logger logger = LoggerFactory.getLogger(FacultyImpl.class);

    @Override
    public void create(Faculty faculty) {
        String sqlCreate = "INSERT INTO FACULTY_LIST " +
                "(FACULTY_NAME, FACULTY_CAPACITY, FACULTY_MIN_GRADE) " +
                "VALUES " +
                "(?, ?, ?)";

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sqlCreate);
            ps.setString(1, faculty.getFacultyName());
            ps.setInt(2, faculty.getFacultyCapacity());
            ps.setInt(3, faculty.getMinGrade());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.info("Can`t add new faculty to DB");
            e.printStackTrace();
        } finally {
            DBConnection.close(ps, conn);
        }
    }

    @Override
    public List<Faculty> getAll() {
        List<Faculty> faults = new ArrayList<>();
        Faculty faculty;
        String sqlGetAll = "SELECT * FROM FACULTY_LIST";
        Connection conn;
        Statement st;
        ResultSet rs;
        try {
            conn = DBConnection.getConnection();
            if (conn == null) throw new AssertionError();
            st = conn.createStatement();
            st.execute(sqlGetAll);
            rs = st.getResultSet();
            while (rs.next()) {
                faculty = new Faculty();
                faculty.setFacultyId(rs.getInt("FACULTY_ID"));
                faculty.setFacultyName(rs.getString("FACULTY_NAME"));
                faculty.setFacultyCapacity(rs.getInt("FACULTY_CAPACITY"));
                faculty.setMinGrade(rs.getInt("FACULTY_MIN_GRADE"));
                faults.add(faculty);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return faults;
    }








    @Override
    public Faculty getById(int id) {
        return null;
    }

    @Override
    public void update(Faculty faculty) {

    }

    @Override
    public void delete(int id) {

    }


    @Override
    public void setCapacity(String facultyName) {

    }

    @Override
    public void setMinGrade(String facultyName) {

    }

    @Override
    public String getNameById(int id) {
        String sqlGetName = "SELECT FACULTY_NAME FROM FACULTY_LIST WHERE ID = ?";
        String facultyName = "";
        Connection conn;
        PreparedStatement ps;
        ResultSet rs;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sqlGetName);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                facultyName = rs.getString("FACULTY_NAME");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facultyName;
    }
}
