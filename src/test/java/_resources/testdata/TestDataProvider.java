package test.java._resources.testdata;

import org.testng.annotations.DataProvider;
import test.java.utils.PropertyFileUtils;

import java.time.DayOfWeek;
import java.util.*;

import test.java.utils.TestUtils;

/**
 * This is test data provider class that generates test data
 */
public class TestDataProvider {

  /**
   * create test data from hotel city wide page
   *
   * @return key value pair of test data
   */
  @DataProvider(name = "HotelCitywideDataSet1")
  public Object[][] hotelCityWideData() {

    Properties prop = PropertyFileUtils.getHotelCityViewTestData();

    Map<String, String> propertiesMap = new HashMap<>();

    propertiesMap.put(HotelCityTestDataEnum.CITY.getValue(),
        prop.getProperty(HotelCityTestDataEnum.CITY.getValue()));

    String nextFridayDate = TestUtils.getDateFromNextWeek(DayOfWeek.FRIDAY);
    propertiesMap.put(HotelCityTestDataEnum.CHECK_IN_DATE.getValue(), nextFridayDate);

    propertiesMap.put(HotelCityTestDataEnum.CHECK_OUT_DATE.getValue(),
        TestUtils.getDateFromGivenDate(nextFridayDate, DayOfWeek.SUNDAY));

    propertiesMap.put(HotelCityTestDataEnum.GUESTS.getValue(),
        prop.getProperty(HotelCityTestDataEnum.GUESTS.getValue()));

    propertiesMap.put(HotelCityTestDataEnum.ROOMS.getValue(),
        prop.getProperty(HotelCityTestDataEnum.ROOMS.getValue()));


    return new Object[][]{{propertiesMap}};
  }

  public enum HotelCityTestDataEnum {

    CITY("city"),
    CHECK_IN_DATE("CheckInDate"),
    CHECK_OUT_DATE("CheckOutDate"),
    GUESTS("guests"),
    ROOMS("rooms");

    private String name;

    HotelCityTestDataEnum(String name) {
      this.name = name;
    }

    public String getValue() {
      return name;
    }

  }

  /**
   * create test data from flights page
   *
   * @return key value pair of test data
   */
  @DataProvider(name = "flightsDataSet1")
  public Object[][] flightsData() {

    Properties prop = PropertyFileUtils.getFlightsTestData();

    Map<String, String> propertiesMap = new HashMap<>();

    propertiesMap.put(FlightsTestDataEnum.FLIGHT_FROM.getValue(),
        prop.getProperty(FlightsTestDataEnum.FLIGHT_FROM.getValue()));

    propertiesMap.put(FlightsTestDataEnum.FLIGHT_TO.getValue(),
        prop.getProperty(FlightsTestDataEnum.FLIGHT_TO.getValue()));

    String nextFridayDate = TestUtils.getDateFromNextWeek(DayOfWeek.FRIDAY);
    propertiesMap.put(FlightsTestDataEnum.DEPARTURE_DATE.getValue(), nextFridayDate);

    propertiesMap.put(FlightsTestDataEnum.RETURNING_DATE.getValue(),
        TestUtils.getDateFromGivenDate(nextFridayDate, DayOfWeek.SUNDAY));

    propertiesMap.put(FlightsTestDataEnum.NUM_TRAVELERS.getValue(),
        prop.getProperty(FlightsTestDataEnum.NUM_TRAVELERS.getValue()));


    return new Object[][]{{propertiesMap}};
  }

  public enum FlightsTestDataEnum {

    FLIGHT_FROM("FlightFrom"),
    FLIGHT_TO("FlightTo"),
    DEPARTURE_DATE("DepartureDate"),
    RETURNING_DATE("ReturningDate"),
    NUM_TRAVELERS("TravelersNum");

    private String name;

    FlightsTestDataEnum(String name) {
      this.name = name;
    }

    public String getValue() {
      return name;
    }

  }
}
