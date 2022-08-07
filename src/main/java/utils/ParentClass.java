package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ParentClass {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public ParentClass() {
        driver = Driver.getDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // TODO: 5.08.2022 elementin durumuna göre bekleme (visibil,clickable olana kadar) 
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // TODO: 5.08.2022 her element için bekleme süresi
        driver.manage().deleteAllCookies();
    }
    /**
     * aldigi url"e driver.get eden method
     * @param url string
     */
    public void goToUrl(String url) {
        driver.get(url);
    }
    /**
     * aldigi By class"indan locatora click eden method
     * @param locater By
     */
    public void clickTo(By locater) {
        wait.until(ExpectedConditions.elementToBeClickable(locater)).click();
    }
    public void clickTo(WebElement element) {
        //WebElement return ettiği için clik yapabiliyorum
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }
    public void sendKeysTo(By locater, String text) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(locater)).sendKeys(text);
    }
    public void sendKeysTo(By locator, String text, boolean clear) {
         WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        if (clear)
            element.clear();
        element.sendKeys(text);
    }
    public void sendKeysTo(WebElement element, String text) {

        wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
    }
    public void verifyText(By locator, String text) {
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Assert.assertTrue(notification.getText().toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT)));
    }

    /**
     *lokasyonunu verdiğim elemente kadar scroll yap
     * default olarak window'ın en üstüne konuşlanıyor
     * @param element
     */
    public void scrollTo(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
    public void screenShotBySelenium() throws IOException {

        String scrName="ScreenShot_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss"));
        TakesScreenshot scr= (TakesScreenshot) driver ;
        File scrShot=scr.getScreenshotAs(OutputType.FILE);
        File destScr=new File("ScreenShots\\" + scrName+ ".png");
        FileUtils.copyFile(scrShot,destScr);

    }
    public void screenShotByRobot( ) throws AWTException, IOException {
        Robot robot=new Robot();
        //Kaydedilecek ekran alanını belirliyoruz.
        java.awt.Rectangle rect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        //ekranda belirlenen dikdörtgeni resim olarak buffer a atar
        BufferedImage image= robot.createScreenCapture(rect);
        String fileName="ScreenShotRobot_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss"));
        File destFile=new File("ScreenShots\\" + fileName + ".png");
        ImageIO.write(image,"png", destFile);
    }
    public static void sleep(long milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static int random(int size) {
        return (int) (Math.random() * size);
    }
}
