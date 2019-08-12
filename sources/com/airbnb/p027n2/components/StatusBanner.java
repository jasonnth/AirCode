package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.p000v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.StatusBanner */
public class StatusBanner extends LinearLayout {
    @BindView
    AirTextView statusLeft;
    @BindView
    AirTextView statusRight;

    public StatusBanner(Context context) {
        super(context);
        init(null);
    }

    public StatusBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public StatusBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_status_banner, this);
        setOrientation(1);
        ButterKnife.bind((View) this);
        initAttributes(attrs);
    }

    public void setLeftStatus(CharSequence status) {
        this.statusLeft.setText(status);
    }

    public void setRightStatus(CharSequence status) {
        this.statusRight.setText(status);
    }

    public void setLeftStatusColor(int color) {
        this.statusLeft.setTextColor(color);
    }

    public void setRightStatusColor(int color) {
        this.statusRight.setTextColor(color);
    }

    private void initAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_StatusBanner, 0, 0);
        String leftText = a.getString(R.styleable.n2_StatusBanner_n2_leftStatusText);
        String rightText = a.getString(R.styleable.n2_StatusBanner_n2_rightStatusText);
        int leftColor = a.getColor(R.styleable.n2_StatusBanner_n2_leftStatusColor, ContextCompat.getColor(getContext(), R.color.n2_text_color_main));
        int rightColor = a.getColor(R.styleable.n2_StatusBanner_n2_rightStatusColor, ContextCompat.getColor(getContext(), R.color.n2_text_color_main));
        a.recycle();
        setLeftStatus(leftText);
        setRightStatus(rightText);
        setLeftStatusColor(leftColor);
        setRightStatusColor(rightColor);
    }

    public static void mock(StatusBanner view) {
        view.setRightStatus("Right status");
        view.setLeftStatus("Left status");
    }
}
