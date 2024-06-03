package app.codelabs.roadtrip.activities.explore;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import app.codelabs.roadtrip.R;
import app.codelabs.roadtrip.activities.explore.adapter.AdapterImageList;
import app.codelabs.roadtrip.helpers.ConnectionApi;
import app.codelabs.roadtrip.helpers.RecentUtils;
import app.codelabs.roadtrip.helpers.ResponseDetailExplore;
import app.codelabs.roadtrip.models.ResponseListLocation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailExploreActivity extends AppCompatActivity {
    private Context context;
    private ImageView ivBack;
    private LinearLayout layStartFrom;
    private TextView outlet, category, subCategory, price, btnRute, btnTelepone, desc, location, day, time;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private List<String> list = new ArrayList<>();

    private AdapterImageList adapter;

    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;
    private static final String TAG = "DetailExploreActivity";
    private String latitudeSource;
    private String longitudeSource;
    private String latitudeDestination;
    private String longitudeDestination;
    private String phoneNumber;

    private AdView mAdView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_explore);
        context = getApplicationContext();

        functionCheckLocationPermission();
        getData();
        setView();
        setEvent();
        setRecyclerView();
        loadAds();
    }

    private void loadAds() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adViewEx);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
//                Toast.makeText(context, "Ads close", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdImpression() {
                // Code to be executed when an impression is recorded
                // for an ad.
            }

            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }
        });
    }

    private void getData(){
        int id = getIntent().getIntExtra("outlet_id", 0);
                ConnectionApi.apiService(context).getDetailExplore(id).enqueue(new Callback<ResponseDetailExplore>() {
                    @Override
                    public void onResponse(Call<ResponseDetailExplore> call, Response<ResponseDetailExplore> response) {
                        if (response.body() != null) {
                            if (response.isSuccessful() && response.body().getSuccess()) {
                                ResponseDetailExplore.DataEntity data = response.body().getData();
                                Locale localeID = new Locale("in", "ID");
                                NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

                                for (int i = 0; i < data.getImages().size(); i++) {
                                    list.add(data.getImages().get(i));
                                    setImageList(list);
                                }

                                outlet.setText(data.getTitle());
                                category.setText(data.getCategory().getTitle());
                                if (!data.getTags().isEmpty()){
                                    subCategory.setText(data.getTags().get(0));
                                }else{
                                    subCategory.setVisibility(View.GONE);
                                }
                                price.setText(formatRupiah.format((double) data.getPrice_start()).replace(",00", "").replace("Rp",""));
                                desc.setText(Html.fromHtml(data.getDescription().replace("<br>","").replace("<p>","").replace("</p>","")));
                                location.setText(Html.fromHtml(data.getAddress().replace("<br>","").replace("<p>","").replace("</p>","")));
                                day.setText(data.getOperational_days());
                                time.setText(data.getOperational_times());

                                latitudeDestination = String.valueOf(data.getLatitude());
                                longitudeDestination = String.valueOf(data.getLongitude());
                                phoneNumber = data.getPhone();

                                if (data.getId() == 1){
                                    layStartFrom.setVisibility(View.GONE);
                                }
                                if (data.getId() == 2){
                                    layStartFrom.setVisibility(View.GONE);
                                }
                                if (data.getId() == 3){
                                    layStartFrom.setVisibility(View.VISIBLE);
                                }
                                if (data.getId() == 4){
                                    layStartFrom.setVisibility(View.VISIBLE);
                                }

                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseDetailExplore> call, Throwable t) {
                        if (t.getMessage() != null) {
                            Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    private void setView() {
        ivBack = findViewById(R.id.iv_back);
        layStartFrom = findViewById(R.id.lay_start_from);
        outlet = findViewById(R.id.tv_outlet);
        category = findViewById(R.id.tv_kategori);
        subCategory = findViewById(R.id.tv_sub_kategori);
        price = findViewById(R.id.tv_Price);
        btnRute = findViewById(R.id.button_rute);
        btnTelepone = findViewById(R.id.button_telepone);
        desc = findViewById(R.id.tv_desc);
        location = findViewById(R.id.tv_location);
        day = findViewById(R.id.day);
        time = findViewById(R.id.time);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        adapter = new AdapterImageList(context);
    }

    private void setEvent() {

        if (ContextCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    DetailExploreActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_LOCATION_PERMISSION
            );
        } else {
            getCurrentLocation();
        }

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnRute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.google.com/maps/dir/" + latitudeSource + "," + longitudeSource + "/" + latitudeDestination + "," + longitudeDestination);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.setPackage("com.google.android.apps.maps");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        btnTelepone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phoneNumber != null) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(phoneNumber)));
                    startActivity(intent);
                }
            }
        });
    }

    private void setImageList(List<String> data) {
        adapter.setItems(data);
    }

    private void setRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);

        if (0 == recyclerView.getItemDecorationCount()){
            recyclerView.addItemDecoration(new RecentUtils.PaddingItemDecoration(80));
        }
    }

    private void functionCheckLocationPermission() {
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(DetailExploreActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(DetailExploreActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                ActivityCompat.requestPermissions(DetailExploreActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(context,
                            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    functionCheckLocationPermission();
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationServices.getFusedLocationProviderClient(DetailExploreActivity.this)
                .requestLocationUpdates(locationRequest, new LocationCallback() {

                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(DetailExploreActivity.this)
                                .removeLocationUpdates(this);

                        if (locationResult != null && locationResult.getLocations().size() > 0) {
                            int lastLocationIndex = locationResult.getLocations().size() - 1;
                            double latitude = locationResult.getLocations().get(lastLocationIndex).getLatitude();
                            double longitude = locationResult.getLocations().get(lastLocationIndex).getLongitude();
                            
                            latitudeSource = String.valueOf(latitude);
                            longitudeSource = String.valueOf(longitude);

                            Log.e(TAG, "onLocationResult: " + latitudeDestination + " + " + longitudeDestination );
                        }
                    }
                }, Looper.getMainLooper());
    }

}
