package tggod.doom;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TestingDeviceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_device);

        final Intent intent = getIntent();

        String user_id = intent.getStringExtra("UserId");

        FirebaseDatabase fd = FirebaseDatabase.getInstance();

        TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {


            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
       final String imei = getDeviceId();

        final DatabaseReference databaseReference = fd.getReference(user_id);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String conf_imei = dataSnapshot.getValue(String.class);

                if (conf_imei == null) {
                    Toast.makeText(TestingDeviceActivity.this, "تم التحقق", Toast.LENGTH_LONG).show();
                    databaseReference.setValue(imei);
                    Intent intent2 = new Intent(TestingDeviceActivity.this, PPatcher.class);
                    startActivity(intent2);
                    finish();
                }
                if (conf_imei.equals(imei)) {
                    Toast.makeText(TestingDeviceActivity.this, "تم التحقق", Toast.LENGTH_LONG).show();
                    Intent intent1 = new Intent(TestingDeviceActivity.this, PPatcher.class);
                    startActivity(intent1);
                    finish();
                } else {
                    Toast.makeText(TestingDeviceActivity.this, "نأسف , لايمكن تشغيل اليوزر على اكثر من جهاز", Toast.LENGTH_LONG).show();
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    /*
        Fix Added By @RehmanGFx
     */
    @SuppressLint("HardwareIds")
    public String getDeviceId() {

        String deviceId;

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            deviceId = Settings.Secure.getString(
                    getContentResolver(),
                    Settings.Secure.ANDROID_ID);
            Log.e("Your-ID-Secure",deviceId);
        } else {
            final TelephonyManager mTelephony = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return "";
            }
            if (mTelephony.getDeviceId() != null) {
                deviceId = mTelephony.getDeviceId();
                Log.e("Your-ID-IMEI",deviceId);

            } else {
                deviceId = Settings.Secure.getString(
                        getContentResolver(),
                        Settings.Secure.ANDROID_ID);
                Log.e("Your-ID-Secure",deviceId);

            }
        }

        return deviceId;
    }
}
