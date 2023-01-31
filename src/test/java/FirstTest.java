import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class FirstTest {

    public static void main(String[] args) {
        //Bu kısım driverı kullanılabilir hale getirir
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        // =========================================================================
        //                              GET METHODLARI
        // =========================================================================

        // 1- driver.get("url") --> yazdiğimiz url'e gider
        driver.get("https://www.amazon.com.tr/"); // https mutlaka yazılmalıdır ancak " www." yazmaksakta yinede yazlışır

        // 2- driver.getTitle() --> içinde oldugumuz sayfanin basligini döndürür
        System.out.println(driver.getTitle()); // bunu ya sout ile yazdıracağız yada bir String değişkene atayacağız

        // 3- driver.gerCurrentUrl() --> icinde olduğumuz sayfanin url'sini döndürür
        System.out.println(driver.getCurrentUrl()); // bunu ya sout ile yazdıracağız yada bir String değişkene atayacağız

        // 4- driver.getPageSource(); --> icinde oluduğumuz sayfanın kaynak kodlarını yazdırır
        //System.out.println(driver.getPageSource()); //bunu ya sout ile yazdıracağız yada bir String değişkene atayacağız

        // 5- driver.getWindowHandle() --> icinde olduğumuz sayfanin UNIQUE hash kodunu döndürür
        System.out.println(driver.getWindowHandle()); //bunu ya sout ile yazdıracağız yada bir String değişkene atayacağız

        // 6- driver.getWindowHandles() --> driver calisirken acilan tum sayfalarin UNIQUE hash kodunu döndürür
        System.out.println(driver.getWindowHandles()); //bunu sout ile yazdırabiliriz yada bunu  String bir Set'in içine atabiliriz

        // =========================================================================
        //                              NAVIGATE METHODLARI
        // =========================================================================

        // 1- driver.navigate().to("url") --> istenen url'e gider !! get ile arasındaki fark bu bize farklı imkanlar tanır iler geri gibi
        driver.navigate().to("https://www.facebook.com");

        // 2- driver.navigate().back() --> bir önceki sayfaya donus yapar
        driver.navigate().back();

        // 3- driver.navigate().forward() --> back() ile geldiği sayfaya yeniden gider
        driver.navigate().forward();

        // 4- driver.navigate.refresh() --> icinde olunan sayfayi yeniler
        driver.navigate().refresh();

        // 5- driver olusturuldugunda acilan sayfayi kapatmak istersek
        driver.close();

        // 6- driver calisirken birden fazla tab veya window actiysa tumunu kapatmak icin
        driver.quit();

        // =========================================================================
        //                              MANAGE METHODLARI
        // =========================================================================

        // 1- driver.manage().window().getSize() --> içinde olduğumuz sayfanın pixel olarak olculerini dondurur
        System.out.println(driver.manage().window().getSize());

        // 2- driver.manage().window().getPosition() --> icinde olduğumuz sayfanin pixel olarak konumunu döndürür (Başlangıc noktasını döndürür o ise sol alt köşedir)
        System.out.println(driver.manage().window().getPosition());

        // 3- driver.manage().window().setPosition(new Point(15,15) --> sayfanin başlangıcını set ettiğimiz pozisyona getiririz
        driver.manage().window().setPosition(new Point(15, 15));

        // 4- driver.manage().window().setSize(new Dimension(900,600)) --> sayfanin boyutlarını istediğimiz pixel boyutlarına ayarlar
        driver.manage().window().setSize(new Dimension(900, 600));

        // konumu ve boyutu yeniledikten sonra tekrar yazdirirsak
        System.out.println("yeni boyut " + driver.manage().window().getSize());
        System.out.println("yeni konum " + driver.manage().window().getPosition());

        // 5- driver.manage().window().maximize() --> icinde oldugu sayfayi maximize yapar
        driver.manage().window().maximize();

        // 6- driver.manage().window().maximize() --> icinde oldugu sayfayi fullscreen yapar
        driver.manage().window().fullscreen();

        // 7- driver.manage().window().minimize() --> icinde olduğu sayfayı minimize yapar
        driver.manage().window().minimize();
        // =========================================================================

        /*

        İmplicitlyWait: driver'a sayfanin yuklenmesi ve kullanacagimiz webelementlerin bulunmasi icin
        bekeleyecegi maximum sureyi belirtir. Driver bu sure icerisinde sayfa yuklenir/web element bulunursa
        beklemeden calismaya devam eder. bu sure bittiği halde sayfa yuklenememis/webElement bulunamamissa
        Exception vererek calismayi durdurur.

        Not: maximize ve implicitlyWait methodları her zaman kullanmak faydalı olacaktır (en başta kullanmak)

        */

        // 1- driver.manage().timeouts().implicitlyWait(Duration.of..) --> driver'a ne kadar bekleyeceğini söyler
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));


        // =========================================================================
        //                                LOCATORS
        // =========================================================================

        /*
        Bir web uygulamasında kullanilan etkilesimli veya etkilesimsiz her sey bir weElement'tir
        Locator bir web sitesindeki herhangi bir webElementi driver'a tarig erme yontemidir.


        Her bir HTML ogesi temelde 3 unsurdan olusur
        1- Tag    2- Attribute   3- Attribute value


        Locate islemi benzerisiz olmalidir (benzersiz)
        Locate benzersiz olmaz ise NoSuchElementExeption verecek ve islem yapmayacaktir


        Selenium'da kullanilan 8 tane locator vardir
        Bizim test yaparken bu 8 locator'dan hangisini kullanacagimiz WebElement unnique olarak tarif edebilecegini bulup
        onu kullanmamız gerekir
        Locate islemini otomatik yapacak bir islem YOKTUR


        1- id = WebElement aramaKutusu = driver.findElement(By.id("twotabsearchtextbox"))
        En populer yol id kullanmaktır
        eğer id kullanacaksak onun unique oldugundan emin olmalıyız her developer unique yapmayabilir

        2- name = WebElement aramaKutusu = driver.findElement(By.name("field-keywords"))

        3- classname = WebElement aramaKutusu = driver.findElement(By.classname("nav-input nav-progressive-attribute"))

        4- tagname

        5- linktext

        6- partialLinkText

        7- xpath() --> En guclu locator'dır ve tum webElementleri unique olarak belirleyebilir //tagName[@attributeIsmi='attributeValue']

        8- cssSelector() --> xpath'den farkı // ve @ kullanılmamasıdır

        İd dışındakilerde unique değer bulmak neredeyse imkansızdır
         */


        // =========================================================================
        //                             WEBELEMENT METHODLAR
        // =========================================================================

        // 1- sendKeys() --> örneğin amazonda aramacubuguna gidip nutella yazdırabiliriz
        WebElement arKutu = driver.findElement(By.id("twotabsearchtextbox"));
        arKutu.sendKeys("Nutella");

        // 2- submit() methodu --> bu yazıyı yazdıktan sonra enterlayabilmesi için kullanılır
        arKutu.submit();


        //Aşağıdakinin tam syntax'i WebElement resultText = driver.findElement(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small'")); (Bu bir örnek sadece)
        // 3- getText() --> yapılan aramanın sonuç yazısını almak için kullanılır
        WebElement resultText = driver.findElement(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small'"));
        System.out.println(resultText.getText()); // tabikide ya string bir değişkene atanır yada direkt sout ile yazdırılır

        // 4- click() --> istenilen butona tıklamamızı sağlar
        driver.findElement(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small'")).click();

        // 5- driver.findElements() --> bulunulan sayfada içerisinde belirtilen  şeyden kaç tane varsa döndürür.
        List<WebElement> buttonList = driver.findElements(By.tagName("button")); // bu kod bize bir list döndürür ve ilgili sayfada kaç tane buton var bize gösterir
        System.out.println(buttonList.size());
        // =========================================================================

        //@Test ile main methoda gerek kalmadan testlerimizi run edebiliriz

        //@Before her methodan önce çalıştırılır

        //@After her methodan sonra çalıştırılır

        //@BeforeClass sınıfın başında bir kez çalışır sonra çalışmaz !! METHODLAR STATİC OLMAK ZORUNDADIRLAR

        //@AfterClass sınıfın sonunda bir kez çalışır sonra çalışmaz  !! METHODLAR STATİC OLMAK ZORUNDADIRLAR

        //@Ignore methodumuz'u run timede görmezden gelinmesini sağlar

        // =========================================================================
        // =========================================================================

        // =========================================================================
        //                            ASSERTİON METHODLAR
        // =========================================================================

        /*
        Biz Test senaryosunun neyi ongordugune gore karar veririz.


        Bize if-else gibi davranır
        Assert.assertTrue(asilSonuc.contains(beklenenSonuc));


        Assert.assertTrue(kosul);
        Assert.assertFalse(koşul);
        Assert.assertEqual(Expected,actual)
         */

        //    ÖRNEKLER
        WebDriverManager.chromedriver().setup();
        WebDriver driver1 = new ChromeDriver();
        driver1.manage().window().maximize();
        driver1.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));


        driver1.get("http://automationexercise.com");


        WebElement logoElement = driver1.findElement(By.xpath("//img[@src='/static/images/home/logo.png']"));
        Assert.assertTrue(logoElement.isDisplayed()); // isDisplayed bize görünüyor mu görünmüyor mu onu döndürüyor.


        WebElement signUpLink = driver1.findElement(By.xpath("//a[text()=' Signup / Login']"));
        signUpLink.click();


        WebElement loginLetter = driver1.findElement(By.xpath("//h2[text()='Login to your account']"));
        Assert.assertTrue(loginLetter.isDisplayed());


        WebElement emailAdressBox = driver1.findElement(By.xpath("//input[@data-qa='login-email']"));
        emailAdressBox.sendKeys("ahmet@nehaber.com");


        WebElement passwordBox = driver1.findElement(By.xpath("//input[@data-qa='login-password']"));
        passwordBox.sendKeys("12345");


        driver1.findElement(By.xpath("//button[@data-qa='login-button']")).click();


        WebElement loggedIn = driver1.findElement(By.xpath("//a[text()=' Logged in as ']"));
        System.out.println(loggedIn.getText());


        driver1.findElement(By.xpath("//a[text()=' Logout'] ")).click();


        String expected = "https://automationexercise.com/login";
        String actual = driver1.getCurrentUrl();
        Assert.assertEquals(expected,actual);

        driver1.close();



    }
}
