package com.mparticle.messaging;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mparticle.MPService;
import com.mparticle.internal.MPUtility;
import java.util.UUID;

public class CloudAction implements Parcelable {
    public static final Creator<CloudAction> CREATOR = new Creator<CloudAction>() {
        /* renamed from: a */
        public CloudAction createFromParcel(Parcel parcel) {
            return new CloudAction(parcel);
        }

        /* renamed from: a */
        public CloudAction[] newArray(int i) {
            return new CloudAction[i];
        }
    };
    private final String mActionActivity;
    private final String mActionIcon;
    private final String mActionId;
    private final String mActionIdentifier;
    private final String mActionTitle;

    public CloudAction(String actionId, String actionIcon, String actionTitle, String actionActivity) {
        this.mActionIcon = actionIcon;
        this.mActionTitle = actionTitle;
        this.mActionActivity = actionActivity;
        this.mActionId = actionId;
        if (!MPUtility.isEmpty(this.mActionId)) {
            this.mActionIdentifier = this.mActionId;
        } else if (!MPUtility.isEmpty(this.mActionTitle)) {
            this.mActionIdentifier = this.mActionTitle;
        } else if (!MPUtility.isEmpty(this.mActionIcon)) {
            this.mActionIdentifier = this.mActionIcon;
        } else {
            this.mActionIdentifier = UUID.randomUUID().toString();
        }
    }

    public CloudAction(Parcel source) {
        this.mActionId = source.readString();
        this.mActionIcon = source.readString();
        this.mActionTitle = source.readString();
        this.mActionActivity = source.readString();
        this.mActionIdentifier = source.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mActionId);
        dest.writeString(this.mActionIcon);
        dest.writeString(this.mActionTitle);
        dest.writeString(this.mActionActivity);
        dest.writeString(this.mActionIdentifier);
    }

    public PendingIntent getIntent(Context context, AbstractCloudMessage message, CloudAction action) {
        PendingIntent pendingIntent = null;
        if (this.mActionActivity != null) {
            try {
                Intent intent = new Intent(MPService.INTERNAL_NOTIFICATION_TAP);
                intent.setClass(context, Class.forName(this.mActionActivity));
                intent.putExtra(MPMessagingAPI.CLOUD_MESSAGE_EXTRA, message);
                intent.putExtra(MPMessagingAPI.CLOUD_ACTION_EXTRA, action);
                intent.addFlags(268435456);
                pendingIntent = PendingIntent.getActivity(context, action.getActionIdentifier().hashCode(), intent, 134217728);
            } catch (Exception e) {
            }
        }
        if (pendingIntent != null) {
            return pendingIntent;
        }
        Intent defaultOpenIntent = message.getDefaultOpenIntent(context, message);
        defaultOpenIntent.addFlags(268435456);
        defaultOpenIntent.addFlags(67108864);
        defaultOpenIntent.putExtra(MPMessagingAPI.CLOUD_ACTION_EXTRA, action);
        return PendingIntent.getActivity(context, 0, defaultOpenIntent, 134217728);
    }

    public String getIconId() {
        return this.mActionIcon;
    }

    public int getIconId(Context context) {
        if (!MPUtility.isEmpty(this.mActionIcon)) {
            int identifier = context.getResources().getIdentifier(this.mActionIcon, "drawable", context.getPackageName());
            if (identifier > 0) {
                return identifier;
            }
        }
        return 0;
    }

    public String getTitle() {
        return this.mActionTitle;
    }

    public String getActionIdentifier() {
        return this.mActionIdentifier;
    }

    public int getActionIdInt() {
        try {
            return Integer.parseInt(getActionIdentifier());
        } catch (Exception e) {
            return -1;
        }
    }
}
