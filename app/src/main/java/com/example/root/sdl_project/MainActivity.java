package com.example.root.sdl_project;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button B1,B2;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        B1=(Button)findViewById(R.id.logbtn);
        B2=(Button)findViewById(R.id.signbtn);
        textView=(TextView)findViewById(R.id.er) ;
        ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isConnected() )
        {
            textView.setVisibility(View.INVISIBLE);
        }
        else
        {
            B1.setEnabled(false);
            B2.setEnabled(false);
        }
    }


    public void onLogin(View view) {
        Intent intent = new Intent(MainActivity.this,login.class);

        Class cls = login.class;
        startActivity(
                new Intent(this, cls)
        );
    }

    public void onSignin(View view) {


        Class cls = signin.class;
        startActivity(
                new Intent(this, cls)
        );
    }
}
