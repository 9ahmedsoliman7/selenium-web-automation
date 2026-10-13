package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class LoginTests extends BaseTest {

    @Test
    public void validUserShouldReachInventoryPage() {
        InventoryPage inventory = new LoginPage(driver)
                .open()
                .loginAs("standard_user", "secret_sauce");

        Assert.assertTrue(inventory.isLoaded(), "Inventory page was not loaded");
        Assert.assertEquals(inventory.getTitleText(), "Products");
    }

    @Test
    public void lockedOutUserShouldSeeError() {
        LoginPage loginPage = new LoginPage(driver).open();
        loginPage.login("locked_out_user", "secret_sauce");

        String message = loginPage.getErrorMessage();
        Assert.assertTrue(message.contains("locked out"), "Unexpected message: " + message);
    }

    @Test
    public void emptyUsernameShouldSeeError() {
        LoginPage loginPage = new LoginPage(driver).open();
        loginPage.login("", "secret_sauce");

        String message = loginPage.getErrorMessage();
        Assert.assertTrue(message.contains("Username is required"), "Unexpected message: " + message);
    }

    @Test
    public void emptyPasswordShouldSeeError() {
        LoginPage loginPage = new LoginPage(driver).open();
        loginPage.login("standard_user", "");

        String message = loginPage.getErrorMessage();
        Assert.assertTrue(message.contains("Password is required"), "Unexpected message: " + message);
    }

    @Test
    public void wrongPasswordShouldSeeError() {
        LoginPage loginPage = new LoginPage(driver).open();
        loginPage.login("standard_user", "wrong_password");

        String message = loginPage.getErrorMessage();
        Assert.assertTrue(message.contains("do not match"), "Unexpected message: " + message);
    }
}
