package com.airbnb.android.listyourspacedls.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.GuestControls;
import com.airbnb.android.core.requests.GuestControlsRequest;
import com.airbnb.android.core.responses.GuestControlsResponse;
import com.airbnb.android.listing.LYSStep;
import com.airbnb.android.listing.adapters.HouseRulesSettingsAdapter;
import com.airbnb.android.listing.adapters.HouseRulesSettingsAdapter.Listener;
import com.airbnb.android.listing.utils.TextSetting;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.android.listyourspacedls.LYSDataController.UpdateListener;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import p032rx.Observer;

public class LYSHouseRulesFragment extends LYSBaseFragment implements UpdateListener {
    private HouseRulesSettingsAdapter adapter;
    private final Listener adapterListener = new Listener() {
        public void onAdditionalHouseRulesClicked() {
            LYSHouseRulesFragment.this.controller.showTextSettingModal(TextSetting.LYSAdditionalRules);
        }

        public void onGuestExpectationsClicked() {
            LYSHouseRulesFragment.this.controller.showGuestExpectationsFragment();
        }

        public void onLearnMoreClicked(View v) {
            LYSHouseRulesFragment.this.controller.showHouseRulesLegalInfoModal();
        }
    };
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;
    final RequestListener<GuestControlsResponse> updateGuestControlsListener = new C0699RL().onResponse(LYSHouseRulesFragment$$Lambda$1.lambdaFactory$(this)).onError(LYSHouseRulesFragment$$Lambda$2.lambdaFactory$(this)).onComplete(LYSHouseRulesFragment$$Lambda$3.lambdaFactory$(this)).build();

    public static LYSHouseRulesFragment newInstance(boolean isStandalone) {
        return (LYSHouseRulesFragment) ((FragmentBundleBuilder) FragmentBundler.make(new LYSHouseRulesFragment()).putBoolean("within_flow", isStandalone)).build();
    }

    static /* synthetic */ void lambda$new$0(LYSHouseRulesFragment lYSHouseRulesFragment, GuestControlsResponse response) {
        lYSHouseRulesFragment.controller.setGuestControls(response.guestControls);
        lYSHouseRulesFragment.navigateInFlow(LYSStep.HouseRules);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.controller.getGuestControls() == null) {
            this.controller.setGuestControls(new GuestControls());
        }
        this.adapter = new HouseRulesSettingsAdapter(getContext(), this.controller.getListing(), this.controller.getGuestControls(), this.adapterListener, savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7251R.layout.lys_dls_toolbar_and_recycler_view, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        if (this.comingFromBackstack) {
            this.adapter = new HouseRulesSettingsAdapter(getContext(), this.controller.getListing(), this.controller.getGuestControls(), this.adapterListener, null);
        }
        this.recyclerView.setAdapter(this.adapter);
        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.adapter.onSaveInstanceState(outState);
    }

    @OnClick
    public void clickNext() {
        this.userAction = UserAction.GoToNext;
        onSaveActionPressed();
    }

    private void updateHouseRules() {
        setLoading(this.adapter);
        GuestControlsRequest.updateGuestControls(this.controller.getListing().getId(), this.adapter.getGuestControls()).withListener((Observer) this.updateGuestControlsListener).execute(this.requestManager);
    }

    public void dataUpdated() {
        this.adapter.updateAdditionalRulesAndExpectationsRows(this.controller.getListing());
    }

    /* access modifiers changed from: protected */
    public void onSaveActionPressed() {
        if (!canSaveChanges()) {
            navigateInFlow(LYSStep.HouseRules);
        } else {
            updateHouseRules();
        }
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return this.adapter.hasChanged(this.controller.getGuestControls());
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.LYSHouseRules;
    }
}
