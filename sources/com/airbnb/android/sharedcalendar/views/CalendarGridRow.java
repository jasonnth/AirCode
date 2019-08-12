package com.airbnb.android.sharedcalendar.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.p000v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import butterknife.BindDimen;
import butterknife.ButterKnife;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.erf.PricingFeatureToggles;
import com.airbnb.android.core.models.CalendarDayPromotion;
import com.airbnb.android.sharedcalendar.C1576R;
import com.airbnb.android.sharedcalendar.listeners.CalendarGridTapListener;
import com.airbnb.android.sharedcalendar.models.CalendarGridDayModel;
import com.airbnb.android.sharedcalendar.models.CalendarGridNestedBusyModel;
import com.airbnb.android.sharedcalendar.models.CalendarGridReservationModel;
import com.airbnb.android.utils.DrawingUtils;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.fonts.Font;
import com.airbnb.p027n2.primitives.fonts.FontManager;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;
import java.util.List;

public class CalendarGridRow extends ViewGroup {
    private static final int NUMBER_OF_DAYS = 7;
    private static final float pastReservationGuestPhotoAlpha = 0.4f;
    private final Paint babuFillPaint;
    private final Paint baseFillPaint;
    private final Paint boldWhiteTextPaint;
    @BindDimen
    int cellPadding;
    private List<CalendarGridDayModel> dayModels;
    private final Paint dayTextPaint;
    private boolean editMode;
    private final Paint grayFillPaint;
    private final Paint grayStrokePaint;
    private final Paint gridLinesPaint;
    private int halfRowSize;
    private int halfRowSizeMinusPadding;
    private final int halfStrokeWidth;
    private final Paint hofFillPaint;
    private final Paint hofStrokePaint;
    @BindDimen
    int imagePadding;
    private final Paint lightWhiteTextPaint;
    private List<CalendarGridNestedBusyModel> nestedBusyModels;
    private final Paint pastDayTextPaint;
    private int positionEnd;
    private int positionStart;
    private final RectF rectF;
    private List<CalendarGridReservationModel> reservationModels;
    private int rowSize;
    @BindDimen
    int strokeWidth;
    private CalendarGridTapListener tapListener;
    @BindDimen
    int tipCircleRadius;
    private final Paint whiteFillPaint;
    private final Paint whiteStrokePaint;

    public CalendarGridRow(Context context) {
        this(context, null);
    }

    public CalendarGridRow(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarGridRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.dayTextPaint = new Paint();
        this.pastDayTextPaint = new Paint();
        this.boldWhiteTextPaint = new Paint();
        this.lightWhiteTextPaint = new Paint();
        this.baseFillPaint = new Paint();
        this.whiteFillPaint = new Paint();
        this.hofFillPaint = new Paint();
        this.babuFillPaint = new Paint();
        this.grayFillPaint = new Paint();
        this.hofStrokePaint = new Paint();
        this.grayStrokePaint = new Paint();
        this.whiteStrokePaint = new Paint();
        this.gridLinesPaint = new Paint();
        this.rectF = new RectF();
        ButterKnife.bind((View) this);
        this.halfStrokeWidth = this.strokeWidth / 2;
        initPaints();
    }

