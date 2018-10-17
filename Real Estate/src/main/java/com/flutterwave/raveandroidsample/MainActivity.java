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
                .setfName(MyConstants.FIRST_NAME)
                .setlName(MyConstants.LAST_NAME)
                .setEmail(MyConstants.EMAIL)
                .setNarration("Testing out subscription payment")
                .setPublicKey(MyConstants.STAGING_PUBLIC_KEY)
                .setSecretKey(MyConstants.STAGING_SECRET_KEY)
                .setTxRef("1")
                .acceptCardPayments(true)
                .acceptAccountPayments(false)
                .acceptGHMobileMoneyPayments(false)
                .acceptMpesaPayments(false)
                .onStagingEnv(true)
                .allowSaveCardFeature(true)
                .setSubAccounts(MyConstants.SUB_ACCOUNTS)
                .setPaymentPlan(MyConstants.PAYMENT_PLAN_ID);

    }
}
