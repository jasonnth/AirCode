package com.airbnb.android.lib.views;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class GuestIdentifyInformationView_ViewBinding implements Unbinder {
    private GuestIdentifyInformationView target;
    private View view2131756980;

    public GuestIdentifyInformationView_ViewBinding(GuestIdentifyInformationView target2) {
        this(target2, target2);
    }

    public GuestIdentifyInformationView_ViewBinding(final GuestIdentifyInformationView target2, View source) {
        this.target = target2;
        target2.additionalDetailsLayout = Utils.findRequiredView(source, C0880R.C0882id.additional_details_layout, "field 'additionalDetailsLayout'");
        View view = Utils.findRequiredView(source, C0880R.C0882id.full_name_cell, "field 'fullNameCell' and method 'onFullNameClick'");
        target2.fullNameCell = (GroupedCell) Utils.castView(view, C0880R.C0882id.full_name_cell, "field 'fullNameCell'", GroupedCell.class);
        this.view2131756980 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onFullNameClick((GroupedCell) Utils.castParam(p0, "doClick", 0, "onFullNameClick", 0));
            }
        });
        target2.secondInfoCell = (GroupedCell) Utils.findRequiredViewAsType(source, C0880R.C0882id.second_info_cell, "field 'secondInfoCell'", GroupedCell.class);
        target2.thirdInfoCell = (GroupedCell) Utils.findRequiredViewAsType(source, C0880R.C0882id.third_info_cell, "field 'thirdInfoCell'", GroupedCell.class);
    }

    public void unbind() {
        GuestIdentifyInformationView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.additionalDetailsLayout = null;
        target2.fullNameCell = null;
        target2.secondInfoCell = null;
        target2.thirdInfoCell = null;
        this.view2131756980.setOnClickListener(null);
        this.view2131756980 = null;
    }
}
