package dao.interfaces;

import dao.model.Faculty;

public interface FacultyDao extends BaseDao<Faculty> {

    String getNameById(int facultyId);
}
