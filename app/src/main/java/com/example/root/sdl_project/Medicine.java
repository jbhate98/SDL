package com.example.root.sdl_project;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
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


public class Medicine extends AppCompatActivity {
    AutoCompleteTextView M1,M2,M3,M4,M5,M6;
    TextView t1,t2,t3,t4;
    Button b1,b2,b3;
    CheckBox cm1,cm2,cm3,cm4,cm5,cm6,ce1,ce2,ce3,ce4,ce5,ce6,cn1,cn2,cn3,cn4,cn5,cn6;
    String   m1,m2,m3,m4,m5,m6;
    StringBuilder trace=new StringBuilder("");
    StringBuilder cb1=new StringBuilder(""),cb2=new StringBuilder(""),cb3=new StringBuilder(""),cb4=new StringBuilder(""),cb5=new StringBuilder(""),cb6=new StringBuilder("");
    StringBuilder qrtext=new StringBuilder("");
    String meds="";
    String[] splt_meds=new String[]{"Crocin","Combiflame","penicilin",""};




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);
        Background bg=new Background();
        bg.execute("0");

        t1=(TextView)findViewById(R.id.textView25);
        t2=(TextView)findViewById(R.id.textView26);
        t3=(TextView)findViewById(R.id.textView27);
        t4=(TextView)findViewById(R.id.textView28);
        M1=(AutoCompleteTextView)findViewById(R.id.et_m1);
        M2=(AutoCompleteTextView)findViewById(R.id.et_m2);
        M3=(AutoCompleteTextView)findViewById(R.id.et_m3);
        M4=(AutoCompleteTextView)findViewById(R.id.et_m4);
        M5=(AutoCompleteTextView)findViewById(R.id.et_m5);
        M6=(AutoCompleteTextView)findViewById(R.id.et_m6);
        M1.setEnabled(false);
        M2.setEnabled(false);
        M3.setEnabled(false);
        M4.setEnabled(false);
        M5.setEnabled(false);
        M6.setEnabled(false);


        cm1=(CheckBox)findViewById(R.id.cb_m1);
        cm2=(CheckBox)findViewById(R.id.cb_m2);
        cm3=(CheckBox)findViewById(R.id.cb_m3);
        cm4=(CheckBox)findViewById(R.id.cb_m4);
        cm5=(CheckBox)findViewById(R.id.cb_m5);
        cm6=(CheckBox)findViewById(R.id.cb_m6);
        ce1=(CheckBox)findViewById(R.id.cb_e1);
        ce2=(CheckBox)findViewById(R.id.cb_e2);
        ce3=(CheckBox)findViewById(R.id.cb_e3);
        ce4=(CheckBox)findViewById(R.id.cb_e4);
        ce5=(CheckBox)findViewById(R.id.cb_e5);
        ce6=(CheckBox)findViewById(R.id.cb_e6);
        cn1=(CheckBox)findViewById(R.id.cb_n1);
        cn2=(CheckBox)findViewById(R.id.cb_n2);
        cn3=(CheckBox)findViewById(R.id.cb_n3);
        cn4=(CheckBox)findViewById(R.id.cb_n4);
        cn5=(CheckBox)findViewById(R.id.cb_n5);
        cn6=(CheckBox)findViewById(R.id.cb_n6);
        b1=(Button)findViewById(R.id.bt_done) ;
        b2=(Button) findViewById(R.id.bt_dosage);
        b3=(Button) findViewById(R.id.bt_entmed);
        cm1.setVisibility(View.INVISIBLE);cm2.setVisibility(View.INVISIBLE);cm3.setVisibility(View.INVISIBLE);cm4.setVisibility(View.INVISIBLE);cm5.setVisibility(View.INVISIBLE);cm6.setVisibility(View.INVISIBLE);
        ce1.setVisibility(View.INVISIBLE);ce2.setVisibility(View.INVISIBLE);ce3.setVisibility(View.INVISIBLE);ce4.setVisibility(View.INVISIBLE);ce5.setVisibility(View.INVISIBLE);ce6.setVisibility(View.INVISIBLE);
        cn1.setVisibility(View.INVISIBLE);cn2.setVisibility(View.INVISIBLE);cn3.setVisibility(View.INVISIBLE);cn4.setVisibility(View.INVISIBLE);cn5.setVisibility(View.INVISIBLE);cn6.setVisibility(View.INVISIBLE);
        t1.setVisibility(View.INVISIBLE);t2.setVisibility(View.INVISIBLE);t3.setVisibility(View.INVISIBLE);t4.setVisibility(View.INVISIBLE);
        b1.setVisibility(View.INVISIBLE);
        b2.setVisibility(View.INVISIBLE);

    }

    public void setauto(View view){
        b3.setVisibility(View.INVISIBLE);
        b2.setVisibility(View.VISIBLE);
        M1.setEnabled(true);
        M2.setEnabled(true);
        M3.setEnabled(true);
        M4.setEnabled(true);
        M5.setEnabled(true);
        M6.setEnabled(true);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, splt_meds);
        M1.setAdapter(adapter);
        M2.setAdapter(adapter);
        M3.setAdapter(adapter);
        M4.setAdapter(adapter);
        M5.setAdapter(adapter);
        M6.setAdapter(adapter);



    }

    public void dosage(View view){
        int count=0;
        Background bg=new Background();
        AlertDialog.Builder alrt=new AlertDialog.Builder(Medicine.this);
        alrt.setTitle("ALERT MESSAGE");
        alrt.setMessage("PLEASE ENTER MEDICINE NAMES");
        alrt.setCancelable(true);

        m1=M1.getText().toString();
        m2=M2.getText().toString();
        m3=M3.getText().toString();
        m4=M4.getText().toString();
        m5=M5.getText().toString();
        m6=M6.getText().toString();
        t1.setVisibility(View.VISIBLE);t2.setVisibility(View.VISIBLE);t3.setVisibility(View.VISIBLE);t4.setVisibility(View.VISIBLE);
        b1.setVisibility(View.VISIBLE);
        b2.setVisibility(View.INVISIBLE);


        if(!m1.equalsIgnoreCase("")) {
//            Log.d("EDIT TEXT VALUE", "NULL VALUE PRESENT IN M1");
            cm1.setVisibility(View.VISIBLE);
            ce1.setVisibility(View.VISIBLE);
            cn1.setVisibility(View.VISIBLE);
            count++;
            trace.append('1');
            qrtext.append(m1);
            qrtext.append(',');
            bg.execute("1",m1.toLowerCase());

        }
        else {
            M1.setVisibility(View.INVISIBLE);
            trace.append('0');
        }
        if(!m2.equalsIgnoreCase("")){
            cm2.setVisibility(View.VISIBLE);ce2.setVisibility(View.VISIBLE);cn2.setVisibility(View.VISIBLE);
            count++;
            trace.append('1');
            qrtext.append(m2);
            qrtext.append(',');
            Background bg1=new Background();
            bg1.execute("1",m2.toLowerCase());
        }
        else {
            M2.setVisibility(View.INVISIBLE);
            trace.append('0');
        }
        if(!m3.equalsIgnoreCase("")){
            cm3.setVisibility(View.VISIBLE);ce3.setVisibility(View.VISIBLE);cn3.setVisibility(View.VISIBLE);
            count++;
            trace.append('1');
            qrtext.append(m3);
            qrtext.append(',');
            Background bg2=new Background();
            bg2.execute("1",m3.toLowerCase());

        }
        else {
            M3.setVisibility(View.INVISIBLE);
            trace.append('0');

        }
        if(!m4.equalsIgnoreCase("")){
            cm4.setVisibility(View.VISIBLE);ce4.setVisibility(View.VISIBLE);cn4.setVisibility(View.VISIBLE);
            count++;
            trace.append('1');
            qrtext.append(m4);
            qrtext.append(',');
            Background bg3=new Background();
            bg3.execute("1",m4.toLowerCase());

        }
        else {
            M4.setVisibility(View.INVISIBLE);
            trace.append('0');

        }

        if(!m5.equalsIgnoreCase("")){
            cm5.setVisibility(View.VISIBLE);ce5.setVisibility(View.VISIBLE);cn5.setVisibility(View.VISIBLE);
            count++;
            trace.append('1');
            qrtext.append(m5);
            qrtext.append(',');
            Background bg4=new Background();
            bg4.execute("1",m5.toLowerCase());

        }
        else{
            M5.setVisibility(View.INVISIBLE);
            trace.append('0');

        }
        if (!m6.equalsIgnoreCase("")){
            ce6.setVisibility(View.VISIBLE);cm6.setVisibility(View.VISIBLE);cn6.setVisibility(View.VISIBLE);
            count++;
            trace.append('1');
            qrtext.append(m6);
            qrtext.append(',');
            Background bg5=new Background();
            bg5.execute("1",m6.toLowerCase());

        }
        else {
            M6.setVisibility(View.INVISIBLE);
            trace.append('0');

        }
        Log.d("trace result ",trace.toString());
        if (trace.toString().equalsIgnoreCase("000000"))
        {
            t1.setVisibility(View.INVISIBLE);t2.setVisibility(View.INVISIBLE);t3.setVisibility(View.INVISIBLE);t4.setVisibility(View.INVISIBLE);
            M1.setVisibility(View.VISIBLE); M2.setVisibility(View.VISIBLE); M3.setVisibility(View.VISIBLE);M4.setVisibility(View.VISIBLE); M5.setVisibility(View.VISIBLE);M6.setVisibility(View.VISIBLE);

            Log.d("trace result ",trace.toString());
            b1.setVisibility(View.INVISIBLE);
            b2.setVisibility(View.VISIBLE);
            alrt.show();
            trace.delete(0,5);
            trace=new StringBuilder("");

        }
        Log.d("TEXT TO BE GIVEN AS INPUT TO QR-CODE TO DECODE IT IS ",qrtext.toString());
    }





    public void done(View view){

        Intent intent=new Intent();
        intent.putExtra("trace",trace.toString());
        intent.putExtra("QRCODE",qrtext.toString());

        if (trace.charAt(0)=='1'){
            intent.putExtra("med1",m1);
            if(cm1.isChecked()){
                cb1.append('1');
            }
            else {
                cb1.append('0');
            }
            if (ce1.isChecked()){
                cb1.append('1');
            }
            else {
                cb1.append('0');
            }
            if (cn1.isChecked()){
                cb1.append('1');
            }
            else {
                cb1.append('0');
            }
            intent.putExtra("dos_med1",cb1.toString());
            Log.d("MED1 DOSAGE  ",cb1.toString());

        }


        if (trace.charAt(1)=='1'){
            intent.putExtra("med2",m2);
            if(cm2.isChecked()){
                cb2.append('1');
            }
            else {
                cb2.append('0');
            }
            if (ce2.isChecked()){
                cb2.append('1');
            }
            else {
                cb2.append('0');
            }
            if (cn2.isChecked()){
                cb2.append('1');
            }
            else {
                cb2.append('0');
            }
            intent.putExtra("dos_med2",cb2.toString());

        }


        if (trace.charAt(2)=='1'){
            intent.putExtra("med3",m3);

            if(cm3.isChecked()){
                cb3.append('1');
            }
            else {
                cb3.append('0');
            }
            if (ce3.isChecked()){
                cb3.append('1');
            }
            else {
                cb3.append('0');
            }
            if (cn3.isChecked()){
                cb3.append('1');
            }
            else {
                cb3.append('0');
            }

            intent.putExtra("dos_med3",cb3.toString());
        }



        if (trace.charAt(3)=='1'){
            intent.putExtra("med4",m4);
            if(cm4.isChecked()){
                cb4.append('1');
            }
            else {
                cb4.append('0');
            }
            if (ce4.isChecked()){
                cb4.append('1');
            }
            else {
                cb4.append('0');
            }
            if (cn4.isChecked()){
                cb4.append('1');
            }
            else {
                cb4.append('0');
            }

            intent.putExtra("dos_med4",cb4.toString());
        }


        if (trace.charAt(4)=='1'){
            intent.putExtra("med5",m5);

            if(cm5.isChecked()){
                cb5.append('1');
            }
            else {
                cb5.append('0');
            }
            if (ce5.isChecked()){
                cb5.append('1');
            }
            else {
                cb5.append('0');
            }
            if (cn5.isChecked()){
                cb5.append('1');
            }
            else {
                cb5.append('0');
            }

            intent.putExtra("dos_med5",cb5.toString());
        }


        if (trace.charAt(5)=='1'){
            intent.putExtra("med6",m6);
            if(cm6.isChecked()){
                cb6.append('1');
            }
            else {
                cb6.append('0');
            }
            if (ce6.isChecked()){
                cb6.append('1');
            }
            else {
                cb6.append('0');
            }
            if (cn6.isChecked()){
                cb6.append('1');
            }
            else {
                cb6.append('0');
            }
            intent.putExtra("dos_med6",cb6.toString());
        }



        setResult(RESULT_OK,intent);
        finish();

    }


    public void getmed(String a){
        if (a!=null)
            splt_meds=new String[]{};
        {splt_meds=a.split(",");
            for (int i=0; i< splt_meds.length; i++)
            {
                Log.d("Splited medicen names",splt_meds[i]);
            }

        }
    }


    class  Background extends AsyncTask<String,String,String>
    {
        String siteurl,siteurl2;
        int flag=0;

        @Override
        protected void onPreExecute() {
            siteurl="https://jbhate98.000webhostapp.com/connection/getmeds.php";
            siteurl2="https://jbhate98.000webhostapp.com/connection/verifymed.php";

        }


        @Override
        protected String doInBackground(String... args) {
            if (args[0].equalsIgnoreCase("0") )
            {
                flag++;
                HttpURLConnection httpURLConnection = null;
                try {
                    URL url = new URL(siteurl);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();

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

            else  if (args[0].equalsIgnoreCase("1") )
            {
                String med;

                med = args[1];

                HttpURLConnection httpURLConnection = null;
                try {
                    URL url = new URL(siteurl2);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String data_string = URLEncoder.encode("med", "UTF-8") + "=" + URLEncoder.encode(med, "UTF-8") + "&" ;

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
            return ("unsuccessful");
        }


        @Override
        protected void onPostExecute(String result) {

            if (flag==1) {
                meds = result;

                Toast.makeText(getApplicationContext(), meds, Toast.LENGTH_LONG).show();
                getmed(result);
            }
            else
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

        }


    }
}
