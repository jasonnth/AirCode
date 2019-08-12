package com.airbnb.android.identity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

public class AccountVerificationDeviceNotSupportedFragment_ViewBinding implements Unbinder {
    private AccountVerificationDeviceNotSupportedFragment target;
    private View view2131755591;

    public AccountVerificationDeviceNotSupportedFragment_ViewBinding(final AccountVerificationDeviceNotSupportedFragment target2, View source) {
        this.target = target2;
        target2.iconView = (AirImageView) Utils.findRequiredViewAsType(source, C6533R.C6535id.device_not_supported_icon, "field 'iconView'", AirImageView.class);
        target2.titleView = (AirTextView) Utils.findRequiredViewAsType(source, C6533R.C6535id.device_not_supported_title, "field 'titleView'", AirTextView.class);
        target2.bodyView = (AirTextView) Utils.findRequiredViewAsType(source, C6533R.C6535id.device_not_supported_body, "field 'bodyView'", AirTextView.class);
        View view = Utils.findRequiredView(source, C6533R.C6535id.device_not_supported_button, "method 'onConfirmClick'");
        this.view2131755591 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onConfirmClick();
            }
        });
    }

    public void unbind() {
        AccountVerificationDeviceNotSupportedFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.iconView = null;
        target2.titleView = null;
        target2.bodyView = null;
        this.view2131755591.setOnClickListener(null);
        this.view2131755591 = null;
    }
}
