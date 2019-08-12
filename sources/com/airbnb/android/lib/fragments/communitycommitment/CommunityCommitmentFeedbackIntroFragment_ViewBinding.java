package com.airbnb.android.lib.fragments.communitycommitment;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;

public class CommunityCommitmentFeedbackIntroFragment_ViewBinding implements Unbinder {
    private CommunityCommitmentFeedbackIntroFragment target;
    private View view2131756211;

    public CommunityCommitmentFeedbackIntroFragment_ViewBinding(final CommunityCommitmentFeedbackIntroFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.introMarquee = (DocumentMarquee) Utils.findRequiredViewAsType(source, C0880R.C0882id.write_feedback_intro, "field 'introMarquee'", DocumentMarquee.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.write_feedback, "method 'writeFeeback'");
        this.view2131756211 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.writeFeeback();
            }
        });
    }

    public void unbind() {
        CommunityCommitmentFeedbackIntroFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.introMarquee = null;
        this.view2131756211.setOnClickListener(null);
        this.view2131756211 = null;
    }
}
