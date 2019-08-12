package com.airbnb.android.lib.paidamenities.fragments.hostservice;

import android.content.Intent;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.fragments.ZenDialog.ZenBuilder;
import com.airbnb.android.lib.C0880R;

public class DeleteAmenityDialog extends ZenDialog {
    public static DeleteAmenityDialog newInstance(int requestCode) {
        return (DeleteAmenityDialog) new ZenBuilder(new DeleteAmenityDialog()).withBodyText(C0880R.string.host_amenities_delete_a_service_confirmation_text).withDualButton(C0880R.string.cancel, 0, C0880R.string.delete, requestCode).create();
    }

    /* access modifiers changed from: protected */
    public void clickRightButton(int requestCodeRight) {
        sendActivityResult(requestCodeRight, -1, new Intent());
    }
}
