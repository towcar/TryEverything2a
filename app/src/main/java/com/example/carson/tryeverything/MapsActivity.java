package com.example.carson.tryeverything;


import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private static final String PLACES_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/search/json?";
    private static final boolean PRINT_AS_STRING = false;

    public double latitude;
    public double longitude;
    private double radius;

    private MapView mapView;
    public static GoogleMap mMap;
    private static final int permResult = 1;
    int[] grantResults;
    String textName;

    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;

    Integer position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //test to make sure gps is running
        if (!isGooglePlayServicesAvailable()) {
            finish();
        }

        setContentView(R.layout.activity_item_action);


        // Here, thisActivity is the current activity, if permission called does not equal permission granted.. request permission
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // No explanation needed, we can request the permission.
            Log.e("Log", "beforeRequest");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, permResult);
            Log.e("Text", "AfterRequest");
            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
        }

        //get intent data
        Bundle b = this.getIntent().getExtras();
        textName = b.getString("text");
        Log.e("Text", textName);

        position = b.getInt("position");

        //sets title text
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(textName);

        //sets the background image
        setBackground(textName);

        //sets to see what the value is at should be 1 while green
        Log.e("MapActivity","arrayInt = " + Global.arrayInt[position]);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //pass search task data to mapArray
        SearchTask search = new SearchTask();
        Object[] mapArray = (Object[]) search.locate("brewery", 100.34234, 100.2342);

    }




    @Override
    public void onLocationChanged(Location location) {

       //
       //Canceled because I did not want updating when moving if trying to view location
       //

       /*
        Log.e("", "");
        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(location.getLatitude(), location.getLongitude()))
                .title("Me"));


        //move map camera
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));

*/
    }


    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
    }


    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
    }


    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }

    private boolean isGooglePlayServicesAvailable() {
        //double check this is the correct activity

        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int status = googleApiAvailability.isGooglePlayServicesAvailable(this);
        if (status != ConnectionResult.SUCCESS) {
            if (googleApiAvailability.isUserResolvableError(status)) {
                googleApiAvailability.getErrorDialog(this, status, 2404).show();
            }
            return false;
        }
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        //double checks permission is granted
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.e("onMapReady", "returned..");
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        Log.e("Log", "LocationEnabledTrue");
        buildGoogleApiClient();

    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)

                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        //get request seems to return null
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        Log.e("Log", "onConnected");


        // Creating a criteria object to retrieve provider
        Criteria criteria = new Criteria();

        // Getting the name of the best provider
        String provider = locationManager.getBestProvider(criteria, true);

        // Getting Current Location
        Location location = locationManager.getLastKnownLocation(provider);

        latitude = location.getLatitude();
        longitude = location.getLongitude();

        //adds your own location to the map
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(location.getLatitude(), location.getLongitude()))
                .title("Me"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude())));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(8));

        //checks for location service enabled
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }

        //get marker for second point
        //call StringBuilder and get url String
        StringBuilder sb = sbMethod();
        String callUrl = sb.toString();

        PlacesTask placesTask = new PlacesTask();
        placesTask.execute(callUrl);

        //get directions for two points
        //http://stackoverflow.com/questions/32810495/google-direction-route-from-current-location-to-known-location
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public StringBuilder sbMethod() {

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        //get request seems to return null
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        Log.e("Log", "onConnected");


        // Creating a criteria object to retrieve provider
        Criteria criteria = new Criteria();

        // Getting the name of the best provider
        String provider = locationManager.getBestProvider(criteria, true);

        // Getting Current Location
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        Location location = locationManager.getLastKnownLocation(provider);

        latitude = location.getLatitude();
        longitude = location.getLongitude();


        //use your current location here
        double mLatitude = 49.663;
        double mLongitude = -103.85;

        double mLatitude2 = latitude;
        double mLongitude2 = longitude;

        //StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        //
        sb.append("location=" + latitude + "," + longitude);
        sb.append("&radius=20000");
       sb.append("&keyword=" + textName);
        Log.e("TextName",textName);
        //sb.append("&keyword=restaurant");
        sb.append("&key=AIzaSyA0x66sAnITFffayOlxPie3SlHadc4eVn4");

        Log.d("Map", "api: " + sb.toString());


        return sb;
    }

    public static void addMapMarker(MarkerOptions markerOptions){
        //adds a marker to the map
        mMap.addMarker(markerOptions);
    }

    private void setBackground(String text){

        LinearLayout layout =(LinearLayout)findViewById(R.id.activity_item_action);

        switch (text) {
            case "Brewery Tour":
                layout.setBackgroundResource(R.drawable.brewery2);
                break;
            case "Bungee Jumping":
                layout.setBackgroundResource(R.drawable.bungee);
                break;
            case "Cave Diving":
                layout.setBackgroundResource(R.drawable.snowboardlist);
                break;
            case "Camping":
                layout.setBackgroundResource(R.drawable.snowboardlist);
                break;
            case "Cliff Jumping":
                layout.setBackgroundResource(R.drawable.snowboardlist);
                break;
            case "Drive In Theater":
                layout.setBackgroundResource(R.drawable.snowboardlist);
                break;
            case "Escape Room Challenge":
                layout.setBackgroundResource(R.drawable.snowboardlist);
                break;
            case "Golfing":
                layout.setBackgroundResource(R.drawable.snowboardlist);
                break;
            case "Helicopter Tour":
                layout.setBackgroundResource(R.drawable.snowboardlist);
                break;
            case "Horseback Riding":
                layout.setBackgroundResource(R.drawable.snowboardlist);
                break;
            case "Kayaking":
                layout.setBackgroundResource(R.drawable.snowboardlist);
                break;
            case "Maze Challenge":
                layout.setBackgroundResource(R.drawable.snowboardlist);
                break;
            case "Mountain Biking":
                layout.setBackgroundResource(R.drawable.snowboardlist);
                break;
            case "Paintball":
                layout.setBackgroundResource(R.drawable.snowboardlist);
                break;
            case "Ride A Roller Coaster":
                layout.setBackgroundResource(R.drawable.snowboardlist);
                break;
            case "River Rapid Riding":
                layout.setBackgroundResource(R.drawable.snowboardlist);
                break;
            case "Sand Surfing":
                layout.setBackgroundResource(R.drawable.snowboardlist);
                break;
            case "Scuba Diving":
                layout.setBackgroundResource(R.drawable.snowboardlist);
                break;
            case "Shark Cave Diving":
                layout.setBackgroundResource(R.drawable.snowboardlist);
                break;
            case "Skydiving":
                layout.setBackgroundResource(R.drawable.snowboardlist);
                break;
            case "snowboarding":
                layout.setBackgroundResource(R.drawable.snowboardlist);
                break;
            case "Surfing":
                layout.setBackgroundResource(R.drawable.surfing);
                break;
            case "Volcano Trekking":
                layout.setBackgroundResource(R.drawable.snowboardlist);
                break;
            case "Whale Watching":
                layout.setBackgroundResource(R.drawable.snowboardlist);
                break;
            case "Wine Tasting":
                layout.setBackgroundResource(R.drawable.snowboardlist);
                break;
            case "Yachting":
                layout.setBackgroundResource(R.drawable.snowboardlist);
                break;
            case "Zip Lining":
                layout.setBackgroundResource(R.drawable.snowboardlist);
                break;
            default:
                layout.setBackgroundResource(R.drawable.snowboardlist);
                break;

        }

        Log.e("Test","Background Set");

    } //probably delete if maps activity doesnt need a background
}



