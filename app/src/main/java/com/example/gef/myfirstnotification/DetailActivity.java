package com.example.gef.myfirstnotification;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    private static final int PERMISSION_CAMERA = 1234;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);
    }

    public void onClickBoutonAutorisation(View view)
    {
        int permission = ContextCompat.checkSelfPermission(this , Manifest.permission.CAMERA);
        if(permission != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA))
            {
                Toast.makeText(this,"la caméra est nécessaire au fonctionnement de l'application",Toast.LENGTH_LONG).show();
            }

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},PERMISSION_CAMERA);
        }
        else {
            Toast.makeText(this,"Access Granted!",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == PERMISSION_CAMERA){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this , "Permission caméra granted",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this ,"Permission caméré denid", Toast.LENGTH_LONG).show();
            }
        }
    }
}
