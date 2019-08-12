package com.airbnb.android.listyourspacedls.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.p027n2.collections.VerboseScrollView;
import com.airbnb.p027n2.components.AirToolbar;

public class LYSGuestAdditionalRequirementsFragment_ViewBinding extends LYSBaseFragment_ViewBinding {
    private LYSGuestAdditionalRequirementsFragment target;
    private View view2131755495;

    public LYSGuestAdditionalRequirementsFragment_ViewBinding(final LYSGuestAdditionalRequirementsFragment target2, View source) {
        super(target2, source);
        this.target = target2;
        target2.scrollView = (VerboseScrollView) Utils.findRequiredViewAsType(source, C7251R.C7253id.scroll_view, "field 'scrollView'", VerboseScrollView.class);
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C7251R.C7253id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7251R.C7253id.toolbar, "field 'toolbar'", AirToolbar.class);
        View view = Utils.findRequiredView(source, C7251R.C7253id.next_btn, "method 'clickSave'");
        this.view2131755495 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.clickSave();
            }
        });
    }

    public void unbind() {
        LYSGuestAdditionalRequirementsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.scrollView = null;
        target2.recyclerView = null;
        target2.toolbar = null;
        this.view2131755495.setOnClickListener(null);
        this.view2131755495 = null;
        super.unbind();
    }
}
