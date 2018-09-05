package com.example.money.agnikai;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by money on 5/15/2016.
 */
public class ProfileActivity  extends Activity implements View.OnClickListener{
    //public static BroadcastReceiver broadcastReceiver;
    private TextView ty_name;
    private SharedPreferences pref;
    //final SmsManager sms= SmsManager.getDefault();
    Boolean isFabOpen=false;
    private FloatingActionButton fab,fab1,fab2;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ty_name=(TextView)findViewById(R.id.name);
        fab=(FloatingActionButton)findViewById(R.id.fab);
        fab1=(FloatingActionButton)findViewById(R.id.fab1);
        fab2=(FloatingActionButton)findViewById(R.id.fab2);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        rotate_forward= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_forward);
        rotate_backward= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward);
        String str_getName=SplashActivity.pref.getString("name",null);
        ty_name.setText("Welcome: " + str_getName);
        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);

        Intent intent= getIntent();
        if(null!=intent) {
            String Msg = intent.getStringExtra("msg");
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(ProfileActivity.this);
            //Setting Dialog Title
            alertDialog.setTitle("Alert!");
            // Setting Dialog message
            alertDialog.setMessage(Msg);
            //Set icon to dialog
            alertDialog.setIcon(R.drawable.ic_error);
            //Setting Positive Button
            alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "You clicked YES", Toast.LENGTH_LONG).show();
                }
            });
            alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "You clicked NO", Toast.LENGTH_LONG).show();
                    dialog.cancel();
                }
            });
            alertDialog.show();
        }
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id)
        {
            case R.id.fab:
                animateFAB();
                break;
            case R.id.fab1:
                Log.d("User", "fab1");
                break;
            case R.id.fab2:
                Log.d("User","fab2");
                break;
        }
    }

    private void animateFAB() {
        if(isFabOpen)
        {
            fab.startAnimation(rotate_backward);
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            isFabOpen = false;
            Log.d("User","close");

        }
        else
        {
            fab.startAnimation(rotate_forward);
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            isFabOpen = true;
            Log.d("User","open");
        }
    }
}
