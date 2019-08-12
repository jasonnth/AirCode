package com.airbnb.android.core.p009p3;

import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.enums.TripPurpose;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.PricingQuote;
import com.airbnb.android.core.models.SectionedListingDescription;
import com.airbnb.android.core.models.SimilarListing;
import java.util.List;

/* renamed from: com.airbnb.android.core.p3.$AutoValue_P3State reason: invalid class name */
abstract class C$AutoValue_P3State extends P3State {
    private final String cancellationPolicyLabel;
    private final AirDate checkInDate;
    private final AirDate checkOutDate;
    private final String firstVerificationStep;
    private final String from;
    private final GuestDetails guestDetails;
    private final String inquiryMessage;
    private final boolean isCurrentUserListingHost;
    private final boolean isFetchingReportedListingStatus;
    private final boolean isHostPreview;
    private final Listing listing;
    private final long listingId;
    private final String phoneVerificationCode;
    private final PricingQuote pricingQuote;
    private final String searchSessionId;
    private final SectionedListingDescription sectionedListingDescription;
    private final boolean showTranslatedSections;
    private final List<SimilarListing> similarListings;
    private final SectionedListingDescription translatedSectionedListingDescription;
    private final TripPurpose tripPurpose;

    /* renamed from: com.airbnb.android.core.p3.$AutoValue_P3State$Builder */
    static final class Builder extends com.airbnb.android.core.p009p3.P3State.Builder {
        private String cancellationPolicyLabel;
        private AirDate checkInDate;
        private AirDate checkOutDate;
        private String firstVerificationStep;
        private String from;
        private GuestDetails guestDetails;
        private String inquiryMessage;
        private Boolean isCurrentUserListingHost;
        private Boolean isFetchingReportedListingStatus;
        private Boolean isHostPreview;
        private Listing listing;
        private Long listingId;
        private String phoneVerificationCode;
        private PricingQuote pricingQuote;
        private String searchSessionId;
        private SectionedListingDescription sectionedListingDescription;
        private Boolean showTranslatedSections;
        private List<SimilarListing> similarListings;
        private SectionedListingDescription translatedSectionedListingDescription;
        private TripPurpose tripPurpose;

        Builder() {
        }

        private Builder(P3State source) {
            this.showTranslatedSections = Boolean.valueOf(source.showTranslatedSections());
            this.isFetchingReportedListingStatus = Boolean.valueOf(source.isFetchingReportedListingStatus());
            this.isHostPreview = Boolean.valueOf(source.isHostPreview());
            this.isCurrentUserListingHost = Boolean.valueOf(source.isCurrentUserListingHost());
            this.similarListings = source.similarListings();
            this.guestDetails = source.guestDetails();
            this.listingId = Long.valueOf(source.listingId());
            this.sectionedListingDescription = source.sectionedListingDescription();
            this.translatedSectionedListingDescription = source.translatedSectionedListingDescription();
            this.listing = source.listing();
            this.pricingQuote = source.pricingQuote();
            this.checkInDate = source.checkInDate();
            this.checkOutDate = source.checkOutDate();
            this.inquiryMessage = source.inquiryMessage();
            this.cancellationPolicyLabel = source.cancellationPolicyLabel();
            this.from = source.from();
            this.tripPurpose = source.tripPurpose();
            this.searchSessionId = source.searchSessionId();
            this.firstVerificationStep = source.firstVerificationStep();
            this.phoneVerificationCode = source.phoneVerificationCode();
        }

        public com.airbnb.android.core.p009p3.P3State.Builder showTranslatedSections(boolean showTranslatedSections2) {
            this.showTranslatedSections = Boolean.valueOf(showTranslatedSections2);
            return this;
        }

        public com.airbnb.android.core.p009p3.P3State.Builder isFetchingReportedListingStatus(boolean isFetchingReportedListingStatus2) {
            this.isFetchingReportedListingStatus = Boolean.valueOf(isFetchingReportedListingStatus2);
            return this;
        }

        public com.airbnb.android.core.p009p3.P3State.Builder isHostPreview(boolean isHostPreview2) {
            this.isHostPreview = Boolean.valueOf(isHostPreview2);
            return this;
        }

        public com.airbnb.android.core.p009p3.P3State.Builder isCurrentUserListingHost(boolean isCurrentUserListingHost2) {
            this.isCurrentUserListingHost = Boolean.valueOf(isCurrentUserListingHost2);
            return this;
        }

