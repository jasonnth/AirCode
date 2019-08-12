package com.airbnb.android.profile_completion.edit_about_me;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.profile_completion.C7646R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirEditTextView;

public class EditAboutMeFragment_ViewBinding implements Unbinder {
    private EditAboutMeFragment target;

    public EditAboutMeFragment_ViewBinding(EditAboutMeFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7646R.C7648id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.aboutMeEditText = (AirEditTextView) Utils.findRequiredViewAsType(source, C7646R.C7648id.about_me_edit_text, "field 'aboutMeEditText'", AirEditTextView.class);
    }

    public void unbind() {
        EditAboutMeFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.aboutMeEditText = null;
    }
}
