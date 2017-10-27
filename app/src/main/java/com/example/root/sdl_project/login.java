package com.example.root.sdl_project;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class login extends AppCompatActivity {

    EditText user, pass;
    String User, Pass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        user = (EditText) findViewById(R.id.et_login_user);
        pass = (EditText) findViewById(R.id.et_login_passw);


    }

    public void Onlogin(View view) {
        User=user.getText().toString();
        Pass=pass.getText().toString();
        Background background=new Background(getApplicationContext());
        background.execute(User,Pass);



    }

    public  void verify(String str){
        Toast.makeText( getApplicationContext(),str, Toast.LENGTH_LONG).show();
        //startActivity(new Intent(this, login_verification.class));
        Class cls = login_verification.class;
        Intent intent = new Intent(this, cls);
        intent.putExtra("key", str);
        startActivity(intent);
        finish();

    }





    class  Background extends AsyncTask<String,Void,String> {
        String siteurl;

        Context context;
        private Background(Context context) {
            this.context = context.getApplicationContext();
        }

        @Override
        protected void onPreExecute() {
            siteurl = "https://jbhate98.000webhostapp.com/connection/chemlogin.php";

        }


        @Override
        protected String doInBackground(String... args) {
            String user_name, password;

            user_name = args[0];
            password= args[1];

            HttpURLConnection httpURLConnection = null;
            try {
                URL url = new URL(siteurl);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data_string = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&" ;
                bufferedWriter.write(data_string);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                int response_code = httpURLConnection.getResponseCode();
                if (response_code == HttpURLConnection.HTTP_OK) {
                    InputStream input = httpURLConnection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    return (result.toString());

                }
                // Pass data to onPostExecute method
                else {

                    return ("unsuccessful");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                httpURLConnection.disconnect();

            }
            return ("unsuccessful");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
           // Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            if (result.charAt(0)=='N')
            {
                /*Intent intent_name = new Intent();
                intent_name.setClass(getApplicationContext(),login_verification.class);
                //intent_name.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent_name.putExtra("id",result);*/

             verify(result);


               }
            else
                if (result.charAt(0) == 'P') {
                    Intent intent = new Intent(login.this, patient_GUI.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //Toast.makeText(getApplicationContext(), "WELCOME PATIENT", Toast.LENGTH_LONG).show();
                    intent.putExtra("id", result);
                    getApplicationContext().startActivity(intent);
                 }
                else
                if (result.charAt(0) == 'D') {
                    Intent intent = new Intent(login.this, doctor_GUI.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("id", result);
                    //Toast.makeText(getApplicationContext(), "WELCOME DOCTOR", Toast.LENGTH_LONG).show();
                    getApplicationContext().startActivity(intent);
                }

                else
                if (result.charAt(0) == 'C') {
                    Intent intent = new Intent(login.this, chemist_GUI.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Toast.makeText(getApplicationContext(), "WELCOME CHEMIST", Toast.LENGTH_LONG).show();
                    getApplicationContext().startActivity(intent);
                }
        }




        }



}





