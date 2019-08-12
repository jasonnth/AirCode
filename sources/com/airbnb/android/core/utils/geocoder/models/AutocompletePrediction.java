package com.airbnb.android.core.utils.geocoder.models;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.utils.geocoder.AddressComponentType;
import com.airbnb.android.core.utils.geocoder.models.generated.GenAutocompletePrediction;
import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;
import java.util.List;

public class AutocompletePrediction extends GenAutocompletePrediction implements Autocompletable {
    public static final Creator<AutocompletePrediction> CREATOR = new Creator<AutocompletePrediction>() {
        public AutocompletePrediction[] newArray(int size) {
            return new AutocompletePrediction[size];
        }

        public AutocompletePrediction createFromParcel(Parcel source) {
            AutocompletePrediction object = new AutocompletePrediction();
            object.readFromParcel(source);
            return object;
        }
    };

    public String getLocationTag(Context context) {
        Optional<AddressComponentType> locationTag = FluentIterable.m1283of(AddressComponentType.values()).filter(AutocompletePrediction$$Lambda$1.lambdaFactory$(AddressComponentType.getFromKeys(getTypes()))).first();
        if (locationTag.isPresent()) {
            return context.getString(((AddressComponentType) locationTag.get()).descriptionResource);
        }
        return null;
    }

    static /* synthetic */ boolean lambda$getLocationTag$0(List types, AddressComponentType type) {
        return types.contains(type) && type.hasDescription();
    }
}
