package com.jumio.p311nv.barcode.environment;

import android.graphics.Point;
import com.jumio.commons.log.Log;
import com.jumio.p311nv.barcode.environment.RecognizerWrapper.C4446a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/* renamed from: com.jumio.nv.barcode.environment.RecognizerCallback */
public class RecognizerCallback {
    private static ArrayList<C4446a> mListeners = new ArrayList<>();

    /* renamed from: com.jumio.nv.barcode.environment.RecognizerCallback$a */
    enum C4445a {
        SUCCESS(1),
        FAIL(2),
        CAMERA_TOO_HIGH(4),
        QR_SUCCESS(8),
        PARTIAL_OBJECT(64),
        CAMERA_AT_ANGLE(128);
        

        /* renamed from: h */
        private static final Map<Integer, C4445a> f3337h = null;

        /* renamed from: g */
        private final int f3339g;

        static {
            f3337h = new TreeMap();
            for (int i = 0; i < values().length; i++) {
                int i2 = values()[i].f3339g;
                f3337h.put(Integer.valueOf(i2), values()[i]);
            }
        }

        private C4445a(int i) {
            this.f3339g = i;
        }

        /* renamed from: a */
        public static C4445a m1967a(int i) {
            return (C4445a) f3337h.get(Integer.valueOf(i));
        }
    }

    public static void onObjectDetected(NativePoint[] nativePointArr, int i, int i2, int i3) {
        _onObjectDetected(nativePointArr, i, i2, C4445a.m1967a(i3));
    }

    private static synchronized void _onObjectDetected(NativePoint[] nativePointArr, int i, int i2, C4445a aVar) {
        synchronized (RecognizerCallback.class) {
            if (isSuccess(aVar) && nativePointArr != null && nativePointArr.length > 0) {
                Iterator it = mListeners.iterator();
                while (it.hasNext()) {
                    try {
                        ((C4446a) it.next()).mo43359a(convert(nativePointArr), i, i2);
                    } catch (Exception e) {
                        Log.printStackTrace(e);
                    }
                }
            }
        }
    }

    private static boolean isSuccess(C4445a aVar) {
        return aVar != null && (aVar.equals(C4445a.SUCCESS) || aVar.equals(C4445a.QR_SUCCESS));
    }

    private static Point[] convert(NativePoint[] nativePointArr) {
        Point[] pointArr = new Point[nativePointArr.length];
        for (int i = 0; i < nativePointArr.length; i++) {
            pointArr[i] = new Point(nativePointArr[i].f3329a, nativePointArr[i].f3330b);
        }
        return pointArr;
    }

    public static synchronized boolean addListener(C4446a aVar) {
        boolean z;
        synchronized (RecognizerCallback.class) {
            if (!mListeners.contains(aVar)) {
                mListeners.add(aVar);
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    public static synchronized void releaseListeners() {
        synchronized (RecognizerCallback.class) {
            mListeners.clear();
        }
    }
}
