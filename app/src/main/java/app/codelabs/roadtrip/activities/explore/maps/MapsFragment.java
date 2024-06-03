package app.codelabs.roadtrip.activities.explore.maps;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import app.codelabs.roadtrip.R;
import app.codelabs.roadtrip.activities.explore.DetailExploreActivity;
import app.codelabs.roadtrip.helpers.ConnectionApi;
import app.codelabs.roadtrip.helpers.GPSCheck;
import app.codelabs.roadtrip.helpers.LocationTracker;
import app.codelabs.roadtrip.helpers.Session;
import app.codelabs.roadtrip.models.CategoryExploreSelected;
import app.codelabs.roadtrip.models.ResponseListLocation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener, OnMapReadyCallback{

    private Session session;
    private Context context;

    private LinearLayout layOutlet, layStartFrom;
    private ImageView imgOutlet;
    private TextView nameOutlet, cityOutlet, provinceOutlet, countryFood, priceFrom;
    int id = 1;
    private List<ResponseListLocation.DataEntity> responseOutlet;

    public static View view;
    protected GoogleApiClient mGoogleApiClient;

    private static final String TAG = "MapFragment";
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 700;
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS =
            UPDATE_INTERVAL_IN_MILLISECONDS / 2;
    private static final float DEFAULT_ZOOM = 15f;
    private SettingsClient mSettingsClient;
    private LocationSettingsRequest mLocationSettingsRequest;
    private LocationRequest mLocationRequest;
    private LocationCallback mLocationCallback;
    private Boolean mRequestingLocationUpdates;
    private static final int REQUEST_CHECK_SETTINGS = 0x1;

    //vars
    private Boolean mLocationPermissionsGranted = false;
    private GoogleMap mMap;
    private FusedLocationProviderClient mfusedLocationProviderClient;
    View mapView;

    private String keyword = "";

    private String Latitude;
    private String Longitude;
    private double lat;
    private double longitude;
    private String valueLatitude = "";
    private String valueLongitude = "";

    Marker mk = null;
//    private com.google.android.gms.maps.GoogleMap GoogleMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_maps, container, false);

        context = getContext();

        setView(view);
        getLocationPermission();
        initMap();

//        if (mMap == null) {
//            mSettingsClient = LocationServices.getSettingsClient(context);
//            getDeviceLocation();
//        }else{
//            fetchData();
//        }
        fetchData();

        return view;
    }

    private void fetchData() {
        getData();
    }

    private void getData() {
        ConnectionApi.apiService(context).getListByCategories(id, "").enqueue(new Callback<ResponseListLocation>() {
            @Override
            public void onResponse(Call<ResponseListLocation> call, Response<ResponseListLocation> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() & response.body().getSuccess()) {
//                        setListLocation(response.body().getData());
                        responseOutlet = response.body().getData();
                        for (int i = 0; i < responseOutlet.size(); i++) {
//                            Latitude = String.valueOf(responseOutlet.get(i).getLatitude());
//                            Longitude = String.valueOf(responseOutlet.get(i).getLongitude());
                            String name = responseOutlet.get(i).getTitle();
//                            lat = Double.parseDouble(Latitude.replace(",", "."));
//                            longitude = Double.parseDouble(Longitude.replace(",", "."));
                            lat = responseOutlet.get(i).getLatitude();
                            longitude = responseOutlet.get(i).getLongitude();
                            LatLng cordinate = new LatLng(lat, longitude);
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cordinate, 10));
                            BitmapDescriptor icon11 = BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_car_unclick);
                            BitmapDescriptor icon12 = BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_car_click);
                            BitmapDescriptor icon21 = BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_wisata_unclick);
                            BitmapDescriptor icon22 = BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_wisata_click);
                            BitmapDescriptor icon31 = BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_food_unclick);
                            BitmapDescriptor icon32 = BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_food_click);
                            BitmapDescriptor icon41 = BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_rent_unclick);
                            BitmapDescriptor icon42 = BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_rent_click);

                            mk = mMap.addMarker(new MarkerOptions().position(new LatLng(lat, longitude)).anchor(0.5f,0.5f));
//                            mk.setIcon(icon11);
                            if (id == 1){
                                mk.setIcon(icon11);
                            }
                            if (id == 2){
                                mk.setIcon(icon21);
                            }
                            if (id == 3){
                                mk.setIcon(icon31);
                            }
                            if (id == 4){
                                mk.setIcon(icon41);
                            }
                            mk.setTag(responseOutlet.get(i).getId());

                            Locale localeID = new Locale("in", "ID");
                            NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

                            nameOutlet.setText(responseOutlet.get(i).getTitle());
                            cityOutlet.setText(responseOutlet.get(i).getCity());
                            provinceOutlet.setText(responseOutlet.get(i).getProvince());
                            if (!responseOutlet.get(i).getTags().isEmpty()){
                                countryFood.setText(responseOutlet.get(i).getTags().get(0));
                            }
                            priceFrom.setText(formatRupiah.format((double)responseOutlet.get(i).getPrice_start()).replace(",00", "").replace("Rp",""));

                            if (responseOutlet.get(i).getImages().isEmpty()){

                            }else {
                                Picasso.with(context).load(responseOutlet.get(i).getImages().get(0))
                                        .placeholder(R.drawable.ic_car)
                                        .error(R.drawable.default_no_image)
                                        .fit().centerCrop().into(imgOutlet);
                            }

                            layOutlet.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(context, DetailExploreActivity.class);
                                    int outletId = (Integer) mk.getTag();
