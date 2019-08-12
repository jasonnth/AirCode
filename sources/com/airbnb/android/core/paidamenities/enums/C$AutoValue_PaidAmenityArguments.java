package com.airbnb.android.core.paidamenities.enums;

import com.airbnb.android.core.enums.HelpCenterArticle;

/* renamed from: com.airbnb.android.core.paidamenities.enums.$AutoValue_PaidAmenityArguments reason: invalid class name */
abstract class C$AutoValue_PaidAmenityArguments extends PaidAmenityArguments {
    private final String confirmationCode;
    private final boolean hasPaidAmenityOrders;
    private final long listingId;
    private final long threadId;

    /* renamed from: com.airbnb.android.core.paidamenities.enums.$AutoValue_PaidAmenityArguments$Builder */
    static final class Builder extends com.airbnb.android.core.paidamenities.enums.PaidAmenityArguments.Builder {
        private String confirmationCode;
        private Boolean hasPaidAmenityOrders;
        private Long listingId;
        private Long threadId;

        Builder() {
        }

        public com.airbnb.android.core.paidamenities.enums.PaidAmenityArguments.Builder threadId(long threadId2) {
            this.threadId = Long.valueOf(threadId2);
            return this;
        }

        public com.airbnb.android.core.paidamenities.enums.PaidAmenityArguments.Builder listingId(long listingId2) {
            this.listingId = Long.valueOf(listingId2);
            return this;
        }

        public com.airbnb.android.core.paidamenities.enums.PaidAmenityArguments.Builder confirmationCode(String confirmationCode2) {
            if (confirmationCode2 == null) {
                throw new NullPointerException("Null confirmationCode");
            }
            this.confirmationCode = confirmationCode2;
            return this;
        }

        public com.airbnb.android.core.paidamenities.enums.PaidAmenityArguments.Builder hasPaidAmenityOrders(boolean hasPaidAmenityOrders2) {
            this.hasPaidAmenityOrders = Boolean.valueOf(hasPaidAmenityOrders2);
            return this;
        }

        public PaidAmenityArguments build() {
            String missing = "";
            if (this.threadId == null) {
                missing = missing + " threadId";
            }
            if (this.listingId == null) {
                missing = missing + " listingId";
            }
            if (this.confirmationCode == null) {
                missing = missing + " confirmationCode";
            }
            if (this.hasPaidAmenityOrders == null) {
                missing = missing + " hasPaidAmenityOrders";
            }
            if (missing.isEmpty()) {
                return new AutoValue_PaidAmenityArguments(this.threadId.longValue(), this.listingId.longValue(), this.confirmationCode, this.hasPaidAmenityOrders.booleanValue());
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_PaidAmenityArguments(long threadId2, long listingId2, String confirmationCode2, boolean hasPaidAmenityOrders2) {
        this.threadId = threadId2;
        this.listingId = listingId2;
        if (confirmationCode2 == null) {
            throw new NullPointerException("Null confirmationCode");
        }
        this.confirmationCode = confirmationCode2;
        this.hasPaidAmenityOrders = hasPaidAmenityOrders2;
    }

    public long threadId() {
        return this.threadId;
    }

    public long listingId() {
        return this.listingId;
    }

    public String confirmationCode() {
        return this.confirmationCode;
    }

    public boolean hasPaidAmenityOrders() {
        return this.hasPaidAmenityOrders;
    }

    public String toString() {
        return "PaidAmenityArguments{threadId=" + this.threadId + ", listingId=" + this.listingId + ", confirmationCode=" + this.confirmationCode + ", hasPaidAmenityOrders=" + this.hasPaidAmenityOrders + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PaidAmenityArguments)) {
            return false;
        }
        PaidAmenityArguments that = (PaidAmenityArguments) o;
        if (this.threadId == that.threadId() && this.listingId == that.listingId() && this.confirmationCode.equals(that.confirmationCode()) && this.hasPaidAmenityOrders == that.hasPaidAmenityOrders()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((int) (((long) (((int) (((long) (1 * 1000003)) ^ ((this.threadId >>> 32) ^ this.threadId))) * 1000003)) ^ ((this.listingId >>> 32) ^ this.listingId))) * 1000003) ^ this.confirmationCode.hashCode()) * 1000003) ^ (this.hasPaidAmenityOrders ? 1231 : HelpCenterArticle.VERIFIED_ID_LEARN_MORE);
    }
}
