package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnLongClickListener;

final /* synthetic */ class ThreadAdapter$$Lambda$7 implements OnLongClickListener {
    private static final ThreadAdapter$$Lambda$7 instance = new ThreadAdapter$$Lambda$7();

    private ThreadAdapter$$Lambda$7() {
    }

    public static OnLongClickListener lambdaFactory$() {
        return instance;
    }

    public boolean onLongClick(View view) {
        return view.showContextMenu();
    }
}
