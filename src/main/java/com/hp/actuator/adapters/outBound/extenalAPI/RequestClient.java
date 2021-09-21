package com.hp.actuator.adapters.outBound.extenalAPI;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestClient {
    public static int HttpClient() throws IOException {
        URL url = new URL("http://localhost:3001/health");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        con.setDoInput(true);
        return con.getResponseCode();
    }
}
