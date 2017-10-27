package com.example.root.sdl_project;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class login_verification extends AppCompatActivity {
    EditText vericode;
    String code;
    String id;
    String temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_verification);
        vericode = (EditText) findViewById(R.id.et_vericode);


        temp=getIntent().getExtras().getString("key");


        Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_LONG).show();
        if(temp== null){
            Toast.makeText(getApplicationContext(), "string is null ", Toast.LENGTH_LONG).show();
        }

            Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_LONG).show();
        id=temp.substring(1);
        //Toast.makeText(getApplicationContext(), id, Toast.LENGTH_LONG).show();




    }

    public void verify(View view) {
        code = vericode.getText().toString();

        Background background = new Background();
        background.execute(code, id);


    }

    class Background extends AsyncTask<String, Void, String> {
        String siteurl;
        Class cls = doctor_GUI.class;

        @Override
        protected void onPreExecute() {

            siteurl = "https://jbhate98.000webhostapp.com/connection/verification.php";

        }

        @Override
        protected String doInBackground(String... args) {
            String code;
            String id;
            char type;
            code = args[0];
            id = args[1].substring(1);
            Log.d("id",id);
            type = args[1].charAt(0);
            Log.d("type field", String.valueOf(type));
            URL url = null;
            HttpURLConnection httpURLConnection = null;
            StringBuilder result = null;
            try {
                url = new URL(siteurl);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data_string = URLEncoder.encode("vericode", "UTF-8") + "=" + URLEncoder.encode(code, "UTF-8") + "&" +
                        URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8") + "&" +
                        URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(type), "UTF-8") + "&";
                bufferedWriter.write(data_string);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();


                int response_code = httpURLConnection.getResponseCode();
                if (response_code == HttpURLConnection.HTTP_OK) {
                    InputStream input = httpURLConnection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    result = new StringBuilder();
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


            }//END OF TRY CLAUSE
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                httpURLConnection.disconnect();

            }

            return ("unsuccessful");

        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("response from php",result);
            if (result.equalsIgnoreCase("verification success")) {
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                getApplicationContext().startActivity(new Intent(login_verification.this,login.class));
            } else {
                Toast.makeText(getApplicationContext(), "PLEASE CHECK YOUR CODE AND TRY AGAIN!!!!!", Toast.LENGTH_LONG).show();
            }
        }

    }
}
