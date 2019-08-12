package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenSelectRoomMedia;

public class SelectRoomMedia extends GenSelectRoomMedia {
    private static final String COVER_TYPE_PRIMARY = "primary";
    public static final Creator<SelectRoomMedia> CREATOR = new Creator<SelectRoomMedia>() {
        public SelectRoomMedia[] newArray(int size) {
            return new SelectRoomMedia[size];
        }

        public SelectRoomMedia createFromParcel(Parcel source) {
            SelectRoomMedia object = new SelectRoomMedia();
            object.readFromParcel(source);
            return object;
        }
    };
    private static final String SHOT_TYPE_DETAIL = "detail";

    public boolean isPrimaryCoverPhoto() {
        return COVER_TYPE_PRIMARY.equals(this.mCover);
    }

    public boolean isDetailShot() {
        return SHOT_TYPE_DETAIL.equals(this.mShotType);
    }
}
