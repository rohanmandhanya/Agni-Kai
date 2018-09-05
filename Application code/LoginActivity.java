package com.example.money.agnikai;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by money on 5/15/2016.
 */
public class LoginActivity extends Activity implements View.OnClickListener {
    String str_username,str_password,get_Id,get_password;
    EditText et_username,et_password;
    Button btn_login;
    TextView link_reg;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.material_design_login_form);
        get_Id=SplashActivity.pref.getString("username",null);
        get_password=SplashActivity.pref.getString("password",null);
        et_username=(EditText)findViewById(R.id.uname);
        et_password=(EditText)findViewById(R.id.password);
        btn_login=(Button)findViewById(R.id.btn_login);
        link_reg=(TextView)findViewById(R.id.reg_link);
        progress=(ProgressBar)findViewById(R.id.progress_bar);
        link_reg.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      switch(v.getId())
      {
          case R.id.btn_login:
              str_username=et_username.getText().toString();
              str_password=et_password.getText().toString();
              /*if(!str_username.isEmpty() && !str_password.isEmpty())
              {
                  //progress.setVisibility(View.VISIBLE);
                  loginProcess(str_username, str_password);
              }*/
              if(str_username.length()==0 && str_password.length()==0)
              {
                  Snackbar.make(getCurrentFocus(),"Please enter your username and password",Snackbar.LENGTH_LONG).show();
              }
              else if (str_username.length() == 0)
              {
                  Snackbar.make(getCurrentFocus(), "Please enter your username", Snackbar.LENGTH_LONG).show();
              }
              else if (str_password.length() == 0)
              {
                  Snackbar.make(getCurrentFocus(), "Please enter your password", Snackbar.LENGTH_LONG).show();
              }
              else if (get_Id.matches("") && get_password.matches(""))
              {
                  Snackbar.make(getCurrentFocus(), "Invalid username or password !", Snackbar.LENGTH_LONG).show();
              }
              else if (!(str_username.matches(get_Id)))
              {
                  Snackbar.make(getCurrentFocus(), "Invalid username or password", Snackbar.LENGTH_LONG).show();
              }
              else if (!(str_password.matches(str_password)))
              {
                  Snackbar.make(getCurrentFocus(), "Invalid username or password", Snackbar.LENGTH_LONG).show();
              }
              else if ((get_Id.matches(str_username)) && (get_password.matches(str_password))) {

                  SplashActivity.ed.putString("loginTest", "true");
                  SplashActivity.ed.commit();

                  Toast.makeText(getApplicationContext(),"Successfully logged in",Toast.LENGTH_LONG).show();
                  Intent Send=new Intent(getApplicationContext(),ProfileActivity.class);
                  startActivity(Send);
              }

              break;
          case R.id.reg_link:
              goToRegister();
              break;
      }
    }

    private void goToRegister() {
        Intent send=new Intent(getApplicationContext(),RegistrationActivity.class);
        startActivity(send);
    }
    // on back key press exit the application.

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(LoginActivity.this, SplashActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}
