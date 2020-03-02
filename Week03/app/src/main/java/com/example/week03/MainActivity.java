package com.example.week03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button launchDetailActivity;
    Spinner pickList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayList<String> coinList = new ArrayList<String>();
        for(int i = 0; i < Coin.getCoins().size(); i++){
            coinList.add(Coin.getCoins().get(i).getName());
        }
        ArrayAdapter<String> coinAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, coinList);

        pickList = findViewById(R.id.pickList);
        pickList.setAdapter(coinAdapter);

        launchDetailActivity = findViewById(R.id.mainButton);
        launchDetailActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("coinName", pickList.getSelectedItem().toString());
                startActivity(intent);
            }
        });
    }
}
