package com.airbnb.android.contentframework.fragments;

import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.p027n2.components.AirToolbar;

public final class CommentInputFragment_ViewBinding implements Unbinder {
    private CommentInputFragment target;

    public CommentInputFragment_ViewBinding(CommentInputFragment target2, View source) {
        this.target = target2;
        target2.inputEditText = (EditText) Utils.findRequiredViewAsType(source, C5709R.C5711id.input_editText, "field 'inputEditText'", EditText.class);
        target2.airToolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C5709R.C5711id.toolbar, "field 'airToolbar'", AirToolbar.class);
        target2.loaderFrame = (LoaderFrame) Utils.findRequiredViewAsType(source, C5709R.C5711id.loading_overlay, "field 'loaderFrame'", LoaderFrame.class);
    }

    public void unbind() {
        CommentInputFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.inputEditText = null;
        target2.airToolbar = null;
        target2.loaderFrame = null;
    }
}
