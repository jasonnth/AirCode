package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.StoryElement;
import com.airbnb.android.core.models.StoryImageDetails;
import com.airbnb.android.core.models.StoryProductLinkDetails;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenStoryElement implements Parcelable {
    @JsonProperty("image_details")
    protected StoryImageDetails mImageDetails;
    @JsonProperty("product_link_details")
    protected StoryProductLinkDetails mProductLinkDetails;
    @JsonProperty("sub_elements")
    protected List<StoryElement> mSubElements;
    @JsonProperty("text")
    protected String mText;
    @JsonProperty("type")
    protected String mTypeString;

    protected GenStoryElement(List<StoryElement> subElements, StoryImageDetails imageDetails, StoryProductLinkDetails productLinkDetails, String typeString, String text) {
        this();
        this.mSubElements = subElements;
        this.mImageDetails = imageDetails;
        this.mProductLinkDetails = productLinkDetails;
        this.mTypeString = typeString;
        this.mText = text;
    }

    protected GenStoryElement() {
    }

    public List<StoryElement> getSubElements() {
        return this.mSubElements;
    }

    @JsonProperty("sub_elements")
    public void setSubElements(List<StoryElement> value) {
        this.mSubElements = value;
    }

    public StoryImageDetails getImageDetails() {
        return this.mImageDetails;
    }

    @JsonProperty("image_details")
    public void setImageDetails(StoryImageDetails value) {
        this.mImageDetails = value;
    }

    public StoryProductLinkDetails getProductLinkDetails() {
        return this.mProductLinkDetails;
    }

    @JsonProperty("product_link_details")
    public void setProductLinkDetails(StoryProductLinkDetails value) {
        this.mProductLinkDetails = value;
    }

    public String getTypeString() {
        return this.mTypeString;
    }

    @JsonProperty("type")
    public void setTypeString(String value) {
        this.mTypeString = value;
    }

    public String getText() {
        return this.mText;
    }

    @JsonProperty("text")
    public void setText(String value) {
        this.mText = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(this.mSubElements);
        parcel.writeParcelable(this.mImageDetails, 0);
        parcel.writeParcelable(this.mProductLinkDetails, 0);
        parcel.writeString(this.mTypeString);
        parcel.writeString(this.mText);
    }

    public void readFromParcel(Parcel source) {
        this.mSubElements = source.createTypedArrayList(StoryElement.CREATOR);
        this.mImageDetails = (StoryImageDetails) source.readParcelable(StoryImageDetails.class.getClassLoader());
        this.mProductLinkDetails = (StoryProductLinkDetails) source.readParcelable(StoryProductLinkDetails.class.getClassLoader());
        this.mTypeString = source.readString();
        this.mText = source.readString();
    }
}
