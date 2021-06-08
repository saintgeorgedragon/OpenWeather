package apiCalls;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import org.json.JSONObject;

public class WebAPI {
   private static final String SERVER = "http://api.openweathermap.org";
   private static final String accessCode = "dbfe8eea1d71b65fd146ea8399bc1801";
   private static final String ABS_URL_API_BASE = String.format("%s/data/2.5/forecast?", SERVER);

   private static final HttpClient httpClient = HttpClient.newBuilder()
      .version(HttpClient.Version.HTTP_2)
      .connectTimeout(Duration.ofSeconds(10))
      .build();

   public WebAPI() {
   }

   //Call the open weather map api to get the weather forecast for the given number of days
   public JSONObject getCurrentWeather(int days) {
      try {

         //Build the api request
         HttpRequest request = this.requestBuilder(
            HttpMethod.GET,
            ABS_URL_API_BASE + "q=" + "Sydney" + ",AUS" + "&APPID="
               + accessCode + "&cnt=" + days + "&units=metric"
         );

         if (request != null) {

            //Send the request
            HttpResponse<String> response = httpClient.send(
               request, HttpResponse.BodyHandlers.ofString()
            );
            String code = response.toString().substring(response.toString().length() - 3);

            //return null if on failure
            if (code.compareTo("200") != 0) {
               return null;
            }

            //Return json response
            return new JSONObject(response.body());
         }

         return null;

      } catch (Exception e) {
         e.printStackTrace();
         return null;
      }

   }

   //Build api request - will be able to handle other requests than get in the future
   private HttpRequest requestBuilder(
      HttpMethod method,
      String url) {
      HttpRequest request;

      if (method == HttpMethod.GET) {
         request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();
         return request;
      }

      return null;
   }


   enum HttpMethod {
      GET
   }


}
