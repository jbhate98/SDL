package com.example.root.sdl_project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class doctor_GUI extends AppCompatActivity {
    String ptname,ptemail,temp,d_id,p_id;
    EditText Ptname,Ptemail;
    String trace;
    String dos_med1,dos_med2,dos_med3,dos_med4,dos_med5,dos_med6;
    String med1,med2,med3,med4,med5,med6;
    StringBuilder m_ids=new StringBuilder(),merge_dose=new StringBuilder();
    public final static int QRcodeWidth = 500 ;
    Bitmap bitmap;
    String qrinput,Date;
   // ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__gui);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Ptname= (EditText) findViewById(R.id.et_ptname);
        Ptemail=(EditText)findViewById(R.id.et_ptemail);
      //  imageView=(ImageView)findViewById(R.id.imageView);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd ");
        Date = mdformat.format(calendar.getTime());
        temp=getIntent().getExtras().getString("id");   // gets id with alphabet truncated to it
        d_id=temp.substring(1);



        Toast.makeText(getApplicationContext(),"Doctor ID is " +d_id, Toast.LENGTH_LONG).show();
        
    }
    public void MED(View view){
        ptname=Ptname.getText().toString();
        ptemail=Ptemail.getText().toString();
        Log.d("ENTERED PATIENT NAME",ptname);
        Log.d("ENTERED PATIENT EMAIL",ptemail);
        Background bg =new Background();
        bg.execute("2",ptemail);
        Intent intent=new Intent(this,Medicine.class);
        startActivityForResult(intent,1);



    }


    Bitmap TextToImageEncode(String Value) throws WriterException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    Value,
                    BarcodeFormat.DATA_MATRIX.QR_CODE,
                    QRcodeWidth, QRcodeWidth, null
            );

        } catch (IllegalArgumentException Illegalargumentexception) {

            return null;
        }
        int bitMatrixWidth = bitMatrix.getWidth();

        int bitMatrixHeight = bitMatrix.getHeight();

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {

                pixels[offset + x] = bitMatrix.get(x, y) ?
                        getResources().getColor(R.color.QRCodeBlackColor):getResources().getColor(R.color.QRCodeWhiteColor);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==1){
            if (resultCode==RESULT_OK){

                trace=data.getStringExtra("trace").toString();
                qrinput=data.getStringExtra("QRCODE").toString();


                Log.d("TRACE AFTER PASSING TO NEW ACTIVITY",trace);

                Toast.makeText(getApplicationContext(),trace,Toast.LENGTH_LONG).show();
                if (trace.charAt(0)=='1'){

                    Background bg1=new Background();
                    med1=data.getStringExtra("med1");
                    dos_med1=data.getStringExtra("dos_med1");
                    Log.d("MEDICINE-1 NAME: ",med1);
                    Log.d("MEDICINE-1 DOSAGE: ",dos_med1);
                    merge_dose.append(dos_med1);
                    merge_dose.append(',');
                    bg1.execute("1",med1);
                   // id=getmid(med1);
                    // Log.d("MEDICINE-1 M_ID: ",id);
                    //Toast t2=Toast.makeText(getApplicationContext(),cb1,Toast.LENGTH_LONG);

                }
                if (trace.charAt(1)=='1'){

                    Background bg2 =new Background();
                    med2=data.getStringExtra("med2");
                    dos_med2=data.getStringExtra("dos_med2");
                    Log.d("MEDICINE-2 NAME: ",med2);
                    Log.d("MEDICINE-2 DOSAGE: ",dos_med2);
                    merge_dose.append(dos_med2);
                    merge_dose.append(',');
                    bg2.execute("1",med2);
                }
                if (trace.charAt(2)=='1'){
                    Background bg3 =new Background();
                    med3=data.getStringExtra("med3");
                    dos_med3=data.getStringExtra("dos_med3");
                    Log.d("MEDICINE-3 NAME: ",med3);
                    Log.d("MEDICINE-3 DOSAGE: ",dos_med3);
                    merge_dose.append(dos_med3);
                    merge_dose.append(',');
                    bg3.execute("1",med3);
                }
                if (trace.charAt(3)=='1'){
                    Background bg4 =new Background();
                    med4=data.getStringExtra("med4");
                    dos_med4=data.getStringExtra("dos_med4");
                    Log.d("MEDICINE-4 NAME: ",med4);
                    Log.d("MEDICINE-4 DOSAGE: ",dos_med4);
                    merge_dose.append(dos_med4);
                    merge_dose.append(',');
                    bg4.execute("1",med4);
                }
                if (trace.charAt(4)=='1'){
                    Background bg5 =new Background();
                    med5=data.getStringExtra("med5");
                    dos_med5=data.getStringExtra("dos_med5");
                    Log.d("MEDICINE-5 NAME: ",med5);
                    Log.d("MEDICINE-5 DOSAGE: ",dos_med5);
                    merge_dose.append(dos_med5);
                    merge_dose.append(',');
                    bg5.execute("1",med4);
                }
                if (trace.charAt(5)=='1'){
                    Background bg6 =new Background();
                    med6=data.getStringExtra("med6");
                    dos_med6=data.getStringExtra("dos_med6");
                    Log.d("MEDICINE-6 NAME: ",med6);
                    Log.d("MEDICINE-6 DOSAGE: ",dos_med6);
                    merge_dose.append(dos_med6);
                    merge_dose.append(',');
                    bg6.execute("1",med4);
                }
            }
        }

    }//end of ativity result





    public void merge(String a){
        m_ids.append(a);
        m_ids.append(',');
    }


    public void done(View view){
        String encodedImage = new String();
        Toast.makeText(getApplicationContext(),"FINAL MID IS "+m_ids.toString(),Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(),"MERGED DOSAGE IS  "+merge_dose.toString(),Toast.LENGTH_SHORT).show();
        try
        {
            bitmap=TextToImageEncode(qrinput);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,100,bos);
            byte[] bArray = bos.toByteArray();
            encodedImage = Base64.encodeToString(bArray, Base64.DEFAULT);
            // imageView.setImageBitmap(bitmap);


        } catch (WriterException e) {
            e.printStackTrace();
        }

        dbinsert db =new dbinsert();
        db.execute(d_id,p_id,m_ids.toString(),merge_dose.toString(),encodedImage);


        // finish();
    }

    class  Background extends AsyncTask<String,String,String> {
        String siteurl,name,type;

        @Override
        protected void onPreExecute() {
            siteurl = "https://jbhate98.000webhostapp.com/connection/get_mid.php";

        }


        @Override
        protected String doInBackground(String... args) {

            if (args[0].equalsIgnoreCase("1")) {
                type="1";
                HttpURLConnection httpURLConnection = null;
                try {
                    name = args[1];
                    URL url = new URL(siteurl);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String data_string =URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(args[0], "UTF-8") + "&" +
                            URLEncoder.encode("med", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&";

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
                return ("error");
            }//end of !st if statement


            if (args[0].equalsIgnoreCase("2")){
                type="2";
                HttpURLConnection httpURLConnection = null;
                try {
                    name = args[1];
                    URL url = new URL(siteurl);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String data_string =URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(args[0], "UTF-8") + "&" +
                            URLEncoder.encode("med", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&";

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
                return ("error");

            }//end of 2nd IF statement
            return ("error");
        }


        @Override
        protected void onPostExecute(String result) {

            if (type.equalsIgnoreCase("1"))
            {
                merge(result);
            }
            else if (type.equalsIgnoreCase("2"))
            {
                p_id=result;
                Toast.makeText(getApplicationContext(),"P_ID OF  "+ptname+" IS "+result,Toast.LENGTH_LONG).show();
            }

        }

    }


    class  dbinsert extends AsyncTask<String,String,String> {
        String siteurl,name;

        @Override
        protected void onPreExecute() {
            siteurl = "https://jbhate98.000webhostapp.com/connection/insert_prescription.php";

        }


        @Override
        protected String doInBackground(String... args) {



                HttpURLConnection httpURLConnection = null;
                try {
                    name = args[1];
                    URL url = new URL(siteurl);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String data_string =URLEncoder.encode("d_id", "UTF-8") + "=" + URLEncoder.encode(args[0], "UTF-8") + "&" +
                            URLEncoder.encode("p_id", "UTF-8") + "=" + URLEncoder.encode(args[1], "UTF-8") + "&" +
                            URLEncoder.encode("m_id", "UTF-8") + "=" + URLEncoder.encode(args[2], "UTF-8") + "&" +
                            URLEncoder.encode("dosage", "UTF-8") + "=" + URLEncoder.encode(args[3], "UTF-8") + "&" +
                            URLEncoder.encode("img", "UTF-8") + "=" + URLEncoder.encode(args[4], "UTF-8") + "&" +
                            URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(Date, "UTF-8") + "&" ;

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
                return ("error");


        }


        @Override
        protected void onPostExecute(String result) {

            AlertDialog.Builder alrt=new AlertDialog.Builder(doctor_GUI.this);
            alrt.setMessage("SUCCESSFULLY UPLOADED THE PRESCRIPTION");
            alrt.setCancelable(true);
            alrt.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                finish();
                }
            });
            alrt.show();
                finish();

        }

    }

}
