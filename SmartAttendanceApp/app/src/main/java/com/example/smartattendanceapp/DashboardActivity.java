package com.example.smartattendanceapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DashboardActivity extends AppCompatActivity {
    private static final String TAG = "Lifecycle-Dashboard";
    private static final String PREF = "attendance_prefs";
    private static final String KEY_IN  = "check_in_time";
    private static final String KEY_OUT = "check_out_time";
    private static final String KEY_DATE = "today_date";

    private TextView tvFullname, tvDate, tvCheckInCell, tvCheckOutCell, tvCheckInTime, tvCheckOutTime;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_dashboard);

        tvFullname     = findViewById(R.id.tvFullname);
        tvDate         = findViewById(R.id.tvDate);
        tvCheckInCell  = findViewById(R.id.tvCheckInCell);
        tvCheckOutCell = findViewById(R.id.tvCheckOutCell);

        MaterialButton btnIn  = findViewById(R.id.btnCheckIn);
        MaterialButton btnOut = findViewById(R.id.btnCheckOut);
        TextView btnLogout    = findViewById(R.id.btnLogout);


        String name = getIntent().getStringExtra("fullname");
        if (name == null || name.isEmpty()) name = "Mahasiswa UPB";
        tvFullname.setText(name);

        SharedPreferences sp = getSharedPreferences(PREF, MODE_PRIVATE);


        String today = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        tvDate.setText(today);


        String lastDate = sp.getString(KEY_DATE, today);
        String inTime   = sp.getString(KEY_IN, "-");
        String outTime  = sp.getString(KEY_OUT, "-");

        if (!lastDate.equals(today)) { //
            inTime = "-"; outTime = "-";
            sp.edit().putString(KEY_DATE, today).putString(KEY_IN, "-").putString(KEY_OUT, "-").apply();
        }
        tvCheckInCell.setText(inTime);
        tvCheckOutCell.setText(outTime);

        btnIn.setOnClickListener(v -> {
            String now = timeNow();
            sp.edit().putString(KEY_IN, now).putString(KEY_DATE, today).apply();
            tvCheckInCell.setText(now);
            Toast.makeText(this, "Absen masuk berhasil direkam.", Toast.LENGTH_SHORT).show();
        });

        btnOut.setOnClickListener(v -> {
            String now = timeNow();
            sp.edit().putString(KEY_OUT, now).putString(KEY_DATE, today).apply();
            tvCheckOutCell.setText(now);
            Toast.makeText(this, "Absen keluar berhasil direkam.", Toast.LENGTH_SHORT).show();
        });

        btnLogout.setOnClickListener(v -> new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_info)
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin akan keluar dari aplikasi ini?")
                .setNegativeButton("Tidak", null)
                .setPositiveButton("Ya", (d, w) -> {
                    // kembali ke login
                    startActivity(new Intent(this, LoginActivity.class));
                    finish();
                }).show());
    }

    private String timeNow() {
        return new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
    }

    @Override protected void onStart(){ super.onStart(); Log.d(TAG,"onStart"); }
    @Override protected void onResume(){ super.onResume(); Log.d(TAG,"onResume"); }
    @Override protected void onPause(){ super.onPause(); Log.d(TAG,"onPause"); }
    @Override protected void onStop(){ super.onStop(); Log.d(TAG,"onStop"); }
    @Override protected void onDestroy(){ super.onDestroy(); Log.d(TAG,"onDestroy"); }
}
