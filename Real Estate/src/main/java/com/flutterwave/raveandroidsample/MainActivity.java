package com.flutterwave.raveandroidsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.flutterwave.raveandroid.RavePayManager;

public class MainActivity extends AppCompatActivity {
    Button payButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        payButton = findViewById(R.id.pay);
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pay();
            }
        });

    }

    private void pay() {
        RavePayManager ravePayManager = new RavePayManager(MainActivity.this);
        ravePayManager
                .setAmount(500000)
                .setCountry(MyConstants.COUNTRY)
                .setCurrency(MyConstants.CURRENCY)
                .setfName(MyConstants.FIRSTNAME)
                .setlName(MyConstants.LASTNAME)
                .setEmail(MyConstants.EMAIL)
                .setNarration("Testing out subscription payment");
    }
}
