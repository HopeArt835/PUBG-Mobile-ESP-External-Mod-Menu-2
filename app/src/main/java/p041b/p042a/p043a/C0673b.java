package p041b.p042a.p043a;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class C0673b {


    protected static String[] f2717a = {"echo -BOC-", "id"};


    public static class C0674a {


        private static String[] f2718a = {null, null};


        public static boolean m3185a(String str) {
            int indexOf = str.indexOf(32);
            if (indexOf >= 0) {
                str = str.substring(0, indexOf);
            }
            int lastIndexOf = str.lastIndexOf(47);
            if (lastIndexOf >= 0) {
                str = str.substring(lastIndexOf + 1);
            }
            return str.equals("su");
        }


        public static List<String> m3186b(String str) {
            return C0673b.m3184a("su", new String[]{str}, (String[]) null, false);
        }
    }

    ///
    @SuppressWarnings("CallToPrintStackTrace")
    public static List<String> m3184a(String str, String[] strArr, String[] strArr2, boolean z) {
        List<String> list = null;
        String str2 = str;
        String[] strArr3 = strArr;
        String[] strArr4 = strArr2;
        String upperCase = str2.toUpperCase(Locale.ENGLISH);
        if (!C0671a.m3181c() || !C0671a.m3182d()) {
            C0671a.m3178b(String.format("[%s%%] START", new Object[]{upperCase}));
            List<String> synchronizedList = Collections.synchronizedList(new ArrayList());
            if (strArr4 != null) {
                try {
                    HashMap hashMap = new HashMap();
                    hashMap.putAll(System.getenv());
                    for (String str3 : strArr4) {
                        int indexOf = str3.indexOf("=");
                        if (indexOf >= 0) {
                            hashMap.put(str3.substring(0, indexOf), str3.substring(indexOf + 1));
                        }
                    }
                    strArr4 = new String[hashMap.size()];
                    int i = 0;
                    for (Object entry : hashMap.entrySet()) {
                        Map.Entry mapEntry = (Map.Entry) entry;
                        strArr4[i] = ((String) mapEntry.getKey()) + "=" + ((String) mapEntry.getValue());
                        i++;
                    }
                } catch (Exception e) {
                    if (!e.getMessage().contains("EPIPE")) {
                        throw e;
                    }
                }
            }
            Process exec = null;
            try {
                exec = Runtime.getRuntime().exec(str2, strArr4);
            } catch (IOException e) {
                e.printStackTrace();
            }
            DataOutputStream dataOutputStream = null;
            if (exec != null) {
                dataOutputStream = new DataOutputStream(exec.getOutputStream());
            }
            C0676d dVar = null;
            if (exec != null) {
                dVar = new C0676d(upperCase + "-", exec.getInputStream(), synchronizedList);
            }
            C0676d dVar2 = null;
            if (exec != null) {
                dVar2 = new C0676d(upperCase + "*", exec.getErrorStream(), z ? synchronizedList : null);
            }
            if (dVar != null) {
                dVar.start();
            }
            if (dVar2 != null) {
                dVar2.start();
            }
            for (String str4 : strArr3) {
                C0671a.m3178b(String.format("[%s+] %s", new Object[]{upperCase, str4}));
                try {
                    if (dataOutputStream != null) {
                        dataOutputStream.write((str4 + "\n").getBytes("UTF-8"));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (dataOutputStream != null) {
                        dataOutputStream.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                if (dataOutputStream != null) {
                    dataOutputStream.write("exit\n".getBytes("UTF-8"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (dataOutputStream != null) {
                    dataOutputStream.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (exec != null) {
                    exec.waitFor();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (dVar != null) {
                    dVar.join();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                if (dVar2 != null) {
                    dVar2.join();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (exec != null) {
                exec.destroy();
            }
            if (exec != null) {
                list = (C0674a.m3185a(str) || exec.exitValue() != 255) ? synchronizedList : null;
            }
            C0671a.m3178b(String.format("[%s%%] END", new Object[]{str2.toUpperCase(Locale.ENGLISH)}));
            return list;
        }
        C0671a.m3176a("Application attempted to run a shell command from the main thread");
        throw new C0675c("Application attempted to run a shell command from the main thread");
    }
}
