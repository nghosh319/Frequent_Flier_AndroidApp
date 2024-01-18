package com.example.freqflier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class MainActivity6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        String eid2 = getIntent().getStringExtra("empid");
        Spinner spinner2 = findViewById(R.id.spinner2);
        EditText editText3 = findViewById(R.id.editText3);
        Button button7 = findViewById(R.id.button7);
//        TextView textView11 = findViewById(R.id.textView11);
//        TextView textView12 = findViewById(R.id.textView12);
        RequestQueue queue = Volley.newRequestQueue(this);

//        Getting Data to spinner
        String url10 = "http://10.0.2.2:8080/frequentflier/GetPassengerids.jsp?passid=" + eid2;
        StringRequest request10 = new StringRequest(Request.Method.GET, url10, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                String result = s.trim();
                ArrayList<String> list = new ArrayList<String>();
                String[] rows = result.split("#");
                for (int i = 0; i < rows.length; i++) {
                    list.add(rows[i]);

                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity6.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, list);
                spinner2.setAdapter(adapter);

            }
        }, null);
        queue.add(request10);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 String dpid=adapterView.getSelectedItem().toString();
                 button7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RequestQueue queue= Volley.newRequestQueue(MainActivity6.this);
                        String point=editText3.getText().toString();
                        String url11="http://10.0.2.2:8080/frequentflier/TransferPoints.jsp?spid="+eid2+"&dpid="+dpid+"&npoints="+point;
                        StringRequest request11= new StringRequest(Request.Method.GET, url11, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String s) {
                                String res=s.trim();
                                if(res.contains("successful")){
                                    if (Integer.parseInt(point) == 0) {
                                        Toast.makeText(MainActivity6.this, "No points transfered, try again!!", Toast.LENGTH_LONG).show();
                                    }
                                    else if (Integer.parseInt(point) == 1) {
                                        Toast.makeText(MainActivity6.this, point + " point Transfer successful!!", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(MainActivity6.this, point + " points Transfer successful!!", Toast.LENGTH_LONG).show();
                                    }

                                }
                                else{
                                    Toast.makeText(MainActivity6.this,"Something went wrong", Toast.LENGTH_LONG).show();
                                }


                            }
                        },null);
                        queue.add(request11);
                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
}