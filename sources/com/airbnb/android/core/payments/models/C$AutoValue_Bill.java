package com.airbnb.android.core.payments.models;

import com.airbnb.android.core.payments.models.Bill.BillItem;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/* renamed from: com.airbnb.android.core.payments.models.$AutoValue_Bill reason: invalid class name */
abstract class C$AutoValue_Bill extends Bill {
    private final List<BillItem> billItems;
    private final List<BookingResult> bookingResults;
    private final RedirectSettings redirectSettings;
    private final long status;
    private final String token;
    private final long userId;

    /* renamed from: com.airbnb.android.core.payments.models.$AutoValue_Bill$Builder */
    static final class Builder extends com.airbnb.android.core.payments.models.Bill.Builder {
        private List<BillItem> billItems;
        private List<BookingResult> bookingResults;
        private RedirectSettings redirectSettings;
        private Long status;
        private String token;
        private Long userId;

        Builder() {
        }

        public com.airbnb.android.core.payments.models.Bill.Builder billItems(List<BillItem> billItems2) {
            if (billItems2 == null) {
                throw new NullPointerException("Null billItems");
            }
            this.billItems = billItems2;
            return this;
        }

        public com.airbnb.android.core.payments.models.Bill.Builder bookingResults(List<BookingResult> bookingResults2) {
            this.bookingResults = bookingResults2;
            return this;
        }

        public com.airbnb.android.core.payments.models.Bill.Builder status(long status2) {
            this.status = Long.valueOf(status2);
            return this;
        }

        public com.airbnb.android.core.payments.models.Bill.Builder token(String token2) {
            if (token2 == null) {
                throw new NullPointerException("Null token");
            }
            this.token = token2;
            return this;
        }

        public com.airbnb.android.core.payments.models.Bill.Builder userId(long userId2) {
            this.userId = Long.valueOf(userId2);
            return this;
        }

        public com.airbnb.android.core.payments.models.Bill.Builder redirectSettings(RedirectSettings redirectSettings2) {
            this.redirectSettings = redirectSettings2;
            return this;
        }

        public Bill build() {
            String missing = "";
            if (this.billItems == null) {
                missing = missing + " billItems";
            }
            if (this.status == null) {
                missing = missing + " status";
            }
            if (this.token == null) {
                missing = missing + " token";
            }
            if (this.userId == null) {
                missing = missing + " userId";
            }
            if (missing.isEmpty()) {
                return new AutoValue_Bill(this.billItems, this.bookingResults, this.status.longValue(), this.token, this.userId.longValue(), this.redirectSettings);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_Bill(List<BillItem> billItems2, List<BookingResult> bookingResults2, long status2, String token2, long userId2, RedirectSettings redirectSettings2) {
        if (billItems2 == null) {
            throw new NullPointerException("Null billItems");
        }
        this.billItems = billItems2;
        this.bookingResults = bookingResults2;
        this.status = status2;
        if (token2 == null) {
            throw new NullPointerException("Null token");
        }
        this.token = token2;
        this.userId = userId2;
        this.redirectSettings = redirectSettings2;
    }

    @JsonProperty("bill_items")
    public List<BillItem> billItems() {
        return this.billItems;
    }

    @JsonProperty("booking_results")
    public List<BookingResult> bookingResults() {
        return this.bookingResults;
    }

    @JsonProperty("status")
    public long status() {
        return this.status;
    }

    @JsonProperty("token")
    public String token() {
        return this.token;
    }

    @JsonProperty("user_id")
    public long userId() {
        return this.userId;
    }

    @JsonProperty("redirect_settings")
    public RedirectSettings redirectSettings() {
        return this.redirectSettings;
    }

    public String toString() {
        return "Bill{billItems=" + this.billItems + ", bookingResults=" + this.bookingResults + ", status=" + this.status + ", token=" + this.token + ", userId=" + this.userId + ", redirectSettings=" + this.redirectSettings + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Bill)) {
            return false;
        }
        Bill that = (Bill) o;
        if (this.billItems.equals(that.billItems()) && (this.bookingResults != null ? this.bookingResults.equals(that.bookingResults()) : that.bookingResults() == null) && this.status == that.status() && this.token.equals(that.token()) && this.userId == that.userId()) {
            if (this.redirectSettings == null) {
                if (that.redirectSettings() == null) {
                    return true;
                }
            } else if (this.redirectSettings.equals(that.redirectSettings())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((int) (((long) (((((int) (((long) (((((1 * 1000003) ^ this.billItems.hashCode()) * 1000003) ^ (this.bookingResults == null ? 0 : this.bookingResults.hashCode())) * 1000003)) ^ ((this.status >>> 32) ^ this.status))) * 1000003) ^ this.token.hashCode()) * 1000003)) ^ ((this.userId >>> 32) ^ this.userId))) * 1000003;
        if (this.redirectSettings != null) {
            i = this.redirectSettings.hashCode();
        }
        return h ^ i;
    }
}
