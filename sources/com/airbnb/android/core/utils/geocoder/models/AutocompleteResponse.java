package com.airbnb.android.core.utils.geocoder.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.utils.geocoder.models.generated.GenAutocompleteResponse;

public class AutocompleteResponse extends GenAutocompleteResponse {
    public static final Creator<AutocompleteResponse> CREATOR = new Creator<AutocompleteResponse>() {
        public AutocompleteResponse[] newArray(int size) {
            return new AutocompleteResponse[size];
        }

        public AutocompleteResponse createFromParcel(Parcel source) {
            AutocompleteResponse object = new AutocompleteResponse();
            object.readFromParcel(source);
            return object;
        }
    };
}
