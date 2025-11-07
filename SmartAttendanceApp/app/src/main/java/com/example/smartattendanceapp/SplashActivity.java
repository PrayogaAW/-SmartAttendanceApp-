package com.example.smartattendanceapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "Lifecycle-Splash";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_splash);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        }, 3000);
    }

    @Override protected void onStart(){ super.onStart(); Log.d(TAG,"onStart"); }
    @Override protected void onResume(){ super.onResume(); Log.d(TAG,"onResume"); }
    @Override protected void onPause(){ super.onPause(); Log.d(TAG,"onPause"); }
    @Override protected void onStop(){ super.onStop(); Log.d(TAG,"onStop"); }
    @Override protected void onDestroy(){ super.onDestroy(); Log.d(TAG,"onDestroy"); }
}
