package com.example.myapplication;

import static android.app.ProgressDialog.show;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Button mBtn;
    private Button mBtn2;
    private Button mBtn3;
    private static final String TAG = "MainActivity";
    String s1="1";
    String s2="2";
    String s3="3";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"MainActivity Start!");
        //获取app现有token
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task-> {
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "Fetching FcM registration token failed", task.getException());
                        return;
                    }
                    String token = task.getResult();
                });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EdgeToEdge.enable(this);
        mBtn = findViewById(R.id.btn);
        mBtn2 = findViewById(R.id.btn2);
        mBtn3 = findViewById(R.id.btn3);
        mBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,chatactivity.class);;
                intent.putExtra("key", s1);
                startActivity(intent);
            }
        });
        mBtn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this,chatactivity.class);
                intent2.putExtra("key", s2);
                startActivity(intent2);
            }
        });
        mBtn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(MainActivity.this,chatactivity.class);
                intent3.putExtra("key", s3);
                startActivity(intent3);
            }
        });
    }

    public void get_chatrooms(String url)throws IOException, JSONException {
        URL baseurl;
        baseurl = new URL(url);
        HttpURLConnection connection;
        connection =(HttpURLConnection)baseurl.openConnection();
        connection.setRequestMethod("GET");
        int responsecode=connection.getResponseCode();
        if(responsecode==HttpURLConnection.HTTP_OK) {
            BufferedReader read;
            read = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            StringBuffer content = new StringBuffer();
            while ((line = read.readLine()) != null)
                content.append(line);
            read.close();

            JSONObject object = new JSONObject(content.toString());
            JSONArray data = object.getJSONArray("data");
            String[] roomArray = new String[data.length()];
            for (int i = 0; i < data.length(); i++) {
                JSONObject one_data;
                one_data = data.getJSONObject(i);
                int id = one_data.getInt("id");
                String name = one_data.getString("name");
                roomArray[i] = name + "(ID:" + id + ")";
            }
        }
    }
}