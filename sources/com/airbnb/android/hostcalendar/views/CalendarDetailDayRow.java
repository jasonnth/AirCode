package com.airbnb.android.hostcalendar.views;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.p000v4.content.ContextCompat;
import android.support.p002v7.content.res.AppCompatResources;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindColor;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.erf.PricingFeatureToggles;
import com.airbnb.android.core.models.CalendarDay;
import com.airbnb.android.core.models.CalendarDayPromotion;
import com.airbnb.android.core.models.CalendarRule;
import com.airbnb.android.core.utils.PercentageUtils;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.android.hostcalendar.adapters.CalendarDetailAdapter.CalendarDetailRow;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.components.TextRow;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;
import java.util.Collection;

public class CalendarDetailDayRow extends LinearLayout implements CalendarDetailRow {
    private static final int ANIMATION_DURATION_MILLIS = 1000;
    private static final float DEFAULT_ALPHA = 1.0f;
    private static final float PAST_ALPHA = 0.5f;
    private final String abbreviatedDayOfWeekFormat;
    @BindView
    AirTextView availabilityText;
    private final String availableText;
    private final String blockedText;
    @BindView
    AirTextView busyReasonText;
    private CalendarDay calendarDay;
    private CalendarRule calendarRule;
    @BindView
    View collapsedIcon;
    @BindColor
    int darkHofColor;
    private final Paint darkHofStrokePaint;
    private AirDate date;
    @BindView
    View dayContainer;
    @BindView
    AirTextView dayOfWeekText;
    @BindView
    AirTextView dayText;
    @BindView
    View divider;
    private boolean isFutureBlockedButEditable;
    private boolean isToday;
    @BindColor
    int lightHofColor;
    private final Paint lightHofStrokePaint;
    private final String noGuestsText;
    @BindView
    TextRow notesText;
    @BindDimen
    int paddingSmall;
    private int paddingTop;
    @BindView
    AirTextView priceText;
    private GradientDrawable selectedCircle;
    private GradientDrawable selectedTodayCircle;
    private final Rect slashRect;
    @BindView
    AirTextView smartPricingOffText;
    @BindView
    AirTextView smartPromoText;
    @BindDimen
    int strokeWidth;
    private final AirDate today;
    private Drawable todayCircle;
    @BindView
    View topSpace;
    @BindColor
    int transparentColor;
    private ValueAnimator unselectedCircleAnimator;
    private ValueAnimator unselectedTextAnimator;
    private boolean wasSelected;
    @BindColor
    int whiteColor;

    public interface CalendarDetailDayClickListener {
        void onDayClick(CalendarDetailDayRow calendarDetailDayRow);
    }

    public CalendarDetailDayRow(Context context) {
        this(context, null);
    }

    public CalendarDetailDayRow(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarDetailDayRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.today = AirDate.today();
        this.darkHofStrokePaint = new Paint();
        this.lightHofStrokePaint = new Paint();
        this.slashRect = new Rect();
        inflate(context, C6418R.layout.calendar_detail_day_row, this);
        ButterKnife.bind((View) this);
        setOrientation(1);
        this.abbreviatedDayOfWeekFormat = context.getString(C6418R.string.abbreviated_day_of_week_format);
        this.availableText = context.getString(C6418R.string.calendar_details_available);
        this.blockedText = context.getString(C6418R.string.calendar_details_blocked);
        this.noGuestsText = context.getString(C6418R.string.calendar_details_no_guests);
        initPaints();
        initAnimators();
        this.paddingTop = this.paddingSmall;
    }

