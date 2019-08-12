package com.airbnb.android.lib.fragments.unlist;

import android.view.View;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.views.GroupedStatusCell;

public class ListingVisibilityFragment_ViewBinding extends BaseSnoozeListingFragment_ViewBinding {
    private ListingVisibilityFragment target;
    private View view2131756438;
    private View view2131756439;
    private View view2131756440;
    private View view2131756441;

    public ListingVisibilityFragment_ViewBinding(final ListingVisibilityFragment target2, View source) {
        super(target2, source);
        this.target = target2;
        View view = Utils.findRequiredView(source, C0880R.C0882id.list_listing_cell, "field 'listCell' and method 'onClickListListing'");
        target2.listCell = (GroupedStatusCell) Utils.castView(view, C0880R.C0882id.list_listing_cell, "field 'listCell'", GroupedStatusCell.class);
        this.view2131756438 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickListListing();
            }
        });
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.snooze_listing_cell, "field 'snoozeCell' and method 'onClickSnoozeMode'");
        target2.snoozeCell = (GroupedStatusCell) Utils.castView(view2, C0880R.C0882id.snooze_listing_cell, "field 'snoozeCell'", GroupedStatusCell.class);
        this.view2131756439 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickSnoozeMode();
            }
        });
        View view3 = Utils.findRequiredView(source, C0880R.C0882id.unlist_listing_cell, "field 'unlistCell' and method 'onClickUnlistReason'");
        target2.unlistCell = (GroupedStatusCell) Utils.castView(view3, C0880R.C0882id.unlist_listing_cell, "field 'unlistCell'", GroupedStatusCell.class);
        this.view2131756440 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickUnlistReason();
            }
        });
        View view4 = Utils.findRequiredView(source, C0880R.C0882id.deactivate_listing_text, "method 'onClickDeleteListing'");
        this.view2131756441 = view4;
        view4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickDeleteListing();
            }
        });
    }

    public void unbind() {
        ListingVisibilityFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.listCell = null;
        target2.snoozeCell = null;
        target2.unlistCell = null;
        this.view2131756438.setOnClickListener(null);
        this.view2131756438 = null;
        this.view2131756439.setOnClickListener(null);
        this.view2131756439 = null;
        this.view2131756440.setOnClickListener(null);
        this.view2131756440 = null;
        this.view2131756441.setOnClickListener(null);
        this.view2131756441 = null;
        super.unbind();
    }
}
