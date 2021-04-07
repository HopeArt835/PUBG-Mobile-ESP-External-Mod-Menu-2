package tggod.doom;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.lzyzsd.circleprogress.ArcProgress;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;

import java.io.File;

import ir.mahdi.mzip.zip.ZipArchive;

public class Download extends AppCompatActivity {
    private static final String TAG = "Download";

    public ArcProgress arcProgress;

    public TextView footer;

    public File localFile = null;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    public TextView DOOM;

    public File rootPath = null;

    public TextView size;
    double st = 0.0d;
    private FirebaseUser user;
    VideoView vidback;


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_download);
        getIntent();
        this.arcProgress = (ArcProgress) findViewById(R.id.arc_progress);
        this.footer = (TextView) findViewById(R.id.textView6);
        this.footer.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"SetTextI18n", "WrongConstant"})
            public void onClick(View view) {
                Toast.makeText(Download.this, "Telegram Username ", 1).show();
                Download.this.footer.setText("Made Mishaal");
            }
        });
        this.size = (TextView) findViewById(R.id.size);
        final FirebaseStorage instance = FirebaseStorage.getInstance();
        DatabaseReference child = FirebaseDatabase.getInstance().getReference().child("zip");
        this.mAuth = FirebaseAuth.getInstance();
        this.user = this.mAuth.getCurrentUser();
        this.DOOM = (TextView) findViewById(R.id.doom);
        String uid = this.user.getUid();
        ///this.DOOM.setText("Server : Offline");
        child.child(uid);
        DatabaseReference child2 = child.child("files");
        final DatabaseReference child3 = child.child("Pass");
        final String[] strArr = {"na"};
        final String[] strArr2 = {"na"};
        child2.addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                strArr[0] = (String) dataSnapshot.getValue(String.class);
                ///Download.this.DOOM.setText("Server : Online");
                StorageReference child = instance.getReferenceFromUrl("gs://doom-dae96.appspot.com").child(strArr[0]);
                Download download = Download.this;
                download.rootPath = new File(String.valueOf(download.getFilesDir()));
                if (!Download.this.rootPath.exists()) {
                    Download.this.rootPath.mkdirs();
                }
                Download download2 = Download.this;
                download2.localFile = new File(download2.rootPath, "files.zip");
                child.getFile(Download.this.localFile).addOnProgressListener((OnProgressListener) new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
                    public void onProgress(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        Download.this.st = (double) ((taskSnapshot.getBytesTransferred() * 100) / taskSnapshot.getTotalByteCount());
                        Download.this.arcProgress.setProgress((int) Download.this.st);
                        TextView access$500 = Download.this.size;
                        access$500.setText("Total Size " + (taskSnapshot.getTotalByteCount() / 1000000) + " MB");
                    }
                }).addOnSuccessListener((OnSuccessListener) new OnSuccessListener() {
                    @SuppressLint("WrongConstant")
                    public void onSuccess(Object obj) {
                        Toast.makeText(Download.this, "Download Finished", 1).show();
                        child3.addListenerForSingleValueEvent(new ValueEventListener() {
                            private DataSnapshot dataSnapshot;

                            @SuppressLint("WrongConstant")
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                this.dataSnapshot = dataSnapshot;
                                strArr2[0] = (String) dataSnapshot.getValue(String.class);
                                String valueOf = String.valueOf(Download.this.rootPath);
                                String valueOf2 = String.valueOf(Download.this.localFile);
                                new ZipArchive();
                                ZipArchive.unzip(valueOf2, valueOf, strArr2[0]);
                                if (Download.this.localFile.delete()) {
                                    Toast.makeText(Download.this, "Junk File deleted successfully", 1).show();
                                } else {
                                    Toast.makeText(Download.this, "Failed to delete the Junk file", 1).show();
                                }
                            }

                            public void onCancelled(DatabaseError databaseError) {
                                Toast.makeText(Download.this, "Error..", 1).show();
                            }
                        });
                        Download.this.finish();
                    }
                }).addOnFailureListener((OnFailureListener) new OnFailureListener() {
                    @SuppressLint("WrongConstant")
                    public void onFailure(Exception exc) {
                        Toast.makeText(Download.this, "Download Failed", 1).show();
                    }
                });
            }

            public void onCancelled(DatabaseError databaseError) {
                Log.w(Download.TAG, "onCancelled", databaseError.toException());
            }
        });
    }
}
