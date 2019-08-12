package com.airbnb.android.lib.payments.networking.requests.requestbodies.params.trips;

import com.airbnb.android.core.models.TripSecondaryGuest;
import com.airbnb.android.core.payments.models.BillProductType;
import com.airbnb.android.lib.payments.networking.requests.requestbodies.params.ProductParam;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class TripsProductParam extends ProductParam {

    public static abstract class Builder {
        /* access modifiers changed from: 0000 */
        public abstract TripsProductParam autoBuild();

        public abstract Builder couponCode(String str);

        public abstract Builder numberOfGuests(int i);

        /* access modifiers changed from: 0000 */
        public abstract Builder productType(String str);

        public abstract Builder secondaryGuestInfos(List<TripSecondaryGuest> list);

        public abstract Builder templateId(long j);

        public TripsProductParam build() {
            productType(BillProductType.Trip.getServerKey());
            return autoBuild();
        }
    }

    @JsonProperty("coupon_code")
    public abstract String couponCode();

    @JsonProperty("number_of_guests")
    public abstract int numberOfGuests();

    @JsonProperty("secondary_guest_infos")
    public abstract List<TripSecondaryGuest> secondaryGuestInfos();

    @JsonProperty("mt_scheduled_template_id")
    public abstract long templateId();

    public static Builder builder() {
        return new Builder();
    }
}
