package com.example.root.sdl_project;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class signin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void Ptsignin(View view) {


        Class cls = Patient_signin.class;
        startActivity(
                new Intent(this, cls)
        );
    }
    public void Dctsignin(View view) {


        Class cls = doctor_sigin.class;
        startActivity(
                new Intent(this, cls)
        );
    }

    public void Chmsignin(View view) {


        Class cls = Chemist_signin.class;
        startActivity(
                new Intent(this, cls)
        );
    }

}
