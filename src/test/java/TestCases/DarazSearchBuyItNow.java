package TestCases;

import Base.BaseTest;

import Utils.ExcelHandler;
import Utils.ErrorScreenShotsHandler;
import org.testng.annotations.Test;
import Pages.HomePage;
import Pages.SearchResultsPage;
import Pages.ProductPage;

public class DarazSearchBuyItNow extends BaseTest {

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

        // Step 1: Search for Thinkpad Laptop
        homePage.searchFor(laptopBrand);
        setReportName("Buy It Now Scenario- Test Case 2");
        startTest("Buy It Now Scenario- Test Case 2");
        test = extent.createTest("Successful Searched", "System Successfully searched the item and get the result");
        String screenshotPath1 = ErrorScreenShotsHandler.takeScreenshot(driver, "SuccessfulSearch");
        test.pass("System Successfully searched the item and get the result").addScreenCaptureFromPath(screenshotPath1);

        // Step 2: Select the first product
        searchResultsPage.selectFirstProduct();
        test = extent.createTest("First Item Selected", "System Successfully searched the item and get the select the first result");
        String screenshotPath2 = ErrorScreenShotsHandler.takeScreenshot(driver, "FirstResultTaken");
        test.pass("System Successfully searched the item and select  the first result").addScreenCaptureFromPath(screenshotPath2);

        // Write data back to the Excel file
        excel.setCellData(1, 2, "Thinkpad laptop is selected", excelFilePath);

        // Step 3: Proceed to Buy It Now
        productPage.buyItNow();
        test = extent.createTest("Successfully click on Buy Now", "System Successfully  click on Buy Now");
        String screenshotPath3 = ErrorScreenShotsHandler.takeScreenshot(driver, "BuyItNow");
        test.pass("System Successfully  click on Buy Now").addScreenCaptureFromPath(screenshotPath3);

        // Close workbook
        excel.closeWorkbook();
    }
}