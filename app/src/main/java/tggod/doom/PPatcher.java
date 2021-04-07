package tggod.doom;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.thekhaeng.pushdownanim.PushDownAnim;
import com.xw.repo.BubbleSeekBar;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static android.os.Environment.DIRECTORY_DOWNLOADS;
import static eu.chainfire.libsuperuser.Shell.SU.run;
import static java.lang.Integer.parseInt;


@SuppressWarnings("CallToPrintStackTrace")
public class PPatcher extends AppCompatActivity {

    FloatingActionButton mAddFab, mAddAlarmFab, mAddPersonFab;


    TextView addAlarmActionText, addPersonActionText;

    Boolean isAllFabsVisible;
    public static String state = "";
    private TextView Email;
    String aaaaaaa = "Made mishaal";
    private Button act;
    private FirebaseAuth.AuthStateListener authStateListener;
    private Button clog;
    private Button deact;
    BubbleSeekBar delayer;
    private ImageView e;
    private ImageView f;
    private ImageView ff;
    FirebaseAuth firebaseAuth;
    private Button fixx;
    private TextView footer;
    private Button gf;
    String hehe = "";
    private Button kf;
    private Button logout;
    private FirebaseAuth mAuth;
    private LinearLayout ma;
    private ScrollView main;
    private Button minfix;

    public RadioButton newmethod;
    private ImageView o;
    private RadioButton oldmethod;
    private Button pkorea;
    private Button ppubg;
    private Button prekoo;
    private Button pvng;
    private ImageView r;
    private ImageView s;
    private LinearLayout set;
    private ScrollView sett;
    private Button sf;
    private ImageView ss;
    private ImageView t;
    private Button tf;
    String time;

    public TextView timer;
    private ImageView tt;
    private FirebaseUser user;
    private Button vf;
    VideoView vidback;

    ImageView settings_btn;

    long downloadID;




   

    FirebaseFirestore firebaseFirestore;

    FirebaseStorage firebaseStorage;
    StorageReference storageReference, ref;

