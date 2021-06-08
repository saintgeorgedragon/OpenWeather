import weather.WeatherForecast;

import javax.swing.*;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class OpenWeather {

   public static void main(String[] args) throws ParseException {
      WeatherForecast forecast = new WeatherForecast();
      Integer numberOfDaysAtTemp = forecast.getNumberOfDaysAtTemp(5, 20.0);
      Integer numberOfSunnyDays = forecast.getNumberOfSunnyDays(5);

      //Show the number of days above 20 degrees
      JFrame daysAtTempFrame = new JFrame("JOptionPane showMessageDialog example");
      JOptionPane.showMessageDialog(daysAtTempFrame, "Number of days over 20 degrees for the next 5 days is : "
         + numberOfDaysAtTemp);

      //Show the number of sunny days
      JFrame sunnyDaysFrame = new JFrame("JOptionPane showMessageDialog example");
      JOptionPane.showMessageDialog(sunnyDaysFrame, "Number of sunny days for the next 5 days is : "
         + numberOfSunnyDays);

      //Used for getting hourly weather updates
      LocalTime localTime = LocalTime.now();

      //Ask user if they want to get hourly updates
      int res = JOptionPane.showConfirmDialog(null, "Continue getting hourly forecasts", "",
         JOptionPane.YES_NO_OPTION);

      //Exit system on no option
      if (res == 1) {
         System.exit(0);
      }

      //Continue to give hourly updates
      while (res == 0) {
         LocalTime localTimeLater = LocalTime.now();
         long minutesBetween = ChronoUnit.MINUTES.between(localTime, localTimeLater);

         //Check if its been an hour since last update - make this less for testing purposes eg 1 minute
         if(minutesBetween > 1) {
            numberOfDaysAtTemp = forecast.getNumberOfDaysAtTemp(5, 20.0);
            numberOfSunnyDays = forecast.getNumberOfSunnyDays(5);

            //Show the number of days above 20 degrees
            JOptionPane.showMessageDialog(daysAtTempFrame, "Number of days over 20 degrees for the next 5 days is : "
               + numberOfDaysAtTemp);

            //Show the number of sunny days
            JOptionPane.showMessageDialog(sunnyDaysFrame, "Number of sunny days for the next 5 days is : "
               + numberOfSunnyDays);

            //Ask user if they want to get hourly updates
            res = JOptionPane.showConfirmDialog(null, "Continue getting hourly forecasts", "",
               JOptionPane.YES_NO_OPTION);

            localTime = LocalTime.now();
         }

      }

      System.exit(0);

   }

}
