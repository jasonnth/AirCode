package com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.guestidentity;

import android.os.Parcelable;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.interfaces.GuestIdentity;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class HomesGuestIdentity implements Parcelable {

    public static abstract class Builder {
        public abstract HomesGuestIdentity build();

        /* renamed from: id */
        public abstract Builder mo10936id(Integer num);

        public abstract Builder idType(String str);

        public abstract Builder isBooker(boolean z);
    }

    @JsonProperty("id")
    /* renamed from: id */
    public abstract Integer mo10931id();

    @JsonProperty("id_type")
    public abstract String idType();

    @JsonProperty("is_booker")
    public abstract boolean isBooker();

    public static Builder builder() {
        return new Builder();
    }

    public static HomesGuestIdentity fromGuestIdentity(GuestIdentity identity) {
        String idType;
        if (identity == null) {
            return null;
        }
        int id = identity.getId();
        boolean isBooker = identity.isBooker();
        switch (identity.getType()) {
            case Passport:
                idType = "passport";
                break;
            case ChineseNationalID:
                idType = "china_resident_identity_card";
                break;
            default:
                idType = "unknown";
                BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalArgumentException("unknown identification type: " + identity.getType().name()));
                break;
        }
        return builder().mo10936id(Integer.valueOf(id)).idType(idType).isBooker(isBooker).build();
    }
}
