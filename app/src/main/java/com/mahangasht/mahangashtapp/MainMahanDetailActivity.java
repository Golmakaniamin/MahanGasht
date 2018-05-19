package com.mahangasht.mahangashtapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.mahangasht.mahangashtapp.Model.Agencies;
import com.mahangasht.mahangashtapp.adapter.profileDrawerAdaptor;

import org.apache.http.Header;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MainMahanDetailActivity extends AppCompatActivity {

    private ArrayList<Agencies> myAgencies = null;
    TextView TxtResult;
    ProgressBar progressBarLoading;

    String Logger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String id = intent.getStringExtra("IType");

        setContentView(R.layout.activity_main_mahan_detail);

        progressBarLoading = (ProgressBar) findViewById(R.id.ProgressBarLoading);
        TxtResult = (TextView) findViewById(R.id.txtResult);

        Typeface tf = Typeface.createFromAsset(this.getApplicationContext().getAssets(), "fonts/BYEKAN_0.TTF");
        TxtResult.setTypeface(tf);

        TxtResult.setText("در حال دریافت اطلاعات ... ");
        Logger = "1.Start ..." + "\n";

        mgServiceAgencies(id);

        TxtResult.setText(Logger);
    }

    private void mgServiceAgencies(String ServiceTypeGroupId) {
        progressBarLoading.setVisibility(View.VISIBLE);

        Logger += "2.Call GPSTracker ..." + "\n";
        GPSTracker gps = new GPSTracker(getBaseContext());

        Logger += "3.Call AsyncHttpClient ..." + "\n";
        AsyncHttpClient client = new AsyncHttpClient();

        Logger += "4.Get Lat , Long ..." + "\n";
        String TmpStr = ServiceTypeGroupId + "," + gps.getLatitude()+ "," + gps.getLongitude();
        Logger += "5." + TmpStr  + "\n";

        String ServiceGet = PublicMethods.getServisHost() + "/AgenciesGroup//" + TmpStr;
        Logger += "6.Create Address ... " +  ServiceGet + "\n";

        Logger += "6.1.Call Service ..." + "\n";
        client.get(ServiceGet, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Logger += "7. onSuccess Service" + "\n";

                Logger += "8. Convert Json To List" + "\n";
                myAgencies = new Gson().fromJson(response.toString(), new TypeToken<List<Agencies>>() { }.getType());

                Logger += "9. Create List" + "\n";
                ListView LVList = (ListView) findViewById(R.id.LVProfile);

                Logger += "10. Create Adapter" + "\n";
                profileDrawerAdaptor LVPadapter = new profileDrawerAdaptor(MainMahanDetailActivity.this, myAgencies);

                Logger += "11. Set Adapter" + "\n";
                LVList.setAdapter(LVPadapter);

                Logger += "12. Result Count ... " +myAgencies.size() + "\n";
//                if (myAgencies.size() == 0)
//                    TxtResult.setText("مرکزی یافت نشد لطفا خدمات دیگر را امتحان نمایید");
//                else
//                    TxtResult.setVisibility(View.INVISIBLE);
//
//                Logger += "12. Set Adapter" + "\n";
//                //
//                // progressBarLoading.setVisibility(View.GONE);

                TxtResult.setText(Logger);
                super.onSuccess(statusCode, headers, response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                Logger += "7. onFailure Service" + "\n";
                myAgencies = null;
                TxtResult.setText("عدم ارتباط به اینترنت");

                TxtResult.setText(Logger);

                progressBarLoading.setVisibility(View.GONE);
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }
}
