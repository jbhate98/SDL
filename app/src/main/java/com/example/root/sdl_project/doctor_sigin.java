package com.example.root.sdl_project;

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

public class doctor_sigin extends AppCompatActivity {
    EditText Name,Num,Email,Pass,Degno,Spcl;
    String name,num,email,pass,degno,spcl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_sigin);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Name=(EditText)findViewById(R.id.et_doc_name);
        Num=(EditText)findViewById(R.id.et_doc_num);
        Email=(EditText)findViewById(R.id.et_doc_email);
        Pass=(EditText)findViewById(R.id.et_doc_pass);
        Degno=(EditText)findViewById(R.id.et_doc_deg);
        Spcl=(EditText)findViewById(R.id.et_doc_spcl);
    }
    public void Regdoc(View view)
    {
        name=Name.getText().toString();
        num=Num.getText().toString();
        email=Email.getText().toString();
        pass=Pass.getText().toString();
        degno=Degno.getText().toString();
        spcl=Spcl.getText().toString();

        Background background=new Background();
        background.execute(name,num,degno,spcl,email,pass);


    }

    class Background extends AsyncTask<String,Void,String>
    {
        String siteurl;

        @Override
        protected void onPreExecute() {
            siteurl="https://jbhate98.000webhostapp.com/connection/docsignin.php";
        }

        @Override
        protected String doInBackground(String... args) {
            String name, num, email, pass, degno, spcl;
            name = args[0];
            num = args[1];
            degno = args[2];
            spcl = args[3];
            email = args[4];
            pass = args[5];
            URL url = null;
            try {
                url = new URL(siteurl);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection httpURLConnection = null;
            try {
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data_string = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                        URLEncoder.encode("mob", "UTF-8") + "=" + URLEncoder.encode(num, "UTF-8") + "&" +
                        URLEncoder.encode("deg_no", "UTF-8") + "=" + URLEncoder.encode(degno, "UTF-8") + "&" +
                        URLEncoder.encode("spcl", "UTF-8") + "=" + URLEncoder.encode(spcl, "UTF-8") + "&" +
                        URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
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

                } else {

                    return ("unsuccessful");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                httpURLConnection.disconnect();
            }
            return ("unsuccessful");
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.equalsIgnoreCase("SUCCESFULLY SIGNED-IN !!!!!!!"))
            {

                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                Class cls = login.class;
                startActivity(
                        new Intent(doctor_sigin.this, cls)
                );
            }
            else
            {
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

            }
        }
    }
}
