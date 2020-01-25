// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class UsuwaniewycieczkiTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void usuwaniewycieczki() {
    driver.get("http://localhost:4200//manageTrips");
    {
      WebDriverWait wait = new WebDriverWait(driver, 30);
      WebElement element = driver.findElement(By.cssSelector(".mat-progress-spinner"));
      wait.until(ExpectedConditions.stalenessOf(element));
    }
    driver.findElement(By.xpath("//tr[5]/td[7]/button")).click();
    driver.findElement(By.xpath("//button[contains(.,\'Tak\')]")).click();
    {
      WebDriverWait wait = new WebDriverWait(driver, 30);
      WebElement element = driver.findElement(By.cssSelector(".mat-progress-spinner"));
      wait.until(ExpectedConditions.stalenessOf(element));
    }
    driver.findElement(By.xpath("//button[contains(.,\'OK\')]")).click();
    {
      WebDriverWait wait = new WebDriverWait(driver, 30);
      WebElement element = driver.findElement(By.cssSelector(".mat-progress-spinner"));
      wait.until(ExpectedConditions.stalenessOf(element));
    }
    {
      WebDriverWait wait = new WebDriverWait(driver, 30);
      WebElement element = driver.findElement(By.cssSelector(".mat-row:nth-child(5)"));
      wait.until(ExpectedConditions.stalenessOf(element));
    }
  }
}
