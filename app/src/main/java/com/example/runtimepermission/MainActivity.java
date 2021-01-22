package com.example.runtimepermission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int STORAGE_PERMISSION_CODE = 100;
    private static final int GPS_PERMISSION_CODE = 101;
    private static final int GPS_BACKGROUND_PERMISSION_CODE = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE);
checkPermission(Manifest.permission.ACCESS_FINE_LOCATION, GPS_PERMISSION_CODE);
    }

    private void checkPermission(String permission, int permissionCode) {
        if(ContextCompat.checkSelfPermission(MainActivity.this, permission) == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, new String[]{permission},permissionCode);
        }
        else{
            Toast.makeText(MainActivity.this, "Permission already granted", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantedResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantedResults);
        if(requestCode == STORAGE_PERMISSION_CODE){
            if(grantedResults.length > 0 && grantedResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(MainActivity.this, "Storage permission is already granted", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(MainActivity.this, "Storage permission is denied by User", Toast.LENGTH_SHORT).show();
            }
        }
        else if(requestCode == GPS_PERMISSION_CODE){
            if(grantedResults.length > 0 && grantedResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(MainActivity.this, "GPS permission is already granted", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(MainActivity.this, "GPS permission is denied by User", Toast.LENGTH_SHORT).show();
            }
        }
    }
}