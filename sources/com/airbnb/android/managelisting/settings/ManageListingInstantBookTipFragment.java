package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.p027n2.components.AirToolbar;

public class ManageListingInstantBookTipFragment extends ManageListingBaseFragment {
    @BindView
    AirToolbar toolbar;

    public static ManageListingInstantBookTipFragment create() {
        return (ManageListingInstantBookTipFragment) FragmentBundler.make(new ManageListingInstantBookTipFragment()).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_manage_listing_instant_book_tip, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        return view;
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onHouseRulesClicked() {
        pop();
        this.controller.actionExecutor.houseRules();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onGuestRequirementsClicked() {
        pop();
        this.controller.actionExecutor.guestRequirements();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onTryClicked() {
        pop();
        this.controller.actionExecutor.instantBook();
    }

    private void pop() {
        getActivity().getSupportFragmentManager().popBackStack();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingBookingTip;
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }
}
