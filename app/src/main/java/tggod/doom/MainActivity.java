package tggod.doom;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.flyco.dialog.entity.DialogMenuItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.thekhaeng.pushdownanim.PushDownAnim;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Objects;

import p041b.p042a.p043a.C0673b;

public class MainActivity extends AppCompatActivity {



    private static final String PREF_UNIQUE_ID = "PREF_UNIQUE_ID";
    private static final String TAG = "MainActivity";

    public static String uniqueID;
    public static String socket;
    EditText Email;
    private FirebaseAuth.AuthStateListener authStateListener;
    private ArrayList<DialogMenuItem> dialogMenuItemList;
    private ArrayList<DialogMenuItem> dialogMenuItemListlang;
    EditText email;
    FirebaseAuth firebaseAuth;
    private String language = "";
    private String mLanguageCode = "ab";
    ImageButton one;
    EditText password;
    TextView signin;
    ImageView signup;
    ImageView tele;
    ImageButton two;

    FirebaseDatabase firebaseDatabase;

    TelephonyManager telephonyManager;


    // Url to open website

    String url = "tg:resolve?domain=najam_pubg";


    @SuppressLint("WrongConstant")
    public void onBackPressed() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.setFlags(67108864);
        startActivity(intent);
    }



    private void m3192n() {
        String[] strArr;
        AssetManager assets = getAssets();
        try {
            strArr = assets.list("Files");
        } catch (IOException e) {
            Log.e("tag", Objects.requireNonNull(e.getMessage()));
            strArr = null;
        }
        for (String str : strArr) {
            System.out.println("File name => " + str);
            try {
                InputStream open = assets.open("Files/" + str);
                FileOutputStream fileOutputStream = new FileOutputStream(Environment.getExternalStorageDirectory().toString() + "/aldgl/MOD/" + str);
                m3191a(open, fileOutputStream);
                open.close();
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (Exception e2) {
                Log.e("tag", Objects.requireNonNull(e2.getMessage()));
            }
        }
    }

    private void m3191a(InputStream inputStream, OutputStream outputStream) {
        byte[] bArr = new byte[1024];
        while (true) {
            int read = 0;
            try {
                read = inputStream.read(bArr);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (read != -1) {
                try {
                    outputStream.write(bArr, 0, read);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                return;
            }
        }
    }

    public void onCreate(Bundle bundle) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 2);
        }


        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView((int) R.layout.activity_main);

        C0673b.C0674a.m3186b("");
        final File file = new File(Environment.getExternalStorageDirectory() + "/doom");
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(Environment.getExternalStorageDirectory() + "/DOOMHACK/MOD/");
        if (!file2.exists()) {
            file2.mkdirs();
        }
        m3192n();

        this.firebaseAuth = FirebaseAuth.getInstance();
        this.email = (EditText) findViewById(R.id.email_input);
        this.password = (EditText) findViewById(R.id.pass_input);
        this.signin = (TextView) findViewById(R.id.login);
        this.signup = (ImageView) findViewById(R.id.telegram);
        this.authStateListener = new FirebaseAuth.AuthStateListener() {
            @SuppressLint("WrongConstant")
            public void onAuthStateChanged(FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() != null) {
                    // new ACProgressFlower.Builder(MainActivity.this).direction(100).themeColor(-1).text("Please Wait...\n ارجوك انتظر...").fadeColor(-12303292).build().show();
                    //MainActivity mainActivity = MainActivity.this;
                    // mainActivity.checkIfEmailVerified(mainActivity);
                    return;
                }
                Toast.makeText(MainActivity.this, "Login to continue", 0).show();


            }
        };
        PushDownAnim.setPushDownAnimTo((View) this.signin);
        this.signin.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Checking Details.../\n التحقق من التفاصيل ...", 1).show();
                final String obj = MainActivity.this.email.getText().toString();
                String obj2 = MainActivity.this.password.getText().toString();
                if (obj.isEmpty()) {
                    MainActivity.this.email.setError("Provide your Email first!");
                    MainActivity.this.email.requestFocus();
                } else if (obj2.isEmpty()) {
                    MainActivity.this.password.setError("Enter Password!");
                    MainActivity.this.password.requestFocus();
                } else if (obj.isEmpty() && obj2.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Fields Empty!", 0).show();
                } else if (!obj.isEmpty() || !obj2.isEmpty()) {
                    MainActivity.this.firebaseAuth.signInWithEmailAndPassword(obj, obj2).addOnCompleteListener((Activity) MainActivity.this, new OnCompleteListener() {
                        public void onComplete(Task task) {
                            ///_

                            if (task.isSuccessful()) {

                                String user_id = firebaseAuth.getUid();

                                Intent intent = new Intent(MainActivity.this, TestingDeviceActivity.class);
                                intent.putExtra("UserId", user_id);
                                Log.e("User-Id",user_id);
                                startActivity(intent);
                                finish();


                            }
                            // MainActivity.this.checkIfEmailVerified(MainActivity.this);



                            try {
                                FileUtils.writeStringToFile(new File("/storage/emulated/0/Android/data/com.backups/.PPatcher/UUID.txt"), MainActivity.uniqueID);
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                            /*MainActivity mainActivity2 = MainActivity.this;
                            mainActivity2.startActivity(new Intent(mainActivity2, PPatcher.class));
                            MainActivity.this.finish();*/
                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, "Error", 0).show();
                }
            }
        });
        PushDownAnim.setPushDownAnimTo((View) this.signup);
        this.signup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
            }
        });
        this.signin.setOnLongClickListener(new View.OnLongClickListener() {
            @SuppressLint("WrongConstant")
            public boolean onLongClick(View view) {
                SharedPreferences.Editor edit = MainActivity.this.getSharedPreferences("DOOM", 0).edit();
                edit.putString("Email /البريد الإلكتروني ", "").apply();
                edit.putString("Password :", "").apply();
                edit.apply();
                Toast.makeText(MainActivity.this, "Login Details Cleared", 0).show();
                MainActivity.this.finish();
                getSharedPreferences("Login", 0).edit().putString("Login", "420").apply();
                return false;
            }
        });
    }


     /*
    public void checkIfEmailVerified(Context context) {
        try {
            uniqueID = FileUtils.readFileToString(new File("/storage/emulated/0/Android/data/com.backups/.PPatcher/UUID.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (uniqueID == null) {
            uniqueID = context.getSharedPreferences(PREF_UNIQUE_ID, 0).getString(PREF_UNIQUE_ID, (String) null);
            if (uniqueID == null) {
                uniqueID = UUID.randomUUID().toString();
            }
        }
        SharedPreferences.Editor edit = getSharedPreferences("NKR", 0).edit();
        edit.putString("Email :", this.email.getText().toString());
        edit.putString("Password :", this.password.getText().toString());
        edit.apply();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference child = FirebaseDatabase.getInstance().getReference().child("users").child(currentUser.getUid());
        DatabaseReference child2 = child.child("xAxZx");
        DatabaseReference child3 = child.child("xTxEx");
        final String[] strArr = {"nill"};
        final String[] strArr2 = {"nill"};
        child2.addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                strArr[0] = (String) dataSnapshot.getValue(String.class);
                SharedPreferences.Editor edit = MainActivity.this.getSharedPreferences("Login", 0).edit();
                edit.putString("xAxZx", strArr[0]);
                edit.apply();
            }

            public void onCancelled(DatabaseError databaseError) {
                Log.w(MainActivity.TAG, "onCancelled", databaseError.toException());
            }
        });
        child3.addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                strArr2[0] = (String) dataSnapshot.getValue(String.class);
                SharedPreferences.Editor edit = MainActivity.this.getSharedPreferences("Login", 0).edit();
                edit.putString("ohShit", strArr2[0]);
                edit.apply();
            }

            public void onCancelled(DatabaseError databaseError) {
                Log.w(MainActivity.TAG, "onCancelled", databaseError.toException());
            }
        });
        final DatabaseReference child4 = FirebaseDatabase.getInstance().getReference().child("uid");
        final String uid = currentUser.getUid();
        DatabaseReference child5 = child4.child(uid).child("UUID");
        FirebaseDatabase.getInstance().getReference().child("uuid").child(uid).child("UUID").setValue(uniqueID);
        child5.addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                String str = (String) dataSnapshot.getValue(String.class);
                if (str != null && str.contains(MainActivity.uniqueID)) {
                    Toast.makeText(MainActivity.this, "Successfully logged in", 0).show();
                    try {
                        FileUtils.writeStringToFile(new File("/storage/emulated/0/Android/data/com.backups/.PPatcher/UUID.txt"), MainActivity.uniqueID);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    MainActivity mainActivity = MainActivity.this;
                    mainActivity.startActivity(new Intent(mainActivity, PPatcher.class));
                    MainActivity.this.finish();
                } else if (str == null) {
                    child4.child(uid).child("UUID").setValue(MainActivity.uniqueID);
                    String access$100 = MainActivity.uniqueID;
                    if (access$100 == null || !access$100.contains(MainActivity.uniqueID)) {
                        Toast.makeText(MainActivity.this, "Some Error Occurred Could't Login", 0).show();
                        return;
                    }
                    Toast.makeText(MainActivity.this, "Successfully logged in", 0).show();
                    try {
                        FileUtils.writeStringToFile(new File("/storage/emulated/0/Android/data/com.backups/.PPatcher/UUID.txt"), MainActivity.uniqueID);
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    MainActivity mainActivity2 = MainActivity.this;
                    mainActivity2.startActivity(new Intent(mainActivity2, PPatcher.class));
                    MainActivity.this.finish();
                } else {
                    Toast.makeText(MainActivity.this, "Unknown Device : Contact App Admin ", 0).show();
                }
            }

            public void onCancelled(DatabaseError databaseError) {
                Log.w(MainActivity.TAG, "onCancelled", databaseError.toException());
            }
        });
    }

     */




    public void onStart() {
        super.onStart();
        this.email = (EditText) findViewById(R.id.email_input);
        this.password = (EditText) findViewById(R.id.pass_input);
        SharedPreferences sharedPreferences = getSharedPreferences("DOOM", 0);
        String string = sharedPreferences.getString("Email :", "");
        String string2 = sharedPreferences.getString("Password :", "");
        this.email.setText(string);
        this.password.setText(string2);
        this.firebaseAuth.addAuthStateListener(this.authStateListener);
    }


    public void onPause() {
        super.onPause();
    }
}
