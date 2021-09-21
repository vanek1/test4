import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TEST1 {

    public static WebDriver driver;
    public static WebDriverWait wait(int timeout) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait;
    }

    //method  - function
    public static WebDriver startBrowser (String url) throws Exception {
        System.setProperty("webdriver.chrome.driver", "src/driver/chromedriver");
        driver = new ChromeDriver();
        try {
            driver.get(url);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("browser started!!!");
//        driver.manage().window().maximize();
//        System.out.println("maximized!!!");
//        driver.manage().deleteAllCookies();
//        System.out.println("cookies deleted!!!");
        return driver;
    }

    @Test
       //method  - function
        public void test1 () throws Exception {
        System.out.println("my test 1");
        startBrowser("https://demoqa.com/");

        }
    @Test
        //method  - function
        public void test2 () throws Exception {
            System.out.println("my test 2");

    }

    @Test
    // verify home page title
    public void T001 () throws Exception {
        startBrowser("https://demoqa.com/");
        String actualTitle = driver.getTitle();
        String expectedTitle = "ToolsQA";
        Assert.assertTrue(actualTitle.equals(expectedTitle));
        System.out.println(expectedTitle + " Expected Result - " + actualTitle.equals(expectedTitle));
        driver.close();
    }

    @Test
        // verify home page category cards
    public void T002 () throws Exception {
        startBrowser("https://demoqa.com/");

        int cards = driver.findElements(By.xpath("//*[@class='card mt-4 top-card']")).size();
        int cardsExpected = 6;
        System.out.println(cards);
//        Assert.assertTrue(cards == 6);
        Assert.assertEquals(cards, 7);
        System.out.println("found 6 cards - " + (cards == 7));

        String actualTextCart1 = driver.findElement(By.xpath("//*[@class='card mt-4 top-card'][1]")).getText();
        System.out.println(actualTextCart1);
        String expectedTextCard1 = "Elements";
        Assert.assertTrue(actualTextCart1.equals(expectedTextCard1));
        System.out.println(actualTextCart1 + " Expected Result - " + actualTextCart1.equals(expectedTextCard1));

        String actualTextCart2 = driver.findElement(By.xpath("//*[@class='card mt-4 top-card'][2]")).getText();
        System.out.println(actualTextCart2);
        String expectedTextCard2 = "Forms";
        Assert.assertTrue(actualTextCart2.equals(expectedTextCard2));
        System.out.println(actualTextCart2 + " Expected Result - " + actualTextCart2.equals(expectedTextCard2));

        driver.close();
    }

    @Test
    // User can see correct text when clicks double button.
    public void T005 () throws Exception {
        startBrowser("https://demoqa.com/buttons");
        WebElement doubleButton = driver.findElement(By.xpath("//button[@id='doubleClickBtn']"));

        //User should NOT see text “You have done a double click
        int y = driver.findElements(By.xpath("//*[@id='doubleClickMessage']")).size();
        System.out.println("Look for any text on the page");
        System.out.println(" found - " + y);
        Assert.assertTrue(y == 0);

        //Click on the “Double Click Me Button
        doubleButton.click();
        int c = driver.findElements(By.xpath("//*[@id='doubleClickMessage']")).size();
        System.out.println("Click on the “Double Click Me Button");
        System.out.println(" found - " + c);
        Assert.assertTrue(c == 0);

        //Double-Click the button
        Actions actions = new Actions(driver);
        actions.doubleClick(doubleButton).perform();
        int b = driver.findElements(By.xpath("//*[@id='doubleClickMessage']")).size();
        System.out.println("Double Click Me Button");
        System.out.println(" found - " + b);
        Assert.assertTrue(b == 1);
        String text = driver.findElement(By.xpath("//*[@id='doubleClickMessage']")).getText();
        System.out.println(text);


        driver.close();
    }
    @Test
    // T012 : User can accept or cancel alert.
    public void T012 () throws Exception {
        startBrowser("https://demoqa.com/alerts");
        // Step 1: Click on the  3rd 'click me' button
        System.out.println("Step 1: Click on the  3rd 'click me' button");
        WebElement buttonClickMe = driver.findElement(By.xpath("//button[@id='confirmButton']"));
        buttonClickMe.click();
        // Step 2: Switch to the alert and click ‘OK’ button.
        System.out.println("Step 2: Switch to the alert and click ‘OK’ button.");
        Alert myAlert = driver.switchTo().alert();
        myAlert.accept();
        System.out.println("Alert is accepted, User should see text on the page: 'You selected Ok'.");
        //wait(3000);
        driver.switchTo().defaultContent();
        String actualAlertIsAccepted = driver.findElement(By.xpath("//span[@id='confirmResult']")).getText();
        String expectedAlertIsAccepted = "You selected Ok";
        Assert.assertTrue(actualAlertIsAccepted.equals(expectedAlertIsAccepted));
        System.out.println(actualAlertIsAccepted + " - User see text on the page: - " + (actualAlertIsAccepted.equals(expectedAlertIsAccepted)));
        // Step 3: Click on the  3rd click me button - Button is clicked.
        System.out.println("Step 3: Click on the  3rd click me button - Button is clicked.");
        buttonClickMe.click();
        System.out.println("Step 4: Switch to the alert and click ‘Cancel’ button.");
        Alert myAlert2 = driver.switchTo().alert();
        myAlert.dismiss();
        System.out.println("Alert is dismissed - User should see text on the page: 'You selected Cancel' ");
        String actualAlertIsCancel = driver.findElement(By.xpath("//span[@id='confirmResult']")).getText();
        String expectedAlertIsCancel = "You selected Cancel";
        Assert.assertTrue(actualAlertIsCancel.equals(expectedAlertIsCancel));
        System.out.println(actualAlertIsCancel + " - User see text on the page: - " + (actualAlertIsCancel.equals(expectedAlertIsCancel)));


        driver.close();





    }



}
