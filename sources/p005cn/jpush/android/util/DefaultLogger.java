package p005cn.jpush.android.util;

import android.util.Log;

/* renamed from: cn.jpush.android.util.DefaultLogger */
public class DefaultLogger implements ILogger {
    /* renamed from: v */
    public void mo18531v(String tag, String msg) {
        Log.v(tag, msg);
    }

    /* renamed from: d */
    public void mo18525d(String tag, String msg) {
        Log.d(tag, msg);
    }

    /* renamed from: i */
    public void mo18529i(String tag, String msg) {
        Log.i(tag, msg);
    }

    /* renamed from: w */
    public void mo18533w(String tag, String msg) {
        Log.w(tag, msg);
    }

    /* renamed from: e */
    public void mo18527e(String tag, String msg) {
        Log.e(tag, msg);
    }

    /* renamed from: v */
    public void mo18532v(String tag, String msg, Throwable t) {
        Log.v(tag, msg, t);
    }

    /* renamed from: d */
    public void mo18526d(String tag, String msg, Throwable t) {
        Log.d(tag, msg, t);
    }

    /* renamed from: i */
    public void mo18530i(String tag, String msg, Throwable t) {
        Log.i(tag, msg, t);
    }

    /* renamed from: w */
    public void mo18534w(String tag, String msg, Throwable t) {
        Log.w(tag, msg, t);
    }

    /* renamed from: e */
    public void mo18528e(String tag, String msg, Throwable t) {
        Log.e(tag, msg, t);
    }
}
