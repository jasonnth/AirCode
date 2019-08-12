package com.airbnb.android.listyourspacedls.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;

public class LYSRTBChecklistFragment_ViewBinding extends LYSBaseFragment_ViewBinding {
    private LYSRTBChecklistFragment target;
    private View view2131755476;

    public LYSRTBChecklistFragment_ViewBinding(final LYSRTBChecklistFragment target2, View source) {
        super(target2, source);
        this.target = target2;
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C7251R.C7253id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7251R.C7253id.toolbar, "field 'toolbar'", AirToolbar.class);
        View view = Utils.findRequiredView(source, C7251R.C7253id.done_button, "field 'doneButton' and method 'clickNext'");
        target2.doneButton = (AirButton) Utils.castView(view, C7251R.C7253id.done_button, "field 'doneButton'", AirButton.class);
        this.view2131755476 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.clickNext();
            }
        });
    }

    public void unbind() {
        LYSRTBChecklistFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.recyclerView = null;
        target2.toolbar = null;
        target2.doneButton = null;
        this.view2131755476.setOnClickListener(null);
        this.view2131755476 = null;
        super.unbind();
    }
}
