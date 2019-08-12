package com.airbnb.android.utils;

import android.webkit.MimeTypeMap;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.MediaType;

public class IOUtils {
    public static boolean copyFile(File srcFile, File dstFile) {
        InputStream in = null;
        FileOutputStream out = null;
        try {
            InputStream in2 = new FileInputStream(srcFile);
            try {
                if (dstFile.exists()) {
                    dstFile.delete();
                }
                FileOutputStream out2 = new FileOutputStream(dstFile);
                try {
                    byte[] buffer = new byte[4096];
                    while (true) {
                        int bytesRead = in2.read(buffer);
                        if (bytesRead >= 0) {
                            out2.write(buffer, 0, bytesRead);
                        } else {
                            out2.flush();
                            out2.getFD().sync();
                            closeQuietly(in2);
                            closeQuietly(out2);
                            FileOutputStream fileOutputStream = out2;
                            FileInputStream fileInputStream = in2;
                            return true;
                        }
                    }
                } catch (IOException e) {
                    out = out2;
                    in = in2;
                    closeQuietly(in);
                    closeQuietly(out);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    out = out2;
                    in = in2;
                    closeQuietly(in);
                    closeQuietly(out);
                    throw th;
                }
            } catch (IOException e2) {
                in = in2;
                closeQuietly(in);
                closeQuietly(out);
                return false;
            } catch (Throwable th2) {
                th = th2;
                in = in2;
                closeQuietly(in);
                closeQuietly(out);
                throw th;
            }
        } catch (IOException e3) {
            closeQuietly(in);
            closeQuietly(out);
            return false;
        } catch (Throwable th3) {
            th = th3;
            closeQuietly(in);
            closeQuietly(out);
            throw th;
        }
    }

    public static boolean createFile(File file, byte[] data) {
        FileOutputStream out = null;
        try {
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream out2 = new FileOutputStream(file);
            try {
                out2.write(data, 0, data.length);
                out2.flush();
                out2.getFD().sync();
                closeQuietly(out2);
                FileOutputStream fileOutputStream = out2;
                return true;
            } catch (IOException e) {
                out = out2;
                closeQuietly(out);
                return false;
            } catch (Throwable th) {
                th = th;
                out = out2;
                closeQuietly(out);
                throw th;
            }
        } catch (IOException e2) {
            closeQuietly(out);
            return false;
        } catch (Throwable th2) {
            th = th2;
            closeQuietly(out);
            throw th;
        }
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    public static MediaType getContentType(String fileName) {
        return MediaType.parse(MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(fileName)));
    }

    public static byte[] readBytesFromFile(File file) {
        if (file == null || file.length() == 0) {
            return null;
        }
        byte[] fileContent = new byte[((int) file.length())];
        try {
            new FileInputStream(file).read(fileContent);
            return fileContent;
        } catch (Exception e) {
            return null;
        }
    }
}
