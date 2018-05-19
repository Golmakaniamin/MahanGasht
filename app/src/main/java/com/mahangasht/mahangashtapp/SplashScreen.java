package com.mahangasht.mahangashtapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.mahangasht.mahangashtapp.Model.ServiceType;

import org.apache.http.Header;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mgServiceGetTypes();
    }

    private void mgServiceGetTypes() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(PublicMethods.getServisHost() + "/GetTypes", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                MyApplication.myServiceType =  new Gson().fromJson(response.toString(), new TypeToken<List<ServiceType>>() {}.getType());

                Intent main_intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(main_intent);
                finish();

                super.onSuccess(statusCode, headers, response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                MyApplication.myServiceType = new ArrayList<ServiceType>();
                Toast.makeText(getBaseContext(), "خطا در اتصال به اینترنت", Toast.LENGTH_SHORT).show();
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }
}
