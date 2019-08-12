package com.airbnb.android.managelisting.settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.SeasonalMinNightsCalendarSetting;
import com.airbnb.android.core.requests.CalendarRulesRequest;
import com.airbnb.android.core.responses.CalendarRulesResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.managelisting.settings.ManageListingSeasonalCalendarSettingsAdapter.Listener;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.google.common.collect.FluentIterable;
import icepick.State;
import java.util.List;
import p032rx.Observer;

public class ManageListingSeasonalCalendarSettingsFragment extends ManageListingBaseFragment {
    private static final String CALENDAR_SETTING_ARG = "calendar_setting_bundle";
    private ManageListingSeasonalCalendarSettingsAdapter adapter;
    @State
    boolean allowSettingRemoveButton;
    private final Listener listener = new Listener() {
        public void onValidInputChanged(boolean isValid) {
            ManageListingSeasonalCalendarSettingsFragment.this.saveButton.setEnabled(isValid);
        }

        public void onDatesRowSelected(AirDate startDate, AirDate endDate) {
            ManageListingSeasonalCalendarSettingsFragment.this.startDatesPickerActivity(startDate, endDate);
        }
    };
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirButton saveButton;
    @BindView
    AirToolbar toolbar;
    final RequestListener<CalendarRulesResponse> updateCalendarListener = new C0699RL().onResponse(ManageListingSeasonalCalendarSettingsFragment$$Lambda$1.lambdaFactory$(this)).onError(ManageListingSeasonalCalendarSettingsFragment$$Lambda$2.lambdaFactory$(this)).build();

    public static ManageListingSeasonalCalendarSettingsFragment create(SeasonalMinNightsCalendarSetting setting) {
        return (ManageListingSeasonalCalendarSettingsFragment) ((FragmentBundleBuilder) FragmentBundler.make(new ManageListingSeasonalCalendarSettingsFragment()).putParcelable(CALENDAR_SETTING_ARG, setting)).build();
    }

    static /* synthetic */ void lambda$new$0(ManageListingSeasonalCalendarSettingsFragment manageListingSeasonalCalendarSettingsFragment, CalendarRulesResponse response) {
        manageListingSeasonalCalendarSettingsFragment.saveButton.setState(AirButton.State.Success);
        manageListingSeasonalCalendarSettingsFragment.controller.setCalendarRule(response.calendarRule);
        manageListingSeasonalCalendarSettingsFragment.getFragmentManager().popBackStack();
    }

    static /* synthetic */ void lambda$new$1(ManageListingSeasonalCalendarSettingsFragment manageListingSeasonalCalendarSettingsFragment, AirRequestNetworkException e) {
        NetworkUtil.tryShowErrorWithSnackbar(manageListingSeasonalCalendarSettingsFragment.getView(), e);
        manageListingSeasonalCalendarSettingsFragment.saveButton.setState(AirButton.State.Normal);
        manageListingSeasonalCalendarSettingsFragment.adapter.setEnabled(true);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SeasonalMinNightsCalendarSetting calendarSetting = (SeasonalMinNightsCalendarSetting) getArguments().getParcelable(CALENDAR_SETTING_ARG);
        this.adapter = new ManageListingSeasonalCalendarSettingsAdapter(getContext(), calendarSetting, this.listener, savedInstanceState);
        if (savedInstanceState == null) {
            this.allowSettingRemoveButton = this.controller.getCalendarRule().getSeasonalCalendarSettings().contains(calendarSetting);
            if (isBlankSeasonalSetting(calendarSetting)) {
                startDatesPickerActivity(null, null);
            }
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_listing_recycler_view_with_save, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.adapter);
        setHasOptionsMenu(true);
        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.adapter.onSaveInstanceState(outState);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (this.allowSettingRemoveButton) {
            inflater.inflate(C7368R.C7371menu.seasonal_calendar_settings, menu);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C7368R.C7370id.menu_remove_setting) {
            return super.onOptionsItemSelected(item);
        }
        makeUpdateCalendarSettingsRequest(FluentIterable.from((Iterable<E>) this.controller.getCalendarRule().getSeasonalCalendarSettings()).filter(ManageListingSeasonalCalendarSettingsFragment$$Lambda$3.lambdaFactory$((SeasonalMinNightsCalendarSetting) getArguments().getParcelable(CALENDAR_SETTING_ARG))).toList());
        return true;
    }

    static /* synthetic */ boolean lambda$onOptionsItemSelected$2(SeasonalMinNightsCalendarSetting originalSetting, SeasonalMinNightsCalendarSetting setting) {
        return !originalSetting.equals(setting);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 223) {
            switch (resultCode) {
                case -1:
                    this.adapter.setDateRange((AirDate) data.getParcelableExtra(DatesModalActivity.RESULT_START_DATE_ARG), (AirDate) data.getParcelableExtra(DatesModalActivity.RESULT_END_DATE_ARG));
                    return;
                case 0:
                    if (isBlankSeasonalSetting(this.adapter.getSeasonalSetting())) {
                        getFragmentManager().popBackStack();
                        return;
                    }
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return this.adapter.hasChanged((SeasonalMinNightsCalendarSetting) getArguments().getParcelable(CALENDAR_SETTING_ARG));
    }

    /* access modifiers changed from: protected */
    @OnClick
    public void saveClicked() {
        if (!canSaveChanges()) {
            this.saveButton.setState(AirButton.State.Success);
            getFragmentManager().popBackStack();
            return;
        }
        makeUpdateCalendarSettingsRequest(FluentIterable.from((Iterable<E>) this.controller.getCalendarRule().getSeasonalCalendarSettings()).filter(ManageListingSeasonalCalendarSettingsFragment$$Lambda$4.lambdaFactory$((SeasonalMinNightsCalendarSetting) getArguments().getParcelable(CALENDAR_SETTING_ARG))).append((E[]) new SeasonalMinNightsCalendarSetting[]{this.adapter.getSeasonalSetting()}).toList());
    }

    static /* synthetic */ boolean lambda$saveClicked$3(SeasonalMinNightsCalendarSetting originalSetting, SeasonalMinNightsCalendarSetting setting) {
        return !originalSetting.equals(setting);
    }

    private void makeUpdateCalendarSettingsRequest(List<SeasonalMinNightsCalendarSetting> seasonalSettings) {
        this.saveButton.setState(AirButton.State.Loading);
        this.adapter.setEnabled(false);
        CalendarRulesRequest.updateSeasonalCalendarSettings(this.controller.getListing().getId(), seasonalSettings).withListener((Observer) this.updateCalendarListener).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void startDatesPickerActivity(AirDate startDate, AirDate endDate) {
        startActivityForResult(DatesModalActivity.intentForEditDates(getContext(), startDate, endDate, C7368R.string.manage_listing_seasonal_settings_start_date_title, C7368R.string.manage_listing_seasonal_settings_end_date_title, C7368R.string.done, NavigationTag.ManageListingSeasonalDatePicker), DatesModalActivity.RESULT_CODE);
    }

    private static boolean isBlankSeasonalSetting(SeasonalMinNightsCalendarSetting setting) {
        return setting.getStartDate() == null && setting.getEndDate() == null;
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingSeasonalSettings;
    }
}
