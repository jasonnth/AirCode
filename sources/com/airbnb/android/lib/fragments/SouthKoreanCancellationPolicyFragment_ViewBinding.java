package com.airbnb.android.lib.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.DocumentMarquee;

public class SouthKoreanCancellationPolicyFragment_ViewBinding implements Unbinder {
    private SouthKoreanCancellationPolicyFragment target;
    private View view2131756104;

    public SouthKoreanCancellationPolicyFragment_ViewBinding(final SouthKoreanCancellationPolicyFragment target2, View source) {
        this.target = target2;
        target2.marquee = (DocumentMarquee) Utils.findRequiredViewAsType(source, C0880R.C0882id.sk_cancellation_marquee, "field 'marquee'", DocumentMarquee.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.continue_button, "method 'clickContinue'");
        this.view2131756104 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.clickContinue();
            }
        });
    }

    public void unbind() {
        SouthKoreanCancellationPolicyFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.marquee = null;
        this.view2131756104.setOnClickListener(null);
        this.view2131756104 = null;
    }
}
