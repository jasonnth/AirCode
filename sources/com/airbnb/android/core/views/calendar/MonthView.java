package com.airbnb.android.core.views.calendar;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.p000v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.views.calendar.CalendarDayModel.Type;
import com.airbnb.android.utils.DrawingUtils;
import com.airbnb.p027n2.primitives.fonts.Font;
import com.airbnb.p027n2.primitives.fonts.FontManager;

public class MonthView extends View {
    private static final int DEFAULT_NUM_ROWS = 6;
    private static final int TEXT_STYLE_BOLD_AVAILABLE_NO_STRIKETHROUGH = 1;
    private static final int TEXT_STYLE_BOLD_UNAVAILABLE_NO_STRIKETHROUGH = 2;
    private static final int TEXT_STYLE_NORMAL_UNAVAILABLE_NO_STRIKETHROUGH = 4;
    private static final int TEXT_STYLE_NORMAL_UNAVAILABLE_STRIKETHROUGH = 3;
    private static final int TEXT_STYLE_SELECTED = 5;
    private Paint availablePaint;
    private final Rect dayBoundsRect = new Rect();
    private int extraUnavailableSlashWidth;
    private int halfRowHeight;
    private int halfRowHeightMinusPadding;
    private int monthHeaderLeftPadding;
    private int monthHeaderSize;
    private CalendarMonthModel monthModel;
    private Paint monthTitlePaint;
    private Paint normalUnavailablePaint;
    private int numCells;
    private int numRows = 6;
    private OnDayClickListener onDayClickListener;
    private int rowHeight;
    private Paint selectedCirclePaint;
    private int selectedCircleRadius;
    private Paint selectedDayPaint;
    private Paint selectedNonEndNumberPaint;
    private int selectionHalfPadding;
    private final Path slashPath = new Path();
    private MonthViewStyle style;
    private Paint todayCirclePaint;
    private int todayCircleRadius;
    private Paint unavailablePaint;
    private Paint unavailableSlashPaint;

    public enum MonthViewStyle {
        WHITE(C0716R.color.n2_babu, C0716R.color.n2_hof, C0716R.color.n2_kazan, C0716R.color.n2_hof, C0716R.color.n2_calendar_bold_unavailable_date_white_style, C0716R.color.n2_calendar_unavailable_date_white_style, C0716R.color.n2_white, C0716R.color.n2_horizontal_rule_gray, C0716R.dimen.jellyfish_calendar_stroke_width, Font.CircularBold, Font.CircularBook, Font.CircularLight),
        WHITE_NEW(C0716R.color.n2_babu, C0716R.color.n2_hof, C0716R.color.n2_kazan, C0716R.color.n2_hof, C0716R.color.n2_calendar_bold_unavailable_date_white_style, C0716R.color.n2_calendar_unavailable_date_white_style, C0716R.color.n2_white, C0716R.color.n2_horizontal_rule_gray, C0716R.dimen.jellyfish_calendar_stroke_width_thin, Font.CircularBold, Font.CircularBold, Font.CircularLight),
        BABU(C0716R.color.white, C0716R.color.white, C0716R.color.translucent_white, C0716R.color.white, C0716R.color.n2_white_66, C0716R.color.n2_white_66, C0716R.color.n2_kazan, C0716R.color.white, C0716R.dimen.jellyfish_calendar_stroke_width, Font.CircularBold, Font.CircularBold, Font.CircularBook),
        BABU_NEW(C0716R.color.white, C0716R.color.white, C0716R.color.translucent_white, C0716R.color.white, C0716R.color.n2_white_66, C0716R.color.n2_white_66, C0716R.color.n2_kazan, C0716R.color.white, C0716R.dimen.jellyfish_calendar_stroke_width_thin, Font.CircularBold, Font.CircularBold, Font.CircularLight);
        
        final Font availableDateFont;
        final int boldAvailablePaintColor;
        final int boldUnavailablePaintColor;
        final int monthTitlePaintColor;
        final int monthTitleSelectedRangePaintColor;
        final int normalUnavailablePaintColor;
        final int selectedCircleColor;
        final Font selectedDateFont;
        final int selectedDayPaintColor;
        final int slashStrokeWidth;
        final int todayCirclePaintColor;
        final Font unavailableDateFont;

