package com.airbnb.android.lib.fragments.unlist;

import android.view.View;
import android.widget.TextView;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class SnoozeModeFragment_ViewBinding extends BaseSnoozeListingFragment_ViewBinding {
    private SnoozeModeFragment target;
    private View view2131756794;
    private View view2131756795;
    private View view2131756796;

    public SnoozeModeFragment_ViewBinding(final SnoozeModeFragment target2, View source) {
        super(target2, source);
        this.target = target2;
        View view = Utils.findRequiredView(source, C0880R.C0882id.snooze_start_date_button, "field 'snoozeStartDatePickerButton' and method 'onClickSnoozeStartDateButton'");
        target2.snoozeStartDatePickerButton = (TextView) Utils.castView(view, C0880R.C0882id.snooze_start_date_button, "field 'snoozeStartDatePickerButton'", TextView.class);
        this.view2131756795 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickSnoozeStartDateButton();
            }
        });
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.snooze_end_date_button, "field 'snoozeEndDatePickerButton' and method 'onClickSnoozeEndDateButton'");
        target2.snoozeEndDatePickerButton = (TextView) Utils.castView(view2, C0880R.C0882id.snooze_end_date_button, "field 'snoozeEndDatePickerButton'", TextView.class);
        this.view2131756796 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickSnoozeEndDateButton();
            }
        });
        View view3 = Utils.findRequiredView(source, C0880R.C0882id.submit_snooze_date_button, "method 'snoozeListing'");
        this.view2131756794 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.snoozeListing();
            }
        });
    }

    public void unbind() {
        SnoozeModeFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.snoozeStartDatePickerButton = null;
        target2.snoozeEndDatePickerButton = null;
        this.view2131756795.setOnClickListener(null);
        this.view2131756795 = null;
        this.view2131756796.setOnClickListener(null);
        this.view2131756796 = null;
        this.view2131756794.setOnClickListener(null);
        this.view2131756794 = null;
        super.unbind();
    }
}
