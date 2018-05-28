package main.java.pages;

import main.java.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class CompareTravelSitePage extends BasePage {

  private final String SHOWMEDEAL_BTN_XPATH = ".//a[contains(text(),'Show me the first deal')]";
  private final String TAB_BTN_XPATH = ".//*[@id='ct-header']//li[xxx]/div/a";
  private final String TAB_COUNT_XPATH = ".//*[@id='ct-header'] //li";

  @FindBy(xpath = SHOWMEDEAL_BTN_XPATH)
  WebElement showMetheFirstDealButton;

  @FindBy(xpath = TAB_COUNT_XPATH)
  List<WebElement> tabs;

  /**
   * Class Constructor.
   */
  public CompareTravelSitePage(WebDriver driver) {
    super(driver);
    waitForPageLoad();

    Assert.assertTrue(verifyPage("CompareTravelSitePage",
        driver.findElement(By.xpath(SHOWMEDEAL_BTN_XPATH))));

    utils.log("landed on compare travel site page");
  }

  /**
   * Clicks on Show Me The First Deal Button.
   */
  public void clickShowMeTheFirstDealBtn() {
    try {
      clickWithJs(showMetheFirstDealButton);
      //showMetheFirstDealButton.click();
      utils.log("show me the first deal on modal clicked");
      waitForPageLoad();
    } catch (Exception e) {
      utils.logError("clickShowMeTheFirstDealBtn failed\n" + e.toString());
    }
  }

  /**
   * Clicks on Show Me The First Deal Button.
   */
  public int numberOfTabs() {
    int count = 0;
    try {
      count = tabs.size();
    } catch (Exception e) {
      utils.logError("getting counts of tabs failed\n" + e.toString());
    }
    return count;
  }

  /**
   * Clicks on Tab given the tab number.
   *
   * @param tabNum tab item to click
   */
  public void clickTab(int tabNum) {
    waitForPageLoad();
    try {
      clickWithJs(
          driver.findElement(
              By.xpath(TAB_BTN_XPATH.replace(
                  "xxx", String.valueOf(tabNum)
                  )
              )
          ));
      utils.log("tab clicked: " + tabNum);
    } catch (NoSuchElementException e) {
      utils.logError(e.toString());
    }
  }

}
