package com.chetsgani.permissioncheck;

import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.BODY_SENSORS;
import static android.Manifest.permission.READ_CALENDAR;
import static android.Manifest.permission.READ_SMS;

public class MainActivity extends AppCompatActivity {
    Button checkbtn;
    public static final int RequestPermissionCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkbtn = (Button) findViewById(R.id.button);

        checkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermissionsMandAbove();
            }
        });
    }

/*-------------------------------Permission Group and Details-----------------------------*/
    /*  android.permission-group.CALENDER       * android.permission.READ_CALENDER
                                                * android.permission.WRITE_CALENDER
        -----------------------------------------------------------------------------------
        android.permission-group.CAMERA         * android.permission.CAMERA
        -----------------------------------------------------------------------------------
        android.permission-group.CONTACTS       * android.permission.READ_CONTACTS
                                                * android.permission.WRITE_CONTACTS
                                                * android.permission.GET_CONTACTS
        -----------------------------------------------------------------------------------
        android.permission-group.LOCATION       * android.permission.ACCESS_FINE_LOCATION
                                                * android.permission.ACCESS_COARSE_LOCATION
        -----------------------------------------------------------------------------------
        android.permission-group.MICROPHONE     * android.permission.RECORD_AUDIO
        -----------------------------------------------------------------------------------
        android.permission-group.PHONE          * android.permission.READ_PHONE_STATE
                                                * android.permission.CALL_PHONE
                                                * android.permission.READ_CALL_LOG
                                                * android.permission.WRITE_CALL_LOG
                                                * com.android.voicemail.permission.ADD_VOICEMAIL
                                                * android.permission.USE_SIP
                                                * android.permission.PROCESS_OUTGOING_CALLS
        -----------------------------------------------------------------------------------
        android.permission-group.SENSORS        * android.permission.BODY_SENSORS
        -----------------------------------------------------------------------------------
        android.permission-group.SMS            * android.permission.SEND_SMS
                                                * android.permission.RECEIVE_SMS
                                                * android.permission.READ_SMS
                                                * android.permission.RECEIVE_WAP_PUSH
                                                * android.permission.RECEIVE_MMS
                                                * android.permission.READ_CELL_BROADCASTS
        -----------------------------------------------------------------------------------
        android.permission-group.STORAGE        * android.permission.READ_EXTERNAL_STORAGE
                                                * android.permission.WRITE_EXTERNAL_STORAGE
        -----------------------------------------------------------------------------------  */

    @TargetApi(23)
    public void checkPermissionsMandAbove() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkPermission()) {
                Toast.makeText(MainActivity.this, "All Permissions Granted", Toast.LENGTH_SHORT).show();
            } else {
                requestPermission();
            }
        } else {
            Toast.makeText(MainActivity.this, "Below M, permissions not via code", Toast.LENGTH_SHORT).show();
        }
    }

    @TargetApi(23)
    private void requestPermission() {
        ActivityCompat.requestPermissions(MainActivity.this, new String[]
                {
                        CAMERA,
                        READ_CONTACTS,
                        READ_PHONE_STATE,
                        WRITE_EXTERNAL_STORAGE,
                        ACCESS_FINE_LOCATION,
                        RECORD_AUDIO,
                        BODY_SENSORS,
                        READ_CALENDAR,
                        READ_SMS
                }, RequestPermissionCode);
    }

    @TargetApi(23)
    private boolean checkPermission() {
        int FirstPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
        int SecondPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), READ_CONTACTS);
        int ThirdPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), READ_PHONE_STATE);
        int FourthPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int FifthPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION);
        int SixthPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), RECORD_AUDIO);
        int SeventhPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), BODY_SENSORS);
        int EigthPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), READ_CALENDAR);
        int NinthPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), READ_SMS);
        return FirstPermissionResult == PackageManager.PERMISSION_GRANTED &&
                SecondPermissionResult == PackageManager.PERMISSION_GRANTED &&
                ThirdPermissionResult == PackageManager.PERMISSION_GRANTED &&
                FourthPermissionResult == PackageManager.PERMISSION_GRANTED &&
                FifthPermissionResult == PackageManager.PERMISSION_GRANTED &&
                SixthPermissionResult == PackageManager.PERMISSION_GRANTED &&
                SeventhPermissionResult == PackageManager.PERMISSION_GRANTED &&
                EigthPermissionResult == PackageManager.PERMISSION_GRANTED &&
                NinthPermissionResult == PackageManager.PERMISSION_GRANTED;
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RequestPermissionCode:
                if (grantResults.length > 0) {
                    boolean CameraPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean ReadContactsPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean ReadPhoneStatePermission = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                    boolean ReadStoragePermission = grantResults[3] == PackageManager.PERMISSION_GRANTED;
                    boolean ReadLocationPermission = grantResults[4] == PackageManager.PERMISSION_GRANTED;
                    boolean ReadMicrophonePermission = grantResults[5] == PackageManager.PERMISSION_GRANTED;
                    boolean ReadSensorsPermission = grantResults[6] == PackageManager.PERMISSION_GRANTED;
                    boolean ReadCalenderPermission = grantResults[7] == PackageManager.PERMISSION_GRANTED;
                    boolean ReadSMSPermission = grantResults[8] == PackageManager.PERMISSION_GRANTED;
                    if (CameraPermission && ReadContactsPermission && ReadPhoneStatePermission && ReadStoragePermission
                            && ReadLocationPermission && ReadMicrophonePermission && ReadSensorsPermission &&
                            ReadCalenderPermission && ReadSMSPermission) {
                        Toast.makeText(MainActivity.this, "All Permissions Granted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
}
