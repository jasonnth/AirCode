package com.airbnb.android.lib.fragments;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.views.LinearListView;

public class AccountVerificationPhonePickerFragment_ViewBinding implements Unbinder {
    private AccountVerificationPhonePickerFragment target;

    public AccountVerificationPhonePickerFragment_ViewBinding(AccountVerificationPhonePickerFragment target2, View source) {
        this.target = target2;
        target2.mTitleText = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.txt_title, "field 'mTitleText'", TextView.class);
        target2.mPhonePickerList = (LinearListView) Utils.findRequiredViewAsType(source, C0880R.C0882id.phone_list, "field 'mPhonePickerList'", LinearListView.class);
        target2.mPhoneSMSOptions = Utils.findRequiredView(source, C0880R.C0882id.section_phone_sms, "field 'mPhoneSMSOptions'");
        target2.mSendSMSButton = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.btn_sms, "field 'mSendSMSButton'", TextView.class);
        target2.mSendPhoneCallButton = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.btn_phone_call, "field 'mSendPhoneCallButton'", TextView.class);
    }

    public void unbind() {
        AccountVerificationPhonePickerFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mTitleText = null;
        target2.mPhonePickerList = null;
        target2.mPhoneSMSOptions = null;
        target2.mSendSMSButton = null;
        target2.mSendPhoneCallButton = null;
    }
}
