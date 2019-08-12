package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenTravelDestination;

public class TravelDestination extends GenTravelDestination {
    public static final Creator<TravelDestination> CREATOR = new Creator<TravelDestination>() {
        public TravelDestination[] newArray(int size) {
            return new TravelDestination[size];
        }

        public TravelDestination createFromParcel(Parcel source) {
            TravelDestination destination = new TravelDestination();
            destination.readFromParcel(source);
            return destination;
        }
    };

    public enum Type {
        WEEKEND("weekend"),
        POPULAR("popular"),
        ATTRIBUTE("attribute");
        
        public final String type;

        private Type(String type2) {
            this.type = type2;
        }

        public static Type getTypeFromString(String type2) {
            Type[] values;
            for (Type t : values()) {
                if (t.type.equals(type2)) {
                    return t;
                }
            }
            throw new IllegalStateException("Cannot create Type for " + type2);
        }
    }

    public Type getType() {
        return Type.getTypeFromString(getTravelDestinationType());
    }

    public SearchParams getSearchParams() {
        return getSearchFilterSet();
    }
}
