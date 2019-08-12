package p005cn.jpush.android.util;

import com.airbnb.android.airmapview.AirMapInterface;
import com.airbnb.android.explore.views.ExploreBaseRangeSeekBar;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: cn.jpush.android.util.StreamTool */
public class StreamTool {
    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        while (true) {
            int len = inStream.read(buffer);
            if (len != -1) {
                outStream.write(buffer, 0, len);
            } else {
                inStream.close();
                return outStream.toByteArray();
            }
        }
    }

    public void streamSaveAsFile(InputStream is, File outfile) {
        FileOutputStream fos = null;
        try {
            FileOutputStream fos2 = new FileOutputStream(outfile);
            try {
                byte[] buffer = new byte[1024];
                while (true) {
                    int len = is.read(buffer);
                    if (len > 0) {
                        fos2.write(buffer, 0, len);
                    } else {
                        try {
                            is.close();
                            fos2.close();
                            return;
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            throw new RuntimeException(e2);
                        }
                    }
                }
            } catch (Exception e) {
                e = e;
                fos = fos2;
                try {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                } catch (Throwable th) {
                    th = th;
                    try {
                        is.close();
                        fos.close();
                        throw th;
                    } catch (Exception e22) {
                        e22.printStackTrace();
                        throw new RuntimeException(e22);
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                fos = fos2;
                is.close();
                fos.close();
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static String streamToString(InputStream in) throws IOException {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        while (true) {
            int n = in.read(b);
            if (n == -1) {
                return out.toString();
            }
            out.append(new String(b, 0, n));
        }
    }

    public static byte[] stream2Byte(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        while (true) {
            int len = is.read(b, 0, b.length);
            if (len == -1) {
                return baos.toByteArray();
            }
            baos.write(b, 0, len);
        }
    }

    public static byte[] inputStream2Byte(InputStream inStream) throws Exception {
        int count = 0;
        while (count == 0) {
            count = inStream.available();
        }
        byte[] b = new byte[count];
        inStream.read(b);
        return b;
    }

    public static InputStream byte2InputStream(byte[] b) throws Exception {
        return new ByteArrayInputStream(b);
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=short, code=int, for r5v0, types: [short, int] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] shortToByte(int r5) {
        /*
            r2 = r5
            r3 = 2
            byte[] r0 = new byte[r3]
            r1 = 0
        L_0x0005:
            int r3 = r0.length
            if (r1 >= r3) goto L_0x001a
            java.lang.Integer r3 = new java.lang.Integer
            r4 = r2 & 255(0xff, float:3.57E-43)
            r3.<init>(r4)
            byte r3 = r3.byteValue()
            r0[r1] = r3
            int r2 = r2 >> 8
            int r1 = r1 + 1
            goto L_0x0005
        L_0x001a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p005cn.jpush.android.util.StreamTool.shortToByte(short):byte[]");
    }

    public static short byteToShort(byte[] b) {
        return (short) (((short) (b[0] & 255)) | ((short) (((short) (b[1] & 255)) << 8)));
    }

    public static byte[] intToByte(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((65280 & i) >> 8), (byte) ((16711680 & i) >> 16), (byte) ((-16777216 & i) >> 24)};
    }

    public static int bytesToInt(byte[] bytes) {
        return (bytes[0] & 255) | ((bytes[1] << 8) & ExploreBaseRangeSeekBar.ACTION_POINTER_INDEX_MASK) | ((bytes[2] << 16) & 16711680) | ((bytes[3] << 24) & AirMapInterface.CIRCLE_BORDER_COLOR);
    }

    public static byte[] longToByte(long number) {
        long temp = number;
        byte[] b = new byte[8];
        for (int i = 0; i < b.length; i++) {
            b[i] = new Long(255 & temp).byteValue();
            temp >>= 8;
        }
        return b;
    }

    public static long byteToLong(byte[] b) {
        long j = ((long) (b[0] & 255)) | (((long) (b[1] & 255)) << 8);
        long j2 = j | (((long) (b[2] & 255)) << 16);
        long j3 = j2 | (((long) (b[3] & 255)) << 24);
        long j4 = j3 | (((long) (b[4] & 255)) << 32);
        long j5 = j4 | (((long) (b[5] & 255)) << 40);
        return j5 | (((long) (b[6] & 255)) << 48) | (((long) (b[7] & 255)) << 56);
    }
}
