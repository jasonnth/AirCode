package com.airbnb.android.lib.fragments.communitycommitment;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.views.ScrollViewWithCustomListener;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;

public class CommunityCommitmentCancelAccountFragment_ViewBinding implements Unbinder {
    private CommunityCommitmentCancelAccountFragment target;
    private View view2131756208;
    private View view2131756209;

    public CommunityCommitmentCancelAccountFragment_ViewBinding(final CommunityCommitmentCancelAccountFragment target2, View source) {
        this.target = target2;
        target2.titleMarquee = (DocumentMarquee) Utils.findRequiredViewAsType(source, C0880R.C0882id.title_marquee, "field 'titleMarquee'", DocumentMarquee.class);
        target2.introText = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.intro_text, "field 'introText'", AirTextView.class);
        target2.reservationConcernTitleRow = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.row_reservation_concern_title, "field 'reservationConcernTitleRow'", AirTextView.class);
        target2.reservationConcernBodyRow = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.row_reservation_concern_body, "field 'reservationConcernBodyRow'", AirTextView.class);
        target2.safetyConcernTitleRow = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.row_safety_concern_title, "field 'safetyConcernTitleRow'", AirTextView.class);
        target2.safetyConcernBodyRow = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.row_safety_concern_body, "field 'safetyConcernBodyRow'", AirTextView.class);
        target2.lawConcernTitleRow = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.row_law_concern_title, "field 'lawConcernTitleRow'", AirTextView.class);
        target2.lawConcernBodyRow = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.row_law_concern_body, "field 'lawConcernBodyRow'", AirTextView.class);
        target2.disabilityConcernTitleRow = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.row_disability_concern_title, "field 'disabilityConcernTitleRow'", AirTextView.class);
        target2.disabilityConcernBodyRow = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.row_disability_concern_body, "field 'disabilityConcernBodyRow'", AirTextView.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.cancel_account_button, "field 'cancelAccountButton' and method 'cancelAccountAndAskForFeedback'");
        target2.cancelAccountButton = (AirButton) Utils.castView(view, C0880R.C0882id.cancel_account_button, "field 'cancelAccountButton'", AirButton.class);
        this.view2131756208 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.cancelAccountAndAskForFeedback();
            }
        });
        target2.shareFeedbackBodyRow = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.row_share_feedback_body, "field 'shareFeedbackBodyRow'", TextView.class);
        target2.moreHelpInfoBodyRow = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.row_more_help_info_body, "field 'moreHelpInfoBodyRow'", TextView.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.scrollView = (ScrollViewWithCustomListener) Utils.findRequiredViewAsType(source, C0880R.C0882id.scroll_view, "field 'scrollView'", ScrollViewWithCustomListener.class);
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.go_back_button, "method 'goBackToPreviousScreen'");
        this.view2131756209 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.goBackToPreviousScreen();
            }
        });
        target2.bottomPadding = source.getContext().getResources().getDimensionPixelSize(C0880R.dimen.n2_vertical_padding_large);
    }

    public void unbind() {
        CommunityCommitmentCancelAccountFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleMarquee = null;
        target2.introText = null;
        target2.reservationConcernTitleRow = null;
        target2.reservationConcernBodyRow = null;
        target2.safetyConcernTitleRow = null;
        target2.safetyConcernBodyRow = null;
        target2.lawConcernTitleRow = null;
        target2.lawConcernBodyRow = null;
        target2.disabilityConcernTitleRow = null;
        target2.disabilityConcernBodyRow = null;
        target2.cancelAccountButton = null;
        target2.shareFeedbackBodyRow = null;
        target2.moreHelpInfoBodyRow = null;
        target2.toolbar = null;
        target2.scrollView = null;
        this.view2131756208.setOnClickListener(null);
        this.view2131756208 = null;
        this.view2131756209.setOnClickListener(null);
        this.view2131756209 = null;
    }
}
