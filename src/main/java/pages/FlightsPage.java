package main.java.pages;

import main.java.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class FlightsPage extends BasePage {

  /**
   * Class Constructor.
   */
  public FlightsPage(WebDriver driver) {
    super(driver);
    waitForPageLoad();
    Assert.assertTrue(verifyPage("HotelCityWidePage", pageHeader));
    utils.log("landed on hotel city wide page");
  }

  private final String HEADER_TEXT = ".//h1[contains(text(),'Flights Test Form')]";
  private final String FLIGHT_FROM_INPUT_ID = "city1";
  private final String FLIGHT_TO_INPUT_ID = "city2";
  private final String DEPARTING_DATE_ID = "date1";
  private final String RETURNING_DATE_ID = "date2";
  private final String TRAVELERS_DD_ID = "travelers";
  private final String SEARCH_BTN_XPATH = ".//*[@id='search-button']";


  @FindBy(xpath = HEADER_TEXT)
  WebElement pageHeader;

  @FindBy(id = FLIGHT_FROM_INPUT_ID)
  WebElement flightFrom;

  @FindBy(id = FLIGHT_TO_INPUT_ID)
  WebElement flightTo;

  @FindBy(id = DEPARTING_DATE_ID)
  WebElement departingDate;

  @FindBy(id = RETURNING_DATE_ID)
  WebElement returningDate;

  @FindBy(id = TRAVELERS_DD_ID)
  WebElement travelersCount;

  @FindBy(xpath = SEARCH_BTN_XPATH)
  WebElement searchButton;


  /**
   * enters Flight From City Code.
   *
   * @param city tab item to click
   */
  public FlightsPage enterFlightFrom(String city) {
    try {
      flightFrom.clear();
      flightFrom.sendKeys(city);
      utils.log("flight from entered: " + city);
    } catch (NoSuchElementException e) {
      utils.logError(e.toString());
    }
    return this;
  }

  /**
   * enters Flight to City Code.
   *
   * @param city tab item to click
   */
  public FlightsPage enterFlightTo(String city) {
    try {
      flightTo.clear();
      flightTo.sendKeys(city);
      utils.log("flight to entered: " + city);
    } catch (NoSuchElementException e) {
      utils.logError(e.toString());
    }
    return this;
  }

  /**
   * Enters flight departing date.
   *
   * @param date tab item to click
   */
  public FlightsPage departingDate(String date) {
    try {
      departingDate.clear();
      departingDate.sendKeys(date);
      utils.log("departing data entered: " + date);
      pageHeader.click();
    } catch (NoSuchElementException e) {
      utils.logError(e.toString());
    }
    return this;
  }

  /**
   * Enters returning date.
   *
   * @param date tab item to click
   */
  public FlightsPage setReturningDate(String date) {
    try {
      returningDate.clear();
      returningDate.sendKeys(date);
      utils.log("returning data entered: " + date);
      pageHeader.click();
    } catch (NoSuchElementException e) {
      utils.logError(e.toString());
    }
    return this;
  }

  /**
   * selects number of travelers from dropdown.
   *
   * @param count tab item to click
   */
  public FlightsPage setTravelersCount(int count) {
    try {
      Select dropdown = new Select(travelersCount);
      dropdown.selectByIndex(handlingDropDownSelection(count - 1));
      utils.log("travelers drop down selected: " + count);
      pageHeader.click();
    } catch (
        NoSuchElementException e)

    {
      utils.logError(e.toString());
    }
    return this;
  }


  /**
   * Clicks on search button.
   */
  public CompareTravelSitePage clickSearchBtn() {
    try {
      waitForPageLoad();
      clickWithJs(searchButton);
      utils.log("search flights button clicked");
      switchToNewHandle();
      utils.log("switched to new handle");
    } catch (NoSuchElementException e) {
      utils.logError(e.toString());
    }
    return new CompareTravelSitePage(driver);
  }

  /**
   * helper method for drop down selection.
   *
   * @param count tab item to click
   */
  private int handlingDropDownSelection(int count) {
    if (count > 10) {
      count = 10;
      utils.logError("drop down selection can't be greater than 10");
    }
    return count;
  }


}
