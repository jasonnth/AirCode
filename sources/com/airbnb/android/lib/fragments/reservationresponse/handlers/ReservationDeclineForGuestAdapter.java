package com.airbnb.android.lib.fragments.reservationresponse.handlers;

import android.text.TextUtils;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.ReservationResponseActivity;
import com.airbnb.android.lib.fragments.reservationresponse.ReservationResponseBaseFragment.MessageType;
import com.airbnb.epoxy.EpoxyModel;

public class ReservationDeclineForGuestAdapter extends AirEpoxyAdapter {
    public ReservationDeclineForGuestAdapter(ReservationResponseActivity activity) {
        super(true);
        String name = activity.getReservation().getGuest().getFirstName();
        this.models.add(new DocumentMarqueeEpoxyModel_().titleText((CharSequence) activity.getString(C0880R.string.ro_response_decline_guest_public_reason_subtitle, new Object[]{name})).captionText((CharSequence) activity.getString(C0880R.string.ro_response_decline_reservation_review)));
        String privateMessage = activity.getDeclineMessage(MessageType.MessageToAirbnb);
        StandardRowEpoxyModel_ privateDetails = new StandardRowEpoxyModel_().title((CharSequence) activity.getString(C0880R.string.ro_response_decline_guest_title)).subtitle((CharSequence) activity.getString(C0880R.string.ro_response_decline_guest_private_reason_subtitle)).titleMaxLine(2).subtitle((CharSequence) privateMessage).subTitleMaxLine(1).clickListener(ReservationDeclineForGuestAdapter$$Lambda$1.lambdaFactory$(activity)).actionText(TextUtils.isEmpty(privateMessage) ? C0880R.string.add : C0880R.string.edit);
        String publicMessage = activity.getDeclineMessage(MessageType.MessageToGuest);
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{new StandardRowEpoxyModel_().title((CharSequence) activity.getString(C0880R.string.ro_response_decline_reason, new Object[]{name})).titleMaxLine(2).subtitle((CharSequence) publicMessage).subTitleMaxLine(1).clickListener(ReservationDeclineForGuestAdapter$$Lambda$2.lambdaFactory$(activity)).actionText(TextUtils.isEmpty(publicMessage) ? C0880R.string.add : C0880R.string.edit), privateDetails});
    }
}