        private MonthViewStyle(int selectedCircleColor2, int monthTitlePaintColor2, int monthTitleSelectedRangePaintColor2, int boldAvailablePaintColor2, int boldUnavailablePaintColor2, int normalUnavailablePaintColor2, int selectedDayPaintColor2, int todayCirclePaintColor2, int slashStrokeWidth2, Font selectedDateFont2, Font availableDateFont2, Font unavailableDateFont2) {
            this.selectedCircleColor = selectedCircleColor2;
            this.monthTitlePaintColor = monthTitlePaintColor2;
            this.monthTitleSelectedRangePaintColor = monthTitleSelectedRangePaintColor2;
            this.boldAvailablePaintColor = boldAvailablePaintColor2;
            this.boldUnavailablePaintColor = boldUnavailablePaintColor2;
            this.normalUnavailablePaintColor = normalUnavailablePaintColor2;
            this.selectedDayPaintColor = selectedDayPaintColor2;
            this.todayCirclePaintColor = todayCirclePaintColor2;
            this.slashStrokeWidth = slashStrokeWidth2;
            this.selectedDateFont = selectedDateFont2;
            this.availableDateFont = availableDateFont2;
            this.unavailableDateFont = unavailableDateFont2;
        }
    }

    public interface OnDayClickListener {
        void onDayClick(MonthView monthView, CalendarMonthModel calendarMonthModel, CalendarDayModel calendarDayModel, int[] iArr);
    }

    public MonthView(Context context) {
        super(context);
        initPaints();
    }

