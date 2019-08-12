package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.ListingExpectation;
import com.airbnb.android.core.models.PreBookingQuestion;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenBookingSettings implements Parcelable {
    @JsonProperty("booking_custom_questions")
    protected List<String> mBookingCustomQuestions;
    @JsonProperty("booking_standard_questions")
    protected List<PreBookingQuestion> mBookingStandardQuestions;
    @JsonProperty("listing_expectations")
    protected List<ListingExpectation> mListingExpectations;

    protected GenBookingSettings(List<ListingExpectation> listingExpectations, List<PreBookingQuestion> bookingStandardQuestions, List<String> bookingCustomQuestions) {
        this();
        this.mListingExpectations = listingExpectations;
        this.mBookingStandardQuestions = bookingStandardQuestions;
        this.mBookingCustomQuestions = bookingCustomQuestions;
    }

    protected GenBookingSettings() {
    }

    public List<ListingExpectation> getListingExpectations() {
        return this.mListingExpectations;
    }

    @JsonProperty("listing_expectations")
    public void setListingExpectations(List<ListingExpectation> value) {
        this.mListingExpectations = value;
    }

    public List<PreBookingQuestion> getBookingStandardQuestions() {
        return this.mBookingStandardQuestions;
    }

    @JsonProperty("booking_standard_questions")
    public void setBookingStandardQuestions(List<PreBookingQuestion> value) {
        this.mBookingStandardQuestions = value;
    }

    public List<String> getBookingCustomQuestions() {
        return this.mBookingCustomQuestions;
    }

    @JsonProperty("booking_custom_questions")
    public void setBookingCustomQuestions(List<String> value) {
        this.mBookingCustomQuestions = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(this.mListingExpectations);
        parcel.writeTypedList(this.mBookingStandardQuestions);
        parcel.writeStringList(this.mBookingCustomQuestions);
    }

    public void readFromParcel(Parcel source) {
        this.mListingExpectations = source.createTypedArrayList(ListingExpectation.CREATOR);
        this.mBookingStandardQuestions = source.createTypedArrayList(PreBookingQuestion.CREATOR);
        this.mBookingCustomQuestions = source.createStringArrayList();
    }
}
