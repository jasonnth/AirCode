package com.airbnb.android.core.views.calendar;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.CoordinatorLayout.LayoutParams;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.view.animation.FastOutSlowInInterpolator;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewStub;
import android.view.animation.Interpolator;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.calendar.AvailabilityController;
import com.airbnb.android.core.calendar.AvailabilityController.UnavailabilityType;
import com.airbnb.android.core.controllers.CalendarViewCallbacks;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.viewcomponents.models.RangeDisplayEpoxyModel_;
import com.airbnb.android.core.views.calendar.MonthView.MonthViewStyle;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.components.CalendarBubblePopUp;
import com.airbnb.p027n2.components.RangeDisplay;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import com.airbnb.p027n2.utils.ViewLibUtils;

public class CalendarView extends CoordinatorLayout implements VerticalCalendarCallbacks {
    private boolean allowEmptyDates;
    private boolean allowSingleDateSelection;
    private AvailabilityController availabilityController;
    @BindView
    ViewStub bottomBar;
    @BindView
    VerticalCalendarView calendarView;
    private CalendarViewCallbacks controller;
    @BindString
    String dateFormat;
    @BindString
    String dayOfWeekFormat;
    private int elevation;
    private AirDate endDate;
    @BindString
    String endDateTitleString;
    private boolean formatWithYear;
    @BindView
    AirTextView fridayTextView;
    private AdditionalUnavailabilityInfoProvider informationProvider;
    @BindView
    JellyfishView jellyfishView;
    private DateRangeChangeListener listener;
    @BindView
    AirTextView mondayTextView;
    private CalendarBubblePopUp popUp;
    private PopUpAnimationManager popUpAnimationManager;
    private int popUpHorizontalMinimumPadding;
    private int popUpPointerPadding;
    private int popUpWidth;
    @BindView
    View progressView;
    @BindView
    RangeDisplay rangeDisplay;
    @BindView
    AirTextView saturdayTextView;
    /* access modifiers changed from: private */
    public CalendarBottomBarInterfaceWrapper saveButton;
    private boolean singleDayMode;
    @BindView
    AirTextView singleDayText;
    SnackbarWrapper snackbar;
    private AirDate startDate;
    @BindString
    String startDateTitleString;
    @BindView
    AirTextView sundayTextView;
    @BindView
    AirTextView thursdayTextView;
    @BindView
    AirTextView tuesdayTextView;
    @BindView
    AirTextView wednesdayTextView;
    @BindView
    View weekDaysDivider;

    public interface AdditionalUnavailabilityInfoProvider {
        String getListingHostName();
    }

    public interface DateRangeChangeListener {
        void onDateRangeChanged(DateRangeModel dateRangeModel);
    }

    private class PopUpAnimationManager {
        private static final int ANIMATION_DURATION = 300;
        private final ObjectAnimator alphaAnimator;
        private final CalendarBubblePopUp calendarBubblePopUp;
        private final Interpolator interpolator = new FastOutSlowInInterpolator();
        private final ObjectAnimator scaleXAnimator;
        private final ObjectAnimator scaleYAnimator;

        PopUpAnimationManager(CalendarBubblePopUp calendarBubblePopUp2) {
            this.calendarBubblePopUp = calendarBubblePopUp2;
            this.scaleXAnimator = ObjectAnimator.ofFloat(calendarBubblePopUp2, View.SCALE_X, new float[]{0.8f, 1.0f});
            this.scaleYAnimator = ObjectAnimator.ofFloat(calendarBubblePopUp2, View.SCALE_Y, new float[]{0.8f, 1.0f});
            this.alphaAnimator = ObjectAnimator.ofFloat(calendarBubblePopUp2, View.ALPHA, new float[]{0.0f, 1.0f});
            this.scaleXAnimator.setDuration(300);
            this.scaleYAnimator.setDuration(300);
            this.alphaAnimator.setDuration(300);
            this.scaleYAnimator.setInterpolator(this.interpolator);
            this.scaleYAnimator.setInterpolator(this.interpolator);
            this.alphaAnimator.setInterpolator(this.interpolator);
        }

        /* access modifiers changed from: 0000 */
        public void animateShow() {
            this.calendarBubblePopUp.setEnabled(true);
            this.scaleXAnimator.start();
            this.scaleYAnimator.start();
            this.alphaAnimator.start();
        }

        /* access modifiers changed from: 0000 */
        public void animateDismiss() {
            this.calendarBubblePopUp.setEnabled(false);
            this.scaleXAnimator.reverse();
            this.scaleYAnimator.reverse();
            this.alphaAnimator.reverse();
        }
    }

