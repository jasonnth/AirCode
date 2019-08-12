package com.airbnb.android.listyourspacedls.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.AdvanceNoticeSetting;
import com.airbnb.android.core.models.CalendarRule;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.MaxDaysNoticeSetting;
import com.airbnb.android.core.models.TurnoverDaysSetting;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.requests.CalendarRulesRequest;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.responses.CalendarRulesResponse;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.ErrorUtils;
import com.airbnb.android.listing.LYSStep;
import com.airbnb.android.listing.utils.SpannableParagraphBuilder;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.android.listyourspacedls.adapters.CombinedAvailabilitySettingsAdapter;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.google.common.collect.Lists;
import java.util.List;

public class LYSAvailabilityFragment extends LYSBaseFragment {
    public final NonResubscribableRequestListener<AirBatchResponse> batchUpdateListener = new C0699RL().onResponse(LYSAvailabilityFragment$$Lambda$1.lambdaFactory$(this)).onError(LYSAvailabilityFragment$$Lambda$2.lambdaFactory$(this)).onComplete(LYSAvailabilityFragment$$Lambda$3.lambdaFactory$(this)).buildWithoutResubscription();
    @BindView
    RecyclerView recyclerView;
    private CombinedAvailabilitySettingsAdapter settingsAdapter;
    @BindView
    AirToolbar toolbar;

    public static LYSAvailabilityFragment newInstance(boolean isStandalone) {
        return (LYSAvailabilityFragment) ((FragmentBundleBuilder) FragmentBundler.make(new LYSAvailabilityFragment()).putBoolean("within_flow", isStandalone)).build();
    }

    static /* synthetic */ void lambda$new$0(LYSAvailabilityFragment lYSAvailabilityFragment, AirBatchResponse batchResponse) {
        Listing listing = ((SimpleListingResponse) batchResponse.singleResponse(SimpleListingResponse.class)).listing;
        CalendarRule calendarRule = ((CalendarRulesResponse) batchResponse.singleResponse(CalendarRulesResponse.class)).calendarRule;
        lYSAvailabilityFragment.controller.setListing((Listing) Check.notNull(listing));
        if (lYSAvailabilityFragment.hasAvailabilitySettingsChanged()) {
            lYSAvailabilityFragment.controller.setShouldReloadCalendar(true);
        }
        lYSAvailabilityFragment.controller.setCalendarRule((CalendarRule) Check.notNull(calendarRule));
        lYSAvailabilityFragment.navigateInFlow(LYSStep.AvailabilityStep);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.controller.getCalendarRule() == null) {
            this.controller.setCalendarRule(getDefaultCalendarRule());
        }
        resetAdapter(savedInstanceState);
    }

    private CalendarRule getDefaultCalendarRule() {
        CalendarRule calendarRule = new CalendarRule();
        calendarRule.setAdvanceNotice(new AdvanceNoticeSetting(24));
        calendarRule.setMaxDaysNotice(MaxDaysNoticeSetting.newInstance(90));
        calendarRule.setTurnoverDays(TurnoverDaysSetting.newInstance(1));
        return calendarRule;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7251R.layout.lys_dls_toolbar_and_recycler_view, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        if (this.comingFromBackstack) {
            resetAdapter(null);
        }
        this.recyclerView.setAdapter(this.settingsAdapter);
        showTip(C7251R.string.lys_dls_availability_tip, LYSAvailabilityFragment$$Lambda$4.lambdaFactory$(this));
        return view;
    }

    private void resetAdapter(Bundle savedInstanceState) {
        this.settingsAdapter = new CombinedAvailabilitySettingsAdapter(getContext(), this.controller.getCalendarRule(), this.controller.getListing(), this.controller.isInstantBook(), this.controller.getCheckInTimeOptions(), savedInstanceState);
    }

    /* access modifiers changed from: protected */
    public void onSaveActionPressed() {
        boolean isValid = this.settingsAdapter.notifyValidSettingsListener();
        if (!canSaveChanges()) {
            navigateInFlow(LYSStep.AvailabilityStep);
        } else if (isValid) {
            updateAvailabilitySettings();
        } else {
            ErrorUtils.showErrorUsingSnackbar(getView(), C7251R.string.lys_dls_availability_change_min_max_nights, C7251R.string.lys_dls_availability_change_min_max_nights_explanation, 0);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.settingsAdapter.onSaveInstanceState(outState);
    }

    @OnClick
    public void clickNext() {
        this.userAction = UserAction.GoToNext;
        onSaveActionPressed();
    }

    /* access modifiers changed from: private */
    public void showTipModal() {
        this.controller.showTipModal(C7251R.string.lys_dls_availability_tip_title, new SpannableParagraphBuilder(getContext()).append(C7251R.string.lys_dls_availability_tip_text_title_1, C7251R.string.lys_dls_availability_tip_text_paragraph_1).append(C7251R.string.lys_dls_availability_tip_text_title_2, C7251R.string.lys_dls_availability_tip_text_paragraph_2).build(), NavigationTag.LYSAvailabilityTip);
    }

    private void updateAvailabilitySettings() {
        setLoading(this.settingsAdapter);
        new AirBatchRequest((List<? extends BaseRequestV2<?>>) Lists.newArrayList((E[]) new BaseRequestV2[]{UpdateListingRequest.forFieldsLYS(this.controller.getListing().getId(), this.settingsAdapter.getUpdateListingSettings()), CalendarRulesRequest.forFields(this.controller.getListing().getId(), this.settingsAdapter.getCalendarRulesUpdateSettings())}), true, this.batchUpdateListener).execute(this.requestManager);
    }

    private boolean hasAvailabilitySettingsChanged() {
        return this.settingsAdapter.hasAvailabilitySettingsChanged(this.controller.getCalendarRule());
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return this.settingsAdapter.hasChanged(this.controller.getListing(), this.controller.getCalendarRule());
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.LYSAvailability;
    }
}
