package util;

import dao.implementations.ExaminationListImpl;
import dao.implementations.FacultyImpl;
import dao.model.ExaminationList;
import dao.model.Faculty;

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
        FacultyImpl fac = new FacultyImpl();
        List<Faculty> facultyList;
        List<Integer> list_id = new ArrayList<>();
        facultyList = fac.getAll();
        for (Faculty el: facultyList) {
            list_id.add(el.getFacultyId());
        }
        SecureRandom r = new SecureRandom();
        for (int i = 10000; i < 10014; i++) {
            List<Integer> copy = new ArrayList<>(list_id);
            for (int j = 0; j < 3; j++) {
                int randomIndex = r.nextInt(copy.size());
                ex.create(new ExaminationList(i, copy.get(randomIndex), r.nextInt(101)));
                copy.remove(randomIndex);
            }
        }
        System.out.println("\n Create samples records done\n");
    }
}
