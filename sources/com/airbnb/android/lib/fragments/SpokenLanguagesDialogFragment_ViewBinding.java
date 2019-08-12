package com.airbnb.android.lib.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.views.LoaderListView;

public class SpokenLanguagesDialogFragment_ViewBinding implements Unbinder {
    private SpokenLanguagesDialogFragment target;

    public SpokenLanguagesDialogFragment_ViewBinding(SpokenLanguagesDialogFragment target2, View source) {
        this.target = target2;
        target2.mLoaderListView = (LoaderListView) Utils.findRequiredViewAsType(source, 16908298, "field 'mLoaderListView'", LoaderListView.class);
    }

    public void unbind() {
        SpokenLanguagesDialogFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mLoaderListView = null;
    }
}
