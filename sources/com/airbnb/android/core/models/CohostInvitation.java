package com.airbnb.android.core.models;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.airdate.AirDateConstants;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.generated.GenCohostInvitation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class CohostInvitation extends GenCohostInvitation {
    public static final Creator<CohostInvitation> CREATOR = new Creator<CohostInvitation>() {
        public CohostInvitation[] newArray(int size) {
            return new CohostInvitation[size];
        }

        public CohostInvitation createFromParcel(Parcel source) {
            CohostInvitation object = new CohostInvitation();
            object.readFromParcel(source);
            return object;
        }
    };
    public static final String DAY = "day";
    public static final String HOUR = "hour";
    public static final String MINUTE = "minute";

    @Retention(RetentionPolicy.SOURCE)
    public @interface DayType {
    }

    public String getFormattedRemainingTime(Context context) {
        long minutes = getMinutesUntilExpiration();
        if (minutes >= AirDateConstants.MINUTES_PER_DAY) {
            int days = (int) (minutes / AirDateConstants.MINUTES_PER_DAY);
            return context.getResources().getQuantityString(C0716R.plurals.days_left_to_accept_cohosting_invitation, days, new Object[]{Integer.valueOf(days)});
        } else if (minutes >= 60) {
            int hours = (int) (minutes / 60);
            return context.getResources().getQuantityString(C0716R.plurals.hours_left_to_accept_cohosting_invitation, hours, new Object[]{Integer.valueOf(hours)});
        } else {
            return context.getResources().getQuantityString(C0716R.plurals.minutes_left_to_accept_cohosting_invitation, (int) minutes, new Object[]{Integer.valueOf((int) minutes)});
        }
    }
}
