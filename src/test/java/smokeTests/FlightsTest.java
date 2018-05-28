package test.java.smokeTests;

import main.java.pages.CompareTravelSitePage;
import main.java.pages.FlightsPage;
import main.java.pages.HomePage;
import main.java.pages.HotelCityWidePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.java._resources.testdata.TestDataProvider;

import java.util.Map;

public class FlightsTest extends BaseTest {


  @Test(dataProvider = "flightsDataSet1", dataProviderClass = TestDataProvider.class)
  public void flightsTest(Map<String, String> dataMap) {
    try {
      //opens home page
      HomePage homePage = new HomePage(driver);

      //clicks on travels tab
      FlightsPage flightsPage = homePage.clickOnTravelsTab();

      //navigates to flights page and enters all the fields
      flightsPage
          .enterFlightFrom(dataMap.get(TestDataProvider.FlightsTestDataEnum.FLIGHT_FROM.getValue()))
          .enterFlightTo(dataMap.get(TestDataProvider.FlightsTestDataEnum.FLIGHT_TO.getValue()))
          .departingDate(dataMap.get(TestDataProvider.FlightsTestDataEnum.DEPARTURE_DATE.getValue()))
          .setReturningDate(dataMap.get(TestDataProvider.FlightsTestDataEnum.RETURNING_DATE.getValue()))
          .setTravelersCount(Integer.parseInt(
              dataMap.get(TestDataProvider.FlightsTestDataEnum.NUM_TRAVELERS.getValue())
          ));

      //from flights page clicks on search btn and returns CompareTravelSitePage object
      CompareTravelSitePage compareTravelSite = flightsPage.clickSearchBtn();

      //takes screenshot
      attachScreenshotTestNgReport("travelSitePageForFlights");
      compareTravelSite.clickShowMeTheFirstDealBtn();
      attachScreenshotTestNgReport("showMeDealModalForFlights");

      //clicks through the number of tabs and takes screenshots of the page
      for (int i = 2; i <= compareTravelSite.numberOfTabs(); i++) {
        compareTravelSite.clickTab(i);
        attachScreenshotTestNgReport("tab" + i);
      }

      //logs current url
      utils.log(getCurrentUrl());

      //closes all open window handles
      compareTravelSite.closeHandles();
    } catch (Exception ex) {
      utils.logError(ex.toString());
      Assert.fail("Exception found on hotelCityWideTest");
    }
  }


}
