package com.airbnb.android.lib.fragments.find;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.presenters.p338n2.ListingSelectionView;
import com.airbnb.p027n2.components.AirToolbar;

public class ListingSelectionFragment_ViewBinding implements Unbinder {
    private ListingSelectionFragment target;
    private View view2131756164;

    public ListingSelectionFragment_ViewBinding(final ListingSelectionFragment target2, View source) {
        this.target = target2;
        target2.selectionView = (ListingSelectionView) Utils.findRequiredViewAsType(source, C0880R.C0882id.selection_view, "field 'selectionView'", ListingSelectionView.class);
        target2.loader = Utils.findRequiredView(source, C0880R.C0882id.full_loader, "field 'loader'");
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.save_button, "method 'onSaveClicked'");
        this.view2131756164 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onSaveClicked();
            }
        });
    }

    public void unbind() {
        ListingSelectionFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.selectionView = null;
        target2.loader = null;
        target2.toolbar = null;
        this.view2131756164.setOnClickListener(null);
        this.view2131756164 = null;
    }
}
