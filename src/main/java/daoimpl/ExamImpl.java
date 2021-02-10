package daoimpl;

import dao.ExamDao;
import data.DBConnection;
import model.Exam;

import java.sql.*;
import java.util.List;

public class ExamImpl implements ExamDao {

    @Override
    public List getAll() {
        String sql = "select * from exams_list";
        try {
            Connection con = DBConnection.getConnection();
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery("select * from exams_list");
            while (rs.next()) {
                int id;
                String name;
                id = rs.getInt(1);
                name = rs.getString(2);
                System.out.println(id + "name: " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
        return null;
    }

    public Exam getById(int id) throws SQLException {
        Exam exam = null;
        Connection con = null;
        String sql = "select * from exams_list where id = ?";
        try {
            con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            exam.setExamId(rs.getInt("exam_id"));
            exam.setExamName(rs.getString("exam_name"));
            System.out.println(exam.getExamId() + "     " + exam.getExamName());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(con);
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
