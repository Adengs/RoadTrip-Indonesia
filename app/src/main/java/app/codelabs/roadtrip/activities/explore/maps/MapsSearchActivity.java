package app.codelabs.roadtrip.activities.explore.maps;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import app.codelabs.roadtrip.R;
import app.codelabs.roadtrip.activities.explore.DetailExploreActivity;
import app.codelabs.roadtrip.helpers.ConnectionApi;
import app.codelabs.roadtrip.helpers.ResponseDetailExplore;
import app.codelabs.roadtrip.models.ResponseDetailShopItem;
import app.codelabs.roadtrip.models.ResponseListLocation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsSearchActivity extends AppCompatActivity implements OnMapReadyCallback {
    private Context context;
    private ImageView ivBack;
    private LinearLayout layOutlet, layStartFrom;
    private ImageView imgOutlet;
    private TextView nameOutlet, cityOutlet, provinceOutlet, countryFood, priceFrom;

    public ResponseDetailExplore.DataEntity data;
    private List<ResponseDetailExplore.DataEntity> responsee;
    private double latitude, longitude;
    Marker mk = null;

    private static final String TAG = "MapSearchActivity";
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 700;
    private static final float DEFAULT_ZOOM = 15f;
    private SettingsClient mSettingsClient;
    private Boolean mLocationPermissionsGranted = false;
    private FusedLocationProviderClient mfusedLocationProviderClient;
    private GoogleMap mMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_maps);
        context = getApplicationContext();

        setView();
        getLocationPermission();

//        if(mMap!=null) {
            getData();
//        }
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        getData();
//    }

    private void setView() {
        ivBack = findViewById(R.id.iv_back);
        layOutlet = findViewById(R.id.branchInfo);
        layStartFrom = findViewById(R.id.lay_start_from);
        imgOutlet = findViewById(R.id.icon_store);
        nameOutlet = findViewById(R.id.store);
        cityOutlet = findViewById(R.id.city);
        provinceOutlet = findViewById(R.id.province);
        countryFood = findViewById(R.id.country_food);
        priceFrom = findViewById(R.id.price_from);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void getData() {
        int id = getIntent().getIntExtra("id-maps", 0);
        ConnectionApi.apiService(context).getDetailExplore(id).enqueue(new Callback<ResponseDetailExplore>() {
            @Override
            public void onResponse(Call<ResponseDetailExplore> call, Response<ResponseDetailExplore> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()) {
                        layOutlet.setVisibility(View.VISIBLE);
//                        responsee = response.body().getData();
                        data = response.body().getData();
                        latitude = data.getLatitude();
                        longitude = data.getLongitude();

                        LatLng cordinate = new LatLng(latitude, longitude);
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cordinate, 17));
                        BitmapDescriptor icon12 = BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_car_click);
                        BitmapDescriptor icon22 = BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_wisata_click);
                        BitmapDescriptor icon32 = BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_food_click);
                        BitmapDescriptor icon42 = BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_rent_click);

                        mk = mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).anchor(0.5f, 0.5f));
//                            mk.setIcon(icon11);
                        mk.setTitle(data.getTitle());
                        if (id == 1) {
                            mk.setIcon(icon12);
                            countryFood.setVisibility(View.GONE);
                            layStartFrom.setVisibility(View.GONE);
                        }
                        if (id == 2) {
                            mk.setIcon(icon22);
                            countryFood.setVisibility(View.GONE);
                            layStartFrom.setVisibility(View.GONE);
                        }
                        if (id == 3) {
                            mk.setIcon(icon32);
                            countryFood.setVisibility(View.VISIBLE);
                            layStartFrom.setVisibility(View.VISIBLE);
                        }
                        if (id == 4) {
                            mk.setIcon(icon42);
                            countryFood.setVisibility(View.GONE);
                            layStartFrom.setVisibility(View.VISIBLE);
                        }

                        Locale localeID = new Locale("in", "ID");
                        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

                        nameOutlet.setText(data.getTitle());
                        cityOutlet.setText(data.getCity());
                        provinceOutlet.setText(data.getProvince());
                        countryFood.setText(data.getTags().get(0));
                        priceFrom.setText(formatRupiah.format((double) data.getPrice_start()).replace(",00", "").replace("Rp", ""));

                        if (data.getImages().isEmpty()) {

                        } else {
                            Picasso.with(context).load(data.getImages().get(0))
                                    .placeholder(R.drawable.ic_car)
                                    .error(R.drawable.default_no_image)
                                    .fit().centerCrop().into(imgOutlet);
                        }

                        layOutlet.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(context, DetailExploreActivity.class);
                                int outletId = data.getId();
//                                intent.putExtra("latitude", latitude);
//                                intent.putExtra("longitude", longitude);
                                intent.putExtra("outlet_id", outletId);
                                startActivity(intent);
                            }
                        });
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

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        if (mLocationPermissionsGranted) {
            mSettingsClient = LocationServices.getSettingsClient(context);
            getDeviceLocation();

            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
            mMap.getUiSettings().setMapToolbarEnabled(false);
            mMap.setPadding(0,0,0,0);
//            mMap.setOnInfoWindowClickListener(this);

            getData();

        }
    }

    private void getLocationPermission() {
        Log.d(TAG, "getLocationPermission: getting location permissions");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionsGranted = true;
                initMap();
            } else {
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView);
        @SuppressLint("ResourceType") View myLocationButton = mapFragment.getView().findViewById(0x2);
        mapFragment.getMapAsync((OnMapReadyCallback) this);

        if (myLocationButton != null && myLocationButton.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            // location button is inside of RelativeLayout
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) myLocationButton.getLayoutParams();

            // Align it to
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
            params.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
            params.setMargins(0, 30, 0, 0);
        }
    }

    private void getDeviceLocation() {
        mfusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        try {
            if (mLocationPermissionsGranted) {
                final Task location = mfusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Location currentLocation = (Location) task.getResult();
                            if (currentLocation != null) {
                                moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()),
                                        DEFAULT_ZOOM);
                            } else {
//                                startLocationUpdates();
                                functionCheckGPS();
                            }

                        } else {
                            Toast.makeText(context, "unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage());
        }
    }

    private void functionCheckGPS() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        }
    }

    private void buildAlertMessageNoGps() {
        androidx.appcompat.app.AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setCancelable(true);
        dialog.setMessage("Your GPS seems to be disabled, do you want to enable it?");
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        });
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialog.show();
    }

    private void moveCamera(LatLng latLng, float zoom) {

        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

//        valueLatitude = String.valueOf(latLng.latitude);
//        valueLongitude = String.valueOf(latLng.longitude);
//        loadOutlet();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mLocationPermissionsGranted = false;

        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionsGranted = false;
                            Log.d(TAG, "onRequestPermissionsResult: permission failed");
                            return;
                        }
                    }
                    Log.d(TAG, "onRequestPermissionsResult: permission granted");
                    mLocationPermissionsGranted = true;
                    //initialize our map
                    initMap();
                }
            }
        }
    }
}
