package util;

import dao.implementations.ApplicantImpl;
import dao.implementations.ExamImpl;
import dao.implementations.ExaminationListImpl;
import dao.model.Applicant;
import dao.model.Exam;
import dao.model.ExaminationList;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static String generatePin() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public static void createExamRecords() {
        ExaminationListImpl ex = new ExaminationListImpl();
        ApplicantImpl appService = new ApplicantImpl ();
        ExamImpl exam = new ExamImpl();
        SecureRandom r = new SecureRandom();

        List<Integer> list_id = new ArrayList<>();
        List<Exam> examList = exam.getAll();
        List<Applicant> applicantList = appService.getAll();

        for (Exam el: examList) {
            list_id.add(el.getExamId());
        }
        for (Applicant aplct: applicantList) {
            List<Integer> copy = new ArrayList<>(list_id);
            for (int i = 0; i < 3; i++) {
                int randomIndex = r.nextInt(copy.size());
                ex.create(new ExaminationList(aplct.getId(), copy.get(randomIndex) , r.nextInt(101)));
                copy.remove(randomIndex);
            }
        }
        System.out.println("\n Create samples records done\n");
    }
}
