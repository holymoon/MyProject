package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}