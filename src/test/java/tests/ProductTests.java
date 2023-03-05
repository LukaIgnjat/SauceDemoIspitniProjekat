package tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductPage;

public class ProductTests {
   @Test
    public void verifyProductFromAtoZ() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\PC\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
       // CartPage cartPage= new CartPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();

        ProductPage productsPage = new ProductPage(driver);
        Thread.sleep(3000);

        productsPage.sortByNamefromAtoZ("Name (Z to A)");
        Thread.sleep(3000);

        boolean isProductSortedZA= productsPage.isProductFromZACompareTo();

       if(isProductSortedZA) {
           System.out.println("|| Sorting is as expected ||");
       }
       else {
           System.out.println("|| Sorting is not as expected ||");
       }

       Assert.assertEquals(isProductSortedZA, true, "Products are not sorted as expected, from Name (Z to A)");



       productsPage.sortByNamefromAtoZ("Name (A to Z)");
        Thread.sleep(3000);

       boolean isProductSorted= productsPage.isProductFromAZCompareTo();

       if(isProductSorted) {
           System.out.println("|| Sorting is as expected ||");
       }
       else {
           System.out.println("|| Sorting is not as expected ||");
       }

       Assert.assertEquals(isProductSorted, true, "Products are not sorted as expected, from Name (A to Z)");


        driver.quit();



    }
    @Test
    public void verifyNameDesPrice() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\PC\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();
        ProductPage productsPage = new ProductPage(driver);

        productsPage.addProductToCartByName("Sauce Labs Backpack");
        Thread.sleep(3000);
        //productsPage.addProductToCartByName("Sauce Labs Bike Light");
        productsPage.openCart();
        productsPage.productCountInCart();
        CartPage cartPage = new CartPage(driver);

        boolean countCart = cartPage.verifyProductCountCard("1");
        Assert.assertEquals(countCart, true, "number in cart is not 1");

        boolean productName = cartPage.verifyProductName("Sauce Labs Backpack");

        Assert.assertEquals(productName, true, "Product with name Sauce Labs Backpack is not added to cart");

        boolean productDescription = cartPage.verifyProducDescription("carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.");

        Assert.assertEquals(productDescription, true, "Product With right description is not added to cart" );

        // productsPage.productCountInCart();

        //productsPage.printProductPrice();


        boolean productPrice = cartPage.verifyProducPrice("29.99");

        Assert.assertEquals(productPrice, true, "Price is not match");
        cartPage.close();



    }
    @Test
    public void verifyTotalPriceProduct() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\PC\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();

        ProductPage productsPage = new ProductPage(driver);

        System.out.println("Number of Items before add: " + productsPage.productCountInCart1());
        Assert.assertEquals(productsPage.productCountInCart1(), 0, "Product count is not equals");

        productsPage.addProductToCartByName("Sauce Labs Backpack");
        productsPage.addProductToCartByName("Sauce Labs Bike Light");
        productsPage.addProductToCartByName("Sauce Labs Fleece Jacket");
      // productsPage.addProductToCartByName("Sauce Labs Onesie");
       // productsPage.addProductToCartByName("Sauce Labs Bolt T-Shirt");
       // productsPage.addProductToCartByName("Test.allTheThings() T-Shirt (Red)");

        productsPage.openCart();
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();
        cartPage.setFirstName("Luka");
        Thread.sleep(3000);
        cartPage.setLastName("Ignjatovic");
        Thread.sleep(3000);
        cartPage.setZip("15306");
        Thread.sleep(3000);
        cartPage.clickContinue();

        System.out.println("Number of Items after add: " + productsPage.productCountInCart1());
        System.out.println("List of products in Cart: ");
        cartPage.printNamesProductInCart();
        System.out.println("**************************************************************************");
        System.out.println("Total sum count Cart Chekout: $" + cartPage.ChekoutCount());
        System.out.println("Total sum count Cart my sum:  $" + cartPage.MyCount());

        Assert.assertEquals(cartPage.ChekoutCount(), cartPage.MyCount(), "Product count is not equals");

        cartPage.close();
    }


}
