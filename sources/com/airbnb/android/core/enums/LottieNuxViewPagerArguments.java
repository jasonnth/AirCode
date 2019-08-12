package com.airbnb.android.core.enums;

import android.os.Parcelable;
import com.google.common.base.Optional;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;

public abstract class LottieNuxViewPagerArguments implements Parcelable {

    public static abstract class Builder {
        public abstract Builder animationFilename(String str);

        public abstract Builder animationTimes(List<Float> list);

        public abstract LottieNuxViewPagerArguments build();

        public abstract Builder buttonDeepLink(String str);

        public abstract Builder buttonText(String str);

        public abstract Builder coverPageButtonTextRes(Integer num);

        public abstract Builder coverPageDescriptionRes(Integer num);

        public abstract Builder coverPageImageRes(Integer num);

        public abstract Builder coverPageTitleRes(Integer num);

        public abstract Builder pagesContent(List<SimpleEntry<Integer, Integer>> list);

        public abstract Builder sharedPrefsConstant(String str);
    }

    public abstract String animationFilename();

    public abstract List<Float> animationTimes();

    public abstract Optional<String> buttonDeepLink();

    public abstract Optional<String> buttonText();

    public abstract Optional<Integer> coverPageButtonTextRes();

    public abstract Optional<Integer> coverPageDescriptionRes();

    public abstract Optional<Integer> coverPageImageRes();

    public abstract Optional<Integer> coverPageTitleRes();

    public abstract List<SimpleEntry<Integer, Integer>> pagesContent();

    public abstract String sharedPrefsConstant();

    public static Builder builder() {
        return new Builder();
    }
}
