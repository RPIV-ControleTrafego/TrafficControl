package com.infraction.serviceinfraction.service.calculator;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
@Service
public class CurrencyConverterAdapter {
    private static final String API_KEY = "qOpahvu71xPZOBX1";
    private static final String API_SECRET = "pgXMITMRfnK7vBdPUdI3VU605yMq0jx7";
    private static final String BASE_URL = "https://api.coinbase.com/v2/";
    public Double convertCurrency(Double amount, String fromCurrency, String toCurrency) {
        try {
            String path = String.format("prices/%s-%s/spot", fromCurrency, toCurrency);
            String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
            String message = timestamp + "GET" + path;
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(API_SECRET.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] hmacBytes = sha256_HMAC.doFinal(message.getBytes(StandardCharsets.UTF_8));
            String signature = bytesToHex(hmacBytes);
            String urlString = BASE_URL + path;
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("CB-ACCESS-KEY", API_KEY);
            conn.setRequestProperty("CB-ACCESS-SIGN", signature);
            conn.setRequestProperty("CB-ACCESS-TIMESTAMP", timestamp);
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            // Parse the response to get the exchange rate
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONObject data = jsonResponse.getJSONObject("data");
            String amountStr = data.getString("amount");
            Double exchangeRate = Double.parseDouble(amountStr);
            return amount * exchangeRate;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte aByte : bytes) {
            result.append(String.format("%02X", aByte));
        }
        return result.toString();
    }
}