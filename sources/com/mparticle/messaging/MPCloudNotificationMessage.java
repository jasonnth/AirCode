package com.mparticle.messaging;

import android.app.Notification;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.p000v4.app.NotificationCompat.BigPictureStyle;
import android.support.p000v4.app.NotificationCompat.BigTextStyle;
import android.support.p000v4.app.NotificationCompat.Builder;
import android.support.p000v4.app.NotificationCompat.InboxStyle;
import com.airbnb.android.lib.fragments.verifiedid.OfficialIdPhotoSelectionFragment;
import com.miteksystems.misnap.params.SDKConstants;
import com.mparticle.internal.MPUtility;
import com.mparticle.messaging.AbstractCloudMessage.InvalidGcmMessageException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONObject;

public class MPCloudNotificationMessage extends AbstractCloudMessage {
    private static final String ACTION_1_ACTIVITY = "m_a1_act";
    private static final String ACTION_1_ICON = "m_a1_ai";
    private static final String ACTION_1_ID = "m_a1_aid";
    private static final String ACTION_1_TITLE = "m_a1_at";
    private static final String ACTION_2_ACTIVITY = "m_a2_act";
    private static final String ACTION_2_ICON = "m_a2_ai";
    private static final String ACTION_2_ID = "m_a2_aid";
    private static final String ACTION_2_TITLE = "m_a2_at";
    private static final String ACTION_3_ACTIVITY = "m_a3_act";
    private static final String ACTION_3_ICON = "m_a3_ai";
    private static final String ACTION_3_ID = "m_a3_aid";
    private static final String ACTION_3_TITLE = "m_a3_at";
    private static final String ALERT_ONCE = "m_ao";
    private static final String BIG_IMAGE = "m_bi";
    private static final String BIG_TEXT = "m_bt";
    private static final String CAMPAIGN_ID = "m_cid";
    public static final String COMMAND = "m_cmd";
    public static final int COMMAND_ALERT_BACKGROUND = 3;
    public static final int COMMAND_ALERT_CONFIG_REFRESH = 4;
    public static final int COMMAND_ALERT_LOCALTIME = 2;
    public static final int COMMAND_ALERT_NOW = 1;
    public static final int COMMAND_DONOTHING = 0;
    private static final String CONTENT_ID = "m_cntid";
    public static final Creator<MPCloudNotificationMessage> CREATOR = new Creator<MPCloudNotificationMessage>() {
        /* renamed from: a */
        public MPCloudNotificationMessage createFromParcel(Parcel parcel) {
            return new MPCloudNotificationMessage(parcel);
        }

        /* renamed from: a */
        public MPCloudNotificationMessage[] newArray(int i) {
            return new MPCloudNotificationMessage[i];
        }
    };
    private static final String DEFAULT_ACTIVITY = "m_dact";
    private static final String EXPIRATION = "m_expy";
    private static final String GROUP = "m_g";
    private static final String INAPP_MESSAGE_THEME = "m_iamt";
    private static final String INBOX_TEXT_1 = "m_ib_1";
    private static final String INBOX_TEXT_2 = "m_ib_2";
    private static final String INBOX_TEXT_3 = "m_ib_3";
    private static final String INBOX_TEXT_4 = "m_ib_4";
    private static final String INBOX_TEXT_5 = "m_ib_5";
    private static final String LARGE_ICON = "m_li";
    private static final String LIGHTS_COLOR = "m_l_c";
    private static final String LIGHTS_OFF_MILLIS = "m_l_off";
    private static final String LIGHTS_ON_MILLIS = "m_l_on";
    private static final String LOCAL_DELIVERY_TIME = "m_ldt";
    private static final String NUMBER = "m_n";
    private static final String PRIMARY_MESSAGE = "m_m";
    private static final String PRIORITY = "m_p";
    private static final String SECONDARY_MESSAGE = "m_sm";
    private static final String SHOW_INAPP_MESSAGE = "m_sia";
    private static final String SMALL_ICON = "m_si";
    private static final String SOUND = "m_s";
    private static final String TITLE = "m_t";
    private static final String TITLE_EXPANDED = "m_xt";
    private static final String VIBRATION_PATTERN = "m_v";
    private CloudAction[] mActions;

