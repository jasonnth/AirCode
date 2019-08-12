package com.airbnb.android.p011p3.models;

import com.airbnb.android.core.models.GuestControls;
import com.airbnb.android.core.models.Photo;
import com.airbnb.android.core.models.SectionedListingDescription;
import com.airbnb.android.core.models.User;
import java.util.List;

/* renamed from: com.airbnb.android.p3.models.$AutoValue_ListingDetails reason: invalid class name */
abstract class C$AutoValue_ListingDetails extends ListingDetails {
    private final String bathroomLabel;
    private final String city;
    private final String country;
    private final String countryCode;
    private final String descriptionLocale;
    private final GuestControls guestControls;
    private final String initialDescriptionAuthorType;
    private final boolean isBusinessTravelReady;
    private final boolean isHostedBySuperhost;
    private final Double lat;
    private final String license;
    private final List<ListingAmenity> listingAmenities;
    private final Double lng;
    private final String localizedCheckInTimeWindow;
    private final String localizedCheckOutTime;
    private final Integer minNights;
    private final String p3SummaryAddress;
    private final String p3SummaryTitle;
    private final List<Photo> photos;
    private final User primaryHost;
    private final ListingReviewDetails reviewDetailsInterface;
    private final String roomAndPropertyType;
    private final String roomTypeCategory;
    private final SectionedListingDescription sectionedDescription;
    private final Float starRating;
    private final String state;

    /* renamed from: com.airbnb.android.p3.models.$AutoValue_ListingDetails$Builder */
    static final class Builder extends com.airbnb.android.p011p3.models.ListingDetails.Builder {
        private String bathroomLabel;
        private String city;
        private String country;
        private String countryCode;
        private String descriptionLocale;
        private GuestControls guestControls;
        private String initialDescriptionAuthorType;
        private Boolean isBusinessTravelReady;
        private Boolean isHostedBySuperhost;
        private Double lat;
        private String license;
        private List<ListingAmenity> listingAmenities;
        private Double lng;
        private String localizedCheckInTimeWindow;
        private String localizedCheckOutTime;
        private Integer minNights;
        private String p3SummaryAddress;
        private String p3SummaryTitle;
        private List<Photo> photos;
        private User primaryHost;
        private ListingReviewDetails reviewDetailsInterface;
        private String roomAndPropertyType;
        private String roomTypeCategory;
        private SectionedListingDescription sectionedDescription;
        private Float starRating;
        private String state;

        Builder() {
        }

        public com.airbnb.android.p011p3.models.ListingDetails.Builder p3SummaryTitle(String p3SummaryTitle2) {
            if (p3SummaryTitle2 == null) {
                throw new NullPointerException("Null p3SummaryTitle");
            }
            this.p3SummaryTitle = p3SummaryTitle2;
            return this;
        }

        public com.airbnb.android.p011p3.models.ListingDetails.Builder p3SummaryAddress(String p3SummaryAddress2) {
            if (p3SummaryAddress2 == null) {
                throw new NullPointerException("Null p3SummaryAddress");
            }
            this.p3SummaryAddress = p3SummaryAddress2;
            return this;
        }

        public com.airbnb.android.p011p3.models.ListingDetails.Builder roomTypeCategory(String roomTypeCategory2) {
            if (roomTypeCategory2 == null) {
                throw new NullPointerException("Null roomTypeCategory");
            }
            this.roomTypeCategory = roomTypeCategory2;
            return this;
        }

        public com.airbnb.android.p011p3.models.ListingDetails.Builder descriptionLocale(String descriptionLocale2) {
            if (descriptionLocale2 == null) {
                throw new NullPointerException("Null descriptionLocale");
            }
            this.descriptionLocale = descriptionLocale2;
            return this;
        }

        public com.airbnb.android.p011p3.models.ListingDetails.Builder bathroomLabel(String bathroomLabel2) {
            this.bathroomLabel = bathroomLabel2;
            return this;
        }

        public com.airbnb.android.p011p3.models.ListingDetails.Builder initialDescriptionAuthorType(String initialDescriptionAuthorType2) {
            this.initialDescriptionAuthorType = initialDescriptionAuthorType2;
            return this;
        }

        public com.airbnb.android.p011p3.models.ListingDetails.Builder roomAndPropertyType(String roomAndPropertyType2) {
            this.roomAndPropertyType = roomAndPropertyType2;
            return this;
        }

