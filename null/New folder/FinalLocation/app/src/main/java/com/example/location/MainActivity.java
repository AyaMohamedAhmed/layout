package com.example.location;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity{
    Button btnFindLocation;
    LocationManager manager;
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private static final int SEND_SMS_PERMISSIONS_REQUEST_SEND_SMS =2 ;
    private double wayLatitude = 0.0, wayLongitude = 0.0;
    private FusedLocationProviderClient mFusedLocationClient;
    TextView txtLocation;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    Button btnGetAddress;
    TextView txtAddress;
    Button btnSMS;
    String phoneNo;
    String message;
    EditText edtTxtPhoneNumber;
    List<Address> addresses;
    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.txtLocation = (TextView) findViewById(R.id.txtLocation);
        this.edtTxtPhoneNumber = (EditText) findViewById(R.id.edtTxtPhoneNumber);
        btnFindLocation = findViewById(R.id.btnFindLocation);
        btnFindLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    String[] permissions = {android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION};
                    ActivityCompat.requestPermissions(MainActivity.this, permissions, LOCATION_PERMISSION_REQUEST_CODE);

                } else {
                    mFusedLocationClient = LocationServices.getFusedLocationProviderClient(MainActivity.this);
                    locationRequest = LocationRequest.create();
                    locationRequest.setInterval(500)
                            .setFastestInterval(0)
                            .setMaxWaitTime(0)
                            .setSmallestDisplacement(0)
                            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                    locationCallback = new LocationCallback() {
                        @Override
                        public void onLocationResult(LocationResult locationResult) {
                            if (locationResult == null) {
                                return;
                            }
                            for (Location location : locationResult.getLocations()) {
                                if (location != null) {
                                    wayLatitude = location.getLatitude();
                                    wayLongitude = location.getLongitude();
                                    txtLocation.setText(String.format(Locale.US, "%s -- %s", wayLatitude, wayLongitude));
                                }
                            }
                        }
                    };
                    mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());

                }
                ;
            }

        });
        this.txtAddress = (TextView) findViewById(R.id.txtAddress);
        btnGetAddress = findViewById(R.id.btnGetAddress);
        btnGetAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Geocoder geo = new Geocoder(MainActivity.this.getApplicationContext(), Locale.getDefault());
                    addresses = geo.getFromLocation(wayLatitude, wayLongitude, 1);
                    if (addresses.isEmpty()) {
                        txtAddress.setText("Waiting for Location");
                    }
                    else {
                        if (addresses.size() > 0) {
                            txtAddress.setText(addresses.get(0).getFeatureName() + ", "
                                    + addresses.get(0).getLocality() +", "
                                    + addresses.get(0).getAdminArea() + ", "
                                    + addresses.get(0).getCountryName());

                        }
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

             }
        });
        btnSMS = (Button) findViewById(R.id.btnSMS);
        btnSMS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendSMSMessage();
            }
        });

    }
    protected void sendSMSMessage() {
        phoneNo = edtTxtPhoneNumber.getText().toString();
        message = txtAddress.getText().toString();

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        SEND_SMS_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }else{
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.",
                    Toast.LENGTH_LONG).show();
        }
    }


    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE:
                if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, permissions, LOCATION_PERMISSION_REQUEST_CODE);

                } else {
                    Toast.makeText(MainActivity.this, "Location refused", Toast.LENGTH_SHORT).show();
                }
                break;
            case SEND_SMS_PERMISSIONS_REQUEST_SEND_SMS:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, message, null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS refused, please try again.", Toast.LENGTH_LONG).show();

                }
                break;

            }
        }
    }
