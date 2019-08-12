package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.CalendarRule;
import com.airbnb.android.core.requests.CalendarRulesRequest;
import com.airbnb.android.core.responses.CalendarRulesResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.listing.adapters.AvailabilitySettingsAdapter;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import p032rx.Observer;

public class ManageListingAvailabilitySettingsFragment extends ManageListingBaseFragment {
    private AvailabilitySettingsAdapter adapter;
    final RequestListener<CalendarRulesResponse> calendarRulesListener = new C0699RL().onResponse(ManageListingAvailabilitySettingsFragment$$Lambda$1.lambdaFactory$(this)).onError(ManageListingAvailabilitySettingsFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirButton saveButton;
    @BindView
    AirToolbar toolbar;

    static /* synthetic */ void lambda$new$0(ManageListingAvailabilitySettingsFragment manageListingAvailabilitySettingsFragment, CalendarRulesResponse response) {
        manageListingAvailabilitySettingsFragment.saveButton.setState(State.Success);
        manageListingAvailabilitySettingsFragment.controller.setCalendarRule(response.calendarRule);
        manageListingAvailabilitySettingsFragment.getFragmentManager().popBackStack();
    }

    static /* synthetic */ void lambda$new$1(ManageListingAvailabilitySettingsFragment manageListingAvailabilitySettingsFragment, AirRequestNetworkException e) {
        manageListingAvailabilitySettingsFragment.adapter.setInputEnabled(true);
        manageListingAvailabilitySettingsFragment.saveButton.setState(State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(manageListingAvailabilitySettingsFragment.getView(), e);
    }

    public static ManageListingAvailabilitySettingsFragment create() {
        return (ManageListingAvailabilitySettingsFragment) FragmentBundler.make(new ManageListingAvailabilitySettingsFragment()).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.adapter = new AvailabilitySettingsAdapter(getContext(), this.controller.getCalendarRule(), this.controller.getListing(), savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_listing_recycler_view_with_save, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.adapter);
        return view;
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return this.adapter.hasChanged(this.controller.getCalendarRule());
    }

    /* access modifiers changed from: protected */
    @OnClick
    public void saveClicked() {
        this.adapter.setInputEnabled(false);
        if (!canSaveChanges()) {
            this.saveButton.setState(State.Success);
            getFragmentManager().popBackStack();
            return;
        }
        this.saveButton.setState(State.Loading);
        CalendarRule settings = this.adapter.getNewAvailabilitySettings();
        CalendarRulesRequest.updateForFields(this.controller.getListing().getId(), settings.getAdvanceNotice().getHours(), settings.getAdvanceNotice().getAllowRequestToBook(), settings.getTurnoverDays().getDays(), settings.getMaxDaysNotice().getDays()).withListener((Observer) this.calendarRulesListener).execute(this.requestManager);
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.adapter.onSaveInstanceState(outState);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingAvailability;
    }
}
