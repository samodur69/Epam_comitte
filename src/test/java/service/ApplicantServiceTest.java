package service;

public class ApplicantServiceTest {

//    private ApplicantService applicantServiceUnderTest;
//    private ApplicantImpl aplImplService;
//    private Connection conn;
//    private Statement st;
//    private Logger logger = (Logger) LoggerFactory.getLogger(ApplicantServiceTest.class);
//
//    @BeforeMethod
//    public void setUp() throws SQLException {
//        applicantServiceUnderTest = new ApplicantService();
//        aplImplService = new ApplicantImpl();
//        conn = DBConnection.getConnection();
//        if (conn != null) {
//            st = conn.createStatement();
//        }
//        logger.info("Start testing Applicant DAO class");
//    }
//
//    @Test
//    public void testEnrollAllApplicants() {
//        // Setup
//
//        // Run the test
//        final int result = applicantServiceUnderTest.enrollAllApplicants();
//
//        // Verify the results
//        assertEquals(0 , result);
//    }
//
//    @Test
//    public void testGetTotalMark() throws SQLException {
//        // use with one student
//        ResultSet rs = st.executeQuery("SELECT MAX(ID) FROM APPLICANTS");
//        int studentId = 0;
//        if (rs.next()) {
//            studentId = rs.getInt(1);
//        }
//        Applicant applicant = aplImplService.getById(studentId);
//
//        // Run the test
//        final int result = applicantServiceUnderTest.getTotalMark(0);
//
//        // Verify the results
//        assertEquals(0 , result);
//    }
//
//    @Test
//    public void testShowTopTenStudents() {
//        // Setup
//
//        // Run the test
//        applicantServiceUnderTest.showTopTenStudents();
//
//        // Verify the results
//    }
}
