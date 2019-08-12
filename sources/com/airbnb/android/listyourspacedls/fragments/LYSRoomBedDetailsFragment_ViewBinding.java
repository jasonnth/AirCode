package com.airbnb.android.listyourspacedls.fragments;

import android.view.View;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;

public class LYSRoomBedDetailsFragment_ViewBinding extends LYSBaseFragment_ViewBinding {
    private LYSRoomBedDetailsFragment target;
    private View view2131755496;

    public LYSRoomBedDetailsFragment_ViewBinding(final LYSRoomBedDetailsFragment target2, View source) {
        super(target2, source);
        this.target = target2;
        target2.recyclerView = (AirRecyclerView) Utils.findRequiredViewAsType(source, C7251R.C7253id.recycler_view, "field 'recyclerView'", AirRecyclerView.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7251R.C7253id.toolbar, "field 'toolbar'", AirToolbar.class);
        View view = Utils.findRequiredView(source, C7251R.C7253id.save_button, "field 'saveButton' and method 'onClickSave'");
        target2.saveButton = (AirButton) Utils.castView(view, C7251R.C7253id.save_button, "field 'saveButton'", AirButton.class);
        this.view2131755496 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickSave();
            }
        });
    }

    public void unbind() {
        LYSRoomBedDetailsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.recyclerView = null;
        target2.toolbar = null;
        target2.saveButton = null;
        this.view2131755496.setOnClickListener(null);
        this.view2131755496 = null;
        super.unbind();
    }
}
