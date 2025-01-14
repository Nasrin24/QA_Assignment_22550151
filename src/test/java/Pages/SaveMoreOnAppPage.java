package Pages;
import Base.BaseTest;
import Utils.ErrorScreenShotsHandler;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SaveMoreOnAppPage  extends BaseTest{
    WebDriver driver;

    // Locators using @FindBy
    @FindBy(xpath = "//*[@id=\"topActionDownload\"]/span") // Save More On App link
            WebElement saveMoreOnApp;

    @FindBy(xpath = "/html/body/section[2]/div[1]/div/div[2]/ul/li[1]/a") // About Daraz link
    WebElement aboutDaraz;

    @FindBy(xpath = "/html/body/section[2]/div[4]/div/div[1]/h3") //  Daraz international links
    WebElement darazInterationalLinks;


    // Constructor to initialize elements
    public SaveMoreOnAppPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to searchFor
    public String saveMoreOnAppInfoAssertion() {
        // Click On save more on app
        saveMoreOnApp.click();
        test = extent.createTest("Successfully show the Download the app link window", "Successfully show the Download the app link window");
        String screenshotPath1 = ErrorScreenShotsHandler.takeScreenshot(driver, "Save more on app");
        test.pass("Successfully show the Download the app link window").addScreenCaptureFromPath(screenshotPath1);

        // Scroll to the bottom of the page
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        test = extent.createTest("Successfully Navigate to Bottom Of the Page", "Successfully Navigate to Bottom Of the Page");
        String screenshotPath2 = ErrorScreenShotsHandler.takeScreenshot(driver, "PageBottom");
        test.pass("System Successfully Navigate to Bottom Of the Page").addScreenCaptureFromPath(screenshotPath2);
        String aboutDaraz = darazInterationalLinks.getText();
        return aboutDaraz;
    }
}
