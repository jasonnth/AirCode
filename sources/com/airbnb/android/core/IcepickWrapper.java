package com.airbnb.android.core;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.Toast;
import com.airbnb.android.core.utils.BuildHelper;
import icepick.Icepick;

public class IcepickWrapper {
    private static final int BYTES_IN_KB = 1024;
    private static final int CRITICAL_LIMIT_BYTES = 512000;
    private static final String TAG = IcepickWrapper.class.getName();
    private static final int WARNING_LIMIT_BYTES = 409600;

    public static <T> void saveInstanceState(T target, Bundle bundle) {
        int startingSize = getByteSize(bundle);
        Icepick.saveInstanceState(target, bundle);
        logSizeUsed(target, startingSize, getByteSize(bundle));
    }

    public static <T extends View> Parcelable saveInstanceState(T target, Parcelable parcel) {
        int startingSize = getByteSize(parcel);
        Parcelable parcel2 = Icepick.saveInstanceState(target, parcel);
        logSizeUsed(target, startingSize, getByteSize(parcel2));
        return parcel2;
    }

    public static <T> void restoreInstanceState(T target, Bundle bundle) {
        Icepick.restoreInstanceState(target, bundle);
    }

    public static <T extends View> Parcelable restoreInstanceState(T target, Parcelable parcel) {
        return Icepick.restoreInstanceState(target, parcel);
    }

    private static void logSizeUsed(Object target, int startSize, int endSize) {
        if (BuildHelper.isDevelopmentBuild()) {
            int kbUsed = (endSize - startSize) / 1024;
            if (kbUsed > 0) {
                String message = String.format("Parcel size used for %1$s is %2$skB", new Object[]{target.getClass().getSimpleName(), Integer.valueOf(kbUsed)});
                C0715L.m1189d(TAG, message);
                if (kbUsed >= WARNING_LIMIT_BYTES) {
                    Toast.makeText(CoreApplication.appContext(), message, 1).show();
                }
            }
        }
    }

    private static int getByteSize(Parcelable parcelable) {
        if (!BuildHelper.isDevelopmentBuild()) {
            return 0;
        }
        Parcel parcel = Parcel.obtain();
        parcelable.writeToParcel(parcel, 0);
        int dataSize = parcel.dataSize();
        parcel.recycle();
        return dataSize;
    }

    public static boolean isOverWarningLimitByteSize(Parcelable... parcelables) {
        int size = 0;
        for (Parcelable parcelable : parcelables) {
            if (parcelable != null) {
                size += getByteSize(parcelable);
            }
        }
        if (size >= WARNING_LIMIT_BYTES) {
            return true;
        }
        return false;
    }

    public static boolean isOverCriticalLimitByteSize(Parcelable... parcelables) {
        int size = 0;
        for (Parcelable parcelable : parcelables) {
            size += getByteSize(parcelable);
        }
        if (size >= CRITICAL_LIMIT_BYTES) {
            return true;
        }
        return false;
    }
}
