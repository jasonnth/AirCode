package com.airbnb.android.lib.paidamenities.models;

import com.airbnb.android.core.models.PaidAmenityCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

/* renamed from: com.airbnb.android.lib.paidamenities.models.$AutoValue_PaidAmenityDetails reason: invalid class name */
abstract class C$AutoValue_PaidAmenityDetails extends PaidAmenityDetails {
    private final String currency;
    private final String description;
    private final Boolean isComplementary;
    private final Long listingId;
    private final PaidAmenityCategory paidAmenityType;
    private final Integer price;
    private final String title;

    /* renamed from: com.airbnb.android.lib.paidamenities.models.$AutoValue_PaidAmenityDetails$Builder */
    static final class Builder extends com.airbnb.android.lib.paidamenities.models.PaidAmenityDetails.Builder {
        private String currency;
        private String description;
        private Boolean isComplementary;
        private Long listingId;
        private PaidAmenityCategory paidAmenityType;
        private Integer price;
        private String title;

        Builder() {
        }

        private Builder(PaidAmenityDetails source) {
            this.listingId = source.listingId();
            this.paidAmenityType = source.paidAmenityType();
            this.title = source.title();
            this.description = source.description();
            this.currency = source.currency();
            this.price = source.price();
            this.isComplementary = source.isComplementary();
        }

        public com.airbnb.android.lib.paidamenities.models.PaidAmenityDetails.Builder setListingId(Long listingId2) {
            this.listingId = listingId2;
            return this;
        }

        public com.airbnb.android.lib.paidamenities.models.PaidAmenityDetails.Builder setPaidAmenityType(PaidAmenityCategory paidAmenityType2) {
            this.paidAmenityType = paidAmenityType2;
            return this;
        }

        public com.airbnb.android.lib.paidamenities.models.PaidAmenityDetails.Builder setTitle(String title2) {
            this.title = title2;
            return this;
        }

        public com.airbnb.android.lib.paidamenities.models.PaidAmenityDetails.Builder setDescription(String description2) {
            this.description = description2;
            return this;
        }

        public com.airbnb.android.lib.paidamenities.models.PaidAmenityDetails.Builder setCurrency(String currency2) {
            this.currency = currency2;
            return this;
        }

        public com.airbnb.android.lib.paidamenities.models.PaidAmenityDetails.Builder setPrice(Integer price2) {
            this.price = price2;
            return this;
        }

        public com.airbnb.android.lib.paidamenities.models.PaidAmenityDetails.Builder setIsComplementary(Boolean isComplementary2) {
            this.isComplementary = isComplementary2;
            return this;
        }

        public PaidAmenityDetails build() {
            return new AutoValue_PaidAmenityDetails(this.listingId, this.paidAmenityType, this.title, this.description, this.currency, this.price, this.isComplementary);
        }
    }

    C$AutoValue_PaidAmenityDetails(Long listingId2, PaidAmenityCategory paidAmenityType2, String title2, String description2, String currency2, Integer price2, Boolean isComplementary2) {
        this.listingId = listingId2;
        this.paidAmenityType = paidAmenityType2;
        this.title = title2;
        this.description = description2;
        this.currency = currency2;
        this.price = price2;
        this.isComplementary = isComplementary2;
    }

    @JsonProperty("listing_id")
    public Long listingId() {
        return this.listingId;
    }

    @JsonUnwrapped
    public PaidAmenityCategory paidAmenityType() {
        return this.paidAmenityType;
    }

    @JsonProperty("title")
    public String title() {
        return this.title;
    }

    @JsonProperty("description")
    public String description() {
        return this.description;
    }

    @JsonProperty("currency")
    public String currency() {
        return this.currency;
    }

    @JsonProperty("price")
    public Integer price() {
        return this.price;
    }

    @JsonProperty("is_complimentary")
    public Boolean isComplementary() {
        return this.isComplementary;
    }

    public String toString() {
        return "PaidAmenityDetails{listingId=" + this.listingId + ", paidAmenityType=" + this.paidAmenityType + ", title=" + this.title + ", description=" + this.description + ", currency=" + this.currency + ", price=" + this.price + ", isComplementary=" + this.isComplementary + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PaidAmenityDetails)) {
            return false;
        }
        PaidAmenityDetails that = (PaidAmenityDetails) o;
        if (this.listingId != null ? this.listingId.equals(that.listingId()) : that.listingId() == null) {
            if (this.paidAmenityType != null ? this.paidAmenityType.equals(that.paidAmenityType()) : that.paidAmenityType() == null) {
                if (this.title != null ? this.title.equals(that.title()) : that.title() == null) {
                    if (this.description != null ? this.description.equals(that.description()) : that.description() == null) {
                        if (this.currency != null ? this.currency.equals(that.currency()) : that.currency() == null) {
                            if (this.price != null ? this.price.equals(that.price()) : that.price() == null) {
                                if (this.isComplementary == null) {
                                    if (that.isComplementary() == null) {
                                        return true;
                                    }
                                } else if (this.isComplementary.equals(that.isComplementary())) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((((((1 * 1000003) ^ (this.listingId == null ? 0 : this.listingId.hashCode())) * 1000003) ^ (this.paidAmenityType == null ? 0 : this.paidAmenityType.hashCode())) * 1000003) ^ (this.title == null ? 0 : this.title.hashCode())) * 1000003) ^ (this.description == null ? 0 : this.description.hashCode())) * 1000003) ^ (this.currency == null ? 0 : this.currency.hashCode())) * 1000003) ^ (this.price == null ? 0 : this.price.hashCode())) * 1000003;
        if (this.isComplementary != null) {
            i = this.isComplementary.hashCode();
        }
        return h ^ i;
    }

    public com.airbnb.android.lib.paidamenities.models.PaidAmenityDetails.Builder toBuilder() {
        return new Builder(this);
    }
}
