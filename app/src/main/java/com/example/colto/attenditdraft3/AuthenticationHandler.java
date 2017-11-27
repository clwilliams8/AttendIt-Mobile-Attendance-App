package com.example.colto.attenditdraft3;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.widget.Toast;

/**
 * Created by colto on 11/26/2017.
 */

public class AuthenticationHandler extends FingerprintManager.AuthenticationCallback {

    private Context sActivity;

    public AuthenticationHandler(Context studentActivity) {
        this.sActivity = studentActivity;
    }
    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        super.onAuthenticationError(errorCode, errString);
        Toast.makeText(sActivity, "Authentication Error" + errString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
        super.onAuthenticationHelp(helpCode, helpString);
        Toast.makeText(sActivity, "Authentication Help!" + helpString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        super.onAuthenticationSucceeded(result);
        Toast.makeText(sActivity, "Authentication Succeeded!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAuthenticationFailed() {
        super.onAuthenticationFailed();
        Toast.makeText(sActivity, "Authentication Failed", Toast.LENGTH_SHORT).show();
    }
}
