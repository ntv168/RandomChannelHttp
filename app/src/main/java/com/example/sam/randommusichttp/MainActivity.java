package com.example.sam.randommusichttp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.sam.randommusichttp.http.WebServer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private WebServer mWebServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Start Server
        final int port = 9090;
        mWebServer = new WebServer(port,MainActivity.this);

        (new Thread(mWebServer)).start();
        Toast.makeText(this, "Server Start", Toast.LENGTH_SHORT).show();

        // Check for the camera permission before accessing the camera.  If the
        // permission is not granted yet, request permission.

    }

}
