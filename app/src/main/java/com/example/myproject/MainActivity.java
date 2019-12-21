package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText ed;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed = findViewById(R.id.ed);
        tv = findViewById(R.id.tv);
    }
    public void check_net(View view){
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        if(manager != null){
            NetworkInfo activeNetworks =manager.getActiveNetworkInfo();

            if(activeNetworks != null){
                if(activeNetworks.getType() == ConnectivityManager.TYPE_WIFI){
                    Toast.makeText(this,"Wifi",Toast.LENGTH_SHORT).show();
                }else if(activeNetworks.getType() == ConnectivityManager.TYPE_MOBILE){
                    Toast.makeText(this,"Mobile Data",Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this,"No Internet",Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void send(View view){
       RequestQueue queue = Volley.newRequestQueue(this);
       String url = String.format("https://samples.openweathermap.org/data/2.5/weather?q=%s,uk&appid=b6907d289e10d714a6e88b30761fae22",ed.getText().toString());
        StringRequest request =new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this,"Response OK",Toast.LENGTH_SHORT).show();
                        show_result(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,"Response Ok",Toast.LENGTH_SHORT).show();
                    }
                }
        );
        queue.add(request);
    }
    public void show_result(String data){
        try {

            JSONObject main = new JSONObject(data.trim());
         JSONArray weather = main.getJSONArray("weather");
         JSONObject weather_obj = weather.getJSONObject(0);

         String main_weather = weather_obj.getString("main");
         String desc_weather = weather_obj.getString("description");

         JSONObject obj = main.getJSONObject("main");
        double temp_weather_kelvin =  obj.getDouble("temp");

        String result = String.format("Weather: %s\nDescription: %s\nTemp: %s(c)",
                main_weather,desc_weather,temp_weather_kelvin-273.15);
        tv.setText(result);


        }catch (JSONException e){

        }


    }
}