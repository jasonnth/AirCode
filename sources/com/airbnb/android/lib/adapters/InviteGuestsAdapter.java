package com.airbnb.android.lib.adapters;

import com.airbnb.android.core.models.ReservationUser;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.epoxy.EpoxyModel;
import java.util.List;

public class InviteGuestsAdapter extends AirEpoxyAdapter {
    private final InviteGuestsAdapterCallbacks callback;
    private final LinkActionRowEpoxyModel_ linkRowModel;
    private final DocumentMarqueeEpoxyModel_ marqueeEpoxyModel = new DocumentMarqueeEpoxyModel_().titleRes(C0880R.string.share_itinerary_invite_guests).captionRes(C0880R.string.share_itinerary_info_accepted).withNoTopPaddingLayout();

    public interface InviteGuestsAdapterCallbacks {
        void goToInvite();

        void removeGuest(ReservationUser reservationUser);
    }

    public InviteGuestsAdapter(InviteGuestsAdapterCallbacks callback2) {
        this.callback = callback2;
        this.linkRowModel = new LinkActionRowEpoxyModel_().clickListener(InviteGuestsAdapter$$Lambda$1.lambdaFactory$(callback2)).textRes(C0880R.string.share_itinerary_add_guest);
        this.models.add(this.marqueeEpoxyModel);
        this.models.add(this.linkRowModel);
    }

    public void setGuests(List<ReservationUser> users) {
        removeAllAfterModel(this.marqueeEpoxyModel);
        for (ReservationUser user : users) {
            this.models.add(buildRows(user));
        }
        this.models.add(this.linkRowModel);
        notifyDataSetChanged();
    }

    private EpoxyModel buildRows(ReservationUser user) {
        return new StandardRowEpoxyModel_().title((CharSequence) user.getEmail()).rowDrawableRes(C0880R.C0881drawable.ic_action_close).rowDrawableClickListener(InviteGuestsAdapter$$Lambda$2.lambdaFactory$(this, user)).m5602id(user.getId());
    }
}
