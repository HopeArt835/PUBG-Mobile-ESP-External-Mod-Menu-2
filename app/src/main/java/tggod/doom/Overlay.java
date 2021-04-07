package tggod.doom;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Build;
import android.os.IBinder;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import java.io.DataOutputStream;
import java.io.IOException;

public class Overlay extends Service {
    private static Overlay Instance;
    static Context ctx;
    View mFloatingView;
    ESPView overlayView;
    Process process;
    WindowManager windowManager;

    private native void Close();

    public static native void DrawOn(ESPView eSPView, Canvas canvas);

    public static native boolean getReady(int i);

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        ctx = this;
        if (MainActivity2.gameType == 1 && MainActivity2.is32) {
            Start(ctx, 1, 1);
        } else if (MainActivity2.gameType == 1 && MainActivity2.is64) {
            Start(ctx, 1, 2);
        } else if (MainActivity2.gameType == 2 && MainActivity2.is32) {
            Start(ctx, 2, 1);
        } else if (MainActivity2.gameType == 2 && MainActivity2.is64) {
            Start(ctx, 2, 2);
        } else if (MainActivity2.gameType == 3 && MainActivity2.is32) {
            Start(ctx, 3, 1);
        } else if (MainActivity2.gameType == 3 && MainActivity2.is64) {
            Start(ctx, 3, 2);
        } else if (MainActivity2.gameType == 4 && MainActivity2.is32) {
            Start(ctx, 4, 1);
        } else if (MainActivity2.gameType == 4 && MainActivity2.is64) {
            Start(ctx, 4, 2);
        }
        this.windowManager = (WindowManager) ctx.getSystemService("window");
        this.overlayView = new ESPView(ctx);
        DrawCanvas();
    }

    public void onDestroy() {
        super.onDestroy();
        Close();
        if (this.overlayView != null) {
            ((WindowManager) ctx.getSystemService("window")).removeView(this.overlayView);
            this.overlayView = null;
        }
        this.process.destroy();
    }

    public void Start(final Context context, final int i, final int i2) {
        if (Instance == null) {
            new Thread(new Runnable() {
                public void run() {
                    Overlay.getReady(i);
                }
            }).start();
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Overlay.this.StartDaemon(context, i2);
                }
            }).start();
        }
    }

    public void StartDaemon(Context context, int i) {
        Shell(MainActivity2.socket);
    }

    public static void Stop(Context context) {
        context.stopService(new Intent(context, Overlay.class));
        context.stopService(new Intent(context, FloatLogo.class));
    }

    static boolean getConfig(String str) {
        return ctx.getSharedPreferences("espValue", 0).getBoolean(str, false);
    }

    private void DrawCanvas() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, -1, 0, getNavigationBarHeight(), Build.VERSION.SDK_INT >= 26 ? 2038 : 2006, 1304, 1);
        if (Build.VERSION.SDK_INT >= 28) {
            layoutParams.layoutInDisplayCutoutMode = 1;
        }
        layoutParams.gravity = 8388659;
        layoutParams.x = 0;
        layoutParams.y = 0;
        WindowManager windowManager2 = (WindowManager) getSystemService("window");
        this.windowManager = windowManager2;
        windowManager2.addView(this.overlayView, layoutParams);
    }

    private int getNavigationBarHeight() {
        boolean hasPermanentMenuKey = ViewConfiguration.get(this).hasPermanentMenuKey();
        int identifier = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (identifier <= 0 || hasPermanentMenuKey) {
            return 0;
        }
        return getResources().getDimensionPixelSize(identifier);
    }

    public void Shell(String str) {
        DataOutputStream dataOutputStream = null;
        try {
            this.process = Runtime.getRuntime().exec(str);
        } catch (IOException e) {
            e.printStackTrace();
            this.process = null;
        }
        if (this.process != null) {
            dataOutputStream = new DataOutputStream(this.process.getOutputStream());
        }
        try {
            dataOutputStream.flush();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        try {
            this.process.waitFor();
        } catch (InterruptedException e3) {
            e3.printStackTrace();
        }
    }
}
