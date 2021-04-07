package tggod.doom;

import android.annotation.SuppressLint;
import android.app.*;
import android.content.*;
import android.net.*;
import android.os.*;
import android.provider.*;
//import android.support.v7.app.*;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.view.*;
import android.view.View.*;
import android.widget.*;

import com.scottyab.rootbeer.Const;
import com.scottyab.rootbeer.RootBeer;
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
import androidx.appcompat.app.AppCompatActivity;
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
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import static java.lang.Integer.parseInt;

import java.io.*;
import java.lang.Process;


public class MainActivity2 extends AppCompatActivity implements OnClickListener {
	static boolean bitMod = false;
	static int gameType = 1;
	static boolean is32 = false;
	static boolean is64 = false;
	static boolean isIND = false;
	static boolean isInt = true;
	static boolean isNoroot = false;
	static boolean isRoot = false;
	static boolean rootMod = false;
	public static String socket;
	static boolean verMod = true;
	public String MemHack;
	int bit = 1;
	LinearLayout brand;
	TextView clc;
	Context ctx;
	public String daemonPath;
	public String daemonPath64;
	public String daemonPathIND;
	String gameName = "com.tencent.ig";
	LinearLayout gll;
	TextView glv;
	String indMEM;
	public boolean isDaemon = false;
	private boolean isDisplay = false;
	private boolean isMenuDis = false;
	public Button join;
	LinearLayout krl;
	TextView krv;
	LinearLayout logout;
	public Button mbutton1;
	public Button mbutton2;
	public Button mbutton3;
	public Button mbutton4;
	public Button mbutton5;
	public Button mbutton6;
	View menu;
	TextView msg;

	/* renamed from: mx */
	int f69mx;

	/* renamed from: my */
	int f70my;
	public boolean onESP = false;
	Process process;
	private RadioButton radiobutton1;
	private RadioButton radiobutton2;
	private RadioButton radiobutton3;
	private RadioButton radiobutton4;
	private RadioButton radiobutton5;
	private RadioButton radiobutton6;
	private RadioGroup radiogroup1;
	private RadioGroup radiogroup2;
	private RadioGroup radiogroup3;
	ImageView root_img;
	Button sell;
	Button start;
	Button stop;
	LinearLayout telegram;
	LinearLayout translate;
	LinearLayout twl;
	TextView twv;
	LinearLayout vtl;
	TextView vtv;
	int mx,my;

	static {
		System.loadLibrary("doom-lib");
	}

	public String daemonPathIND32;

