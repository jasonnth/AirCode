package p004bo.app;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import p005cn.jpush.android.JPushConstants;

/* renamed from: bo.app.fv */
final class C0529fv {

    /* renamed from: a */
    static final Charset f564a = Charset.forName("US-ASCII");

    /* renamed from: b */
    static final Charset f565b = Charset.forName(JPushConstants.ENCODING_UTF_8);

    /* renamed from: a */
    static void m758a(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            throw new IOException("not a readable directory: " + file);
        }
        for (File file2 : listFiles) {
            if (file2.isDirectory()) {
                m758a(file2);
            }
            if (!file2.delete()) {
                throw new IOException("failed to delete file: " + file2);
            }
        }
    }

    /* renamed from: a */
    static void m757a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
            }
        }
    }
}
