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
    public int create(Faculty faculty) {
        String sqlCreate = "INSERT INTO FACULTY_LIST " +
                "(FACULTY_NAME, FACULTY_CAPACITY, FACULTY_MIN_GRADE) " +
                "VALUES " +
                "(?, ?, ?)";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        int id = -1;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sqlCreate);
            ps.setString(1, faculty.getFacultyName());
            ps.setInt(2, faculty.getFacultyCapacity());
            ps.setInt(3, faculty.getMinGrade());
            ps.executeUpdate();
            rs = ps.executeQuery("SELECT SQ_FACULTY_ID.CURRVAL FROM DUAL");
            if (rs.next()) {
                id = (int) rs.getLong(1);
            }
        } catch (SQLException | NullPointerException e) {
            logger.info("Can`t add new faculty to DB");
            e.printStackTrace();
        } finally {
            DBConnection.close(ps, conn);
        }
        return id;
    }

    @Override
    public List<Faculty> getAll() {
        List<Faculty> facultyList = new ArrayList<>();
        Faculty faculty;
        String sqlGetAll = "SELECT * FROM FACULTY_LIST";
        Connection conn = null;
        Statement st;
        ResultSet rs;
        try {
            conn = DBConnection.getConnection();
            st = conn.createStatement();
            st.execute(sqlGetAll);
            rs = st.getResultSet();
            while (rs.next()) {
                faculty = new Faculty();
                faculty.setFacultyId(rs.getInt("FACULTY_ID"));
                faculty.setFacultyName(rs.getString("FACULTY_NAME"));
                faculty.setFacultyCapacity(rs.getInt("FACULTY_CAPACITY"));
                faculty.setMinGrade(rs.getInt("FACULTY_MIN_GRADE"));
                facultyList.add(faculty);
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(conn);
        }
        return facultyList;
    }

    @Override
    public int update(Faculty faculty) {
        String sqlUpdate = "UPDATE " +
                "FACULTY_LIST " +
                "SET " +
                "FACULTY_NAME = ?, " +
                "FACULTY_CAPACITY = ?, " +
                "FACULTY_MIN_GRADE = ?" +
                "WHERE FACULTY_ID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        int rows;
        try {
            conn = DBConnection.getConnection ();
            ps = conn.prepareStatement (sqlUpdate);
            ps.setString (1, faculty.getFacultyName ());
            ps.setInt (2, faculty.getFacultyCapacity ());
            ps.setInt (3, faculty.getMinGrade ());
            ps.setInt (4, faculty.getFacultyId ());
            rows = ps.executeUpdate();
            return rows;
        } catch (SQLException e) {
            logger.warn ("Error when update faculty");
            e.printStackTrace ();
        } finally {
            DBConnection.close (ps, conn);
        }
        return 0;
    }

    @Override
    public int delete(int id) {
        String sqlDelete = "DELETE " +
                "FROM FACULTY_LIST " +
                "WHERE FACULTY_ID = ?";
        int rows;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement (sqlDelete);
            ps.setInt (1 , id);
            rows = ps.executeUpdate();
            return rows;
        } catch (SQLException e) {
            logger.warn("Error when try to delete record with faculty by id");
            e.printStackTrace();
        } finally {
            DBConnection.close (ps, conn);
        }
        return 0;
    }

    @Override
    public Faculty getById(int id) {
        Faculty faculty;
        String sqlGetById = "SELECT * FROM FACULTY_LIST WHERE FACULTY_ID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement (sqlGetById);
            ps.setInt (1, id);
            rs = ps.executeQuery ();
            while (rs.next()) {
                faculty = new Faculty();
                faculty.setFacultyId (rs.getInt("FACULTY_ID"));
                faculty.setFacultyName (rs.getString("FACULTY_NAME"));
                faculty.setFacultyCapacity (rs.getInt ("FACULTY_CAPACITY"));
                faculty.setMinGrade (rs.getInt("FACULTY_MIN_GRADE"));
                return faculty;
            }
        } catch (SQLException e) {
            logger.warn("Troubles with getting faculty by his ID");
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, ps, conn);
        }
        return null;
    }

    @Override
    public String getNameById(int id) {
        String sqlGetName = "SELECT FACULTY_NAME FROM FACULTY_LIST WHERE FACULTY_ID = ?";
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
