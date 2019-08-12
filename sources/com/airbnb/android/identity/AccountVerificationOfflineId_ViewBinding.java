package com.airbnb.android.identity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.SheetMarquee;

public class AccountVerificationOfflineId_ViewBinding implements Unbinder {
    private AccountVerificationOfflineId target;
    private View view2131755309;
    private View view2131755310;
    private View view2131755311;

    public AccountVerificationOfflineId_ViewBinding(AccountVerificationOfflineId target2) {
        this(target2, target2);
    }

    public AccountVerificationOfflineId_ViewBinding(final AccountVerificationOfflineId target2, View source) {
        this.target = target2;
        target2.offlineIdMarquee = (SheetMarquee) Utils.findRequiredViewAsType(source, C6533R.C6535id.offline_id_marquee, "field 'offlineIdMarquee'", SheetMarquee.class);
        View view = Utils.findRequiredView(source, C6533R.C6535id.scan_id_driver_license, "method 'scanDriversLicense'");
        this.view2131755309 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.scanDriversLicense();
            }
        });
        View view2 = Utils.findRequiredView(source, C6533R.C6535id.scan_id_passport, "method 'scanPassport'");
        this.view2131755310 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.scanPassport();
            }
        });
        View view3 = Utils.findRequiredView(source, C6533R.C6535id.scan_id_us_visa, "method 'scanVisa'");
        this.view2131755311 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.scanVisa();
            }
        });
    }

    public void unbind() {
        AccountVerificationOfflineId target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.offlineIdMarquee = null;
        this.view2131755309.setOnClickListener(null);
        this.view2131755309 = null;
        this.view2131755310.setOnClickListener(null);
        this.view2131755310 = null;
        this.view2131755311.setOnClickListener(null);
        this.view2131755311 = null;
    }
}
