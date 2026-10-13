package tests;

import base.AuthenticatedTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CheckoutPage;

public class CheckoutTests extends AuthenticatedTest {

    private static final String BACKPACK = "sauce-labs-backpack";

    @Test
    public void completePurchaseShouldShowConfirmation() {
        inventoryPage.addToCart(BACKPACK);

        CheckoutPage checkoutPage = inventoryPage.openCart().proceedToCheckout();
        checkoutPage.fillInformation("Ahmed", "Soliman", "12345")
                .clickContinue()
                .clickFinish();

        String message = checkoutPage.getConfirmationMessage();
        Assert.assertTrue(message.contains("Thank you for your order"), "Unexpected message: " + message);
    }

    @Test
    public void missingFirstNameShouldShowError() {
        inventoryPage.addToCart(BACKPACK);

        CheckoutPage checkoutPage = inventoryPage.openCart().proceedToCheckout();
        checkoutPage.fillInformation("", "Soliman", "12345").clickContinue();

        String message = checkoutPage.getErrorMessage();
        Assert.assertTrue(message.contains("First Name is required"), "Unexpected message: " + message);
    }
}