package weather;

import apiCalls.WebAPI;
import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherForecast {

   private final WebAPI webAPI;
   private JSONObject weather;

   public WeatherForecast() {
      this.webAPI = new WebAPI();
   }

   //Get number of days above temperature
   public Integer getNumberOfDaysAtTemp(int days, Double temp) {
      try {
         Integer numberOfDays = 0;

         //Get forecast
         this.getForecast(days);
         JSONArray weatherForecast = this.weather.getJSONArray("list");

         if (this.weather != null) {

            //Look for days above the requested temperature
            for (int i = 0; i < weatherForecast.length(); i++) {
               JSONObject jsonObject = (JSONObject) weatherForecast.get(i);
               JSONObject main = (JSONObject) jsonObject.get("main");
               double maxTemp = Double.parseDouble(main.get("temp_max").toString());

               //Increment the number of days above the requested temperature
               if (maxTemp > temp) {
                  numberOfDays++;
               }

            }

         }

         //Return number of days above the requested temperature
         return numberOfDays;

      } catch (Exception e) {
         e.printStackTrace();
         return null;
      }
   }

   //Get number of sunny days
   public Integer getNumberOfSunnyDays(int days) {
      try {
         boolean clearSky;
         Integer numberOfSunnyDays = 0;

         //Get forecast
         this.getForecast(days);
         JSONArray weatherForecast = this.weather.getJSONArray("list");

         if (this.weather != null) {

            for (int i = 0; i < weatherForecast.length(); i++) {
               clearSky = false;
               JSONObject jsonObject = (JSONObject) weatherForecast.get(i);
               JSONArray weather = (JSONArray) jsonObject.get("weather");

               //Look for clear skies to get number of sunny days
               for (int j = 0; j < weather.length(); j++) {
                  JSONObject weatherObject = (JSONObject) weather.get(j);
                  String description = (String) weatherObject.get("description");
                  clearSky = description.compareTo("clear sky") == 0;
               }

               //Increment the number of sunny days
               if(clearSky){
                  numberOfSunnyDays++;
               }

            }

         }

         //Return the number of sunny days
         return numberOfSunnyDays;

      } catch (Exception e) {
         e.printStackTrace();
         return null;
      }
   }

   //Call the open weather map api to get the weather forecast for the given number of days
   private void getForecast(int days) {
      this.weather = webAPI.getCurrentWeather(days);
   }

}
