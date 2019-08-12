package com.airbnb.android.core.instant_promo.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.instant_promo.models.generated.GenInstantPromotion;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.FluentIterable;

public class InstantPromotion extends GenInstantPromotion {
    public static final Creator<InstantPromotion> CREATOR = new Creator<InstantPromotion>() {
        public InstantPromotion[] newArray(int size) {
            return new InstantPromotion[size];
        }

        public InstantPromotion createFromParcel(Parcel source) {
            InstantPromotion object = new InstantPromotion();
            object.readFromParcel(source);
            return object;
        }
    };

    public enum SurfaceType {
        ForYouTab("for_you_tab");
        
        public final String key;

        private SurfaceType(String media_text_unit) {
            this.key = media_text_unit;
        }

        static SurfaceType fromKey(String key2) {
            return (SurfaceType) FluentIterable.from((E[]) values()).filter(InstantPromotion$SurfaceType$$Lambda$1.lambdaFactory$(key2)).first().orNull();
        }
    }

    public enum TemplateType {
        SimpleMediaTabUnit("simple_media_tab_unit");
        
        public final String key;

        private TemplateType(String key2) {
            this.key = key2;
        }

        static TemplateType fromKey(String key2) {
            return (TemplateType) FluentIterable.from((E[]) values()).firstMatch(InstantPromotion$TemplateType$$Lambda$1.lambdaFactory$(key2)).orNull();
        }
    }

    @JsonProperty("template")
    public void setTemplate(String key) {
        this.mTemplate = TemplateType.fromKey(key);
    }

    @JsonProperty("surface")
    public void setSurface(String key) {
        this.mSurface = SurfaceType.fromKey(key);
    }
}
