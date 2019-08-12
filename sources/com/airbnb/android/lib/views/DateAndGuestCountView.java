package com.airbnb.android.lib.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.analytics.BookItAnalytics;
import com.airbnb.android.core.analytics.BookItAnalytics.Flow;
import com.airbnb.android.core.models.SpecialOffer;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.Strap;

public class DateAndGuestCountView extends LinearLayout {
    private static final int DEFAULT_TEXT_SIZE = -1;
    private int guestCountDelayMillis = 0;
    @BindView
    GroupedCounter guestCountSelector;
    private final Handler handler = new Handler(Looper.getMainLooper());
    @BindView
    GroupedDates mGroupedDates;
    private boolean mIsBookItFlow;
    private Callbacks mViewer;

    public interface Callbacks {
        Flow getFlow();

        void setGuestCount(int i);

        void showCalendarDialog(DateAndGuestCountView dateAndGuestCountView);

        void showDialogIfClickWithSpecialOffer();
    }

    public static abstract class SimpleCallbacks implements Callbacks {
        public void showCalendarDialog(DateAndGuestCountView dateAndGuestCountView) {
        }

        public void setGuestCount(int guestCount) {
        }

        public Flow getFlow() {
            return null;
        }

        public void showDialogIfClickWithSpecialOffer() {
        }
    }

