package com.manateeworks;

import android.app.Activity;
import android.graphics.PointF;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

public class BarcodeScanner {

    public static final class MWLocation {

        /* renamed from: p1 */
        public PointF f3556p1;

        /* renamed from: p2 */
        public PointF f3557p2;

        /* renamed from: p3 */
        public PointF f3558p3;

        /* renamed from: p4 */
        public PointF f3559p4;
        public PointF[] points = new PointF[4];

        public MWLocation(float[] _points) {
            for (int i = 0; i < 4; i++) {
                this.points[i] = new PointF();
                this.points[i].x = _points[i * 2];
                this.points[i].y = _points[(i * 2) + 1];
            }
            this.f3556p1 = new PointF();
            this.f3557p2 = new PointF();
            this.f3558p3 = new PointF();
            this.f3559p4 = new PointF();
            this.f3556p1.x = _points[0];
            this.f3556p1.y = _points[1];
            this.f3557p2.x = _points[2];
            this.f3557p2.y = _points[3];
            this.f3558p3.x = _points[4];
            this.f3558p3.y = _points[5];
            this.f3559p4.x = _points[6];
            this.f3559p4.y = _points[7];
        }
    }

    public static final class MWResult {
        public byte[] bytes = null;
        public int bytesLength = 0;
        public String encryptedResult;
        public int imageHeight = 0;
        public int imageWidth = 0;
        public boolean isGS1 = false;
        public MWLocation locationPoints = null;
        public int subtype = 0;
        public String text = null;
        public int type = 0;
    }

    public static final class MWResults {
        public int count = 0;
        public ArrayList<MWResult> results = new ArrayList<>();
        public int version = 0;

        public MWResults(byte[] buffer) {
            if (buffer[0] == 77 && buffer[1] == 87 && buffer[2] == 82) {
                this.version = buffer[3];
                this.count = buffer[4];
                int currentPos = 5;
                for (int i = 0; i < this.count; i++) {
                    MWResult result = new MWResult();
                    byte fieldsCount = buffer[currentPos];
                    currentPos++;
                    for (int f = 0; f < fieldsCount; f++) {
                        byte fieldType = buffer[currentPos];
                        byte fieldNameLength = buffer[currentPos + 1];
                        int fieldContentLength = ((buffer[currentPos + 3 + fieldNameLength] & 255) * 256) + (buffer[currentPos + 2 + fieldNameLength] & 255);
                        if (fieldNameLength > 0) {
                            new String(buffer, currentPos + 2, fieldNameLength);
                        }
                        int contentPos = currentPos + fieldNameLength + 4;
                        float[] locations = new float[8];
                        switch (fieldType) {
                            case 1:
                                result.bytes = new byte[fieldContentLength];
                                result.bytesLength = fieldContentLength;
                                for (int c = 0; c < fieldContentLength; c++) {
                                    result.bytes[c] = buffer[contentPos + c];
                                }
                                break;
                            case 2:
                                result.text = new String(buffer, contentPos, fieldContentLength);
                                break;
                            case 3:
                                result.type = ByteBuffer.wrap(buffer, contentPos, 4).order(ByteOrder.LITTLE_ENDIAN).getInt();
                                break;
                            case 4:
                                result.subtype = ByteBuffer.wrap(buffer, contentPos, 4).order(ByteOrder.LITTLE_ENDIAN).getInt();
                                break;
                            case 6:
                                result.isGS1 = ByteBuffer.wrap(buffer, contentPos, 4).order(ByteOrder.LITTLE_ENDIAN).getInt() == 1;
                                break;
                            case 7:
                                for (int l = 0; l < 8; l++) {
                                    locations[l] = ByteBuffer.wrap(buffer, (l * 4) + contentPos, 4).order(ByteOrder.LITTLE_ENDIAN).getFloat();
                                }
                                result.locationPoints = new MWLocation(locations);
                                break;
                            case 8:
                                result.imageWidth = ByteBuffer.wrap(buffer, contentPos, 4).order(ByteOrder.LITTLE_ENDIAN).getInt();
                                break;
                            case 9:
                                result.imageHeight = ByteBuffer.wrap(buffer, contentPos, 4).order(ByteOrder.LITTLE_ENDIAN).getInt();
                                break;
                            case 10:
                                result.encryptedResult = new String(buffer, contentPos, fieldContentLength);
                                break;
                        }
                        currentPos += fieldNameLength + fieldContentLength + 4;
                    }
                    this.results.add(result);
                }
            }
        }

        public MWResult getResult(int index) {
            return (MWResult) this.results.get(index);
        }
    }

    public static native int MWBcleanupLib();

    public static native int[] MWBcropPreviewFrame(byte[] bArr, int i, int i2, int i3);

    public static native int MWBgetActiveCodes();

    public static native float[] MWBgetBarcodeLocation();

    public static native int MWBgetDirection();

    public static native int MWBgetLastType();

    public static native int MWBgetLibVersion();

    public static native int MWBgetResultType();

    public static native float[] MWBgetScanningRectArray(int i);

    public static native int MWBgetSupportedCodes();

    public static native int MWBisLastGS1();

    public static native int MWBregisterSDK(String str, Activity activity);

    public static native byte[] MWBscanGrayscaleImage(byte[] bArr, int i, int i2);

    public static native int MWBsetActiveCodes(int i);

    public static native int MWBsetActiveSubcodes(int i, int i2);

    public static native int MWBsetDirection(int i);

    public static native void MWBsetDuplicate(byte[] bArr, int i);

    public static native int MWBsetDuplicatesTimeout(int i);

    public static native int MWBsetFlags(int i, int i2);

    public static native int MWBsetLevel(int i);

    public static native int MWBsetMinLength(int i, int i2);

    public static native int MWBsetParam(int i, int i2, int i3);

    public static native int MWBsetResultType(int i);

    public static native int MWBsetScanningRect(int i, float f, float f2, float f3, float f4);

    public static native int MWBvalidateVIN(byte[] bArr);

    static {
        System.loadLibrary("BarcodeScannerLib");
    }
}
