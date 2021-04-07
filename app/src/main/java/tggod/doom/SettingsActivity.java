package tggod.doom;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static android.os.Environment.DIRECTORY_DOWNLOADS;
import static eu.chainfire.libsuperuser.Shell.SU.run;

public class SettingsActivity extends AppCompatActivity {








    // for getting file name and url from firebase




    private Button button_qestgl;
    private Button button_qestkr;
    private Button button_datagl;
    private Button button_datakr;
    private Button d_new;
    private Button d_old;

    long downloadID;

    private BroadcastReceiver onDownloadComplete = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //Fetching the download id received with the broadcast
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            //Checking if the received broadcast is for our enqueued download by matching download id
            if (downloadID == id) {
                Toast.makeText(SettingsActivity.this, "Download Completed", Toast.LENGTH_SHORT).show();
                unpackZip("/storage/emulated/0/Android/data/tggod.doom/files/Download/", "files.zip");
            }
        }
    };

    FirebaseFirestore firebaseFirestore;

    FirebaseStorage firebaseStorage;
    StorageReference storageReference, ref;


    // for getting file name and url from firebase

    public void download_shells(){
        storageReference = FirebaseStorage.getInstance().getReference();
        ref = storageReference.child("files").child("files.zip");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                try {
                    ProgressDialog progressDialog = new ProgressDialog(SettingsActivity.this);// Setting Title
                    progressDialog.setMax(100);// Fetching max value
                    progressDialog.setTitle("");// Setting Message
                    progressDialog.setMessage("Loading...");// Progress Dialog Style Horizontal
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL); // Progress Dialog Style Horizontal
                    progressDialog.show(); // Display Progress Dialog
                    progressDialog.setCancelable(true);
                    progressDialog.dismiss();
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }



                String url = uri.toString();
                download_files(SettingsActivity.this, "files", ".zip", DIRECTORY_DOWNLOADS,url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SettingsActivity.this, "فشل التحميل حاول مره اخرى لاحقا", Toast.LENGTH_LONG).show();
            }
        });
    }

    // for downloading file

    public void download_files(Context context, String filename, String fileExtention, String destinationDirectory, String url){

        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);

        Uri uri = Uri.parse(url);

        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);
        request.setDestinationInExternalFilesDir(context , destinationDirectory, filename + fileExtention);

        downloadID = downloadManager.enqueue(request);

    }

    private boolean unpackZip(String path, String zipname)
    {
        InputStream is;
        ZipInputStream zis;
        try
        {
            is = new FileInputStream(path + zipname);
            zis = new ZipInputStream(new BufferedInputStream(is));
            ZipEntry ze;

            while((ze = zis.getNextEntry()) != null)

            {

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[6*1024];
                int count;

                String filename = ze.getName();
                FileOutputStream fout = new FileOutputStream(path + filename);

                // reading and writing
                while((count = zis.read(buffer)) != -1)
                {
                    baos.write(buffer, 0, count);
                    byte[] bytes = baos.toByteArray();
                    fout.write(bytes);
                    baos.reset();
                }

                fout.close();
                zis.closeEntry();
            }

            zis.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(onDownloadComplete);
    }



    @SuppressLint("ResourceType")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        registerReceiver(onDownloadComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        firebaseFirestore = FirebaseFirestore.getInstance();
        button_qestgl = (Button) findViewById(R.id.button_qestgl);
        button_qestkr = (Button) findViewById(R.id.button_qestkr);
        button_datagl = (Button) findViewById(R.id.button_datagl);
        button_datakr = (Button) findViewById(R.id.button_datakr);
        d_new = (Button) findViewById(R.id.d_new);
        d_old = (Button) findViewById(R.id.d_old);
        registerReceiver(onDownloadComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        firebaseFirestore = FirebaseFirestore.getInstance();

        button_qestgl.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Process exec = Runtime.getRuntime().exec("su");
                    OutputStream outputStream = exec.getOutputStream();
                    exec.getErrorStream();
                    exec.getInputStream();
                    //noinspection deprecation
                    run("GUEST=\"/data/data/com.tencent.ig/shared_prefs/device_id.xml\"\n" +
                            "kill com.tencent.ig\n" +
                            "rm -rf $GUEST\n" +
                            "echo \"<?xml version='1.0' encoding='utf-8' standalone='yes' ?>\n" +
                            "<map>\n" +
                            "    <string name=\\\"random\\\"></string>\n" +
                            "    <string name=\\\"install\\\"></string>\n" +
                            "    <string name=\\\"uuid\\\">$RANDOM$RANDOM-$RANDOM-$RANDOM-$RANDOM-$RANDOM$RANDOM$RANDOM</string>\n" +
                            "</map>\" > $GUEST\n" +
                            "rm -rf /data/data/com.tencent.ig/databases\n" +
                            "rm -rf /data/media/0/Android/data/com.tencent.ig/files/login-identifier.txt");
                    outputStream.flush();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }

                Toast.makeText(SettingsActivity.this, "DONE", Toast.LENGTH_LONG).show();
            }
        });

        button_qestkr.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Process exec = Runtime.getRuntime().exec("su");
                    OutputStream outputStream = exec.getOutputStream();
                    exec.getErrorStream();
                    exec.getInputStream();
                    //noinspection deprecation
                    run("GUEST=\"/data/data/com.pubg.krmobile/shared_prefs/device_id.xml\"\n" +
                            "kill com.pubg.krmobile\n" +
                            "rm -rf $GUEST\n" +
                            "echo \"<?xml version='1.0' encoding='utf-8' standalone='yes' ?>\n" +
                            "<map>\n" +
                            "    <string name=\\\"random\\\"></string>\n" +
                            "    <string name=\\\"install\\\"></string>\n" +
                            "    <string name=\\\"uuid\\\">$RANDOM$RANDOM-$RANDOM-$RANDOM-$RANDOM-$RANDOM$RANDOM$RANDOM</string>\n" +
                            "</map>\" > $GUEST\n" +
                            "rm -rf /data/data/com.pubg.krmobile/databases\n" +
                            "rm -rf /data/media/0/Android/data/com.pubg.krmobile/files/login-identifier.txt");
                    outputStream.flush();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }

                Toast.makeText(SettingsActivity.this, "DONE", Toast.LENGTH_LONG).show();
            }
        });

        button_datagl.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Process exec = Runtime.getRuntime().exec("su");
                    OutputStream outputStream = exec.getOutputStream();
                    exec.getErrorStream();
                    exec.getInputStream();
                    //noinspection deprecation
                    run("rm -rf /data/media/0/Android/data/com.tencent.ig");
                    outputStream.flush();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }

                Toast.makeText(SettingsActivity.this, "DONE", Toast.LENGTH_LONG).show();
            }
        });

        button_datakr.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                try {
                    Process exec = Runtime.getRuntime().exec("su");
                    OutputStream outputStream = exec.getOutputStream();
                    exec.getErrorStream();
                    exec.getInputStream();
                    //noinspection deprecation
                    run("rm -rf /data/media/0/Android/data/com.pubgkr.mobile");
                    outputStream.flush();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }

                Toast.makeText(SettingsActivity.this, "DONE", Toast.LENGTH_LONG).show();
            }
        });

        d_new.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingsActivity.this, "DOWNLOAD", Toast.LENGTH_LONG).show();
                download_shells();

            }
        });


        d_old.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                try {
                    Process exec = Runtime.getRuntime().exec("su");
                    OutputStream outputStream = exec.getOutputStream();
                    exec.getErrorStream();
                    exec.getInputStream();
                    //noinspection deprecation
                    run("rm -rf /data/media/0/Android/data/tggod.doom");
                    outputStream.flush();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }

                Toast.makeText(SettingsActivity.this, "DONE", Toast.LENGTH_LONG).show();
            }
        });

    }

        public BroadcastReceiver getOnDownloadComplete() {
            return onDownloadComplete;
        }

        public void setOnDownloadComplete(BroadcastReceiver onDownloadComplete) {
            this.onDownloadComplete = onDownloadComplete;
        }

    }

