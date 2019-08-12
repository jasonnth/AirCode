package com.airbnb.android.photomarkupeditor.fragments;

import android.os.Bundle;
import com.airbnb.android.photomarkupeditor.enums.DrawingColor;
import com.airbnb.android.photomarkupeditor.fragments.PhotoMarkupEditorFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class PhotoMarkupEditorFragment$$Icepick<T extends PhotoMarkupEditorFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10831H = new Helper("com.airbnb.android.photomarkupeditor.fragments.PhotoMarkupEditorFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.drawingColor = (DrawingColor) f10831H.getSerializable(state, "drawingColor");
            target.imageSource = f10831H.getString(state, "imageSource");
            target.hasEdits = f10831H.getBoolean(state, "hasEdits");
            target.isCroppedOrRotated = f10831H.getBoolean(state, "isCroppedOrRotated");
            target.isColorPickerOpen = f10831H.getBoolean(state, "isColorPickerOpen");
            target.asyncTaskStatus = f10831H.getInt(state, "asyncTaskStatus");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10831H.putSerializable(state, "drawingColor", target.drawingColor);
        f10831H.putString(state, "imageSource", target.imageSource);
        f10831H.putBoolean(state, "hasEdits", target.hasEdits);
        f10831H.putBoolean(state, "isCroppedOrRotated", target.isCroppedOrRotated);
        f10831H.putBoolean(state, "isColorPickerOpen", target.isColorPickerOpen);
        f10831H.putInt(state, "asyncTaskStatus", target.asyncTaskStatus);
    }
}
