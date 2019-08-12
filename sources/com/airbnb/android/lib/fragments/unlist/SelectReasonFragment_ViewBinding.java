package com.airbnb.android.lib.fragments.unlist;

import android.view.View;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class SelectReasonFragment_ViewBinding extends BaseSnoozeListingFragment_ViewBinding {
    private SelectReasonFragment target;
    private View view2131756732;
    private View view2131756733;
    private View view2131756734;
    private View view2131756735;
    private View view2131756736;
    private View view2131756737;
    private View view2131756738;
    private View view2131756739;

    public SelectReasonFragment_ViewBinding(final SelectReasonFragment target2, View source) {
        super(target2, source);
        this.target = target2;
        View view = Utils.findRequiredView(source, C0880R.C0882id.unlist_reason_no_longer_have_access_to_space_cell, "method 'onClickUnlist'");
        this.view2131756733 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickUnlist(p0);
            }
        });
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.unlist_reason_hosting_too_much_work_cell, "method 'onClickUnlist'");
        this.view2131756734 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickUnlist(p0);
            }
        });
        View view3 = Utils.findRequiredView(source, C0880R.C0882id.unlist_reason_not_earning_enough_cell, "method 'onClickUnlist'");
        this.view2131756735 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickUnlist(p0);
            }
        });
        View view4 = Utils.findRequiredView(source, C0880R.C0882id.unlist_reason_questions_about_law_cell, "method 'onClickUnlist'");
        this.view2131756736 = view4;
        view4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickUnlist(p0);
            }
        });
        View view5 = Utils.findRequiredView(source, C0880R.C0882id.unlist_reason_questions_about_trust_cell, "method 'onClickUnlist'");
        this.view2131756737 = view5;
        view5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickUnlist(p0);
            }
        });
        View view6 = Utils.findRequiredView(source, C0880R.C0882id.unlist_reason_negative_experience_cell, "method 'onClickUnlist'");
        this.view2131756738 = view6;
        view6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickUnlist(p0);
            }
        });
        View view7 = Utils.findRequiredView(source, C0880R.C0882id.unlist_reason_snooze_cell, "method 'onClickSnoozeMode'");
        this.view2131756732 = view7;
        view7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickSnoozeMode();
            }
        });
        View view8 = Utils.findRequiredView(source, C0880R.C0882id.unlist_reason_other_cell, "method 'onClickUnlistFeedback'");
        this.view2131756739 = view8;
        view8.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickUnlistFeedback();
            }
        });
    }

    public void unbind() {
        if (this.target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        this.view2131756733.setOnClickListener(null);
        this.view2131756733 = null;
        this.view2131756734.setOnClickListener(null);
        this.view2131756734 = null;
        this.view2131756735.setOnClickListener(null);
        this.view2131756735 = null;
        this.view2131756736.setOnClickListener(null);
        this.view2131756736 = null;
        this.view2131756737.setOnClickListener(null);
        this.view2131756737 = null;
        this.view2131756738.setOnClickListener(null);
        this.view2131756738 = null;
        this.view2131756732.setOnClickListener(null);
        this.view2131756732 = null;
        this.view2131756739.setOnClickListener(null);
        this.view2131756739 = null;
        super.unbind();
    }
}
