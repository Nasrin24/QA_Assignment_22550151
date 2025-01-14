package TestCases;
import Base.BaseTest;
import Pages.*;
import Utils.ExcelHandler;
import Utils.ErrorScreenShotsHandler;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FAQPageChecker extends BaseTest {
    @Test
    public void faqInfoAssertion() {

        HomePage homePage = new HomePage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        ProductPage productPage = new ProductPage(driver);
        BecomeASellerPage becomeASellerPage = new BecomeASellerPage(driver);
        HelpCenterPage helpCenterPage = new HelpCenterPage(driver);
        FAQPage faqPage = new FAQPage(driver);

        // Initialize Excel Information
        String excelFilePath = "src/test/resources/Testdata/TestData.xlsx";
        String sheetName = "Data";

        // Initialize ExcelUtils
        ExcelHandler excel = new ExcelHandler(excelFilePath, sheetName);

        // Read data
        String expectedTitle = excel.getCellData(4, 1); // Row 1, Column 1

        setReportName("FAQ Page  Verifications- Test Case 7");
        startTest("FAQ Page Verifications- Test Case 7");
        String actualPageTitle = faqPage.careersOperations();
        test = extent.createTest("FAQ  Page Title Verification", "FAQ  Page Title Verification");

        try {
            // Capture a screenshot before performing the assertion
            String screenshotPath1 = ErrorScreenShotsHandler.takeScreenshot(driver, "Title Verification");

            // Assert the titles and log success in Extent Report
            Assert.assertEquals(actualPageTitle, expectedTitle, "Title Matching");
            test.pass("Assertion Passed: Titles are Matching").addScreenCaptureFromPath(screenshotPath1);

        } catch (AssertionError e) {
            // Capture a screenshot on failure
            String screenshotPath2 = ErrorScreenShotsHandler.takeScreenshot(driver, "Title Comparison Failure");

            // Log failure in Extent Report
            test.fail("Assertion failed: Titles are not Matching...Looks like FAQ page not loaded ->>> Expected: " + expectedTitle + ", Actual: " + actualPageTitle + ". Exception: " + e.getMessage()).addScreenCaptureFromPath(screenshotPath2);

            // Rethrow the AssertionError to terminate the test and mark it as failed
            throw e;
        }

        // Close workbook
        excel.closeWorkbook();
    }


}
