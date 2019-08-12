package com.airbnb.android.nestedlistings.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.enums.HelpCenterArticle;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.intents.HelpCenterIntents;
import com.airbnb.android.core.models.NestedListing;
import com.airbnb.android.nestedlistings.C7496R;
import com.airbnb.android.nestedlistings.epoxycontrollers.NestedListingsOverviewEpoxyController;
import com.airbnb.android.nestedlistings.epoxycontrollers.NestedListingsOverviewEpoxyController.NestedListingsOverviewListener;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import icepick.State;
import java.util.HashMap;

public class NestedListingsOverviewFragment extends NestedListingsBaseFragment {
    private static int LOADER_DELAY = 1000;
    private NestedListingsOverviewListener actionListener = new NestedListingsOverviewListener() {
        public void onLearnMore() {
            NestedListingsOverviewFragment.this.getContext().startActivity(HelpCenterIntents.intentForHelpCenterArticle(NestedListingsOverviewFragment.this.getContext(), HelpCenterArticle.NESTED_LISTINGS).toIntent());
        }

        public void onLinkMore() {
            NestedListingsOverviewFragment.this.controller.getActionExecutor().nestedListingsParent();
        }

        public void onEditExistingParent(NestedListing listing) {
            NestedListingsOverviewFragment.this.controller.getActionExecutor().nestedListingsChildren(listing, true);
        }
    };
    @State
    HashMap<Long, NestedListing> currentNestedListings;
    @BindView
    AirButton doneButton;
    private NestedListingsOverviewEpoxyController epoxyController;
    private Handler handler;
    @State
    boolean initialLoadDone = false;
    private HashMap<Long, NestedListing> initialNestedListings;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;
    private Runnable updateAdapterRunnable = NestedListingsOverviewFragment$$Lambda$1.lambdaFactory$(this);

    public static NestedListingsOverviewFragment create() {
        return new NestedListingsOverviewFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initialNestedListings = this.controller.getNestedListingsById();
        this.currentNestedListings = this.controller.getNestedListingsById();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7496R.layout.fragment_recycler_view_with_done, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        updateAdapter(false);
        this.doneButton.setVisibility(8);
        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.epoxyController.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: protected */
    @OnClick
    public void doneClicked() {
        getAirActivity().onBackPressed();
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }

    public void onResume() {
        super.onResume();
        this.handler = new Handler();
        if (this.currentNestedListings != this.controller.getNestedListingsById()) {
            updateAdapter(true);
            this.currentNestedListings = this.controller.getNestedListingsById();
            this.handler.postDelayed(this.updateAdapterRunnable, (long) LOADER_DELAY);
        }
        if (this.currentNestedListings != this.initialNestedListings) {
            this.doneButton.setVisibility(0);
        }
    }

    public void onPause() {
        this.handler.removeCallbacks(this.updateAdapterRunnable);
        super.onPause();
    }

    /* access modifiers changed from: private */
    public void updateAdapter(boolean showLoading) {
        this.epoxyController = new NestedListingsOverviewEpoxyController(getContext(), this.controller.getNestedListingsById(), NestedListing.getParentListings(this.controller.getNestedListingsById().values()), this.actionListener, NestedListing.canLinkMore(this.controller.getNestedListingsById().values()), showLoading);
        this.recyclerView.setAdapter(this.epoxyController.getAdapter());
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.NestedListingsOverview;
    }

    public Strap getNavigationTrackingParams() {
        return super.getNavigationTrackingParams().mo11638kv("user_id", this.mAccountManager.getCurrentUserId());
    }
}
