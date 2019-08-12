package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnLongClickListener;

final /* synthetic */ class ThreadAdapter$$Lambda$12 implements OnLongClickListener {
    private static final ThreadAdapter$$Lambda$12 instance = new ThreadAdapter$$Lambda$12();

    private ThreadAdapter$$Lambda$12() {
    }

    public static OnLongClickListener lambdaFactory$() {
        return instance;
    }

    public boolean onLongClick(View view) {
        return view.showContextMenu();
    }
}
