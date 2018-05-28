package test.java.smokeTests;

import main.java.pages.CompareTravelSitePage;
import main.java.pages.HomePage;
import main.java.pages.HotelCityWidePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.java._resources.testdata.TestDataProvider;

import java.util.Map;

public class HotelCityWideTest extends BaseTest {


  @Test(dataProvider = "HotelCitywideDataSet1", dataProviderClass = TestDataProvider.class)
  public void hotelCityWideTest(Map<String, String> dataMap) {
    try {
      //opens home page
      HomePage homePage = new HomePage(driver);

      //clicks on HotelCity Wide tab
      HotelCityWidePage hotelCityWide = homePage.clickOnHotelCityWideTab();

      //navigates to hotel city wide page and enters all the fields
      hotelCityWide
          .enterCity(dataMap.get(TestDataProvider.HotelCityTestDataEnum.CITY.getValue()))
          .setCheckinDate(dataMap.get(TestDataProvider.HotelCityTestDataEnum.CHECK_IN_DATE.getValue()))
          .setCheckoutDate(dataMap.get(TestDataProvider.HotelCityTestDataEnum.CHECK_OUT_DATE.getValue()))
          .setGuestCount(Integer.parseInt(dataMap.get(TestDataProvider.HotelCityTestDataEnum.ROOMS.getValue())))
          .setRoomCount(Integer.parseInt(dataMap.get(TestDataProvider.HotelCityTestDataEnum.GUESTS.getValue())));

      //from hotel city view page clicks on search btn and returns CompareTravelSitePage object
      CompareTravelSitePage compareTravelSite = hotelCityWide.clickSearchHotelBtn();

      //takes screenshots
      attachScreenshotTestNgReport("travelSitePage");

      //clicks on show me the first deal
      compareTravelSite.clickShowMeTheFirstDealBtn();

      //takes screenshots
      attachScreenshotTestNgReport("showMeDealModal");

      //navigates throughs all the open tabs and takes screenshots
      for (int i = 2; i <= compareTravelSite.numberOfTabs(); i++) {
        compareTravelSite.clickTab(i);
        attachScreenshotTestNgReport("tab" + i);
      }

      //logs current url
      utils.log(getCurrentUrl());

      //closes all the new handles/windows
      compareTravelSite.closeHandles();
    } catch (Exception ex) {
      utils.logError(ex.toString());
      Assert.fail("Exception found on hotelCityWideTest");
    }
  }


}
