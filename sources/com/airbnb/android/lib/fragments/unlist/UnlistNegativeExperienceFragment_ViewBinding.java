package com.airbnb.android.lib.fragments.unlist;

import android.view.View;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class UnlistNegativeExperienceFragment_ViewBinding extends BaseSnoozeListingFragment_ViewBinding {
    private UnlistNegativeExperienceFragment target;
    private View view2131756847;

    public UnlistNegativeExperienceFragment_ViewBinding(final UnlistNegativeExperienceFragment target2, View source) {
        super(target2, source);
        this.target = target2;
        View view = Utils.findRequiredView(source, C0880R.C0882id.negative_experience_unlist_and_contact_us, "method 'unlistAndContactUs'");
        this.view2131756847 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.unlistAndContactUs();
            }
        });
    }

    public void unbind() {
        if (this.target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        this.view2131756847.setOnClickListener(null);
        this.view2131756847 = null;
        super.unbind();
    }
}
