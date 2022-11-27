package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner currencyList;
    TextView tvRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currencyList = findViewById(R.id.spnrCurrency);
        tvRate = findViewById(R.id.txtViewResult);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.spinnerItems, R.layout.spinner_layout);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        currencyList.setAdapter(adapter);
        currencyList.setOnItemSelectedListener(this);
    }

    public void onBtnDownloadClick(View view) throws IOException {
        String selectedCurrency = String.valueOf(currencyList.getSelectedItem());
        new DataLoader() {
            public void onPostExecute(String result)
            {
                tvRate.setText(result);
            }
        }.execute(selectedCurrency);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}