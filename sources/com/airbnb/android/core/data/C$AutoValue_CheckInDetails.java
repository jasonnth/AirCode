package com.airbnb.android.core.data;

/* renamed from: com.airbnb.android.core.data.$AutoValue_CheckInDetails reason: invalid class name */
abstract class C$AutoValue_CheckInDetails extends CheckInDetails {
    private final String checkIn;
    private final String checkOut;
    private final String latestCheckIn;

    /* renamed from: com.airbnb.android.core.data.$AutoValue_CheckInDetails$Builder */
    static final class Builder extends com.airbnb.android.core.data.CheckInDetails.Builder {
        private String checkIn;
        private String checkOut;
        private String latestCheckIn;

        Builder() {
        }

        private Builder(CheckInDetails source) {
            this.checkIn = source.checkIn();
            this.latestCheckIn = source.latestCheckIn();
            this.checkOut = source.checkOut();
        }

        /* access modifiers changed from: 0000 */
        public com.airbnb.android.core.data.CheckInDetails.Builder checkIn(String checkIn2) {
            this.checkIn = checkIn2;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public com.airbnb.android.core.data.CheckInDetails.Builder latestCheckIn(String latestCheckIn2) {
            this.latestCheckIn = latestCheckIn2;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public com.airbnb.android.core.data.CheckInDetails.Builder checkOut(String checkOut2) {
            this.checkOut = checkOut2;
            return this;
        }

        public CheckInDetails build() {
            return new AutoValue_CheckInDetails(this.checkIn, this.latestCheckIn, this.checkOut);
        }
    }

    C$AutoValue_CheckInDetails(String checkIn2, String latestCheckIn2, String checkOut2) {
        this.checkIn = checkIn2;
        this.latestCheckIn = latestCheckIn2;
        this.checkOut = checkOut2;
    }

    public String checkIn() {
        return this.checkIn;
    }

    public String latestCheckIn() {
        return this.latestCheckIn;
    }

    public String checkOut() {
        return this.checkOut;
    }

    public String toString() {
        return "CheckInDetails{checkIn=" + this.checkIn + ", latestCheckIn=" + this.latestCheckIn + ", checkOut=" + this.checkOut + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CheckInDetails)) {
            return false;
        }
        CheckInDetails that = (CheckInDetails) o;
        if (this.checkIn != null ? this.checkIn.equals(that.checkIn()) : that.checkIn() == null) {
            if (this.latestCheckIn != null ? this.latestCheckIn.equals(that.latestCheckIn()) : that.latestCheckIn() == null) {
                if (this.checkOut == null) {
                    if (that.checkOut() == null) {
                        return true;
                    }
                } else if (this.checkOut.equals(that.checkOut())) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((1 * 1000003) ^ (this.checkIn == null ? 0 : this.checkIn.hashCode())) * 1000003) ^ (this.latestCheckIn == null ? 0 : this.latestCheckIn.hashCode())) * 1000003;
        if (this.checkOut != null) {
            i = this.checkOut.hashCode();
        }
        return h ^ i;
    }

    public com.airbnb.android.core.data.CheckInDetails.Builder toBuilder() {
        return new Builder(this);
    }
}
