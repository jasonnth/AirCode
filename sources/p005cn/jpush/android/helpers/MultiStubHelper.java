package p005cn.jpush.android.helpers;

import android.content.Context;
import android.os.RemoteException;
import p005cn.jpush.android.IDataShare.Stub;
import p005cn.jpush.android.util.Logger;
import p005cn.jpush.android.util.SharePreferenceProcess;

/* renamed from: cn.jpush.android.helpers.MultiStubHelper */
public class MultiStubHelper extends Stub {
    private static final String TAG = "MultiStubHelper";
    private Context context;

    public MultiStubHelper(Context context2) {
        this.context = context2;
    }

    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
    }

    public int getInt(String key, int defaultValue) throws RemoteException {
        int value = SharePreferenceProcess.getInt(this.context, key, defaultValue);
        Logger.m1428v(TAG, "get integer by aidl, key:" + key + ", value:" + value);
        return value;
    }

    public void commitInt(String key, int value) throws RemoteException {
        Logger.m1428v(TAG, "commit integer by aidl, key:" + key + ", value:" + value);
        SharePreferenceProcess.commitInt(this.context, key, value);
    }

    public long getLong(String key, long defaultValue) throws RemoteException {
        long value = SharePreferenceProcess.getLong(this.context, key, defaultValue);
        Logger.m1428v(TAG, "get long by aidl, key:" + key + ", value:" + value);
        return value;
    }

    public void commitLong(String key, long value) throws RemoteException {
        Logger.m1428v(TAG, "commit long by aidl, key:" + key + ", value:" + value);
        SharePreferenceProcess.commitLong(this.context, key, value);
    }

    public boolean getBoolean(String key, boolean defaultValue) throws RemoteException {
        boolean value = SharePreferenceProcess.getBoolean(this.context, key, defaultValue);
        Logger.m1428v(TAG, "get boolean by aidl, key:" + key + ", value:" + value);
        return value;
    }

    public void commitBoolean(String key, boolean value) throws RemoteException {
        Logger.m1428v(TAG, "commit boolean by aidl, key:" + key + ", value:" + value);
        SharePreferenceProcess.commitBoolean(this.context, key, value);
    }

    public String getString(String key, String defaultValue) throws RemoteException {
        String value = SharePreferenceProcess.getString(this.context, key, defaultValue);
        Logger.m1428v(TAG, "get String-data by aidl, key:" + key + ", value:" + value);
        return value;
    }

    public void commitString(String key, String value) throws RemoteException {
        Logger.m1428v(TAG, "commit String by aidl, key:" + key + ", value:" + value);
        SharePreferenceProcess.commitString(this.context, key, value);
    }
}
