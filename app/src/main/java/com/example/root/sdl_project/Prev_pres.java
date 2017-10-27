package com.example.root.sdl_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Prev_pres extends AppCompatActivity {
        TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prev_pres);
       // textView=(TextView)findViewById(R.id.tv_1);
        textView.setText("hii this is/n text");
    }
}
