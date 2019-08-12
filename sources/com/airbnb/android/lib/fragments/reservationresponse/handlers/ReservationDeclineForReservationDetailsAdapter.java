package com.airbnb.android.lib.fragments.reservationresponse.handlers;

import android.text.TextUtils;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.ReservationResponseActivity;
import com.airbnb.android.lib.fragments.reservationresponse.ReservationResponseBaseFragment.MessageType;
import com.airbnb.epoxy.EpoxyModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ReservationDeclineForReservationDetailsAdapter extends AirEpoxyAdapter {
    public ReservationDeclineForReservationDetailsAdapter(ReservationResponseActivity activity) {
        super(true);
        SimpleDateFormat format = new SimpleDateFormat(activity.getString(C0880R.string.date_name_format));
        String guestName = activity.getReservation().getGuest().getFirstName();
        this.models.add(new DocumentMarqueeEpoxyModel_().titleText((CharSequence) activity.getString(C0880R.string.ro_response_decline_reservation_title, new Object[]{guestName})).captionText((CharSequence) activity.getString(C0880R.string.ro_response_decline_reservation_subtitle, new Object[]{activity.getReservation().getCheckinDate().formatDate((DateFormat) format), activity.getReservation().getCheckoutDate().formatDate((DateFormat) format)})));
        String publicMessage = activity.getDeclineMessage(MessageType.MessageToGuest);
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{new StandardRowEpoxyModel_().title((CharSequence) activity.getString(C0880R.string.ro_response_decline_reason, new Object[]{guestName})).titleMaxLine(2).subtitle((CharSequence) publicMessage).subTitleMaxLine(1).clickListener(ReservationDeclineForReservationDetailsAdapter$$Lambda$1.lambdaFactory$(activity)).actionText(TextUtils.isEmpty(publicMessage) ? C0880R.string.add : C0880R.string.edit)});
    }
}
