package application.API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Translator {

    public static String enToVi(String content) {
        try {
            return translate("en", "vi", content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Error: translating";
    }

    public static String viToEn(String content) {
        try {
            return translate("vi", "en", content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Error: translating";
    }
    /**
     * Translate text from `langSource` to `langDes`.
     * @param langSource input language
     * @param langDes    output language
     * @param content    the text to be translated
     * @return           the translation text in `langDes`
     */
    public static String translate(String langSource, String langDes, String content)
            throws IOException {
        String urlStr =
                "https://script.google.com/macros/s/AKfycby3AOWmhe32TgV9nW-Q0TyGOEyCHQeFiIn7hRgy5m8jHPaXDl2GdToyW_3Ys5MTbK6wjg/exec"
                        + "?q=" + URLEncoder.encode(content, StandardCharsets.UTF_8)
                        + "&target=" + langDes
                        + "&source=" + langSource;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in =
                new BufferedReader(
                        new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}
