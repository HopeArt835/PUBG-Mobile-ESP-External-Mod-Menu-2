package tggod.doom;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.IBinder;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;


public class FloatAim extends Service implements View.OnClickListener {
    private static FloatAim Instance;
    View espView;
    View logoView;
    /* access modifiers changed from: private */
    public View mFloatingView;
    /* access modifiers changed from: private */
    public WindowManager mWindowManager;

    public native void SettingValue(int setting_code,boolean value);

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onClick(View view) {
    }

    static {
        System.loadLibrary("doom-lib");
    }

    public void onCreate() {
        super.onCreate();
        Instance = this;
        createOver();
        this.logoView = this.mFloatingView.findViewById(R.id.relativeLayoutParent);
        this.espView = this.mFloatingView.findViewById(R.id.floatLogo);
        Init();
    }

    public void createOver() {
        this.mFloatingView = LayoutInflater.from(this).inflate(R.layout.float_aim, (ViewGroup) null);
        final WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-2, -2, Build.VERSION.SDK_INT >= 26 ? 2038 : 2002, 8, 1);
        layoutParams.gravity = 49;
        if (getConfig("paramaim")) {
            layoutParams.x = Integer.valueOf((int) getConfigi("paramxaim")).intValue();
            layoutParams.y = Integer.valueOf((int) getConfigi("paramyaim")).intValue();
        }
        WindowManager windowManager = (WindowManager) getSystemService("window");
        this.mWindowManager = windowManager;
        windowManager.addView(this.mFloatingView, layoutParams);
        final GestureDetector gestureDetector = new GestureDetector(this, new SingleTapConfirm());
        this.mFloatingView.findViewById(R.id.relativeLayoutParent).setOnTouchListener(new View.OnTouchListener() {
            private float initialTouchX;
            private float initialTouchY;
            private int initialX;
            private int initialY;

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (gestureDetector.onTouchEvent(motionEvent)) {
                    FloatAim floatAim = FloatAim.this;
                    floatAim.SettingValue(11, floatAim.getConfig("Aimbot"));
                    if (!FloatAim.this.getConfig("Aimbot")) {
                        FloatAim.this.espView.setBackgroundResource(R.drawable.ic_aimbot_button_on);
                        FloatAim.this.setValue("Aimbot", true);
                        FloatAim floatAim2 = FloatAim.this;
                        floatAim2.SettingValue(11, floatAim2.getConfig("Aimbot"));
                    } else {
                        FloatAim.this.espView.setBackgroundResource(R.drawable.ic_aimbot_button_off);
                        FloatAim.this.setValue("Aimbot", false);
                        FloatAim floatAim3 = FloatAim.this;
                        floatAim3.SettingValue(11, floatAim3.getConfig("Aimbot"));
                    }
                    return true;
                }
                int action = motionEvent.getAction();
                if (action == 0) {
                    this.initialX = layoutParams.x;
                    this.initialY = layoutParams.y;
                    this.initialTouchX = motionEvent.getRawX();
                    this.initialTouchY = motionEvent.getRawY();
                    return true;
                } else if (action != 2) {
                    return false;
                } else {
                    layoutParams.x = this.initialX + ((int) (motionEvent.getRawX() - this.initialTouchX));
                    layoutParams.y = this.initialY + ((int) (motionEvent.getRawY() - this.initialTouchY));
                    FloatAim.this.setValuei("paramxaim", (float) layoutParams.x);
                    FloatAim.this.setValuei("paramyaim", (float) layoutParams.y);
                    FloatAim.this.setValue("paramaim", true);
                    FloatAim.this.mWindowManager.updateViewLayout(FloatAim.this.mFloatingView, layoutParams);
                    return true;
                }
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
        View view = this.mFloatingView;
        if (view != null) {
            this.mWindowManager.removeView(view);
        }
    }

    /* access modifiers changed from: private */
    public void setValuei(String str, float f) {
        SharedPreferences.Editor edit = getSharedPreferences("espValue", 0).edit();
        edit.putFloat(str, f);
        edit.apply();
    }

    /* access modifiers changed from: private */
    public void setValue(String str, boolean z) {
        SharedPreferences.Editor edit = getSharedPreferences("espValue", 0).edit();
        edit.putBoolean(str, z);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    public boolean getConfig(String str) {
        return getSharedPreferences("espValue", 0).getBoolean(str, false);
    }

    /* access modifiers changed from: package-private */
    public float getConfigi(String str) {
        return getSharedPreferences("espValue", 0).getFloat(str, 0.0f);
    }

    public static void HideFloat() {
        FloatAim floatAim = Instance;
        if (floatAim != null) {
            floatAim.Hide();
        }
    }

    public void Hide() {
        stopSelf();
        System.exit(-1);
    }

    /* access modifiers changed from: package-private */
    public void Init() {
        if (getConfig("Aimbot")) {
            this.espView.setBackgroundResource(R.drawable.ic_aimbot_button_on);
            setValue("Aimbot", true);
            SettingValue(11, getConfig("Aimbot"));
            return;
        }
        this.espView.setBackgroundResource(R.drawable.ic_aimbot_button_off);
        setValue("Aimbot", false);
        SettingValue(11, getConfig("Aimbot"));
    }
}