        public com.airbnb.android.p011p3.models.ListingDetails.Builder localizedCheckInTimeWindow(String localizedCheckInTimeWindow2) {
            this.localizedCheckInTimeWindow = localizedCheckInTimeWindow2;
            return this;
        }

        public com.airbnb.android.p011p3.models.ListingDetails.Builder localizedCheckOutTime(String localizedCheckOutTime2) {
            this.localizedCheckOutTime = localizedCheckOutTime2;
            return this;
        }

        public com.airbnb.android.p011p3.models.ListingDetails.Builder city(String city2) {
            this.city = city2;
            return this;
        }

        public com.airbnb.android.p011p3.models.ListingDetails.Builder countryCode(String countryCode2) {
            this.countryCode = countryCode2;
            return this;
        }

        public com.airbnb.android.p011p3.models.ListingDetails.Builder country(String country2) {
            this.country = country2;
            return this;
        }

        public com.airbnb.android.p011p3.models.ListingDetails.Builder state(String state2) {
            this.state = state2;
            return this;
        }

        public com.airbnb.android.p011p3.models.ListingDetails.Builder license(String license2) {
            this.license = license2;
            return this;
        }

        public com.airbnb.android.p011p3.models.ListingDetails.Builder isHostedBySuperhost(boolean isHostedBySuperhost2) {
            this.isHostedBySuperhost = Boolean.valueOf(isHostedBySuperhost2);
            return this;
        }

        public com.airbnb.android.p011p3.models.ListingDetails.Builder isBusinessTravelReady(boolean isBusinessTravelReady2) {
            this.isBusinessTravelReady = Boolean.valueOf(isBusinessTravelReady2);
            return this;
        }

        public com.airbnb.android.p011p3.models.ListingDetails.Builder listingAmenities(List<ListingAmenity> listingAmenities2) {
            if (listingAmenities2 == null) {
                throw new NullPointerException("Null listingAmenities");
            }
            this.listingAmenities = listingAmenities2;
            return this;
        }

        public com.airbnb.android.p011p3.models.ListingDetails.Builder photos(List<Photo> photos2) {
            if (photos2 == null) {
                throw new NullPointerException("Null photos");
            }
            this.photos = photos2;
            return this;
        }

        public com.airbnb.android.p011p3.models.ListingDetails.Builder starRating(Float starRating2) {
            this.starRating = starRating2;
            return this;
        }

        public com.airbnb.android.p011p3.models.ListingDetails.Builder minNights(Integer minNights2) {
            this.minNights = minNights2;
            return this;
        }

        public com.airbnb.android.p011p3.models.ListingDetails.Builder lat(Double lat2) {
            this.lat = lat2;
            return this;
        }

        public com.airbnb.android.p011p3.models.ListingDetails.Builder lng(Double lng2) {
            this.lng = lng2;
            return this;
        }

        public com.airbnb.android.p011p3.models.ListingDetails.Builder primaryHost(User primaryHost2) {
            if (primaryHost2 == null) {
                throw new NullPointerException("Null primaryHost");
            }
            this.primaryHost = primaryHost2;
            return this;
        }

        public com.airbnb.android.p011p3.models.ListingDetails.Builder guestControls(GuestControls guestControls2) {
            if (guestControls2 == null) {
                throw new NullPointerException("Null guestControls");
            }
            this.guestControls = guestControls2;
            return this;
        }

        public com.airbnb.android.p011p3.models.ListingDetails.Builder sectionedDescription(SectionedListingDescription sectionedDescription2) {
            if (sectionedDescription2 == null) {
                throw new NullPointerException("Null sectionedDescription");
            }
            this.sectionedDescription = sectionedDescription2;
            return this;
        }

        public com.airbnb.android.p011p3.models.ListingDetails.Builder reviewDetailsInterface(ListingReviewDetails reviewDetailsInterface2) {
            if (reviewDetailsInterface2 == null) {
                throw new NullPointerException("Null reviewDetailsInterface");
            }
            this.reviewDetailsInterface = reviewDetailsInterface2;
            return this;
        }

