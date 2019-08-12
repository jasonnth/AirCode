package com.airbnb.android.lib.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.views.GroupedCheck;
import com.airbnb.android.lib.views.LoaderListView;

public class ReviewsFragment_ViewBinding implements Unbinder {
    private ReviewsFragment target;

    public ReviewsFragment_ViewBinding(ReviewsFragment target2, View source) {
        this.target = target2;
        target2.mLoaderListView = (LoaderListView) Utils.findRequiredViewAsType(source, 16908298, "field 'mLoaderListView'", LoaderListView.class);
        target2.mTranslateCheckbox = (GroupedCheck) Utils.findRequiredViewAsType(source, C0880R.C0882id.translate_checkbox, "field 'mTranslateCheckbox'", GroupedCheck.class);
    }

    public void unbind() {
        ReviewsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mLoaderListView = null;
        target2.mTranslateCheckbox = null;
    }
}
