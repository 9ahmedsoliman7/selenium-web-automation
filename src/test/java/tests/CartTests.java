package tests;

import base.AuthenticatedTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;

import java.util.List;

public class CartTests extends AuthenticatedTest {

    private static final String BACKPACK = "sauce-labs-backpack";
    private static final String BIKE_LIGHT = "sauce-labs-bike-light";

    @Test
    public void addingOneProductShouldShowOneInCartBadge() {
        inventoryPage.addToCart(BACKPACK);

        Assert.assertEquals(inventoryPage.getCartCount(), 1);
    }

    @Test
    public void addingTwoProductsShouldShowTwoInCartBadge() {
        inventoryPage.addToCart(BACKPACK).addToCart(BIKE_LIGHT);

        Assert.assertEquals(inventoryPage.getCartCount(), 2);
    }

    @Test
    public void removingProductShouldClearCartBadge() {
        inventoryPage.addToCart(BACKPACK).removeFromCart(BACKPACK);

        Assert.assertEquals(inventoryPage.getCartCount(), 0);
    }

    @Test
    public void cartShouldListTheAddedProduct() {
        inventoryPage.addToCart(BACKPACK);
        CartPage cartPage = inventoryPage.openCart();

        Assert.assertEquals(cartPage.getItemNames(), List.of("Sauce Labs Backpack"));
    }
}
