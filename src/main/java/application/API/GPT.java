package application.API;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class GPT {

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String API_KEY = "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";
    private static final String MODEL = "gpt-3.5-turbo";
    public static String chatGPT(String message) {
        try {
            HttpURLConnection connection = createConnection();
            sendRequest(connection, message);
            return getResponse(connection);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static HttpURLConnection createConnection() throws IOException {
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        return connection;
    }

    private static void sendRequest(HttpURLConnection connection, String message) throws IOException {
        String requestBody = String.format("{\"model\": \"%s\", \"messages\": [{\"role\": \"user\", \"content\": \"%s\"}]}", MODEL, message);
        try (OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream())) {
            writer.write(requestBody);
            writer.flush();
        }
    }

    private static String getResponse(HttpURLConnection connection) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return extractContentFromResponse(response.toString());
        }
    }

    private static String extractContentFromResponse(String response) {
        int startMarker = response.indexOf("content") + 11;
        int endMarker = response.indexOf("\"", startMarker);
        return response.substring(startMarker, endMarker);
    }
}
