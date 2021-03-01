package dao.interfaces;

import dao.model.Applicant;

import java.util.List;

public interface ApplicantDao extends BaseDao<Applicant> {

    Applicant getByEmail(String email);
    int getIdByEmail(String email);
    boolean checkEmailUnique(String email);
    List<Applicant> getByEnrolled();

}