    private void initPaints() {
        Context c = getContext();
        Resources r = c.getResources();
        Typeface circularLightTf = FontManager.getTypeface(Font.CircularLight, getContext());
        Typeface circularBoldTf = FontManager.getTypeface(Font.CircularBold, getContext());
        int dayTextSize = r.getDimensionPixelSize(C1576R.dimen.calendar_text_size_day);
        int hofColor = ContextCompat.getColor(c, C1576R.color.n2_hof);
        int babuColor = ContextCompat.getColor(c, C1576R.color.n2_babu);
        int gridLinesColor = ContextCompat.getColor(c, C1576R.color.n2_background_gray);
        int grayBackgroundColor = ContextCompat.getColor(c, C1576R.color.n2_background_gray_50);
        int mediumHofColor = ContextCompat.getColor(c, C1576R.color.n2_hof_40);
        int lightWhiteColor = ContextCompat.getColor(c, C1576R.color.n2_white_60);
        this.baseFillPaint.setAntiAlias(true);
        this.baseFillPaint.setStyle(Style.FILL);
        this.baseFillPaint.setTypeface(circularLightTf);
        Paint baseStrokePaint = new Paint();
        baseStrokePaint.setAntiAlias(true);
        baseStrokePaint.setStyle(Style.STROKE);
        baseStrokePaint.setStrokeWidth((float) this.strokeWidth);
        this.whiteFillPaint.set(this.baseFillPaint);
        this.whiteFillPaint.setColor(-1);
        this.hofFillPaint.set(this.baseFillPaint);
        this.hofFillPaint.setColor(hofColor);
        this.babuFillPaint.set(this.baseFillPaint);
        this.babuFillPaint.setColor(babuColor);
        this.dayTextPaint.set(this.baseFillPaint);
        this.dayTextPaint.setTextSize((float) dayTextSize);
        this.dayTextPaint.setColor(hofColor);
        this.grayFillPaint.set(this.baseFillPaint);
        this.grayFillPaint.setColor(grayBackgroundColor);
        this.pastDayTextPaint.set(this.baseFillPaint);
        this.pastDayTextPaint.setTextSize((float) dayTextSize);
        this.pastDayTextPaint.setColor(mediumHofColor);
        this.boldWhiteTextPaint.set(this.whiteFillPaint);
        this.boldWhiteTextPaint.setTextSize((float) dayTextSize);
        this.boldWhiteTextPaint.setTypeface(circularBoldTf);
        this.lightWhiteTextPaint.set(this.baseFillPaint);
        this.lightWhiteTextPaint.setColor(lightWhiteColor);
        this.lightWhiteTextPaint.setTextSize((float) dayTextSize);
        this.lightWhiteTextPaint.setTypeface(circularBoldTf);
        this.hofStrokePaint.set(baseStrokePaint);
        this.hofStrokePaint.setColor(hofColor);
        this.grayStrokePaint.set(baseStrokePaint);
        this.grayStrokePaint.setColor(mediumHofColor);
        this.whiteStrokePaint.set(baseStrokePaint);
        this.whiteStrokePaint.setColor(-1);
        this.gridLinesPaint.set(baseStrokePaint);
        this.gridLinesPaint.setColor(gridLinesColor);
    }

    public void bind(List<CalendarGridReservationModel> reservationModels2, List<CalendarGridDayModel> dayModels2, List<CalendarGridNestedBusyModel> nestedBusyModels2, int positionStart2, int positionEnd2, boolean editMode2) {
        this.dayModels = dayModels2;
        this.reservationModels = reservationModels2;
        this.nestedBusyModels = nestedBusyModels2;
        this.positionStart = positionStart2;
        this.positionEnd = positionEnd2;
        this.editMode = editMode2;
        resetViewsForGuestPhotos();
        requestLayout();
    }

