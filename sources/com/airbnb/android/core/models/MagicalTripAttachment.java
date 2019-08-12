package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenMagicalTripAttachment;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class MagicalTripAttachment extends GenMagicalTripAttachment {
    public static final Creator<MagicalTripAttachment> CREATOR = new Creator<MagicalTripAttachment>() {
        public MagicalTripAttachment[] newArray(int size) {
            return new MagicalTripAttachment[size];
        }

        public MagicalTripAttachment createFromParcel(Parcel source) {
            MagicalTripAttachment object = new MagicalTripAttachment();
            object.readFromParcel(source);
            return object;
        }
    };

    public List<Long> getGuestsIds() {
        return getRoleUserIds(TripRole.ROLE_KEY_GUEST);
    }

    public List<Long> getHostIds() {
        return getRoleUserIds(TripRole.ROLE_KEY_HOST);
    }

    private List<Long> getRoleUserIds(String roleKey) {
        for (TripRole role : getRoles()) {
            if (roleKey.equals(role.getRole())) {
                return role.getUserIds();
            }
        }
        throw new IllegalStateException("Role type not found: " + roleKey);
    }

    @JsonProperty("status")
    public void setStatus(String value) {
        super.setStatus(TripStatus.fromKey(value));
    }

    public MagicalTripAttachmentType getTypeEnum() {
        return MagicalTripAttachmentType.getType(getType());
    }
}
