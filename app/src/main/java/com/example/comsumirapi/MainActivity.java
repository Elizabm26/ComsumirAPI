package com.example.comsumirapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void mostrar(View view) {
        Bundle bundle = this.getIntent().getExtras();
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://gorest.co.in/public/v1/users",
                  datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");

    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView lista= findViewById(R.id.txtlista);
        String Lista="";
        JSONObject object = new JSONObject(result);
        JSONArray array= object.getJSONArray("data");
        for (int i=0; i < array.length();i++){
            JSONObject Usuario = array.getJSONObject(i);
            Lista = Lista + "user: "+Usuario.getString("id").toString()+
                    "\n" + Usuario.getString("name").toString()+ "\n" +
                    Usuario.getString("email").toString()+"\n"+ Usuario.getString("gender").toString()+
                    "\n"+ Usuario.getString("status").toString()+"\n";
        }
        lista.setText(Lista);
    }
}