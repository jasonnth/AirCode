package com.airbnb.android.core.arguments;

import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.enums.TripPurpose;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.PricingQuote;

/* renamed from: com.airbnb.android.core.arguments.$AutoValue_P3Arguments reason: invalid class name */
abstract class C$AutoValue_P3Arguments extends P3Arguments {
    private final AirDate checkInDate;
    private final AirDate checkOutDate;
    private final String firstVerificationStep;
    private final String from;
    private final GuestDetails guestDetails;
    private final Listing listing;
    private final long listingId;
    private final String phoneVerificationCode;
    private final PricingQuote pricingQuote;
    private final String searchSessionId;
    private final TripPurpose tripPurpose;

    /* renamed from: com.airbnb.android.core.arguments.$AutoValue_P3Arguments$Builder */
    static final class Builder extends com.airbnb.android.core.arguments.P3Arguments.Builder {
        private AirDate checkInDate;
        private AirDate checkOutDate;
        private String firstVerificationStep;
        private String from;
        private GuestDetails guestDetails;
        private Listing listing;
        private Long listingId;
        private String phoneVerificationCode;
        private PricingQuote pricingQuote;
        private String searchSessionId;
        private TripPurpose tripPurpose;

        Builder() {
        }

        private Builder(P3Arguments source) {
            this.listingId = Long.valueOf(source.listingId());
            this.guestDetails = source.guestDetails();
            this.from = source.from();
            this.listing = source.listing();
            this.pricingQuote = source.pricingQuote();
            this.checkInDate = source.checkInDate();
            this.checkOutDate = source.checkOutDate();
            this.tripPurpose = source.tripPurpose();
            this.searchSessionId = source.searchSessionId();
            this.firstVerificationStep = source.firstVerificationStep();
            this.phoneVerificationCode = source.phoneVerificationCode();
        }

        public com.airbnb.android.core.arguments.P3Arguments.Builder listingId(long listingId2) {
            this.listingId = Long.valueOf(listingId2);
            return this;
        }

        public com.airbnb.android.core.arguments.P3Arguments.Builder guestDetails(GuestDetails guestDetails2) {
            if (guestDetails2 == null) {
                throw new NullPointerException("Null guestDetails");
            }
            this.guestDetails = guestDetails2;
            return this;
        }

        public com.airbnb.android.core.arguments.P3Arguments.Builder from(String from2) {
            this.from = from2;
            return this;
        }

