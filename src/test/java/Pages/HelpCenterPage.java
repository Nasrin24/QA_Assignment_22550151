package Pages;

import Base.BaseTest;
import Utils.ErrorScreenShotsHandler;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HelpCenterPage extends BaseTest {
    WebDriver driver;

    // Locators using @FindBy
    @FindBy(xpath = "//*[@id=\"topActionHeader\"]/div/div[2]/div/div[1]/a") // Home page link
            WebElement homeDarazIcon;

    @FindBy(xpath = "(//a[@href='//buyer-helpcenter.daraz.lk/s/page'])[1]") // Help Center link
    WebElement helpCenterLink;

    @FindBy(xpath = "(//span[@class='Notice--content--3ybAmid'])[1]") // words aboutEbay button
    WebElement wordsAboutHelpCenter;

    // Constructor to initialize elements
    public HelpCenterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to searchFor
    public String helpInfoAssertion() {
        // Click On Deals
        homeDarazIcon.click();
        test = extent.createTest("Successfully Navigate to Home Page", "Successfully Home Page using DARAZ Icon");
        String screenshotPath1 = ErrorScreenShotsHandler.takeScreenshot(driver, "GlobalDealsPage");
        test.pass("Successfully Home Page using DARAZ Icon").addScreenCaptureFromPath(screenshotPath1);

        // Scroll to the bottom of the page

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        test = extent.createTest("Successfully Navigate to Bottom Of the Page", "Successfully Navigate to Bottom Of the Page");
        String screenshotPath2 = ErrorScreenShotsHandler.takeScreenshot(driver, "PageBottom");
        test.pass("System Successfully Navigate to Bottom Of the Page").addScreenCaptureFromPath(screenshotPath2);

        String aboutHelpCenter = wordsAboutHelpCenter.getText();
        return aboutHelpCenter;


    }
}
