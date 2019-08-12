package p004bo.app;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* renamed from: bo.app.hm */
public final class C0597hm {

    /* renamed from: bo.app.hm$a */
    public interface C0598a {
        /* renamed from: a */
        boolean mo7167a(int i, int i2);
    }

    /* renamed from: a */
    public static boolean m1057a(InputStream inputStream, OutputStream outputStream, C0598a aVar, int i) {
        int available = inputStream.available();
        if (available <= 0) {
            available = 512000;
        }
        byte[] bArr = new byte[i];
        if (m1056a(aVar, 0, available)) {
            return false;
        }
        int i2 = 0;
        do {
            int read = inputStream.read(bArr, 0, i);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
                i2 += read;
            } else {
                outputStream.flush();
                return true;
            }
        } while (!m1056a(aVar, i2, available));
        return false;
    }

    /* renamed from: a */
    private static boolean m1056a(C0598a aVar, int i, int i2) {
        if (aVar == null || aVar.mo7167a(i, i2) || (i * 100) / i2 >= 75) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public static void m1055a(InputStream inputStream) {
        do {
            try {
            } catch (IOException e) {
                return;
            } finally {
                m1054a((Closeable) inputStream);
            }
        } while (inputStream.read(new byte[32768], 0, 32768) != -1);
    }

    /* renamed from: a */
    public static void m1054a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
            }
        }
    }
}
