package p005cn.jpush.android.api;

/* renamed from: cn.jpush.android.api.ActivityFlowItem */
public class ActivityFlowItem {
    public long duration;
    public String label;

    public ActivityFlowItem(String name, long du) {
        this.label = name;
        this.duration = du;
    }
}