    public DateAndGuestCountView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ((AirbnbGraph) AirbnbApplication.instance(context).component()).inject(this);
        LayoutInflater.from(context).inflate(C0880R.layout.date_and_guest_count_view, this);
        ButterKnife.bind((View) this);
        initAttributedSet(context, attrs);
    }

    private void initAttributedSet(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, C0880R.styleable.DateAndGuestCountView, 0, 0);
            float textSize = (float) a.getDimensionPixelSize(C0880R.styleable.DateAndGuestCountView_textSize, -1);
            a.recycle();
            if (textSize > 0.0f) {
                this.mGroupedDates.setTextViewsTextSize(0, textSize);
                this.guestCountSelector.setTextSize(0, textSize);
            }
        }
    }

    public void initForSearchFilter(Callbacks viewer, int guestCount, AirDate checkIn, AirDate checkOut, boolean noBottomMargin) {
        init(viewer, guestCount, getResources().getInteger(C0880R.integer.max_num_guests), checkIn, checkOut, null, false);
        this.mGroupedDates.setHideNightsCount(true);
        if (noBottomMargin) {
            ((LayoutParams) this.mGroupedDates.getLayoutParams()).bottomMargin = 0;
        }
        this.guestCountSelector.setVisibility(8);
        this.mGroupedDates.setTitles(C0880R.string.check_in, C0880R.string.check_out);
        this.guestCountSelector.setTextAppearance(C0880R.C0885style.GroupedCounterRoundSearchFilters);
        ButterKnife.findById((View) this, C0880R.C0882id.p4_guests_section_header).setVisibility(8);
    }

    public void init(Callbacks viewer, int guestCount, int maxGuestCount, AirDate checkIn, AirDate checkOut, SpecialOffer optionalSpecialOffer, boolean showSectionHeaders) {
        boolean isSpecialOffer;
        boolean z;
        boolean z2;
        boolean z3 = true;
        this.mViewer = viewer;
        setCheckInDate(checkIn);
        setCheckOutDate(checkOut);
        if (optionalSpecialOffer != null) {
            isSpecialOffer = true;
        } else {
            isSpecialOffer = false;
        }
        if (this.mViewer.getFlow() == Flow.BOOK_IT) {
            z = true;
        } else {
            z = false;
        }
        this.mIsBookItFlow = z;
        this.mGroupedDates.setOnClickListener(DateAndGuestCountView$$Lambda$1.lambdaFactory$(this, isSpecialOffer));
        boolean enableGuest = guestCount > -1 && maxGuestCount > -1;
        if (enableGuest) {
            if (guestCount < 1) {
                guestCount = 1;
            }
            setGuestCount(guestCount);
            this.guestCountSelector.setMaxValue(maxGuestCount);
            if (maxGuestCount < getResources().getInteger(C0880R.integer.max_num_guests)) {
                this.guestCountSelector.showPlusOnMax(false);
            }
            if (isSpecialOffer) {
                this.guestCountSelector.setUserInputEnabled(false);
                this.guestCountSelector.setOnClickListener(DateAndGuestCountView$$Lambda$2.lambdaFactory$(this));
            } else {
                this.guestCountSelector.setOnValueChangeListener(DateAndGuestCountView$$Lambda$3.lambdaFactory$(this));
            }
            this.mViewer.setGuestCount(this.guestCountSelector.getSelectedValue());
        } else {
            this.mGroupedDates.setHideNightsCount(true);
            this.guestCountSelector.setVisibility(8);
        }
        int i = C0880R.C0882id.p4_guests_section_header;
        if (!showSectionHeaders || !enableGuest) {
            z2 = true;
        } else {
            z2 = false;
        }
        MiscUtils.setGoneIf(i, this, z2);
        int i2 = C0880R.C0882id.p4_dates_section_header;
        if (showSectionHeaders) {
            z3 = false;
        }
        MiscUtils.setGoneIf(i2, this, z3);
    }

    static /* synthetic */ void lambda$init$0(DateAndGuestCountView dateAndGuestCountView, boolean isSpecialOffer, View v) {
        if (isSpecialOffer) {
            dateAndGuestCountView.mViewer.showDialogIfClickWithSpecialOffer();
            return;
        }
        dateAndGuestCountView.mViewer.showCalendarDialog(dateAndGuestCountView);
        if (dateAndGuestCountView.mIsBookItFlow) {
            BookItAnalytics.trackBookItDateSelectClick(null);
        } else {
            BookItAnalytics.trackInquiryDateSelectClick(null);
        }
    }

    static /* synthetic */ void lambda$init$3(DateAndGuestCountView dateAndGuestCountView, BaseCounter groupedCounter, int newVal) {
        if (dateAndGuestCountView.guestCountDelayMillis > 0) {
            dateAndGuestCountView.handler.removeCallbacksAndMessages(null);
            dateAndGuestCountView.handler.postDelayed(DateAndGuestCountView$$Lambda$4.lambdaFactory$(dateAndGuestCountView, newVal), (long) dateAndGuestCountView.guestCountDelayMillis);
            return;
        }
        dateAndGuestCountView.doGuestCountOnValueChanged(newVal);
    }

    public void setGuestCountDelayMillis(int millis) {
        this.guestCountDelayMillis = millis;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.handler.removeCallbacksAndMessages(null);
    }

    /* access modifiers changed from: private */
    public void doGuestCountOnValueChanged(int newVal) {
        this.mViewer.setGuestCount(newVal);
        Strap params = Strap.make().mo11639kv(BookItAnalytics.PARAM_GUEST_COUNT, Integer.toString(newVal));
        if (this.mIsBookItFlow) {
            BookItAnalytics.trackBookItGuestSelectClick(params);
        } else {
            BookItAnalytics.trackInquiryGuestSelectClick(params);
        }
    }

    public void setGuestCount(int guestCount) {
        this.guestCountSelector.setSelectedValue(guestCount);
    }

    public void setCheckInDate(AirDate checkInDate) {
        this.mGroupedDates.setCheckInDate(checkInDate);
    }

    public void setCheckOutDate(AirDate checkOutDate) {
        this.mGroupedDates.setCheckOutDate(checkOutDate);
    }

    public boolean hasDates() {
        return (this.mGroupedDates.getCheckInDate() == null || this.mGroupedDates.getCheckOutDate() == null) ? false : true;
    }

    public GroupedDates getGroupedDates() {
        return this.mGroupedDates;
    }

    public GroupedCounter getGroupedGuestCounter() {
        return this.guestCountSelector;
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        this.guestCountSelector.setUserInputEnabled(enabled);
        this.mGroupedDates.setEnabled(enabled);
    }
}
