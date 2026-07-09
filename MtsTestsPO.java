package org.example;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.stream.Collectors;

public class MtsTestsPO {
    private WebDriver driver;
    private MainPage mainPage;

    // PO гл. стр.
    public static class MainPage {
        private final WebDriver drv;
        public MainPage(WebDriver driver) { this.drv = driver; }

        public String getBlockTitle() { return drv.findElement(By.cssSelector(".pay__wrapper h2")).getText().replace("\n", " ").trim(); }
        public boolean isVisaLogoDisplayed() { return drv.findElement(By.xpath("//div[@class='pay__partners']//img[contains(@alt, 'Visa')]")).isDisplayed(); }
        public boolean isMastercardLogoDisplayed() { return drv.findElement(By.xpath("//div[@class='pay__partners']//img[contains(@alt, 'Mastercard')]")).isDisplayed(); }
        public boolean isBelkartLogoDisplayed() { return drv.findElement(By.xpath("//div[@class='pay__partners']//img[contains(@alt, 'Белкарт')]")).isDisplayed(); }
        public String getLinkHref() { return drv.findElement(By.xpath("//div[@class='pay__wrapper']//a[contains(text(), 'Подробнее о сервисе')]")).getAttribute("href"); }

        // инкапсуляция локаторов
        public String getPlaceholder(String name) {
            String css;
            switch (name) {
                case "connection-phone": css = "#connection-phone"; break;
                case "connection-sum": css = "#connection-sum"; break;
                case "connection-email": css = "#connection-email"; break;
                case "internet-phone": css = "#internet-phone"; break;
                case "internet-sum": css = "#internet-sum"; break;
                case "score-instalment": css = "#score-instalment"; break;
                case "instalment-sum": css = "#instalment-sum"; break;
                case "debt-phone": css = "input[id*='debt'], input[placeholder*='счета']"; break;
                case "debt-sum": css = "input[id*='sum'], #instalment-sum"; break;
                default: css = name;
            }
            return drv.findElement(By.cssSelector(css)).getAttribute("placeholder");
        }

        public void selectTab(String partialText) {
            WebElement tab = drv.findElement(By.xpath("//div[@class='pay__wrapper']//*[contains(text(), '" + partialText + "')]"));
            ((JavascriptExecutor) drv).executeScript("arguments[0].click();", tab);
        }

        public void fillAndSubmit(String phone, String sum, String email) {
            drv.findElement(By.id("connection-phone")).sendKeys(phone);
            drv.findElement(By.id("connection-sum")).sendKeys(sum);
            drv.findElement(By.id("connection-email")).sendKeys(email);
            WebElement btn = drv.findElement(By.xpath("//form[@id='pay-connection']//button[@type='submit']"));
            ((JavascriptExecutor) drv).executeScript("arguments[0].click();", btn);
        }
    }

    // PO окна оплаты
    public static class PaymentPage {
        private final WebDriver drv;
        public PaymentPage(WebDriver driver) { this.drv = driver; }

        public void switchToFrame() {
            new WebDriverWait(drv, Duration.ofSeconds(20)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector(".pay__wrapper iframe, iframe[src*='bepaid'], iframe")));
        }
        public String getAmount() { return drv.findElement(By.cssSelector(".pay-description__cost")).getText(); }
        public String getPhone() { return drv.findElement(By.cssSelector(".pay-description__text")).getText(); }
        public String getBtnText() { return drv.findElement(By.cssSelector("button[type='submit']")).getText(); }
        public int getCardLogos() { return drv.findElements(By.cssSelector(".cards-brands img")).size(); }

        public String getFormLabelsText() {
            return drv.findElements(By.xpath("//label | //span | //input[@placeholder]")).stream()
                    .map(el -> el.getText() + " " + (el.getAttribute("placeholder") != null ? el.getAttribute("placeholder") : ""))
                    .collect(Collectors.joining(" "));
        }
    }

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.silentOutput", "true");
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(java.util.logging.Level.SEVERE);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://mts.by");
        mainPage = new MainPage(driver);
        try { driver.findElement(By.cssSelector("button.cookie__btn")).click(); } catch (Exception ignored) {}
    }

    @Test
    public void testMtsPaymentFlow() {
        Assertions.assertTrue(mainPage.getBlockTitle().equalsIgnoreCase("Онлайн пополнение без комиссии"));
        Assertions.assertTrue(mainPage.isVisaLogoDisplayed(), "Логотип Visa не отображается!");
        Assertions.assertTrue(mainPage.isMastercardLogoDisplayed(), "Логотип Mastercard не отображается!");
        Assertions.assertTrue(mainPage.isBelkartLogoDisplayed(), "Логотип Белкарт не отображается!");
        Assertions.assertTrue(mainPage.getLinkHref().contains("help"));

        // проверка плейсхолдеров
        Assertions.assertEquals("Номер телефона", mainPage.getPlaceholder("connection-phone"));
        Assertions.assertEquals("Сумма", mainPage.getPlaceholder("connection-sum"));
        Assertions.assertEquals("E-mail для отправки чека", mainPage.getPlaceholder("connection-email"));

        mainPage.selectTab("интернет");
        Assertions.assertEquals("Номер абонента", mainPage.getPlaceholder("internet-phone"));
        Assertions.assertEquals("Сумма", mainPage.getPlaceholder("internet-sum"));

        mainPage.selectTab("Рассрочка");
        Assertions.assertEquals("Номер счета на 44", mainPage.getPlaceholder("score-instalment"));
        Assertions.assertEquals("Сумма", mainPage.getPlaceholder("instalment-sum"));

        mainPage.selectTab("Задолженность");
        String debtPlaceholder = mainPage.getPlaceholder("debt-phone");
        Assertions.assertTrue(debtPlaceholder.contains("Номер счета"));
        Assertions.assertEquals("Сумма", mainPage.getPlaceholder("debt-sum"));

        // отправка формы и переход во фрейм
        mainPage.selectTab("связи");
        mainPage.fillAndSubmit("297777777", "100", "test@test.com");

        PaymentPage paymentPage = new PaymentPage(driver);
        paymentPage.switchToFrame();

        // проверки внутри окна оплаты
        Assertions.assertTrue(paymentPage.getAmount().contains("100"));
        Assertions.assertTrue(paymentPage.getPhone().contains("297777777"));
        Assertions.assertTrue(paymentPage.getBtnText().contains("100"));
        Assertions.assertTrue(paymentPage.getCardLogos() > 0);

        String labels = paymentPage.getFormLabelsText();
        Assertions.assertTrue(labels.contains("Номер карты"));
        Assertions.assertTrue(labels.contains("ММ") && labels.contains("ГГ"));
        Assertions.assertTrue(labels.contains("CVC") || labels.contains("CVV"));
        Assertions.assertTrue(labels.contains("Имя") || labels.contains("держателя"));
    }

    @AfterEach
    public void tearDown() { if (driver != null) driver.quit(); }
}