    public enum Style {
        WHITE(false, false, C0716R.C0719style.WeekDaysStripView_Inverse, C0716R.color.n2_divider_color, MonthViewStyle.WHITE, C0716R.layout.calendar_bottom_bar_inset_button),
        WHITE_NEW(false, false, C0716R.C0719style.WeekDaysStripView_Inverse, C0716R.color.n2_divider_color, MonthViewStyle.WHITE_NEW, C0716R.layout.calendar_bottom_bar_button_with_text),
        BABU(true, true, C0716R.C0719style.WeekDaysStripView, C0716R.color.n2_white_10, MonthViewStyle.BABU, C0716R.layout.calendar_bottom_bar_inset_button),
        BABU_NEW(true, true, C0716R.C0719style.WeekDaysStripView, C0716R.color.n2_white_10, MonthViewStyle.BABU_NEW, C0716R.layout.calendar_bottom_bar_button_with_text);
        
        final int bottomBarLayout;
        final int dividerColor;
        final boolean hasJellyFish;
        final boolean invertRangeDisplayColors;
        final MonthViewStyle monthViewStyle;
        final int weekdayStyle;

        private Style(boolean hasJellyFish2, boolean invertRangeDisplayColors2, int weekdayStyle2, int dividerColor2, MonthViewStyle monthViewStyle2, int bottomBarLayout2) {
            this.hasJellyFish = hasJellyFish2;
            this.invertRangeDisplayColors = invertRangeDisplayColors2;
            this.weekdayStyle = weekdayStyle2;
            this.dividerColor = dividerColor2;
            this.monthViewStyle = monthViewStyle2;
            this.bottomBarLayout = bottomBarLayout2;
        }

        public void setStyle(CalendarView calendarView) {
            Context context = calendarView.getContext();
            ViewUtils.setVisibleIf((View) calendarView.jellyfishView, this.hasJellyFish);
            calendarView.rangeDisplay.invertColors(this.invertRangeDisplayColors);
            calendarView.sundayTextView.setTextAppearance(context, this.weekdayStyle);
            calendarView.mondayTextView.setTextAppearance(context, this.weekdayStyle);
            calendarView.tuesdayTextView.setTextAppearance(context, this.weekdayStyle);
            calendarView.wednesdayTextView.setTextAppearance(context, this.weekdayStyle);
            calendarView.thursdayTextView.setTextAppearance(context, this.weekdayStyle);
            calendarView.fridayTextView.setTextAppearance(context, this.weekdayStyle);
            calendarView.saturdayTextView.setTextAppearance(context, this.weekdayStyle);
            calendarView.weekDaysDivider.setBackgroundColor(ContextCompat.getColor(context, this.dividerColor));
            calendarView.saveButton.setStyle(this);
        }
    }