        public ListingDetails build() {
            String missing = "";
            if (this.p3SummaryTitle == null) {
                missing = missing + " p3SummaryTitle";
            }
            if (this.p3SummaryAddress == null) {
                missing = missing + " p3SummaryAddress";
            }
            if (this.roomTypeCategory == null) {
                missing = missing + " roomTypeCategory";
            }
            if (this.descriptionLocale == null) {
                missing = missing + " descriptionLocale";
            }
            if (this.isHostedBySuperhost == null) {
                missing = missing + " isHostedBySuperhost";
            }
            if (this.isBusinessTravelReady == null) {
                missing = missing + " isBusinessTravelReady";
            }
            if (this.listingAmenities == null) {
                missing = missing + " listingAmenities";
            }
            if (this.photos == null) {
                missing = missing + " photos";
            }
            if (this.primaryHost == null) {
                missing = missing + " primaryHost";
            }
            if (this.guestControls == null) {
                missing = missing + " guestControls";
            }
            if (this.sectionedDescription == null) {
                missing = missing + " sectionedDescription";
            }
            if (this.reviewDetailsInterface == null) {
                missing = missing + " reviewDetailsInterface";
            }
            if (missing.isEmpty()) {
                return new AutoValue_ListingDetails(this.p3SummaryTitle, this.p3SummaryAddress, this.roomTypeCategory, this.descriptionLocale, this.bathroomLabel, this.initialDescriptionAuthorType, this.roomAndPropertyType, this.localizedCheckInTimeWindow, this.localizedCheckOutTime, this.city, this.countryCode, this.country, this.state, this.license, this.isHostedBySuperhost.booleanValue(), this.isBusinessTravelReady.booleanValue(), this.listingAmenities, this.photos, this.starRating, this.minNights, this.lat, this.lng, this.primaryHost, this.guestControls, this.sectionedDescription, this.reviewDetailsInterface);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_ListingDetails(String p3SummaryTitle2, String p3SummaryAddress2, String roomTypeCategory2, String descriptionLocale2, String bathroomLabel2, String initialDescriptionAuthorType2, String roomAndPropertyType2, String localizedCheckInTimeWindow2, String localizedCheckOutTime2, String city2, String countryCode2, String country2, String state2, String license2, boolean isHostedBySuperhost2, boolean isBusinessTravelReady2, List<ListingAmenity> listingAmenities2, List<Photo> photos2, Float starRating2, Integer minNights2, Double lat2, Double lng2, User primaryHost2, GuestControls guestControls2, SectionedListingDescription sectionedDescription2, ListingReviewDetails reviewDetailsInterface2) {
        if (p3SummaryTitle2 == null) {
            throw new NullPointerException("Null p3SummaryTitle");
        }
        this.p3SummaryTitle = p3SummaryTitle2;
        if (p3SummaryAddress2 == null) {
            throw new NullPointerException("Null p3SummaryAddress");
        }
        this.p3SummaryAddress = p3SummaryAddress2;
        if (roomTypeCategory2 == null) {
            throw new NullPointerException("Null roomTypeCategory");
        }
        this.roomTypeCategory = roomTypeCategory2;
        if (descriptionLocale2 == null) {
            throw new NullPointerException("Null descriptionLocale");
        }
        this.descriptionLocale = descriptionLocale2;
        this.bathroomLabel = bathroomLabel2;
        this.initialDescriptionAuthorType = initialDescriptionAuthorType2;
        this.roomAndPropertyType = roomAndPropertyType2;
        this.localizedCheckInTimeWindow = localizedCheckInTimeWindow2;
        this.localizedCheckOutTime = localizedCheckOutTime2;
        this.city = city2;
        this.countryCode = countryCode2;
        this.country = country2;
        this.state = state2;
        this.license = license2;
        this.isHostedBySuperhost = isHostedBySuperhost2;
        this.isBusinessTravelReady = isBusinessTravelReady2;
        if (listingAmenities2 == null) {
            throw new NullPointerException("Null listingAmenities");
        }
        this.listingAmenities = listingAmenities2;
        if (photos2 == null) {
            throw new NullPointerException("Null photos");
        }
        this.photos = photos2;
        this.starRating = starRating2;
        this.minNights = minNights2;
        this.lat = lat2;
        this.lng = lng2;
        if (primaryHost2 == null) {
            throw new NullPointerException("Null primaryHost");
        }
        this.primaryHost = primaryHost2;
        if (guestControls2 == null) {
            throw new NullPointerException("Null guestControls");
        }
        this.guestControls = guestControls2;
        if (sectionedDescription2 == null) {
            throw new NullPointerException("Null sectionedDescription");
        }
        this.sectionedDescription = sectionedDescription2;
        if (reviewDetailsInterface2 == null) {
            throw new NullPointerException("Null reviewDetailsInterface");
        }
        this.reviewDetailsInterface = reviewDetailsInterface2;
    }

    public String p3SummaryTitle() {
        return this.p3SummaryTitle;
    }

    public String p3SummaryAddress() {
        return this.p3SummaryAddress;
    }

    public String roomTypeCategory() {
        return this.roomTypeCategory;
    }

    public String descriptionLocale() {
        return this.descriptionLocale;
    }

    public String bathroomLabel() {
        return this.bathroomLabel;
    }

    public String initialDescriptionAuthorType() {
        return this.initialDescriptionAuthorType;
    }

    public String roomAndPropertyType() {
        return this.roomAndPropertyType;
    }

    public String localizedCheckInTimeWindow() {
        return this.localizedCheckInTimeWindow;
    }

    public String localizedCheckOutTime() {
        return this.localizedCheckOutTime;
    }

    public String city() {
        return this.city;
    }

    public String countryCode() {
        return this.countryCode;
    }

    public String country() {
        return this.country;
    }

    public String state() {
        return this.state;
    }

    public String license() {
        return this.license;
    }

    public boolean isHostedBySuperhost() {
        return this.isHostedBySuperhost;
    }

    public boolean isBusinessTravelReady() {
        return this.isBusinessTravelReady;
    }

    public List<ListingAmenity> listingAmenities() {
        return this.listingAmenities;
    }

    public List<Photo> photos() {
        return this.photos;
    }

    public Float starRating() {
        return this.starRating;
    }

    public Integer minNights() {
        return this.minNights;
    }

    public Double lat() {
        return this.lat;
    }

    public Double lng() {
        return this.lng;
    }

    public User primaryHost() {
        return this.primaryHost;
    }

    public GuestControls guestControls() {
        return this.guestControls;
    }

    public SectionedListingDescription sectionedDescription() {
        return this.sectionedDescription;
    }

    public ListingReviewDetails reviewDetailsInterface() {
        return this.reviewDetailsInterface;
    }

    public String toString() {
        return "ListingDetails{p3SummaryTitle=" + this.p3SummaryTitle + ", p3SummaryAddress=" + this.p3SummaryAddress + ", roomTypeCategory=" + this.roomTypeCategory + ", descriptionLocale=" + this.descriptionLocale + ", bathroomLabel=" + this.bathroomLabel + ", initialDescriptionAuthorType=" + this.initialDescriptionAuthorType + ", roomAndPropertyType=" + this.roomAndPropertyType + ", localizedCheckInTimeWindow=" + this.localizedCheckInTimeWindow + ", localizedCheckOutTime=" + this.localizedCheckOutTime + ", city=" + this.city + ", countryCode=" + this.countryCode + ", country=" + this.country + ", state=" + this.state + ", license=" + this.license + ", isHostedBySuperhost=" + this.isHostedBySuperhost + ", isBusinessTravelReady=" + this.isBusinessTravelReady + ", listingAmenities=" + this.listingAmenities + ", photos=" + this.photos + ", starRating=" + this.starRating + ", minNights=" + this.minNights + ", lat=" + this.lat + ", lng=" + this.lng + ", primaryHost=" + this.primaryHost + ", guestControls=" + this.guestControls + ", sectionedDescription=" + this.sectionedDescription + ", reviewDetailsInterface=" + this.reviewDetailsInterface + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ListingDetails)) {
            return false;
        }
        ListingDetails that = (ListingDetails) o;
        if (!this.p3SummaryTitle.equals(that.p3SummaryTitle()) || !this.p3SummaryAddress.equals(that.p3SummaryAddress()) || !this.roomTypeCategory.equals(that.roomTypeCategory()) || !this.descriptionLocale.equals(that.descriptionLocale()) || (this.bathroomLabel != null ? !this.bathroomLabel.equals(that.bathroomLabel()) : that.bathroomLabel() != null) || (this.initialDescriptionAuthorType != null ? !this.initialDescriptionAuthorType.equals(that.initialDescriptionAuthorType()) : that.initialDescriptionAuthorType() != null) || (this.roomAndPropertyType != null ? !this.roomAndPropertyType.equals(that.roomAndPropertyType()) : that.roomAndPropertyType() != null) || (this.localizedCheckInTimeWindow != null ? !this.localizedCheckInTimeWindow.equals(that.localizedCheckInTimeWindow()) : that.localizedCheckInTimeWindow() != null) || (this.localizedCheckOutTime != null ? !this.localizedCheckOutTime.equals(that.localizedCheckOutTime()) : that.localizedCheckOutTime() != null) || (this.city != null ? !this.city.equals(that.city()) : that.city() != null) || (this.countryCode != null ? !this.countryCode.equals(that.countryCode()) : that.countryCode() != null) || (this.country != null ? !this.country.equals(that.country()) : that.country() != null) || (this.state != null ? !this.state.equals(that.state()) : that.state() != null) || (this.license != null ? !this.license.equals(that.license()) : that.license() != null) || this.isHostedBySuperhost != that.isHostedBySuperhost() || this.isBusinessTravelReady != that.isBusinessTravelReady() || !this.listingAmenities.equals(that.listingAmenities()) || !this.photos.equals(that.photos()) || (this.starRating != null ? !this.starRating.equals(that.starRating()) : that.starRating() != null) || (this.minNights != null ? !this.minNights.equals(that.minNights()) : that.minNights() != null) || (this.lat != null ? !this.lat.equals(that.lat()) : that.lat() != null) || (this.lng != null ? !this.lng.equals(that.lng()) : that.lng() != null) || !this.primaryHost.equals(that.primaryHost()) || !this.guestControls.equals(that.guestControls()) || !this.sectionedDescription.equals(that.sectionedDescription()) || !this.reviewDetailsInterface.equals(that.reviewDetailsInterface())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i;
        int i2 = 1231;
        int i3 = 0;
        int h = ((((((((((((((((((((((((((((1 * 1000003) ^ this.p3SummaryTitle.hashCode()) * 1000003) ^ this.p3SummaryAddress.hashCode()) * 1000003) ^ this.roomTypeCategory.hashCode()) * 1000003) ^ this.descriptionLocale.hashCode()) * 1000003) ^ (this.bathroomLabel == null ? 0 : this.bathroomLabel.hashCode())) * 1000003) ^ (this.initialDescriptionAuthorType == null ? 0 : this.initialDescriptionAuthorType.hashCode())) * 1000003) ^ (this.roomAndPropertyType == null ? 0 : this.roomAndPropertyType.hashCode())) * 1000003) ^ (this.localizedCheckInTimeWindow == null ? 0 : this.localizedCheckInTimeWindow.hashCode())) * 1000003) ^ (this.localizedCheckOutTime == null ? 0 : this.localizedCheckOutTime.hashCode())) * 1000003) ^ (this.city == null ? 0 : this.city.hashCode())) * 1000003) ^ (this.countryCode == null ? 0 : this.countryCode.hashCode())) * 1000003) ^ (this.country == null ? 0 : this.country.hashCode())) * 1000003) ^ (this.state == null ? 0 : this.state.hashCode())) * 1000003) ^ (this.license == null ? 0 : this.license.hashCode())) * 1000003;
        if (this.isHostedBySuperhost) {
            i = 1231;
        } else {
            i = 1237;
        }
        int h2 = (h ^ i) * 1000003;
        if (!this.isBusinessTravelReady) {
            i2 = 1237;
        }
        int h3 = (((((((((((h2 ^ i2) * 1000003) ^ this.listingAmenities.hashCode()) * 1000003) ^ this.photos.hashCode()) * 1000003) ^ (this.starRating == null ? 0 : this.starRating.hashCode())) * 1000003) ^ (this.minNights == null ? 0 : this.minNights.hashCode())) * 1000003) ^ (this.lat == null ? 0 : this.lat.hashCode())) * 1000003;
        if (this.lng != null) {
            i3 = this.lng.hashCode();
        }
        return ((((((((h3 ^ i3) * 1000003) ^ this.primaryHost.hashCode()) * 1000003) ^ this.guestControls.hashCode()) * 1000003) ^ this.sectionedDescription.hashCode()) * 1000003) ^ this.reviewDetailsInterface.hashCode();
    }
}
