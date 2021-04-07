package tggod.doom;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;

import org.apache.commons.io.FileUtils;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class LogCleaner extends AppCompatActivity {

    public int ShouldStop;
    private Button down;

    public TextView footer;

    public Button start;
    private Button stop;
    String time;
    private Button vhost;

    @SuppressLint("WrongConstant")
    public void onBackPressed() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.setFlags(67108864);
        startActivity(intent);
    }


    public void onCreate(Bundle bundle) {
        Date date;
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_log_cleaner);
        this.start = (Button) findViewById(R.id.button_sta);
        this.stop = (Button) findViewById(R.id.button_sto);
        this.down = (Button) findViewById(R.id.hoch);
        this.footer = (TextView) findViewById(R.id.textView6);
        this.vhost = (Button) findViewById(R.id.vhosts);
        this.start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LogCleaner.this.start.setText("Log Cleaner Started");
                LogCleaner.this.start.setEnabled(false);
                int unused = LogCleaner.this.ShouldStop = 1;
                LogCleaner.this.cleaner();
            }
        });
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String format = simpleDateFormat.format(instance.getTime());
        this.time = getSharedPreferences("Login", 0).getString("ohShit", format);
        try {
            date = simpleDateFormat.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        instance.setTime(date);
        try {
            instance2.setTime(simpleDateFormat.parse(this.time));
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
        long timeInMillis = instance.getTimeInMillis();
        Integer.parseInt("111");
        new CountDownTimer(instance2.getTimeInMillis() - timeInMillis, 1000) {
            public void onTick(long j) {
                LogCleaner.this.footer.setText(String.format("Subscription Expires in %02d:%02d:%02d", new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toDays(j)),
                        Long.valueOf(TimeUnit.MILLISECONDS.toHours(j) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(j))),
                        Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(j) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(j))),
                        Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(j)))}));
                try {
                    FileUtils.writeStringToFile(new File("/storage/emulated/0/Android/data/.PPatcher/time.txt"), String.valueOf(TimeUnit.MILLISECONDS.toMinutes(j)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @SuppressLint("SetTextI18n")
            public void onFinish() {
                LogCleaner.this.footer.setText("Subscription Expired!");
                FirebaseAuth.getInstance().signOut();
                LogCleaner.this.finish();
            }
        }.start();
        this.stop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LogCleaner.this.ShouldStop = 0;
                LogCleaner.this.finishAndRemoveTask();
                System.exit(0);
            }
        });
        this.footer.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            public void onClick(View view) {
                Toast.makeText(LogCleaner.this, "Telegram Username misho44", 1).show();
                LogCleaner.this.footer.setText("Made mishaal");
            }
        });
        this.down.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FirebaseStorage.getInstance().getReferenceFromUrl("gs://doom-dae96.appspot.com").child("Host.txt").getFile(new File(new File("/storage/emulated/0"), "Host.txt")).addOnSuccessListener((OnSuccessListener) new OnSuccessListener() {
                    @SuppressLint("WrongConstant")
                    public void onSuccess(Object obj) {
                        Toast.makeText(LogCleaner.this, "Download Finished", 1).show();
                    }
                }).addOnFailureListener((OnFailureListener) new OnFailureListener() {
                    @SuppressLint("WrongConstant")
                    public void onFailure(Exception exc) {
                        Toast.makeText(LogCleaner.this, "Download Failed", 1).show();
                    }
                });
            }
        });
        this.vhost.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LogCleaner.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://apk-dl.com/dl/com.github.xfalcon.vhosts")));
            }
        });
    }


    public void cleaner() {
        if (this.ShouldStop == 1) {
            try {
                DataOutputStream dataOutputStream = new DataOutputStream(Runtime.getRuntime().exec("su").getOutputStream());
                dataOutputStream.writeBytes("sh /storage/emulated/0/Android/data/.PPatcher/alog.sh");
                dataOutputStream.flush();
                dataOutputStream.writeBytes("exit\n");
                dataOutputStream.flush();
            } catch (Exception unused) {
            }
        }
    }
}
