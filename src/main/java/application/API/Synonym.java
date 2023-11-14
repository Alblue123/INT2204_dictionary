package application.API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.apache.commons.text.StringEscapeUtils;
import org.json.JSONObject;

public class Synonym {
    private static final String key = "aca2c0c9a3mshdae9b0fd091fb0dp1923ffjsn84863605816e";
    public static JSONObject getSynonym(String wordForm) {
        try {
            // Construct the URL
            String urlString = "https://languagetools.p.rapidapi.com/all/"
                    + URLEncoder.encode(wordForm, StandardCharsets.UTF_8)
                    .replace("+", "%20");
            URL url = new URL(urlString);

            // Open a connection
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();

            // Set the request properties
            connect.setRequestMethod("GET");
            connect.setRequestProperty("x-rapidapi-key", key);

            // Get the response
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(connect.getInputStream()));
            String output;
            StringBuilder response = new StringBuilder();
            while ((output = input.readLine()) != null) {
                response.append(output);
            }
            input.close();

            // Convert the response to a JSONObject and return it
            return new JSONObject(StringEscapeUtils.unescapeHtml4(response.toString()));
        } catch (IOException e) {
            // If there's an error, return an empty JSONObject
            return new JSONObject("{\"synonyms\":[],\"antonyms\":[]}");
        }
    }
}