    public MPCloudNotificationMessage(Parcel pc) {
        super(pc);
        this.mActions = new CloudAction[3];
        pc.readTypedArray(this.mActions, CloudAction.CREATOR);
    }

    public MPCloudNotificationMessage(Bundle extras) throws InvalidGcmMessageException {
        super(extras);
        if (getExpiration() <= System.currentTimeMillis()) {
            throw new InvalidGcmMessageException("GCM message is expired.");
        }
        this.mActions = new CloudAction[3];
        try {
            if (this.mExtras.containsKey(ACTION_1_ICON) || this.mExtras.containsKey(ACTION_1_TITLE)) {
                this.mActions[0] = new CloudAction(this.mExtras.getString(ACTION_1_ID), this.mExtras.getString(ACTION_1_ICON), this.mExtras.getString(ACTION_1_TITLE), this.mExtras.getString(ACTION_1_ACTIVITY));
            }
            if (this.mExtras.containsKey(ACTION_2_ICON) || this.mExtras.containsKey(ACTION_2_TITLE)) {
                this.mActions[1] = new CloudAction(this.mExtras.getString(ACTION_2_ID), this.mExtras.getString(ACTION_2_ICON), this.mExtras.getString(ACTION_2_TITLE), this.mExtras.getString(ACTION_2_ACTIVITY));
            }
            if (this.mExtras.containsKey(ACTION_3_ICON) || this.mExtras.containsKey(ACTION_3_TITLE)) {
                this.mActions[2] = new CloudAction(this.mExtras.getString(ACTION_3_ID), this.mExtras.getString(ACTION_3_ICON), this.mExtras.getString(ACTION_3_TITLE), this.mExtras.getString(ACTION_3_ACTIVITY));
            }
        } catch (Exception e) {
            throw new InvalidGcmMessageException(e.getMessage());
        }
    }

    private String getDefaultActivity() {
        return this.mExtras.getString(DEFAULT_ACTIVITY);
    }

