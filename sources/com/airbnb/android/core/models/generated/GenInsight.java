package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.ActionCardCopy;
import com.airbnb.android.core.models.DynamicPricingControl;
import com.airbnb.android.core.models.Insight.ConversionType;
import com.airbnb.android.core.models.Insight.GraphicType;
import com.airbnb.android.core.models.InsightConversionPayload;
import com.airbnb.android.core.models.InsightGraphicPayload;
import com.airbnb.android.core.models.Listing;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenInsight implements Parcelable {
    @JsonProperty("backend_position")
    protected int mBackendPosition;
    @JsonProperty("conversion_payload")
    protected InsightConversionPayload mConversionPayload;
    @JsonProperty("copies")
    protected ActionCardCopy mCopies;
    @JsonProperty("dynamic_pricing_control")
    protected DynamicPricingControl mDynamicPricingControl;
    @JsonProperty("global_position")
    protected int mGlobalPosition;
    @JsonProperty("graphic_payload")
    protected InsightGraphicPayload mGraphicPayload;
    @JsonProperty("listing")
    protected Listing mListing;
    @JsonProperty("listing_id")
    protected long mListingId;
    @JsonProperty("original_request_id")
    protected String mOriginalRequestId;
    @JsonProperty("position")
    protected int mPosition;
    @JsonProperty("story_conversion_type")
    protected ConversionType mStoryConversionType;
    @JsonProperty("story_graphic_type")
    protected GraphicType mStoryGraphicType;
    @JsonProperty("story_id")
    protected String mStoryId;
    @JsonProperty("story_type")
    protected int mStoryType;

    protected GenInsight(ActionCardCopy copies, ConversionType storyConversionType, DynamicPricingControl dynamicPricingControl, GraphicType storyGraphicType, InsightConversionPayload conversionPayload, InsightGraphicPayload graphicPayload, Listing listing, String storyId, String originalRequestId, int position, int globalPosition, int backendPosition, int storyType, long listingId) {
        this();
        this.mCopies = copies;
        this.mStoryConversionType = storyConversionType;
        this.mDynamicPricingControl = dynamicPricingControl;
        this.mStoryGraphicType = storyGraphicType;
        this.mConversionPayload = conversionPayload;
        this.mGraphicPayload = graphicPayload;
        this.mListing = listing;
        this.mStoryId = storyId;
        this.mOriginalRequestId = originalRequestId;
        this.mPosition = position;
        this.mGlobalPosition = globalPosition;
        this.mBackendPosition = backendPosition;
        this.mStoryType = storyType;
        this.mListingId = listingId;
    }

    protected GenInsight() {
    }

    public ActionCardCopy getCopies() {
        return this.mCopies;
    }

    @JsonProperty("copies")
    public void setCopies(ActionCardCopy value) {
        this.mCopies = value;
    }

    public ConversionType getStoryConversionType() {
        return this.mStoryConversionType;
    }

    @JsonProperty("story_conversion_type")
    public void setStoryConversionType(ConversionType value) {
        this.mStoryConversionType = value;
    }

    public DynamicPricingControl getDynamicPricingControl() {
        return this.mDynamicPricingControl;
    }

    @JsonProperty("dynamic_pricing_control")
    public void setDynamicPricingControl(DynamicPricingControl value) {
        this.mDynamicPricingControl = value;
    }

    public GraphicType getStoryGraphicType() {
        return this.mStoryGraphicType;
    }

    public InsightConversionPayload getConversionPayload() {
        return this.mConversionPayload;
    }

    @JsonProperty("conversion_payload")
    public void setConversionPayload(InsightConversionPayload value) {
        this.mConversionPayload = value;
    }

    public InsightGraphicPayload getGraphicPayload() {
        return this.mGraphicPayload;
    }

    @JsonProperty("graphic_payload")
    public void setGraphicPayload(InsightGraphicPayload value) {
        this.mGraphicPayload = value;
    }

    public Listing getListing() {
        return this.mListing;
    }

    @JsonProperty("listing")
    public void setListing(Listing value) {
        this.mListing = value;
    }

    public String getStoryId() {
        return this.mStoryId;
    }

    @JsonProperty("story_id")
    public void setStoryId(String value) {
        this.mStoryId = value;
    }

    public String getOriginalRequestId() {
        return this.mOriginalRequestId;
    }

    @JsonProperty("original_request_id")
    public void setOriginalRequestId(String value) {
        this.mOriginalRequestId = value;
    }

    public int getPosition() {
        return this.mPosition;
    }

    @JsonProperty("position")
    public void setPosition(int value) {
        this.mPosition = value;
    }

    public int getGlobalPosition() {
        return this.mGlobalPosition;
    }

    @JsonProperty("global_position")
    public void setGlobalPosition(int value) {
        this.mGlobalPosition = value;
    }

    public int getBackendPosition() {
        return this.mBackendPosition;
    }

    public int getStoryType() {
        return this.mStoryType;
    }

    @JsonProperty("story_type")
    public void setStoryType(int value) {
        this.mStoryType = value;
    }

    public long getListingId() {
        return this.mListingId;
    }

    @JsonProperty("listing_id")
    public void setListingId(long value) {
        this.mListingId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mCopies, 0);
        parcel.writeSerializable(this.mStoryConversionType);
        parcel.writeParcelable(this.mDynamicPricingControl, 0);
        parcel.writeSerializable(this.mStoryGraphicType);
        parcel.writeParcelable(this.mConversionPayload, 0);
        parcel.writeParcelable(this.mGraphicPayload, 0);
        parcel.writeParcelable(this.mListing, 0);
        parcel.writeString(this.mStoryId);
        parcel.writeString(this.mOriginalRequestId);
        parcel.writeInt(this.mPosition);
        parcel.writeInt(this.mGlobalPosition);
        parcel.writeInt(this.mBackendPosition);
        parcel.writeInt(this.mStoryType);
        parcel.writeLong(this.mListingId);
    }

    public void readFromParcel(Parcel source) {
        this.mCopies = (ActionCardCopy) source.readParcelable(ActionCardCopy.class.getClassLoader());
        this.mStoryConversionType = (ConversionType) source.readSerializable();
        this.mDynamicPricingControl = (DynamicPricingControl) source.readParcelable(DynamicPricingControl.class.getClassLoader());
        this.mStoryGraphicType = (GraphicType) source.readSerializable();
        this.mConversionPayload = (InsightConversionPayload) source.readParcelable(InsightConversionPayload.class.getClassLoader());
        this.mGraphicPayload = (InsightGraphicPayload) source.readParcelable(InsightGraphicPayload.class.getClassLoader());
        this.mListing = (Listing) source.readParcelable(Listing.class.getClassLoader());
        this.mStoryId = source.readString();
        this.mOriginalRequestId = source.readString();
        this.mPosition = source.readInt();
        this.mGlobalPosition = source.readInt();
        this.mBackendPosition = source.readInt();
        this.mStoryType = source.readInt();
        this.mListingId = source.readLong();
    }
}
