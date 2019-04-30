package com.example.fingerprintscanner;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.M)
public class Fingerprint extends FingerprintManager.AuthenticationCallback {
Context context;

    public Fingerprint(Context context) {
        this.context = context;
    }

    public void setAuth(FingerprintManager fingerprintManager,FingerprintManager.CryptoObject cryptoObject)
    {
        CancellationSignal cancellationSignal=new CancellationSignal();
        fingerprintManager.authenticate(cryptoObject,cancellationSignal,0,this,null);
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        this.update("An Auth Error. "+errString,false);
    }

    @Override
    public void onAuthenticationFailed() {
        this.update("Wrong Finger Print",false);
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
        this.update("Error. "+helpString,false);
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        this.update("You can Access The Application",true);
    }

    private void update(String s, boolean b) {
        TextView textView2=(TextView)((Activity)context).findViewById(R.id.text2);
        ImageView imageView=(ImageView) ((Activity)context).findViewById(R.id.img);
        textView2.setText(s);

        if(b==false)
        {
            textView2.setTextColor(ContextCompat.getColor(context,R.color.colorAccent));

        }
        else
        {
            textView2.setTextColor(ContextCompat.getColor(context,R.color.colorPrimary));
            imageView.setImageResource(R.mipmap.done1);


        }


    }


}
