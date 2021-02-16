package dao.implementations;

import dao.interfaces.ExaminationListDao;
import dao.model.ExaminationList;
import data.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ExaminationListImpl implements ExaminationListDao {

    private static final Logger logger = LoggerFactory.getLogger(ExaminationList.class);

    @Override
    public void create(ExaminationList record) {
        String sqlCreateRecord = "INSERT INTO EXAMINATION_RECORDS " +
                "(STUDENT_ID, EXAM_ID, GRADE) VALUES (?, ?, ?)";

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sqlCreateRecord);
            ps.setInt(1, record.getStudentId());
            ps.setInt(2, record.getExamId());
            ps.setInt(3, record.getGrade());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.info("Can`t add new exam record to DB");
            e.printStackTrace();
        } finally {
            DBConnection.close(ps, conn);
        }
    }

    @Override
    public List<ExaminationList> getAll() {
        return null;
    }

    @Override
    public ExaminationList getById(int id) {
        return null;
    }

    @Override
    public void update(ExaminationList obj) {

    }

    @Override
    public void delete(int id) {

    }


}
