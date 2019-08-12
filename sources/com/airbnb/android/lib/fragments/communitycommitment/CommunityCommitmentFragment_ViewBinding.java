package com.airbnb.android.lib.fragments.communitycommitment;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.DocumentMarquee;

public class CommunityCommitmentFragment_ViewBinding implements Unbinder {
    private CommunityCommitmentFragment target;
    private View view2131755988;
    private View view2131756190;

    public CommunityCommitmentFragment_ViewBinding(final CommunityCommitmentFragment target2, View source) {
        this.target = target2;
        target2.marquee = (DocumentMarquee) Utils.findRequiredViewAsType(source, C0880R.C0882id.agreement_text, "field 'marquee'", DocumentMarquee.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.accept_button, "method 'accept'");
        this.view2131755988 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.accept();
            }
        });
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.decline_button, "method 'showCancellationContent'");
        this.view2131756190 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.showCancellationContent();
            }
        });
    }

    public void unbind() {
        CommunityCommitmentFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.marquee = null;
        this.view2131755988.setOnClickListener(null);
        this.view2131755988 = null;
        this.view2131756190.setOnClickListener(null);
        this.view2131756190 = null;
    }
}