    public AirDate getDateForScrolling() {
        return this.date;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            calculateSlashRect();
        }
    }

    public void bindCalendarDay(CalendarDay calendarDay2, boolean isSelected, CalendarRule calendarRule2) {
        boolean isUneditable;
        boolean z = true;
        this.calendarDay = calendarDay2;
        this.isToday = this.today.isSameDay(calendarDay2.getDate());
        boolean isPast = calendarDay2.getDate().isBefore(this.today);
        boolean isAvailable = calendarDay2.isAvailable();
        if (isPast || calendarDay2.isBlockedForExceedingMaxDayCap()) {
            isUneditable = true;
        } else {
            isUneditable = false;
        }
        if (isPast || isAvailable || calendarDay2.isBlockedForExceedingMaxDayCap()) {
            z = false;
        }
        this.isFutureBlockedButEditable = z;
        this.calendarRule = calendarRule2;
        bindDay(calendarDay2.getDate(), isUneditable);
        setSelected(isSelected);
        setBackgroundColor(ContextCompat.getColor(getContext(), this.isFutureBlockedButEditable ? C6418R.color.light_grey : C6418R.color.white));
        setAvailabilityText(getAvailablilityText(isAvailable, isPast));
        if (!isPast && !calendarDay2.isBlockedForExceedingMaxDayCap()) {
            setPriceText(calendarDay2.getFormattedNativePrice());
        }
        setSmartPromo(calendarDay2.getPromotion());
        setBusyReason(calendarDay2.getBusyReason(getResources(), calendarRule2));
        setNotes(calendarDay2.getNotes());
        ViewUtils.setVisibleIf((View) this.smartPricingOffText, calendarDay2.getPriceInfo().isDemandBasedPricingOverridden());
    }

    private void setSmartPromo(CalendarDayPromotion smartPromo) {
        boolean showSmartPromo;
        if (smartPromo == null || smartPromo.getDiscountPercentage() <= 0 || !PricingFeatureToggles.showHostSmartPromo()) {
            showSmartPromo = false;
        } else {
            showSmartPromo = true;
        }
        if (showSmartPromo) {
            this.smartPromoText.setText(getResources().getString(C6418R.string.host_calendar_smart_promotion_detail, new Object[]{PercentageUtils.localizePercentage(smartPromo.getDiscountPercentage())}));
        }
        ViewUtils.setVisibleIf((View) this.smartPromoText, showSmartPromo);
    }

    public void bindDay(AirDate date2, boolean isNonEditable) {
        boolean z;
        boolean z2 = true;
        this.date = date2;
        if (date2 != null) {
            setDayText(Integer.toString(date2.getDayOfMonth()));
            setDayOfWeekText(date2.formatDate(this.abbreviatedDayOfWeekFormat));
            setAlphaAndVisibility(isNonEditable);
        }
        ViewUtils.setVisibleIf(this.collapsedIcon, date2 == null);
        AirTextView airTextView = this.dayText;
        if (date2 != null) {
            z = true;
        } else {
            z = false;
        }
        ViewUtils.setVisibleIf((View) airTextView, z);
        AirTextView airTextView2 = this.dayOfWeekText;
        if (date2 == null) {
            z2 = false;
        }
        ViewUtils.setVisibleIf((View) airTextView2, z2);
        setAvailabilityText(null);
        setPriceText(null);
        setBusyReason(null);
        setSmartPromo(null);
        setNotes(null);
        setBackgroundColor(ContextCompat.getColor(getContext(), C6418R.color.white));
    }

    public CalendarDay getCalendarDay() {
        return this.calendarDay;
    }

    public void showTopSpace(boolean show) {
        ViewUtils.setVisibleIf(this.topSpace, show);
        this.paddingTop = show ? this.paddingSmall : this.strokeWidth;
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }

    public void setSelected(boolean selected) {
        this.wasSelected = isSelected();
        super.setSelected(selected);
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        boolean drawSelected;
        super.dispatchDraw(canvas);
        if (skipSelected() || (!this.wasSelected && !isSelected())) {
            drawSelected = false;
        } else {
            drawSelected = true;
        }
        drawSelectedCircle(drawSelected);
        if (this.wasSelected && !isSelected()) {
            this.wasSelected = false;
            this.unselectedCircleAnimator.start();
            this.unselectedTextAnimator.start();
        }
        if (!drawSelected && this.isToday) {
            this.dayContainer.setBackground(this.todayCircle);
        }
        if (shouldDrawFutureBusyStrikeThrough()) {
            canvas.drawLine((float) this.slashRect.right, (float) this.slashRect.top, (float) this.slashRect.left, (float) this.slashRect.bottom, this.darkHofStrokePaint);
        }
        if (shouldDrawPastNestedListingStrikeThrough()) {
            canvas.drawLine((float) this.slashRect.right, (float) this.slashRect.top, (float) this.slashRect.left, (float) this.slashRect.bottom, this.lightHofStrokePaint);
        }
    }

    private boolean skipSelected() {
        return FeatureToggles.showNestedListings() && this.calendarDay != null && !ListUtils.isEmpty((Collection<?>) this.calendarDay.getNestedBusyDetails());
    }

    private boolean shouldDrawFutureBusyStrikeThrough() {
        return this.calendarDay != null && this.isFutureBlockedButEditable;
    }

    private boolean shouldDrawPastNestedListingStrikeThrough() {
        return FeatureToggles.showNestedListings() && this.calendarDay != null && this.calendarDay.getDate().isBefore(this.today) && !ListUtils.isEmpty((Collection<?>) this.calendarDay.getNestedBusyDetails());
    }

    private void drawSelectedCircle(boolean selected) {
        if (selected) {
            this.dayContainer.setBackground(this.isToday ? this.selectedTodayCircle : this.selectedCircle);
            this.selectedTodayCircle.setColor(this.darkHofColor);
            this.selectedCircle.setColor(this.darkHofColor);
            this.dayOfWeekText.setTextColor(this.whiteColor);
            this.dayText.setTextColor(this.whiteColor);
            return;
        }
        this.dayContainer.setBackground(null);
        this.dayOfWeekText.setTextColor(this.darkHofColor);
        this.dayText.setTextColor(this.darkHofColor);
    }

    private void setAlphaAndVisibility(boolean isNonEditable) {
        float alpha = isNonEditable ? PAST_ALPHA : 1.0f;
        this.dayText.setAlpha(alpha);
        this.dayOfWeekText.setAlpha(alpha);
        this.availabilityText.setAlpha(alpha);
        this.busyReasonText.setAlpha(alpha);
        this.notesText.setAlpha(alpha);
    }

    private void setDayText(CharSequence text) {
        this.dayText.setText(text);
    }

    private void setDayOfWeekText(CharSequence text) {
        this.dayOfWeekText.setText(text);
    }

    private void setAvailabilityText(CharSequence text) {
        this.availabilityText.setText(text);
    }

    private void setPriceText(CharSequence price) {
        this.priceText.setText(price);
        ViewUtils.setGoneIf(this.priceText, TextUtils.isEmpty(price));
    }

    private void setBusyReason(CharSequence busyReason) {
        this.busyReasonText.setText(busyReason);
        ViewUtils.setGoneIf(this.busyReasonText, TextUtils.isEmpty(busyReason));
    }

    private void setNotes(CharSequence notes) {
        boolean hasEmptyNotes = TextUtils.isEmpty(notes);
        if (!hasEmptyNotes) {
            this.notesText.setText(notes);
            this.notesText.setReadMoreText(getContext().getString(C6418R.string.read_more_lower_cased));
        }
        ViewUtils.setGoneIf(this.notesText, hasEmptyNotes);
    }

    private String getAvailablilityText(boolean isAvailable, boolean isPast) {
        if (isAvailable) {
            return isPast ? this.noGuestsText : this.availableText;
        }
        return this.blockedText;
    }

    private void calculateSlashRect() {
        this.dayText.getDrawingRect(this.slashRect);
        this.slashRect.left = this.dayContainer.getLeft() + this.dayText.getLeft();
        this.slashRect.right = this.slashRect.left + this.dayText.getWidth();
        this.slashRect.top = this.dayContainer.getTop() + this.dayText.getTop() + this.paddingTop;
        this.slashRect.bottom = this.slashRect.top + this.dayText.getHeight();
    }

    private void initPaints() {
        this.darkHofStrokePaint.setAntiAlias(true);
        this.darkHofStrokePaint.setColor(this.darkHofColor);
        this.darkHofStrokePaint.setStyle(Style.STROKE);
        this.darkHofStrokePaint.setStrokeWidth((float) this.strokeWidth);
        this.lightHofStrokePaint.setAntiAlias(true);
        this.lightHofStrokePaint.setColor(this.lightHofColor);
        this.lightHofStrokePaint.setStyle(Style.STROKE);
        this.lightHofStrokePaint.setStrokeWidth((float) this.strokeWidth);
    }

    private void initAnimators() {
        this.todayCircle = AppCompatResources.getDrawable(getContext(), C6418R.C6419drawable.calendar_day_today_background);
        this.selectedTodayCircle = (GradientDrawable) AppCompatResources.getDrawable(getContext(), C6418R.C6419drawable.calendar_day_selected_today_background);
        this.selectedCircle = (GradientDrawable) AppCompatResources.getDrawable(getContext(), C6418R.C6419drawable.calendar_day_selected_background);
        this.unselectedCircleAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), new Object[]{Integer.valueOf(this.darkHofColor), Integer.valueOf(this.transparentColor)});
        this.unselectedCircleAnimator.addUpdateListener(CalendarDetailDayRow$$Lambda$1.lambdaFactory$(this));
        this.unselectedCircleAnimator.setDuration(1000);
        this.unselectedTextAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), new Object[]{Integer.valueOf(this.whiteColor), Integer.valueOf(this.darkHofColor)});
        this.unselectedTextAnimator.addUpdateListener(CalendarDetailDayRow$$Lambda$2.lambdaFactory$(this));
        this.unselectedTextAnimator.setDuration(1000);
    }

    static /* synthetic */ void lambda$initAnimators$0(CalendarDetailDayRow calendarDetailDayRow, ValueAnimator animator) {
        calendarDetailDayRow.selectedCircle.setColor(((Integer) animator.getAnimatedValue()).intValue());
        calendarDetailDayRow.selectedTodayCircle.setColor(((Integer) animator.getAnimatedValue()).intValue());
    }

    static /* synthetic */ void lambda$initAnimators$1(CalendarDetailDayRow calendarDetailDayRow, ValueAnimator animator) {
        calendarDetailDayRow.dayOfWeekText.setTextColor(((Integer) animator.getAnimatedValue()).intValue());
        calendarDetailDayRow.dayText.setTextColor(((Integer) animator.getAnimatedValue()).intValue());
    }
}
