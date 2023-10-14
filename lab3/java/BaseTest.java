import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.objects.BasePage;

import java.time.Duration;


abstract public class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Optional. If not specified, WebDriver searches the PATH for chromedriver.
        // System.setProperty("webdriver.chrome.driver", readProperties.ConfProperties.getProperty("chromedriver"));
        WebDriverManager.chromedriver().setup();

        // создание экземпляра драйвера
        driver = new ChromeDriver();

        // окно разворачивается на полный экран
        driver.manage().window().maximize();

        // задержка на выполнение - 10 секунд
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // использование драйвера для взаимодействия со страницами
        BasePage.setDriver(driver);
    }

    @AfterEach
    public  void tearDown() {
        // Close the current window, quitting the browser if it's the last window currently open.
        //driver.close();
        // Quits this driver, closing every associated window.
        driver.quit();
    }
}
