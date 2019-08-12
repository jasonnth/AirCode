package p005cn.jpush.p034im.api;

/* renamed from: cn.jpush.im.api.BasicCallback */
public abstract class BasicCallback {
    private boolean isRunInUIThread = true;

    public abstract void gotResult(int i, String str);

    public BasicCallback() {
    }

    public BasicCallback(boolean isRunInUIThread2) {
        this.isRunInUIThread = isRunInUIThread2;
    }

    public boolean isRunInUIThread() {
        return this.isRunInUIThread;
    }
}
