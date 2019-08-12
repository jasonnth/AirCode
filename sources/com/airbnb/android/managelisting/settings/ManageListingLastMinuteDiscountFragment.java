package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.listing.adapters.LastMinuteDiscountsEpoxyController;
import com.airbnb.android.listing.adapters.LastMinuteDiscountsEpoxyController.Listener;
import com.airbnb.android.listing.views.TipView;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.managelisting.ManageListingGraph;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;

public class ManageListingLastMinuteDiscountFragment extends ManageListingBaseFragment {
    private LastMinuteDiscountsEpoxyController epoxyController;
    private final Listener listener = new Listener() {
        public void showLastMinuteDiscountLearnMore() {
            ManageListingLastMinuteDiscountFragment.this.controller.actionExecutor.aboutLastMinuteDiscounts();
        }
    };
    @BindView
    AirRecyclerView recyclerView;
    @BindView
    AirButton saveButton;
    @BindView
    TipView tipView;
    @BindView
    AirToolbar toolbar;

    public static ManageListingLastMinuteDiscountFragment create() {
        return new ManageListingLastMinuteDiscountFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ManageListingGraph) CoreApplication.instance(getContext()).component()).inject(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_listing_recycler_view_with_save, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.epoxyController = new LastMinuteDiscountsEpoxyController(getContext(), this.listener, savedInstanceState);
        this.recyclerView.setEpoxyControllerAndBuildModels(this.epoxyController);
        this.saveButton.setEnabled(false);
        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.epoxyController.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }

    /* access modifiers changed from: protected */
    @OnClick
    public void saveClicked() {
        if (canSaveChanges()) {
            this.saveButton.setState(State.Success);
            getFragmentManager().popBackStack();
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingLastMinuteDiscounts;
    }
}
