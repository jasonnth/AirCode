package p005cn.jpush.android.data;

/* renamed from: cn.jpush.android.data.LocalNotificationDBData */
public class LocalNotificationDBData {
    private long ln_add_time;
    private int ln_count;
    private String ln_extra;
    private long ln_id;
    private int ln_remove;
    private long ln_trigger_time;
    private int ln_type;

    public LocalNotificationDBData() {
        this.ln_id = 0;
        this.ln_count = 0;
        this.ln_remove = 0;
        this.ln_type = 0;
        this.ln_extra = "";
        this.ln_trigger_time = 0;
        this.ln_add_time = 0;
        this.ln_id = 0;
        this.ln_count = 0;
        this.ln_remove = 0;
        this.ln_type = 0;
        this.ln_extra = "";
        this.ln_trigger_time = 0;
        this.ln_add_time = 0;
    }

    public long getLn_id() {
        return this.ln_id;
    }

    public void setLn_id(long ln_id2) {
        this.ln_id = ln_id2;
    }

    public int getLn_count() {
        return this.ln_count;
    }

    public void setLn_count(int ln_count2) {
        this.ln_count = ln_count2;
    }

    public int getLn_remove() {
        return this.ln_remove;
    }

    public void setLn_remove(int ln_remove2) {
        this.ln_remove = ln_remove2;
    }

    public int getLn_type() {
        return this.ln_type;
    }

    public void setLn_type(int ln_type2) {
        this.ln_type = ln_type2;
    }

    public String getLn_extra() {
        return this.ln_extra;
    }

    public void setLn_extra(String ln_extra2) {
        this.ln_extra = ln_extra2;
    }

    public long getLn_add_time() {
        return this.ln_add_time;
    }

    public void setLn_add_time(long ln_add_time2) {
        this.ln_add_time = ln_add_time2;
    }

    public long getLn_trigger_time() {
        return this.ln_trigger_time;
    }

    public void setLn_trigger_time(long ln_trigger_time2) {
        this.ln_trigger_time = ln_trigger_time2;
    }

    public String toString() {
        return "LocalNotificationDBData [ln_id=" + this.ln_id + ", ln_count=" + this.ln_count + ", ln_remove=" + this.ln_remove + ", ln_type=" + this.ln_type + ", ln_extra=" + this.ln_extra + ", ln_trigger_time=" + this.ln_trigger_time + ", ln_add_time=" + this.ln_add_time + "]";
    }
}
