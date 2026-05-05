import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.util.Scanner;

public class WeatherFetcher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // user input for longitude and latitude
        System.out.print("Enter Longitude: ");
        double lon = scanner.nextDouble();

        System.out.print("Enter Latitude: ");
        double lat = scanner.nextDouble();

        // build the request API URL with user input
        String url = "https://www.7timer.info/bin/astro.php"
            + "?lon=" + lon
            + "&lat=" + lat
            + "&ac=0&unit=metric&output=json";
            
        // create HTTP client and request using the GET method
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        System.out.println("\nFetching weather data for Longitude: " + lon + ", Latitude: " + lat + "...");

        try {
                // send request and get response
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                // check status code of the response and print the body if successful
                if (response.statusCode() == 200) {
                    System.out.println("\nWeather Data (Raw JSON):");
                    System.out.println(response.body());
                } else {
                    System.out.println("Error: Received status code " + response.statusCode());
                }

            } catch (IOException e) {
                System.out.println("Network error occurred: " + e.getMessage());
            } catch (InterruptedException e) {
                System.out.println("Request was interrupted: " + e.getMessage());
            }

            scanner.close();
    }
}
