import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

public class Page {
    public static WebDriver driver;
    public static WebDriverWait wait;

    @Before
    public void before() {

        System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.americanas.com.br/");
        driver.manage().window().maximize();

    }

    @Test
    public void cartSuccess() throws InterruptedException {
        String expectProduct = "liquidificador";
        String Product;
        String description = "Liquidificador Philips Walita com Jarra Inquebrável ProBlend RI2137 Inox 2,4L 6 Lâminas 12 Velocidades Duravita 800W - 220V";
        String TitleSearch;
        driver.findElement(By.id("h_search-input")).sendKeys(expectProduct);
        driver.findElement(By.id("h_search-btn")).click();
        sleep(6000);
        TitleSearch = driver.findElement(By.xpath("//*[@id='content-middle']/div[1]/div/div/h1")).getText();
        assertEquals(expectProduct, TitleSearch);
        driver.findElement(By.xpath("//*[@id='content-middle'/div[6]/div/div/div/div[1]/div[3]/div/div[2]/a/section/div[2]/div[1]/h2[contains(text(),'Liquidificador')]")).click();
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        sleep(700);
        jse.executeScript("scrollBy(0,250)", "");
        sleep(3000);
        driver.findElement(By.id("btn-buy")).click();
        driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div[3]/div/div[2]/div/a")).click();
        driver.findElement(By.id("btn-continue")).click();
        sleep(3000);
        Product = driver.findElement(By.xpath("//*[@id='app']/section/section/div[1]/div/div[1]/section/ul/li/div[2]/div[1]/h2/a")).getText();
        assertEquals(expectProduct, Product);
    }

    @Test
    public void invalidSearch() throws InterruptedException {
        String Product = "ushdsdhsduhsdhudhsdh";
        String menssage;
        driver.findElement(By.id("h_search-input")).sendKeys(Product);
        driver.findElement(By.id("h_search-btn")).click();
        sleep(500);
        menssage = driver.findElement(By.xpath("//*[@id='content-middle']/div[6]/div/div/div/div[2]/span[1]/span/span[1]")).getText();
       assertEquals("Ops!", menssage);

    }

    @Test
    public void removeProduct() throws InterruptedException {
        String Product = "liquidificador";
        String emptyBasket;
        String msgBasket = "sua cesta está vazia";
        driver.findElement(By.id("h_search-input")).sendKeys(Product);
        driver.findElement(By.id("h_search-btn")).click();
        sleep(8000);
        driver.findElement(By.xpath("//*[@id='content-middle']/div[6]/div/div/div/div[1]/div[3]/div/div[2]/a/section")).click();
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        sleep(700);
        jse.executeScript("scrollBy(0,250)", "");
        sleep(3000);
        driver.findElement(By.id("btn-buy")).click();
        driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div[3]/div/div[2]/div/a")).click();
        driver.findElement(By.id("btn-continue")).click();
        sleep(3000);
        driver.findElement(By.xpath("//*[@id='app']/section/section/div[1]/div/div[1]/section/ul/li/div[2]/div[2]/div[2]/a")).click();
        sleep(6000);
        emptyBasket = driver.findElement(By.xpath("//*[@id=\"app\"]/section/section/div[1]/div/section/h2")).getText();
        assertEquals(msgBasket, emptyBasket);
    }

    @After
    public void after(){
        driver.quit();
    }
}