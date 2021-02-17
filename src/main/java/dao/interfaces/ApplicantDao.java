package dao.interfaces;

import dao.model.Applicant;

import java.util.List;

public interface ApplicantDao extends BaseDao<Applicant> {

    List<Applicant> getByFaculty(String faculty);
    List<Applicant> getByEnrolled(boolean isStudent);
    Applicant getByEmail(String email);
    int getIdByEmail(String email);
    boolean checkEmailUnique(String email);
    int getTotalMark(int id);

}
