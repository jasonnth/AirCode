package com.airbnb.android.core.enums;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.erf.Experiments;
import com.google.common.collect.FluentIterable;

public enum UrgencyMessageType implements Parcelable {
    BookingProbability("booking_probability", LottieAnimation.WingsKey),
    CompetingViewsP2("p2_competing_views", LottieAnimation.Binoculars),
    CompetingViewsP3("dated_views_people", LottieAnimation.Eye),
    LastBooked("last_booked", LottieAnimation.LightBulb),
    LastMinuteTrip("last-minute-trip", LottieAnimation.Suitcase),
    LongTermPricingDiscount("long_term_pricing_discount", LottieAnimation.Tag),
    LongTermStayFriendly("long_term_stay_friendly", LottieAnimation.LightBulb),
    GoodPrice("good_price", LottieAnimation.Tag),
    GoodValue("good_value", LottieAnimation.Trophy),
    NumberAvailable("number-available", LottieAnimation.Alarm),
    PercentageAvailable("percent-available", LottieAnimation.Alarm),
    PriceTrends("price_trends", LottieAnimation.LightBulb),
    RareFind("occupancy_rate", LottieAnimation.Diamond),
    RecentViews("recent_views", LottieAnimation.LightBulb),
    SmartPromotion("smart_promotion", LottieAnimation.Tag),
    ExperienceViews("experience_views", LottieAnimation.Binoculars),
    FriendsOnExperiences("friends_on_experiences", LottieAnimation.Binoculars),
    Unknown("", LottieAnimation.LightBulb);
    
    public static final Creator<UrgencyMessageType> CREATOR = null;
    private final LottieAnimation animation;
    private final String serverKey;

    enum LottieAnimation {
        private static final /* synthetic */ LottieAnimation[] $VALUES = null;
        public static final LottieAnimation Alarm = null;
        public static final LottieAnimation Binoculars = null;
        public static final LottieAnimation Diamond = null;
        public static final LottieAnimation Eye = null;
        public static final LottieAnimation LightBulb = null;
        public static final LottieAnimation Suitcase = null;
        public static final LottieAnimation Tag = null;
        public static final LottieAnimation Trophy = null;
        public static final LottieAnimation WingsKey = null;
        private final String fileName;

        public static LottieAnimation valueOf(String name) {
            return (LottieAnimation) Enum.valueOf(LottieAnimation.class, name);
        }

        public static LottieAnimation[] values() {
            return (LottieAnimation[]) $VALUES.clone();
        }

        static {
            Alarm = new LottieAnimation("Alarm", 0, "alarm.json");
            Binoculars = new LottieAnimation("Binoculars", 1, "light-bulb.json");
            Diamond = new LottieAnimation("Diamond", 2, "diamond.json");
            Eye = new LottieAnimation("Eye", 3, "eye.json");
            LightBulb = new LottieAnimation("LightBulb", 4, "light-bulb.json");
            Suitcase = new LottieAnimation("Suitcase", 5, "alarm.json");
            Tag = new LottieAnimation("Tag", 6, "price-tag.json");
            Trophy = new LottieAnimation("Trophy", 7, "uc_trophy_static.json");
            WingsKey = new LottieAnimation("WingsKey", 8, "diamond.json");
            $VALUES = new LottieAnimation[]{Alarm, Binoculars, Diamond, Eye, LightBulb, Suitcase, Tag, Trophy, WingsKey};
        }

        private LottieAnimation(String str, int i, String fileName2) {
            this.fileName = fileName2;
        }

        /* access modifiers changed from: private */
        public String fileName() {
            boolean useAnimated;
            if (Experiments.useNewUrgencyAnimatedIcons()) {
                useAnimated = true;
            } else if (!Experiments.useStaticUrgencyIcons()) {
                return this.fileName;
            } else {
                useAnimated = false;
            }
            switch (this) {
                case Alarm:
                    return useAnimated ? "uc_alarm_animated.json" : "uc_alarm_static.json";
                case Binoculars:
                    return useAnimated ? "uc_binoculars_animated.json" : "uc_binoculars_static.json";
                case Diamond:
                    return useAnimated ? "uc_diamond_animated.json" : "uc_diamond_static.json";
                case Eye:
                    return useAnimated ? "uc_eye_animated.json" : "uc_eye_static.json";
                case LightBulb:
                    return useAnimated ? "uc_light_bulb_animated.json" : "uc_light_bulb_static.json";
                case Suitcase:
                    return useAnimated ? "uc_suitcase_animated.json" : "uc_suitcase_static.json";
                case Tag:
                    return useAnimated ? "uc_tag_animated.json" : "uc_tag_static.json";
                case Trophy:
                    return useAnimated ? "uc_trophy_animated.json" : "uc_trophy_static.json";
                case WingsKey:
                    return useAnimated ? "uc_wings_key_animated.json" : "uc_wings_key_static.json";
                default:
                    BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Unhandled type: " + this));
                    return this.fileName;
            }
        }
    }

    static {
        CREATOR = new Creator<UrgencyMessageType>() {
            public UrgencyMessageType createFromParcel(Parcel source) {
                return UrgencyMessageType.values()[source.readInt()];
            }

            public UrgencyMessageType[] newArray(int size) {
                return new UrgencyMessageType[size];
            }
        };
    }

    private UrgencyMessageType(String serverKey2, LottieAnimation animation2) {
        this.serverKey = serverKey2;
        this.animation = animation2;
    }

    public String getLottieFile() {
        return this.animation.fileName();
    }

    public String getServerKey() {
        return this.serverKey;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ordinal());
    }

    public static UrgencyMessageType fromKey(String key) {
        return (UrgencyMessageType) FluentIterable.m1283of(values()).filter(UrgencyMessageType$$Lambda$1.lambdaFactory$(key)).first().mo41059or(Unknown);
    }
}
