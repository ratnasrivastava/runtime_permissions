package com.example.runtimepermission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int STORAGE_PERMISSION_CODE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE);
    }

    private void checkPermission(String writeExternalStorage, int storagePermissionCode) {
        if(ContextCompat.checkSelfPermission(MainActivity.this, writeExternalStorage) == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, new String[]{writeExternalStorage},storagePermissionCode);
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
    }
}