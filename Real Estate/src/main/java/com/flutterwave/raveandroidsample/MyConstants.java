package com.flutterwave.raveandroidsample;

import com.flutterwave.raveandroid.responses.SubAccount;

import java.util.ArrayList;
import java.util.List;

public class MyConstants {
    public static final String COUNTRY = "NG";
    public static final String CURRENCY = "NGN";
    public static final String FIRST_NAME = "Jeremiah";
    public static final String LAST_NAME = "Ifeanyichukwu";
    public static final String EMAIL = "varisiv@gmail.com";
    public static final List<SubAccount> SUB_ACCOUNTS =
            new ArrayList<SubAccount>(){{
                add(new SubAccount(
                        "RS_E6860E55CFE13066B3AE2584DAE58ACB",
                        "3"));
    }};
    //    Todo: Add your API keys here
    public static final String STAGING_PUBLIC_KEY = "";
    public static final String STAGING_SECRET_KEY = "";
    public static final String LIVE_PUBLIC_KEY = "";
    public static final String LIVE_SECRET_KEY = "";


}
