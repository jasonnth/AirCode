package com.airbnb.android.lib.fragments.verifiedid;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class OfflineIdChildFragment_ViewBinding implements Unbinder {
    private OfflineIdChildFragment target;
    private View view2131756157;

    public OfflineIdChildFragment_ViewBinding(final OfflineIdChildFragment target2, View source) {
        this.target = target2;
        target2.privacyText = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.offline_privacy, "field 'privacyText'", TextView.class);
        target2.beginButton = (Button) Utils.findRequiredViewAsType(source, C0880R.C0882id.verified_id_scan_id_button, "field 'beginButton'", Button.class);
        target2.sesameCreditSwitcherContainer = Utils.findRequiredView(source, C0880R.C0882id.verified_id_switch_to_sesame_credit_container, "field 'sesameCreditSwitcherContainer'");
        View view = Utils.findRequiredView(source, C0880R.C0882id.verified_id_switch_to_sesame_credit, "method 'switchToSesameCredit'");
        this.view2131756157 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.switchToSesameCredit();
            }
        });
    }

    public void unbind() {
        OfflineIdChildFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.privacyText = null;
        target2.beginButton = null;
        target2.sesameCreditSwitcherContainer = null;
        this.view2131756157.setOnClickListener(null);
        this.view2131756157 = null;
    }
}
