package com.airbnb.android.lib.cancellation;

import com.airbnb.android.core.enums.CancellationReason;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;

public class CancelReservationReasonAdapter extends AirEpoxyAdapter {

    public interface Listener {
        void cancelWithReason(CancellationReason cancellationReason);
    }

    public CancelReservationReasonAdapter(Listener listener) {
        this.models.add(new DocumentMarqueeEpoxyModel_().titleRes(C0880R.string.cancel_reservation).captionRes(C0880R.string.cancel_reason_caption));
        for (CancellationReason reason : CancellationReason.getGuestCancellationReasons()) {
            this.models.add(new StandardRowEpoxyModel_().title(reason.getReasonStr()).titleMaxLine(2).rowDrawableRes(C0880R.C0881drawable.n2_standard_row_right_caret_gray).clickListener(CancelReservationReasonAdapter$$Lambda$1.lambdaFactory$(listener, reason)));
        }
    }
}
