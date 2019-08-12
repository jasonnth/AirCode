package com.airbnb.android.lib.fragments.reservationresponse;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.enums.DeclineReason;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.ReservationResponseActivity;
import com.airbnb.p027n2.components.AirToolbar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ReservationDeclineLandingFragment extends ReservationResponseBaseFragment {
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    private static class DeclineReasonsAdapter extends AirEpoxyAdapter {
        public DeclineReasonsAdapter(ReservationResponseActivity activity) {
            DeclineReason[] values;
            super(true);
            SimpleDateFormat format = new SimpleDateFormat(activity.getString(C0880R.string.date_name_format));
            String checkOut = activity.getReservation().getCheckoutDate().formatDate((DateFormat) format);
            String checkIn = activity.getReservation().getCheckinDate().formatDate((DateFormat) format);
            this.models.add(getMarqueeEpoxyModel(activity));
            for (DeclineReason reason : DeclineReason.values()) {
                this.models.add(new StandardRowEpoxyModel_().title((CharSequence) reason.getTitle(activity, checkIn, checkOut)).titleMaxLine(2).disclosure().clickListener(C6975x6355aba3.lambdaFactory$(activity, reason)));
            }
        }

        private DocumentMarqueeEpoxyModel getMarqueeEpoxyModel(ReservationResponseActivity activity) {
            return new DocumentMarqueeEpoxyModel_().titleText((CharSequence) activity.getString(C0880R.string.ro_response_decline_title, new Object[]{activity.getReservation().getGuest().getFirstName()})).captionText((CharSequence) activity.getString(C0880R.string.ro_response_decline_subtitle));
        }
    }

    public static ReservationDeclineLandingFragment newInstance() {
        return new ReservationDeclineLandingFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_ro_decline_landing, container, false);
        ButterKnife.bind(this, view);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(new DeclineReasonsAdapter(getReservationResponseActivity()));
        this.recyclerView.setHasFixedSize(true);
        return view;
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ReservationRejectionDeclineIntro;
    }
}
