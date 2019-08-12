package com.airbnb.android.listyourspacedls.fragments;

import android.view.View;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.AirEditTextPageView;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.p027n2.components.AirToolbar;

public class LYSTextSettingFragment_ViewBinding extends LYSBaseFragment_ViewBinding {
    private LYSTextSettingFragment target;
    private View view2131755495;

    public LYSTextSettingFragment_ViewBinding(final LYSTextSettingFragment target2, View source) {
        super(target2, source);
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7251R.C7253id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.editTextPage = (AirEditTextPageView) Utils.findRequiredViewAsType(source, C7251R.C7253id.edit_text_page, "field 'editTextPage'", AirEditTextPageView.class);
        View view = Utils.findRequiredView(source, C7251R.C7253id.next_btn, "method 'saveClicked'");
        this.view2131755495 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.saveClicked();
            }
        });
    }

    public void unbind() {
        LYSTextSettingFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.editTextPage = null;
        this.view2131755495.setOnClickListener(null);
        this.view2131755495 = null;
        super.unbind();
    }
}