    public MonthView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaints();
    }

    public MonthView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaints();
    }

    public void setStyle(MonthViewStyle style2) {
        if (this.style != style2) {
            this.style = style2;
            initPaints();
        }
    }

    private void initPaints() {
        if (this.style == null) {
            this.style = MonthViewStyle.BABU;
        }
        Resources r = getContext().getResources();
        int dayTextSize = r.getDimensionPixelSize(C0716R.dimen.calendar_text_size_day);
        int monthLabelTextSize = r.getDimensionPixelSize(C0716R.dimen.calendar_text_size_month);
        this.monthHeaderLeftPadding = r.getDimensionPixelOffset(C0716R.dimen.calendar_header_month_left_padding);
        this.monthHeaderSize = r.getDimensionPixelOffset(C0716R.dimen.calendar_header_month_height);
        this.selectionHalfPadding = r.getDimensionPixelOffset(C0716R.dimen.calendar_selected_state_half_padding);
        Typeface monthTitleFont = FontManager.getTypeface(Font.CircularLight, getContext());
        Typeface selectedDateTypeFace = FontManager.getTypeface(this.style.selectedDateFont, getContext());
        Typeface availableDateTypeFace = FontManager.getTypeface(this.style.availableDateFont, getContext());
        Typeface unavailableDateTypeFace = FontManager.getTypeface(this.style.unavailableDateFont, getContext());
        this.monthTitlePaint = new Paint();
        this.monthTitlePaint.setAntiAlias(true);
        this.monthTitlePaint.setTextSize((float) monthLabelTextSize);
        this.monthTitlePaint.setColor(getColor(this.style.monthTitlePaintColor));
        this.monthTitlePaint.setTypeface(monthTitleFont);
        this.monthTitlePaint.setStyle(Style.FILL);
        new Paint(this.monthTitlePaint).setColor(getColor(this.style.monthTitleSelectedRangePaintColor));
        this.selectedCirclePaint = new Paint();
        this.selectedCirclePaint.setAntiAlias(true);
        this.selectedCirclePaint.setColor(getColor(this.style.selectedCircleColor));
        this.selectedCirclePaint.setTextAlign(Align.CENTER);
        this.selectedCirclePaint.setStyle(Style.FILL);
        this.selectedNonEndNumberPaint = new Paint(this.selectedCirclePaint);
        this.availablePaint = new Paint();
        this.availablePaint.setAntiAlias(true);
        this.availablePaint.setTextSize((float) dayTextSize);
        this.availablePaint.setStyle(Style.FILL);
        this.availablePaint.setFakeBoldText(false);
        this.availablePaint.setColor(getColor(this.style.boldAvailablePaintColor));
        this.availablePaint.setTypeface(availableDateTypeFace);
        this.unavailablePaint = new Paint(this.availablePaint);
        this.unavailablePaint.setColor(getColor(this.style.boldUnavailablePaintColor));
        this.normalUnavailablePaint = new Paint(this.unavailablePaint);
        this.normalUnavailablePaint.setColor(getColor(this.style.normalUnavailablePaintColor));
        this.normalUnavailablePaint.setTypeface(unavailableDateTypeFace);
        this.selectedDayPaint = new Paint(this.availablePaint);
        this.selectedDayPaint.setColor(getColor(this.style.selectedDayPaintColor));
        this.selectedDayPaint.setTypeface(selectedDateTypeFace);
        this.unavailableSlashPaint = new Paint(this.unavailablePaint);
        this.unavailableSlashPaint.setColor(getColor(this.style.normalUnavailablePaintColor));
        this.unavailableSlashPaint.setStrokeWidth((float) r.getDimensionPixelSize(this.style.slashStrokeWidth));
        this.unavailableSlashPaint.setStyle(Style.STROKE);
        this.unavailableSlashPaint.setStrokeCap(Cap.ROUND);
        this.extraUnavailableSlashWidth = r.getDimensionPixelSize(C0716R.dimen.jellyfish_calendar_unavailable_slash_extra_width);
        this.todayCirclePaint = new Paint(this.availablePaint);
        this.todayCirclePaint.setStyle(Style.STROKE);
        this.todayCirclePaint.setStrokeWidth((float) r.getDimensionPixelSize(C0716R.dimen.calendar_today_circle_stroke_width));
        this.todayCirclePaint.setColor(getColor(this.style.todayCirclePaintColor));
        this.todayCircleRadius = getResources().getDimensionPixelSize(C0716R.dimen.calendar_today_circle_radius);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        drawMonthTitle(canvas);
        drawDates(canvas);
    }

    private void drawMonthTitle(Canvas canvas) {
        DrawingUtils.drawTextVerticallyCentred(canvas, this.monthTitlePaint, this.monthModel.getMonthString(getContext(), AirDate.today().getYear() != this.monthModel.getCurrentYear()), (float) this.monthHeaderLeftPadding, (float) (this.monthHeaderSize / 2));
    }

    private void drawDates(Canvas canvas) {
        Paint paint;
        int y = (this.rowHeight / 2) + this.monthHeaderSize;
        int paddingDay = getWidth() / 14;
        int dayOffset = this.monthModel.getStartDayOffset();
        fillSelectedMonthStart(dayOffset, paddingDay, y, canvas);
        for (CalendarDayModel dayModel : this.monthModel.getDays()) {
            int x = paddingDay * ((dayOffset * 2) + 1);
            drawSelectedEnds(dayModel, x, y, canvas);
            if (dayModel.isSelectedMiddle()) {
                canvas.drawRect((float) (x - this.halfRowHeight), (float) (y - this.halfRowHeightMinusPadding), (float) (this.halfRowHeight + x), (float) (this.halfRowHeightMinusPadding + y), this.selectedNonEndNumberPaint);
            }
            String dayText = String.valueOf(dayModel.day);
            int textStyle = getTextStyle(dayModel);
            switch (textStyle) {
                case 2:
                    paint = this.unavailablePaint;
                    break;
                case 3:
                    paint = this.normalUnavailablePaint;
                    break;
                case 4:
                    paint = this.normalUnavailablePaint;
                    break;
                case 5:
                    paint = this.selectedDayPaint;
                    break;
                default:
                    paint = this.availablePaint;
                    break;
            }
            if (dayModel.type == Type.SelectedUnavailable) {
                Paint paint2 = new Paint(paint);
                paint2.setColor(getColor(C0716R.color.n2_arches));
                paint = paint2;
            }
            DrawingUtils.drawTextCentred(canvas, paint, dayText, (float) x, (float) y);
            paint.getTextBounds(dayText, 0, dayText.length(), this.dayBoundsRect);
            if (textStyle == 3) {
                drawUnavailableSlash(canvas, dayModel, x, y, this.dayBoundsRect);
            }
            drawTodayCircleIfNeeded(dayModel, x, y, canvas);
            dayOffset++;
            if (dayOffset == 7) {
                dayOffset = 0;
                y += this.rowHeight;
            }
        }
        fillSelectedMonthEnd(dayOffset, paddingDay, y, canvas);
    }

    private void drawUnavailableSlash(Canvas canvas, CalendarDayModel currDay, int x, int y, Rect textBounds) {
        Paint paint = new Paint(this.unavailableSlashPaint);
        if (currDay.type == Type.SelectedUnavailable) {
            paint.setColor(getColor(C0716R.color.n2_arches));
        }
        this.slashPath.moveTo((float) ((x - (textBounds.width() / 2)) - this.extraUnavailableSlashWidth), (float) y);
        this.slashPath.lineTo((float) ((textBounds.width() / 2) + x + this.extraUnavailableSlashWidth), (float) y);
        canvas.drawPath(this.slashPath, paint);
        this.slashPath.reset();
    }

    private void drawTodayCircleIfNeeded(CalendarDayModel currDay, int x, int y, Canvas canvas) {
        if (currDay.isToday && !currDay.isSelected()) {
            if (currDay.isSelected()) {
                this.todayCirclePaint.setColor(this.selectedDayPaint.getColor());
            } else {
                this.todayCirclePaint.setColor(getColor(this.style.todayCirclePaintColor));
            }
            canvas.drawCircle((float) x, (float) y, (float) this.todayCircleRadius, this.todayCirclePaint);
        }
    }

    @DayTextStyle
    private int getTextStyle(CalendarDayModel dayModel) {
        switch (dayModel.type) {
            case Past:
                return 4;
            case CheckIn:
            case CheckOut:
                return 1;
            case SelectedCheckIn:
            case SelectedMiddleDayAvailable:
            case SelectedMiddleDayUnavailable:
            case SelectedCheckOut:
                return 5;
            case Unavailable:
            case SelectedUnavailable:
                Check.notNull(dayModel.unavailabilityType);
                switch (dayModel.unavailabilityType) {
                    case UnavailableForCheckIn:
                    case UnavailableForCheckOut:
                    case ClosedToArrival:
                    case ClosedToDeparture:
                    case ContainsUnavailableDates:
                    case DoesntSatisfyMaxNights:
                    case SpecificCheckInDate:
                        return 3;
                    case DoesntSatisfyMinNights:
                    case CantSatisfyMinNights:
                        return 2;
                }
        }
        BugsnagWrapper.notify((Throwable) new IllegalArgumentException("Unknown paint for day with type " + dayModel.type + " and unavailabilityType " + dayModel.unavailabilityType));
        return 1;
    }

    private void drawSelectedEnds(CalendarDayModel day, int x, int y, Canvas canvas) {
        if (day.isSelectedCheckIn() || day.isSelectedCheckOut()) {
            if (this.monthModel.hasSelectedStartAndEnd()) {
                if (day.isSelectedCheckIn()) {
                    canvas.drawRect((float) x, (float) (y - this.halfRowHeightMinusPadding), (float) (this.halfRowHeight + x), (float) (this.halfRowHeightMinusPadding + y), this.selectedNonEndNumberPaint);
                } else if (day.isSelectedCheckOut()) {
                    canvas.drawRect((float) (x - this.halfRowHeight), (float) (y - this.halfRowHeightMinusPadding), (float) x, (float) (this.halfRowHeightMinusPadding + y), this.selectedNonEndNumberPaint);
                }
            }
            canvas.drawCircle((float) x, (float) y, (float) (this.selectedCircleRadius - this.selectionHalfPadding), this.selectedCirclePaint);
        }
    }

    private void fillSelectedMonthStart(int dayOffset, int paddingDay, int y, Canvas canvas) {
        if (this.monthModel.doesSelectionStartInPreviousMonths()) {
            for (int i = 0; i < dayOffset; i++) {
                int x = paddingDay * ((i * 2) + 1);
                canvas.drawRect((float) (x - this.halfRowHeight), (float) (y - this.halfRowHeightMinusPadding), (float) (this.halfRowHeight + x), (float) (this.halfRowHeightMinusPadding + y), this.selectedNonEndNumberPaint);
            }
        }
    }

    private void fillSelectedMonthEnd(int dayOffset, int paddingDay, int y, Canvas canvas) {
        if (this.monthModel.doesSelectionEndInUpcomingMonths()) {
            for (int i = 0; (i + dayOffset) % 7 != 0; i++) {
                int x = paddingDay * (((i + dayOffset) * 2) + 1);
                canvas.drawRect((float) (x - this.halfRowHeight), (float) (y - this.halfRowHeightMinusPadding), (float) (this.halfRowHeight + x), (float) (this.halfRowHeightMinusPadding + y), this.selectedNonEndNumberPaint);
            }
        }
    }

    private int calculateNumRows() {
        int offset = this.monthModel.getStartDayOffset();
        return ((this.numCells + offset) % 7 > 0 ? 1 : 0) + ((this.numCells + offset) / 7);
    }

    public CalendarDayModel getDayFromLocation(float x, float y) {
        if (y < ((float) this.monthHeaderSize)) {
            return null;
        }
        return this.monthModel.getDayModel((((int) ((7.0f * x) / ((float) getWidth()))) - this.monthModel.getStartDayOffset()) + 1 + ((((int) (y - ((float) this.monthHeaderSize))) / this.rowHeight) * 7));
    }

    private int[] getTopCenterLocationForDay(CalendarDayModel dayModel) {
        int numDaysFromStartDay = dayModel.day - this.monthModel.getStartDay();
        int numWeeksFromStartDay = numDaysFromStartDay / 7;
        int dayOfWeek = this.monthModel.getStartDayOffset() + (numDaysFromStartDay % 7);
        if (dayOfWeek >= 7) {
            numWeeksFromStartDay++;
        }
        return new int[]{(getWidth() / 14) * (((dayOfWeek % 7) * 2) + 1), this.monthHeaderSize + (this.rowHeight * numWeeksFromStartDay)};
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        initDimensions(width);
        setMeasuredDimension(width, (this.rowHeight * this.numRows) + this.monthHeaderSize);
    }

    private void initDimensions(int viewWidth) {
        this.rowHeight = viewWidth / 7;
        this.halfRowHeight = this.rowHeight / 2;
        this.halfRowHeightMinusPadding = this.halfRowHeight - this.selectionHalfPadding;
        this.selectedCircleRadius = this.halfRowHeight;
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == 1) {
            CalendarDayModel day = getDayFromLocation(event.getX(), event.getY());
            if (day != null && !day.isInPast()) {
                onDayClick(day);
            }
        }
        return true;
    }

    public void onDayClick(CalendarDayModel day) {
        int[] screenCoordinate = new int[2];
        getLocationOnScreen(screenCoordinate);
        int[] relativeCoordinate = getTopCenterLocationForDay(day);
        screenCoordinate[0] = screenCoordinate[0] + relativeCoordinate[0];
        screenCoordinate[1] = screenCoordinate[1] + relativeCoordinate[1];
        if (this.onDayClickListener != null) {
            this.onDayClickListener.onDayClick(this, this.monthModel, day, screenCoordinate);
        }
    }

    private int getColor(int res) {
        return ContextCompat.getColor(getContext(), res);
    }

    public void bindMonthData(CalendarMonthModel monthModel2) {
        this.monthModel = monthModel2;
        this.numCells = this.monthModel.getNumberDaysInMonth();
        this.numRows = calculateNumRows();
        requestLayout();
    }

    public void setOnDayClickListener(OnDayClickListener onDayClickListener2) {
        this.onDayClickListener = onDayClickListener2;
    }
}
