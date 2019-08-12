package com.airbnb.android.core.utils.geocoder.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.utils.geocoder.models.AutocompletePrediction;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenAutocompleteResponse implements Parcelable {
    @JsonProperty("predictions")
    protected List<AutocompletePrediction> mPredictions;

    protected GenAutocompleteResponse(List<AutocompletePrediction> predictions) {
        this();
        this.mPredictions = predictions;
    }

    protected GenAutocompleteResponse() {
    }

    public List<AutocompletePrediction> getPredictions() {
        return this.mPredictions;
    }

    @JsonProperty("predictions")
    public void setPredictions(List<AutocompletePrediction> value) {
        this.mPredictions = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(this.mPredictions);
    }

    public void readFromParcel(Parcel source) {
        this.mPredictions = source.createTypedArrayList(AutocompletePrediction.CREATOR);
    }
}