	public String daemonPathIND64;
	@RequiresApi(api = Build.VERSION_CODES.M)
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView((int) R.layout.main);
		CheckFloatViewPermission();
		this.ctx = this;
		if (!isConfigExist()) {
			Init();
		}
		this.start = (Button) findViewById(R.id.start);
		this.stop = (Button) findViewById(R.id.stop);
		this.root_img = (ImageView) findViewById(R.id.root_img);
		this.brand = (LinearLayout) findViewById(R.id.brand_ll);
		this.telegram = (LinearLayout) findViewById(R.id.telegram_ll);
		this.translate = (LinearLayout) findViewById(R.id.translate_ll);
		this.logout = (LinearLayout) findViewById(R.id.logout_ll);
		this.sell = (Button) findViewById(R.id.sell);
		this.join = (Button) findViewById(R.id.join);
//        this.brand.setOnClickListener(this);
//        this.telegram.setOnClickListener(this);
//        this.translate.setOnClickListener(this);
//        this.logout.setOnClickListener(this);
//        this.sell.setOnClickListener(this);
		this.start.setOnClickListener(this);
		this.stop.setOnClickListener(this);
		this.gll = (LinearLayout) findViewById(R.id.sglih);
		this.krl = (LinearLayout) findViewById(R.id.skrih);
		this.twl = (LinearLayout) findViewById(R.id.stwih);
		this.vtl = (LinearLayout) findViewById(R.id.svnih);
		this.msg = (TextView) findViewById(R.id.card_text);
		this.glv = (TextView) findViewById(R.id.sgli);
		this.krv = (TextView) findViewById(R.id.skri);
		this.twv = (TextView) findViewById(R.id.stwi);
		this.vtv = (TextView) findViewById(R.id.svni);
		this.glv.setOnClickListener(this);
		this.krv.setOnClickListener(this);
		this.twv.setOnClickListener(this);
		this.vtv.setOnClickListener(this);
		//  this.msg.setOnClickListener(this);
//        this.join.setOnClickListener(this);
		this.radiogroup1 = (RadioGroup) findViewById(R.id.radioInd);
		this.radiogroup2 = (RadioGroup) findViewById(R.id.radioMod);
		this.radiogroup3 = (RadioGroup) findViewById(R.id.radioBit);
		this.radiobutton1 = (RadioButton) findViewById(R.id.radiobutton1);
		this.radiobutton2 = (RadioButton) findViewById(R.id.radiobutton2);
		this.radiobutton3 = (RadioButton) findViewById(R.id.radiobutton3);
		this.radiobutton4 = (RadioButton) findViewById(R.id.radiobutton4);
		this.radiobutton5 = (RadioButton) findViewById(R.id.radiobutton5);
		this.radiobutton6 = (RadioButton) findViewById(R.id.radiobutton6);
		try {
			this.process = Runtime.getRuntime().exec("su -c");
		} catch (IOException e) {
			e.printStackTrace();
			this.process = null;
		}
		this.radiogroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int i) {
				switch (i) {
					case R.id.radiobutton1 /*2131296732*/:
						MainActivity2.isIND = true;
						MainActivity2.isInt = false;
						MainActivity2.this.indMEM = "IND";
						MainActivity2.verMod = true;
						return;
					case R.id.radiobutton2 /*2131296733*/:
						MainActivity2.isInt = true;
						MainActivity2.isIND = false;
						MainActivity2.this.indMEM = "INTER";
						MainActivity2.verMod = true;
						return;
					default:
				}
			}
		});
		this.radiogroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int i) {
				switch (i) {
					case R.id.radiobutton3 /*2131296734*/:
						MainActivity2.isRoot = true;
						MainActivity2.this.ExecuteElf("su -c");
						MainActivity2.isNoroot = false;
						MainActivity2.rootMod = true;
						return;
					case R.id.radiobutton4 /*2131296735*/:
						MainActivity2.isNoroot = true;
						MainActivity2.isRoot = false;
						MainActivity2.rootMod = true;
						return;
					default:
				}
			}
		});
		this.radiogroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int i) {
				switch (i) {
					case R.id.radiobutton5 /*2131296736*/:
						MainActivity2.is32 = true;
						MainActivity2.is64 = false;
						MainActivity2.bitMod = true;
						return;
					case R.id.radiobutton6 /*2131296737*/:
						MainActivity2.is64 = true;
						MainActivity2.is32 = false;
						MainActivity2.bitMod = true;
						return;
					default:
				}
			}
		});

		ExecuteElf("su -c");

		loadAssets();
		loadAssets64();
		loadAssets99();
		//loadAssets991();
		loadAssets992();
		loadAssets993();

		socket = daemonPath;


	}


	@SuppressLint("WrongConstant")
	public void onClick(View view) {
		boolean z;
		switch (view.getId()) {
			case R.id.sgli /*2131296778*/:
				this.glv.setBackgroundResource(R.drawable.bg_border_green);
				this.krv.setBackgroundResource(R.drawable.bg_borderred);
				this.vtv.setBackgroundResource(R.drawable.bg_borderred);
				this.twv.setBackgroundResource(R.drawable.bg_borderred);
				this.gameName = "com.tencent.ig";
				gameType = 1;
				return;
			case R.id.skri /*2131296790*/:
				this.krv.setBackgroundResource(R.drawable.bg_border_green);
				this.glv.setBackgroundResource(R.drawable.bg_borderred);
				this.vtv.setBackgroundResource(R.drawable.bg_borderred);
				this.twv.setBackgroundResource(R.drawable.bg_borderred);
				this.gameName = "com.pubg.krmobile";
				gameType = 2;
				return;
			case R.id.start /*2131296812*/:
				boolean z2 = this.isDisplay;
				if (!z2 && !(z = this.isMenuDis)) {
					if (!rootMod || !verMod || !bitMod) {
						Toast.makeText(this, "Please Select Settings First !!", 1).show();
						return;
					} else {
						if (isNoroot) {
							if (is32) {
								if (isIND) {
									socket = this.daemonPathIND;
								} else {
									socket = this.daemonPath;
								}
							} else if (is64) {
								if (isIND) {
									socket = this.daemonPathIND;
								} else {
									socket = this.daemonPath64;
								}
							}
						}
						if (isRoot) {
							if (is32) {
								if (isIND) {
									socket = "su -c " + this.daemonPathIND;
								} else {
									socket = "su -c " + this.daemonPath;
								}
							} else if (is64) {
								if (isIND) {
									socket = "su -c " + this.daemonPathIND;
								} else {
									socket = "su -c " + this.daemonPath64;
								}
							}
						}
						try {
							DataOutputStream dataOutputStream = new DataOutputStream(Runtime.getRuntime().exec(Const.BINARY_SU).getOutputStream());
							dataOutputStream.writeBytes(" am start -n " + this.gameName + "/com.epicgames.ue4.SplashActivity \n");
							dataOutputStream.flush();
						} catch (IOException e) {
							e.printStackTrace();
						}
						this.start.setVisibility(8);
						this.stop.setVisibility(0);
						startPatcher();
						startService(new Intent(this, Overlay.class));
						this.isDisplay = true;
						this.isDaemon = true;
						return;
					}
				} else {
					return;
				}
			case R.id.stop /*2131296817*/:
				this.start.setVisibility(0);
				this.stop.setVisibility(8);
				if (this.isDisplay) {
					this.isDisplay = false;
					this.isMenuDis = false;
					this.isDaemon = false;
					stopService(new Intent(this, FloatLogo.class));
					stopService(new Intent(this, Overlay.class));
					return;
				}
				return;
			case R.id.stwi /*2131296819*/:
				this.twv.setBackgroundResource(R.drawable.bg_border_green);
				this.krv.setBackgroundResource(R.drawable.bg_borderred);
				this.vtv.setBackgroundResource(R.drawable.bg_borderred);
				this.glv.setBackgroundResource(R.drawable.bg_borderred);
				this.gameName = "com.rekoo.pubgm";
				gameType = 4;
				return;
			case R.id.svni /*2131296823*/:
				this.vtv.setBackgroundResource(R.drawable.bg_border_green);
				this.krv.setBackgroundResource(R.drawable.bg_borderred);
				this.glv.setBackgroundResource(R.drawable.bg_borderred);
				this.twv.setBackgroundResource(R.drawable.bg_borderred);
				this.gameName = "com.vng.pubgmobile";
				gameType = 3;
				return;
			default:
		}
	}

	@RequiresApi(api = Build.VERSION_CODES.M)
	public void CheckFloatViewPermission()
	{
		if (!Settings.canDrawOverlays(this))
		{
			Toast.makeText(this,"Enable Floating Permission ",Toast.LENGTH_LONG).show();
			startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())), 0);
		}
	}

	private boolean isServiceRunning() {
		ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		if (manager != null) {
			for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
				if (FloatLogo.class.getName().equals(service.service.getClassName())) {
					return true;
				}
			}
		}
		return false;
	}



	void startPatcher() {
		if (Build.VERSION.SDK_INT >= 23 && !Settings.canDrawOverlays(this)) {
			Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
			startActivityForResult(intent, 123);
		} else {
			startFloater();
		}
	}

	private void startFloater() {
		if (!isServiceRunning()) {
			startService(new Intent(this, FloatLogo.class));
		} else {
			Toast.makeText(this, "Service Already Running!", Toast.LENGTH_SHORT).show();
		}
	}

	private void ExecuteElf(String shell)
	{
		String s=shell;

		try
		{
			Runtime.getRuntime().exec(s,null,null);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	public void Shell(String str) {

		DataOutputStream dataOutputStream = null;
		try {
			process = Runtime.getRuntime().exec(str);
		} catch (IOException e) {
			e.printStackTrace();
			process = null;
		}
		if (process != null) {
			dataOutputStream = new DataOutputStream(process.getOutputStream());
		}
		try {
			dataOutputStream.flush();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		try {
			process.waitFor();
		} catch (InterruptedException e3) {
			e3.printStackTrace();
		}
	}

	public void startDaemon(){
		new Thread()
		{
			public void run()
			{
				Shell(socket);

			}
		}.start();
	}

	public void loadAssets()
	{
		String filepath =Environment.getExternalStorageDirectory()+"/Android/data/.tyb";
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filepath);
			byte[] buffer = "DO NOT DELETE".getBytes();
			fos.write(buffer, 0, buffer.length);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		String pathf = getFilesDir().toString()+"/sock";
		try
		{
			OutputStream myOutput = new FileOutputStream(pathf);
			byte[] buffer = new byte[1024];
			int length;
			InputStream myInput = getAssets().open("sock");
			while ((length = myInput.read(buffer)) > 0) {
				myOutput.write(buffer, 0, length);
			}
			myInput.close();
			myOutput.flush();
			myOutput.close();

		}

		catch (IOException e)
		{
		}


		daemonPath = getFilesDir().toString()+"/sock";


		try{
			Runtime.getRuntime().exec("chmod 777 "+daemonPath);
		}
		catch (IOException e)
		{
		}

	}
	public void loadAssets99()
	{

		String pathf = getFilesDir().toString()+"/libavutil.so";
		try
		{
			OutputStream myOutput = new FileOutputStream(pathf);
			byte[] buffer = new byte[1024];
			int length;
			InputStream myInput = getAssets().open("libavutil.so");
			while ((length = myInput.read(buffer)) > 0) {
				myOutput.write(buffer, 0, length);
			}
			myInput.close();
			myOutput.flush();
			myOutput.close();

		}

		catch (IOException e)
		{
		}
		daemonPath64 = getFilesDir().toString()+"/libavutil.so";


		try{
			Runtime.getRuntime().exec("chmod 777 /data/data/Com.loda.le/files/libavutil.so");
		}
		catch (IOException e)
		{
		}

	}

	public void loadAssets993()
	{

		String pathf = getFilesDir().toString()+"/libwrapper.so";
		try
		{
			OutputStream myOutput = new FileOutputStream(pathf);
			byte[] buffer = new byte[1024];
			int length;
			InputStream myInput = getAssets().open("libwrapper.so");
			while ((length = myInput.read(buffer)) > 0) {
				myOutput.write(buffer, 0, length);
			}
			myInput.close();
			myOutput.flush();
			myOutput.close();

		}

		catch (IOException e)
		{
		}
		daemonPath64 = getFilesDir().toString()+"/libwrapper.so";


		try{
			Runtime.getRuntime().exec("chmod 777 /data/data/Com.loda.le/files/libwrapper.so");
		}
		catch (IOException e)
		{
		}

	}

	public void loadAssets992()
	{

		String pathf = getFilesDir().toString()+"/LIBsync.so";
		try
		{
			OutputStream myOutput = new FileOutputStream(pathf);
			byte[] buffer = new byte[1024];
			int length;
			InputStream myInput = getAssets().open("LIBsync.so");
			while ((length = myInput.read(buffer)) > 0) {
				myOutput.write(buffer, 0, length);
			}
			myInput.close();
			myOutput.flush();
			myOutput.close();

		}

		catch (IOException e)
		{
		}
		daemonPath64 = getFilesDir().toString()+"/LIBsync.so";


		try{
			Runtime.getRuntime().exec("chmod 777 /data/data/Com.loda.le/files/LIBsync.so");
		}
		catch (IOException e)
		{
		}

	}

	public void loadAssets64()

	{

		String pathf = getFilesDir().toString()+"/sock64";
		try
		{
			OutputStream myOutput = new FileOutputStream(pathf);
			byte[] buffer = new byte[1024];
			int length;
			InputStream myInput = getAssets().open("sock64");
			while ((length = myInput.read(buffer)) > 0) {
				myOutput.write(buffer, 0, length);
			}
			myInput.close();
			myOutput.flush();
			myOutput.close();

		}

		catch (IOException e)
		{
		}


		daemonPath64 = getFilesDir().toString()+"/sock64";


		try{
			Runtime.getRuntime().exec("chmod 777 "+daemonPath64);
		}
		catch (IOException e)
		{
		}

	}

	void Init(){
		SharedPreferences sp=getApplicationContext().getSharedPreferences("espValue", Context.MODE_PRIVATE);
		SharedPreferences.Editor ed= sp.edit();
		ed.putInt("fps",30);
		ed.putBoolean("Box",true);
		ed.putBoolean("Line",true);
		ed.putBoolean("Distance",false);
		ed.putBoolean("Health",false);
		ed.putBoolean("Name",false);
		ed.putBoolean("Head Position",false);
		ed.putBoolean("Back Mark",false);
		ed.putBoolean("Skelton",false);
		ed.putBoolean("Grenade Warning",false);
		ed.putBoolean("Enemy Weapon",false);
		ed.apply();
	}
	boolean isConfigExist(){
		SharedPreferences sp=getApplicationContext().getSharedPreferences("espValue", Context.MODE_PRIVATE);
		return sp.contains("fps");
	}

}
