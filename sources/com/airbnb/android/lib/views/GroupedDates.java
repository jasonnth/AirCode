package com.airbnb.android.lib.views;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.utils.DateHelper;
import com.airbnb.android.lib.C0880R;
import icepick.State;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class GroupedDates extends LinearLayout {
    private static final int DEFAULT_TEXT_SIZE = -1;
    @BindView
    TextView checkInTitle;
    @BindView
    TextView checkOutTitle;
    @State
    protected AirDate mCheckInDate;
    @BindView
    TextView mCheckInText;
    @State
    protected AirDate mCheckOutDate;
    @BindView
    TextView mCheckOutText;
    private SimpleDateFormat mDateFormat;
    private boolean mHideNightsCount;
    @BindView
    TextView mNumNightsText;

    public GroupedDates(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public GroupedDates(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public GroupedDates(Context context) {
        this(context, null);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        LinearLayout content = (LinearLayout) LayoutInflater.from(context).inflate(C0880R.layout.grouped_dates, this, true);
        content.setOrientation(1);
        ButterKnife.bind(this, content);
        setLayoutTransition(new LayoutTransition());
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, C0880R.styleable.GroupedDates, 0, 0);
            this.mHideNightsCount = a.getBoolean(C0880R.styleable.GroupedDates_hideNightsCount, false);
            float textSize = (float) a.getDimensionPixelSize(C0880R.styleable.GroupedDates_textSize, -1);
            a.recycle();
            if (this.mHideNightsCount) {
                this.mNumNightsText.setVisibility(8);
            }
            if (textSize > 0.0f) {
                setTextViewsTextSize(0, textSize);
            }
        }
        this.mDateFormat = new SimpleDateFormat(getResources().getString(C0880R.string.md_with_abbr_day_name), getResources().getConfiguration().locale);
    }

    public Parcelable onSaveInstanceState() {
        return IcepickWrapper.saveInstanceState(this, super.onSaveInstanceState());
    }

    public void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(IcepickWrapper.restoreInstanceState(this, state));
        setCheckInDate(this.mCheckInDate);
        setCheckOutDate(this.mCheckOutDate);
    }

    public AirDate getCheckInDate() {
        return this.mCheckInDate;
    }

    public void setCheckInDate(AirDate checkInDate) {
        this.mCheckInDate = checkInDate;
        if (this.mCheckInDate != null) {
            this.mCheckInText.setText(this.mCheckInDate.formatDate((DateFormat) this.mDateFormat));
        } else {
            this.mCheckInText.setText(C0880R.string.select_date);
        }
        updateNumNightsText();
    }

    public AirDate getCheckOutDate() {
        return this.mCheckOutDate;
    }

    public void setCheckOutDate(AirDate checkOutDate) {
        this.mCheckOutDate = checkOutDate;
        if (this.mCheckOutDate != null) {
            this.mCheckOutText.setText(this.mCheckOutDate.formatDate((DateFormat) this.mDateFormat));
        } else {
            this.mCheckOutText.setText(C0880R.string.select_date);
        }
        updateNumNightsText();
    }

    public void setHideNightsCount(boolean hideNightsCount) {
        this.mHideNightsCount = hideNightsCount;
        updateNumNightsText();
    }

    private void updateNumNightsText() {
        if (this.mCheckInDate == null || this.mCheckOutDate == null || this.mHideNightsCount) {
            this.mNumNightsText.setVisibility(8);
            return;
        }
        int numNights = DateHelper.getStayDuration(this.mCheckInDate, this.mCheckOutDate);
        this.mNumNightsText.setText(getResources().getQuantityString(C0880R.plurals.x_nights_total, numNights, new Object[]{Integer.valueOf(numNights)}));
        this.mNumNightsText.setVisibility(0);
    }

    public void setDatesTextColor(int colorResId) {
        int color = getResources().getColor(colorResId);
        this.mCheckInText.setTextColor(color);
        this.mCheckOutText.setTextColor(color);
    }

    public void setTextViewsTextSize(int unit, float textSize) {
        this.checkInTitle.setTextSize(unit, textSize);
        this.checkOutTitle.setTextSize(unit, textSize);
        this.mCheckInText.setTextSize(unit, textSize);
        this.mCheckOutText.setTextSize(unit, textSize);
    }

    public void setTitles(int checkInRes, int checkOutRes) {
        this.checkInTitle.setText(checkInRes);
        this.checkOutTitle.setText(checkOutRes);
    }
}
