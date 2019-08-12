package p005cn.jpush.android.util;

import p005cn.jpush.android.JPush;

/* renamed from: cn.jpush.android.util.TimeWatcher */
public class TimeWatcher {
    private long _begin;
    private boolean _notStartAutomated = false;
    private String msg;
    private String tag;

    public TimeWatcher(String tag2, String msg2) {
        if (JPush.DEBUG_MODE) {
            this.tag = tag2;
            this.msg = msg2;
            start();
        }
    }

    public TimeWatcher(String tag2, String msg2, boolean notStartAutomated) {
        if (JPush.DEBUG_MODE) {
            this.tag = tag2;
            this.msg = msg2;
            this._notStartAutomated = notStartAutomated;
            if (!notStartAutomated) {
                start();
            }
        }
    }

    public void start() {
        this._begin = System.currentTimeMillis();
    }

    public void show() {
        if (JPush.DEBUG_MODE) {
            Logger.m1416d(this.tag, "--- Time watcher for '" + this.msg + "': " + (System.currentTimeMillis() - this._begin) + "ms");
            if (!this._notStartAutomated) {
                start();
            }
        }
    }
}
