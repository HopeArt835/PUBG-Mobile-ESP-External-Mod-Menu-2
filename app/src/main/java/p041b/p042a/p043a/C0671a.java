package p041b.p042a.p043a;

import android.os.Looper;
import android.util.Log;


public class C0671a {


    private static boolean f2713a = false;


    private static int f2714b = 65535;


    private static C0672a f2715c = null;


    private static boolean f2716d = true;


    public interface C0672a {

        void mo3362a(int i, String str, String str2);
    }


    private static void m3175a(int i, String str, String str2) {
        if (f2713a && (f2714b & i) == i) {
            C0672a aVar = f2715c;
            if (aVar != null) {
                aVar.mo3362a(i, str, str2);
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("[libsuperuser][");
            sb.append(str);
            sb.append("]");
            String str3 = " ";
            if (str2.startsWith("[") || str2.startsWith(str3)) {
                str3 = "";
            }
            sb.append(str3);
            sb.append(str2);
            Log.d("libsuperuser", sb.toString());
        }
    }


    public static void m3176a(String str) {
        m3175a(1, "G", str);
    }


    public static boolean m3177a() {
        return f2713a;
    }


    public static void m3178b(String str) {
        m3175a(2, "C", str);
    }


    public static boolean m3179b() {
        return f2716d;
    }


    public static void m3180c(String str) {
        m3175a(4, "O", str);
    }


    public static boolean m3181c() {
        return m3177a() && m3179b();
    }


    public static boolean m3182d() {
        return Looper.myLooper() != null && Looper.myLooper() == Looper.getMainLooper();
    }
}
