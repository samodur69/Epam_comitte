package dao.implementations;

import dao.model.Faculty;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class FacultyImplTest {
    FacultyImpl impl;

    @BeforeTest
    public void start() {
        impl = new FacultyImpl();
    }

    @Test (description = "add new faculty to DB", enabled = true)
    public void testGetById() {
        Faculty fac = new Faculty("Test", 10, 200);
        impl.create(fac);
        List<Faculty> fac_list = impl.getAll();
//        Assert.assertTrue(fac_list.contains(fac));
        Assert.assertEquals(11, fac.getFacultyId(), "Faculty record was added correctly");
    }

    @AfterTest
    public void deleteData() {

    }
}