        public com.airbnb.android.core.arguments.P3Arguments.Builder listing(Listing listing2) {
            this.listing = listing2;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public Listing listing() {
            return this.listing;
        }

        public com.airbnb.android.core.arguments.P3Arguments.Builder pricingQuote(PricingQuote pricingQuote2) {
            this.pricingQuote = pricingQuote2;
            return this;
        }

        public com.airbnb.android.core.arguments.P3Arguments.Builder checkInDate(AirDate checkInDate2) {
            this.checkInDate = checkInDate2;
            return this;
        }

        public com.airbnb.android.core.arguments.P3Arguments.Builder checkOutDate(AirDate checkOutDate2) {
            this.checkOutDate = checkOutDate2;
            return this;
        }

        public com.airbnb.android.core.arguments.P3Arguments.Builder tripPurpose(TripPurpose tripPurpose2) {
            this.tripPurpose = tripPurpose2;
            return this;
        }

        public com.airbnb.android.core.arguments.P3Arguments.Builder searchSessionId(String searchSessionId2) {
            this.searchSessionId = searchSessionId2;
            return this;
        }

        public com.airbnb.android.core.arguments.P3Arguments.Builder firstVerificationStep(String firstVerificationStep2) {
            this.firstVerificationStep = firstVerificationStep2;
            return this;
        }

        public com.airbnb.android.core.arguments.P3Arguments.Builder phoneVerificationCode(String phoneVerificationCode2) {
            this.phoneVerificationCode = phoneVerificationCode2;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public P3Arguments autoBuild() {
            String missing = "";
            if (this.listingId == null) {
                missing = missing + " listingId";
            }
            if (this.guestDetails == null) {
                missing = missing + " guestDetails";
            }
            if (missing.isEmpty()) {
                return new AutoValue_P3Arguments(this.listingId.longValue(), this.guestDetails, this.from, this.listing, this.pricingQuote, this.checkInDate, this.checkOutDate, this.tripPurpose, this.searchSessionId, this.firstVerificationStep, this.phoneVerificationCode);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_P3Arguments(long listingId2, GuestDetails guestDetails2, String from2, Listing listing2, PricingQuote pricingQuote2, AirDate checkInDate2, AirDate checkOutDate2, TripPurpose tripPurpose2, String searchSessionId2, String firstVerificationStep2, String phoneVerificationCode2) {
        this.listingId = listingId2;
        if (guestDetails2 == null) {
            throw new NullPointerException("Null guestDetails");
        }
        this.guestDetails = guestDetails2;
        this.from = from2;
        this.listing = listing2;
        this.pricingQuote = pricingQuote2;
        this.checkInDate = checkInDate2;
        this.checkOutDate = checkOutDate2;
        this.tripPurpose = tripPurpose2;
        this.searchSessionId = searchSessionId2;
        this.firstVerificationStep = firstVerificationStep2;
        this.phoneVerificationCode = phoneVerificationCode2;
    }

    public long listingId() {
        return this.listingId;
    }

    public GuestDetails guestDetails() {
        return this.guestDetails;
    }

    public String from() {
        return this.from;
    }

    public Listing listing() {
        return this.listing;
    }

    public PricingQuote pricingQuote() {
        return this.pricingQuote;
    }

    public AirDate checkInDate() {
        return this.checkInDate;
    }

    public AirDate checkOutDate() {
        return this.checkOutDate;
    }

    public TripPurpose tripPurpose() {
        return this.tripPurpose;
    }

    public String searchSessionId() {
        return this.searchSessionId;
    }

    public String firstVerificationStep() {
        return this.firstVerificationStep;
    }

    public String phoneVerificationCode() {
        return this.phoneVerificationCode;
    }

    public String toString() {
        return "P3Arguments{listingId=" + this.listingId + ", guestDetails=" + this.guestDetails + ", from=" + this.from + ", listing=" + this.listing + ", pricingQuote=" + this.pricingQuote + ", checkInDate=" + this.checkInDate + ", checkOutDate=" + this.checkOutDate + ", tripPurpose=" + this.tripPurpose + ", searchSessionId=" + this.searchSessionId + ", firstVerificationStep=" + this.firstVerificationStep + ", phoneVerificationCode=" + this.phoneVerificationCode + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof P3Arguments)) {
            return false;
        }
        P3Arguments that = (P3Arguments) o;
        if (this.listingId == that.listingId() && this.guestDetails.equals(that.guestDetails()) && (this.from != null ? this.from.equals(that.from()) : that.from() == null) && (this.listing != null ? this.listing.equals(that.listing()) : that.listing() == null) && (this.pricingQuote != null ? this.pricingQuote.equals(that.pricingQuote()) : that.pricingQuote() == null) && (this.checkInDate != null ? this.checkInDate.equals(that.checkInDate()) : that.checkInDate() == null) && (this.checkOutDate != null ? this.checkOutDate.equals(that.checkOutDate()) : that.checkOutDate() == null) && (this.tripPurpose != null ? this.tripPurpose.equals(that.tripPurpose()) : that.tripPurpose() == null) && (this.searchSessionId != null ? this.searchSessionId.equals(that.searchSessionId()) : that.searchSessionId() == null) && (this.firstVerificationStep != null ? this.firstVerificationStep.equals(that.firstVerificationStep()) : that.firstVerificationStep() == null)) {
            if (this.phoneVerificationCode == null) {
                if (that.phoneVerificationCode() == null) {
                    return true;
                }
            } else if (this.phoneVerificationCode.equals(that.phoneVerificationCode())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((((((((((((((int) (((long) (1 * 1000003)) ^ ((this.listingId >>> 32) ^ this.listingId))) * 1000003) ^ this.guestDetails.hashCode()) * 1000003) ^ (this.from == null ? 0 : this.from.hashCode())) * 1000003) ^ (this.listing == null ? 0 : this.listing.hashCode())) * 1000003) ^ (this.pricingQuote == null ? 0 : this.pricingQuote.hashCode())) * 1000003) ^ (this.checkInDate == null ? 0 : this.checkInDate.hashCode())) * 1000003) ^ (this.checkOutDate == null ? 0 : this.checkOutDate.hashCode())) * 1000003) ^ (this.tripPurpose == null ? 0 : this.tripPurpose.hashCode())) * 1000003) ^ (this.searchSessionId == null ? 0 : this.searchSessionId.hashCode())) * 1000003) ^ (this.firstVerificationStep == null ? 0 : this.firstVerificationStep.hashCode())) * 1000003;
        if (this.phoneVerificationCode != null) {
            i = this.phoneVerificationCode.hashCode();
        }
        return h ^ i;
    }

    public com.airbnb.android.core.arguments.P3Arguments.Builder toBuilder() {
        return new Builder(this);
    }
}
