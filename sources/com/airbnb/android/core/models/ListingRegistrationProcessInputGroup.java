package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.airbnb.android.core.enums.ListingRegistrationInputType;
import com.airbnb.android.core.models.generated.GenListingRegistrationProcessInputGroup;
import com.google.common.collect.FluentIterable;
import java.util.List;

public class ListingRegistrationProcessInputGroup extends GenListingRegistrationProcessInputGroup {
    public static final Creator<ListingRegistrationProcessInputGroup> CREATOR = new Creator<ListingRegistrationProcessInputGroup>() {
        public ListingRegistrationProcessInputGroup[] newArray(int size) {
            return new ListingRegistrationProcessInputGroup[size];
        }

        public ListingRegistrationProcessInputGroup createFromParcel(Parcel source) {
            ListingRegistrationProcessInputGroup object = new ListingRegistrationProcessInputGroup();
            object.readFromParcel(source);
            return object;
        }
    };

    public ListingRegistrationQuestion getQuestionFromInputKey(String inputKey) {
        return (ListingRegistrationQuestion) FluentIterable.from((Iterable<E>) getQuestions()).filter(ListingRegistrationProcessInputGroup$$Lambda$1.lambdaFactory$(inputKey)).first().orNull();
    }

    public boolean hasAddressInput() {
        return FluentIterable.from((Iterable<E>) getQuestions()).anyMatch(ListingRegistrationProcessInputGroup$$Lambda$2.lambdaFactory$());
    }

    static /* synthetic */ boolean lambda$hasAddressInput$1(ListingRegistrationQuestion v) {
        return v.getInputType() == ListingRegistrationInputType.Address;
    }

    public ListingRegistrationQuestion getAddressInput() {
        return (ListingRegistrationQuestion) FluentIterable.from((Iterable<E>) getQuestions()).filter(ListingRegistrationProcessInputGroup$$Lambda$3.lambdaFactory$()).first().orNull();
    }

    static /* synthetic */ boolean lambda$getAddressInput$2(ListingRegistrationQuestion v) {
        return v.getInputType() == ListingRegistrationInputType.Address;
    }

    public String getSubtitleString() {
        List<String> subtitles = getGroupSubtitles();
        if (subtitles == null || subtitles.size() < 1) {
            return null;
        }
        return TextUtils.join("\n", subtitles);
    }
}
