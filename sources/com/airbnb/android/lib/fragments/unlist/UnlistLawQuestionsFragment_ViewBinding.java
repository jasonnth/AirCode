package com.airbnb.android.lib.fragments.unlist;

import android.view.View;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class UnlistLawQuestionsFragment_ViewBinding extends BaseSnoozeListingFragment_ViewBinding {
    private UnlistLawQuestionsFragment target;
    private View view2131756846;

    public UnlistLawQuestionsFragment_ViewBinding(final UnlistLawQuestionsFragment target2, View source) {
        super(target2, source);
        this.target = target2;
        View view = Utils.findRequiredView(source, C0880R.C0882id.law_questions_keep_listing_listed_button, "method 'keepListingListed'");
        this.view2131756846 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.keepListingListed();
            }
        });
    }

    public void unbind() {
        if (this.target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        this.view2131756846.setOnClickListener(null);
        this.view2131756846 = null;
        super.unbind();
    }
}
