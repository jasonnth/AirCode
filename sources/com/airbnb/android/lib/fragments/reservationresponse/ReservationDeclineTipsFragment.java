package com.airbnb.android.lib.fragments.reservationresponse;

import android.content.Context;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.RejectionTip;
import com.airbnb.android.core.requests.constants.CalendarRulesRequestConstants;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.utils.SettingDeepLink;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.managelisting.EditPriceFragment;
import com.airbnb.android.lib.fragments.reservationresponse.ReservationResponseBaseFragment.AnalyticsParams;
import com.airbnb.android.lib.fragments.reservationresponse.ReservationResponseBaseFragment.ReservationResponseNavigator;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import java.util.List;

public class ReservationDeclineTipsFragment extends ReservationResponseBaseFragment {
    @BindView
    View loaderView;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    private static class ReservationSettingsAdapter extends AirEpoxyAdapter {
        public ReservationSettingsAdapter(Context context, ReservationResponseNavigator navigator, List<RejectionTip> rejectionTips) {
            super(true);
            this.models.add(new DocumentMarqueeEpoxyModel_().titleText((CharSequence) context.getString(C0880R.string.ro_response_decline_tips_title)));
            for (RejectionTip tip : rejectionTips) {
                SettingDeepLink settingDeepLink = ReservationDeclineTipsFragment.createDeepLink(tip.getKey());
                if (settingDeepLink != null) {
                    this.models.add(new StandardRowEpoxyModel_().title((CharSequence) tip.getTitle()).titleMaxLine(2).subtitle((CharSequence) tip.getSubtitle()).actionText(C0880R.string.update).clickListener(C6976x64f43486.lambdaFactory$(navigator, settingDeepLink, tip)));
                }
            }
        }
    }

    public static ReservationDeclineTipsFragment newInstance() {
        return new ReservationDeclineTipsFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_ro_decline_landing, container, false);
        ButterKnife.bind(this, view);
        setToolbar(this.toolbar);
        this.recyclerView.setHasFixedSize(true);
        this.toolbar.setNavigationIcon(2);
        this.toolbar.setNavigationOnClickListener(ReservationDeclineTipsFragment$$Lambda$1.lambdaFactory$(this));
        return view;
    }

    public void onResume() {
        super.onResume();
        if (getNavigator().hasPendingRequest()) {
            onUpdateStarted();
        } else {
            refreshTips();
        }
    }

    public void onUpdateStarted() {
        this.loaderView.setVisibility(0);
    }

    public void onUpdateFinished(boolean isSuccess) {
        this.loaderView.setVisibility(8);
        refreshTips();
    }

    public Strap getNavigationTrackingParams() {
        return super.getNavigationTrackingParams().mo11639kv(AnalyticsParams.DeclineReason.key, getReservationResponseActivity().getDeclineReason().serverKey);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ReservationRejectionTips;
    }

    private void refreshTips() {
        this.recyclerView.swapAdapter(new ReservationSettingsAdapter(getReservationResponseActivity(), getReservationResponseActivity().getNavigator(), getReservation().getRejectionTips()), false);
    }

    /* access modifiers changed from: private */
    public static SettingDeepLink createDeepLink(String key) {
        char c = 65535;
        switch (key.hashCode()) {
            case -734181725:
                if (key.equals("min_max_nights")) {
                    c = 2;
                    break;
                }
                break;
            case 41667946:
                if (key.equals(CalendarRulesRequestConstants.BOOKING_LEAD_TIME)) {
                    c = 0;
                    break;
                }
                break;
            case 106934601:
                if (key.equals(EditPriceFragment.RESULT_PRICE)) {
                    c = 6;
                    break;
                }
                break;
            case 307626890:
                if (key.equals("num_of_guests")) {
                    c = 3;
                    break;
                }
                break;
            case 1525543269:
                if (key.equals(CalendarRulesRequestConstants.MAX_DAYS_NOTICE)) {
                    c = 1;
                    break;
                }
                break;
            case 1568595411:
                if (key.equals("check_in_window")) {
                    c = 5;
                    break;
                }
                break;
            case 1570609944:
                if (key.equals(ListingRequestConstants.JSON_HOUSE_RULES_KEY)) {
                    c = 4;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return SettingDeepLink.AdvanceNotice;
            case 1:
                return SettingDeepLink.BookingWindow;
            case 2:
                return SettingDeepLink.TripLength;
            case 3:
                return SettingDeepLink.NumberOfGuests;
            case 4:
                return SettingDeepLink.HouseRules;
            case 5:
                return SettingDeepLink.CheckInWindow;
            case 6:
                return SettingDeepLink.Price;
            default:
                return null;
        }
    }
}
