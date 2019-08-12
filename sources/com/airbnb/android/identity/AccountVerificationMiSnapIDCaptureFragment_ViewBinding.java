package com.airbnb.android.identity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.ImagePreviewRow;
import com.airbnb.p027n2.components.PrimaryButton;
import com.airbnb.p027n2.components.SheetMarquee;

public class AccountVerificationMiSnapIDCaptureFragment_ViewBinding implements Unbinder {
    private AccountVerificationMiSnapIDCaptureFragment target;

    public AccountVerificationMiSnapIDCaptureFragment_ViewBinding(AccountVerificationMiSnapIDCaptureFragment target2, View source) {
        this.target = target2;
        target2.sheetMarquee = (SheetMarquee) Utils.findRequiredViewAsType(source, C6533R.C6535id.id_intro, "field 'sheetMarquee'", SheetMarquee.class);
        target2.frontRow = (ImagePreviewRow) Utils.findRequiredViewAsType(source, C6533R.C6535id.id_front_row, "field 'frontRow'", ImagePreviewRow.class);
        target2.backRow = (ImagePreviewRow) Utils.findRequiredViewAsType(source, C6533R.C6535id.id_back_row, "field 'backRow'", ImagePreviewRow.class);
        target2.applyButton = (PrimaryButton) Utils.findRequiredViewAsType(source, C6533R.C6535id.submit_license_photos, "field 'applyButton'", PrimaryButton.class);
    }

    public void unbind() {
        AccountVerificationMiSnapIDCaptureFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.sheetMarquee = null;
        target2.frontRow = null;
        target2.backRow = null;
        target2.applyButton = null;
    }
}
