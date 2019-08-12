package p005cn.jpush.android.util;

/* renamed from: cn.jpush.android.util.TestLogger */
public class TestLogger implements ILogger {
    public static void setTestLogger() {
        Logger.setDelegate(new TestLogger());
    }

    /* renamed from: v */
    public void mo18531v(String tag, String msg) {
        System.out.println("[" + tag + "] - " + msg);
    }

    /* renamed from: d */
    public void mo18525d(String tag, String msg) {
        System.out.println("[" + tag + "] - " + msg);
    }

    /* renamed from: i */
    public void mo18529i(String tag, String msg) {
        System.out.println("[" + tag + "] - " + msg);
    }

    /* renamed from: w */
    public void mo18533w(String tag, String msg) {
        System.out.println("[" + tag + "] - " + msg);
    }

    /* renamed from: e */
    public void mo18527e(String tag, String msg) {
        System.out.println("[" + tag + "] - " + msg);
    }

    /* renamed from: v */
    public void mo18532v(String tag, String msg, Throwable t) {
        System.out.println("[" + tag + "] - " + msg + " - " + t.getMessage());
    }

    /* renamed from: d */
    public void mo18526d(String tag, String msg, Throwable t) {
        System.out.println("[" + tag + "] - " + msg + " - " + t.getMessage());
    }

    /* renamed from: i */
    public void mo18530i(String tag, String msg, Throwable t) {
        System.out.println("[" + tag + "] - " + msg + " - " + t.getMessage());
    }

    /* renamed from: w */
    public void mo18534w(String tag, String msg, Throwable t) {
        System.out.println("[" + tag + "] - " + msg + " - " + t.getMessage());
    }

    /* renamed from: e */
    public void mo18528e(String tag, String msg, Throwable t) {
        System.out.println("[" + tag + "] - " + msg + " - " + t.getMessage());
    }
}
