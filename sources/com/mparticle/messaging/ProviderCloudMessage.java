package com.mparticle.messaging;

import android.app.Notification;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.p000v4.app.NotificationCompat.Builder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProviderCloudMessage extends AbstractCloudMessage {
    public static final Creator<ProviderCloudMessage> CREATOR = new Creator<ProviderCloudMessage>() {
        /* renamed from: a */
        public ProviderCloudMessage createFromParcel(Parcel parcel) {
            return new ProviderCloudMessage(parcel);
        }

        /* renamed from: a */
        public ProviderCloudMessage[] newArray(int i) {
            return new ProviderCloudMessage[i];
        }
    };
    private final String mPrimaryText;

    public ProviderCloudMessage(Bundle extras, JSONArray pushKeys) {
        super(extras);
        this.mPrimaryText = findProviderMessage(pushKeys);
    }

    public ProviderCloudMessage(Parcel pc) {
        super(pc);
        this.mPrimaryText = pc.readString();
    }

    /* access modifiers changed from: protected */
    public CloudAction getDefaultAction() {
        return new CloudAction(Integer.toString(getId()), null, this.mPrimaryText, null);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.mPrimaryText);
    }

    public int getId() {
        return this.mPrimaryText.hashCode();
    }

    public String getPrimaryMessage(Context context) {
        return this.mPrimaryText;
    }

    public JSONObject getRedactedJsonPayload() {
        JSONObject jSONObject = new JSONObject();
        for (String str : this.mExtras.keySet()) {
            try {
                if (VERSION.SDK_INT >= 19) {
                    jSONObject.put(str, JSONObject.wrap(this.mExtras.get(str)));
                } else {
                    jSONObject.put(str, this.mExtras.get(str));
                }
            } catch (JSONException e) {
            }
        }
        return jSONObject;
    }

    public Notification buildNotification(Context context) {
        Notification build = new Builder(context).setContentIntent(getLoopbackIntent(context, this, getDefaultAction())).setSmallIcon(AbstractCloudMessage.getFallbackIcon(context)).setTicker(this.mPrimaryText).setContentTitle(AbstractCloudMessage.getFallbackTitle(context)).setContentText(this.mPrimaryText).build();
        build.flags |= 16;
        return build;
    }

    private String findProviderMessage(JSONArray possibleKeys) {
        if (possibleKeys != null) {
            for (int i = 0; i < possibleKeys.length(); i++) {
                try {
                    String string = this.mExtras.getString(possibleKeys.getString(i));
                    if (string != null && string.length() > 0) {
                        this.mExtras.remove(possibleKeys.getString(i));
                        return string;
                    }
                } catch (JSONException e) {
                }
            }
        }
        return "";
    }
}
