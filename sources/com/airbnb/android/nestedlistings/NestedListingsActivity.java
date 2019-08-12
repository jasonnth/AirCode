package com.airbnb.android.nestedlistings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.p000v4.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.intents.NestedListingsIntents;
import com.airbnb.android.core.models.NestedListing;
import com.airbnb.android.core.requests.NestedListingsRequest;
import com.airbnb.android.core.responses.NestedListingsResponse;
import com.airbnb.android.core.utils.NavigationUtils;
import com.airbnb.android.nestedlistings.fragments.NestedListingsChooseChildrenFragment;
import com.airbnb.android.nestedlistings.fragments.NestedListingsChooseParentFragment;
import com.airbnb.android.nestedlistings.fragments.NestedListingsOverviewFragment;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.p027n2.components.LoadingView;
import icepick.State;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class NestedListingsActivity extends AirActivity implements NestedListingsActionExecutor, NestedListingsController {
    @BindView
    LoadingView fullLoader;
    final RequestListener<NestedListingsResponse> nestedListingListener = new C0699RL().onResponse(NestedListingsActivity$$Lambda$1.lambdaFactory$(this)).onError(NestedListingsActivity$$Lambda$2.lambdaFactory$(this)).build();
    @State
    HashMap<Long, NestedListing> nestedListingsById;

    public void nestedListingsParent() {
        showModal(NestedListingsChooseParentFragment.create());
    }

    public void nestedListingsChildren(NestedListing listing, boolean fromOverview) {
        if (fromOverview) {
            showModal(NestedListingsChooseChildrenFragment.create(listing, fromOverview));
        } else {
            showFragmentWithinModal(NestedListingsChooseChildrenFragment.create(listing, fromOverview));
        }
    }

    public void setNestedListingsById(HashMap<Long, NestedListing> nestedListingsById2) {
        this.nestedListingsById = nestedListingsById2;
    }

    public HashMap<Long, NestedListing> getNestedListingsById() {
        return this.nestedListingsById;
    }

    public ArrayList<NestedListing> getNestedListings() {
        return new ArrayList<>(this.nestedListingsById.values());
    }

    public NestedListingsActionExecutor getActionExecutor() {
        return this;
    }

    public void popToFragment(Class<? extends Fragment> clazz) {
        NavigationUtils.popBackStackToFragment(getSupportFragmentManager(), clazz.getCanonicalName());
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return true;
    }

    static /* synthetic */ void lambda$new$0(NestedListingsActivity nestedListingsActivity, NestedListingsResponse response) {
        nestedListingsActivity.setNestedListingsById(response.getNestedListingsById());
        nestedListingsActivity.fullLoader.setVisibility(8);
        nestedListingsActivity.showFragment(NestedListingsOverviewFragment.create());
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        IcepickWrapper.restoreInstanceState(this, savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(C7496R.layout.activity_simple_fragment);
        ButterKnife.bind((Activity) this);
        if (savedInstanceState == null) {
            ArrayList<NestedListing> nestedListings = getIntent().getParcelableArrayListExtra(NestedListingsIntents.INTENT_NESTED_LISTINGS);
            if (!ListUtils.isEmpty((Collection<?>) nestedListings)) {
                this.nestedListingsById = NestedListing.listToHashById(nestedListings);
                showFragment(NestedListingsOverviewFragment.create());
                return;
            }
            this.fullLoader.setVisibility(0);
            NestedListingsRequest.forCurrentUser().skipCache().withListener(this.nestedListingListener).execute(this.requestManager);
        }
    }

    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        IcepickWrapper.saveInstanceState(this, outState);
    }

    private void showFragment(Fragment fragment) {
        showFragment(fragment, C7496R.C7498id.content_container, FragmentTransitionType.SlideInFromSide, true, fragment.getClass().getCanonicalName());
    }

    private void showModal(Fragment fragment) {
        showModal(fragment, C7496R.C7498id.content_container, C7496R.C7498id.modal_container, true, fragment.getClass().getCanonicalName());
    }

    private void showFragmentWithinModal(Fragment fragment) {
        showFragment(fragment, C7496R.C7498id.modal_container, FragmentTransitionType.SlideInFromSide, true, fragment.getClass().getCanonicalName());
    }

    private Intent getIntentData() {
        Intent data = new Intent();
        data.putParcelableArrayListExtra(NestedListingsIntents.INTENT_NESTED_LISTINGS, getNestedListings());
        return data;
    }

    public void finish() {
        setResult(-1, getIntentData());
        super.finish();
    }
}
