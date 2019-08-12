package com.airbnb.android.core.utils.geocoder.models;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.utils.geocoder.AddressComponentType;
import com.airbnb.android.core.utils.geocoder.models.generated.GenSatoriAutocompletePrediction;
import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;
import java.util.List;

public class SatoriAutocompletePrediction extends GenSatoriAutocompletePrediction implements Autocompletable {
    public static final Creator<SatoriAutocompletePrediction> CREATOR = new Creator<SatoriAutocompletePrediction>() {
        public SatoriAutocompletePrediction[] newArray(int size) {
            return new SatoriAutocompletePrediction[size];
        }

        public SatoriAutocompletePrediction createFromParcel(Parcel source) {
            SatoriAutocompletePrediction object = new SatoriAutocompletePrediction();
            object.readFromParcel(source);
            return object;
        }
    };

    public String getLocationTag(Context context) {
        Optional<AddressComponentType> locationTag = FluentIterable.m1283of(AddressComponentType.values()).filter(SatoriAutocompletePrediction$$Lambda$1.lambdaFactory$(AddressComponentType.getFromKeys(getTypes()))).first();
        if (locationTag.isPresent()) {
            return context.getString(((AddressComponentType) locationTag.get()).descriptionResource);
        }
        return null;
    }

    static /* synthetic */ boolean lambda$getLocationTag$0(List types, AddressComponentType type) {
        return types.contains(type) && type.hasDescription();
    }
}
