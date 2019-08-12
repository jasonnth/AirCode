package com.airbnb.android.fixit.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.AirEditTextPageView;
import com.airbnb.android.fixit.C6380R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedDualActionFooter;

public class FixItItemCommentFragment_ViewBinding implements Unbinder {
    private FixItItemCommentFragment target;

    public FixItItemCommentFragment_ViewBinding(FixItItemCommentFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C6380R.C6382id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.editTextPage = (AirEditTextPageView) Utils.findRequiredViewAsType(source, C6380R.C6382id.edit_text_page, "field 'editTextPage'", AirEditTextPageView.class);
        target2.footer = (FixedDualActionFooter) Utils.findRequiredViewAsType(source, C6380R.C6382id.footer, "field 'footer'", FixedDualActionFooter.class);
    }

    public void unbind() {
        FixItItemCommentFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.editTextPage = null;
        target2.footer = null;
    }
}
