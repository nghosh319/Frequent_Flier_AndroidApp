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

public class MainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        String eid1 = getIntent().getStringExtra("empid");
        Spinner spinner1 = findViewById(R.id.spinner1);
        TextView textView11 = findViewById(R.id.textView11);
        TextView textView12 = findViewById(R.id.textView12);
        RequestQueue queue= Volley.newRequestQueue(this);
        String url8 = "http://10.0.2.2:8080/frequentflier/AwardIds.jsp?passid="+ eid1;
        StringRequest request8 = new StringRequest(Request.Method.GET, url8, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                String result = s.trim();
                ArrayList<String> list = new ArrayList<String>();
                String[] rows =result.split("#");
                for(int i = 0;i<rows.length;i++){
                    String[] cols = rows[i].split(",");
                    list.add(cols[0]);

                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity5.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,list);
                spinner1.setAdapter(adapter);

            }
        }, null);
        queue.add(request8);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item=adapterView.getSelectedItem().toString();
                String url9 = "http://10.0.2.2:8080/frequentflier/RedemptionDetails.jsp?AwardId="+item+"&passid="+ eid1;
                StringRequest request9= new StringRequest(Request.Method.GET, url9, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        String output3 ="";
                        String output4= "      Redemption Date         Exchange center \n";
                        output4+= "   -----------------------------------------------\n";
                        String result9 = s.trim();
                        String[] cols =result9.split(",");
                        output3+= "  Prize Desc : \n   "+ cols[0] + "\n" + "  Points Needed : " + cols[1] +"\n" ;
                        output4+= "    "+cols[2]+"             "+cols[3]+"\n";
                        output4+= "   -----------------------------------------------\n";
                        textView11.setText(output3);
                        textView12.setText(output4);

                    }
                }, null);
                queue.add(request9);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}