        public com.airbnb.android.core.p009p3.P3State.Builder similarListings(List<SimilarListing> similarListings2) {
            if (similarListings2 == null) {
                throw new NullPointerException("Null similarListings");
            }
            this.similarListings = similarListings2;
            return this;
        }

        public com.airbnb.android.core.p009p3.P3State.Builder guestDetails(GuestDetails guestDetails2) {
            if (guestDetails2 == null) {
                throw new NullPointerException("Null guestDetails");
            }
            this.guestDetails = guestDetails2;
            return this;
        }

        public com.airbnb.android.core.p009p3.P3State.Builder listingId(long listingId2) {
            this.listingId = Long.valueOf(listingId2);
            return this;
        }

        public com.airbnb.android.core.p009p3.P3State.Builder sectionedListingDescription(SectionedListingDescription sectionedListingDescription2) {
            this.sectionedListingDescription = sectionedListingDescription2;
            return this;
        }

        public com.airbnb.android.core.p009p3.P3State.Builder translatedSectionedListingDescription(SectionedListingDescription translatedSectionedListingDescription2) {
            this.translatedSectionedListingDescription = translatedSectionedListingDescription2;
            return this;
        }

        public com.airbnb.android.core.p009p3.P3State.Builder listing(Listing listing2) {
            this.listing = listing2;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public Listing listing() {
            return this.listing;
        }

        public com.airbnb.android.core.p009p3.P3State.Builder pricingQuote(PricingQuote pricingQuote2) {
            this.pricingQuote = pricingQuote2;
            return this;
        }

        public com.airbnb.android.core.p009p3.P3State.Builder checkInDate(AirDate checkInDate2) {
            this.checkInDate = checkInDate2;
            return this;
        }

        public com.airbnb.android.core.p009p3.P3State.Builder checkOutDate(AirDate checkOutDate2) {
            this.checkOutDate = checkOutDate2;
            return this;
        }

        public com.airbnb.android.core.p009p3.P3State.Builder inquiryMessage(String inquiryMessage2) {
            this.inquiryMessage = inquiryMessage2;
            return this;
        }

        public com.airbnb.android.core.p009p3.P3State.Builder cancellationPolicyLabel(String cancellationPolicyLabel2) {
            this.cancellationPolicyLabel = cancellationPolicyLabel2;
            return this;
        }

        public com.airbnb.android.core.p009p3.P3State.Builder from(String from2) {
            this.from = from2;
            return this;
        }

        public com.airbnb.android.core.p009p3.P3State.Builder tripPurpose(TripPurpose tripPurpose2) {
            this.tripPurpose = tripPurpose2;
            return this;
        }

        public com.airbnb.android.core.p009p3.P3State.Builder searchSessionId(String searchSessionId2) {
            this.searchSessionId = searchSessionId2;
            return this;
        }

        public com.airbnb.android.core.p009p3.P3State.Builder firstVerificationStep(String firstVerificationStep2) {
            this.firstVerificationStep = firstVerificationStep2;
            return this;
        }

        public com.airbnb.android.core.p009p3.P3State.Builder phoneVerificationCode(String phoneVerificationCode2) {
            this.phoneVerificationCode = phoneVerificationCode2;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public P3State autoBuild() {
            String missing = "";
            if (this.showTranslatedSections == null) {
                missing = missing + " showTranslatedSections";
            }
            if (this.isFetchingReportedListingStatus == null) {
                missing = missing + " isFetchingReportedListingStatus";
            }
            if (this.isHostPreview == null) {
                missing = missing + " isHostPreview";
            }
            if (this.isCurrentUserListingHost == null) {
                missing = missing + " isCurrentUserListingHost";
            }
            if (this.similarListings == null) {
                missing = missing + " similarListings";
            }
            if (this.guestDetails == null) {
                missing = missing + " guestDetails";
            }
            if (this.listingId == null) {
                missing = missing + " listingId";
            }
            if (missing.isEmpty()) {
                return new AutoValue_P3State(this.showTranslatedSections.booleanValue(), this.isFetchingReportedListingStatus.booleanValue(), this.isHostPreview.booleanValue(), this.isCurrentUserListingHost.booleanValue(), this.similarListings, this.guestDetails, this.listingId.longValue(), this.sectionedListingDescription, this.translatedSectionedListingDescription, this.listing, this.pricingQuote, this.checkInDate, this.checkOutDate, this.inquiryMessage, this.cancellationPolicyLabel, this.from, this.tripPurpose, this.searchSessionId, this.firstVerificationStep, this.phoneVerificationCode);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_P3State(boolean showTranslatedSections2, boolean isFetchingReportedListingStatus2, boolean isHostPreview2, boolean isCurrentUserListingHost2, List<SimilarListing> similarListings2, GuestDetails guestDetails2, long listingId2, SectionedListingDescription sectionedListingDescription2, SectionedListingDescription translatedSectionedListingDescription2, Listing listing2, PricingQuote pricingQuote2, AirDate checkInDate2, AirDate checkOutDate2, String inquiryMessage2, String cancellationPolicyLabel2, String from2, TripPurpose tripPurpose2, String searchSessionId2, String firstVerificationStep2, String phoneVerificationCode2) {
        this.showTranslatedSections = showTranslatedSections2;
        this.isFetchingReportedListingStatus = isFetchingReportedListingStatus2;
        this.isHostPreview = isHostPreview2;
        this.isCurrentUserListingHost = isCurrentUserListingHost2;
        if (similarListings2 == null) {
            throw new NullPointerException("Null similarListings");
        }
        this.similarListings = similarListings2;
        if (guestDetails2 == null) {
            throw new NullPointerException("Null guestDetails");
        }
        this.guestDetails = guestDetails2;
        this.listingId = listingId2;
        this.sectionedListingDescription = sectionedListingDescription2;
        this.translatedSectionedListingDescription = translatedSectionedListingDescription2;
        this.listing = listing2;
        this.pricingQuote = pricingQuote2;
        this.checkInDate = checkInDate2;
        this.checkOutDate = checkOutDate2;
        this.inquiryMessage = inquiryMessage2;
        this.cancellationPolicyLabel = cancellationPolicyLabel2;
        this.from = from2;
        this.tripPurpose = tripPurpose2;
        this.searchSessionId = searchSessionId2;
        this.firstVerificationStep = firstVerificationStep2;
        this.phoneVerificationCode = phoneVerificationCode2;
    }

    public boolean showTranslatedSections() {
        return this.showTranslatedSections;
    }

    public boolean isFetchingReportedListingStatus() {
        return this.isFetchingReportedListingStatus;
    }

    public boolean isHostPreview() {
        return this.isHostPreview;
    }

    public boolean isCurrentUserListingHost() {
        return this.isCurrentUserListingHost;
    }

    public List<SimilarListing> similarListings() {
        return this.similarListings;
    }

    public GuestDetails guestDetails() {
        return this.guestDetails;
    }

    public long listingId() {
        return this.listingId;
    }

    public SectionedListingDescription sectionedListingDescription() {
        return this.sectionedListingDescription;
    }

    public SectionedListingDescription translatedSectionedListingDescription() {
        return this.translatedSectionedListingDescription;
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

    public String inquiryMessage() {
        return this.inquiryMessage;
    }

    public String cancellationPolicyLabel() {
        return this.cancellationPolicyLabel;
    }

    public String from() {
        return this.from;
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
        return "P3State{showTranslatedSections=" + this.showTranslatedSections + ", isFetchingReportedListingStatus=" + this.isFetchingReportedListingStatus + ", isHostPreview=" + this.isHostPreview + ", isCurrentUserListingHost=" + this.isCurrentUserListingHost + ", similarListings=" + this.similarListings + ", guestDetails=" + this.guestDetails + ", listingId=" + this.listingId + ", sectionedListingDescription=" + this.sectionedListingDescription + ", translatedSectionedListingDescription=" + this.translatedSectionedListingDescription + ", listing=" + this.listing + ", pricingQuote=" + this.pricingQuote + ", checkInDate=" + this.checkInDate + ", checkOutDate=" + this.checkOutDate + ", inquiryMessage=" + this.inquiryMessage + ", cancellationPolicyLabel=" + this.cancellationPolicyLabel + ", from=" + this.from + ", tripPurpose=" + this.tripPurpose + ", searchSessionId=" + this.searchSessionId + ", firstVerificationStep=" + this.firstVerificationStep + ", phoneVerificationCode=" + this.phoneVerificationCode + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof P3State)) {
            return false;
        }
        P3State that = (P3State) o;
        if (this.showTranslatedSections == that.showTranslatedSections() && this.isFetchingReportedListingStatus == that.isFetchingReportedListingStatus() && this.isHostPreview == that.isHostPreview() && this.isCurrentUserListingHost == that.isCurrentUserListingHost() && this.similarListings.equals(that.similarListings()) && this.guestDetails.equals(that.guestDetails()) && this.listingId == that.listingId() && (this.sectionedListingDescription != null ? this.sectionedListingDescription.equals(that.sectionedListingDescription()) : that.sectionedListingDescription() == null) && (this.translatedSectionedListingDescription != null ? this.translatedSectionedListingDescription.equals(that.translatedSectionedListingDescription()) : that.translatedSectionedListingDescription() == null) && (this.listing != null ? this.listing.equals(that.listing()) : that.listing() == null) && (this.pricingQuote != null ? this.pricingQuote.equals(that.pricingQuote()) : that.pricingQuote() == null) && (this.checkInDate != null ? this.checkInDate.equals(that.checkInDate()) : that.checkInDate() == null) && (this.checkOutDate != null ? this.checkOutDate.equals(that.checkOutDate()) : that.checkOutDate() == null) && (this.inquiryMessage != null ? this.inquiryMessage.equals(that.inquiryMessage()) : that.inquiryMessage() == null) && (this.cancellationPolicyLabel != null ? this.cancellationPolicyLabel.equals(that.cancellationPolicyLabel()) : that.cancellationPolicyLabel() == null) && (this.from != null ? this.from.equals(that.from()) : that.from() == null) && (this.tripPurpose != null ? this.tripPurpose.equals(that.tripPurpose()) : that.tripPurpose() == null) && (this.searchSessionId != null ? this.searchSessionId.equals(that.searchSessionId()) : that.searchSessionId() == null) && (this.firstVerificationStep != null ? this.firstVerificationStep.equals(that.firstVerificationStep()) : that.firstVerificationStep() == null)) {
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
        int i;
        int i2;
        int i3 = 1231;
        int i4 = 0;
        int h = ((1 * 1000003) ^ (this.showTranslatedSections ? 1231 : 1237)) * 1000003;
        if (this.isFetchingReportedListingStatus) {
            i = 1231;
        } else {
            i = 1237;
        }
        int h2 = (h ^ i) * 1000003;
        if (this.isHostPreview) {
            i2 = 1231;
        } else {
            i2 = 1237;
        }
        int h3 = (h2 ^ i2) * 1000003;
        if (!this.isCurrentUserListingHost) {
            i3 = 1237;
        }
        int h4 = ((((((((((((((((((((((((((int) (((long) ((((((h3 ^ i3) * 1000003) ^ this.similarListings.hashCode()) * 1000003) ^ this.guestDetails.hashCode()) * 1000003)) ^ ((this.listingId >>> 32) ^ this.listingId))) * 1000003) ^ (this.sectionedListingDescription == null ? 0 : this.sectionedListingDescription.hashCode())) * 1000003) ^ (this.translatedSectionedListingDescription == null ? 0 : this.translatedSectionedListingDescription.hashCode())) * 1000003) ^ (this.listing == null ? 0 : this.listing.hashCode())) * 1000003) ^ (this.pricingQuote == null ? 0 : this.pricingQuote.hashCode())) * 1000003) ^ (this.checkInDate == null ? 0 : this.checkInDate.hashCode())) * 1000003) ^ (this.checkOutDate == null ? 0 : this.checkOutDate.hashCode())) * 1000003) ^ (this.inquiryMessage == null ? 0 : this.inquiryMessage.hashCode())) * 1000003) ^ (this.cancellationPolicyLabel == null ? 0 : this.cancellationPolicyLabel.hashCode())) * 1000003) ^ (this.from == null ? 0 : this.from.hashCode())) * 1000003) ^ (this.tripPurpose == null ? 0 : this.tripPurpose.hashCode())) * 1000003) ^ (this.searchSessionId == null ? 0 : this.searchSessionId.hashCode())) * 1000003) ^ (this.firstVerificationStep == null ? 0 : this.firstVerificationStep.hashCode())) * 1000003;
        if (this.phoneVerificationCode != null) {
            i4 = this.phoneVerificationCode.hashCode();
        }
        return h4 ^ i4;
    }

    public com.airbnb.android.core.p009p3.P3State.Builder toBuilder() {
        return new Builder(this);
    }
}
