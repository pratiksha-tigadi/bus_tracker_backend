package com.tracker.bus_tracker.service;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class QRCodeService {

    private static final String API_HOST = "getqrcode.p.rapidapi.com";
    private static final String API_KEY ="0300949659mshaad05a8d4cf0135p146d64jsn126ac5a12c8c";

    public CompletableFuture<Map<String, Object>> generateQRCode(String sourceCode, String destinationCode) {
        String uniqueKey = UUID.randomUUID().toString();
        String url = "https://getqrcode.p.rapidapi.com/api/getQR?forQR=" + uniqueKey;
        LocalDateTime generationTime = LocalDateTime.now();

        // Calculate distance between sourceCode and destinationCode
        int distance = Math.abs(destinationCode.charAt(0) - sourceCode.charAt(0));
         int price = distance * 6;
        AsyncHttpClient client = new DefaultAsyncHttpClient();

        return client.prepare("GET", url)
                .setHeader("x-rapidapi-key", API_KEY)
                .setHeader("x-rapidapi-host", API_HOST)
                .execute()
                .toCompletableFuture()
                .thenApply(Response::getResponseBody)
                .thenApply(body -> {
                    try {
                        client.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    Map<String, Object> result = new HashMap<>();
                    result.put("uniqueKey", uniqueKey);
                    result.put("qrCodeImage", body);
                    result.put("sourceCode", sourceCode);
                    result.put("destinationCode", destinationCode);
                    result.put("generationTime", generationTime.toString());
                    result.put("price", price);

                    return result;
                });
    }
}
