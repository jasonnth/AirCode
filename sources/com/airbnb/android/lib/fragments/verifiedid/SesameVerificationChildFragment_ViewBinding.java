package com.airbnb.android.lib.fragments.verifiedid;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class SesameVerificationChildFragment_ViewBinding implements Unbinder {
    private SesameVerificationChildFragment target;
    private View view2131756753;
    private View view2131756755;

    public SesameVerificationChildFragment_ViewBinding(final SesameVerificationChildFragment target2, View source) {
        this.target = target2;
        target2.privacyText = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.sesame_verification_privacy_policy, "field 'privacyText'", TextView.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.sesame_verification_connect, "method 'connectSesame'");
        this.view2131756753 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.connectSesame();
            }
        });
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.switch_to_verify_gov_id, "method 'switchToVerifyGovID'");
        this.view2131756755 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.switchToVerifyGovID();
            }
        });
    }

    public void unbind() {
        SesameVerificationChildFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.privacyText = null;
        this.view2131756753.setOnClickListener(null);
        this.view2131756753 = null;
        this.view2131756755.setOnClickListener(null);
        this.view2131756755 = null;
    }
}
