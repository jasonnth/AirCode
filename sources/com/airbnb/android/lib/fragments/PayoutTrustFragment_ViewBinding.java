package com.airbnb.android.lib.fragments;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class PayoutTrustFragment_ViewBinding implements Unbinder {
    private PayoutTrustFragment target;
    private View view2131756523;
    private View view2131756613;
    private View view2131756615;

    public PayoutTrustFragment_ViewBinding(final PayoutTrustFragment target2, View source) {
        this.target = target2;
        target2.mBirthdayLabel = Utils.findRequiredView(source, C0880R.C0882id.dob_label, "field 'mBirthdayLabel'");
        View view = Utils.findRequiredView(source, C0880R.C0882id.dob_selection, "field 'mBirthdaySelector' and method 'selectBirthday'");
        target2.mBirthdaySelector = view;
        this.view2131756613 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.selectBirthday();
            }
        });
        target2.mBirthdaySelectorText = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.txt_selected_dob, "field 'mBirthdaySelectorText'", TextView.class);
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.address_country_input, "field 'mPayoutCountrySelector' and method 'selectCountry'");
        target2.mPayoutCountrySelector = (TextView) Utils.castView(view2, C0880R.C0882id.address_country_input, "field 'mPayoutCountrySelector'", TextView.class);
        this.view2131756523 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.selectCountry();
            }
        });
        target2.mPayoutStreet = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.address_street_input, "field 'mPayoutStreet'", TextView.class);
        target2.mPayoutApt = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.address_apt_input, "field 'mPayoutApt'", TextView.class);
        target2.mPayoutCity = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.address_city_input, "field 'mPayoutCity'", TextView.class);
        target2.mPayoutState = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.address_state_input, "field 'mPayoutState'", TextView.class);
        target2.mPayoutZip = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.address_zip_input, "field 'mPayoutZip'", TextView.class);
        View view3 = Utils.findRequiredView(source, C0880R.C0882id.payout_start, "method 'onNextButtonClicked'");
        this.view2131756615 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onNextButtonClicked();
            }
        });
    }

    public void unbind() {
        PayoutTrustFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mBirthdayLabel = null;
        target2.mBirthdaySelector = null;
        target2.mBirthdaySelectorText = null;
        target2.mPayoutCountrySelector = null;
        target2.mPayoutStreet = null;
        target2.mPayoutApt = null;
        target2.mPayoutCity = null;
        target2.mPayoutState = null;
        target2.mPayoutZip = null;
        this.view2131756613.setOnClickListener(null);
        this.view2131756613 = null;
        this.view2131756523.setOnClickListener(null);
        this.view2131756523 = null;
        this.view2131756615.setOnClickListener(null);
        this.view2131756615 = null;
    }
}
