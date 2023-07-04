import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class practiceWebform {
    WebDriver driver;

    @BeforeAll
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @DisplayName("Visit the webform and get title")
    @Test
    public void getTitle() {
        driver.get("https://www.digitalunite.com/practice-webform-learners");
        String title_actual = driver.getTitle();
        String title_expected = "Practice webform for learners | Digital Unite";
        System.out.println(title_actual);
        Assertions.assertTrue(title_actual.contains(title_expected));
        Assertions.assertEquals(title_actual, title_expected);

    }

    @DisplayName("Fillup given webform and assert message")
    @Test
    public void fillWebForm() {
        driver.get("https://www.digitalunite.com/practice-webform-learners");

        driver.findElement(By.id("onetrust-reject-all-handler")).click();


        driver.findElement(By.id("edit-name")).sendKeys("Maria Mimi");
        driver.findElement(By.id("edit-number")).sendKeys("01997280348");
        driver.findElement(By.xpath("//label[contains(text(),'20-30')]")).click();

        driver.findElement(By.id("edit-date")).click();
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Date date = new Date();
        String currentDate = dateFormat.format(date);

        driver.findElement(By.id("edit-date")).sendKeys(currentDate, Keys.ENTER);

        driver.findElement(By.id("edit-email")).sendKeys("mariamimi2247@gmail.com");
        driver.findElement(By.id("edit-tell-us-a-bit-about-yourself-")).sendKeys("I am student of CSE student at Daffodil International University,.");

        WebElement uploadElement = driver.findElement(By.id("edit-documentation-upload"));

        File file = new File(".\\src\\test\\resources\\Assignment _Group 6_Sec-A(55).pdf");
        uploadElement.sendKeys(file.getAbsolutePath());

        driver.findElement(By.id("edit-age")).click();
        driver.findElement(By.id("edit-submit")).click();

        driver.switchTo().alert().accept();

        String actual_msg = driver.findElement(By.className("page-title")).getText();
        String expected_msg = "Thank you for your submission!";
        Assertions.assertEquals(actual_msg, expected_msg);

    }

    @AfterAll
    public void closeDriver(){
        driver.quit();

    }
}

