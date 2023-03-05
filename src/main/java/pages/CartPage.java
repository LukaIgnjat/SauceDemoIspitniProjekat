package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class CartPage {
    public WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {

    }

    public WebElement getCheckout() {
        return driver.findElement(By.id("checkout"));
    }

    public void clickCheckout() {
        getCheckout().click();
    }

    public WebElement inputFirstName() {
        return driver.findElement(By.id("first-name"));
    }

    public WebElement inputLastName() {
        return driver.findElement(By.id("last-name"));
    }

    public WebElement inputZip() {
        return driver.findElement(By.id("postal-code"));
    }

    public void setFirstName(String FirstName) {
        inputFirstName().sendKeys(FirstName);
    }

    public void setLastName(String LastName) {
        inputLastName().sendKeys(LastName);
    }

    public void setZip(String Zip) {
        inputZip().sendKeys(Zip);
    }

    public WebElement getContinue() {
        return driver.findElement(By.id("continue"));
    }

    public void clickContinue() {
        getContinue().click();
    }

    public WebElement getTotal() {
        return driver.findElement(By.className("summary_total_label"));
    }

    public Double ChekoutCount() {
        WebElement total = getTotal();
        return Double.parseDouble(total.getText().substring(8));
    }

    public Double MyCount(){
        WebElement container = driver.findElement(By.className("cart_list"));
        WebElement tax = driver.findElement(By.className("summary_tax_label"));
        Double taxNum = Double.parseDouble(tax.getText().substring(6));
        List<WebElement> listInventoryItems = container.findElements(By.className("item_pricebar"));
        double sum = 0;
        for(int i = 0; i < listInventoryItems.size(); i++) {
            WebElement itemPriceFirst = listInventoryItems.get(i).findElement(By.className("inventory_item_price"));
            String itemPriceFirstText = itemPriceFirst.getText();
            Double itemPriceFirstNumber = Double.parseDouble(itemPriceFirstText.substring(1));
            sum += itemPriceFirstNumber ++;


        }
        Double sum11 = sum + taxNum;
        BigDecimal bd = new BigDecimal(sum11).setScale(2, RoundingMode.HALF_UP);
        double sum_22 = bd.doubleValue();
        return sum_22;
    }

    public void printNamesProductInCart(){
        WebElement container = driver.findElement(By.className("cart_list"));

        List<WebElement> listInventoryItems = container.findElements(By.className("cart_item"));

        for(int i = 0; i < listInventoryItems.size(); i++) {
            WebElement itemPriceFirst = listInventoryItems.get(i).findElement(By.className("inventory_item_name"));
            String itemPriceFirstText = itemPriceFirst.getText();
            System.out.println(itemPriceFirstText);
        }

    }


    public boolean verifyProductName(String productName) {
        boolean toReturn = false;

        WebElement cartList = driver.findElement(By.xpath("//div[@class='cart_list']"));

        List<WebElement> listItems = cartList.findElements(By.xpath(".//div[@class='cart_item']"));

        for(int i = 0; i < listItems.size(); i++) {
            WebElement name = listItems.get(i).findElement(By.xpath(".//div[@class='inventory_item_name']"));
            if(name.getText().equals(productName)) {
                toReturn = true;
                break;
            }
        }
        return toReturn;
    }

    public boolean verifyProducDescription(String productDescription) {
        boolean toReturn = false;

        WebElement cartList = driver.findElement(By.xpath("//div[@class='cart_list']"));

        List<WebElement> listItems = cartList.findElements(By.xpath(".//div[@class='cart_item']"));

        for(int i = 0; i < listItems.size(); i++) {
            WebElement description = listItems.get(i).findElement(By.xpath(".//div[@class='inventory_item_desc']"));
            if(description.getText().equals(productDescription)) {
                toReturn = true;
                break;
            }
        }
        return toReturn;
    }

    public boolean verifyProducPrice(String productPrice) {
        boolean toReturn = false;

        WebElement cartList = driver.findElement(By.xpath("//div[@class='cart_list']"));

        List<WebElement> listItems = cartList.findElements(By.xpath(".//div[@class='cart_item']"));

        for(int i = 0; i < listItems.size(); i++) {
            WebElement price = listItems.get(i).findElement(By.xpath(".//div[@class='inventory_item_price']"));
            //String itemPriceText = price.getText();
            //Double itemPriceNumber = Double.parseDouble(itemPriceText.substring(1));
            if(price.getText().substring(1).equals(productPrice)) {
                toReturn = true;
                break;
            }
        }
        return toReturn;
    }




    public boolean verifyProductCountCard(String productCountCart) {
        boolean toReturn = false;

        WebElement cartList = driver.findElement(By.xpath(".//div[@class='primary_header']"));

        List<WebElement> listItems = cartList.findElements(By.xpath(".//div[@class='shopping_cart_container']"));

        for(int i = 0; i < listItems.size(); i++) {
            WebElement number = listItems.get(i).findElement(By.xpath(".//span[@class='shopping_cart_badge']"));
            if(number.getText().equals(productCountCart)) {
                toReturn = true;
                break;
            }
        }
        return toReturn;
    }


    public void close() {
        driver.close();
        driver.quit();
    }

}