    private void resetViewsForGuestPhotos() {
        removeAllViews();
        for (CalendarGridReservationModel reservationModel : this.reservationModels) {
            if (reservationModel.showGuestProfilePhoto()) {
                HaloImageView imageView = new HaloImageView(getContext());
                AirTextView placeholderTextView = new AirTextView(getContext());
                if (reservationModel.shouldHideGuestProfilePhoto()) {
                    imageView.setImageResource(C1576R.C1577drawable.no_profile_image);
                    placeholderTextView.setText(reservationModel.getProfilePlaceholderText());
                    placeholderTextView.setTextAppearance(getContext(), C1576R.C1581style.n2_LargeText_Inverse);
                    placeholderTextView.setGravity(17);
                    placeholderTextView.setVisibility(0);
                } else {
                    imageView.setImageResource(0);
                    imageView.setImageUrl(reservationModel.getGuestPhotoUrl());
                    placeholderTextView.setVisibility(8);
                }
                if (reservationModel.hasEnded()) {
                    imageView.setAlpha(pastReservationGuestPhotoAlpha);
                }
                addView(imageView);
                addView(placeholderTextView);
                reservationModel.setImageView(imageView);
                reservationModel.setPlaceholderTextView(placeholderTextView);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == 1) {
            onTapPosition((int) (event.getX() / ((float) this.rowSize)));
        }
        return true;
    }

    private void onTapPosition(int position) {
        if (position < this.positionEnd && position >= this.positionStart && this.tapListener != null) {
            CalendarGridDayModel dayModel = (CalendarGridDayModel) this.dayModels.get(position - this.positionStart);
            if (dayModel.hasReservation()) {
                if (!this.editMode) {
                    this.tapListener.onReservationClick(dayModel.getReservationCode());
                }
            } else if (!dayModel.isUnEditable() || dayModel.isNestedBusy()) {
                this.tapListener.onDayClick(dayModel);
            }
        }
    }

    public void setTapListener(CalendarGridTapListener tapListener2) {
        this.tapListener = tapListener2;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(width, width / 7);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.rowSize = w / 7;
        this.halfRowSize = this.rowSize / 2;
        this.halfRowSizeMinusPadding = this.halfRowSize - this.cellPadding;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        positionViewsForGuestPhotos();
    }

    private void positionViewsForGuestPhotos() {
        for (CalendarGridReservationModel reservationModel : this.reservationModels) {
            if (reservationModel.showGuestProfilePhoto()) {
                HaloImageView imageView = reservationModel.getImageView();
                AirTextView placeholderTextView = reservationModel.getPlaceholderTextView();
                ViewUtils.setInvisibleIf(imageView, this.editMode);
                if (!this.editMode) {
                    int left = getCellStartOffset(reservationModel.getDaysUntilReservationStarts() + this.positionStart);
                    imageView.layout(this.imagePadding + left, this.imagePadding, (this.rowSize + left) - this.imagePadding, this.rowSize - this.imagePadding);
                    placeholderTextView.layout(this.imagePadding + left, this.imagePadding * 2, (this.rowSize + left) - this.imagePadding, this.rowSize - this.imagePadding);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        drawGrayBackgroundsForBlockedDays(canvas);
        drawVerticalGridLines(canvas);
        drawHorizontalGridLine(canvas);
        drawToday(canvas);
        drawReservations(canvas);
        if (FeatureToggles.showNestedListings()) {
            drawNestedBusy(canvas);
        }
        drawDates(canvas);
        super.dispatchDraw(canvas);
    }

    private void drawGrayBackgroundsForBlockedDays(Canvas canvas) {
        for (CalendarGridDayModel dayModel : this.dayModels) {
            if (dayModel.showWithGrayBackground()) {
                int position = dayModel.getDrawnPosition();
                this.rectF.set((float) getCellStart(position), 0.0f, (float) getCellStart(position + 1), (float) getHeight());
                canvas.drawRect(this.rectF, this.grayFillPaint);
            }
        }
    }

    private void drawVerticalGridLines(Canvas canvas) {
        int start = Math.max(this.positionStart, 1);
        int end = Math.min(this.positionEnd + 1, 7);
        int height = getHeight();
        for (int i = start; i < end; i++) {
            int x = getCellStart(i);
            canvas.drawLine((float) x, 0.0f, (float) x, (float) height, this.gridLinesPaint);
        }
        int x2 = getCellStart(0);
        canvas.drawLine((float) x2, 0.0f, (float) x2, (float) height, this.gridLinesPaint);
        int x3 = getCellStart(7);
        canvas.drawLine((float) x3, 0.0f, (float) x3, (float) height, this.gridLinesPaint);
    }

    private void drawHorizontalGridLine(Canvas canvas) {
        int gridLineY = getHeight() - this.halfStrokeWidth;
        canvas.drawLine(0.0f, (float) gridLineY, (float) getWidth(), (float) gridLineY, this.gridLinesPaint);
    }

    private void drawToday(Canvas canvas) {
        for (CalendarGridDayModel dayModel : this.dayModels) {
            if (dayModel.isToday()) {
                int position = dayModel.getDrawnPosition();
                this.rectF.set((float) getCellStart(position), 0.0f, (float) getCellStart(position + 1), (float) getHeight());
                canvas.drawRect(this.rectF, this.hofStrokePaint);
            }
        }
    }

    private void drawDates(Canvas canvas) {
        int halfSlashLength = this.rowSize / 5;
        for (CalendarGridDayModel dayModel : this.dayModels) {
            int x = getCellCenter(dayModel.getDrawnPosition());
            CalendarDayPromotion smartPromotion = dayModel.getCalendarDay().getPromotion();
            Paint tipPaint = this.babuFillPaint;
            if (dayModel.isSelected()) {
                canvas.drawCircle((float) x, (float) this.halfRowSize, (float) this.halfRowSizeMinusPadding, this.hofFillPaint);
                tipPaint = this.whiteFillPaint;
            }
            if (smartPromotion != null && smartPromotion.getDiscountPercentage() > 0 && PricingFeatureToggles.showHostSmartPromo()) {
                canvas.drawCircle((float) x, (float) (this.halfRowSize + ((this.halfRowSize * 5) / 8)), (float) this.tipCircleRadius, tipPaint);
            }
            if (this.editMode || !dayModel.hasReservation() || !dayModel.isFirstDayOfReservation()) {
                DrawingUtils.drawTextCentred(canvas, getTextPaintForDay(dayModel), String.valueOf(dayModel.getDayOfMonth()), (float) x, (float) this.halfRowSize);
            }
            if (dayModel.showWithUnavailableSlash()) {
                Canvas canvas2 = canvas;
                drawUnavailableSlash(canvas2, getUnavailableSlashPaint(dayModel.isSelected()), x, this.halfRowSize, halfSlashLength);
            }
            if (dayModel.showWithLightSlash()) {
                drawUnavailableSlash(canvas, this.grayStrokePaint, x, this.halfRowSize, halfSlashLength);
            }
        }
    }

    private Paint getTextPaintForDay(CalendarGridDayModel dayModel) {
        if (dayModel.isSelected() || dayModel.hasPendingRequest()) {
            return this.boldWhiteTextPaint;
        }
        if (dayModel.hasReservation() && this.editMode) {
            return this.pastDayTextPaint;
        }
        if (dayModel.hasReservation()) {
            if (!dayModel.isInPast() || !dayModel.hasReservationInProgress()) {
                return this.boldWhiteTextPaint;
            }
            return this.lightWhiteTextPaint;
        } else if (dayModel.drawWithLightTextColor()) {
            return this.pastDayTextPaint;
        } else {
            return this.dayTextPaint;
        }
    }

    private Paint getUnavailableSlashPaint(boolean isSelected) {
        return isSelected ? this.whiteStrokePaint : this.hofStrokePaint;
    }

    private void drawUnavailableSlash(Canvas canvas, Paint paint, int x, int y, int halfSlash) {
        canvas.drawLine((float) (x - halfSlash), (float) (y + halfSlash), (float) (x + halfSlash), (float) (y - halfSlash), paint);
    }

    private void drawReservations(Canvas canvas) {
        for (CalendarGridReservationModel reservationModel : this.reservationModels) {
            int drawnStart = reservationModel.getDaysUntilReservationStarts() + this.positionStart;
            int drawnEnd = reservationModel.getDaysUntilReservationEnds() + this.positionStart;
            this.baseFillPaint.setColor(ContextCompat.getColor(getContext(), this.editMode ? C1576R.color.c_hof_light : reservationModel.getColor()));
            drawReservationHead(canvas, this.baseFillPaint, drawnStart);
            drawReservationBody(canvas, this.baseFillPaint, drawnStart, drawnEnd);
            drawReservationButt(canvas, this.baseFillPaint, drawnEnd);
        }
    }

    private void drawReservationHead(Canvas canvas, Paint paint, int drawnStart) {
        if (drawnStart >= this.positionStart) {
            int x = getCellCenterOffset(drawnStart);
            canvas.drawCircle((float) x, (float) (this.halfRowSize - this.halfStrokeWidth), (float) (this.halfRowSize - this.strokeWidth), this.whiteFillPaint);
            canvas.drawCircle((float) x, (float) (this.halfRowSize - this.halfStrokeWidth), (float) this.halfRowSizeMinusPadding, paint);
            if (drawnStart == this.positionEnd - 1 && this.positionEnd < 7) {
                canvas.drawRect((float) (getCellStart(drawnStart + 1) + this.halfStrokeWidth), 0.0f, (float) getCellStart(drawnStart + 2), (float) (this.rowSize - this.strokeWidth), this.whiteFillPaint);
            }
        }
    }

    private void drawReservationBody(Canvas canvas, Paint paint, int drawnStart, int drawnEnd) {
        int endX;
        int startX = drawnStart >= this.positionStart ? getCellCenterOffset(drawnStart) : getCellStart(this.positionStart);
        if (drawnEnd < this.positionEnd) {
            endX = getCellEndOffset(drawnEnd);
        } else if (this.positionEnd < 7) {
            endX = getCellStart(this.positionEnd) + this.halfStrokeWidth;
        } else {
            endX = getCellStart(drawnEnd + 1);
        }
        this.rectF.set((float) startX, (float) (this.cellPadding - this.halfStrokeWidth), (float) endX, (float) (this.rowSize - this.cellPadding));
        canvas.drawRect(this.rectF, paint);
    }

    private void drawReservationButt(Canvas canvas, Paint paint, int drawnEnd) {
        if (drawnEnd < this.positionEnd) {
            canvas.drawCircle((float) getCellEndOffset(drawnEnd), (float) (this.halfRowSize - this.halfStrokeWidth), (float) this.halfRowSizeMinusPadding, paint);
            if (drawnEnd == this.positionStart && this.positionStart > 0) {
                canvas.drawRect((float) getCellStart(drawnEnd - 1), 0.0f, (float) (getCellStart(drawnEnd) - this.halfStrokeWidth), (float) (this.rowSize - this.strokeWidth), this.whiteFillPaint);
            }
        }
    }

    private void drawNestedBusy(Canvas canvas) {
        for (CalendarGridNestedBusyModel nestedBusyModel : this.nestedBusyModels) {
            int drawnStart = nestedBusyModel.getDaysUntilNestedBusyStarts() + this.positionStart;
            int drawnEnd = nestedBusyModel.getDaysUntilNestedBusyEnds() + this.positionStart;
            drawNestedBusyHead(canvas, drawnStart);
            drawNestedBusyButt(canvas, drawnEnd);
            drawNestedBusyBody(canvas, drawnStart, drawnEnd);
        }
    }

    private void drawNestedBusyHead(Canvas canvas, int drawnStart) {
        if (drawnStart >= this.positionStart) {
            int x = getCellCenterOffset(drawnStart);
            canvas.drawCircle((float) x, (float) this.halfRowSize, (float) (this.halfRowSizeMinusPadding + this.halfStrokeWidth), this.hofStrokePaint);
            canvas.drawCircle((float) x, (float) this.halfRowSize, (float) this.halfRowSizeMinusPadding, this.grayFillPaint);
            if (drawnStart == this.positionEnd - 1 && this.positionEnd < 7) {
                canvas.drawRect((float) (getCellStart(drawnStart + 1) + this.halfStrokeWidth), 0.0f, (float) getCellStart(drawnStart + 2), (float) (this.rowSize - this.strokeWidth), this.whiteFillPaint);
            }
        }
    }

    private void drawNestedBusyBody(Canvas canvas, int drawnStart, int drawnEnd) {
        int endX;
        if (drawnEnd != this.positionStart) {
            int startX = drawnStart >= this.positionStart ? getCellCenterOffset(drawnStart) : getCellStart(this.positionStart);
            if (drawnEnd < this.positionEnd) {
                endX = getCellEndOffset(drawnEnd);
            } else if (this.positionEnd < 7) {
                endX = getCellStart(this.positionEnd) + this.halfStrokeWidth;
            } else {
                endX = getCellStart(drawnEnd + 1);
            }
            this.rectF.set((float) (this.strokeWidth + startX), (float) (this.cellPadding - this.halfStrokeWidth), (float) (endX - this.strokeWidth), (float) (this.rowSize - (this.cellPadding - this.halfStrokeWidth)));
            canvas.drawRect(this.rectF, this.hofStrokePaint);
            this.rectF.set((float) startX, (float) this.cellPadding, (float) endX, (float) (this.rowSize - this.cellPadding));
            canvas.drawRect(this.rectF, this.whiteFillPaint);
            this.rectF.set((float) startX, (float) this.cellPadding, (float) endX, (float) (this.rowSize - this.cellPadding));
            canvas.drawRect(this.rectF, this.grayFillPaint);
        } else if (this.positionStart > 0) {
            canvas.drawRect((float) getCellStart(drawnEnd - 1), 0.0f, (float) (getCellStart(drawnEnd) - this.halfStrokeWidth), (float) (this.rowSize - this.strokeWidth), this.whiteFillPaint);
        }
    }

    private void drawNestedBusyButt(Canvas canvas, int drawnEnd) {
        if (drawnEnd < this.positionEnd) {
            int x = getCellEndOffset(drawnEnd);
            canvas.drawCircle((float) x, (float) this.halfRowSize, (float) (this.halfRowSizeMinusPadding + this.halfStrokeWidth), this.hofStrokePaint);
            canvas.drawCircle((float) x, (float) this.halfRowSize, (float) this.halfRowSizeMinusPadding, this.grayFillPaint);
            canvas.drawCircle((float) (x - (this.strokeWidth * 2)), (float) this.halfRowSize, (float) this.halfRowSizeMinusPadding, this.whiteFillPaint);
            canvas.drawCircle((float) (x - (this.strokeWidth * 2)), (float) this.halfRowSize, (float) this.halfRowSizeMinusPadding, this.grayFillPaint);
        }
    }

    private int getCellStart(int position) {
        return this.rowSize * position;
    }

    private int getCellStartOffset(int position) {
        return getCellStart(position) + this.imagePadding;
    }

    private int getCellCenter(int position) {
        return getCellStart(position) + this.halfRowSize;
    }

    private int getCellCenterOffset(int position) {
        return getCellStartOffset(position) + this.halfRowSize;
    }

    private int getCellEndOffset(int position) {
        return getCellStart(position) - this.imagePadding;
    }
}