    /* access modifiers changed from: protected */
    public CloudAction getDefaultAction() {
        return new CloudAction(Integer.toString(getContentId()), null, null, getDefaultActivity());
    }

    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeTypedArray(this.mActions, 0);
    }

    public int getId() {
        return getContentId();
    }

    public JSONObject getRedactedJsonPayload() {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.put(CAMPAIGN_ID, getCampaignId());
            jSONObject.put(CONTENT_ID, getContentId());
            jSONObject2.put("data", jSONObject);
        } catch (Exception e) {
        }
        return jSONObject2;
    }

    public boolean shouldDisplay() {
        return getCommand() == 1;
    }

    public int getSmallIconResourceId(Context context) {
        String string = this.mExtras.getString(SMALL_ICON);
        if (!MPUtility.isEmpty(string)) {
            int identifier = context.getResources().getIdentifier(string, "drawable", context.getPackageName());
            if (identifier > 0) {
                return identifier;
            }
        }
        return AbstractCloudMessage.getFallbackIcon(context);
    }

    public String getContentTitle(Context context) {
        String string = this.mExtras.getString(TITLE);
        if (MPUtility.isEmpty(string)) {
            return AbstractCloudMessage.getFallbackTitle(context);
        }
        return string;
    }

    public String getPrimaryMessage(Context context) {
        String string = this.mExtras.getString(PRIMARY_MESSAGE);
        if (MPUtility.isEmpty(string)) {
            return AbstractCloudMessage.getFallbackTitle(context);
        }
        return string;
    }

    public boolean getShowInApp() {
        return Boolean.parseBoolean(this.mExtras.getString(SHOW_INAPP_MESSAGE));
    }

    public String getInAppTheme() {
        return this.mExtras.getString(INAPP_MESSAGE_THEME);
    }

    public String getSubText() {
        return this.mExtras.getString(SECONDARY_MESSAGE);
    }

    public Bitmap getLargeIcon(Context context) {
        Bitmap bitmap = null;
        String string = this.mExtras.getString(LARGE_ICON);
        if (!MPUtility.isEmpty(string)) {
            if (string.contains("http:") || string.contains("https:")) {
                try {
                    URLConnection openConnection = new URL(string).openConnection();
                    openConnection.setConnectTimeout(OfficialIdPhotoSelectionFragment.MAX_IMAGE_SIZE);
                    openConnection.setReadTimeout(SDKConstants.CAM_INIT_CAMERA);
                    bitmap = BitmapFactory.decodeStream(openConnection.getInputStream());
                } catch (Exception e) {
                }
            } else {
                int identifier = context.getResources().getIdentifier(string, "drawable", context.getPackageName());
                if (identifier > 0) {
                    bitmap = BitmapFactory.decodeResource(context.getResources(), identifier);
                }
            }
            if (bitmap != null) {
                return bitmap;
            }
        }
        int i = -1;
        try {
            i = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).icon;
        } catch (NameNotFoundException e2) {
        }
        Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), i);
        if (decodeResource == null) {
            return BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier("ic_dialog_alert", "drawable", "android"));
        }
        return decodeResource;
    }

    public int getLightColorArgb() {
        try {
            return Integer.parseInt(this.mExtras.getString(LIGHTS_COLOR));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public int getLightOffMillis() {
        try {
            return Integer.parseInt(this.mExtras.getString(LIGHTS_OFF_MILLIS));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public int getLightOnMillis() {
        try {
            return Integer.parseInt(this.mExtras.getString(LIGHTS_ON_MILLIS));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public int getNumber() {
        try {
            return Integer.parseInt(this.mExtras.getString(NUMBER));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public boolean getAlertOnlyOnce() {
        if (this.mExtras.containsKey(ALERT_ONCE)) {
            return Boolean.parseBoolean(this.mExtras.getString(ALERT_ONCE));
        }
        return true;
    }

    public Integer getPriority() {
        Integer num = null;
        if (!this.mExtras.containsKey(PRIORITY)) {
            return num;
        }
        try {
            return Integer.valueOf(Integer.parseInt(this.mExtras.getString(PRIORITY)));
        } catch (NumberFormatException e) {
            return num;
        }
    }

    public Uri getSound(Context context) {
        if (!MPUtility.isEmpty(this.mExtras.getString(SOUND))) {
            return Uri.parse("android.resource://" + context.getPackageName() + "/raw/" + this.mExtras.getString(SOUND));
        }
        return null;
    }

    public Bitmap getBigPicture(Context context) {
        String string = this.mExtras.getString(BIG_IMAGE);
        if (!MPUtility.isEmpty(string)) {
            try {
                URLConnection openConnection = new URL(string).openConnection();
                openConnection.setConnectTimeout(OfficialIdPhotoSelectionFragment.MAX_IMAGE_SIZE);
                openConnection.setReadTimeout(SDKConstants.CAM_INIT_CAMERA);
                return BitmapFactory.decodeStream(openConnection.getInputStream());
            } catch (Exception e) {
            }
        }
        return null;
    }

    public String getExpandedTitle() {
        return this.mExtras.getString(TITLE_EXPANDED);
    }

    public String getBigText() {
        return this.mExtras.getString(BIG_TEXT);
    }

    public ArrayList<String> getInboxLines() {
        ArrayList<String> arrayList = new ArrayList<>();
        String string = this.mExtras.getString(INBOX_TEXT_1);
        if (!MPUtility.isEmpty(string)) {
            arrayList.add(string);
        }
        String string2 = this.mExtras.getString(INBOX_TEXT_2);
        if (!MPUtility.isEmpty(string2)) {
            arrayList.add(string2);
        }
        String string3 = this.mExtras.getString(INBOX_TEXT_3);
        if (!MPUtility.isEmpty(string3)) {
            arrayList.add(string3);
        }
        String string4 = this.mExtras.getString(INBOX_TEXT_4);
        if (!MPUtility.isEmpty(string4)) {
            arrayList.add(string4);
        }
        String string5 = this.mExtras.getString(INBOX_TEXT_5);
        if (!MPUtility.isEmpty(string5)) {
            arrayList.add(string5);
        }
        return arrayList;
    }

    public long[] getVibrationPattern() {
        String string = this.mExtras.getString(VIBRATION_PATTERN);
        if (!MPUtility.isEmpty(string)) {
            try {
                String[] split = string.split(",");
                long[] jArr = new long[split.length];
                int i = 0;
                for (String trim : split) {
                    jArr[i] = Long.parseLong(trim.trim());
                    i++;
                }
                return jArr;
            } catch (Exception e) {
            }
        }
        return null;
    }

    public Notification buildNotification(Context context) {
        CloudAction[] cloudActionArr;
        Builder builder = new Builder(context);
        builder.setSmallIcon(getSmallIconResourceId(context));
        builder.setContentTitle(getContentTitle(context));
        String primaryMessage = getPrimaryMessage(context);
        builder.setContentText(primaryMessage);
        builder.setTicker(primaryMessage);
        builder.setSubText(getSubText());
        builder.setLargeIcon(getLargeIcon(context));
        int lightColorArgb = getLightColorArgb();
        if (lightColorArgb > 0) {
            builder.setLights(lightColorArgb, getLightOnMillis(), getLightOffMillis());
        }
        int number = getNumber();
        if (number > 0) {
            builder.setNumber(number);
        }
        builder.setOnlyAlertOnce(getAlertOnlyOnce());
        Integer priority = getPriority();
        if (priority != null) {
            builder.setPriority(priority.intValue());
        }
        Uri sound = getSound(context);
        if (sound != null) {
            builder.setSound(sound);
        }
        Bitmap bigPicture = getBigPicture(context);
        ArrayList inboxLines = getInboxLines();
        String expandedTitle = getExpandedTitle();
        if (bigPicture != null) {
            BigPictureStyle bigPictureStyle = new BigPictureStyle();
            bigPictureStyle.bigPicture(bigPicture);
            if (!MPUtility.isEmpty(expandedTitle)) {
                bigPictureStyle.setBigContentTitle(expandedTitle);
            }
            builder.setStyle(bigPictureStyle);
        } else if (inboxLines == null || inboxLines.size() <= 0) {
            String bigText = getBigText();
            BigTextStyle bigTextStyle = new BigTextStyle();
            if (MPUtility.isEmpty(bigText)) {
                bigText = getPrimaryMessage(context);
            }
            BigTextStyle bigText2 = bigTextStyle.bigText(bigText);
            if (!MPUtility.isEmpty(expandedTitle)) {
                bigText2.setBigContentTitle(expandedTitle);
            }
            builder.setStyle(bigText2);
        } else {
            InboxStyle inboxStyle = new InboxStyle();
            Iterator it = inboxLines.iterator();
            while (it.hasNext()) {
                inboxStyle.addLine((String) it.next());
            }
            if (!MPUtility.isEmpty(expandedTitle)) {
                inboxStyle.setBigContentTitle(expandedTitle);
            }
            builder.setStyle(inboxStyle);
        }
        if (MPUtility.checkPermission(context, "android.permission.VIBRATE")) {
            long[] vibrationPattern = getVibrationPattern();
            if (vibrationPattern != null && vibrationPattern.length > 0) {
                builder.setVibrate(vibrationPattern);
            }
        }
        if (this.mActions != null) {
            for (CloudAction cloudAction : this.mActions) {
                if (cloudAction != null) {
                    builder.addAction(cloudAction.getIconId(context), cloudAction.getTitle(), getLoopbackIntent(context, this, cloudAction));
                }
            }
        }
        builder.setContentIntent(getLoopbackIntent(context, this, getDefaultAction()));
        return builder.build();
    }

    public int getCommand() {
        return Integer.parseInt(this.mExtras.getString(COMMAND));
    }

    public static boolean isValid(Bundle extras) {
        return extras.containsKey(CAMPAIGN_ID);
    }

    public CloudAction[] getActions() {
        return this.mActions;
    }

    public int getContentId() {
        return Integer.parseInt(this.mExtras.getString(CONTENT_ID));
    }

    public int getCampaignId() {
        return Integer.parseInt(this.mExtras.getString(CAMPAIGN_ID));
    }

    public long getExpiration() {
        return Long.parseLong(this.mExtras.getString(EXPIRATION));
    }

    public boolean isDelayed() {
        return getCommand() == 2;
    }

    public long getDeliveryTime() {
        String string = this.mExtras.getString(LOCAL_DELIVERY_TIME);
        long currentTimeMillis = System.currentTimeMillis();
        if (MPUtility.isEmpty(string)) {
            return currentTimeMillis;
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(string).getTime();
        } catch (ParseException e) {
            return currentTimeMillis;
        }
    }
}
