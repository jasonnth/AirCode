package com.airbnb.android.pickwishlist;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

final /* synthetic */ class CreateWishListActivity$$Lambda$9 implements OnEditorActionListener {
    private final CreateWishListActivity arg$1;

    private CreateWishListActivity$$Lambda$9(CreateWishListActivity createWishListActivity) {
        this.arg$1 = createWishListActivity;
    }

    public static OnEditorActionListener lambdaFactory$(CreateWishListActivity createWishListActivity) {
        return new CreateWishListActivity$$Lambda$9(createWishListActivity);
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return CreateWishListActivity.lambda$initNameInput$3(this.arg$1, textView, i, keyEvent);
    }
}
