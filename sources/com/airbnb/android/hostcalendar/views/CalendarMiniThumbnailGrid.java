package com.airbnb.android.hostcalendar.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.support.p000v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import butterknife.BindColor;
import butterknife.BindDimen;
import butterknife.ButterKnife;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.airdate.DayOfWeek;
import com.airbnb.android.airmapview.AirMapInterface;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.sharedcalendar.enums.CalendarReservationColor;
import java.util.ArrayList;
import java.util.Iterator;

public class CalendarMiniThumbnailGrid extends View {
    @BindColor
    int backgroundGray;
    private int bigRadius;
    private int cellSize;
    private final ArrayList<CalendarThumbnailCircle> circles;
    @BindDimen
    int dotSize;
    private final DayOfWeek firstDayOfWeek;
    private int halfCellSize;
    private int numberOfRows;
    private final Paint paint;
    private final Rect rect;
    private final ArrayList<CalendarReservationRect> rects;
    private int smallRadius;
    @BindDimen
    int strokeWidth;

    private static class CalendarReservationRect {
        final int colorInt;
        final int column;
        final boolean isHalf;
        final boolean isOffset;
        final int row;

        public CalendarReservationRect(int row2, int column2, boolean isHalf2, boolean isOffset2, int colorInt2) {
            this.row = row2;
            this.column = column2;
            this.isHalf = isHalf2;
            this.isOffset = isOffset2;
            this.colorInt = colorInt2;
        }
    }

    private static class CalendarThumbnailCircle {
        final boolean big;
        final int colorInt;
        final int column;
        final boolean isGray;
        final boolean isStroke;
        final int row;

        static CalendarThumbnailCircle big(int row2, int column2, int colorInt2) {
            return new CalendarThumbnailCircle(row2, column2, true, colorInt2, false, false);
        }

        static CalendarThumbnailCircle today(int row2, int column2) {
            return new CalendarThumbnailCircle(row2, column2, true, AirMapInterface.CIRCLE_BORDER_COLOR, true, false);
        }

        static CalendarThumbnailCircle day(int row2, int column2) {
            return new CalendarThumbnailCircle(row2, column2, false, 0, false, true);
        }

        public CalendarThumbnailCircle(int row2, int column2, boolean big2, int colorInt2, boolean isStroke2, boolean isGray2) {
            this.row = row2;
            this.column = column2;
            this.big = big2;
            this.colorInt = colorInt2;
            this.isStroke = isStroke2;
            this.isGray = isGray2;
        }
    }

    public CalendarMiniThumbnailGrid(Context context) {
        this(context, null);
    }

