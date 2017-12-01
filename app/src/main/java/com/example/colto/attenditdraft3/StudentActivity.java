package com.example.colto.attenditdraft3;

import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.security.KeyStore;

import javax.crypto.KeyGenerator;

public class StudentActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView = null;
    Toolbar toolbar = null;
    String studentNameValue;
    StudentActivity studentActivity;

    //FingerPrint Stuff
    private static final String TAG =  StudentActivity.class.getSimpleName();

    private static final String DIALOG_FRAGMENT_TAG = "myFragment";
    private static final String SECRET_MESSAGE = "Very secret message";
    private static final String KEY_NAME_NOT_INVALIDATED = "key_not_invalidated";
    static final String DEFAULT_KEY_NAME = "default_key";

    private KeyStore mKeyStore;
    private KeyGenerator mKeyGenerator;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        studentActivity = this;
        studentNameValue = getIntent().getStringExtra("STUDENT_NAME");

        //..........................................................................//
        //..........................................................................//

//        try {
//            mKeyStore = KeyStore.getInstance("AndroidKeyStore");
//        } catch (KeyStoreException e) {
//            throw new RuntimeException("Failed to get an instance of KeyStore", e);
//        }
//        try {
//            mKeyGenerator = KeyGenerator
//                    .getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
//        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
//            throw new RuntimeException("Failed to get an instance of KeyGenerator", e);
//        }
//        Cipher defaultCipher;
//        Cipher cipherNotInvalidated;
//        try {
//            defaultCipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/"
//                    + KeyProperties.BLOCK_MODE_CBC + "/"
//                    + KeyProperties.ENCRYPTION_PADDING_PKCS7);
//            cipherNotInvalidated = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/"
//                    + KeyProperties.BLOCK_MODE_CBC + "/"
//                    + KeyProperties.ENCRYPTION_PADDING_PKCS7);
//        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
//            throw new RuntimeException("Failed to get an instance of Cipher", e);
//        }
//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//
//        KeyguardManager keyguardManager = getSystemService(KeyguardManager.class);
//        FingerprintManager fingerprintManager = getSystemService(FingerprintManager.class);
//        Button purchaseButton = (Button) findViewById(R.id.purchase_button);
//        Button purchaseButtonNotInvalidated = (Button) findViewById(
//                R.id.purchase_button_not_invalidated);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            purchaseButtonNotInvalidated.setEnabled(true);
//            purchaseButtonNotInvalidated.setOnClickListener(
//                    new PurchaseButtonClickListener(cipherNotInvalidated,
//                            KEY_NAME_NOT_INVALIDATED));
//        } else {
//            // Hide the purchase button which uses a non-invalidated key
//            // if the app doesn't work on Android N preview
//            purchaseButtonNotInvalidated.setVisibility(View.GONE);
//            findViewById(R.id.purchase_button_not_invalidated_description)
//                    .setVisibility(View.GONE);
//        }
//
//        if (!keyguardManager.isKeyguardSecure()) {
//            // Show a message that the user hasn't set up a fingerprint or lock screen.
//            Toast.makeText(getApplicationContext(),
//                    "Secure lock screen hasn't set up.\n"
//                            + "Go to 'Settings -> Security -> Fingerprint' to set up a fingerprint",
//                    Toast.LENGTH_LONG).show();
//            purchaseButton.setEnabled(false);
//            purchaseButtonNotInvalidated.setEnabled(false);
//            return;
//        }
//
//        // Now the protection level of USE_FINGERPRINT permission is normal instead of dangerous.
//        // See http://developer.android.com/reference/android/Manifest.permission.html#USE_FINGERPRINT
//        // The line below prevents the false positive inspection from Android Studio
//        // noinspection ResourceType
//        if (!fingerprintManager.hasEnrolledFingerprints()) {
//            purchaseButton.setEnabled(false);
//            // This happens when no fingerprints are registered.
//            Toast.makeText(getApplicationContext(),
//                    "Go to 'Settings -> Security -> Fingerprint' and register at least one fingerprint",
//                    Toast.LENGTH_LONG).show();
//            return;
//        }
//        createKey(DEFAULT_KEY_NAME, true);
//        createKey(KEY_NAME_NOT_INVALIDATED, false);
//        purchaseButton.setEnabled(true);
//        purchaseButton.setOnClickListener(
//                new PurchaseButtonClickListener(defaultCipher, DEFAULT_KEY_NAME));




        //..........................................................................//
        //..........................................................................//
        //..........................................................................//


        //Set the fragment init
        Bundle bundle = new Bundle();
        bundle.putString("STUDENT_NAME",studentNameValue);
        MyClassesFragmentForStudents fragment = new MyClassesFragmentForStudents();
        fragment.setArguments(bundle);
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.myDrawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }





    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.myDrawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_student_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.MyClasses) {
            Bundle bundle = new Bundle();
            bundle.putString("STUDENT_NAME",studentNameValue);
            MyClassesFragmentForStudents fragment = new MyClassesFragmentForStudents();
            fragment.setArguments(bundle);
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.joinAClass) {
            Bundle bundle = new Bundle();
            bundle.putString("STUDENT_NAME",studentNameValue);
            JoinAClassFragment fragment = new JoinAClassFragment();
            fragment.setArguments(bundle);
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.myRecords) {
            Bundle bundle = new Bundle();
            bundle.putString("USER_NAME",studentNameValue);
            MyRecordsStudentFragment fragment = new MyRecordsStudentFragment();
            fragment.setArguments(bundle);
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.logOut) {
            LogOutFragment fragment = new LogOutFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.myDrawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    //..........................................................................//
//..........................................................................//

//    private boolean initCipher(Cipher cipher, String keyName) {
//        try {
//            mKeyStore.load(null);
//            SecretKey key = (SecretKey) mKeyStore.getKey(keyName, null);
//            cipher.init(Cipher.ENCRYPT_MODE, key);
//            return true;
//        } catch (KeyPermanentlyInvalidatedException e) {
//            return false;
//        } catch (KeyStoreException | CertificateException | UnrecoverableKeyException | IOException
//                | NoSuchAlgorithmException | InvalidKeyException e) {
//            throw new RuntimeException("Failed to init Cipher", e);
//        }
//    }
//
//
//    public void onPurchased(boolean withFingerprint,
//                            @Nullable FingerprintManager.CryptoObject cryptoObject) {
//        if (withFingerprint) {
//            // If the user has authenticated with fingerprint, verify that using cryptography and
//            // then show the confirmation message.
//            assert cryptoObject != null;
//            tryEncrypt(cryptoObject.getCipher());
//        } else {
//            // Authentication happened with backup password. Just show the confirmation message.
//            showConfirmation(null);
//        }
//    }
//
//    // Show confirmation, if fingerprint was used show crypto information.
//    private void showConfirmation(byte[] encrypted) {
//        findViewById(R.id.confirmation_message).setVisibility(View.VISIBLE);
//        if (encrypted != null) {
//            TextView v = (TextView) findViewById(R.id.encrypted_message);
//            v.setVisibility(View.VISIBLE);
//            v.setText(Base64.encodeToString(encrypted, 0 /* flags */));
//        }
//    }
//
//    /**
//     * Tries to encrypt some data with the generated key in {@link #createKey} which is
//     * only works if the user has just authenticated via fingerprint.
//     */
//    private void tryEncrypt(Cipher cipher) {
//        try {
//            byte[] encrypted = cipher.doFinal(SECRET_MESSAGE.getBytes());
//            showConfirmation(encrypted);
//        } catch (BadPaddingException | IllegalBlockSizeException e) {
//            Toast.makeText(getApplicationContext(), "Failed to encrypt the data with the generated key. "
//                    + "Retry the purchase", Toast.LENGTH_LONG).show();
//            Log.e(TAG, "Failed to encrypt the data with the generated key." + e.getMessage());
//        }
//    }
//
//    public void createKey(String keyName, boolean invalidatedByBiometricEnrollment) {
//        // The enrolling flow for fingerprint. This is where you ask the user to set up fingerprint
//        // for your flow. Use of keys is necessary if you need to know if the set of
//        // enrolled fingerprints has changed.
//        try {
//            mKeyStore.load(null);
//            // Set the alias of the entry in Android KeyStore where the key will appear
//            // and the constrains (purposes) in the constructor of the Builder
//
//            KeyGenParameterSpec.Builder builder = new KeyGenParameterSpec.Builder(keyName,
//                    KeyProperties.PURPOSE_ENCRYPT |
//                            KeyProperties.PURPOSE_DECRYPT)
//                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
//                    // Require the user to authenticate with a fingerprint to authorize every use
//                    // of the key
//                    .setUserAuthenticationRequired(true)
//                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7);
//
//            // This is a workaround to avoid crashes on devices whose API level is < 24
//            // because KeyGenParameterSpec.Builder#setInvalidatedByBiometricEnrollment is only
//            // visible on API level +24.
//            // Ideally there should be a compat library for KeyGenParameterSpec.Builder but
//            // which isn't available yet.
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                builder.setInvalidatedByBiometricEnrollment(invalidatedByBiometricEnrollment);
//            }
//            mKeyGenerator.init(builder.build());
//            mKeyGenerator.generateKey();
//        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException
//                | CertificateException | IOException e) {
//            throw new RuntimeException(e);
//        }
//    }




    //..........................................................................//
    //..........................................................................//
//..........................................................................//





}
