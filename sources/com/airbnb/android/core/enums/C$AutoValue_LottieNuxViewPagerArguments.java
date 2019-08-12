package com.airbnb.android.core.enums;

import com.google.common.base.Optional;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;

/* renamed from: com.airbnb.android.core.enums.$AutoValue_LottieNuxViewPagerArguments reason: invalid class name */
abstract class C$AutoValue_LottieNuxViewPagerArguments extends LottieNuxViewPagerArguments {
    private final String animationFilename;
    private final List<Float> animationTimes;
    private final Optional<String> buttonDeepLink;
    private final Optional<String> buttonText;
    private final Optional<Integer> coverPageButtonTextRes;
    private final Optional<Integer> coverPageDescriptionRes;
    private final Optional<Integer> coverPageImageRes;
    private final Optional<Integer> coverPageTitleRes;
    private final List<SimpleEntry<Integer, Integer>> pagesContent;
    private final String sharedPrefsConstant;

    /* renamed from: com.airbnb.android.core.enums.$AutoValue_LottieNuxViewPagerArguments$Builder */
    static final class Builder extends com.airbnb.android.core.enums.LottieNuxViewPagerArguments.Builder {
        private String animationFilename;
        private List<Float> animationTimes;
        private Optional<String> buttonDeepLink = Optional.absent();
        private Optional<String> buttonText = Optional.absent();
        private Optional<Integer> coverPageButtonTextRes = Optional.absent();
        private Optional<Integer> coverPageDescriptionRes = Optional.absent();
        private Optional<Integer> coverPageImageRes = Optional.absent();
        private Optional<Integer> coverPageTitleRes = Optional.absent();
        private List<SimpleEntry<Integer, Integer>> pagesContent;
        private String sharedPrefsConstant;

        Builder() {
        }

        public com.airbnb.android.core.enums.LottieNuxViewPagerArguments.Builder pagesContent(List<SimpleEntry<Integer, Integer>> pagesContent2) {
            if (pagesContent2 == null) {
                throw new NullPointerException("Null pagesContent");
            }
            this.pagesContent = pagesContent2;
            return this;
        }

        public com.airbnb.android.core.enums.LottieNuxViewPagerArguments.Builder animationTimes(List<Float> animationTimes2) {
            if (animationTimes2 == null) {
                throw new NullPointerException("Null animationTimes");
            }
            this.animationTimes = animationTimes2;
            return this;
        }

        public com.airbnb.android.core.enums.LottieNuxViewPagerArguments.Builder buttonText(String buttonText2) {
            if (buttonText2 == null) {
                throw new NullPointerException("Null buttonText");
            }
            this.buttonText = Optional.m1897of(buttonText2);
            return this;
        }

        public com.airbnb.android.core.enums.LottieNuxViewPagerArguments.Builder buttonDeepLink(String buttonDeepLink2) {
            if (buttonDeepLink2 == null) {
                throw new NullPointerException("Null buttonDeepLink");
            }
            this.buttonDeepLink = Optional.m1897of(buttonDeepLink2);
            return this;
        }

        public com.airbnb.android.core.enums.LottieNuxViewPagerArguments.Builder sharedPrefsConstant(String sharedPrefsConstant2) {
            if (sharedPrefsConstant2 == null) {
                throw new NullPointerException("Null sharedPrefsConstant");
            }
            this.sharedPrefsConstant = sharedPrefsConstant2;
            return this;
        }

        public com.airbnb.android.core.enums.LottieNuxViewPagerArguments.Builder animationFilename(String animationFilename2) {
            if (animationFilename2 == null) {
                throw new NullPointerException("Null animationFilename");
            }
            this.animationFilename = animationFilename2;
            return this;
        }

        public com.airbnb.android.core.enums.LottieNuxViewPagerArguments.Builder coverPageTitleRes(Integer coverPageTitleRes2) {
            if (coverPageTitleRes2 == null) {
                throw new NullPointerException("Null coverPageTitleRes");
            }
            this.coverPageTitleRes = Optional.m1897of(coverPageTitleRes2);
            return this;
        }

        public com.airbnb.android.core.enums.LottieNuxViewPagerArguments.Builder coverPageDescriptionRes(Integer coverPageDescriptionRes2) {
            if (coverPageDescriptionRes2 == null) {
                throw new NullPointerException("Null coverPageDescriptionRes");
            }
            this.coverPageDescriptionRes = Optional.m1897of(coverPageDescriptionRes2);
            return this;
        }

        public com.airbnb.android.core.enums.LottieNuxViewPagerArguments.Builder coverPageButtonTextRes(Integer coverPageButtonTextRes2) {
            if (coverPageButtonTextRes2 == null) {
                throw new NullPointerException("Null coverPageButtonTextRes");
            }
            this.coverPageButtonTextRes = Optional.m1897of(coverPageButtonTextRes2);
            return this;
        }

        public com.airbnb.android.core.enums.LottieNuxViewPagerArguments.Builder coverPageImageRes(Integer coverPageImageRes2) {
            if (coverPageImageRes2 == null) {
                throw new NullPointerException("Null coverPageImageRes");
            }
            this.coverPageImageRes = Optional.m1897of(coverPageImageRes2);
            return this;
        }

