package com.airbnb.android.core.enums;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.common.base.Optional;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;

final class AutoValue_LottieNuxViewPagerArguments extends C$AutoValue_LottieNuxViewPagerArguments {
    public static final Creator<AutoValue_LottieNuxViewPagerArguments> CREATOR = new Creator<AutoValue_LottieNuxViewPagerArguments>() {
        public AutoValue_LottieNuxViewPagerArguments createFromParcel(Parcel in) {
            return new AutoValue_LottieNuxViewPagerArguments(in.readArrayList(Integer.class.getClassLoader()), in.readArrayList(Float.class.getClassLoader()), (Optional) in.readSerializable(), (Optional) in.readSerializable(), in.readString(), in.readString(), (Optional) in.readSerializable(), (Optional) in.readSerializable(), (Optional) in.readSerializable(), (Optional) in.readSerializable());
        }

        public AutoValue_LottieNuxViewPagerArguments[] newArray(int size) {
            return new AutoValue_LottieNuxViewPagerArguments[size];
        }
    };

    AutoValue_LottieNuxViewPagerArguments(List<SimpleEntry<Integer, Integer>> pagesContent, List<Float> animationTimes, Optional<String> buttonText, Optional<String> buttonDeepLink, String sharedPrefsConstant, String animationFilename, Optional<Integer> coverPageTitleRes, Optional<Integer> coverPageDescriptionRes, Optional<Integer> coverPageButtonTextRes, Optional<Integer> coverPageImageRes) {
        super(pagesContent, animationTimes, buttonText, buttonDeepLink, sharedPrefsConstant, animationFilename, coverPageTitleRes, coverPageDescriptionRes, coverPageButtonTextRes, coverPageImageRes);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(pagesContent());
        dest.writeList(animationTimes());
        dest.writeSerializable(buttonText());
        dest.writeSerializable(buttonDeepLink());
        dest.writeString(sharedPrefsConstant());
        dest.writeString(animationFilename());
        dest.writeSerializable(coverPageTitleRes());
        dest.writeSerializable(coverPageDescriptionRes());
        dest.writeSerializable(coverPageButtonTextRes());
        dest.writeSerializable(coverPageImageRes());
    }

    public int describeContents() {
        return 0;
    }
}
