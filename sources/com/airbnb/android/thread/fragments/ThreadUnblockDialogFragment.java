package com.airbnb.android.thread.fragments;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import com.airbnb.android.core.fragments.AirDialogFragment;
import com.airbnb.android.thread.C1729R;
import icepick.State;

public class ThreadUnblockDialogFragment extends AirDialogFragment {
    @State
    String recipientName;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setStyle(0, C1729R.C1734style.ProgressDialog);
        return new Builder(getContext()).setTitle(C1729R.string.message_unblock_title).setMessage(getString(C1729R.string.message_unblock_caption, this.recipientName)).setPositiveButton(C1729R.string.unblock, ThreadUnblockDialogFragment$$Lambda$1.lambdaFactory$(this)).setNegativeButton(C1729R.string.cancel, ThreadUnblockDialogFragment$$Lambda$2.lambdaFactory$(this)).create();
    }

    static /* synthetic */ void lambda$onCreateDialog$0(ThreadUnblockDialogFragment threadUnblockDialogFragment, DialogInterface dialog, int which) {
        if (threadUnblockDialogFragment.getTargetFragment() != null) {
            threadUnblockDialogFragment.getTargetFragment().onActivityResult(threadUnblockDialogFragment.getTargetRequestCode(), -1, null);
        }
        dialog.dismiss();
    }

    static /* synthetic */ void lambda$onCreateDialog$1(ThreadUnblockDialogFragment threadUnblockDialogFragment, DialogInterface dialog, int which) {
        if (threadUnblockDialogFragment.getTargetFragment() != null) {
            threadUnblockDialogFragment.getTargetFragment().onActivityResult(threadUnblockDialogFragment.getTargetRequestCode(), 0, null);
        }
        dialog.dismiss();
    }

    public void setRecipientName(String recipientName2) {
        this.recipientName = recipientName2;
    }
}
