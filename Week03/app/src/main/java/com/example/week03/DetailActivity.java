package com.example.week03;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class DetailActivity extends AppCompatActivity {

    TextView coinName;
    TextView coinSymbol;
    TextView coinValue;
    TextView coinChange1h;
    TextView coinChange24h;
    TextView coinChange7d;
    TextView coinMarketcap;
    TextView coinVolume;
    ImageView searchCoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String getSelectedCoin = intent.getStringExtra("coinName");

        final Coin coinSelected = Coin.searchCoin(getSelectedCoin);

        setTitle(coinSelected.getName());

        coinName = findViewById(R.id.coinName);
        coinSymbol = findViewById(R.id.coinSymbol);
        coinValue = findViewById(R.id.coinValue);
        coinChange1h = findViewById(R.id.coinChange1h);
        coinChange24h = findViewById(R.id.coinChange24h);
        coinChange7d = findViewById(R.id.coinChange7d);
        coinMarketcap = findViewById(R.id.coinMarketcap);
        coinVolume = findViewById(R.id.coinVolume);

        DecimalFormatSymbols syms = new DecimalFormatSymbols();
        syms.setDecimalSeparator('.');
        syms.setGroupingSeparator(',');
        DecimalFormat myFormatter = new DecimalFormat("###,###.00", syms);

        coinName.setText(coinSelected.getName());
        coinSymbol.setText(coinSelected.getSymbol());
        coinValue.setText("$" + (String.valueOf(myFormatter.format(coinSelected.getValue()))));
        coinChange1h.setText(String.valueOf(coinSelected.getChange1h()) + " %");
        coinChange24h.setText(String.valueOf(coinSelected.getChange24h()) + " %");
        coinChange7d.setText(String.valueOf(coinSelected.getChange7d()) + " %");
        coinMarketcap.setText("$" + String.valueOf(myFormatter.format(coinSelected.getMarketcap())));
        coinVolume.setText("$" + String.valueOf(myFormatter.format(coinSelected.getVolume())));

        searchCoin = findViewById(R.id.searchCoin);
        searchCoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchUrl = "https://www.google.com/#q=" + coinSelected.getName();
                Uri uri = Uri.parse(searchUrl);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                v.getContext().startActivity(intent);
            }
        });
    }
}
