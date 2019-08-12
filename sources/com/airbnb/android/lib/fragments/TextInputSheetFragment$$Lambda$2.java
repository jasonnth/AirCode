package com.airbnb.android.lib.fragments;

import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;

final /* synthetic */ class TextInputSheetFragment$$Lambda$2 implements OnMenuItemClickListener {
    private final TextInputSheetFragment arg$1;

    private TextInputSheetFragment$$Lambda$2(TextInputSheetFragment textInputSheetFragment) {
        this.arg$1 = textInputSheetFragment;
    }

    public static OnMenuItemClickListener lambdaFactory$(TextInputSheetFragment textInputSheetFragment) {
        return new TextInputSheetFragment$$Lambda$2(textInputSheetFragment);
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        return TextInputSheetFragment.lambda$onCreateOptionsMenu$1(this.arg$1, menuItem);
    }
}
