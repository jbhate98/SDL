package com.example.root.sdl_project;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
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

public class Patient_signin extends Activity{
    EditText Name,Num,Age,Email,Pass;
    String name,num,age,email,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_signin);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Name=(EditText)findViewById(R.id.et_name);
        Num=(EditText)findViewById(R.id.et_num);
        Age=(EditText)findViewById(R.id.et_age);
        Email=(EditText)findViewById(R.id.et_email);
        Pass=(EditText)findViewById(R.id.et_pass);
    }

    public void Regpt(View view)
    {
        name=Name.getText().toString();
        num=Num.getText().toString();
        age=Age.getText().toString();
        email=Email.getText().toString();
        pass=Pass.getText().toString();

        Background background=new Background();
        background.execute(name,num,age,email,pass);



    }

    class  Background extends AsyncTask<String,Void,String>
    {
        String siteurl;

        @Override
        protected void onPreExecute() {
            siteurl="https://jbhate98.000webhostapp.com/connection/ptsign-up.php";

        }


        @Override
        protected String doInBackground(String... args) {
            String name, num, age, email, pass;

            name = args[0];
            num = args[1];
            age = args[2];
            email = args[3];
            pass = args[4];
            HttpURLConnection httpURLConnection = null;
            try {
                URL url = new URL(siteurl);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data_string = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                        URLEncoder.encode("mob", "UTF-8") + "=" + URLEncoder.encode(num, "UTF-8") + "&" +
                        URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                        URLEncoder.encode("age", "UTF-8") + "=" + URLEncoder.encode(age, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8") + "&";
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


                if(result.equalsIgnoreCase("SUCCESFULLY SIGNED-IN!!!!!!!"))
                {
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

                    Class cls = login.class;
                    startActivity(
                            new Intent(Patient_signin.this, cls)
                    );

                }
                else
                {
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

                }
            }

        }


    }

