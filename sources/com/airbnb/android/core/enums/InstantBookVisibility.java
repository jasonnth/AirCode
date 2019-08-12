package com.airbnb.android.core.enums;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.C0716R;
import com.facebook.internal.NativeProtocol;
import java.util.ArrayList;
import java.util.List;

public enum InstantBookVisibility implements Parcelable {
    Off(C0716R.string.no_one, C0716R.string.ib_visibility_desc_no_one, false, false, "off", Integer.valueOf(C0716R.string.booking_settings_noone)),
    Everyone(C0716R.string.ml_ib_visibility_everyone, C0716R.string.ib_visibility_desc_everyone, true, false, NativeProtocol.AUDIENCE_EVERYONE, Integer.valueOf(C0716R.string.booking_settings_everyone)),
    Experienced(C0716R.string.ml_ib_visibility_experienced, C0716R.string.ib_visibility_desc_certain_guests, true, false, "experienced", Integer.valueOf(C0716R.string.booking_settings_experienced)),
    Friends(C0716R.string.ml_ib_visibility_friends, C0716R.string.ib_visibility_desc_certain_guests, true, true, NativeProtocol.AUDIENCE_FRIENDS, null);
    
    public static final Creator<InstantBookVisibility> CREATOR = null;
    public static final InstantBookVisibility DEFAULT = null;
    public final int descriptionId;
    public final boolean isDeprecated;
    public final boolean isUserFacing;
    public final Integer newListingTitleId;
    public final String serverDescKey;
    public final int titleId;

    static {
        DEFAULT = Everyone;
        CREATOR = new Creator<InstantBookVisibility>() {
            public InstantBookVisibility createFromParcel(Parcel source) {
                return InstantBookVisibility.values()[source.readInt()];
            }

            public InstantBookVisibility[] newArray(int size) {
                return new InstantBookVisibility[size];
            }
        };
    }

    private InstantBookVisibility(int titleId2, int descriptionId2, boolean isUserFacing2, boolean isDeprecated2, String serverDescKey2, Integer newListingTitleId2) {
        this.titleId = titleId2;
        this.descriptionId = descriptionId2;
        this.isUserFacing = isUserFacing2;
        this.isDeprecated = isDeprecated2;
        this.serverDescKey = serverDescKey2;
        this.newListingTitleId = newListingTitleId2;
    }

    public static List<InstantBookVisibility> userFacingValues(InstantBookVisibility currentVisibility) {
        InstantBookVisibility[] values;
        List<InstantBookVisibility> values2 = new ArrayList<>();
        for (InstantBookVisibility value : values()) {
            if (value.isUserFacing && (!value.isDeprecated || value.serverDescKey.equals(currentVisibility.serverDescKey))) {
                values2.add(value);
            }
        }
        return values2;
    }

    public static InstantBookVisibility getType(int index) {
        InstantBookVisibility[] values = values();
        if (index < 0 || index >= values.length) {
            return null;
        }
        return values[index];
    }

    public static InstantBookVisibility getTypeFromKey(String roomTypeKey) {
        InstantBookVisibility[] values;
        for (InstantBookVisibility space : values()) {
            if (space.serverDescKey.equalsIgnoreCase(roomTypeKey)) {
                return space;
            }
        }
        return null;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ordinal());
    }
}
