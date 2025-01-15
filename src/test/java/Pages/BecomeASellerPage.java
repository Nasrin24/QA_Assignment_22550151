package Pages;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BecomeASellerPage {
    WebDriver driver;

    // Locators using @FindBy
    @FindBy(xpath = "//*[@id=\"topActionSell\"]") // Become a Seller Button
            WebElement sellButton;

    @FindBy(xpath = "//*[@id=\"2625514080\"]/div/div/a/img") // Search button
    WebElement sellerLogin;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[1]/div/div[1]/div[1]")
    WebElement infoText;

    // Constructor to initialize elements
    public BecomeASellerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    // Method to searchFor
    public String SellerInfo() {
        sellButton.click();
//        // Scroll to the bottom of the page
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        sellerLogin.click();
        String info = infoText.getText();
        return info;


    }
}
