package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.CalendarRule;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.SeasonalMinNightsCalendarSetting;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.requests.CalendarRulesRequest;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.responses.BatchOperation;
import com.airbnb.android.core.responses.CalendarRulesResponse;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.managelisting.settings.ManageListingTripLengthAdapter.Listener;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import com.google.common.collect.Lists;
import java.util.List;

public class ManageListingTripLengthFragment extends ManageListingBaseFragment {
    private ManageListingTripLengthAdapter adapter;
    public final NonResubscribableRequestListener<AirBatchResponse> batchUpdateListener = new C0699RL().onResponse(ManageListingTripLengthFragment$$Lambda$1.lambdaFactory$(this)).onError(ManageListingTripLengthFragment$$Lambda$2.lambdaFactory$(this)).buildWithoutResubscription();
    private boolean firstInstantiation;
    private final Listener listener = new Listener() {
        public void settingsAreValid(boolean valid) {
            if (ManageListingTripLengthFragment.this.saveButton != null) {
                ManageListingTripLengthFragment.this.saveButton.setEnabled(valid);
            }
        }

        public void onModifySeasonalRequirement(SeasonalMinNightsCalendarSetting setting) {
            ManageListingTripLengthFragment.this.controller.actionExecutor.tripLengthSeasonalRequirement(setting);
        }
    };
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirButton saveButton;
    @BindView
    AirToolbar toolbar;

    public static ManageListingTripLengthFragment create() {
        return new ManageListingTripLengthFragment();
    }

    static /* synthetic */ void lambda$new$0(ManageListingTripLengthFragment manageListingTripLengthFragment, AirRequestNetworkException e) {
        manageListingTripLengthFragment.adapter.setEnabled(true);
        manageListingTripLengthFragment.saveButton.setState(State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(manageListingTripLengthFragment.getView(), e);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.firstInstantiation = savedInstanceState == null;
        this.adapter = new ManageListingTripLengthAdapter(getContext(), this.controller.getListing(), this.controller.getCalendarRule(), this.listener, savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_listing_recycler_view_with_save, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.adapter);
        this.adapter.notifyValidSettingsListener();
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int previousSeasonalSettingsCount = this.adapter.getSeasonalRequirementsCount();
        this.adapter.setSeasonalRequirements(this.controller.getCalendarRule().getSeasonalCalendarSettings());
        if (!this.firstInstantiation && this.adapter.getSeasonalRequirementsCount() > previousSeasonalSettingsCount) {
            scrollToSeasonalRequirementsSection();
        }
        this.firstInstantiation = false;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.adapter.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return this.adapter.hasChanged(this.controller.getListing(), this.controller.getCalendarRule()) && this.saveButton.isEnabled();
    }

    /* access modifiers changed from: protected */
    @OnClick
    public void saveClicked() {
        this.adapter.setEnabled(false);
        if (!canSaveChanges()) {
            this.saveButton.setState(State.Success);
            getFragmentManager().popBackStack();
            return;
        }
        this.saveButton.setState(State.Loading);
        new AirBatchRequest((List<? extends BaseRequestV2<?>>) Lists.newArrayList((E[]) new BaseRequestV2[]{UpdateListingRequest.forMinMaxNights(this.controller.getListing().getId(), Integer.valueOf(this.adapter.getMinNights()), Integer.valueOf(this.adapter.getMaxNights())), CalendarRulesRequest.updateWeekendMinNights(this.controller.getListing().getId(), this.adapter.getWeekendMinNights())}), true, this.batchUpdateListener).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void handleBatchResponse(AirBatchResponse batchResponse) {
        this.saveButton.setState(State.Success);
        Listing listing = ((SimpleListingResponse) ((BatchOperation) batchResponse.operations.get(0)).convertedResponse).listing;
        CalendarRule calendarRule = ((CalendarRulesResponse) ((BatchOperation) batchResponse.operations.get(1)).convertedResponse).calendarRule;
        Check.notNull(listing);
        Check.notNull(calendarRule);
        this.controller.setData(listing, calendarRule);
        getFragmentManager().popBackStack();
    }

    private void scrollToSeasonalRequirementsSection() {
        this.recyclerView.post(ManageListingTripLengthFragment$$Lambda$3.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$scrollToSeasonalRequirementsSection$1(ManageListingTripLengthFragment manageListingTripLengthFragment) {
        if (manageListingTripLengthFragment.isVisible()) {
            ((LinearLayoutManager) manageListingTripLengthFragment.recyclerView.getLayoutManager()).scrollToPositionWithOffset(manageListingTripLengthFragment.adapter.getSeasonalSettingSectionPosition(), ViewUtils.getActionBarHeight(manageListingTripLengthFragment.getContext()));
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingTripLength;
    }
}
