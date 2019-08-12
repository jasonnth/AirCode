package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.User;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.EmailUtils;

final /* synthetic */ class ReservationObjectAdapter$$Lambda$25 implements OnClickListener {
    private final ReservationObjectAdapter arg$1;
    private final User arg$2;

    private ReservationObjectAdapter$$Lambda$25(ReservationObjectAdapter reservationObjectAdapter, User user) {
        this.arg$1 = reservationObjectAdapter;
        this.arg$2 = user;
    }

    public static OnClickListener lambdaFactory$(ReservationObjectAdapter reservationObjectAdapter, User user) {
        return new ReservationObjectAdapter$$Lambda$25(reservationObjectAdapter, user);
    }

    public void onClick(View view) {
        EmailUtils.send(this.arg$1.context, this.arg$2.getEmailAddress(), this.arg$1.context.getString(C0880R.string.contact_from_airbnb), null);
    }
}
