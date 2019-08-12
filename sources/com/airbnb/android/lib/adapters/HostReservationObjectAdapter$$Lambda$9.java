package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.tripprovider.TripInformationProvider;

final /* synthetic */ class HostReservationObjectAdapter$$Lambda$9 implements OnClickListener {
    private final HostReservationObjectAdapter arg$1;
    private final TripInformationProvider arg$2;

    private HostReservationObjectAdapter$$Lambda$9(HostReservationObjectAdapter hostReservationObjectAdapter, TripInformationProvider tripInformationProvider) {
        this.arg$1 = hostReservationObjectAdapter;
        this.arg$2 = tripInformationProvider;
    }

    public static OnClickListener lambdaFactory$(HostReservationObjectAdapter hostReservationObjectAdapter, TripInformationProvider tripInformationProvider) {
        return new HostReservationObjectAdapter$$Lambda$9(hostReservationObjectAdapter, tripInformationProvider);
    }

    public void onClick(View view) {
        this.arg$1.listener.goToAddExtraServices(this.arg$2.getListing().getId());
    }
}
