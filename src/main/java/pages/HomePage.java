package main.java.pages;

import main.java.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import test.java.utils.PropertyFileUtils;

public class HomePage extends BasePage {

  /**
   * Class Constructor.
   */
  public HomePage(WebDriver driver) {
    super(driver);
    open();
  }

  private final String URL = PropertyFileUtils.getConfig().getProperty("testurl");
  private final String HOTEL_CITYWIDE_TAB_XPATH = ".//a[contains(text(),'Hotel Citywide')]";
  private final String FLIGHTS_TAB_XPATH = ".//a[contains(text(),'Flights')]";


  @FindBy(xpath = HOTEL_CITYWIDE_TAB_XPATH)
  WebElement hotelCityWideTab;

  @FindBy(xpath = FLIGHTS_TAB_XPATH)
  WebElement flightsTab;


  /**
   * opens the home page url.
   */
  public void open() {
    driver.navigate().to(URL);
    utils.log("navigate to url: " + URL);
    waitForPageLoad();
    Assert.assertTrue(verifyPage("home page", hotelCityWideTab));
  }

  /**
   * Clicks on Hotel Citywide Tab.
   *
   * @return HotelCityWide Page Object
   */
  public HotelCityWidePage clickOnHotelCityWideTab() {
    try {
      hotelCityWideTab.click();
      utils.log("hotel city wide tab clicked");
    } catch (NoSuchElementException e) {
      utils.logError(e.toString());
    }
    return new HotelCityWidePage(driver);
  }

  /**
   * Clicks on Flights Tab.
   *
   * @return FlightsPage Object
   */
  public FlightsPage clickOnTravelsTab() {
    try {
      flightsTab.click();
      utils.log("travel tab clicked");
    } catch (NoSuchElementException e) {
      utils.logError(e.toString());
    }
    return new FlightsPage(driver);
  }
}
