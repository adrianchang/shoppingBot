package lacosteBot;

import javafx.scene.input.DataFormat;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Main {
    private static String mainPage = "http://www.supremenewyork.com/shop/new"; //Shouldn't change this
    private static String loginPage = "https://www.lacoste.com/us/account";
    private static String myEmail = "adrian.aa.chang.aa@gmail.com";
    private static String myPassword = "Loyu0118";
    private static List<String> imgKeyWords;
    private static String name = "aaa aaa";
    private static String email = "aa@aa";
    private static String tel = "702-339-2758";
    private static String address = "asdfa";
    private static String aptEtc = "asdf";
    private static String zip = "11111";
    private static String city = "asdfas";
    private static String cityAbbrev = "PA";
    private static String countryAbbrev = "USA";
    private static String creditCardNumber = "1111111111111111";
    private static String cardExpireMonth = "01";
    private static String cardExpireYear = "2021";
    private static String CVV = "277";
    private static String dropTime = "2018-05-03 11:00:00";


    private static WebDriver driver ;

    private static void chromeDriver_init(){
        System.setProperty("webdriver.chrome.driver", "/Users/adrian/softwareConstruction/lacosteBot/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(960, 1500));
    }

    private static void firefoxDriver_init(){
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability("marionette", true);
//        firefoxOptions.addArguments("--headless");
//        firefoxOptions.addPreference("permissions.default.image", 2);
        driver = new FirefoxDriver(firefoxOptions);
        driver.manage().window().setSize(new Dimension(960, 1500));
    }

    private static void phantomDriver_init (){
        String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36";
        DesiredCapabilities desiredCaps ;

        System.setProperty("phantomjs.page.settings.userAgent", USER_AGENT);

        //init
        desiredCaps = new DesiredCapabilities();
        desiredCaps.setJavascriptEnabled(true);
        desiredCaps.setCapability("takesScreenshot", false);
        desiredCaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "phantomjs-2.1.1-macosx/bin/phantomjs");
        desiredCaps.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_CUSTOMHEADERS_PREFIX + "User-Agent", USER_AGENT);

        ArrayList<String> cliArgsCap = new ArrayList<String>();
        cliArgsCap.add("--web-security=false");
        cliArgsCap.add("--ssl-protocol=any");
        cliArgsCap.add("--ignore-ssl-errors=true");
        cliArgsCap.add("--webdriver-loglevel=ERROR");

        desiredCaps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cliArgsCap);
        driver = new PhantomJSDriver(desiredCaps);
        driver.manage().window().setSize(new Dimension(960, 1500));
    }

    private static void login (){
        driver.get(loginPage);
        WebElement email = driver.findElement(By.cssSelector("input#email"));
        email.clear();
        email.sendKeys(myEmail);
        WebElement password = driver.findElement(By.cssSelector("input#password"));
        password.clear();
        password.sendKeys(myPassword);
        driver.findElement(By.xpath("//button[text()='\n" +
                "\t\t\t\t\t\t\tsign in\n" +
                "\t\t\t\t\t\t']")).click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement welcomeMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1.welcome")));
        System.out.println("welcomeMessage " + welcomeMessage.getAttribute("innerHTML"));
    }

    private static void takeScreenShot(){
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("browser.jpeg"),true);
        }catch (IOException e){
            System.out.println(e);
        }
    }

    private static void waitSeconds(int sec){
        try {
            TimeUnit.SECONDS.sleep(sec);
        }catch (InterruptedException i){
            System.out.println(i);
        }
    }

    private static void initConfig(ConfigStructure configs){
        name = configs.getName();
        email = configs.getEmail();
        tel = configs.getTel();
        address = configs.getAddress();
        aptEtc = configs.getAptEtc();
        zip = configs.getZip();
        city = configs.getCity();
        cityAbbrev = configs.getCityAbbrev();
        countryAbbrev = configs.getCountryAbbrev();
        creditCardNumber = configs.getCreditCardNumber();
        cardExpireMonth = configs.getCardExpireMonth();
        cardExpireYear = configs.getCardExpireYear();
        CVV = configs.getCVV();
        imgKeyWords = configs.getKeyWords();
        dropTime = configs.getDropTime();
    }

    private static boolean checkMatch(String content, String keywords){
        System.out.println("content " + content);
        if (content == null){
            return false;
        }
        content = content.toLowerCase();
        keywords = keywords.toLowerCase();
        String[] keywordSplit = keywords.split(" ");
        for (String s : keywordSplit){
            if (!content.contains(s)){
                return false;
            }
        }
        return true;
    }


    private static class autoShopping extends TimerTask
    {
        private List<String> getItemsOnMainPage(){
            System.out.println("start finding item");
            driver.get(mainPage);
            List<WebElement> mainPageLinks = driver.findElements(By.cssSelector("a"));
            List<String> targetUrls = new ArrayList<String>();
            List<String> keywordsClone = new ArrayList<String>(imgKeyWords);
            for(WebElement we : mainPageLinks){
                if (keywordsClone.size() == 0){
                    break;
                }
                for (String keyword : keywordsClone){
                    try{
                        WebElement image = we.findElement(By.cssSelector("img"));
                        if (image.getAttribute("src").contains(keyword)){
                            targetUrls.add(we.getAttribute("href"));
                            keywordsClone.remove(keyword);
                            break;
                        }
                    }catch (Exception e){

                    }
                }
            }
            if (targetUrls.size() == 0){
                System.out.println("Can't find target items");
                return new ArrayList<String>();
            }
            return targetUrls;
        }
        public void run()
        {
            Instant start = Instant.now();
            List<String> targetUrls = getItemsOnMainPage();
            while (targetUrls.isEmpty()){
                targetUrls = getItemsOnMainPage();
            }
            //go to target pages
            for (String s : targetUrls){
                driver.get(s);
                driver.findElement(By.cssSelector("input[type='submit']")).click();
            }

            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement checkoutBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.button.checkout")));
            checkoutBtn.click();

            driver.findElement(By.id("order_billing_name")).sendKeys(name);
            driver.findElement(By.id("order_email")).sendKeys(email);
            driver.findElement(By.id("order_tel")).sendKeys(tel);
            driver.findElement(By.id("bo")).sendKeys(address);
            driver.findElement(By.id("oba3")).sendKeys(aptEtc);
            driver.findElement(By.id("order_billing_zip")).sendKeys(zip);
            driver.findElement(By.id("order_billing_city")).sendKeys(city);
            Select dropdownState = new Select(driver.findElement(By.id("order_billing_state")));
            dropdownState.selectByVisibleText(cityAbbrev);
            Select dropdownCountry = new Select(driver.findElement(By.id("order_billing_country")));
            dropdownCountry.selectByVisibleText(countryAbbrev);
            driver.findElement(By.id("nnaerb")).sendKeys(creditCardNumber);
            Select cardMonth = new Select(driver.findElement(By.id("credit_card_month")));
            cardMonth.selectByVisibleText(cardExpireMonth);
            Select cardYear = new Select(driver.findElement(By.id("credit_card_year")));
            cardYear.selectByVisibleText(cardExpireYear);
            driver.findElement(By.id("orcer")).sendKeys(CVV);
            WebElement checkBox = driver.findElement(By.id("order_terms"));
            Actions actions = new Actions(driver);
            actions.moveToElement(checkBox).click().build().perform();
//            driver.findElement(By.name("commit")).click();
            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            System.out.println("Time taken: "+ timeElapsed.toMillis() +" milliseconds");
//            waitSeconds(10);
//            takeScreenShot();
//            driver.close();
        }
    }

    public static void main(String[] args) {
        ConfigLoader cf = new ConfigLoader();
        ConfigStructure configs = cf.load("config.yml");
        initConfig(configs);
        firefoxDriver_init();
        driver.get("https://www.google.com/");

        //the Date and time at which you want to execute
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = dateFormatter.parse(dropTime);
            //Now create the time and schedule it
            Timer timer = new Timer();

            //Use this if you want to execute it once
            timer.schedule(new autoShopping(), date);
        }catch (Exception e){
            System.out.println("Date error " + e);
        }
    }
}
