package com.airbnb.android.core.businesstravel.models;

import android.os.Parcelable;
import android.text.TextUtils;
import com.airbnb.android.core.enums.WorkEmailStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = Builder.class)
public abstract class BusinessTravelEmployee implements Parcelable {

    public static abstract class Builder {
        public abstract BusinessTravelEmployee build();

        @JsonProperty("admin")
        public abstract Builder setAdmin(boolean z);

        @JsonProperty("business_entity_id")
        public abstract Builder setBusinessEntityId(long j);

        @JsonProperty("email")
        public abstract Builder setEmail(String str);

        @JsonProperty("id")
        public abstract Builder setId(long j);

        @JsonProperty("third_party_bookable")
        public abstract Builder setThirdPartyBookable(boolean z);

        @JsonProperty("user_id")
        public abstract Builder setUserId(long j);

        @JsonProperty("verified")
        public abstract Builder setVerified(boolean z);
    }

    public abstract long getBusinessEntityId();

    public abstract String getEmail();

    public abstract long getId();

    public abstract long getUserId();

    public abstract boolean isAdmin();

    public abstract boolean isThirdPartyBookable();

    public abstract boolean isVerified();

    public static Builder builder() {
        return new Builder();
    }

    public WorkEmailStatus getWorkEmailStatus() {
        if (TextUtils.isEmpty(getEmail())) {
            return WorkEmailStatus.None;
        }
        if (isVerified()) {
            return WorkEmailStatus.Verified;
        }
        return WorkEmailStatus.Pending;
    }
}
