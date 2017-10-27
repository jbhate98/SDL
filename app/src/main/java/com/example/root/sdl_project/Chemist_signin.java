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

public class Chemist_signin extends AppCompatActivity {
    EditText Shopname,Num,Licno,Email,Password,Owner;
    String  shopname,num,licno,email,password,owner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chemist_signin);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Shopname=(EditText)findViewById(R.id.et_chem_shpname);
        Num=(EditText)findViewById(R.id.et_chem_num);
        Licno=(EditText)findViewById(R.id.et_chem_lic);
        Email=(EditText)findViewById(R.id.et_chem_email);
        Password=(EditText)findViewById(R.id.et_chem_pass);
        Owner=(EditText)findViewById(R.id.et_chem_owner);
    }
    public void Regchem(View view)
    {

        shopname=Shopname.getText().toString();
        num=Num.getText().toString();
        licno=Licno.getText().toString();
        email=Email.getText().toString();
        password=Password.getText().toString();
        owner=Owner.getText().toString();

        Background background=new Background();
        background.execute(shopname,num,licno,email,password,owner);



    }
    class Background extends AsyncTask<String,Void,String>
    {
        String siteurl;

        @Override
        protected void onPreExecute() {
            siteurl="https://jbhate98.000webhostapp.com/connection/chemsignin.php";

        }

        @Override
        protected String doInBackground(String... args) {
            String shopname, num, licno, email, password, owner;
            shopname = args[0];
            num = args[1];
            licno = args[2];
            email = args[3];
            password = args[4];
            owner = args[5];

            HttpURLConnection httpURLConnection = null;
            try {
                URL url = new URL(siteurl);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data_string = URLEncoder.encode("Shop_name", "UTF-8") + "=" + URLEncoder.encode(shopname, "UTF-8") + "&" +
                        URLEncoder.encode("Phone_no", "UTF-8") + "=" + URLEncoder.encode(num, "UTF-8") + "&" +
                        URLEncoder.encode("Shop_License_no", "UTF-8") + "=" + URLEncoder.encode(licno, "UTF-8") + "&" +
                        URLEncoder.encode("Username", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                        URLEncoder.encode("Password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&" +
                        URLEncoder.encode("Name", "UTF-8") + "=" + URLEncoder.encode(owner, "UTF-8") + "&";
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
        protected void onPostExecute(String result) {
            if (result.equalsIgnoreCase("SUCCESFULLY SIGNED-IN !!!!!!!"))
            {
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                Class cls = login.class;
                startActivity(
                        new Intent(Chemist_signin.this, cls)
                );

            }
            else
            {
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

            }

        }
    }
}
