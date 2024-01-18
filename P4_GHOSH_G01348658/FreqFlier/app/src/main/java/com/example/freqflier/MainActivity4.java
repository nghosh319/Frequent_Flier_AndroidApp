package com.example.freqflier;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        String eid = getIntent().getStringExtra("empid");
        Spinner spinner = findViewById(R.id.spinner);
        TextView textView8 = findViewById(R.id.textView8);
        TextView textView10 = findViewById(R.id.textView10);
        RequestQueue queue= Volley.newRequestQueue(this);
        String url6 = "http://10.0.2.2:8080/frequentflier/Flights.jsp?passid="+ eid;
        StringRequest request6 = new StringRequest(Request.Method.GET, url6, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                String result = s.trim();
                ArrayList<String> list = new ArrayList<String>();
                String[] rows =result.split("#");
                for(int i = 0;i<rows.length;i++){
                    String[] cols = rows[i].split(",");
                    list.add(cols[0]);

                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity4.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,list);
                spinner.setAdapter(adapter);

            }
        }, null);
        queue.add(request6);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item=adapterView.getSelectedItem().toString();
                String url7 = "http://10.0.2.2:8080/frequentflier/FlightDetails.jsp?flightid="+item;
                StringRequest request7= new StringRequest(Request.Method.GET, url7, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        String output1 ="";
                        String output2= "      Trip Id         Trip Miles \n";
                        output2+= "      --------------------------\n";
                        String result1 = s.trim();
                        String[] cols =result1.split("#");
                        output1+= "  Departure : "+ cols[0] + "\n" + "  Arrival : " + cols[1] +"\n" + "  Miles : "+ cols[3] ;
                        output2+= "        "+cols[3]+"             "+cols[4]+"\n";
                        textView8.setText(output1);
                        textView10.setText(output2);

                    }
                }, null);
                queue.add(request7);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}