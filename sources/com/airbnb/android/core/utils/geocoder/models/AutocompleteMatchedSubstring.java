package com.airbnb.android.core.utils.geocoder.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.utils.geocoder.models.generated.GenAutocompleteMatchedSubstring;

public class AutocompleteMatchedSubstring extends GenAutocompleteMatchedSubstring {
    public static final Creator<AutocompleteMatchedSubstring> CREATOR = new Creator<AutocompleteMatchedSubstring>() {
        public AutocompleteMatchedSubstring[] newArray(int size) {
            return new AutocompleteMatchedSubstring[size];
        }

        public AutocompleteMatchedSubstring createFromParcel(Parcel source) {
            AutocompleteMatchedSubstring object = new AutocompleteMatchedSubstring();
            object.readFromParcel(source);
            return object;
        }
    };
}
