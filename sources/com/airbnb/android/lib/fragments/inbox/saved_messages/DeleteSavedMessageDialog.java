package com.airbnb.android.lib.fragments.inbox.saved_messages;

import android.content.Intent;
import android.support.p000v4.app.Fragment;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.fragments.ZenDialog.ZenBuilder;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.BundleBuilder;

public class DeleteSavedMessageDialog extends ZenDialog {
    public static DeleteSavedMessageDialog newInstance(long messageId, int requestCode, Fragment targetFragment) {
        return (DeleteSavedMessageDialog) new ZenBuilder(new DeleteSavedMessageDialog()).withTitle(C0880R.string.canned_messages_delete_saved_message_title).withBodyText(C0880R.string.canned_messages_delete_saved_message_confirmation).withDualButton(C0880R.string.cancel, 0, C0880R.string.delete, requestCode, targetFragment).withArguments(((BundleBuilder) new BundleBuilder().putLong(SavedMessageConstants.SAVED_MESSAGE_ID, messageId)).toBundle()).create();
    }

    /* access modifiers changed from: protected */
    public void clickRightButton(int requestCodeRight) {
        sendActivityResult(requestCodeRight, -1, new Intent().putExtra(SavedMessageConstants.SAVED_MESSAGE_ID, getArguments().getLong(SavedMessageConstants.SAVED_MESSAGE_ID)));
    }
}
