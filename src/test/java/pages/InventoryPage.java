package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class InventoryPage extends BasePage {

    private final By title = By.cssSelector("[data-test='title']");
    private final By cartLink = By.cssSelector("[data-test='shopping-cart-link']");
    private final By cartBadge = By.cssSelector("[data-test='shopping-cart-badge']");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    // The product name is written in the data-test attribute, for example: sauce-labs-backpack
    private By addToCartButton(String product) {
        return By.cssSelector("[data-test='add-to-cart-" + product + "']");
    }

    private By removeButton(String product) {
        return By.cssSelector("[data-test='remove-" + product + "']");
    }

    public boolean isLoaded() {
        wait.until(ExpectedConditions.urlContains("inventory.html"));
        return waitForVisible(title).isDisplayed();
    }

    public String getTitleText() {
        return getText(title);
    }

    public InventoryPage addToCart(String product) {
        click(addToCartButton(product));
        // Wait for the button to change to "Remove", so the cart is already updated
        waitForVisible(removeButton(product));
        return this;
    }

    public InventoryPage removeFromCart(String product) {
        click(removeButton(product));
        waitForVisible(addToCartButton(product));
        return this;
    }

    public int getCartCount() {
        List<WebElement> badges = driver.findElements(cartBadge);
        return badges.isEmpty() ? 0 : Integer.parseInt(badges.get(0).getText());
    }

    public CartPage openCart() {
        click(cartLink);
        return new CartPage(driver);
    }
}
