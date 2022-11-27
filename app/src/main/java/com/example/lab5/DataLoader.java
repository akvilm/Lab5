package com.example.lab5;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class DataLoader extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
        try {
            return GetData.getRateFromECB(params[0]);
        } catch (Exception e) {
            return null;
        }
    }
}
