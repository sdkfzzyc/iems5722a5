package com.example.myapplication;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Objects;

import javax.net.ssl.HttpsURLConnection;

public class chatactivity extends AppCompatActivity {

    private Button mBtn;
    private Button clear;
    String[] ans={};
    LinkedList<chatdata> myData = new LinkedList<>();

    public chatactivity() throws IOException {
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatactivity);
        EdgeToEdge.enable(this);
        mBtn = findViewById(R.id.btn);
        clear = findViewById(R.id.button2);


        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(chatactivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(chatactivity.this, chatactivity.class);
                startActivity(intent);
            }
        });

        String base;
        String value;
        Intent intent;
        intent = getIntent();
        value = intent.getStringExtra("key");
        if(Objects.equals(value, "3")) {
            /*String s1="5722 Danny 2024-09-29 19:52 user_id:1";
            String s2="distributed system Danny 2024-09-29 19:52 user_id:1";
            String s3="scalable system Danny 2024-09-29 19:52 user_id:1";
            String s4="mobile app Danny 2024-09-29 19:52 user_id:1";
            String s5="test Danny 2024-09-29 19:52 user_id:1";
            myData.add(new chatdata(R.drawable.left_image, s1, 0));
            myData.add(new chatdata(R.drawable.left_image, s2, 0));
            myData.add(new chatdata(R.drawable.left_image, s3, 0));
            myData.add(new chatdata(R.drawable.left_image, s4, 0));
            myData.add(new chatdata(R.drawable.left_image, s5, 0));*/
            base = "http://10.0.2.2:8000/get_messages/?chatroom_id=3";
            try {
                get_messages(base);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            for (int i = ans.length - 1; i >= 0; --i) {
                myData.add(new chatdata(R.drawable.left_image, ans[i], 0));
            }
        }
        else if(Objects.equals(value, "2")){
            /*String s1="software engineering Danny 2024-09-29 19:52 user_id:1";
            String s2="testing 123 TESTING 123 Danny 2024-09-29 19:52 user_id:1";
            String s3="agile scrum Danny 2024-09-29 19:52 user_id:1";
            myData.add(new chatdata(R.drawable.left_image, s1, 0));
            myData.add(new chatdata(R.drawable.left_image, s2, 0));
            myData.add(new chatdata(R.drawable.left_image, s3, 0));*/
            base ="http://10.0.2.2:8000/get_messages/?chatroom_id=2";
            try {
                System.out.println("TRY");
                get_messages(base);
            } catch (IOException e) {
                System.out.println("ERR");
                throw new RuntimeException(e);
            } catch (JSONException e) {
                System.out.println("ERRJ");
                throw new RuntimeException(e);
            }
            for (int i = ans.length - 1; i >= 0; --i) {
                myData.add(new chatdata(R.drawable.left_image, ans[i], 0));
            }
        }

        ListView listView = findViewById(R.id.listView_information);
        listView.setAdapter(new MyAdapter(myData,this));
        return;
    }

    public void send(View view) throws IOException {
        EditText e1;String Sendtext;ListView listView;

        listView = findViewById(R.id.listView_information);
        e1= findViewById(R.id.e1);
        listView.setAdapter(new MyAdapter(myData,this));
        Sendtext = e1.getText().toString();


        if(Sendtext!=""){
            Date time = null;time = new Date();
            SimpleDateFormat t = new SimpleDateFormat("HH:mm");
            String timel;timel = t.format(time);
            Sendtext=Sendtext+"__";Sendtext=Sendtext.concat(timel);

            myData.add(new chatdata(R.drawable.right_image,Sendtext,1));
            listView = findViewById(R.id.listView_information);
            listView.setAdapter(new MyAdapter(myData,this));

        }

        String base;
        String value;
        Intent intent;
        intent = getIntent();
        value = intent.getStringExtra("key");
        if(Objects.equals(value, "3")){
            base = "http://127.0.0.1:0/get_messages/?chatroom_id=3";
            URL obj = new URL(base);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            //添加请求头
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            //url传参的参数
            String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

            //发送Post请求
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println("\n发送 POST 请求到 URL : " + base);
            System.out.println("Post 参数 : " + urlParameters);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine = Sendtext;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }else if(Objects.equals(value, "2")){
            base = "http://127.0.0.1:0/get_messages/?chatroom_id=2";
            URL obj = new URL(base);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            //添加请求头
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            //url传参的参数
            String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

            //发送Post请求
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println("\n发送 POST 请求到 URL : " + base);
            System.out.println("Post 参数 : " + urlParameters);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine= Sendtext;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }
        return;
    }

    private final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:99.0) Gecko/20100101 Firefox/99.0";

    public void get_messages(String s)throws IOException, JSONException {
        URL baseurl;
        baseurl = new URL(s);
        HttpURLConnection connection;
        System.out.println("准备链接");
        System.out.println(s);

        connection = (HttpURLConnection) baseurl.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(3000);
        connection.setReadTimeout(3000);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setUseCaches(false);
        System.out.println("GET");
        connection.setRequestProperty("User-Agent", USER_AGENT);
        connection.connect();
        int responseCode = connection.getResponseCode();
        System.out.println("\n发送 'GET' 请求到 URL : " + baseurl);
        System.out.println("Response Code : " + responseCode);

        int response = connection.getResponseCode();
        if (response == HttpURLConnection.HTTP_OK) {
            BufferedReader r = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String ss;
            StringBuffer content = new StringBuffer();
            while ((ss = r.readLine()) != null)
                content.append(ss);
            r.close();

            JSONObject object = new JSONObject(content.toString());
            JSONObject data = object.getJSONObject("data");
            JSONArray messages = data.getJSONArray("messages");
            String[] datarray = new String[messages.length()];
            int n = messages.length();

            for (int i = 0; i < messages.length(); i++) {
                JSONObject finaldata = messages.getJSONObject(i);

                String message = finaldata.getString("message");
                String name = finaldata.getString("name");
                String message_time = finaldata.getString("message_time");
                int id = finaldata.getInt("user_id");

                datarray[i] = message + name + message_time + "(ID:" + id + ")";
            }
            ans = datarray;

            return;
        }
    }
}