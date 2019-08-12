package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.ListingRegistrationSubmission.FileField;
import com.airbnb.android.core.models.generated.GenListingRegistrationFileAnswer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Map;

@JsonInclude(Include.NON_NULL)
public class ListingRegistrationFileAnswer extends GenListingRegistrationFileAnswer {
    public static final Creator<ListingRegistrationFileAnswer> CREATOR = new Creator<ListingRegistrationFileAnswer>() {
        public ListingRegistrationFileAnswer[] newArray(int size) {
            return new ListingRegistrationFileAnswer[size];
        }

        public ListingRegistrationFileAnswer createFromParcel(Parcel source) {
            ListingRegistrationFileAnswer object = new ListingRegistrationFileAnswer();
            object.readFromParcel(source);
            return object;
        }
    };

    public static ListingRegistrationFileAnswer getInstance(Map map) {
        ListingRegistrationFileAnswer fileAnswer = new ListingRegistrationFileAnswer();
        fileAnswer.setUrl((String) map.get(FileField.url.name()));
        fileAnswer.setValue((String) map.get(FileField.value.name()));
        return fileAnswer;
    }
}
