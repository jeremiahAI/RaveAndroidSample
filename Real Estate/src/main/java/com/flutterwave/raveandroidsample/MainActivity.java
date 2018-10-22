package com.flutterwave.raveandroidsample;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.flutterwave.raveandroid.RaveConstants;
import com.flutterwave.raveandroid.RavePayActivity;
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
                .setAmount(1)
                .setCountry(MyConstants.COUNTRY)
                .setCurrency(MyConstants.CURRENCY)
                .setfName(MyConstants.FIRST_NAME)
                .setlName(MyConstants.LAST_NAME)
                .setEmail(MyConstants.EMAIL)
                .setNarration("Testing out subscription payment")
                .setPublicKey(MyConstants.STAGING_PUBLIC_KEY)
                .setSecretKey(MyConstants.STAGING_SECRET_KEY)
                .setTxRef("3")
                .acceptCardPayments(true)
                .acceptAccountPayments(false)
                .acceptGHMobileMoneyPayments(false)
                .acceptMpesaPayments(false)
                .onStagingEnv(false)
                .allowSaveCardFeature(true)
                .setSubAccounts(MyConstants.SUB_ACCOUNTS)
                .setPaymentPlan(MyConstants.PAYMENT_PLAN_ID)
                .initialize();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == RaveConstants.RAVE_REQUEST_CODE && data != null) {
            String message = data.getStringExtra("response");
            Log.d("Verify response", message);
            if (resultCode == RavePayActivity.RESULT_SUCCESS) {
                Toast.makeText(this, "SUCCESS " + message, Toast.LENGTH_SHORT).show();
            }
            else if (resultCode == RavePayActivity.RESULT_ERROR) {
                Toast.makeText(this, "ERROR " + message, Toast.LENGTH_SHORT).show();
            }
            else if (resultCode == RavePayActivity.RESULT_CANCELLED) {
                Toast.makeText(this, "CANCELLED " + message, Toast.LENGTH_SHORT).show();
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
