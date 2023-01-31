import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class SecondAttempt {
    @Test
    public void test01() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));


        driver.get("http://automationexercise.com");


        WebElement logoElement = driver.findElement(By.xpath("//img[@src='/static/images/home/logo.png']"));
        Assert.assertTrue(logoElement.isDisplayed()); // isDisplayed bize görünüyor mu görünmüyor mu onu döndürüyor.


        WebElement signUpLink = driver.findElement(By.xpath("//a[text()=' Signup / Login']"));
        signUpLink.click();


        WebElement loginLetter = driver.findElement(By.xpath("//h2[text()='Login to your account']"));
        Assert.assertTrue(loginLetter.isDisplayed());


        WebElement emailAdressBox = driver.findElement(By.xpath("//input[@data-qa='login-email']"));
        emailAdressBox.sendKeys("ahmet@nehaber.com");


        WebElement passwordBox = driver.findElement(By.xpath("//input[@data-qa='login-password']"));
        passwordBox.sendKeys("12345");


        driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();


        WebElement loggedIn = driver.findElement(By.xpath("//a[text()=' Logged in as ']"));
        System.out.println(loggedIn.getText());
        Assert.assertTrue(loggedIn.isDisplayed());

        driver.findElement(By.xpath("//a[text()=' Logout'] ")).click();


        String expected = "https://automationexercise.com/login";
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(expected, actual);

        driver.close();


    }
}
