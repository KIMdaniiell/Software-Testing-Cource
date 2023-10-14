package page.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import readProperties.ConfProperties;

public class LoginPage extends BasePage{
    @FindBy(xpath = "//div[contains(@data-test-id, 'password-input-error')]")
    WebElement passwordError;

    @FindBy(xpath = "//div[contains(@class, 'email-block__inner')]//h4")
    WebElement userMailAddress;

    @FindBy(xpath = "//iframe[contains(@class, 'iframe-0-2-24')]")
    private WebElement loginFrame;


    public LoginPage () {
        PageFactory.initElements(driver, this);
    }


    public LoginPage switchToLoginFrame() {
        driver.switchTo().frame(loginFrame);
        return this;
    }

    public LoginPage switchToMainFrame() {
        driver.switchTo().frame(0);
        return this;
    }

    public String getUserMailAddress() {
        return this.userMailAddress.getText();
    }

    public String getErrorMessage() {
        return this.passwordError.getText();
    }
}
