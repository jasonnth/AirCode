package com.airbnb.android.lib.fragments.reservationresponse;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;

public class ReservationDeclineConfirmationFragment_ViewBinding implements Unbinder {
    private ReservationDeclineConfirmationFragment target;
    private View view2131756718;
    private View view2131756719;

    public ReservationDeclineConfirmationFragment_ViewBinding(final ReservationDeclineConfirmationFragment target2, View source) {
        this.target = target2;
        target2.confirmationMessageView = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.ro_response_confirmation_message, "field 'confirmationMessageView'", AirTextView.class);
        target2.confirmationTitleView = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.ro_response_confirmation_title, "field 'confirmationTitleView'", AirTextView.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.ro_response_confirmation_tips, "field 'showTipsButton' and method 'onShowTips'");
        target2.showTipsButton = (AirButton) Utils.castView(view, C0880R.C0882id.ro_response_confirmation_tips, "field 'showTipsButton'", AirButton.class);
        this.view2131756719 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onShowTips();
            }
        });
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.ro_response_confirmation_done, "method 'onDone'");
        this.view2131756718 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onDone();
            }
        });
    }

    public void unbind() {
        ReservationDeclineConfirmationFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.confirmationMessageView = null;
        target2.confirmationTitleView = null;
        target2.showTipsButton = null;
        this.view2131756719.setOnClickListener(null);
        this.view2131756719 = null;
        this.view2131756718.setOnClickListener(null);
        this.view2131756718 = null;
    }
}
