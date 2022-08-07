package pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.ParentClass;

import java.awt.*;

public interface Locators {

    public static final Logger logger = LogManager.getLogger(ParentClass.class);
    public static final String url = "https://www.beymen.com/";

    By searchBox = By.cssSelector("input[class='default-input o-header__search--input']");
    String file = "src/main/java/excelUtils/beymen.xlsx";
    String sayfa = "Sayfa1";

    By cookies = By.cssSelector("button[id='onetrust-accept-btn-handler']");

    By lProductList = By.xpath("//*[@class=\"o-productList__item\"]");


    //Product information

    By lproductBrand = By.xpath("//a[@class='o-productDetail__brandLink']");
    By lproductSkills = By.xpath("//span[@class='o-productDetail__description']");
    By lProductPrice = By.xpath("//*[@class='m-price__new']");

    public static final String pathTXT = "src/main/java/excelUtils";


    By lProductAdd = By.xpath("//button[@id='addBasket']");

    By lSizeSelect = By.xpath("//div[@class='m-variation']/span[@class='m-variation__item']");
    By lSizeSelectCritical = By.cssSelector("div[class='m-variation'] span[class='m-variation__item -criticalStock']");

    By lSizes = By.cssSelector("div[class='m-variation'] span");
    By lMyBasket = By.cssSelector("div[class='o-header__userInfo'] a[title='Sepetim']");

    By lBasketPrice = By.cssSelector("span[class='m-productPrice__salePrice']");

    //ürün sayısını arttırma
    By lProductCount = By.cssSelector("select[id='quantitySelect0-key-0']");
    By lOptions = By.cssSelector("select[id='quantitySelect0-key-0'] option");

    //delete
    By lDeleteButton = By.cssSelector("button[class='m-basket__remove btn-text']");
    By lMessage = By.xpath("//strong[text()='Sepetinizde Ürün Bulunmamaktadır']");
}
