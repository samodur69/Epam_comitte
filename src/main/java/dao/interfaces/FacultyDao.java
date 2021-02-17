package dao.interfaces;

import dao.model.Faculty;

public interface FacultyDao extends BaseDao<Faculty> {

    void setCapacity(String facultyName);
    void setMinGrade(String facultyName);
    String getNameById(int id);
}
