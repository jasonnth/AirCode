package com.airbnb.android.lib.fragments.unlist;

import android.view.View;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.views.GroupedCounter;

public class UnlistTooMuchWorkFragment_ViewBinding extends BaseSnoozeListingFragment_ViewBinding {
    private UnlistTooMuchWorkFragment target;
    private View view2131756853;

    public UnlistTooMuchWorkFragment_ViewBinding(final UnlistTooMuchWorkFragment target2, View source) {
        super(target2, source);
        this.target = target2;
        target2.snoozeEndDateCounter = (GroupedCounter) Utils.findRequiredViewAsType(source, C0880R.C0882id.counter_end_snooze_date, "field 'snoozeEndDateCounter'", GroupedCounter.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.too_much_work_snooze_button, "method 'snoozeListingFromToday'");
        this.view2131756853 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.snoozeListingFromToday();
            }
        });
    }

    public void unbind() {
        UnlistTooMuchWorkFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.snoozeEndDateCounter = null;
        this.view2131756853.setOnClickListener(null);
        this.view2131756853 = null;
        super.unbind();
    }
}
