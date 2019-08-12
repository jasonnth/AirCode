package com.airbnb.android.managelisting.settings.photos;

import android.os.Bundle;
import com.airbnb.android.managelisting.settings.photos.ManagePhotosFragment;
import com.airbnb.p027n2.components.photorearranger.PhotoRearrangerController.Mode;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ManagePhotosFragment$$Icepick<T extends ManagePhotosFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10194H = new Helper("com.airbnb.android.managelisting.settings.photos.ManagePhotosFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.mode = (Mode) f10194H.getSerializable(state, "mode");
            target.uploadingPhotos = f10194H.getParcelableArrayList(state, "uploadingPhotos");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10194H.putSerializable(state, "mode", target.mode);
        f10194H.putParcelableArrayList(state, "uploadingPhotos", target.uploadingPhotos);
    }
}
