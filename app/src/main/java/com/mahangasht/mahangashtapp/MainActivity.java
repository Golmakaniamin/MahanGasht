package com.mahangasht.mahangashtapp;

import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.mahangasht.mahangashtapp.Model.Agencies;
import com.mahangasht.mahangashtapp.Model.ServiceType;
import com.mahangasht.mahangashtapp.adapter.profileDrawerAdaptor;

import org.apache.http.Header;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener{
    private GoogleMap mMap;

    EditText TxtSearch;
    ImageButton ImageBtnSearch;
    TextView TxtResult;
    ProgressBar progressBarLoading;

    private ArrayList<Agencies> myAgencies = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TxtSearch = (EditText) findViewById(R.id.txtSearch);
        ImageBtnSearch = (ImageButton) findViewById(R.id.btnSearch);

        progressBarLoading = (ProgressBar) findViewById(R.id.ProgressBarLoading);
        TxtResult = (TextView) findViewById(R.id.txtResult);

        Typeface tf = Typeface.createFromAsset(this.getApplicationContext().getAssets(), "fonts/BYEKAN_0.TTF");

        TxtSearch.setTypeface(tf);
        TxtResult.setTypeface(tf);

//        if (myServiceType == null)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ImageBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchWork(TxtSearch.getText().toString());
            }
        });
    }

    private void mgServiceAgencies(String ServiceTypeId) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(PublicMethods.getServisHost() + "/Agencies/" + ServiceTypeId, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                myAgencies = new Gson().fromJson(response.toString(), new TypeToken<List<Agencies>>() {
                }.getType());

                ListView LVList = (ListView) findViewById(R.id.LVProfile);
                profileDrawerAdaptor LVPadapter = new profileDrawerAdaptor(MainActivity.this, myAgencies);
                LVList.setAdapter(LVPadapter);

                if (myAgencies.size() == 0)
                    TxtResult.setText("مرکزی یافت نشد لطفا خدمات دیگر را امتحان نمایید");
                else
                    TxtResult.setVisibility(View.INVISIBLE);

                progressBarLoading.setVisibility(View.GONE);
                super.onSuccess(statusCode, headers, response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                myAgencies = null;
                TxtResult.setText("عدم ارتباط به اینترنت");

                progressBarLoading.setVisibility(View.GONE);
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        GPSTracker gps = new GPSTracker(getBaseContext());

        LatLng FundsPoint = new LatLng(gps.getLatitude(), gps.getLongitude());
        MarkerOptions iMarker = new MarkerOptions();
        iMarker.position(FundsPoint);
        iMarker.title("Iran, Tehran");
        final Marker marker = mMap.addMarker(iMarker);
        marker.setTag(0);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(FundsPoint, 15));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        Integer clickCount = (Integer) marker.getTag();

        GPSTracker gps = new GPSTracker(getBaseContext());

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(gps.getLatitude(), gps.getLongitude());
        mMap.addMarker(new MarkerOptions().position(sydney).title("Iran, Tehran"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15));

        return false;
    }

    private void searchWork(String searchText) {
        List<String> LStId = new ArrayList<String>();
        String AllServiceTypeId = "";

        if (MyApplication.myServiceType != null) {
            for (ServiceType d : MyApplication.myServiceType) {
                if (d.getSTName() != null) {
                    if (d.getSTName().contains(searchText)) {
                        AllServiceTypeId += d.getServiceTypeId() + ",";
                    }
                }

                if (d.getSTItems() != null) {
                    if (d.getSTItems().contains(searchText)) {
                        AllServiceTypeId += d.getServiceTypeId() + ",";
                    }
                }
            }

            //String.join(",", cities);
            //String aaaa =  LStId.toString();
            progressBarLoading.setVisibility(View.VISIBLE);
            TxtResult.setText("در حال دریافت اطلاعات");
            mgServiceAgencies(AllServiceTypeId);
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }
}