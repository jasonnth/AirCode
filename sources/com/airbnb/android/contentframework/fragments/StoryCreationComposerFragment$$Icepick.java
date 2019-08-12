package com.airbnb.android.contentframework.fragments;

import android.os.Bundle;
import com.airbnb.android.contentframework.data.StoryCreationListingAppendix;
import com.airbnb.android.contentframework.fragments.StoryCreationComposerFragment;
import com.airbnb.android.core.models.StoryCreationPlaceTag;
import com.google.android.gms.maps.model.LatLng;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class StoryCreationComposerFragment$$Icepick<T extends StoryCreationComposerFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8042H = new Helper("com.airbnb.android.contentframework.fragments.StoryCreationComposerFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.appendix = (StoryCreationListingAppendix) f8042H.getParcelable(state, "appendix");
            target.images = f8042H.getParcelableArrayList(state, "images");
            target.placeTag = (StoryCreationPlaceTag) f8042H.getParcelable(state, "placeTag");
            target.recoveryErrorMessageShown = f8042H.getBoolean(state, "recoveryErrorMessageShown");
            target.photoLocationLatLng = (LatLng) f8042H.getParcelable(state, "photoLocationLatLng");
            target.suggestedPlaceTags = f8042H.getParcelableArrayList(state, "suggestedPlaceTags");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8042H.putParcelable(state, "appendix", target.appendix);
        f8042H.putParcelableArrayList(state, "images", target.images);
        f8042H.putParcelable(state, "placeTag", target.placeTag);
        f8042H.putBoolean(state, "recoveryErrorMessageShown", target.recoveryErrorMessageShown);
        f8042H.putParcelable(state, "photoLocationLatLng", target.photoLocationLatLng);
        f8042H.putParcelableArrayList(state, "suggestedPlaceTags", target.suggestedPlaceTags);
    }
}
