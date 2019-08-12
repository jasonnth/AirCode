package com.airbnb.android.explore.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.explore.C0857R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

public class MTSearchInputField extends LinearLayout {
    @BindView
    AirImageView icon;
    @BindView
    AirTextView textView;

    public MTSearchInputField(Context context) {
        this(context, null);
    }

    public MTSearchInputField(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MTSearchInputField(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, C0857R.layout.trips_search_input_field, this);
        ButterKnife.bind((View) this);
        TypedArray a = getContext().obtainStyledAttributes(attrs, C0857R.styleable.MTSearchInputField);
        Drawable iconDrawable = ViewLibUtils.getDrawableCompat(getContext(), a, C0857R.styleable.MTSearchInputField_icon);
        a.recycle();
        setBackgroundResource(C0857R.C0858drawable.search_rounded_bg);
        setOrientation(0);
        int padding = getResources().getDimensionPixelSize(C0857R.dimen.explore_marquee_inside_padding);
        setPadding(padding, padding, padding, padding);
        this.icon.setImageDrawable(iconDrawable);
    }

    public void setTitle(CharSequence title) {
        this.textView.setText(title);
    }

    public AirTextView getTitleTextView() {
        return this.textView;
    }

    public AirImageView getIcon() {
        return this.icon;
    }

    public Drawable getBackgroundDrawable() {
        return getBackground();
    }
}