    public CalendarMiniThumbnailGrid(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarMiniThumbnailGrid(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.firstDayOfWeek = AirDate.getDayOfWeekFromCalendar();
        this.paint = new Paint();
        this.rect = new Rect();
        this.circles = new ArrayList<>();
        this.rects = new ArrayList<>();
        init();
    }

    private void init() {
        ButterKnife.bind((View) this);
        this.paint.setAntiAlias(true);
        this.paint.setStrokeWidth((float) this.strokeWidth);
    }

    public void bind(Listing listing, AirDate startDate, AirDate today, ArrayList<Reservation> reservations) {
        this.circles.clear();
        this.rects.clear();
        AirDate date = startDate;
        AirDate nextMonth = date.plusMonths(1);
        int rowPosition = 0;
        while (date.isBefore(nextMonth)) {
            int columnPosition = date.getDaysFromDayOfWeek(this.firstDayOfWeek);
            addShapesForDay(today, date, rowPosition, columnPosition, findReservationForDay(date, reservations));
            this.numberOfRows = rowPosition + 1;
            if (columnPosition == 6) {
                rowPosition++;
            }
            date = date.plusDays(1);
        }
        postInvalidate();
    }

    private Reservation findReservationForDay(AirDate date, ArrayList<Reservation> reservations) {
        if (reservations == null) {
            return null;
        }
        Iterator it = reservations.iterator();
        while (it.hasNext()) {
            Reservation reservation = (Reservation) it.next();
            if (date.isBetweenInclusive(reservation.getCheckinDate(), reservation.getCheckoutDate())) {
                return reservation;
            }
        }
        return null;
    }

    private void addShapesForDay(AirDate today, AirDate date, int rowPosition, int columnPosition, Reservation reservation) {
        if (reservation == null || reservation.getCheckoutDate().isSameDay(date)) {
            addDayMarker(rowPosition, columnPosition);
        } else {
            int colorInt = ContextCompat.getColor(getContext(), CalendarReservationColor.fromReservation(reservation).getFillColor());
            if (reservation.getCheckinDate().isSameDay(date)) {
                if (reservation.getReservedNightsCount() == 1) {
                    addSingleDayReservation(rowPosition, columnPosition, colorInt);
                } else {
                    addReservationHead(rowPosition, columnPosition, colorInt);
                }
            } else if (reservation.getCheckoutDate().plusDays(-1).isSameDay(date)) {
                addReservationButt(rowPosition, columnPosition, colorInt);
            } else {
                addReservationBody(rowPosition, columnPosition, colorInt);
            }
        }
        if (date.isSameDay(today)) {
            this.circles.add(CalendarThumbnailCircle.today(rowPosition, columnPosition));
        }
    }

    private void addSingleDayReservation(int rowPosition, int columnPosition, int colorInt) {
        this.circles.add(CalendarThumbnailCircle.big(rowPosition, columnPosition, colorInt));
    }

    private void addReservationHead(int rowPosition, int columnPosition, int colorInt) {
        this.circles.add(CalendarThumbnailCircle.big(rowPosition, columnPosition, colorInt));
        this.rects.add(new CalendarReservationRect(rowPosition, columnPosition, true, true, colorInt));
    }

    private void addReservationButt(int rowPosition, int columnPosition, int colorInt) {
        this.circles.add(CalendarThumbnailCircle.big(rowPosition, columnPosition, colorInt));
        this.rects.add(new CalendarReservationRect(rowPosition, columnPosition, true, false, colorInt));
    }

    private void addReservationBody(int rowPosition, int columnPosition, int colorInt) {
        this.rects.add(new CalendarReservationRect(rowPosition, columnPosition, false, false, colorInt));
    }

    private void addDayMarker(int rowPosition, int columnPosition) {
        this.circles.add(CalendarThumbnailCircle.day(rowPosition, columnPosition));
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Iterator it = this.rects.iterator();
        while (it.hasNext()) {
            drawReservationRect(canvas, (CalendarReservationRect) it.next());
        }
        Iterator it2 = this.circles.iterator();
        while (it2.hasNext()) {
            drawCalendarCircle(canvas, (CalendarThumbnailCircle) it2.next());
        }
    }

    private void drawCalendarCircle(Canvas canvas, CalendarThumbnailCircle model) {
        canvas.drawCircle((float) (this.halfCellSize + (model.column * this.cellSize)), (float) (this.halfCellSize + (model.row * this.cellSize)), (float) (model.big ? this.bigRadius : this.smallRadius), getPaint(model.isStroke, model.isGray, model.colorInt));
    }

    private void drawReservationRect(Canvas canvas, CalendarReservationRect model) {
        int offset;
        int width = model.isHalf ? this.halfCellSize + 1 : this.cellSize;
        if (model.isOffset) {
            offset = this.halfCellSize;
        } else {
            offset = 0;
        }
        int left = (model.column * this.cellSize) + offset;
        this.rect.set(left, ((model.row * this.cellSize) + this.halfCellSize) - this.bigRadius, left + width, (((model.row + 1) * this.cellSize) - this.halfCellSize) + this.bigRadius);
        canvas.drawRect(this.rect, getPaint(false, false, model.colorInt));
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        this.cellSize = width / 7;
        this.halfCellSize = this.cellSize / 2;
        this.smallRadius = this.dotSize;
        this.bigRadius = this.dotSize * 2;
        setMeasuredDimension(width, this.cellSize * this.numberOfRows);
    }

    private Paint getPaint(boolean isStroke, boolean isGray, int colorInt) {
        Paint paint2 = this.paint;
        if (isGray) {
            colorInt = this.backgroundGray;
        }
        paint2.setColor(colorInt);
        this.paint.setStyle(isStroke ? Style.STROKE : Style.FILL);
        return this.paint;
    }
}
