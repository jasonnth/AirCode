package com.airbnb.android.lib.fragments;

import android.view.View;
import android.widget.CheckBox;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.AirWebView;
import com.airbnb.android.lib.C0880R;

public class TOSDialogFragment_ViewBinding implements Unbinder {
    private TOSDialogFragment target;

    public TOSDialogFragment_ViewBinding(TOSDialogFragment target2, View source) {
        this.target = target2;
        target2.mAirWebView = (AirWebView) Utils.findRequiredViewAsType(source, C0880R.C0882id.webview, "field 'mAirWebView'", AirWebView.class);
        target2.mCheckBox = (CheckBox) Utils.findRequiredViewAsType(source, C0880R.C0882id.check_agree, "field 'mCheckBox'", CheckBox.class);
    }

    public void unbind() {
        TOSDialogFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mAirWebView = null;
        target2.mCheckBox = null;
    }
}
