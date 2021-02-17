package dao.implementations;

import dao.interfaces.FacultyDao;
import dao.model.Faculty;
import data.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FacultyImpl implements FacultyDao {

    @Override
    public List<Faculty> getAll() {
        List<Faculty> faults = new ArrayList<>();
        Faculty faculty;
        String sqlGetAll = "SELECT * FROM FACULTY_LIST";
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
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
                faults.add(faculty);
//                System.out.println(faculty.toString());
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
    public void create(Faculty faculty) {

    }

    @Override
    public void setCapacity(String facultyName) {

    }

    @Override
    public void setMinGrade(String facultyName) {

    }

    @Override
    public void getNameById(int id) {

    }
}
