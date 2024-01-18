package com.example.freqflier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView textView3 = findViewById(R.id.textView3);
        TextView textView4 = findViewById(R.id.textView4);
        TextView textView5 = findViewById(R.id.textView5);
        TextView textView6 = findViewById(R.id.textView6);
        ImageView imageView=findViewById(R.id.imageView);
        //Button 6 for Exit implementation going back to login Screen
        Button button2=findViewById(R.id.button2);
        Button button3=findViewById(R.id.button3);
        Button button4=findViewById(R.id.button4);
        Button button5=findViewById(R.id.button5);
        Button button6=findViewById(R.id.button6);


//       For creating the fields
        String eid = getIntent().getStringExtra("empid");
        textView3.setText("Welcome back" );
        textView6.setText("Reward Points" );

        RequestQueue queue= Volley.newRequestQueue(this);
        String url2 = "http://10.0.2.2:8080/frequentflier/Info.jsp?passid="+ eid;
        StringRequest request2 = new StringRequest(Request.Method.GET, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                String result = s.trim();
                String[] ids=result.split(":");
                textView4.setText(ids[0]);
                textView5.setText(ids[1]);
            }
        }, null);

        queue.add(request2);

//        For Image Implementation
        String url3="http://10.0.2.2:8080/frequentflier/images/"+eid+".jpeg";
        ImageRequest request3=new ImageRequest(url3, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
            }
        },0,0,null,null);
        queue.add(request3);

        button2.setOnClickListener(new View.OnClickListener() {
                                       public void onClick(View view) {
                                           Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                                           intent.putExtra("empid",eid);
                                           startActivity(intent);

                                       }
                                   }
        );

        button3.setOnClickListener(new View.OnClickListener() {
                                       public void onClick(View view) {
                                           Intent intent = new Intent(MainActivity2.this, MainActivity4.class);
                                           intent.putExtra("empid",eid);
                                           startActivity(intent);

                                       }
                                   }
        );

        button4.setOnClickListener(new View.OnClickListener() {
                                       public void onClick(View view) {
                                           Intent intent = new Intent(MainActivity2.this, MainActivity5.class);
                                           intent.putExtra("empid",eid);
                                           startActivity(intent);

                                       }
                                   }
        );

        button5.setOnClickListener(new View.OnClickListener() {
                                       public void onClick(View view) {
                                           Intent intent = new Intent(MainActivity2.this, MainActivity6.class);
                                           intent.putExtra("empid",eid);
                                           startActivity(intent);

                                       }
                                   }
        );

        button6.setOnClickListener(new View.OnClickListener() {
                                       public void onClick(View view) {
                                           Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                                           startActivity(intent);

                                       }
                                   }
        );

    }
}
