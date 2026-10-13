# Selenium Web Automation (Java + TestNG)

A web test automation framework built with **Selenium WebDriver**, **Java**, **TestNG** and **Maven**, using the **Page Object Model**.

The tests run against [Sauce Demo](https://www.saucedemo.com), a public demo e-commerce site made for practising test automation. The login accounts used here are the public demo accounts shown on that site.

## Tech stack

| Area | Tool |
|---|---|
| Language | Java (compiled at Java 17 level) |
| Browser automation | Selenium WebDriver 4.43.0 (Chrome) |
| Test runner and assertions | TestNG 7.11.0 |
| Build tool | Maven |
| Design pattern | Page Object Model |

Selenium Manager downloads the matching ChromeDriver automatically, so no manual driver setup is needed. Google Chrome must be installed.

## Project structure

```
src
└── test
    └── java
        ├── base/    BaseTest (browser start, screenshot on failure, quit)
        │            AuthenticatedTest (logs in before each test)
        ├── pages/   BasePage, LoginPage, InventoryPage, CartPage, CheckoutPage
        └── tests/   LoginTests, CartTests, CheckoutTests
```

## Test coverage (11 tests)

| Area | Test | Expected result |
|---|---|---|
| Login | Valid user | Inventory page opens with the title "Products" |
| Login | Locked-out user | Error message about the locked-out user |
| Login | Empty username | "Username is required" |
| Login | Empty password | "Password is required" |
| Login | Wrong password | Error that the credentials do not match |
| Cart | Add one product | Cart badge shows 1 |
| Cart | Add two products | Cart badge shows 2 |
| Cart | Remove a product | Cart badge disappears |
| Cart | Open the cart | The added product is listed |
| Checkout | Complete a purchase | Order confirmation is displayed |
| Checkout | Missing first name | "First Name is required" |

## Design notes

- **Page Object Model:** each page has its own class with its locators and actions, and the test classes only describe the scenario.
- **Page methods return the next page** (for example `loginAs(...)` returns `InventoryPage`), so a test reads like the user journey.
- **Explicit waits** (`WebDriverWait`) are used instead of fixed sleeps.
- **Stable locators:** the site's `data-test` attributes are used wherever possible.
- **Test setup is shared:** `BaseTest` starts and closes Chrome for every test, and `AuthenticatedTest` logs in so the cart and checkout tests start from the products page.
- **Screenshot on failure:** a screenshot of a failed test is saved in `target/screenshots`.
- **Chrome password popup:** Chrome can show a "password found in a data breach" popup for the public demo password, so the Chrome password manager and its leak check are turned off in `BaseTest`.

## How to run

Prerequisites: JDK 17 or newer, Google Chrome, and an internet connection.

**From IntelliJ IDEA:** right-click a test class (or the `tests` package) and choose Run.

**From the command line** (Maven required):

```
mvn test
```

## Possible next steps

- Run the tests on more browsers and in parallel.
- Add a test report (for example Allure).
- Use TestNG data providers for the login scenarios.
- Add sorting and product details scenarios.
- Run the suite in CI with GitHub Actions.

## Author

**Ahmed Soliman**, Junior QA Engineer (ISTQB CTFL)

- LinkedIn: [linkedin.com/in/ahmed-soliman-qa](https://linkedin.com/in/ahmed-soliman-qa)
- GitHub: [github.com/9ahmedsoliman7](https://github.com/9ahmedsoliman7)