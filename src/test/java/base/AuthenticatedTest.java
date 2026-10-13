package base;

import org.testng.annotations.BeforeMethod;
import pages.InventoryPage;
import pages.LoginPage;

public class AuthenticatedTest extends BaseTest {

    protected InventoryPage inventoryPage;

    // Runs after BaseTest.setUp(), because parent configuration methods run first
    @BeforeMethod
    public void loginAsStandardUser() {
        inventoryPage = new LoginPage(driver)
                .open()
                .loginAs("standard_user", "secret_sauce");
        inventoryPage.isLoaded();
    }
}
