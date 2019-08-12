package com.airbnb.android.hostcalendar.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.support.p000v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.android.hostcalendar.views.CalendarDetailReservationRow.CalendarDetailReservationClickListener;
import com.airbnb.android.sharedcalendar.enums.CalendarReservationColor;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;

public class CalendarDetailReservationBlock extends RelativeLayout {
    private final Paint backgroundPaint;
    @BindView
    HaloImageView guestImage;
    @BindView
    TextView guestName;
    @BindView
    TextView guestsNightsDetail;
    @BindView
    TextView messageAction;
    @BindView
    TextView price;
    @BindDimen
    int radius;
    private final RectF rect;
    private final Paint strokePaint;
    @BindDimen
    int strokeWidth;

    public CalendarDetailReservationBlock(Context context) {
        this(context, null);
    }

    public CalendarDetailReservationBlock(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarDetailReservationBlock(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.backgroundPaint = new Paint();
        this.strokePaint = new Paint();
        this.rect = new RectF();
        inflate(getContext(), C6418R.layout.calendar_detail_reservation_block, this);
        ButterKnife.bind((View) this);
        this.backgroundPaint.setAntiAlias(true);
        this.backgroundPaint.setStyle(Style.FILL);
        this.strokePaint.setAntiAlias(true);
        this.strokePaint.setStyle(Style.STROKE);
        this.strokePaint.setStrokeWidth((float) this.strokeWidth);
        this.strokePaint.setColor(-1);
    }

    public void setReservation(Reservation reservation) {
        Check.notNull(reservation.getGuest());
        Context c = getContext();
        Resources r = c.getResources();
        CalendarReservationColor crc = CalendarReservationColor.fromReservation(reservation);
        int fillColor = ContextCompat.getColor(c, crc.getFillColor());
        int nameTextColor = ContextCompat.getColor(c, crc.getNameTextColor());
        int detailTextColor = ContextCompat.getColor(c, crc.getDetailTextColor());
        int actionColor = ContextCompat.getColor(c, crc.getActionTextColor());
        float guestPhotoAlpha = crc.getGuestPhotoAlpha();
        this.backgroundPaint.setColor(fillColor);
        this.guestName.setTextColor(nameTextColor);
        this.guestsNightsDetail.setTextColor(detailTextColor);
        this.price.setTextColor(detailTextColor);
        this.messageAction.setTextColor(actionColor);
        this.guestImage.setAlpha(guestPhotoAlpha);
        this.guestImage.setImageUrl(reservation.getGuest().getPictureUrl());
        this.guestName.setText(reservation.getGuest().getName());
        String guestsString = r.getQuantityString(C6418R.plurals.x_guests, reservation.getGuestCount(), new Object[]{Integer.valueOf(reservation.getGuestCount())});
        String nightsString = r.getQuantityString(C6418R.plurals.x_nights, reservation.getReservedNightsCount(), new Object[]{Integer.valueOf(reservation.getReservedNightsCount())});
        this.guestsNightsDetail.setText(r.getString(C6418R.string.bullet_with_space_parameterized, new Object[]{guestsString, nightsString}));
        this.price.setText(reservation.getHostBasePriceFormatted());
        this.messageAction.setText(C6418R.string.action_message_guest);
    }

    public void setReservationClickListener(CalendarDetailReservationRow parent, CalendarDetailReservationClickListener listener) {
        if (listener != null) {
            this.messageAction.setOnClickListener(CalendarDetailReservationBlock$$Lambda$1.lambdaFactory$(listener, parent));
        } else {
            this.messageAction.setOnClickListener(null);
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        drawRoundedBackground(canvas);
        super.dispatchDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.rect.set(0.0f, 0.0f, (float) w, (float) h);
    }

    private void drawRoundedBackground(Canvas canvas) {
        canvas.drawRoundRect(this.rect, (float) this.radius, (float) this.radius, this.backgroundPaint);
        canvas.drawRoundRect(this.rect, (float) this.radius, (float) this.radius, this.strokePaint);
    }
}