        public LottieNuxViewPagerArguments build() {
            String missing = "";
            if (this.pagesContent == null) {
                missing = missing + " pagesContent";
            }
            if (this.animationTimes == null) {
                missing = missing + " animationTimes";
            }
            if (this.sharedPrefsConstant == null) {
                missing = missing + " sharedPrefsConstant";
            }
            if (this.animationFilename == null) {
                missing = missing + " animationFilename";
            }
            if (missing.isEmpty()) {
                return new AutoValue_LottieNuxViewPagerArguments(this.pagesContent, this.animationTimes, this.buttonText, this.buttonDeepLink, this.sharedPrefsConstant, this.animationFilename, this.coverPageTitleRes, this.coverPageDescriptionRes, this.coverPageButtonTextRes, this.coverPageImageRes);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_LottieNuxViewPagerArguments(List<SimpleEntry<Integer, Integer>> pagesContent2, List<Float> animationTimes2, Optional<String> buttonText2, Optional<String> buttonDeepLink2, String sharedPrefsConstant2, String animationFilename2, Optional<Integer> coverPageTitleRes2, Optional<Integer> coverPageDescriptionRes2, Optional<Integer> coverPageButtonTextRes2, Optional<Integer> coverPageImageRes2) {
        if (pagesContent2 == null) {
            throw new NullPointerException("Null pagesContent");
        }
        this.pagesContent = pagesContent2;
        if (animationTimes2 == null) {
            throw new NullPointerException("Null animationTimes");
        }
        this.animationTimes = animationTimes2;
        if (buttonText2 == null) {
            throw new NullPointerException("Null buttonText");
        }
        this.buttonText = buttonText2;
        if (buttonDeepLink2 == null) {
            throw new NullPointerException("Null buttonDeepLink");
        }
        this.buttonDeepLink = buttonDeepLink2;
        if (sharedPrefsConstant2 == null) {
            throw new NullPointerException("Null sharedPrefsConstant");
        }
        this.sharedPrefsConstant = sharedPrefsConstant2;
        if (animationFilename2 == null) {
            throw new NullPointerException("Null animationFilename");
        }
        this.animationFilename = animationFilename2;
        if (coverPageTitleRes2 == null) {
            throw new NullPointerException("Null coverPageTitleRes");
        }
        this.coverPageTitleRes = coverPageTitleRes2;
        if (coverPageDescriptionRes2 == null) {
            throw new NullPointerException("Null coverPageDescriptionRes");
        }
        this.coverPageDescriptionRes = coverPageDescriptionRes2;
        if (coverPageButtonTextRes2 == null) {
            throw new NullPointerException("Null coverPageButtonTextRes");
        }
        this.coverPageButtonTextRes = coverPageButtonTextRes2;
        if (coverPageImageRes2 == null) {
            throw new NullPointerException("Null coverPageImageRes");
        }
        this.coverPageImageRes = coverPageImageRes2;
    }

    public List<SimpleEntry<Integer, Integer>> pagesContent() {
        return this.pagesContent;
    }

    public List<Float> animationTimes() {
        return this.animationTimes;
    }

    public Optional<String> buttonText() {
        return this.buttonText;
    }

    public Optional<String> buttonDeepLink() {
        return this.buttonDeepLink;
    }

    public String sharedPrefsConstant() {
        return this.sharedPrefsConstant;
    }

    public String animationFilename() {
        return this.animationFilename;
    }

    public Optional<Integer> coverPageTitleRes() {
        return this.coverPageTitleRes;
    }

    public Optional<Integer> coverPageDescriptionRes() {
        return this.coverPageDescriptionRes;
    }

    public Optional<Integer> coverPageButtonTextRes() {
        return this.coverPageButtonTextRes;
    }

    public Optional<Integer> coverPageImageRes() {
        return this.coverPageImageRes;
    }

    public String toString() {
        return "LottieNuxViewPagerArguments{pagesContent=" + this.pagesContent + ", animationTimes=" + this.animationTimes + ", buttonText=" + this.buttonText + ", buttonDeepLink=" + this.buttonDeepLink + ", sharedPrefsConstant=" + this.sharedPrefsConstant + ", animationFilename=" + this.animationFilename + ", coverPageTitleRes=" + this.coverPageTitleRes + ", coverPageDescriptionRes=" + this.coverPageDescriptionRes + ", coverPageButtonTextRes=" + this.coverPageButtonTextRes + ", coverPageImageRes=" + this.coverPageImageRes + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof LottieNuxViewPagerArguments)) {
            return false;
        }
        LottieNuxViewPagerArguments that = (LottieNuxViewPagerArguments) o;
        if (!this.pagesContent.equals(that.pagesContent()) || !this.animationTimes.equals(that.animationTimes()) || !this.buttonText.equals(that.buttonText()) || !this.buttonDeepLink.equals(that.buttonDeepLink()) || !this.sharedPrefsConstant.equals(that.sharedPrefsConstant()) || !this.animationFilename.equals(that.animationFilename()) || !this.coverPageTitleRes.equals(that.coverPageTitleRes()) || !this.coverPageDescriptionRes.equals(that.coverPageDescriptionRes()) || !this.coverPageButtonTextRes.equals(that.coverPageButtonTextRes()) || !this.coverPageImageRes.equals(that.coverPageImageRes())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((((((((((((((1 * 1000003) ^ this.pagesContent.hashCode()) * 1000003) ^ this.animationTimes.hashCode()) * 1000003) ^ this.buttonText.hashCode()) * 1000003) ^ this.buttonDeepLink.hashCode()) * 1000003) ^ this.sharedPrefsConstant.hashCode()) * 1000003) ^ this.animationFilename.hashCode()) * 1000003) ^ this.coverPageTitleRes.hashCode()) * 1000003) ^ this.coverPageDescriptionRes.hashCode()) * 1000003) ^ this.coverPageButtonTextRes.hashCode()) * 1000003) ^ this.coverPageImageRes.hashCode();
    }
}
