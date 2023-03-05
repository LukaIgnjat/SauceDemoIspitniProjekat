package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ProductPage {

    public static WebDriver driver;

    public ProductPage(ChromeDriver driver) {
        this.driver = driver;
    }

    public boolean isOpen() {
        String url = driver.getCurrentUrl();

        if (url.equals("https://www.saucedemo.com/inventory.html")) {
            return true;
        } else {
            return false;
        }

    }

    public void close() {
        driver.close();
        driver.quit();
    }

    public void addProductToCartByName(String productName) {
        WebElement container = driver.findElement(By.id("inventory_container"));

        List<WebElement> listInventoryItems = container.findElements(By.xpath(".//div[@class='inventory_item']"));

        for (int i = 0; i < listInventoryItems.size(); i++) {
            WebElement itemName = listInventoryItems.get(i).findElement(By.xpath(".//div[@class='inventory_item_name']"));
            String itemNameText = itemName.getText();

            if (itemNameText.equals(productName)) {
                WebElement addButton = listInventoryItems.get(i).findElement(By.xpath(".//button"));
                addButton.click();
                break;
            }
        }
    }

    public void productCountInCart() {
        WebElement cartNumber = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']"));
        //return Integer.parseInt(cartNumber.getText());
        cartNumber.getText();
        System.out.println("Number of item in Card is " + " " + cartNumber.getText());

    }

    public void printProductPrice() {
        WebElement container = driver.findElement(By.id("inventory_container"));

        List<WebElement> listInventoryItems = container.findElements(By.xpath(".//div[@class='inventory_item']"));

        for (int i = 0; i < listInventoryItems.size(); i++) {
            WebElement itemPrice = listInventoryItems.get(i).findElement(By.xpath(".//div[@class='inventory_item_price']"));
            System.out.println(itemPrice.getText());
        }
    }


    public WebElement getCart() {
        return driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
    }

    public void openCart() {

        getCart().click();
    }

    public void sortByNamefromAtoZ(String sortByNamefromAtoZ) {


        WebElement sortContainer = driver.findElement(By.xpath("//select[@data-test='product_sort_container']"));

        sortContainer.click();

        List<WebElement> options = sortContainer.findElements(By.xpath(".//option"));


        for (int i = 0; i < options.size(); i++) {
            String optionText = options.get(i).getText();
            if (optionText.equals(sortByNamefromAtoZ)) {
                options.get(i).click();
                break;
            }
        }


    }

    public boolean isProductSortFromAToZ() {
        boolean toReturn = true;
        WebElement container = driver.findElement(By.id("inventory_container"));

        List<WebElement> listInventoryItems = container.findElements(By.xpath(".//div[@class='inventory_item']"));

        for (int i = 0; i < listInventoryItems.size() - 1; i++) {
            WebElement itemNameCurrent = listInventoryItems.get(i).findElement(By.xpath(".//div[@class='inventory_item_name']"));
            WebElement itemNameNext = listInventoryItems.get(i + 1).findElement(By.xpath(".//div[@class='inventory_item_name']"));

            if (itemNameNext.getText().compareTo(itemNameCurrent.getText()) < 0) {
                toReturn = false;
            }


        }
        return toReturn;
    }


    public Integer productCountInCart1() {
        //WebElement cartNumber = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']"));
        //return Integer.parseInt(cartNumber.getText());

        List<WebElement> listCartBadge = driver.findElements(By.xpath("//span[@class='shopping_cart_badge']"));
        if (listCartBadge.size() == 0) {
            return 0;
        } else {
            return Integer.parseInt(listCartBadge.get(0).getText());
        }
    }

    public boolean isProductSortFromAZ() {
        boolean toReturn = false;
        WebElement container = driver.findElement(By.id("inventory_container"));
        List<WebElement> listInventoryItems = container.findElements(By.xpath(".//div[@class='inventory_item']"));


        String[] actual = new String[listInventoryItems.size()];
        String[] sorted = new String[listInventoryItems.size()];

        for (int i = 0; i < listInventoryItems.size(); i++) {
            WebElement itemNames = listInventoryItems.get(i).findElement(By.xpath(".//div[@class='inventory_item_name']"));
            actual[i] = sorted[i] = itemNames.getText();
        }


        Arrays.sort(sorted);



        for (int i = 0; i < listInventoryItems.size(); i++) {
            int z = i + 1;
            if (actual[i].equals(sorted[i])) {
                System.out.println("|| Expected: " + sorted[i] + " || Actual: " + actual[i] + " || at row " + z);
                toReturn = true;
            }
        }


        return toReturn;

    }

    public boolean isProductSortFromZA() {
        boolean toReturn = false;
        WebElement container = driver.findElement(By.id("inventory_container"));
        List<WebElement> listInventoryItems = container.findElements(By.xpath(".//div[@class='inventory_item']"));


        String[] actual = new String[listInventoryItems.size()];
        String[] sorted = new String[listInventoryItems.size()];

        for (int i = 0; i < listInventoryItems.size(); i++) {
            WebElement itemNames = listInventoryItems.get(i).findElement(By.xpath(".//div[@class='inventory_item_name']"));
            actual[i] = sorted[i] = itemNames.getText();
        }


        Arrays.sort(sorted, Collections.reverseOrder());




        for (int i = 0; i < listInventoryItems.size(); i++) {
            int z = i + 1;
            if (actual[i].equals(sorted[i])) {
                System.out.println("|| Expected: " + sorted[i] + " || Actual: " + actual[i] + " || at row " + z);
                toReturn = true;
            }
        }


        return toReturn;

    }

    public boolean isProductFromAZCompareTo() {
        boolean toReturn = true;
        WebElement container = driver.findElement(By.id("inventory_container"));

        List<WebElement> listInventoryItems = container.findElements(By.xpath(".//div[@class='inventory_item']"));

        for (int i = 0; i < listInventoryItems.size() - 1; i++) {
            WebElement itemNameCurrent = listInventoryItems.get(i).findElement(By.xpath(".//div[@class='inventory_item_name']"));
            WebElement itemNameNext = listInventoryItems.get(i + 1).findElement(By.xpath(".//div[@class='inventory_item_name']"));

            if (itemNameNext.getText().compareTo(itemNameCurrent.getText()) < 0) {
                toReturn = false;
            }


        }
        return toReturn;
    }

    public boolean isProductFromZACompareTo() {
        boolean toReturn = true;
        WebElement container = driver.findElement(By.id("inventory_container"));

        List<WebElement> listInventoryItems = container.findElements(By.xpath(".//div[@class='inventory_item']"));

        for (int i = 0; i < listInventoryItems.size() - 1; i++) {
            WebElement itemNameCurrent = listInventoryItems.get(i).findElement(By.xpath(".//div[@class='inventory_item_name']"));
            WebElement itemNameNext = listInventoryItems.get(i + 1).findElement(By.xpath(".//div[@class='inventory_item_name']"));

            if (itemNameNext.getText().compareTo(itemNameCurrent.getText()) > 0) {
                toReturn = false;
            }


        }
        return toReturn;
    }
}








