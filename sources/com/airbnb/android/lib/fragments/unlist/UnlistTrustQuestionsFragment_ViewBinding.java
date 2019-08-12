package com.airbnb.android.lib.fragments.unlist;

import android.view.View;
import android.widget.TextView;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class UnlistTrustQuestionsFragment_ViewBinding extends BaseSnoozeListingFragment_ViewBinding {
    private UnlistTrustQuestionsFragment target;
    private View view2131756856;

    public UnlistTrustQuestionsFragment_ViewBinding(final UnlistTrustQuestionsFragment target2, View source) {
        super(target2, source);
        this.target = target2;
        target2.hostGuaranteeTextView = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.trust_questions_host_guarantee_text, "field 'hostGuaranteeTextView'", TextView.class);
        target2.reservationRequirementTextView = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.trust_questions_set_reservation_requirements_text, "field 'reservationRequirementTextView'", TextView.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.trust_questions_keep_listing_listed_button, "method 'keepListingListed'");
        this.view2131756856 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.keepListingListed();
            }
        });
    }

    public void unbind() {
        UnlistTrustQuestionsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.hostGuaranteeTextView = null;
        target2.reservationRequirementTextView = null;
        this.view2131756856.setOnClickListener(null);
        this.view2131756856 = null;
        super.unbind();
    }
}
