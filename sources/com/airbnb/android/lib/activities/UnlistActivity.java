package com.airbnb.android.lib.activities;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import com.airbnb.android.core.activities.SolitAirActivity;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.lib.fragments.unlist.ListingVisibilityFragment;
import com.airbnb.android.lib.fragments.unlist.SelectReasonFragment;
import com.airbnb.android.lib.fragments.unlist.SnoozeModeFragment;
import com.airbnb.android.lib.fragments.unlist.UnlistLawQuestionsFragment;
import com.airbnb.android.lib.fragments.unlist.UnlistNegativeExperienceFragment;
import com.airbnb.android.lib.fragments.unlist.UnlistNoLongerHaveAccessFragment;
import com.airbnb.android.lib.fragments.unlist.UnlistNotEarnEnoughFragment;
import com.airbnb.android.lib.fragments.unlist.UnlistOtherReasonsFragment;
import com.airbnb.android.lib.fragments.unlist.UnlistTooMuchWorkFragment;
import com.airbnb.android.lib.fragments.unlist.UnlistTrustQuestionsFragment;
import icepick.State;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class UnlistActivity extends SolitAirActivity {
    public static final int LAW_QUESTIONS = 7;
    public static final int LISTING_VISIBILITY = 0;
    public static final int NEGATIVE_EXPERIENCE = 9;
    public static final int NOT_EARN_ENOUGH = 6;
    public static final int SELECT_REASON = 1;
    public static final int SNOOZE_MODE = 3;
    public static final int TOO_MUCH_WORK = 5;
    public static final int TRUST_QUESTIONS = 8;
    public static final int UNLIST = 2;
    public static final int UNLIST_OTHER_REASONS = 4;
    @State
    Listing listing;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Reason {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            this.listing = (Listing) getIntent().getParcelableExtra("listing");
            showListingVisibilityFragment();
        }
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return true;
    }

    public void doneWithListingVisibility(int reason) {
        Fragment fragment;
        switch (reason) {
            case 1:
                fragment = SelectReasonFragment.newInstance();
                break;
            case 3:
                fragment = SnoozeModeFragment.newInstance();
                break;
            default:
                throw new IllegalStateException("Invalid state in UnlistActivity:doneWithListingVisibility");
        }
        showFragment(fragment, true);
    }

    public void doneWithSelectReason(int reason) {
        Fragment fragment;
        switch (reason) {
            case 2:
                fragment = UnlistNoLongerHaveAccessFragment.newInstance();
                break;
            case 3:
                fragment = SnoozeModeFragment.newInstance();
                break;
            case 4:
                fragment = UnlistOtherReasonsFragment.newInstance();
                break;
            case 5:
                fragment = UnlistTooMuchWorkFragment.newInstance();
                break;
            case 6:
                fragment = UnlistNotEarnEnoughFragment.newInstance();
                break;
            case 7:
                fragment = UnlistLawQuestionsFragment.newInstance();
                break;
            case 8:
                fragment = UnlistTrustQuestionsFragment.newInstance();
                break;
            case 9:
                fragment = UnlistNegativeExperienceFragment.newInstance();
                break;
            default:
                throw new IllegalStateException("Invalid state in UnlistActivity:doneWithSelectReason");
        }
        showFragment(fragment, true);
    }

    public Listing getListing() {
        return this.listing;
    }

    public void showListingVisibilityFragment() {
        showFragment(ListingVisibilityFragment.newInstance(), true);
    }
}
