package com.airbnb.android.superhero;

import android.os.Bundle;
import com.airbnb.android.superhero.SuperHeroDataController;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class SuperHeroDataController$$Icepick<T extends SuperHeroDataController> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f2443H = new Helper("com.airbnb.android.superhero.SuperHeroDataController$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.superHeroPostMessages = f2443H.getParcelableArrayList(state, "superHeroPostMessages");
            target.superHeroMessages = (SuperHeroSortedSet) f2443H.getParcelable(state, "superHeroMessages");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f2443H.putParcelableArrayList(state, "superHeroPostMessages", target.superHeroPostMessages);
        f2443H.putParcelable(state, "superHeroMessages", target.superHeroMessages);
    }
}
