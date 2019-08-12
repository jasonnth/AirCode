package com.airbnb.android.pickwishlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.intents.LoginActivityIntents;
import com.airbnb.android.core.wishlists.WishListableData;
import com.airbnb.android.utils.KeyboardUtils;

public class PickWishListActivity extends AirActivity {
    private static final String FRAGMENT_TAG = "picker_fragment_tag";
    private static final int REQUEST_CODE_SIGN_IN_ADD_TO_WISHLIST = 13350;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);
        setContentView(C7614R.layout.activity_pick_wishlist_activity);
        ButterKnife.bind((Activity) this);
        if (savedInstanceState == null && !isLoggedIn()) {
            startActivityForResult(LoginActivityIntents.intentWithToast(this, C7614R.string.toast_msg_log_in_to_use_wishlist), REQUEST_CODE_SIGN_IN_ADD_TO_WISHLIST);
        }
    }

    /* access modifiers changed from: protected */
    public void onResumeFragments() {
        super.onResumeFragments();
        loadFragmentIfNeeded();
    }

    private void loadFragmentIfNeeded() {
        if (!hasLoadedFragment() && isLoggedIn()) {
            getSupportFragmentManager().beginTransaction().replace(C7614R.C7616id.fragment_container_new, PickWishListFragment.instance((WishListableData) getIntent().getParcelableExtra("key_wishlistable_data")), FRAGMENT_TAG).commit();
        }
    }

    private boolean hasLoadedFragment() {
        return getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG) != null;
    }

    private boolean isLoggedIn() {
        return this.accountManager.isCurrentUserAuthorized();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onScrimClicked() {
        KeyboardUtils.dismissSoftKeyboard((Activity) this);
        finish();
    }

    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    /* access modifiers changed from: protected */
    public boolean hasCustomEnterTransition() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_SIGN_IN_ADD_TO_WISHLIST /*13350*/:
                if (resultCode != -1 || !isLoggedIn()) {
                    finish();
                    return;
                }
                return;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                return;
        }
    }
}
