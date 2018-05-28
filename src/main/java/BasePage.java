package main.java;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.java.utils.TestUtils;

import java.util.concurrent.TimeUnit;

public abstract class BasePage {
  protected WebDriver driver;
  public TestUtils utils = new TestUtils();
  private final String originalHandle;
  public WebDriverWait wait;

  /**
   * Class Constructor.
   */
  public BasePage(WebDriver driver) {
    this.driver = driver;
    wait = new WebDriverWait(driver, 10);
    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    PageFactory.initElements(driver, this);
    originalHandle = driver.getWindowHandle();

  }

  /**
   * switches to new handle.
   */
  public String switchToNewHandle() {
    String newHandle = null;
    for (String handle : driver.getWindowHandles()) {
      if (!handle.equals(originalHandle)) {
        driver.switchTo().window(handle);
        newHandle = handle;
      }
    }
    return newHandle;
  }

  /**
   * switches to original handle.
   */
  public void switchToOriginalHandle() {
    driver.switchTo().window(originalHandle);
  }

  /**
   * Closes all the open handles/windows excepts the original handle.
   */
  public void closeHandles() {
    for (String handle : driver.getWindowHandles()) {
      if (!handle.equals(originalHandle)) {
        driver.switchTo().window(handle);
        driver.close();
      }
    }

  }

  /**
   * Waits for the page to finish loading.
   *
   * @return true if successful, otherwise false
   */
  public boolean waitForPageLoad() {
    if (waitForJsNotActive(wait, driver)) {
      return true;
    } else {
      utils.logError("ERROR - Failed to wait for page load");
      return false;
    }
  }

  /**
   * Waits for the page to finish loading.
   *
   * @param wait time to wait
   * @return true if successful, otherwise false
   */
  private boolean waitForJsNotActive(WebDriverWait wait, WebDriver driver) {
    try {
      JavascriptExecutor js = (JavascriptExecutor) driver;
      String isjqueryPresent = js.executeScript("return (typeof jQuery != 'undefined');").toString();

      String condition = "";
      switch (isjqueryPresent) {
        case "true":
          condition = "return (document.readyState == 'complete' && "
              + "jQuery(':animated').length == 0 && jQuery.active == 0)";
          break;
        case "false":
          condition = "return (document.readyState == 'complete')";
          break;
        default:
          break;
      }

      wait.until(ExpectedConditions.jsReturnsValue(condition));

      return true;
    } catch (Exception ex) {
      // If document.readyState is complete then don't log as error.
      // Sometimes jQuery stays active but is not a blocker
      JavascriptExecutor js = (JavascriptExecutor) driver;
      if (js.executeScript("return document.readyState").equals("complete")
          || js.executeScript("return document.readyState").equals("interactive")) {
        System.out.println("NOTE - readyState is complete - jQuery is still active or not present");
        return true;
      } else {
        System.out.println("readyState - " + js.executeScript("return document.readyState"));
        System.out.println("jquery animated - " + js.executeScript("return jQuery(':animated').length"));
        System.out.println("jQuery active - " + js.executeScript("return jQuery.active"));

        ex.printStackTrace();
        return false;
      }
    }
  }


  public void clickWithJs(WebElement element) {
    utils.pause(4);
    JavascriptExecutor executor = (JavascriptExecutor) driver;
    executor.executeScript("arguments[0].click();", element);
  }

  /**
   * Verifies page.
   *
   * @return true if succesful, otherwise false
   */
  public boolean verifyPage(String page, WebElement element) {
    boolean status = false;
    try {
      if (element.isDisplayed())
        status = true;
    } catch (Exception ex) {
      utils.logError("unable to verify page: " + page);
    }
    return status;
  }

}
