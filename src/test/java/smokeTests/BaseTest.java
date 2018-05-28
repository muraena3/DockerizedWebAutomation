package test.java.smokeTests;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import test.java.utils.PropertyFileUtils;
import test.java.utils.TestUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;


public abstract class BaseTest {

  public WebDriver driver;
  public TestUtils utils = new TestUtils();


  @BeforeClass(alwaysRun = true)
  public void beforeClass() {
    Reporter.log("-------------------------------Executing test: "
        + this.getClass().getSimpleName() + "-------------------------------", true);
    setup();
  }

  @AfterClass(alwaysRun = true)
  public void afterClass() {
    if (driver != null) driver.quit();
    Reporter.log("--------------------------------------" +
        "-------------------------------------", true);
  }

  /**
   * Sets config options for the test. Including setting up logging and environment.
   */
  private void setup() {
    try {
      driver = createWebDriver();

    } catch (Exception ex) {
      utils.logError("Test setup failed \n" + ex.getMessage());
    }

  }

  /**
   * helper method for create Webdriver.
   */
  private WebDriver createWebDriver() {
    try {

      String browser = PropertyFileUtils.getConfig().getProperty("browser");
      String host = System.getProperty("seleniumHubHost");


      switch (browser) {
        case "CHROME":
          //WebDriverManager.chromedriver().setup();
          ChromeOptions options = new ChromeOptions();
          options.addArguments("disable-infobars");
          options.addArguments("--no-sandbox");
          options.addArguments("--privileged");
          options.addArguments("--start-maximized");

          LoggingPreferences logs = new LoggingPreferences();
          logs.enable(LogType.BROWSER, Level.ALL);
          options.setCapability(CapabilityType.LOGGING_PREFS, logs);
          Map<String, Object> prefs = new HashMap<String, Object>();
          prefs.put("credentials_enable_service", false);
          prefs.put("profile.password_manager_enabled", false);
          options.setExperimentalOption("prefs", prefs);

          DesiredCapabilities capability = DesiredCapabilities.chrome();
          capability.setCapability(ChromeOptions.CAPABILITY, options);
          capability.setBrowserName("chrome");
          try {

            //String pathtoHub = "http://localhost:4444/wd/hub";
            //host = "192.168.99.1";
            //String pathtoHub = "http://54.158.94.89:4444/wd/hub";
            driver = new RemoteWebDriver(new URL(
                "http://" + host + ":4444/wd/hub"), capability);
          } catch (MalformedURLException e) {
            Reporter.log(e.toString(), true);
          }

          break;

        case "Firefox":
          driver = new FirefoxDriver();
          break;

        case "IE":
          InternetExplorerOptions ieOptions
              = new InternetExplorerOptions();
          ieOptions.setCapability(
              InternetExplorerDriver
                  .INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
          ieOptions.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
          ieOptions.setCapability("requireWindowFocus", true);
          driver = new InternetExplorerDriver(ieOptions);
          break;

        case "Opera":
          driver = new OperaDriver();
          break;

        case "Safari":
          driver = new SafariDriver();
          break;
        default:
          break;

      }

      return driver;
    } catch (Exception ex) {
      Reporter.log("- Not able to retrieve web driver.\n" + ex, true);
      return null;
    }
  }

  /**
   * takes a screenshot of the browser window.
   */
  public void attachScreenshotTestNgReport(String postfix) {
    final String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HHmmss")
        .format(Calendar.getInstance().getTime());

    String screenshotDir = System.getProperty("user.dir")
        + File.separator + "target"
        + File.separator + "surefire-reports"
        + File.separator + "screenshots";

    File file = new File(screenshotDir);
    if (!file.exists()) {
      file.mkdir();
    }

    String fileName = "scn-" + timeStamp + "-" + postfix;
    try {

      String filePath = screenshotDir + File.separator + fileName;

      Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS)
          .withName(fileName).save(screenshotDir);

      utils.log("screenshot: " + postfix);
      Reporter.log("\n\n-------------------------------------------------------------------------");
      filePath = "screenshots" + File.separator
          + filePath.substring(filePath.indexOf(fileName)) + ".png";
      Reporter.log("<img src=\"" + filePath + "\" width=\"10%\" />");
      Reporter.log("------------------------------------------------------------------------------");

    } catch (Exception ex) {
      utils.log("FAIL - Failed to take screenshot\n" + ex);
    }
  }

  /**
   * delete folder if exits.
   */
  private void deleteResultsFolder() {
    String directorypath = System.getProperty("user.dir")
        + File.separator + "results";
    try {
      FileUtils.deleteDirectory(new File(directorypath));
    } catch (IOException e) {
      utils.logError("error deleting folder");
    }
  }

  /**
   * @return string value of current url.
   */
  public String getCurrentUrl() {
    return driver.getCurrentUrl();
  }


}
