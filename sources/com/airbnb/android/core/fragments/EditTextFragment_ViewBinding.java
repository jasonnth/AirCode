package com.airbnb.android.core.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirEditTextView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;

public class EditTextFragment_ViewBinding implements Unbinder {
    private EditTextFragment target;

    public EditTextFragment_ViewBinding(EditTextFragment target2, View source) {
        this.target = target2;
        target2.editText = (AirEditTextView) Utils.findRequiredViewAsType(source, C0716R.C0718id.edit_text, "field 'editText'", AirEditTextView.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0716R.C0718id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.titleText = (AirTextView) Utils.findRequiredViewAsType(source, C0716R.C0718id.title_text, "field 'titleText'", AirTextView.class);
        target2.subtitleText = (AirTextView) Utils.findRequiredViewAsType(source, C0716R.C0718id.subtitle_text, "field 'subtitleText'", AirTextView.class);
        target2.userPhoto = (HaloImageView) Utils.findRequiredViewAsType(source, C0716R.C0718id.user_image, "field 'userPhoto'", HaloImageView.class);
        target2.headerView = Utils.findRequiredView(source, C0716R.C0718id.container, "field 'headerView'");
    }

    public void unbind() {
        EditTextFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.editText = null;
        target2.toolbar = null;
        target2.titleText = null;
        target2.subtitleText = null;
        target2.userPhoto = null;
        target2.headerView = null;
    }
}
