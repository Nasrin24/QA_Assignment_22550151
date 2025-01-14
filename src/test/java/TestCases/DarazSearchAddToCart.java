package TestCases;
import Base.BaseTest;
import Pages.HomePage;
import Pages.ProductPage;
import Pages.SearchResultsPage;
import Utils.ExcelHandler;
import Utils.ErrorScreenShotsHandler;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DarazSearchAddToCart extends BaseTest {
    @BeforeTest
    public void setup() {
        setUpBrowser();
    }

    @Test
    public void searchAndBuyThinkpadLaptop() {

        HomePage homePage = new HomePage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        ProductPage productPage = new ProductPage(driver);

        // Initialize Excel Information
        String excelFilePath = "src/test/resources/Testdata/TestData.xlsx";
        String sheetName = "Data";

        // Initialize ExcelUtils
        ExcelHandler excel = new ExcelHandler(excelFilePath, sheetName);

        // Read data
        String laptopBrand = excel.getCellData(1, 1); // Row 1, Column 1

        // Step 1: Search for Thinkpad laptop
        homePage.searchFor(laptopBrand);
        setReportName("Add To Cart Scenario- Test Case 1");
        startTest("Add To Cart Scenario- Test Case 1");
        test = extent.createTest("Successful Searched", "System Successfully searched the item and get the result");
        String screenshotPath1 = ErrorScreenShotsHandler.takeScreenshot(driver, "SuccessfulSearch");
        test.pass("System Successfully searched the item and get the result").addScreenCaptureFromPath(screenshotPath1);

        // Step 2: Select the first product
        double actualValue = searchResultsPage.assertPrice();
        double expectedPrice = 50000;
        test = extent.createTest("Now Price is comparing", "Now Price is comparing");

        try {
            String screenshotPath5 = ErrorScreenShotsHandler.takeScreenshot(driver, "Price Comparison");
            Assert.assertTrue(actualValue <= expectedPrice, "Price of the first item exceeds the expected value! Actual: $" + actualValue + ", Expected: $" + expectedPrice);
            test.pass("Price is within the expected range.").addScreenCaptureFromPath(screenshotPath5);
        } catch (AssertionError e) {
            // Capture screenshot on failure
            String screenshotPath4 = ErrorScreenShotsHandler.takeScreenshot(driver, "Price Comparison");
            test.fail("Assertion failed: " + e.getMessage()).addScreenCaptureFromPath(screenshotPath4);
            throw e; // Rethrow to terminate the test
        }
        searchResultsPage.selectFirstProduct();
        test = extent.createTest("First Item Selected", "System Successfully searched the item and get the select the first result");
        String screenshotPath2 = ErrorScreenShotsHandler.takeScreenshot(driver, "FirstResultTaken");
        test.pass("System Successfully searched the item and select  the first result").addScreenCaptureFromPath(screenshotPath2);

        // Write data back to the Excel file
        excel.setCellData(1, 2, "Thinkpad laptop is selected", excelFilePath);

        // Step 3: Proceed to add To Cart
        productPage.addToCart();
        test = extent.createTest("Successfully Add to Cart", "System Successfully  Add the item to Cart");
        String screenshotPath3 = ErrorScreenShotsHandler.takeScreenshot(driver, "AddToCart");
        test.pass("System Successfully selected Add the item to Cart (User need to login to add the items)").addScreenCaptureFromPath(screenshotPath3);

        // Close workbook
        excel.closeWorkbook();
    }

    @AfterTest
    public void close() {
        tearDown();
    }


}
