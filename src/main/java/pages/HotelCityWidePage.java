package main.java.pages;

import main.java.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class HotelCityWidePage extends BasePage {

  /**
   * Class Constructor.
   */
  public HotelCityWidePage(WebDriver driver) {
    super(driver);
    waitForPageLoad();
    Assert.assertTrue(verifyPage("HotelCityWidePage", pageHeader));
    utils.log("landed on hotel city wide page");
  }

  private final String HEADER_TEXT = ".//h1[contains(text(),'Hotel Citywide Test Form')]";
  private final String CITY_TEXTFIELD_ID = "city";
  private final String CHECKIN_DATE_ID = "date1";
  private final String CHECKOUT_DATE_ID = "date2";
  private final String GUEST_DD_ID = "guests";
  private final String ROOM_DD_ID = "rooms";
  private final String SEARCH_BTN_XPATH = ".//*[@id='search-button']";


  @FindBy(xpath = HEADER_TEXT)
  WebElement pageHeader;

  @FindBy(id = CITY_TEXTFIELD_ID)
  WebElement cityInputField;

  @FindBy(id = CHECKIN_DATE_ID)
  WebElement checkinDatePicker;

  @FindBy(id = CHECKOUT_DATE_ID)
  WebElement checkoutDatePicker;

  @FindBy(id = GUEST_DD_ID)
  WebElement guestDropDown;

  @FindBy(id = ROOM_DD_ID)
  WebElement roomDropDown;

  @FindBy(xpath = SEARCH_BTN_XPATH)
  WebElement searchHotelsButton;


  /**
   * enters City in the text field on Hotel CityWide page.
   *
   * @param city name to be entereed
   */
  public HotelCityWidePage enterCity(String city) {
    try {
      cityInputField.clear();
      cityInputField.sendKeys(city);
      utils.log("city data entered: " + city);
    } catch (NoSuchElementException e) {
      utils.logError(e.toString());
    }
    return this;
  }

  /**
   * enters CheckIn Date.
   *
   * @param checkinDate date to check in
   * @return HotelCityWidePage object
   */
  public HotelCityWidePage setCheckinDate(String checkinDate) {
    try {
      checkinDatePicker.clear();
      checkinDatePicker.sendKeys(checkinDate);
      utils.log("check in date entered: " + checkinDate);
      pageHeader.click();
    } catch (NoSuchElementException e) {
      utils.logError(e.toString());
    }
    return this;
  }

  /**
   * enters CheckOut Date.
   *
   * @param checkoutDate date to check Out
   * @return HotelCityWidePage object
   */
  public HotelCityWidePage setCheckoutDate(String checkoutDate) {
    try {
      checkoutDatePicker.clear();
      checkoutDatePicker.sendKeys(checkoutDate);
      utils.log("check out data entered: " + checkoutDate);
      pageHeader.click();
    } catch (NoSuchElementException e) {
      utils.logError(e.toString());
    }
    return this;
  }

  /**
   * selects number of guests from dropdown.
   * maximum allowed guests is 9
   *
   * @param count tab item to click
   * @return HotelCityWidePage object
   */
  public HotelCityWidePage setGuestCount(int count) {
    try {
      Select dropdown = new Select(guestDropDown);
      dropdown.selectByIndex(handlingDropDownSelection(count - 1));
      utils.log("guest drop down selected: " + count);
      pageHeader.click();
    } catch (
        NoSuchElementException e)

    {
      utils.logError(e.toString());
    }
    return this;
  }

  /**
   * selects number of rooms from dropdown.
   * maximum allowed rooms is 9
   *
   * @param count tab item to click
   * @return HotelCityWidePage object
   */
  public HotelCityWidePage setRoomCount(int count) {
    try {
      Select dropdown = new Select(roomDropDown);
      handlingDropDownSelection(count);
      utils.log("number of room selected: " + count);
      dropdown.selectByIndex(handlingDropDownSelection(count - 1));
      pageHeader.click();
    } catch (NoSuchElementException e) {
      utils.logError(e.toString());
    }
    return this;
  }

  /**
   * clicks on search hotel btn and
   * navigates to Compare Travel Site page.
   *
   * @return CompareTravelSitePage object
   */
  public CompareTravelSitePage clickSearchHotelBtn() {
    try {
      waitForPageLoad();
      clickWithJs(searchHotelsButton);
      utils.log("search hotel button clicked");
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
    if (count > 9) {
      count = 9;
      utils.logError("drop down selectin can't be greater than 9");
    }
    return count;
  }


}
