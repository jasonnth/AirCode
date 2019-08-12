package com.airbnb.android.thread.fragments;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import com.airbnb.android.core.fragments.AirDialogFragment;
import com.airbnb.android.thread.C1729R;

public class ThreadNotSentFragment extends AirDialogFragment {
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setStyle(0, C1729R.C1734style.ProgressDialog);
        return new Builder(getContext()).setTitle(C1729R.string.message_not_sent_title).setMessage(getString(C1729R.string.message_not_sent_caption)).setPositiveButton(C1729R.string.message_not_sent_ok, ThreadNotSentFragment$$Lambda$1.lambdaFactory$(this)).create();
    }

    static /* synthetic */ void lambda$onCreateDialog$0(ThreadNotSentFragment threadNotSentFragment, DialogInterface dialog, int which) {
        if (threadNotSentFragment.getTargetFragment() != null) {
            threadNotSentFragment.getTargetFragment().onActivityResult(threadNotSentFragment.getTargetRequestCode(), -1, null);
        }
        dialog.dismiss();
    }
}
