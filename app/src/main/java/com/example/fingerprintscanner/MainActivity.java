package com.example.fingerprintscanner;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView textView1,textView2;
ImageView imageView;
FingerprintManager fingerprintManager;
KeyguardManager keyguardManager;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = findViewById(R.id.text1);
        textView2 = findViewById(R.id.text2);
        imageView = findViewById(R.id.img);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
            keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

            if (!fingerprintManager.isHardwareDetected()) {
                textView2.setText("FingerPrint Scanner Not Detected ");
            } else if (ContextCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                textView2.setText("Permission not Granted to use FingerPrint Scanner");
            } else if (!keyguardManager.isKeyguardSecure()) {
                textView2.setText("Add lock to your Phone in Setting");

            } else if (!fingerprintManager.hasEnrolledFingerprints()) {
                textView2.setText("Add atleast One FingerPrint to use this Feature");
            } else {
                textView2.setText("Place Your Finger On the Scanner to Access the App");
                Fingerprint fingerprint=new Fingerprint(this);
                fingerprint.setAuth(fingerprintManager,null);
            }
        }

    }


}
