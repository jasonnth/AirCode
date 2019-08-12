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
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.managelisting.analytics.UnlistAnalytics;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import p032rx.Observer;

public class ManageListingSnoozeSettingFragment extends ManageListingBaseFragment {
    private static final String PARAM_FROM_REASONS_PAGE = "param_from_reasons_page";
    public static final int SNOOZE_LISTING_SOURCE_REASONS_PAGE = 1;
    public static final int SNOOZE_LISTING_SOURCE_STATUS_PAGE = 0;
    public static final int SNOOZE_LISTING_SOURCE_TOO_MUCH_WORK_PAGE = 2;
    private ManageListingSnoozeSettingAdapter adapter;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirButton saveButton;
    final RequestListener<SimpleListingResponse> snoozeUpdateListener = new C0699RL().onResponse(ManageListingSnoozeSettingFragment$$Lambda$1.lambdaFactory$(this)).onError(ManageListingSnoozeSettingFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    AirToolbar toolbar;

    @Retention(RetentionPolicy.SOURCE)
    public @interface SnoozeSourcePage {
    }

    public static ManageListingSnoozeSettingFragment create(int sourcePage) {
        return (ManageListingSnoozeSettingFragment) ((FragmentBundleBuilder) FragmentBundler.make(new ManageListingSnoozeSettingFragment()).putInt(PARAM_FROM_REASONS_PAGE, sourcePage)).build();
    }

    static /* synthetic */ void lambda$new$0(ManageListingSnoozeSettingFragment manageListingSnoozeSettingFragment, SimpleListingResponse response) {
        manageListingSnoozeSettingFragment.saveButton.setState(State.Success);
        manageListingSnoozeSettingFragment.controller.setListing(response.listing);
        manageListingSnoozeSettingFragment.controller.actionExecutor.popToFragment(ManageListingStatusSettingFragment.class);
    }

    static /* synthetic */ void lambda$new$1(ManageListingSnoozeSettingFragment manageListingSnoozeSettingFragment, AirRequestNetworkException e) {
        manageListingSnoozeSettingFragment.adapter.setEnabled(true);
        manageListingSnoozeSettingFragment.saveButton.setState(State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(manageListingSnoozeSettingFragment.getView(), e);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.adapter = new ManageListingSnoozeSettingAdapter(this.controller.getListing().getSnoozeMode(), ManageListingSnoozeSettingFragment$$Lambda$3.lambdaFactory$(this), savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_listing_recycler_view_with_save, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.saveButton.setText(C7368R.string.manage_listing_snooze_status_save_button);
        this.recyclerView.setAdapter(this.adapter);
        setHasOptionsMenu(true);
        validateSaveButton();
        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.adapter.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return this.adapter.hasChanged(this.controller.getListing().getSnoozeMode());
    }

    /* access modifiers changed from: protected */
    @OnClick
    public void saveClicked() {
        if (!canSaveChanges()) {
            this.saveButton.setState(State.Success);
            this.controller.actionExecutor.popToFragment(ManageListingStatusSettingFragment.class);
            return;
        }
        this.saveButton.setState(State.Loading);
        this.adapter.setEnabled(false);
        UnlistAnalytics.trackSubmitSnooze(this.controller.getListing(), this.adapter.getStartDate(), this.adapter.getEndDate());
        UpdateListingRequest.forSnoozeMode(getListingId(), this.adapter.getStartDate(), this.adapter.getEndDate()).withListener((Observer) this.snoozeUpdateListener).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void handleDateRangeRowSelected(AirDate startDate, AirDate endDate) {
        startActivityForResult(DatesModalActivity.intentForEditDates(getContext(), startDate, endDate, C7368R.string.manage_listing_seasonal_settings_start_date_title, C7368R.string.manage_listing_seasonal_settings_end_date_title, C7368R.string.done, NavigationTag.ManageListingSeasonalDatePicker), DatesModalActivity.RESULT_CODE);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        int sourcePage = getArguments().getInt(PARAM_FROM_REASONS_PAGE, 0);
        if (this.controller.getListing().isSnoozed() && sourcePage == 0) {
            inflater.inflate(C7368R.C7371menu.listing_snooze_setting, menu);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C7368R.C7370id.menu_remove_setting) {
            return super.onOptionsItemSelected(item);
        }
        this.saveButton.setState(State.Loading);
        this.adapter.setEnabled(false);
        UpdateListingRequest.forField(getListingId(), ListingRequestConstants.JSON_HAS_AVAILABILITY, Boolean.valueOf(true)).withListener((Observer) this.snoozeUpdateListener).execute(this.requestManager);
        return true;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 223 && resultCode == -1) {
            this.adapter.setDateRange((AirDate) data.getParcelableExtra(DatesModalActivity.RESULT_START_DATE_ARG), (AirDate) data.getParcelableExtra(DatesModalActivity.RESULT_END_DATE_ARG));
            validateSaveButton();
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void validateSaveButton() {
        this.saveButton.setEnabled(this.adapter.hasSnoozeDates());
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingSnoozeStatus;
    }
}
