package p005cn.jpush.android.data;

import android.support.p000v4.app.NotificationCompat;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.util.StringUtils;

/* renamed from: cn.jpush.android.data.JLog */
public class JLog {
    public static final String JPUSH_SEPARATOR = "#jpush#";
    public int jLog_level;
    public String jLog_levelStr;
    public String jLog_msg;
    public String jLog_tag;
    public String jLog_time;

    public JLog() {
    }

    public JLog(int level, String levelStr, String tag, String msg, String time) {
        this.jLog_level = level;
        this.jLog_levelStr = levelStr;
        this.jLog_msg = msg;
        this.jLog_tag = tag;
        this.jLog_time = time;
    }

    public String toString() {
        if (this.jLog_msg != null && this.jLog_msg.contains("\\n")) {
            this.jLog_msg.replaceAll("\\n", JPUSH_SEPARATOR);
        }
        StringBuffer sbf = new StringBuffer();
        sbf.append(this.jLog_level).append("  ");
        sbf.append(this.jLog_levelStr).append("  ");
        sbf.append(this.jLog_time).append("  ");
        sbf.append(this.jLog_tag).append("  ");
        sbf.append(this.jLog_msg).append("  ");
        return sbf.toString();
    }

    public boolean equals(Object o) {
        return super.equals(o);
    }

    public int hashCode() {
        return super.hashCode();
    }

    public int getLogSize() {
        return toString().getBytes().length;
    }

    private boolean isValid() {
        if (StringUtils.isEmpty(this.jLog_time) || StringUtils.isEmpty(this.jLog_levelStr) || StringUtils.isEmpty(this.jLog_tag) || StringUtils.isEmpty(this.jLog_msg)) {
            return false;
        }
        return true;
    }

    public JSONObject getJSONObjectLog() {
        JSONObject logJson = new JSONObject();
        if (!isValid()) {
            return null;
        }
        try {
            logJson.put("level", this.jLog_level);
            logJson.put("levelstr", this.jLog_levelStr);
            logJson.put("time", this.jLog_time);
            logJson.put("tag", this.jLog_tag);
            logJson.put(NotificationCompat.CATEGORY_MESSAGE, this.jLog_msg);
            return logJson;
        } catch (JSONException e) {
            return null;
        }
    }
}
