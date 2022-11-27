package com.example.lab5;

import android.os.StrictMode;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetData {
    private static InputStream downloadUrl(String urlString) throws IOException {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        return conn.getInputStream();
    }

    public static String getRateFromECB(String currencyCode) throws IOException {
        String rate = "Data were not retrieved";
        InputStream stream = downloadUrl("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");
        try {
            rate = Parser.getRateFromECB(stream, currencyCode);
        }
        finally {
            if (stream != null) {
                stream.close();
            }
        }
        return rate;
    }
}
