package p005cn.jpush.android.data;

import android.net.ParseException;
import com.facebook.appevents.AppEventsConstants;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.util.Logger;
import p005cn.jpush.android.util.StringUtils;

/* renamed from: cn.jpush.android.data.JPushLocalNotification */
public class JPushLocalNotification implements Serializable {
    private static final long serialVersionUID = 1472982106750878137L;
    private long broadCastTime = 0;
    private long builderId;
    private String content;
    private String contentType = "";
    private String date = "";
    private String extras;
    private String hour = "00";
    private String min = "00";
    private long notificationId = 1;
    private int notificationOnly = 1;
    private String overrideMessageId = "";
    private String title;
    private int type = 1;

    public String toJSON() {
        JSONObject obj = new JSONObject();
        try {
            JSONObject nContent = new JSONObject();
            if (!StringUtils.isEmpty(this.extras)) {
                nContent.put(Entity.KEY_NOTIFICATION_EXTRAS, new JSONObject(this.extras));
            }
            putStringIntoJSONObject(Entity.KEY_NOTI_CONTENT, this.content, nContent);
            putStringIntoJSONObject(Entity.KEY_NOTI_TITLE, this.title, nContent);
            putStringIntoJSONObject(Entity.KEY_NOTI_CONTENT, this.content, nContent);
            nContent.put(Entity.KEY_MSG_TYPE, 0);
            obj.put(Entity.KEY_MESSAGE_BODY_CONTENT, nContent);
            putStringIntoJSONObject("msg_id", "" + this.notificationId, obj);
            putStringIntoJSONObject(Entity.KEY_CONTENT_TYPE, this.contentType, obj);
            putStringIntoJSONObject(Entity.KEY_OVERRIDE_MESSAGE_ID, this.overrideMessageId, obj);
            obj.put(Entity.KEY_NOTIFICATION_ONLY, this.notificationOnly);
            obj.put(Entity.KEY_NOTIFICATION_ONLY_ID, this.builderId);
            obj.put(Entity.KEY_PROTOCOL_VERSION, 3);
            obj.put(Entity.KEY_NOTIFICATION_TYPE, 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj.toString();
    }

    private void putStringIntoJSONObject(String key, String str, JSONObject obj) throws JSONException {
        if (!StringUtils.isEmpty(str)) {
            obj.put(key, str);
        }
    }

    public void setNotificationId(long notificationId2) {
        this.notificationId = notificationId2;
    }

    public long getNotificationId() {
        return this.notificationId;
    }

    public void setBroadcastTime(long broadCastTime2) {
        this.broadCastTime = broadCastTime2;
    }

    public void setBroadcastTime(Date date2) {
        this.broadCastTime = date2.getTime();
    }

    public void setBroadcastTime(int year, int month, int day, int hour2, int minute, int second) {
        if (year < 0 || month < 1 || month > 12 || day < 1 || day > 31 || hour2 < 0 || hour2 > 23 || minute < 0 || minute > 59 || second < 0 || second > 59) {
            Logger.m1422ee("JPushLocalNotification", "Set time fail! Please check your args!");
            return;
        }
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day, hour2, minute, second);
        Date date2 = cal.getTime();
        long curMis = System.currentTimeMillis();
        if (date2.getTime() < curMis) {
            this.broadCastTime = curMis;
        } else {
            this.broadCastTime = date2.getTime();
        }
    }

    public long getBroadcastTime() {
        return this.broadCastTime;
    }

    public void setExtras(String extras2) {
        this.extras = extras2;
    }

    public String getExtras() {
        return this.extras;
    }

    /* access modifiers changed from: protected */
    public int getType() {
        return this.type;
    }

    /* access modifiers changed from: protected */
    public void setType(int paramInt) {
        this.type = paramInt;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String paramString) {
        this.title = paramString;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String paramString) {
        this.content = paramString;
    }

    /* access modifiers changed from: protected */
    public String getHour() {
        if (this.hour.length() < 1) {
            return "00";
        }
        if (this.hour.length() <= 0 || this.hour.length() >= 2) {
            return this.hour;
        }
        return AppEventsConstants.EVENT_PARAM_VALUE_NO + this.hour;
    }

    /* access modifiers changed from: protected */
    public void setHour(String paramString) {
        this.hour = paramString;
    }

    /* access modifiers changed from: protected */
    public String getMin() {
        if (this.min.length() < 1) {
            return "00";
        }
        if (this.min.length() <= 0 || this.min.length() >= 2) {
            return this.min;
        }
        return AppEventsConstants.EVENT_PARAM_VALUE_NO + this.min;
    }

    /* access modifiers changed from: protected */
    public void setMin(String paramString) {
        this.min = paramString;
    }

    public long getBuilderId() {
        return this.builderId;
    }

    public void setBuilderId(long paramLong) {
        this.builderId = paramLong;
    }

    /* access modifiers changed from: protected */
    public String getDate() {
        if (!StringUtils.isEmpty(this.date)) {
            try {
                this.date = this.date.substring(0, 8);
                long parseLong = Long.parseLong(this.date);
                SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                localSimpleDateFormat.setLenient(false);
                localSimpleDateFormat.parse(this.date);
            } catch (ParseException e) {
                return new SimpleDateFormat("yyyyMMdd").format(new Date());
            } catch (Exception e2) {
                return new SimpleDateFormat("yyyyMMdd").format(new Date());
            }
        }
        return this.date;
    }

    /* access modifiers changed from: protected */
    public void setDate(String paramString) {
        this.date = paramString;
    }

    public int hashCode() {
        return ("" + this.notificationId).hashCode();
    }

    public boolean equals(Object o) {
        return this.notificationId == ((JPushLocalNotification) o).notificationId;
    }
}
