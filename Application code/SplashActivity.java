package com.example.money.agnikai;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import java.util.logging.LogRecord;

/**
 * Created by money on 5/15/2016.
 */
public class SplashActivity extends AppCompatActivity
{
    public static SharedPreferences pref;
    public static String str_login_test;
    public static SharedPreferences.Editor ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);
        pref= getSharedPreferences("mypref", 0);
        ed=pref.edit();
        //check here if user is login or not
        str_login_test= pref.getString("loginTest" , null);
        if(getIntent().getBooleanExtra("EXIT",false))
        {
            finish();
            return;
        }
         new Handler().postDelayed(new Runnable() {
             @Override
             public void run() {
                 /*
                   If user login test is true on create then redirect the user to profile Login

                  */
                 if(str_login_test!=null && !str_login_test.toString().trim().equals(""))
                 {
                     Intent send= new Intent(getApplicationContext(),ProfileActivity.class);
                     startActivity(send);
                 }
                 else
                 {
                     Intent send=new Intent(getApplicationContext(),LoginActivity.class);
                     startActivity(send);
                 }
             }
         },3000);
    }
}
