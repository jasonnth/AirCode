package p005cn.jpush.android.util;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/* renamed from: cn.jpush.android.util.FileRecoder */
public class FileRecoder {
    private static HashMap<String, FileRecoder> files = new HashMap<>();
    private static boolean recordEnabled = true;
    private String fileName;
    private String filePrefix = (Environment.getExternalStorageDirectory() + File.separator + this.mContext.getPackageName() + File.separator);
    private Context mContext;

    private FileRecoder(String fileName2, Context context) {
        this.fileName = fileName2;
        this.mContext = context;
    }

    public static FileRecoder getRecorder(String fileName2, Context context) {
        if (files.get(fileName2) == null) {
            files.put(fileName2, new FileRecoder(fileName2, context));
        }
        return (FileRecoder) files.get(fileName2);
    }

    public static boolean isFileExist(String fileName2) {
        if (!files.containsKey(fileName2) || files.get(fileName2) == null) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0031 A[SYNTHETIC, Splitter:B:13:0x0031] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003a A[SYNTHETIC, Splitter:B:18:0x003a] */
    /* JADX WARNING: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeAppend(java.lang.String r6) {
        /*
            r5 = this;
            java.io.File r0 = new java.io.File
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = r5.filePrefix
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r4 = r5.fileName
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            r0.<init>(r3)
            r1 = 0
            java.io.FileWriter r2 = new java.io.FileWriter     // Catch:{ IOException -> 0x002e, all -> 0x0037 }
            r3 = 1
            r2.<init>(r0, r3)     // Catch:{ IOException -> 0x002e, all -> 0x0037 }
            r2.write(r6)     // Catch:{ IOException -> 0x0043, all -> 0x0040 }
            if (r2 == 0) goto L_0x0029
            r2.close()     // Catch:{ IOException -> 0x002b }
        L_0x0029:
            r1 = r2
        L_0x002a:
            return
        L_0x002b:
            r3 = move-exception
            r1 = r2
            goto L_0x002a
        L_0x002e:
            r3 = move-exception
        L_0x002f:
            if (r1 == 0) goto L_0x002a
            r1.close()     // Catch:{ IOException -> 0x0035 }
            goto L_0x002a
        L_0x0035:
            r3 = move-exception
            goto L_0x002a
        L_0x0037:
            r3 = move-exception
        L_0x0038:
            if (r1 == 0) goto L_0x003d
            r1.close()     // Catch:{ IOException -> 0x003e }
        L_0x003d:
            throw r3
        L_0x003e:
            r4 = move-exception
            goto L_0x003d
        L_0x0040:
            r3 = move-exception
            r1 = r2
            goto L_0x0038
        L_0x0043:
            r3 = move-exception
            r1 = r2
            goto L_0x002f
        */
        throw new UnsupportedOperationException("Method not decompiled: p005cn.jpush.android.util.FileRecoder.writeAppend(java.lang.String):void");
    }

    private void init() {
    }

    public static void write(Context context, String fileName2, String content) {
        if (isRecordEnabled()) {
            FileWriter writer = null;
            try {
                FileWriter writer2 = new FileWriter(new File(Environment.getExternalStorageDirectory() + File.separator + context.getPackageName() + File.separator + fileName2));
                try {
                    writer2.write(content);
                    try {
                        writer2.close();
                        FileWriter fileWriter = writer2;
                    } catch (IOException e) {
                        FileWriter fileWriter2 = writer2;
                    }
                } catch (IOException e2) {
                    writer = writer2;
                    try {
                        writer.close();
                    } catch (IOException e3) {
                    }
                } catch (Throwable th) {
                    th = th;
                    writer = writer2;
                    try {
                        writer.close();
                    } catch (IOException e4) {
                    }
                    throw th;
                }
            } catch (IOException e5) {
                writer.close();
            } catch (Throwable th2) {
                th = th2;
                writer.close();
                throw th;
            }
        }
    }

    public static boolean isRecordEnabled() {
        return recordEnabled;
    }

    public static void setRecordEnabled(boolean recordEnabled2) {
        recordEnabled = recordEnabled2;
    }
}
