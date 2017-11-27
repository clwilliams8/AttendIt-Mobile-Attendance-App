package com.example.colto.attenditdraft3;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.preference.PreferenceManager;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * Created by colto on 11/19/2017.
 */

public class MyClassesStudentAdaptor extends RecyclerView.Adapter<MyClassesStudentAdaptor.UserViewHolder>  {

    private List<MyClassesModel> list;
    private StudentActivity studentActivity;
    private String KEY_NAME = "somekeyname";

    public MyClassesStudentAdaptor(List<MyClassesModel> list) {
        this.list = list;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item3, parent, false));
    }

    @Override
    public void onBindViewHolder(final UserViewHolder holder, int position) {

        MyClassesModel myClass = list.get(position);

        holder.itemClassName.setText(myClass.className);
        holder.itemClassTimes.setText(myClass.classTimes);
        holder.itemClassDaysPerWeek.setText(myClass.classDays);

        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.add(holder.getAdapterPosition(), 0, 0, "test?");
                menu.add(holder.getAdapterPosition(), 1, 0, "test2?");

            }
        });


    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    class  UserViewHolder extends RecyclerView.ViewHolder {

        TextView itemClassName, itemClassTimes, itemClassDaysPerWeek;
        Button signInButton;


        public UserViewHolder(final View itemView) {
            super(itemView);

            itemClassName = (TextView) itemView.findViewById(R.id.itemClassNameForStudent);
            itemClassTimes = (TextView) itemView.findViewById(R.id.itemClassTimesForStudent);
            itemClassDaysPerWeek = (TextView) itemView.findViewById(R.id.itemClassDaysPerWeekForStudent);
            signInButton = (Button) itemView.findViewById(R.id.SignInButtonForStudents);


            //BUTTON NOT CLICKABLE UNTIL TIME, DAY, AND LOCATION ARE CORRECT
            signInButton.setClickable(false);
            boolean dayCorrect = false;
            Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_WEEK);

            switch (day) {
                case Calendar.SUNDAY:
                    // Current day is Sunday
                    if(itemClassDaysPerWeek.getText().toString() == "Sunday")
                        dayCorrect = true;
                    break;
                case Calendar.MONDAY:
                    // Current day is Monday
                    if(itemClassDaysPerWeek.getText().toString() == "Monday")
                        dayCorrect = true;
                    break;
                case Calendar.TUESDAY:
                    // etc.
                    if(itemClassDaysPerWeek.getText().toString() == "Tuesday")
                        dayCorrect = true;
                    break;
                case Calendar.WEDNESDAY:
                    if(itemClassDaysPerWeek.getText().toString() == "Wednesday")
                        dayCorrect = true;
                    break;
                case Calendar.THURSDAY:
                    if(itemClassDaysPerWeek.getText().toString() == "Thursday")
                        dayCorrect = true;
                    break;
                case Calendar.FRIDAY:
                    if(itemClassDaysPerWeek.getText().toString() == "Friday")
                        dayCorrect = true;
                    break;
                case Calendar.SATURDAY:
                    if(itemClassDaysPerWeek.getText().toString() == "Saturday")
                        dayCorrect = true;
                    break;
            }





            //check conditions... then toast

            //Toast.makeText(itemView.getContext(), "You may sign into " + itemClassName.getText().toString(), Toast.LENGTH_SHORT).show();

            signInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //ONCLICK - FINGERPRINT AUTH.


//                    KeyguardManager keyguardManager;
//                    keyguardManager = (KeyguardManager) itemView.getContext().getSystemService(Context.KEYGUARD_SERVICE);
//                    FingerprintManager fingerprintManager;
//                    fingerprintManager = (FingerprintManager) itemView.getContext().getSystemService(Context.FINGERPRINT_SERVICE);
//
//
//
//                    //Create Keystore object
//                    KeyStore keyStore;
//
//                    try {
//                            keyStore = KeyStore.getInstance("AndroidKeyStore");
//                    } catch (Exception e) {
//                            Log.e("Keystore", e.getMessage());
//                            return;
//                    }
//
//                    //Create KeyGen object
//                    KeyGenerator keyGenerator;
//
//                    try {
//                            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
//                    } catch (Exception e) {
//                        Log.e("KeyGenerator", e.getMessage());
//                        return;
//                    }
//
//
//                    try {
//                        keyStore.load(null);
//                        KeyGenParameterSpec.Builder builder = new KeyGenParameterSpec.Builder(KEY_NAME,
//                                KeyProperties.PURPOSE_ENCRYPT |
//                                        KeyProperties.PURPOSE_DECRYPT)
//                                .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
//                                // Require the user to authenticate with a fingerprint to authorize every use
//                                // of the key
//                                .setUserAuthenticationRequired(true)
//                                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7);
//
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                            builder.setInvalidatedByBiometricEnrollment(false);
//                        }
//                        keyGenerator.init(builder.build());
//                        keyGenerator.generateKey();
//                        Toast.makeText(itemView.getContext(), "Place your finger on your phone's scanner.", Toast.LENGTH_LONG).show();
//
//                    } catch (Exception e) {
//                        Log.e("Generating keys", e.getMessage());
//                        return;
//                    } //End KeyGen Object
//
//
//
//                    //Create Cipher object
//                    Cipher cipher;
//
//                    try {
//                            cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES
//                                    + "/" + KeyProperties.BLOCK_MODE_CBC
//                                    + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);
//                    } catch (Exception e) {
//                        Log.e("Cipher", e.getMessage());
//                        return;
//                    } //End Cipher object
//
//                    try {
//                            keyStore.load(null);
//                            SecretKey key =  (SecretKey) keyStore.getKey(KEY_NAME, null);
//                            cipher.init(Cipher.ENCRYPT_MODE, key);
//                    } catch (Exception e) {
//                        Log.e("Secret key", e.getMessage());
//                        return;
//                    }// End Cipher Object
//
//
//                    FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);
//
//                    CancellationSignal cancellationSignal = new CancellationSignal();
//
//                    fingerprintManager.authenticate(cryptoObject, cancellationSignal, 0 ,new AuthenticationHandler(itemView.getContext()), null);








                    //ON AUTH - CREATE RECORD IF DOESN'T EXIST, UPDATE RECORD WITH PRESENT,LATE,ABSENT MARK





                }
            });

        }

    }
}
