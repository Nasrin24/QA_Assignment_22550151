package TestCases;
import Base.BaseTest;
import Pages.BecomeASellerPage;
import Pages.HomePage;
import Pages.HelpCenterPage;
import Pages.ProductPage;
import Pages.SearchResultsPage;
import Utils.ErrorScreenShotsHandler;
import Utils.ExcelHandler;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HelpCenterPageChecker extends BaseTest {
    @Test
    public void helpInfoAssertion() {

        HomePage homePage = new HomePage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        ProductPage productPage = new ProductPage(driver);
        BecomeASellerPage becomeASellerPage = new BecomeASellerPage(driver);
        HelpCenterPage helpCenterPage = new HelpCenterPage(driver);

        // Initialize Excel Information
        String excelFilePath = "src/test/resources/Testdata/TestData.xlsx";
        String sheetName = "Data";

        // Initialize ExcelUtils
        ExcelHandler excel = new ExcelHandler(excelFilePath, sheetName);

        // Read data
        String expectedSentence = excel.getCellData(3, 1); // Row 1, Column 1

        setReportName("Help Center page- Test Case 6");
        startTest("HelpCenterInfo- Test Case 6");
        String actualSentence = helpCenterPage.helpInfoAssertion();
        test = extent.createTest("Help Center InFo Page Data Verification", "Help Center InFo Page Data Verification");
        try {
            // Capture a screenshot before performing the assertion
            String screenshotPath1 = ErrorScreenShotsHandler.takeScreenshot(driver, "InfoPage");

            // Assert that the actual sentence matches the expected sentence

            Assert.assertEquals(actualSentence, expectedSentence, "Strings matching!");

            // Log success in Extent Report with a screenshot
            test.pass("System successfully navigated to Info Page")
                    .addScreenCaptureFromPath(screenshotPath1);

        } catch (AssertionError e) {
            // Capture a screenshot on assertion failure
            String screenshotPath4 = ErrorScreenShotsHandler.takeScreenshot(driver, "InfoPage_Failure");

            // Log failure in Extent Report with exception details and a screenshot
            test.fail("Assertion failed: " + e.getMessage())
                    .addScreenCaptureFromPath(screenshotPath4);

            // Rethrow the AssertionError to terminate the test and mark it as failed
            throw e;
        }

        // Close workbook
        excel.closeWorkbook();
    }

}
