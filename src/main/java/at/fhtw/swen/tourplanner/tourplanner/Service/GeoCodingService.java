package at.fhtw.swen.tourplanner.tourplanner.Service;

import at.fhtw.swen.tourplanner.tourplanner.model.Location;
import at.fhtw.swen.tourplanner.tourplanner.model.Route;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


@Component
public class GeoCodingService {
    private static final String API_KEY = "5b3ce3597851110001cf6248753c1bf1ab5d4862bd03e95b4b43434f";
    private static final String GEOCODE_URL = "https://api.openrouteservice.org/geocode/search";
    private static final String DIRECTIONS_URL = "https://api.openrouteservice.org/v2/directions/";

    HttpClient client = HttpClient.newHttpClient();

    public GeoCodingService() {}

    private String createDirectionsUrl(String transportType){
        return DIRECTIONS_URL + transportType /*+ "/geojson"*/;
    }

    public List<Location> searchPlace(String place) throws Exception{

        String encodedQuery = URLEncoder.encode(place, "UTF-8");
        String requestUrl = GEOCODE_URL + "?api_key=" + API_KEY + "&text=" + encodedQuery;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(requestUrl))
                .header("Accept", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject json = new JSONObject(response.body());

        List<Location> suggestions = new ArrayList<>();
        JSONArray features =  json.getJSONArray("features");
        for (int i = 0; i < features.length(); i++) {
            JSONObject feature = features.getJSONObject(i);
            String label = feature.getJSONObject("properties").getString("label");
            JSONArray coords = feature.getJSONObject("geometry").getJSONArray("coordinates");
            String lon = String.valueOf(coords.getDouble(0));
            String lat = String.valueOf(coords.getDouble(1));
            suggestions.add(new Location(label, lon, lat));
        }
        return suggestions;
    }

    public Route getRoute(Location start, Location end, String transportType) throws Exception{
        String body = """
                {
                  "coordinates": [[%s,%s],[%s,%s]]
                }
                """.formatted(start.getLon(), start.getLat(), end.getLon(), end.getLat());

        String url3 = "https://api.openrouteservice.org/v2/directions/" + transportType + "/geojson";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url3))
                .header("Authorization", API_KEY)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();

        JSONObject json = new JSONObject(responseBody);
        JSONObject summary = json.getJSONArray("features")
                .getJSONObject(0)
                .getJSONObject("properties")
                .getJSONObject("summary");

        double distance = summary.getDouble("distance");
        double duration = summary.getDouble("duration");
        return new Route(distance, duration, responseBody);
    }


}
