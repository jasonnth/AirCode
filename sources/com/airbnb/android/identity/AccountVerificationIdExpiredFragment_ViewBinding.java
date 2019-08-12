package com.airbnb.android.identity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.SheetMarquee;

public class AccountVerificationIdExpiredFragment_ViewBinding implements Unbinder {
    private AccountVerificationIdExpiredFragment target;
    private View view2131755601;

    public AccountVerificationIdExpiredFragment_ViewBinding(final AccountVerificationIdExpiredFragment target2, View source) {
        this.target = target2;
        target2.sheetMarquee = (SheetMarquee) Utils.findRequiredViewAsType(source, C6533R.C6535id.offline_id_expired_marquee, "field 'sheetMarquee'", SheetMarquee.class);
        View view = Utils.findRequiredView(source, C6533R.C6535id.offline_id_rescan_button, "method 'rescanId'");
        this.view2131755601 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.rescanId();
            }
        });
    }

    public void unbind() {
        AccountVerificationIdExpiredFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.sheetMarquee = null;
        this.view2131755601.setOnClickListener(null);
        this.view2131755601 = null;
    }
}
