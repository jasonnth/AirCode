package com.airbnb.android.lib.tripassistant.amenities;

import android.os.Bundle;
import com.airbnb.android.lib.tripassistant.amenities.HTPhotoPickerActivity;
import com.facebook.places.model.PlaceFields;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class HTPhotoPickerActivity$$Icepick<T extends HTPhotoPickerActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9667H = new Helper("com.airbnb.android.lib.tripassistant.amenities.HTPhotoPickerActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.photos = f9667H.getParcelableArrayList(state, PlaceFields.PHOTOS_PROFILE);
            target.deletedPhotos = f9667H.getParcelableArrayList(state, "deletedPhotos");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9667H.putParcelableArrayList(state, PlaceFields.PHOTOS_PROFILE, target.photos);
        f9667H.putParcelableArrayList(state, "deletedPhotos", target.deletedPhotos);
    }
}
