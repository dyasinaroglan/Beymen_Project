import excelUtils.ExcelUtilities;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.Driver;
import utils.ParentClass;

import java.io.IOException;
import java.util.List;

import static pages.Locators.*;

public class Tests extends ParentClass {

    Actions actions = new Actions(driver);

    @BeforeTest
    public void gotoSite() {
        driver.get(url);
        String actualUrl = Driver.getDriver().getCurrentUrl();
        Assert.assertEquals(actualUrl, url);
        logger.info("ana sayfaya giriş yapıldı");
    }

    @Test
    public void searchButton() throws IOException {
        clickTo(cookies);
        logger.info("çerezler kabul edildi");
        List<String> list = ExcelUtilities.getData(file, sayfa);
        for (int i = 0; i < list.size(); i++) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
            sendKeysTo(searchBox, list.get(i), true);
            actions.sendKeys(Keys.ENTER).build().perform();
            sleep(1000);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
            element.click();
            actions.sendKeys(Keys.DELETE).build().perform();
        }
    }

    @Test(priority = 1)
    public void randomProductSelect() {
        logger.info("");
        List<WebElement> list = driver.findElements(lProductList);
        int options = list.size();
        int option = random(options);
        list.get(option).click();
    }

    @Test(priority = 2)
    public void productWrite() throws IOException {

        String productData = driver.findElement(lproductBrand).getText() + "\n" +
                driver.findElement(lproductSkills).getText() + "\n" +
                driver.findElement(lProductPrice).getText();
        System.out.println(productData);

        ExcelUtilities.writeTXT(pathTXT, productData);
        logger.info("ürün bilgileri yazıldı");
    }

    @Test(priority = 3)
    public void productAdd() {

        List<WebElement> list = driver.findElements(lSizeSelect);
        List<WebElement> listCritical = driver.findElements(lSizeSelectCritical);

        if (list.size() > 0) {
            clickTo(list.get(random(list.size())));
        } else if (listCritical.size() > 0) {
            clickTo(listCritical.get(random(listCritical.size())));
        }
        sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(lProductAdd)).click();

        String actual = driver.findElement(lProductPrice).getText(); //Ürün sayfasındaki fiyatı alıyoruz.

        wait.until(ExpectedConditions.elementToBeClickable(lMyBasket)).click(); //Sepetim sayfasına gidiyoruz.

        String expected = driver.findElement(lBasketPrice).getText(); //Sepetim sayfasındaki fiyatı alıyoruz.

        Assert.assertEquals(actual, expected); //Fiyatları karşılaştırıyoruz.
        logger.info("ürün sepete eklendi ve fiyat karşılaştırılması yapıldı");
    }

    @Test(priority = 4)
    public void productIncrease() {

        WebElement element = driver.findElement(lProductCount);
        List<WebElement> list = driver.findElements(lOptions);
        try {
            if (list.get(1).getText().contains("2")) {
                Select select = new Select(element);
                select.selectByIndex(1);
                String expectedCount = "2 adet";
                String actualCount = select.getFirstSelectedOption().getText().trim();
                Assert.assertEquals(actualCount, expectedCount);
                logger.info("Ürün sayısı 2'ye eşitlendi.");
            }

        } catch (Exception e) {
            logger.info("Ürün sayısı 2'ye eşitlenemedi.");
        }
    }

    @Test(priority = 5)
    public void productDelete() {
        wait.until(ExpectedConditions.elementToBeClickable(lDeleteButton)).click();
        String actual = "SEPETINIZDE ÜRÜN BULUNMAMAKTADIR";
        String element = driver.findElement(lMessage).getText();
        Assertions.assertEquals(actual, element);
    }
}