package test.java.utils;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

public class TestUtils {


  /**
   * Appends the log file with the input string.
   *
   * @param text String to add to the log file
   */
  public void log(String text) {
    String date = new SimpleDateFormat("HH.mm.sss").format(new Date());
    Reporter.log(date + "\t" + text, true);
  }

  /**
   * Appends the log file with the input string.
   *
   * @param text String to add to the log file
   */
  public void logError(String text) {
    String date = new SimpleDateFormat("HH.mm.sss").format(new Date());
    Reporter.log(date + "\t" + "<font color='red'>" + text + "</font>", true);

  }


  /**
   * todo.
   */
  public static String getDateFromNextWeek(DayOfWeek val) {
    Date now = new Date();

    LocalDate localDate = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    int year = localDate.getYear();
    int month = localDate.getMonthValue();
    int day = localDate.getDayOfMonth();

    LocalDate ld = LocalDate.of(year, month, day);
    ld = ld.with(TemporalAdjusters.next(val));
    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("MM/d/uuuu");
    String text = ld.format(formatters);
    //Reporter.log(text, true);
    return text;
  }

  /**
   * todo.
   */
  public static String getDateFromGivenDate(String date, DayOfWeek val) {
    String dateArr[] = date.split("/");

    LocalDate ld = LocalDate.of(
        Integer.parseInt(dateArr[2]),
        Integer.parseInt(dateArr[0]),
        Integer.parseInt(dateArr[1])
    );

    ld = ld.with(TemporalAdjusters.next(val));
    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("MM/d/uuuu");
    String text = ld.format(formatters);
    //Reporter.log(text, true);
    return text;
  }

  /**
   * Sleeps for a given amount of time.
   *
   * @param timeInSec Time to pause
   */
  public void pause(int timeInSec) {
    try {
      Thread.sleep(timeInSec * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
