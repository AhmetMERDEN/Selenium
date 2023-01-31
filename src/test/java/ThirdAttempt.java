import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class ThirdAttempt {

        /*
        Üzerine geldiğimizde açılan penceler için veya tut çek yapabildiğimiz şeyler için veya sağ click ve double click gibi
        olaylarda yetersiz kalınabilir bunları gerçekleştirebilmek için "Action Class"ı mevcuttur.
        Kısaca "Action Class" mouse ve klavye ile yapılabilecek herşeyi yapmamızı sağlar
        Mouse ve Klavye methodları olarak temelde 2'ye ayrılır.
         */


        /*
        Action class'indaki methodlar Action class'indan olusturulan bir obje uzerinden kullanilir.

        Mesela => Actions actions = new Actions(driver); içene oluşturacaginiz driver nesnesini giriyorsunuz
         */









        /*
        ========================= MOUSE METHODLARI ================================
        1- actions.moveToElement() => belirtilen elementin üzerine gelir

        2-click() bir çok click methodu var

        i)action.click() => istediğiniz web elementine click yapar

        ii)action.clickAndHold() => istedigimiz web element'e basili bekler

        iii)action.contextClick() => istedigimiz web element'e sag click yapar

        iiii)action.doubleClick() => istedigimiz web element'e çift click yapar

        3-actions.dragAndDrop() => istegimiz web element'i alip, istegimiz baska bir elementin uzerine birakir.

         */


        /*
        =========================== KLAVYE METHODLARI ==============================
        Temel olarak iki hareket var: A) Istedigimiz tusa tek seferlik basma B) Istedigimiz tusa istedigimiz surece basili tutma

        1- actions.sendKeys()
        2- actions.keyDown()
        3- actions.keyUp()

        En son işimiz bittiğinde .perform yazmamız gerekmektedir MUTLAKA!!
        actions.click(nameBox).sendKeys("Ahmet").sendKeys(Keys.TAB).sendKeys("a@bcde.com").sendKeys(Keys.TAB).sendKeys(Keys.ENTER).perform();

         */


    @Test
    public void test01() {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        driver.get("http://automationexercise.com");

        WebElement signUpLink = driver.findElement(By.xpath("//a[text()=' Signup / Login']"));
        actions.click(signUpLink).perform();

        WebElement nameBox = driver.findElement(By.xpath(""));
        actions.click(nameBox).sendKeys("Ahmet").sendKeys(Keys.TAB).sendKeys("a@bcde.com").sendKeys(Keys.TAB).sendKeys(Keys.ENTER).perform();
        driver.close();


    }
}
