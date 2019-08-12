package com.airbnb.android.sharing.models;

import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = Builder.class)
public abstract class ShareArguments implements Parcelable {

    public static abstract class Builder {
        public abstract ShareArguments build();

        @JsonProperty("category")
        public abstract Builder category(String str);

        @JsonProperty("creator_name")
        public abstract Builder creatorName(String str);

        @JsonProperty("creator_occupation")
        public abstract Builder creatorOccupation(String str);

        @JsonProperty("detour_about")
        public abstract Builder detourAbout(String str);

        @JsonProperty("detour_description")
        public abstract Builder detourDescription(String str);

        @JsonProperty("detour_tips")
        public abstract Builder detourTips(String str);

        @JsonProperty("detour_title")
        public abstract Builder detourTitle(String str);

        @JsonProperty("entry_point")
        public abstract Builder entryPoint(String str);

        @JsonProperty("experience_description")
        public abstract Builder experienceDescription(String str);

        @JsonProperty("experience_title")
        public abstract Builder experienceTitle(String str);

        @JsonProperty("fb_share_url")
        public abstract Builder fbShareUrl(String str);

        @JsonProperty("guidebook_title")
        public abstract Builder guidebookTitle(String str);

        @JsonProperty("host_name")
        public abstract Builder hostName(String str);

        @JsonProperty("id")
        /* renamed from: id */
        public abstract Builder mo11490id(Long l);

        @JsonProperty("location")
        public abstract Builder location(String str);

        @JsonProperty("num_places")
        public abstract Builder numPlaces(Integer num);

        @JsonProperty("place_name")
        public abstract Builder placeName(String str);

        @JsonProperty("place_type")
        public abstract Builder placeType(String str);

        @JsonProperty("primary_image_url")
        public abstract Builder primaryImageUrl(String str);

        @JsonProperty("product_type")
        public abstract Builder productType(Integer num);

        @JsonProperty("url")
        public abstract Builder url(String str);
    }

    public abstract String category();

    public abstract String creatorName();

    public abstract String creatorOccupation();

    public abstract String detourAbout();

    public abstract String detourDescription();

    public abstract String detourTips();

    public abstract String detourTitle();

    public abstract String entryPoint();

    public abstract String experienceDescription();

    public abstract String experienceTitle();

    public abstract String fbShareUrl();

    public abstract String guidebookTitle();

    public abstract String hostName();

    /* renamed from: id */
    public abstract Long mo11468id();

    public abstract String location();

    public abstract Integer numPlaces();

    public abstract String placeName();

    public abstract String placeType();

    public abstract String primaryImageUrl();

    public abstract Integer productType();

    public abstract String url();

    public static Builder builder() {
        return new Builder();
    }
}
