package dao.daoimpl;

import dao.ExamDao;
import data.DBConnection;
import dao.model.Exam;

import java.sql.*;
import java.util.List;

public class ExamImpl implements ExamDao {

    @Override
    public List getAll() {
        String sql = "select * from exams_list";
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM EXAMS_LIST");
            while (rs.next()) {
                int id;
                String name;
                id = rs.getInt(1);
                name = rs.getString(2);
                System.out.println("id: " + id + " - name: " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(con);
        }
        return null;
    }

    public Exam getById(int id) {
        Exam exam = null;
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM EXAMS_LIST WHERE EXAM_ID = ?";
        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1));
                System.out.println(rs.getString(2));

//                exam.setExamId(rs.getInt("EXAM_ID"));
//                exam.setExamName(rs.getString("EXAM_NAME"));
//                System.out.println(exam.getExamId() + "     " + exam.getExamName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(ps, con);
        }
        return exam;
    }

    @Override
    public Object update(Object entity) {
        return null;
    }

    @Override
    public boolean delete(Object id) {
        return false;
    }

    @Override
    public boolean create(Object exam) {
        return false;
    }
}
