package com.example.root.sdl_project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class patient_GUI extends AppCompatActivity {

    ImageView img;
    Button qrbutton,recbutton;
    String p_id,date,pdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__gui);
        img=(ImageView)findViewById(R.id.imageView);
        qrbutton=(Button)findViewById(R.id.get_qr);
        recbutton=(Button)findViewById(R.id.prev_rec);
        String temp=getIntent().getStringExtra("id").toString();
        p_id=temp.substring(1);
        Date d1,d2;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd ");
        date = mdformat.format(calendar.getTime());
        d1=calendar.getTime();
        calendar.setTime(d1);
        calendar.add(Calendar.DAY_OF_YEAR,-10);
        d2=calendar.getTime();
        pdate=mdformat.format(d2);
        //Toast.makeText(getApplicationContext(),temp,Toast.LENGTH_SHORT).show();


    }



    public void getQR(View view) {

        GetImage gi = new GetImage();
        gi.execute(p_id);
    }

    public void getprev(View view)
    {
        Intent intent=new Intent(this,Prev_pres.class);
        startActivity(intent);
    }

    class GetImage extends AsyncTask<String, Void, Bitmap> {
        String siteurl;


        @Override
        protected void onPreExecute() {

            siteurl = "https://jbhate98.000webhostapp.com/connection/get_qr.php";

        }

        @Override
        protected Bitmap doInBackground(String... args) {

            Bitmap bitmap=null;


            try {
                HttpURLConnection httpURLConnection = null;
                URL url = new URL(siteurl);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data_string = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(args[0], "UTF-8") + "&" +
                                     URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8") + "&" ;

                bufferedWriter.write(data_string);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream input = httpURLConnection.getInputStream();
                bitmap = BitmapFactory.decodeStream(input);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return bitmap;
        }//END OF TRY CLAUSE



        @Override
        protected void onPostExecute (Bitmap result){
            img.setImageBitmap(result);

        }
    }

}