    public CalendarView(Context context) {
        super(context);
        init(null);
    }

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(C0716R.layout.calendar_view, this);
        ButterKnife.bind((View) this);
        TypedArray a = getContext().obtainStyledAttributes(attrs, C0716R.styleable.CalendarView);
        this.allowSingleDateSelection = a.getBoolean(C0716R.styleable.CalendarView_allowSingleDaySelection, false);
        a.recycle();
        this.calendarView.setupCalendar(this, MonthViewStyle.BABU);
        this.calendarView.scrollToSelectedMonth(this.startDate);
    }

    private void initPopUp() {
        this.popUp = new CalendarBubblePopUp(getContext());
        this.popUp.setAlpha(0.0f);
        addView(this.popUp);
        this.popUpAnimationManager = new PopUpAnimationManager(this.popUp);
        this.popUp.setCloseIconOnClickListener(CalendarView$$Lambda$1.lambdaFactory$(this));
        Resources r = getResources();
        this.popUpWidth = r.getDimensionPixelSize(C0716R.dimen.n2_calendar_pop_up_width);
        this.popUpHorizontalMinimumPadding = r.getDimensionPixelSize(C0716R.dimen.calendar_pop_up_min_padding);
        this.popUpPointerPadding = r.getDimensionPixelSize(C0716R.dimen.calendar_pop_up_pointer_top_padding);
        this.elevation = r.getDimensionPixelSize(C0716R.dimen.calendar_pop_up_elevation);
    }

    static /* synthetic */ void lambda$initPopUp$0(CalendarView calendarView2, View view) {
        calendarView2.calendarView.setSelectedState(calendarView2.startDate, calendarView2.endDate, false);
        calendarView2.popUpAnimationManager.animateDismiss();
        calendarView2.calendarView.setEnabled(true);
    }

    public void setup(CalendarViewCallbacks controller2, AirDate startDate2, AirDate endDate2, int startDateTitleRes, int endDateTitleRes, int savebuttonRes, boolean allowEmptyDates2, boolean formatWithYear2, Style style) {
        this.controller = controller2;
        this.allowEmptyDates = allowEmptyDates2;
        this.formatWithYear = formatWithYear2;
        this.bottomBar.setLayoutResource(style.bottomBarLayout);
        this.saveButton = new CalendarBottomBarInterfaceWrapper(this.bottomBar.inflate());
        this.saveButton.setOnClickListener(CalendarView$$Lambda$2.lambdaFactory$(this));
        if (startDateTitleRes != 0) {
            this.startDateTitleString = getResources().getString(startDateTitleRes);
        }
        if (endDateTitleRes != 0) {
            this.endDateTitleString = getResources().getString(endDateTitleRes);
        }
        if (savebuttonRes != 0) {
            this.saveButton.setText(savebuttonRes);
        }
        style.setStyle(this);
        this.calendarView.setupCalendar(this, style.monthViewStyle);
        this.calendarView.setSelectedState(startDate2, endDate2, true);
    }

    public void setup(CalendarViewCallbacks controller2, AirDate startDate2, AirDate endDate2, int startDateTitleRes, int endDateTitleRes, Style style) {
        this.allowSingleDateSelection = false;
        setup(controller2, startDate2, endDate2, startDateTitleRes, endDateTitleRes, 0, true, false, style);
    }

    public void forSingleDay() {
        this.singleDayMode = true;
        this.allowSingleDateSelection = true;
        this.rangeDisplay.setVisibility(8);
        this.singleDayText.setVisibility(0);
        this.calendarView.enableSingleDayMode();
        this.calendarView.setSelectedState(this.startDate, null, true);
    }

    public void showError(String errorMessage) {
        this.snackbar = new SnackbarWrapper().view(this).title(errorMessage, true).duration(0);
        this.snackbar.buildAndShow();
    }

    public void showPopUp(String popUpMessage, int[] dateLocation) {
        if (this.popUp == null) {
            initPopUp();
        }
        this.popUp.setText(popUpMessage);
        int leftMargin = Math.min((ViewLibUtils.getScreenWidth(getContext()) - this.popUpHorizontalMinimumPadding) - this.popUpWidth, Math.max(this.popUpHorizontalMinimumPadding, dateLocation[0] - (this.popUpWidth / 2)));
        this.popUp.setPointerPosition(dateLocation[0] - leftMargin);
        setPopUpElevationIfPossible(this.popUp);
        LayoutParams params = (LayoutParams) this.popUp.getLayoutParams();
        this.popUp.measure(MeasureSpec.makeMeasureSpec(this.popUpWidth, 1073741824), -2);
        this.popUp.setPivotX((float) (dateLocation[0] - leftMargin));
        this.popUp.setPivotY((float) this.popUp.getMeasuredHeight());
        params.setMargins(leftMargin, (dateLocation[1] - this.popUp.getMeasuredHeight()) - this.popUpPointerPadding, 0, 0);
        this.popUpAnimationManager.animateShow();
        this.calendarView.setEnabled(false);
    }

    public boolean onUnavailableDateClicked(UnavailabilityType type, AirDate date, int[] dayLocation) {
        if (type != UnavailabilityType.DoesntSatisfyMinNights && type != UnavailabilityType.CantSatisfyMinNights) {
            return false;
        }
        String error = this.availabilityController.getMessageForUnavailability(type, date, this.informationProvider.getListingHostName());
        if (TextUtils.isEmpty(error)) {
            return false;
        }
        showError(error);
        return true;
    }

    public void onStartDateClicked(AirDate start) {
        this.controller.onStartDateClicked(start);
    }

    public void onEndDateClicked(AirDate end) {
        this.controller.onEndDateClicked(end);
    }

    @TargetApi(21)
    private void setPopUpElevationIfPossible(CalendarBubblePopUp popUp2) {
        if (ViewLibUtils.isAtLeastLollipop()) {
            popUp2.setElevation((float) this.elevation);
        }
    }

    public void onDateRangeSelected(DateRangeModel days) {
        boolean saveEnabled;
        String titleFormat;
        String subtitleFormat;
        String str;
        String str2;
        String str3 = null;
        if (shouldDismissPopUp(days)) {
            dismissPopUpIfPossible();
        }
        this.startDate = days.getCheckInDate();
        this.endDate = days.getCheckOutDate();
        if (!(this.allowEmptyDates && this.startDate == null && this.endDate == null) && ((!this.allowSingleDateSelection || this.startDate == null) && (this.startDate == null || this.endDate == null))) {
            saveEnabled = false;
        } else {
            saveEnabled = true;
        }
        this.saveButton.setEnabled(saveEnabled);
        if (this.singleDayMode) {
            AirTextView airTextView = this.singleDayText;
            if (this.startDate != null) {
                str2 = this.startDate.formatDate(this.dateFormat);
            } else {
                str2 = "";
            }
            airTextView.setText(str2);
        } else {
            if (this.formatWithYear) {
                titleFormat = getContext().getString(C0716R.string.date_name_format_trailing_comma);
            } else {
                titleFormat = getContext().getString(C0716R.string.day_of_week_format);
            }
            if (this.formatWithYear) {
                subtitleFormat = getContext().getString(C0716R.string.full_year_format);
            } else {
                subtitleFormat = getContext().getString(C0716R.string.date_name_format);
            }
            RangeDisplayEpoxyModel_ startTitle = new RangeDisplayEpoxyModel_().startDate(this.startDate).endDate(this.endDate).startTitle(this.startDate != null ? this.startDate.formatDate(titleFormat) : this.startDateTitleString);
            if (this.startDate != null) {
                str = this.startDate.formatDate(subtitleFormat);
            } else {
                str = null;
            }
            RangeDisplayEpoxyModel_ endTitle = startTitle.startSubtitle(str).endTitle(this.endDate != null ? this.endDate.formatDate(titleFormat) : this.endDateTitleString);
            if (this.endDate != null) {
                str3 = this.endDate.formatDate(subtitleFormat);
            }
            endTitle.endSubtitle(str3).showDivider(false).bind(this.rangeDisplay);
        }
        if (this.listener != null) {
            this.listener.onDateRangeChanged(days);
        }
    }

    public void onDetachedFromWindow() {
        if (this.snackbar != null && this.snackbar.isShown()) {
            this.snackbar.dismiss();
            this.snackbar = null;
        }
        super.onDetachedFromWindow();
    }

    public void onApplyClicked() {
        String listingHostName;
        if (!(this.endDate == null || this.startDate == null || this.availabilityController == null)) {
            AvailabilityController availabilityController2 = this.availabilityController;
            AirDate airDate = this.startDate;
            AirDate airDate2 = this.endDate;
            if (this.informationProvider == null) {
                listingHostName = null;
            } else {
                listingHostName = this.informationProvider.getListingHostName();
            }
            String errorMessage = availabilityController2.getUnavailabilityMessageIfNotValid(airDate, airDate2, listingHostName);
            if (!TextUtils.isEmpty(errorMessage)) {
                showError(errorMessage);
                return;
            }
        }
        if (this.endDate == null && this.startDate == null && !this.allowEmptyDates) {
            showError(getResources().getString(C0716R.string.calendar_required_dates));
        } else {
            this.controller.onCalendarDatesApplied(this.startDate, this.endDate);
        }
    }

    public void setAvailabilityController(AvailabilityController controller2) {
        this.availabilityController = (AvailabilityController) Check.notNull(controller2);
        ((VerticalCalendarAdapter) this.calendarView.getAdapter()).setAvailabilityController(controller2);
    }

    public void clearSelectedDates() {
        this.calendarView.setSelectedState(null, null, false);
        dismissPopUpIfPossible();
    }

    public void showProgress() {
        this.progressView.setVisibility(0);
        this.calendarView.setVisibility(8);
    }

    public void hidePogress() {
        this.progressView.setVisibility(8);
        this.calendarView.setVisibility(0);
        this.calendarView.scrollToSelectedMonth(this.startDate);
    }

    public void setDateRangeChangeListener(DateRangeChangeListener listener2) {
        this.listener = listener2;
    }

    public void setAdditionalUnavailabilityInformationProvider(AdditionalUnavailabilityInfoProvider informationProvider2) {
        this.informationProvider = informationProvider2;
    }

    public void setBottomBarText(String bottomBarText) {
        this.saveButton.setOptionalText(bottomBarText);
    }

    private void dismissPopUpIfPossible() {
        if (!this.calendarView.isEnabled()) {
            this.popUpAnimationManager.animateDismiss();
            this.calendarView.setEnabled(true);
        }
    }

    private boolean shouldDismissPopUp(DateRangeModel days) {
        if (days.hasntSetStartOrEnd()) {
            return false;
        }
        if (this.startDate == days.getCheckInDate() && this.endDate == days.getCheckOutDate()) {
            return false;
        }
        return true;
    }
}
