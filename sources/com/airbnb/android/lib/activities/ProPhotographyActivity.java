package com.airbnb.android.lib.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.activities.SolitAirActivity;
import com.airbnb.android.core.intents.LoginActivityIntents;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.analytics.ProPhotoAnalytics.Origin;
import com.airbnb.android.lib.fragments.ProPhotographyFragment;

public class ProPhotographyActivity extends SolitAirActivity {
    private static final long INVALID_ID = -1;
    private static final String LISTING_ID = "listing_id";
    private static final String LISTING_NAME = "listing_name";
    public static final String ORIGIN_FOR_ANALYTICS = "origin_for_analyltics";
    public static final int REQUEST_CODE_PHOTO_FOR_LISTING = 303;
    private static final int REQUEST_LOGIN = 5432;
    private boolean mProPhotoRequested;

    public enum ProPhotoStatus {
        NOT_AVAILABLE("not_available"),
        AVAILABLE("available"),
        REQUESTED("requested"),
        FINISHED("finished");
        
        public final String value;

        private ProPhotoStatus(String apiName) {
            this.value = apiName;
        }
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, ProPhotographyActivity.class);
        intent.putExtra(ORIGIN_FOR_ANALYTICS, Origin.HOSPITALITY.ordinal());
        return intent;
    }

    public static Intent getIntentForListing(Context context, long listingId, String listingName) {
        Intent intent = new Intent(context, ProPhotographyActivity.class);
        intent.putExtra("listing_id", listingId);
        intent.putExtra("listing_name", listingName);
        intent.putExtra(ORIGIN_FOR_ANALYTICS, Origin.EDIT_LISTING.ordinal());
        return intent;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_LOGIN) {
            if (resultCode == -1) {
                startActivity(getIntent());
            }
            finish();
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        ProPhotographyFragment fragment;
        super.onCreate(savedInstanceState);
        if (this.accountManager.isCurrentUserAuthorized()) {
            setupActionBar(C0880R.string.pro_photo_actionbar_title, new Object[0]);
            this.mProPhotoRequested = false;
            if (savedInstanceState == null) {
                long listingId = getIntent().getLongExtra("listing_id", -1);
                String listingName = getIntent().getStringExtra("listing_name");
                int analyticsOrdinal = getIntent().getIntExtra(ORIGIN_FOR_ANALYTICS, -1);
                if (listingId != -1) {
                    fragment = ProPhotographyFragment.newInstanceForListing(analyticsOrdinal, listingId, listingName);
                } else {
                    fragment = ProPhotographyFragment.newInstance(analyticsOrdinal);
                }
                showFragment(fragment, false);
                return;
            }
            return;
        }
        startActivityForResult(LoginActivityIntents.intentWithToast(this, C0880R.string.invite_friends_signin_toast), REQUEST_LOGIN);
    }

    public void setPhotoRequestedForListing(boolean requested) {
        this.mProPhotoRequested = requested;
    }

    public void finish() {
        setResult(this.mProPhotoRequested ? -1 : 0, null);
        super.finish();
    }
}
