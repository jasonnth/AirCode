package com.airbnb.android.lib.fragments.managelisting.handlers;

import android.content.Context;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.models.IconWithTitles;
import com.airbnb.android.core.models.ReservationCancellationInfo;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.viewcomponents.models.RequirementChecklistRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.managelisting.handlers.ReasonPickerAdapter.ReasonPickerCallback;
import com.airbnb.android.lib.viewcomponents.viewmodels.GuestDetailsSummaryEpoxyModel_;
import com.airbnb.epoxy.EpoxyModel;
import java.util.Iterator;

public class ReservationCancellationGuestEmpathyAdapter extends ReasonPickerAdapter {
    private static final String NUMBER_GUESTS = "number_guests";
    private static final String TRAVEL_DISTANCE = "travel_distance";
    private static final String TRIP_LENGTH = "trip_length";
    private static final String TRIP_PLAN = "trip_plan";
    private static final String TRIP_START = "trip_start";

    public ReservationCancellationGuestEmpathyAdapter(ReasonPickerCallback callback, Context context, User guest, ReservationCancellationInfo reservationCancellationInfo) {
        super(callback, reservationCancellationInfo);
        addTitle((CharSequence) context.getString(C0880R.string.guest_empathy_title, new Object[]{guest.getFirstName()}));
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{new GuestDetailsSummaryEpoxyModel_().guest(guest), new StandardRowEpoxyModel_().title(C0880R.string.guest_empathy_subtitle).titleMaxLine(5)});
        if (reservationCancellationInfo.getHostCancelEmpathiesMobile() != null) {
            Iterator it = reservationCancellationInfo.getHostCancelEmpathiesMobile().iterator();
            while (it.hasNext()) {
                IconWithTitles iconWithTitles = (IconWithTitles) it.next();
                String titleText = iconWithTitles.getTitle() + " " + iconWithTitles.getValue();
                String empathyType = iconWithTitles.getType();
                char c = 65535;
                switch (empathyType.hashCode()) {
                    case -496451520:
                        if (empathyType.equals(TRIP_LENGTH)) {
                            c = 0;
                            break;
                        }
                        break;
                    case -398156029:
                        if (empathyType.equals(TRIP_PLAN)) {
                            c = 4;
                            break;
                        }
                        break;
                    case 51649361:
                        if (empathyType.equals(NUMBER_GUESTS)) {
                            c = 3;
                            break;
                        }
                        break;
                    case 461675386:
                        if (empathyType.equals(TRAVEL_DISTANCE)) {
                            c = 2;
                            break;
                        }
                        break;
                    case 545074120:
                        if (empathyType.equals(TRIP_START)) {
                            c = 1;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        int i = C0880R.C0881drawable.icon_moon;
                        break;
                    case 1:
                        int numDaysTillStart = AirDate.today().getDaysUntil(new AirDate(1000 * Long.parseLong(iconWithTitles.getValue())));
                        titleText = iconWithTitles.getTitle() + " " + context.getResources().getQuantityString(C0880R.plurals.x_days, numDaysTillStart, new Object[]{Integer.valueOf(numDaysTillStart)}).toLowerCase();
                        int i2 = C0880R.C0881drawable.icon_trip_start;
                        break;
                    case 2:
                        int iconRes = C0880R.C0881drawable.icon_car;
                        break;
                    case 3:
                        int iconRes2 = C0880R.C0881drawable.icon_am_familyfriendly;
                        break;
                    case 4:
                        int numPlanningDays = new AirDate(1000 * Long.parseLong(iconWithTitles.getValue())).getDaysUntil(AirDate.today());
                        titleText = iconWithTitles.getTitle() + " " + context.getResources().getQuantityString(C0880R.plurals.x_days, numPlanningDays, new Object[]{Integer.valueOf(numPlanningDays)}).toLowerCase();
                        int i3 = C0880R.C0881drawable.icon_am_twentyfourhourcheckin;
                        break;
                    default:
                        BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Unknown guest empathy item."));
                        break;
                }
                addModel(new RequirementChecklistRowEpoxyModel_().title((CharSequence) titleText).rowDrawableRes(C0880R.C0881drawable.n2_ic_bullet));
            }
        }
        addKeepReservationLink();
    }
}
