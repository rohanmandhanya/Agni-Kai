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
public class RegistrationActivity extends Activity implements View.OnClickListener {
    private  EditText et_name,et_username,et_password,et_usercontact,et_neighborcontact,et_useraddress;
    private Button btn_reg;
    private TextView link_login;
    private ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.material_design_register_form);

        et_name=(EditText)findViewById(R.id.name);
        et_username=(EditText)findViewById(R.id.uname);
        et_password=(EditText)findViewById(R.id.password);
        et_usercontact=(EditText)findViewById(R.id.ucontact);
        et_neighborcontact=(EditText)findViewById(R.id.ncontact);
        et_useraddress=(EditText)findViewById(R.id.uaddress);
        btn_reg=(Button)findViewById(R.id.btn_reg);
        link_login=(TextView)findViewById(R.id.login_link);
        progress=(ProgressBar)findViewById(R.id.progress_bar);
        btn_reg.setOnClickListener(this);
        link_login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.btn_reg:
                String name = et_name.getText().toString();
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                String usercontact=et_usercontact.getText().toString();
                String neighborcontact=et_neighborcontact.getText().toString();
                String useraddress=et_useraddress.getText().toString();
               /* if(!name.isEmpty() && !username.isEmpty() && !password.isEmpty() && !usercontact.isEmpty() && !neighborcontact.isEmpty() && !useraddress.isEmpty())
                {

                  //  progress.setVisibility(View.VISIBLE);

                    registerProcess(name, username, password, usercontact, neighborcontact, useraddress);
                    Toast.makeText(getApplicationContext(),"Successfully Register",Toast.LENGTH_LONG).show();
                    Intent send=new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(send);

                }*/
                if(name.length()==0)
                {
                    Snackbar.make(getCurrentFocus(),"Please Enter name",Snackbar.LENGTH_LONG).show();
                }
                else if(username.length()==0)
                {
                    Snackbar.make(getCurrentFocus(),"Please enter username",Snackbar.LENGTH_LONG).show();
                }
                else if(password.length()==0)
                {
                    Snackbar.make(getCurrentFocus(),"Please enter your password",Snackbar.LENGTH_LONG).show();
                }
                else if(usercontact.length()==0)
                {
                    Snackbar.make(getCurrentFocus(),"Please enter your contact number",Snackbar.LENGTH_LONG).show();

                }
                else if(neighborcontact.length()==0)
                {
                    Snackbar.make(getCurrentFocus(),"Please enter your neighbour contact number",Snackbar.LENGTH_LONG).show();
                }
                else if(useraddress.length()==0)
                {
                    Snackbar.make(getCurrentFocus(),"Please enter your Address",Snackbar.LENGTH_LONG).show();
                }
                else if(password.length()<6 && password.length()>6)
                {
                    Snackbar.make(getCurrentFocus(),"Password should contain six character",Snackbar.LENGTH_LONG).show();
                }
                else if(usercontact.length()<11 && usercontact.length()>11)
                {
                    Snackbar.make(getCurrentFocus(),"Please enter a valid contact number",Snackbar.LENGTH_LONG).show();
                }
                else if(neighborcontact.length()<11 && neighborcontact.length()>11)
                {
                    Snackbar.make(getCurrentFocus(),"Please enter a valid contact number ",Snackbar.LENGTH_LONG).show();
                }
                SplashActivity.ed.putString("name", name);
                SplashActivity.ed.putString("username", username);
                SplashActivity.ed.putString("password", password);
                SplashActivity.ed.putString("usercontact", usercontact);
                SplashActivity.ed.putString("neighborcontact", neighborcontact);
                SplashActivity.ed.putString("useraddress", useraddress);
                SplashActivity.ed.commit();
                Toast.makeText(getApplicationContext(),"Registered successfully!",Toast.LENGTH_LONG).show();
                break;
            case R.id.login_link:
            {
                String uname= et_username.getText().toString();
                String pass= et_password.getText().toString();
                goToLogin();
                break;
            }
        }
    }

    private void goToLogin() {
        Intent sendLogin = new Intent(getApplicationContext(),  LoginActivity.class);
        startActivity(sendLogin);
    }
    // on back key press exit the application.

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(RegistrationActivity.this, SplashActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}
