package com.airbnb.android.core.models;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.enums.ListingStatus;
import com.airbnb.android.core.enums.SpaceType;
import com.airbnb.android.core.models.generated.GenListingPickerInfo;
import com.facebook.common.util.UriUtil;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ListingPickerInfo extends GenListingPickerInfo {
    public static final Creator<ListingPickerInfo> CREATOR = new Creator<ListingPickerInfo>() {
        public ListingPickerInfo[] newArray(int size) {
            return new ListingPickerInfo[size];
        }

        public ListingPickerInfo createFromParcel(Parcel source) {
            ListingPickerInfo object = new ListingPickerInfo();
            object.readFromParcel(source);
            return object;
        }
    };

    @JsonProperty("status")
    public void setStatus(String status) {
        super.setStatus(ListingStatus.fromKey(status));
    }

    public String getThumbnailUrl() {
        if (!this.mThumbnailUrl.startsWith(UriUtil.HTTP_SCHEME)) {
            return null;
        }
        return super.getThumbnailUrl();
    }

    public boolean hasPublishedFixItReport() {
        return this.mFixItReport != null && this.mFixItReport.getStatus() == 1;
    }

    public boolean isSnoozed() {
        return getSnoozeMode() != null;
    }

    public double getPercentCompleted() {
        return 100.0d - ((((double) getStepsRemaining()) * 100.0d) / ((double) Math.max(getStepsRemaining(), getStepsTotal())));
    }

    public String getNameOrPlaceholder(Context context) {
        if (!TextUtils.isEmpty(getName())) {
            return getName();
        }
        String roomType = context.getString(SpaceType.getTypeFromKey(getRoomTypeCategory()).titleId);
        return context.getString(C0716R.string.spaces_room_type_in_city, new Object[]{roomType, getCity()});
    }

    public int getCollectionApplicationStatus() {
        if (this.mCollectionsApplication != null) {
            return this.mCollectionsApplication.getStatus();
        }
        return -1;
    }
}