//                                    intent.putExtra("latitude", lat);
//                                    intent.putExtra("longitude", longitude);
                                    intent.putExtra("outlet_id", outletId);
                                    startActivity(intent);
                                }
                            });

                            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                @Override
                                public boolean onMarkerClick(@NonNull Marker marker) {
//                                    mk.setIcon(icon12);
                                    layOutlet.setVisibility(View.VISIBLE);
                                    if (id == 1){
                                        mk.setIcon(icon12);
                                        countryFood.setVisibility(View.GONE);
                                        layStartFrom.setVisibility(View.GONE);
                                    }
                                    if (id == 2){
                                        mk.setIcon(icon22);
                                        countryFood.setVisibility(View.GONE);
                                        layStartFrom.setVisibility(View.GONE);
                                    }
                                    if (id == 3){
                                        mk.setIcon(icon32);
                                        countryFood.setVisibility(View.VISIBLE);
                                        layStartFrom.setVisibility(View.VISIBLE);
                                    }
                                    if (id == 4){
                                        mk.setIcon(icon42);
                                        countryFood.setVisibility(View.GONE);
                                        layStartFrom.setVisibility(View.VISIBLE);
                                    }
                                    return false;
                                }
                            });
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseListLocation> call, Throwable t) {
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setView(View view) {
        layOutlet = view.findViewById(R.id.branchInfo);
        layStartFrom = view.findViewById(R.id.lay_start_from);
        imgOutlet = view.findViewById(R.id.icon_store);
        nameOutlet = view.findViewById(R.id.store);
        cityOutlet = view.findViewById(R.id.city);
        provinceOutlet = view.findViewById(R.id.province);
        countryFood = view.findViewById(R.id.country_food);
        priceFrom = view.findViewById(R.id.price_from);
    }

    @Override
    public void onResume() {
        Log.d("STATUS", "RESUME");
        super.onResume();
    }



    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e("OutletMapFragment", "onConnectionFailed: ConnectionResult.getErrorCode() = "
                + connectionResult.getErrorCode());
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        Log.d("onMapReady", "");

        mMap = googleMap;

        if (mLocationPermissionsGranted) {
            mSettingsClient = LocationServices.getSettingsClient(context);
//            getDeviceLocation();

            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            mMap.getUiSettings().setMapToolbarEnabled(false);
            mMap.setPadding(0,0,0,0);
//            mMap.setOnInfoWindowClickListener(this);

        }
    }

    private void getLocationPermission() {
        Log.d(TAG, "getLocationPermission: getting location permissions");
        locationPermissionRequest.launch(new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        });
    }


    ActivityResultLauncher<String[]> locationPermissionRequest =
            registerForActivityResult(new ActivityResultContracts
                            .RequestMultiplePermissions(), result -> {
                        Boolean fineLocationGranted = null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                            fineLocationGranted = result.getOrDefault(
                                    Manifest.permission.ACCESS_FINE_LOCATION, false);
                        }
                        Boolean coarseLocationGranted = null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                            coarseLocationGranted = result.getOrDefault(
                                    Manifest.permission.ACCESS_COARSE_LOCATION, false);
                        }
                        if (fineLocationGranted != null && fineLocationGranted) {
                            // Precise location access granted.
                            mLocationPermissionsGranted = true;
                            initMap();

                            Log.e(TAG, "SUKSES");
                        } else if (coarseLocationGranted != null && coarseLocationGranted) {
                            // Only approximate location access granted.
                            Log.e(TAG, "CANCEL");
                        } else {
                            // No location access granted.
                            Log.e(TAG, "GAGAL");
                            mLocationPermissionsGranted = false;
                            getActivity().onBackPressed();
                            Toast.makeText(context, "Memerlukan izin lokasi", Toast.LENGTH_SHORT).show();
                        }
                    }
            );




    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
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
        final LocationManager manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

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

        valueLatitude = String.valueOf(latLng.latitude);
        valueLongitude = String.valueOf(latLng.longitude);
//        loadOutlet();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mGoogleApiClient != null)
            mGoogleApiClient.connect();
//        mMap = GoogleMap;
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSelectCategory(CategoryExploreSelected categoryExploreSelected){
        id = categoryExploreSelected.id;
        if (mk != null){
            mk.remove();
            mk = null;
            layOutlet.setVisibility(View.GONE);
        }
//        Marker marker = mMap.addMarker(new MarkerOptions().position(entry.getValue()).title(entry.getKey()));
        getData();
    }
}
