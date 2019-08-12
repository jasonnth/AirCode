package com.airbnb.android.core.beta.models.guidebook;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.beta.models.guidebook.generated.GenLocalAttraction;
import java.util.HashMap;

public class LocalAttraction extends GenLocalAttraction {
    public static final Creator<LocalAttraction> CREATOR = new Creator<LocalAttraction>() {
        public LocalAttraction[] newArray(int size) {
            return new LocalAttraction[size];
        }

        public LocalAttraction createFromParcel(Parcel source) {
            LocalAttraction object = new LocalAttraction();
            object.readFromParcel(source);
            return object;
        }
    };

    public enum GuidebookPin {
        STORE("store", C0716R.C0717drawable.map_pin_basket_sm, C0716R.C0717drawable.map_pin_basket, C0716R.C0717drawable.np_basket),
        CAFE("cafe", C0716R.C0717drawable.map_pin_cup_sm, C0716R.C0717drawable.map_pin_cup, C0716R.C0717drawable.np_cup),
        RESTAURANT("restaurant", C0716R.C0717drawable.map_pin_dining_sm, C0716R.C0717drawable.map_pin_dining, C0716R.C0717drawable.np_dining),
        BAR("bar", C0716R.C0717drawable.map_pin_evening_sm, C0716R.C0717drawable.map_pin_evening, C0716R.C0717drawable.np_evening),
        ART("art", C0716R.C0717drawable.map_pin_event_sm, C0716R.C0717drawable.map_pin_event, C0716R.C0717drawable.np_events),
        TOURISM("tourism", C0716R.C0717drawable.map_pin_eye_sm, C0716R.C0717drawable.map_pin_eye, C0716R.C0717drawable.np_eye),
        OTHER("other", C0716R.C0717drawable.map_pin_favorite_sm, C0716R.C0717drawable.map_pin_favorite, C0716R.C0717drawable.np_flag),
        FLAG("flag", C0716R.C0717drawable.map_pin_flag_sm, C0716R.C0717drawable.map_pin_flag, C0716R.C0717drawable.np_flag),
        KIDS("kids", C0716R.C0717drawable.map_pin_kids_sm, C0716R.C0717drawable.map_pin_kids, C0716R.C0717drawable.np_kids),
        SHOPPING("shopping", C0716R.C0717drawable.map_pin_shopping_sm, C0716R.C0717drawable.map_pin_shopping, C0716R.C0717drawable.np_shopping),
        WIFI("wifi", C0716R.C0717drawable.map_pin_wifi_sm, C0716R.C0717drawable.map_pin_wifi, C0716R.C0717drawable.np_wifi);
        
        private static final HashMap<String, GuidebookPin> mMappings = null;
        private final String mPinValue;
        private final int mResourceIdLarge;
        private final int mResourceIdSmall;
        private final int mResourceThumbnail;

        static {
            int i;
            GuidebookPin[] values;
            mMappings = new HashMap<>();
            for (GuidebookPin pin : values()) {
                mMappings.put(pin.getPinValue(), pin);
            }
        }

        private GuidebookPin(String pinValue, int resourceIdSmall, int resourceIdLarge, int resourceThumbnail) {
            this.mPinValue = pinValue;
            this.mResourceIdSmall = resourceIdSmall;
            this.mResourceIdLarge = resourceIdLarge;
            this.mResourceThumbnail = resourceThumbnail;
        }

        private String getPinValue() {
            return this.mPinValue;
        }

        public int getResourceIdSmall() {
            return this.mResourceIdSmall;
        }

        public int getResourceIdLarge() {
            return this.mResourceIdLarge;
        }

        public int getResourceThumbnail() {
            return this.mResourceThumbnail;
        }

        public static GuidebookPin getGuidebookPin(String pinValue) {
            GuidebookPin pin = (GuidebookPin) mMappings.get(pinValue);
            return pin != null ? pin : FLAG;
        }
    }
}
