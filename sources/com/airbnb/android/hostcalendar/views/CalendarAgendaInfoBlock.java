package com.airbnb.android.hostcalendar.views;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;

public class CalendarAgendaInfoBlock extends RelativeLayout {
    @BindView
    AirTextView additionalDetailsText;
    @BindView
    HaloImageView guestImageView;
    @BindView
    AirTextView guestNameText;
    @BindView
    AirTextView infoTypeText;
    @BindView
    AirTextView messageAction;

    public interface CalendarAgendaTapListener {
        void onMessageClick(long j, long j2);

        void onReservationClick(long j, String str);
    }

    public CalendarAgendaInfoBlock(Context context) {
        this(context, null);
    }

    public CalendarAgendaInfoBlock(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarAgendaInfoBlock(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), C6418R.layout.calendar_agenda_info_block, this);
        ButterKnife.bind((View) this);
    }

    public void bindReservation(int eventType, Reservation reservation, boolean showAdditionalDetails, CalendarAgendaTapListener listener) {
        this.infoTypeText.setText(eventType);
        this.guestNameText.setText(reservation.getGuest().getName());
        Resources r = getResources();
        String guestCount = r.getQuantityString(C6418R.plurals.x_guests, reservation.getGuestCount(), new Object[]{Integer.valueOf(reservation.getGuestCount())});
        String nightCount = r.getQuantityString(C6418R.plurals.x_nights, reservation.getReservedNightsCount(), new Object[]{Integer.valueOf(reservation.getReservedNightsCount())});
        if (showAdditionalDetails) {
            this.additionalDetailsText.setText(r.getString(C6418R.string.bullet_with_space_parameterized, new Object[]{guestCount, nightCount}));
        }
        this.guestImageView.setImageUrl(reservation.getGuest().getThumbnailUrl());
        if (listener != null) {
            long listingId = reservation.getListing().getId();
            setOnClickListener(CalendarAgendaInfoBlock$$Lambda$1.lambdaFactory$(listener, listingId, reservation));
            this.messageAction.setOnClickListener(CalendarAgendaInfoBlock$$Lambda$2.lambdaFactory$(listener, listingId, reservation));
            return;
        }
        setOnClickListener(null);
        this.messageAction.setOnClickListener(null);
    }
}
