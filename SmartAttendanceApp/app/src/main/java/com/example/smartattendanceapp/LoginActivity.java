package com.example.smartattendanceapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "Lifecycle-Login";
    private TextInputEditText etUsername, etPassword;
    private MaterialButton btnLogin;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin   = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            String user = val(etUsername);
            String pass = val(etPassword);

            if (user.isEmpty()) { etUsername.setError("Username wajib diisi"); return; }
            if (pass.isEmpty()) { etPassword.setError("Password wajib diisi"); return; }

            // Kredensial demo UTS
            if (user.equals("RayWafa@gmail.com") && pass.equals("Arsenal")) {
                Intent i = new Intent(this, DashboardActivity.class);
                i.putExtra("fullname", "Prayoga Aenul Wafa");
                startActivity(i);
                finish();
            } else {
                Toast.makeText(this, "username atau kata sandi salah", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private String val(TextInputEditText e){ return e.getText()==null? "": e.getText().toString().trim(); }

    @Override protected void onStart(){ super.onStart(); Log.d(TAG,"onStart"); }
    @Override protected void onResume(){ super.onResume(); Log.d(TAG,"onResume"); }
    @Override protected void onPause(){ super.onPause(); Log.d(TAG,"onPause"); }
    @Override protected void onStop(){ super.onStop(); Log.d(TAG,"onStop"); }
    @Override protected void onDestroy(){ super.onDestroy(); Log.d(TAG,"onDestroy"); }
}
