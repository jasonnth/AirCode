package com.airbnb.android.lib.fragments.unlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.UnlistActivity;
import com.airbnb.android.lib.analytics.LegacyUnlistAnalytics;
import com.airbnb.android.lib.fragments.DeleteListingDialog;
import com.airbnb.android.lib.views.GroupedStatusCell;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ListingVisibilityFragment extends BaseSnoozeListingFragment {
    private static final int DIALOG_REQ_DELETE = 1951;
    private static final int DIALOG_REQ_LIST = 1952;
    @BindView
    GroupedStatusCell listCell;
    @BindView
    GroupedStatusCell snoozeCell;
    @BindView
    GroupedStatusCell unlistCell;

    public static ListingVisibilityFragment newInstance() {
        return new ListingVisibilityFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            LegacyUnlistAnalytics.trackViewUnlist(this.listing);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        boolean z = true;
        View view = inflater.inflate(C0880R.layout.fragment_listing_visibility, container, false);
        bindViews(view);
        initializeSnoozeTooltip();
        boolean isSnoozed = this.listing.isSnoozed();
        boolean isListed = this.listing.hasAvailability();
        this.snoozeCell.setStatusIconVisibility(isSnoozed);
        this.listCell.setStatusIconVisibility(isListed && !isSnoozed);
        GroupedStatusCell groupedStatusCell = this.unlistCell;
        if (isListed || isSnoozed) {
            z = false;
        }
        groupedStatusCell.setStatusIconVisibility(z);
        if (this.listing.isSnoozed()) {
            setFormattedSnoozeCellDates();
        }
        return view;
    }

    private void setFormattedSnoozeCellDates() {
        AirDate startDate = this.listing.getSnoozeMode().getStartDate();
        AirDate endDate = this.listing.getSnoozeMode().getEndDate();
        SimpleDateFormat snoozeDateRangeFormat = new SimpleDateFormat(getResources().getString(C0880R.string.date_name_format));
        this.snoozeCell.setContent(getResources().getString(C0880R.string.snooze_date_range, new Object[]{startDate.formatDate((DateFormat) snoozeDateRangeFormat), endDate.formatDate((DateFormat) snoozeDateRangeFormat)}));
        this.snoozeCell.setContentVisibility(0);
    }

    private void initializeSnoozeTooltip() {
        this.snoozeCell.getTooltip().setOnClickListener(ListingVisibilityFragment$$Lambda$1.lambdaFactory$(this));
    }

    /* access modifiers changed from: protected */
    public int getTitle() {
        return C0880R.string.listing_visibility_title;
    }

    @OnClick
    public void onClickListListing() {
        if (!this.listing.hasAvailability() || this.listing.isSnoozed()) {
            ZenDialog.builder().withTitle(C0880R.string.list).withBodyText(getString(C0880R.string.ml_spaces_reactivate_message, this.listing.getName())).withDualButton(C0880R.string.cancel, 0, C0880R.string.list, (int) DIALOG_REQ_LIST, (Fragment) this).create().show(getFragmentManager(), (String) null);
        }
    }

    @OnClick
    public void onClickSnoozeMode() {
        ((UnlistActivity) getActivity()).doneWithListingVisibility(3);
    }

    @OnClick
    public void onClickUnlistReason() {
        if (this.listing.hasAvailability() || this.listing.isSnoozed()) {
            ((UnlistActivity) getActivity()).doneWithListingVisibility(1);
        }
    }

    @OnClick
    public void onClickDeleteListing() {
        DeleteListingDialog.newInstance(DIALOG_REQ_DELETE, this).show(getFragmentManager(), (String) null);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case DIALOG_REQ_DELETE /*1951*/:
                if (resultCode == -1) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(BaseSnoozeListingFragment.RESULT_EXTRA_DELETED, true);
                    Activity activity = getActivity();
                    activity.setResult(-1, resultIntent);
                    activity.finish();
                    return;
                }
                return;
            case DIALOG_REQ_LIST /*1952*/:
                if (resultCode == -1) {
                    listListing();
                    return;
                }
                return;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                return;
        }
    }
}
