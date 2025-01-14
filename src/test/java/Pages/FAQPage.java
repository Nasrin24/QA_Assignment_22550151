package Pages;

import Utils.ErrorScreenShotsHandler;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import Base.BaseTest;

public class FAQPage extends BaseTest {
    private final WebDriverWait wait;
    WebDriver driver;

    // Locators using @FindBy
    @FindBy(xpath = "//*[@id=\"topActionHeader\"]/div/div[2]/div/div[1]/a") // Home page link
            WebElement homeDarazIcon;

    @FindBy(xpath = "(//a[@href='//buyer-helpcenter.daraz.lk/s/page'])[1]") // Help Center link
    WebElement helpCenterLink;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[1]/div/div/div[3]/div[2]") // FAQ page link
    WebElement faqLink;

    // Constructor to initialize elements
    public FAQPage(WebDriver driver) {
//        this.driver = driver;
//        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        PageFactory.initElements(driver, this);
    }

    // Method to searchFor
    public String careersOperations() {

        // Click On Daraz Icon

            try {
                // Click On Daraz Icon
                waitAndClick(homeDarazIcon);
                takeScreenshotWithTest("HomePage", "Successfully navigated to Home Page using DARAZ Icon");

                // Scroll to bottom
                scrollToBottom();
                takeScreenshotWithTest("PageBottom", "Successfully navigated to Bottom of the Page");

                // Click On Help Center
                waitAndClick(helpCenterLink);
                takeScreenshotWithTest("HelpCenter", "Successfully navigated to Help Center Page");

                // Click On FAQ link
                waitAndClick(faqLink);
                takeScreenshotWithTest("FAQPage", "Successfully navigated to FAQ Page");

                // Search for FAQ
                WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("typehead")));
                searchBox.sendKeys("How do I return my item?" + Keys.ENTER);
                takeScreenshotWithTest("SearchResults", "Successfully navigated to searched content Page");

                return driver.getTitle();

            } catch (Exception e) {
                String errorMsg = "Error in careersOperations: " + e.getMessage();
                test.fail(errorMsg).addScreenCaptureFromPath(
                        ErrorScreenShotsHandler.takeScreenshot(driver, "ErrorScreenshot")
                );
                throw new RuntimeException(errorMsg, e);
            }
        }

        private void waitAndClick(WebElement element) {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        }

        private void scrollToBottom() {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        }

        private void takeScreenshotWithTest(String screenshotName, String description) {
            try {
                // Wait for any animations to complete
                Thread.sleep(3000);
                String screenshotPath = ErrorScreenShotsHandler.takeScreenshot(driver, screenshotName);
                test = extent.createTest(description, description);
                test.pass(description).addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                test.fail("Failed to take screenshot: " + e.getMessage());
            }
        }
    }




//        homeDarazIcon.click();
//        test = extent.createTest("Successful Navigate Home Page", "Successfully Home Page using DARAZ Icon");
//        String screenshotPath1 = ErrorScreenShotsHandler.takeScreenshot(driver, "GlobalDealsPage");
//        test.pass("Successfully Home Page using DARAZ Icon").addScreenCaptureFromPath(screenshotPath1);
//
//        // Scroll to the bottom of the page
//
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
//        test = extent.createTest("Successfully Navigate to Bottom Of the Page", "Successfully Navigate to Bottom Of the Page");
//        String screenshotPath2 = ErrorScreenShotsHandler.takeScreenshot(driver, "PageBottom");
//        test.pass("System Successfully Navigate to Bottom Of the Page").addScreenCaptureFromPath(screenshotPath2);
//
//        // Click On Help Center
//
//        helpCenterLink.click();
//        test = extent.createTest("Successfully Navigate to Help Center Page", "Successfully Navigate to Help Center Page");
//        String screenshotPath3 = ErrorScreenShotsHandler.takeScreenshot(driver, "HelpCenter");
//        test.pass("Successfully Navigate to Help Center Page").addScreenCaptureFromPath(screenshotPath3);
//
//        // Click On FAQ link
//
//        faqLink.click();
//        test = extent.createTest("Successfully Navigate to FAQ Page", "Successfully Navigate to FAQ Page");
//        String screenshotPath4 = ErrorScreenShotsHandler.takeScreenshot(driver, "CareersPage");
//        test.pass("Successfully Navigate to FAQ Page").addScreenCaptureFromPath(screenshotPath4);
//
//        // Create an explicit wait with a timeout of 30 seconds
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//        try {
//            // Wait for the element to be visible
//
//            WebElement startSearchJob = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("typehead")));
//
//            // Perform actions on the element
//            startSearchJob.sendKeys("How do I return my item?" + Keys.ENTER);
//            test = extent.createTest("Successfully Navigate to searched content Page", "Successfully Navigate to searched content Page");
//            String screenshotPath5 = ErrorScreenShotsHandler.takeScreenshot(driver, "Engineering Jobs Page");
//            test.pass("Successfully Navigate to searched content Page").addScreenCaptureFromPath(screenshotPath5);
//
//        } catch (Exception e) {
//
//            System.out.println("Element not found within the specified time.");
//            test = extent.createTest("Element not found within the specified time", "Element not found within the specified time");
//            String screenshotPath5 = ErrorScreenShotsHandler.takeScreenshot(driver, "Element not found within the specified time");
//            test.pass("Element not found within the specified time").addScreenCaptureFromPath(screenshotPath5);
//        }
//        String pageTitle = driver.getTitle();
//        System.out.println(pageTitle);
//        return pageTitle;

