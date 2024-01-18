package com.example.freqflier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String eid = getIntent().getStringExtra("empid");
        setContentView(R.layout.activity_main3);
        TextView textView7 = findViewById(R.id.textView7);
        TextView textView9 = findViewById(R.id.textView9);
        textView7.setText("Flights");
        RequestQueue queue= Volley.newRequestQueue(this);

        String url5 = "http://10.0.2.2:8080/frequentflier/Flights.jsp?passid="+ eid;
        StringRequest request5 = new StringRequest(Request.Method.GET, url5, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                String result = s.trim();
                String output = "Flight Id         Flight Miles         Destination\n";
                output+= "-----------------------------------------------------------------\n";
                String[] rows =result.split("#");
                for(int i = 0;i<rows.length;i++){
                    String[] cols = rows[i].split(",");
                    output+= cols[0]+ "                  "+ cols[1]+"                         "+cols[2]+"\n";
                }
                output+= "-----------------------------------------------------------------\n";
                textView9.setText(output);
            }
        }, null);

        queue.add(request5);
    }
}