package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class MtsTests {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        // отключение логов в консоли
        System.setProperty("webdriver.chrome.silentOutput", "true");
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(java.util.logging.Level.SEVERE);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://mts.by");

        // куки
        try {
            driver.findElement(By.xpath("//button[contains(text(),'Принять') or contains(@class,'cookie__btn')]")).click();
        } catch (Exception ignored) {
        }
    }

    @Test
    public void testOnlineTopUpBlock() {
        String title = driver.findElement(By.xpath("//div[@class='pay__wrapper']/h2")).getText().replace("\n", " ").trim();
        Assertions.assertEquals("онлайн пополнение без комиссии", title.toLowerCase(), "Неверное название блока!");

        List<WebElement> logos = driver.findElements(By.xpath("//div[@class='pay__partners']//img"));
        Assertions.assertFalse(logos.isEmpty(), "Логотипы платежных систем не найдены!");

        WebElement linkElement = driver.findElement(By.xpath("//div[@class='pay__wrapper']//a[contains(text(), 'Подробнее о сервисе')]"));
        String href = linkElement.getAttribute("href");
        Assertions.assertNotNull(href, "Ссылка не содержит атрибут href!");
        Assertions.assertTrue(href.contains("help"), "Ссылка ведет на некорректный URL!");

        driver.findElement(By.id("connection-phone")).sendKeys("297777777");
        driver.findElement(By.id("connection-sum")).sendKeys("100");
        driver.findElement(By.id("connection-email")).sendKeys("test@test.com");

        WebElement button = driver.findElement(By.xpath("//form[@id='pay-connection']//button[@type='submit']"));
        Assertions.assertTrue(button.isEnabled(), "Кнопка 'Продолжить' заблокирована!");
        button.click();

        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe("https://mts.by")));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
