package com.airbnb.android.lib.fragments;

import android.content.Context;
import android.content.Intent;
import android.support.p000v4.app.Fragment;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.fragments.ZenDialog.ZenBuilder;
import com.airbnb.android.core.models.Thread;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.BundleBuilder;

public class ArchiveThreadDialog extends ZenDialog {
    public static final String KEY_THREAD = "message_thread";

    public static ArchiveThreadDialog newInstance(Thread thread, boolean archive, int requestCode, Fragment targetFragment) {
        return (ArchiveThreadDialog) new ZenBuilder(new ArchiveThreadDialog()).withTitle(archive ? C0880R.string.archive_thread_title : C0880R.string.unarchive_thread_title).withBodyText(getBodyText(targetFragment.getContext(), thread, archive)).withDualButton(C0880R.string.cancel, 0, archive ? C0880R.string.archive : C0880R.string.unarchive, requestCode, targetFragment).withArguments(((BundleBuilder) new BundleBuilder().putParcelable("message_thread", thread)).toBundle()).create();
    }

    private static String getBodyText(Context context, Thread thread, boolean archive) {
        return context.getString(archive ? C0880R.string.archive_thread_message : C0880R.string.unarchive_thread_message, new Object[]{thread.getOtherUser().getName()});
    }

    /* access modifiers changed from: protected */
    public void clickRightButton(int requestCodeRight) {
        sendActivityResult(requestCodeRight, -1, new Intent().putExtra("message_thread", getArguments().getParcelable("message_thread")));
    }
}
