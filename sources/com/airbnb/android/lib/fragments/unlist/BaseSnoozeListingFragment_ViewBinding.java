package com.airbnb.android.lib.fragments.unlist;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import com.airbnb.android.lib.C0880R;

public class BaseSnoozeListingFragment_ViewBinding implements Unbinder {
    private BaseSnoozeListingFragment target;
    private View view2131756845;

    public BaseSnoozeListingFragment_ViewBinding(final BaseSnoozeListingFragment target2, View source) {
        this.target = target2;
        View view = source.findViewById(C0880R.C0882id.unlist_my_space_btn);
        if (view != null) {
            this.view2131756845 = view;
            view.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View p0) {
                    target2.unlistListing();
                }
            });
        }
    }

    public void unbind() {
        if (this.target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        if (this.view2131756845 != null) {
            this.view2131756845.setOnClickListener(null);
            this.view2131756845 = null;
        }
    }
}
