package Pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ProductPage {
    WebDriver driver;

    // Locators
    @FindBy(xpath = "//*[@id=\"module_add_to_cart\"]/div/button[2]") // Add to Cart button
            WebElement addToCartButton;

    @FindBy(xpath = "//*[@id=\"module_add_to_cart\"]/div/button[1]/span/span") // Proceed to Buy It Now
    WebElement buyItNow;

    @FindBy(id = "wrong element") // It is a wrong element
    WebElement wrongElement;

    // Constructor to initialize elements
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Methods Used in Products Page
    public void buyItNow() {
        buyItNow.click();
    }

    public void addToCart() {
        addToCartButton.click();
    }

    public void wrongElementTest() {
        wrongElement.click();
    }
}