    @SuppressLint("WrongConstant")
    public void onBackPressed() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.setFlags(67108864);
        startActivity(intent);
    }


    



   


    




    @SuppressLint("MissingSuperCall")
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        final Date[] date = new Date[1];
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);

        setContentView(R.layout.activity_ppatcher);

        mAddFab = findViewById(R.id.add_fab);
        // FAB button
        mAddAlarmFab = findViewById(R.id.add_alarm_fab);
        mAddPersonFab = findViewById(R.id.add_person_fab);

        this.addAlarmActionText = findViewById(R.id.add_alarm_action_text);
        this.addPersonActionText = findViewById(R.id.add_person_action_text);

        mAddAlarmFab.setVisibility(View.GONE);
        mAddPersonFab.setVisibility(View.GONE);
        addAlarmActionText.setVisibility(View.GONE);
        addPersonActionText.setVisibility(View.GONE);

        isAllFabsVisible = false;
        this.firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        this.mAuth = FirebaseAuth.getInstance();
        this.user = this.mAuth.getCurrentUser();
        this.footer = (TextView) findViewById(R.id.textView6);
        this.Email = (TextView) findViewById(R.id.profileEmail);
        TextView textView = (TextView) findViewById(R.id.profileEmail);
        this.timer = (TextView) findViewById(R.id.textView6);
        this.logout = (Button) findViewById(R.id.button_logout);
        this.act = (Button) findViewById(R.id.button_on);
        this.clog = (Button) findViewById(R.id.button_clear);
        this.ppubg = (Button) findViewById(R.id.pubgG);
        this.pvng = (Button) findViewById(R.id.pubg_vng);
        this.pkorea = (Button) findViewById(R.id.pubg_korean);
        this.prekoo = (Button) findViewById(R.id.pubg_rekoo);
        this.deact = (Button) findViewById(R.id.button_off);
        this.oldmethod = (RadioButton) findViewById(R.id.oldmethod);
        this.newmethod = (RadioButton) findViewById(R.id.newmethod);
        this.delayer = (BubbleSeekBar) findViewById(R.id.pubgDelayBubbleSeekBar);



        // settings page opener

       /* mAddFab = (FloatingActionButton) findViewById(R.id.settings_btn);
        mAddFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PPatcher.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
*/




        mAddFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!isAllFabsVisible) {

                            mAddAlarmFab.show();
                            mAddPersonFab.show();


                            isAllFabsVisible = true;
                        } else {


                            mAddAlarmFab.hide();
                            mAddPersonFab.hide();
                            addAlarmActionText.setVisibility(View.GONE);
                            addPersonActionText.setVisibility(View.GONE);

                            isAllFabsVisible = false;
                        }
                    }
                });









        mAddPersonFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(PPatcher.this,MainActivity2.class);
                        startActivity(intent);
                        Toast.makeText(PPatcher.this, "الرادار", Toast.LENGTH_SHORT).show();

                    }
                });




        mAddAlarmFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(PPatcher.this, SettingsActivity.class);
                        startActivity(intent);
                        Toast.makeText(PPatcher.this, "الاعدادات", Toast.LENGTH_SHORT).show();
                    }
                });





        this.oldmethod.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (((RadioButton) view).isChecked()) {
                    PPatcher.this.newmethod.setChecked(false);
                    PPatcher.this.hehe = "";
                }
            }
        });
        this.act.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PPatcher.this.executeOption("activate");
            }
        });
        this.deact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PPatcher.this.executeOption("deactivate");
            }
        });
        this.clog.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PPatcher.this.executeOption("clear");
            }
        });



       
        this.authStateListener = new FirebaseAuth.AuthStateListener() {
            @SuppressLint("WrongConstant")
            public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    if (Build.VERSION.SDK_INT >= 23) {
                        PPatcher.this.requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 2);
                    }
                    if (Build.VERSION.SDK_INT >= 23) {
                        PPatcher.this.requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 3);
                        return;
                    }
                    return;
                }
                Toast.makeText(PPatcher.this, "Login Failed... ", 0).show();
                PPatcher.this.startActivity(new Intent(PPatcher.this, MainActivity.class));
            }
        };
        this.firebaseAuth.addAuthStateListener(this.authStateListener);

         
        PushDownAnim.setPushDownAnimTo((View) this.logout);
        this.logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                PPatcher.this.startActivity(new Intent(PPatcher.this, MainActivity.class));
            }
        });
        PushDownAnim.setPushDownAnimTo((View) this.clog);
        this.clog.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PPatcher.this.executeOption("clear");
            }
        });
        PushDownAnim.setPushDownAnimTo((View) this.ppubg);
        this.ppubg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PPatcher.state = "Global";
                view.startAnimation(AnimationUtils.loadAnimation(PPatcher.this.getApplicationContext(), R.anim.shake));
            }
        });
        PushDownAnim.setPushDownAnimTo((View) this.ppubg);
        this.pvng.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PPatcher.state = "Viet";
                view.startAnimation(AnimationUtils.loadAnimation(PPatcher.this.getApplicationContext(), R.anim.shake));
            }
        });
        PushDownAnim.setPushDownAnimTo((View) this.pvng);
        this.pkorea.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PPatcher.state = "Korea";
                view.startAnimation(AnimationUtils.loadAnimation(PPatcher.this.getApplicationContext(), R.anim.shake));
            }
        });
        PushDownAnim.setPushDownAnimTo((View) this.pkorea);
        this.timer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            }
        });
        PushDownAnim.setPushDownAnimTo((View) this.timer);
        this.prekoo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PPatcher.state = "Prekoo";
            }
        });
        PushDownAnim.setPushDownAnimTo((View) this.prekoo);
        final Calendar instance = Calendar.getInstance();
        final Calendar instance2 = Calendar.getInstance();
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        final String format = simpleDateFormat.format(instance.getTime());

        if (this.user != null) {

            SharedPreferences sharedPreferences = getSharedPreferences("login", 0);
            String string = sharedPreferences.getString("xAxZx", "...");

            this.time = sharedPreferences.getString("ohShit", format);
//.       getting time expired from firebase
            try {
                Log.d("_________", user.getUid());
                DocumentReference docRef = firebaseFirestore.collection("Vip").document(user.getUid());
                ///DocumentReference docRef = firebaseFirestore.collection("Members").document(user.getEmail().replace(".",","));
                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot document) {
                        if (document != null) {
                            if (document.exists()) {
                                time = document.getString("Time");
                                if (time != null) {
                                    Log.d("______TIME", time);
                                } else {
                                    Log.d("______TIME_NULL", "TIME null");
                                }
                                Log.d("______", "DocumentSnapshot data: " + document.getData());
                            }
                        } else {
                            Log.d("______", "Document null");
                        }
                    }
                });
                /*
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document != null) {
                                if (document.exists()) {
                                    time = document.getString("Time");
                                    if (time != null) {
                                        Log.d("______TIME", time);
                                    } else {
                                        Log.d("______TIME_NULL", "TIME null");
                                    }
                                    Log.d("______", "DocumentSnapshot data: " + document.getData());
                                }
                            } else {
                                Log.d("______", "Document null");
                            }
                        } else {
                            Log.d("______", "get failed with ", task.getException());
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("_______FAILURE", "addOnFailureListener: " + e.toString());
                    }
                });

                 */
            } catch (Exception ex) {
                ex.printStackTrace();
            }


            ///user.getEmail().replace(".",","));
            Log.d("____________", time);

            String email = this.user.getEmail();


            ///_
            /*
            this.time = "11/11/2025";
            String email = "emailron@gmail.com";

             */

            TextView textView2 = this.Email;
            textView2.setText("Email:" + email);
            textView.setText("Name: " + string);
        }
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    date[0] = simpleDateFormat.parse(format);
                } catch (ParseException e2) {
                    e2.printStackTrace();
                    date[0] = null;
                }
                instance.setTime(date[0]);
                try {
                    instance2.setTime(simpleDateFormat.parse(time));
                } catch (ParseException e3) {
                    e3.printStackTrace();
                }
                instance.getTimeInMillis();
                instance2.getTimeInMillis();
                long j = 0;
                try {
                    j = simpleDateFormat.parse(time).getTime() - new Date().getTime();
                } catch (ParseException e4) {
                    e4.printStackTrace();
                }
                parseInt("111");
                new CountDownTimer(j, 1000) {
                    public void onTick(long j) {
                        String format = String.format("%02d يوم: %02d ساعه: %02d دقيقه: %02d ثانيه", new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toDays(j)),
                                Long.valueOf(TimeUnit.MILLISECONDS.toHours(j) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(j))),
                                Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(j) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(j))),
                                Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(j)))});
                        TextView access$300 = PPatcher.this.timer;
                        access$300.setText("" + format + " للانتهاء");
                        String.valueOf(TimeUnit.MILLISECONDS.toMinutes(j));
                    }

                    public void onFinish() {
                        PPatcher.this.timer.setText("انتهى الاشتراك");
                        //TODO:
                        PPatcher.this.finish();
                    }
                }.start();
            }
        }, 2500);
    }

    public void permission() {
        if (Build.VERSION.SDK_INT >= 23 && !Settings.canDrawOverlays(this)) {
            startActivityForResult(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + getPackageName())), 69);
        }
    }



    private void delay() {
        this.delayer = (BubbleSeekBar) findViewById(R.id.pubgDelayBubbleSeekBar);
        int intValue = Integer.valueOf(this.delayer.getProgress()).intValue();
        if (!new File("/sdcard/Android/data/com.google.android.youtube/.PPatcher/delay.sh").exists()) {
            try {
                File file = new File("/sdcard/Android/data/com.google.android.youtube/.PPatcher/delay.sh");
                FileUtils.writeStringToFile(file, "sleep " + intValue + "s");
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        run("sh /sdcard/Android/data/com.google.android.youtube/.PPatcher/delay.sh");
    }


    public void onStart() {
        super.onStart();
        if (getPackageManager().getLaunchIntentForPackage("com.tencent.ig") == null) {
            this.ppubg.setAlpha(0.2f);
        }
        if (getPackageManager().getLaunchIntentForPackage("com.vng.pubgmobile") == null) {
            this.pvng.setAlpha(0.2f);
        }
        if (getPackageManager().getLaunchIntentForPackage("com.pubg.krmobile") == null) {
            this.pkorea.setAlpha(0.2f);
        }
        if (getPackageManager().getLaunchIntentForPackage("com.rekoo.pubgm") == null) {
            this.prekoo.setAlpha(0.2f);
        }
    }

    public void clearlogs() {
        try {
            Process exec = Runtime.getRuntime().exec("su");
            OutputStream outputStream = exec.getOutputStream();
            InputStream errorStream = exec.getErrorStream();
            InputStream inputStream = exec.getInputStream();
            outputStream.write(Integer.parseInt("rm -rf /data/data/com.tencent.ig/shared_prefs/MidasOverseaIP.xml > /dev/null 2>&1\n" +
                    "rm -rf /data/data/com.tencent.ig/shared_prefs/HSJsonData.xml > /dev/null 2>&1\n" +
                    "rm -rf /data/data/com.tencent.ig/shared_prefs/gsdk_prefs.xml > /dev/null 2>&1\n" +
                    "rm -rf /data/data/com.tencent.ig/shared_prefs/crashrecord.xml > /dev/null 2>&1\n" +
                    "rm -rf /data/data/com.tencent.ig/shared_prefs/BuglySdkInfos.xml > /dev/null 2>&1\n" +
                    "rm -rf /data/data/com.tencent.ig/shared_prefs/com.google.android.gms.appid.xml > /dev/null 2>&1\n" +
                    "rm -rf /cache/magisk.log &> /dev/null\n" +
                    "rm -rf /cache/magisk.log.bak &> /dev/null\n" +
                    "sleep 10\n".getBytes()));
            outputStream.flush();
            outputStream.close();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String str = "";
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                str = str + readLine + IOUtils.LINE_SEPARATOR_UNIX;
            }
            bufferedReader.close();
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(errorStream));
            while (bufferedReader2.readLine() != null) {
            }
            bufferedReader2.close();
            exec.waitFor();
            exec.destroy();
        } catch (Exception unused) {
        }
    }

    @SuppressLint({"WrongConstant", "ShowToast"})
    private void executeOption(String string0) {
        int i1;
        int i = -1;
        switch (string0.hashCode()) {
            case 1524762248: {
                if (!string0.equals("10_min_fix")) {
                    i1 = -1;
                    break;
                }

                i1 = 3;
                break;
            }
            case -1996763020: {
                if (!string0.equals("deactivate")) {
                    i1 = -1;
                    break;
                }

                i1 = 1;
                break;
            }
            case -1655974669: {
                if (!string0.equals("activate")) {
                    i1 = -1;
                    break;
                }

                i1 = 0;
                break;
            }
            case 94746189: {
                if (string0.equals("clear")) {
                    i1 = 2;
                    break;
                }

                i1 = -1;
                break;
            }
            default: {
                i1 = -1;
                break;
            }
        }

        if (i1 == 0) {
            String string4 = PPatcher.state;
            switch (string4.hashCode()) {
                case 2666178: {
                    if (string4.equals("Viet")) {
                        i = 2;
                    }

                    break;
                }
                case 72683658: {
                    if (string4.equals("Korea")) {
                        i = 3;
                    }

                    break;
                }
                case 2135814083: {
                    if (string4.equals("Global")) {
                        i = 1;
                    }

                    break;
                }
                case -1896238552: {
                    if (string4.equals("Prekoo")) {
                        i = 0;
                    }

                    break;
                }
                default: {
                    break;
                }
            }

            if(i == 0) {
                this.on_taiwan();
                Toast.makeText(this.getApplicationContext(), "الرجاء الانتظار ...", 0).show();
            }
            else if(i == 1) {
                this.on_global();
                Toast.makeText(this.getApplicationContext(), "الرجاء الانتظار ...", 0).show();
            }
            else if(i == 2) {
                this.on_vietnam();
                Toast.makeText(this.getApplicationContext(), "الرجاء الانتظار ...", 0).show();
            }
            else if(i == 3) {
                this.on_korean();
                Toast.makeText(this.getApplicationContext(), "الرجاء الانتظار ...", 0).show();
            }
            else {
                Toast.makeText(this.getApplicationContext(), "قم في اختيار النسخه", 0).show();
            }
        }
        else if(i1 == 1) {
            String string3 = PPatcher.state;
            switch(string3.hashCode()) {
                case 2135814083: {
                    if(string3.equals("Global")) {
                        i = 1;
                    }

                    break;
                }
                case -1896238552: {
                    if (string3.equals("Prekoo")) {
                        i = 0;
                    }

                    break;
                }
                case 2666178: {
                    if (string3.equals("Viet")) {
                        i = 2;
                    }

                    break;
                }
                case 72683658: {
                    if (string3.equals("Korea")) {
                        i = 3;
                    }

                    break;
                }
                default: {
                    break;
                }
            }

            if (i == 0) {
                this.off_taiwan();
                Toast.makeText(this.getApplicationContext(), "تم الغاء التفعيل", 0).show();
            } else if (i == 1) {
                this.off_global();
                Toast.makeText(this.getApplicationContext(), "تم الغاء التفعيل", 0).show();
            } else if (i == 2) {
                this.off_vietnam();
                Toast.makeText(this.getApplicationContext(), "تم الغاء التفعيل", 0).show();
            } else if (i == 3) {
                this.off_korean();
                Toast.makeText(this.getApplicationContext(), "تم الغاء التفعيل", 0).show();
            } else {
                Toast.makeText(this.getApplicationContext(), "قم في اختيار النسخه", 0).show();
            }
        } else if (i1 == 2) {
            String string2 = PPatcher.state;
            switch (string2.hashCode()) {
                case -1896238552: {
                    if (string2.equals("Prekoo")) {
                        i = 0;
                    }

                    break;
                }
                case 2666178: {
                    if (string2.equals("Viet")) {
                        i = 2;
                    }

                    break;
                }
                case 72683658: {
                    if (string2.equals("Korea")) {
                        i = 3;
                    }

                    break;
                }
                case 2135814083: {
                    if (string2.equals("Global")) {
                        i = 1;
                    }

                    break;
                }
                default: {
                    break;
                }
            }

            if(i == 0) {
                this.clearlogs();
                Toast.makeText(this.getApplicationContext(), "تم مسح سجل اللعب", 0).show();
            }
            else if(i == 1) {
                this.clearlogs();
                Toast.makeText(this.getApplicationContext(), "تم مسح سجل اللعب", 0).show();
            }
            else if(i == 2) {
                this.clearlogs();
                Toast.makeText(this.getApplicationContext(), "تم مسح سجل اللعب", 0).show();
            }
            else if(i == 3) {
                this.clearlogs();
                Toast.makeText(this.getApplicationContext(), "تم مسح سجل اللعب", 0).show();
            }
            else {
                Toast.makeText(this.getApplicationContext(), "قم في اختيار النسخه", 0).show();
            }
        }

    }



    private void min_korean() {
        try {
            Process exec = Runtime.getRuntime().exec("su");
            OutputStream outputStream = exec.getOutputStream();
            exec.getErrorStream();
            exec.getInputStream();
            //noinspection deprecation
            run("");
            outputStream.flush();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private void min_taiwan() {
        try {
            Process exec = Runtime.getRuntime().exec("su");
            OutputStream outputStream = exec.getOutputStream();
            exec.getErrorStream();
            exec.getInputStream();
            //noinspection deprecation
            run("");
            outputStream.flush();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private void min_vietnam() {
        try {
            Process exec = Runtime.getRuntime().exec("su");
            OutputStream outputStream = exec.getOutputStream();
            exec.getErrorStream();
            exec.getInputStream();
            //noinspection deprecation
            run("");
            outputStream.flush();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private void off_global() {
        try {
            Process exec = Runtime.getRuntime().exec("su");
            OutputStream outputStream = exec.getOutputStream();
            exec.getErrorStream();
            exec.getInputStream();
            //noinspection deprecation
            run("sh /storage/emulated/0/Android/data/tggod.doom/files/Download/off_global.sh");
            outputStream.flush();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private void off_korean() {
        try {
            Process exec = Runtime.getRuntime().exec("su");
            OutputStream outputStream = exec.getOutputStream();
            exec.getErrorStream();
            exec.getInputStream();
            //noinspection deprecation
            run("sh /storage/emulated/0/Android/data/tggod.doom/files/Download/off_korean.sh");
            outputStream.flush();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private void off_taiwan() {
        try {
            Process exec = Runtime.getRuntime().exec("su");
            OutputStream outputStream = exec.getOutputStream();
            exec.getErrorStream();
            exec.getInputStream();
            //noinspection deprecation
            run("sh /storage/emulated/0/Android/data/tggod.doom/files/Download/off_taiwan.sh");
            outputStream.flush();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private void off_vietnam() {
        try {
            Process exec = Runtime.getRuntime().exec("su");
            OutputStream outputStream = exec.getOutputStream();
            exec.getErrorStream();
            exec.getInputStream();
            //noinspection deprecation
            run("sh /storage/emulated/0/Android/data/tggod.doom/files/Download/off_vietnam.sh");
            outputStream.flush();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private void on_vietnam() {
        try {
            Process exec = Runtime.getRuntime().exec("su");
            OutputStream outputStream = exec.getOutputStream();
            exec.getErrorStream();
            exec.getInputStream();
            //noinspection deprecation
            run("sh /storage/emulated/0/Android/data/tggod.doom/files/Download/on_vietnam.sh");
            outputStream.flush();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private void on_taiwan() {
        try {
            Process exec = Runtime.getRuntime().exec("su");
            OutputStream outputStream = exec.getOutputStream();
            exec.getErrorStream();
            exec.getInputStream();
            //noinspection deprecation
            run("sh /storage/emulated/0/Android/data/tggod.doom/files/Download/on_taiwan.sh");
            outputStream.flush();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private void on_korean() {
        try {
            Process exec = Runtime.getRuntime().exec("su");
            OutputStream outputStream = exec.getOutputStream();
            exec.getErrorStream();
            exec.getInputStream();
            //noinspection deprecation
            run("sh /storage/emulated/0/Android/data/tggod.doom/files/Download/on_korean.sh");
            outputStream.flush();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }


    private void on_global() {
        try {
            Process exec = Runtime.getRuntime().exec("su");
            OutputStream outputStream = exec.getOutputStream();
            exec.getErrorStream();
            exec.getInputStream();
            //noinspection deprecation
            run("sh /storage/emulated/0/Android/data/tggod.doom/files/Download/on_global.sh");
            outputStream.flush();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }


}
