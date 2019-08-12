package com.airbnb.android.listyourspacedls.fragments;

import android.os.Bundle;
import com.airbnb.android.listyourspacedls.fragments.LYSPhotoManagerFragment;
import com.airbnb.p027n2.components.photorearranger.PhotoRearrangerController.Mode;
import icepick.Bundler;
import icepick.Injector.Helper;
import java.util.HashMap;
import java.util.Map;

public class LYSPhotoManagerFragment$$Icepick<T extends LYSPhotoManagerFragment> extends LYSBaseFragment$$Icepick<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9936H = new Helper("com.airbnb.android.listyourspacedls.fragments.LYSPhotoManagerFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.mode = (Mode) f9936H.getSerializable(state, "mode");
            target.uploadingPhotos = f9936H.getParcelableArrayList(state, "uploadingPhotos");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9936H.putSerializable(state, "mode", target.mode);
        f9936H.putParcelableArrayList(state, "uploadingPhotos", target.uploadingPhotos);
    }
}
