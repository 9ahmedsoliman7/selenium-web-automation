package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By usernameField = By.cssSelector("[data-test='username']");
    private final By passwordField = By.cssSelector("[data-test='password']");
    private final By loginButton = By.cssSelector("[data-test='login-button']");

    // NOT verified yet: check this one in Chrome DevTools (F12) after a failed login
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        driver.get(BASE_URL);
        return this;
    }

    public void login(String username, String password) {
        type(usernameField, username);
        type(passwordField, password);
        click(loginButton);
    }

    public InventoryPage loginAs(String username, String password) {
        login(username, password);
        return new InventoryPage(driver);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }
}
