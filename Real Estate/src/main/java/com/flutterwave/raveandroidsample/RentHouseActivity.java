package com.flutterwave.raveandroidsample;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.flutterwave.raveandroid.RaveConstants;
import com.flutterwave.raveandroid.RavePayActivity;
import com.flutterwave.raveandroid.RavePayManager;

public class RentHouseActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button payButton;
    Boolean addedToFavorites = false;
    FloatingActionButton favButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_house);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        favButton = findViewById(R.id.wishlist_button);
        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFavState();
            }
        });

        payButton = findViewById(R.id.pay_button);
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pay();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.wishlist) {
            toggleFavState();
        }
        return super.onOptionsItemSelected(item);
    }

    private void toggleFavState() {
        if (addedToFavorites) {
            removeFromFavorites();
        } else addToFavorites();
    }

    private void addToFavorites() {
        Toast.makeText(this, "Added to wishlist", Toast.LENGTH_SHORT).show();
        addedToFavorites = true;
        favButton.setImageResource(R.drawable.ic_favorite_32dp);
    }


    private void removeFromFavorites() {
        Toast.makeText(this, "Removed from wishlist", Toast.LENGTH_SHORT).show();
        addedToFavorites = false;
        favButton.setImageResource(R.drawable.ic_favorite_border_32dp);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (addedToFavorites) {
            menu.findItem(R.id.wishlist).setTitle("Remove from wishlist");
        } else menu.findItem(R.id.wishlist).setTitle("Add to wishlist");
        return super.onPrepareOptionsMenu(menu);
    }


    private void pay() {
        RavePayManager ravePayManager = new RavePayManager(RentHouseActivity.this);
        ravePayManager
                .setAmount(300000)
                .setCountry(MyConstants.COUNTRY)
                .setCurrency(MyConstants.CURRENCY)
                .setfName(MyConstants.FIRST_NAME)
                .setlName(MyConstants.LAST_NAME)
                .setEmail(MyConstants.EMAIL)
                .setNarration("Testing out subscription payment")
                .setPublicKey(BuildConfig.STAGING_PUBLIC_KEY)
                .setSecretKey(BuildConfig.STAGING_SECRET_KEY)
                .setTxRef("3")
                .acceptCardPayments(true)
                .acceptAccountPayments(false)
                .acceptGHMobileMoneyPayments(false)
                .acceptMpesaPayments(false)
                .onStagingEnv(true)
                .allowSaveCardFeature(true)
                .setSubAccounts(MyConstants.SUB_ACCOUNTS)
                .setPaymentPlan(MyConstants.PAYMENT_PLAN_ID)
                .initialize();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == RaveConstants.RAVE_REQUEST_CODE && data != null) {
            String message = data.getStringExtra("response");
            Log.d("Verify response", "" + message);
            if (resultCode == RavePayActivity.RESULT_SUCCESS) {
                Toast.makeText(this, "Payment successful", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RavePayActivity.RESULT_ERROR) {
                Toast.makeText(this, "There was an error processing your payment. Please try " +
                        "again.", Toast
                        .LENGTH_SHORT).show();
            } else if (resultCode == RavePayActivity.RESULT_CANCELLED) {
                Toast.makeText(this, "Payment cancelled ", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}
