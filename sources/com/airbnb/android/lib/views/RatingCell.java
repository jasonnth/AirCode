package com.airbnb.android.lib.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.utils.ViewLibUtils;
import java.text.DecimalFormat;

public class RatingCell extends FrameLayout {
    @BindView
    View divider;
    @BindView
    RatingBar ratingBar;
    @BindView
    View ratingBarContainer;
    private final DecimalFormat ratingFormatter = new DecimalFormat("0.0");
    @BindView
    TextView ratingText;
    @BindView
    TextView titleTextView;

    private enum RatingStyle {
        FiveStarBar,
        TextWithSingleStar
    }

    public RatingCell(Context context) {
        super(context);
        init(null);
    }

    public RatingCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public RatingCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(C0880R.layout.view_rating_cell, this);
        ButterKnife.bind((View) this);
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, C0880R.styleable.RatingCell, 0, 0);
            if (a != null) {
                String titleText = a.getString(C0880R.styleable.RatingCell_title);
                boolean showDivder = a.getBoolean(C0880R.styleable.RatingCell_lib_showDivider, true);
                RatingStyle ratingStyle = RatingStyle.values()[a.getInt(C0880R.styleable.RatingCell_ratingStyle, RatingStyle.FiveStarBar.ordinal())];
                ViewLibUtils.setVisibleIf(this.ratingText, RatingStyle.TextWithSingleStar.equals(ratingStyle));
                ViewLibUtils.setVisibleIf(this.ratingBar, RatingStyle.FiveStarBar.equals(ratingStyle));
                setTitle(titleText);
                setDividerEnabled(showDivder);
                a.recycle();
            }
        }
    }

    public void setRating(float rating) {
        if (this.ratingBar.getVisibility() == 0) {
            this.ratingBar.setRating(rating);
        } else {
            this.ratingText.setText(this.ratingFormatter.format((double) rating));
        }
    }

    public void setDividerEnabled(boolean showDivder) {
        ViewUtils.setVisibleIf(this.divider, showDivder);
    }

    public void setTitle(String titleText) {
        this.titleTextView.setText(titleText);
    }

    public void setTitle(int titleRes) {
        this.titleTextView.setText(titleRes);
    }

    public void setHorizontalPadding(int horizontalPadding) {
        int padding = getResources().getDimensionPixelOffset(horizontalPadding);
        this.ratingBarContainer.setPadding(0, 0, padding, 0);
        this.titleTextView.setPadding(padding, 0, 0, 0);
    }
}
