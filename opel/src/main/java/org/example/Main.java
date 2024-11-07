package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {

    private static final String BASE_URL = "https://engine.hotellook.com/api/v2/lookup.json?query=sankt-peterburg&lang=ru&lookFor=both&limit=1";

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try {
            URL url = new URL(BASE_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null){
                response.append(inputLine);
            }
            in.close();
            conn.disconnect();

            JSONObject jsonResponse = new JSONObject(response.toString());

            JSONObject results = jsonResponse.getJSONArray("results").getJSONArray(Integer.parseInt("hotels")).getJSONObject(0);
//            JSONObject wind = jsonResponse.getJSONObject("wind");

            String label = results.getString("label");

            System.out.println("full name " + label + ":");
//
//            System.out.println(response);
        }
        catch (Exception e){
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}