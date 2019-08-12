package com.airbnb.android.lib.fragments;

import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.p027n2.primitives.messaging.MessageItem;

final /* synthetic */ class ThreadFragment$$Lambda$23 implements OnMenuItemClickListener {
    private final ThreadFragment arg$1;
    private final View arg$2;

    private ThreadFragment$$Lambda$23(ThreadFragment threadFragment, View view) {
        this.arg$1 = threadFragment;
        this.arg$2 = view;
    }

    public static OnMenuItemClickListener lambdaFactory$(ThreadFragment threadFragment, View view) {
        return new ThreadFragment$$Lambda$23(threadFragment, view);
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        return MiscUtils.copyToClipboard(this.arg$1.getContext(), ((MessageItem) this.arg$2).getMessageText().toString());
    }
}
