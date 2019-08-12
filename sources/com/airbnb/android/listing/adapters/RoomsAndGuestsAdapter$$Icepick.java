package com.airbnb.android.listing.adapters;

import android.os.Bundle;
import com.airbnb.android.core.enums.BathroomType;
import com.airbnb.android.core.enums.LegacyBedType;
import com.airbnb.android.core.enums.SpaceType;
import com.airbnb.android.listing.adapters.RoomsAndGuestsAdapter;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class RoomsAndGuestsAdapter$$Icepick<T extends RoomsAndGuestsAdapter> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9802H = new Helper("com.airbnb.android.listing.adapters.RoomsAndGuestsAdapter$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.spaceType = (SpaceType) f9802H.getSerializable(state, "spaceType");
            target.propertyTypeServerKey = f9802H.getInt(state, "propertyTypeServerKey");
            target.propertyTypeTitleRes = f9802H.getInt(state, "propertyTypeTitleRes");
            target.bedroomCount = f9802H.getInt(state, "bedroomCount");
            target.bedCount = f9802H.getInt(state, "bedCount");
            target.legacyBedType = (LegacyBedType) f9802H.getSerializable(state, "legacyBedType");
            target.personCapacity = f9802H.getInt(state, "personCapacity");
            target.bathroomCount = f9802H.getFloat(state, "bathroomCount");
            target.bathroomType = (BathroomType) f9802H.getSerializable(state, "bathroomType");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9802H.putSerializable(state, "spaceType", target.spaceType);
        f9802H.putInt(state, "propertyTypeServerKey", target.propertyTypeServerKey);
        f9802H.putInt(state, "propertyTypeTitleRes", target.propertyTypeTitleRes);
        f9802H.putInt(state, "bedroomCount", target.bedroomCount);
        f9802H.putInt(state, "bedCount", target.bedCount);
        f9802H.putSerializable(state, "legacyBedType", target.legacyBedType);
        f9802H.putInt(state, "personCapacity", target.personCapacity);
        f9802H.putFloat(state, "bathroomCount", target.bathroomCount);
        f9802H.putSerializable(state, "bathroomType", target.bathroomType);
    }
}
