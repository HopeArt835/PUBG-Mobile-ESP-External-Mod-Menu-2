package p041b.p042a.p043a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;


public class C0676d extends Thread {


    private String f2719a = null;


    private BufferedReader f2720b = null;


    private List<String> f2721c = null;


    private C0677a f2722d = null;


    public interface C0677a {

        void mo3364a(String str);
    }

    public C0676d(String str, InputStream inputStream, List<String> list) {
        this.f2719a = str;
        this.f2720b = new BufferedReader(new InputStreamReader(inputStream));
        this.f2721c = list;
    }

    ///
    public void run() {
        try {
            while (true) {
                String str = this.f2720b.readLine();
                if (str != null) {
                    C0671a.m3180c(String.format("[%s] %s", new Object[] {this.f2719a, str}));
                    if (this.f2721c != null)
                        this.f2721c.add(str);
                    if (this.f2722d != null)
                        this.f2722d.mo3364a(str);
                    continue;
                }
                break;
            }
            try {
                this.f2720b.close();
                return;
            } catch (IOException iOException) {}
        } catch (IOException iOException) {
            try {
                this.f2720b.close();
                return;
            } catch (IOException iOException1) {}
        }
    }
}
