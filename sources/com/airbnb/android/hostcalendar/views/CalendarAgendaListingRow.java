package com.airbnb.android.hostcalendar.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.android.hostcalendar.views.CalendarAgendaInfoBlock.CalendarAgendaTapListener;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.primitives.AirTextView;

public class CalendarAgendaListingRow extends LinearLayout {
    @BindView
    CalendarAgendaInfoBlock bottomInfoBlock;
    @BindView
    View divider;
    @BindView
    AirTextView listingNameText;
    @BindView
    CalendarAgendaInfoBlock topInfoBlock;

    public CalendarAgendaListingRow(Context context) {
        this(context, null);
    }

    public CalendarAgendaListingRow(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarAgendaListingRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), C6418R.layout.calendar_agenda_listing_row, this);
        ButterKnife.bind((View) this);
        setOrientation(1);
    }

    public void bindData(String listingName, Reservation reservationEnding, Reservation reservationOngoing, Reservation reservationStarting, CalendarAgendaTapListener listener) {
        boolean showTopInfo;
        boolean showBottomInfo;
        boolean z = true;
        this.listingNameText.setText(listingName);
        if (reservationOngoing != null) {
            this.topInfoBlock.bindReservation(C6418R.string.host_calendar_current_guest, reservationOngoing, false, listener);
        } else {
            if (reservationEnding != null) {
                this.topInfoBlock.bindReservation(C6418R.string.host_calendar_checking_out, reservationEnding, true, listener);
            }
            if (reservationStarting != null) {
                this.bottomInfoBlock.bindReservation(C6418R.string.host_calendar_checking_in, reservationStarting, true, listener);
            }
        }
        if (reservationEnding == null && reservationOngoing == null) {
            showTopInfo = false;
        } else {
            showTopInfo = true;
        }
        if (reservationStarting != null) {
            showBottomInfo = true;
        } else {
            showBottomInfo = false;
        }
        ViewUtils.setVisibleIf((View) this.topInfoBlock, showTopInfo);
        ViewUtils.setVisibleIf((View) this.bottomInfoBlock, showBottomInfo);
        View view = this.divider;
        if (!showTopInfo || !showBottomInfo) {
            z = false;
        }
        ViewUtils.setVisibleIf(view, z);
    }
}
