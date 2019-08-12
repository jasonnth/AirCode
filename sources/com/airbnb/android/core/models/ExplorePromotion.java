package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenExplorePromotion;

public class ExplorePromotion extends GenExplorePromotion {
    public static final Creator<ExplorePromotion> CREATOR = new Creator<ExplorePromotion>() {
        public ExplorePromotion[] newArray(int size) {
            return new ExplorePromotion[size];
        }

        public ExplorePromotion createFromParcel(Parcel source) {
            ExplorePromotion object = new ExplorePromotion();
            object.readFromParcel(source);
            return object;
        }
    };

    public enum CallToAction {
        TurnOnGps(0),
        OptInNotification(2),
        ExperienceCategory(3),
        AddLocation(4),
        AddDate(5),
        Unknown(-1);
        
        public int type;

        private CallToAction(int type2) {
            this.type = type2;
        }
    }

    public CallToAction getCallToAction() {
        CallToAction[] values;
        for (CallToAction callToAction : CallToAction.values()) {
            if (getType() == callToAction.type) {
                return callToAction;
            }
        }
        return CallToAction.Unknown;
    }
}
