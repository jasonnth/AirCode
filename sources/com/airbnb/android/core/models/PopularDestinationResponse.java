package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenPopularDestinationResponse;

public class PopularDestinationResponse extends GenPopularDestinationResponse {
    public static final Creator<PopularDestinationResponse> CREATOR = new Creator<PopularDestinationResponse>() {
        public PopularDestinationResponse[] newArray(int size) {
            return new PopularDestinationResponse[size];
        }

        public PopularDestinationResponse createFromParcel(Parcel source) {
            PopularDestinationResponse object = new PopularDestinationResponse();
            object.readFromParcel(source);
            return object;
        }
    };
}
