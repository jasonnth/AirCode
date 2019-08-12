package com.airbnb.android.contentframework.imagepicker;

import android.os.Bundle;
import com.airbnb.android.contentframework.imagepicker.StoryCreationImagePickerFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class StoryCreationImagePickerFragment$$Icepick<T extends StoryCreationImagePickerFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8047H = new Helper("com.airbnb.android.contentframework.imagepicker.StoryCreationImagePickerFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.selectedItems = f8047H.getParcelableArrayList(state, "selectedItems");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8047H.putParcelableArrayList(state, "selectedItems", target.selectedItems);
    }
}
