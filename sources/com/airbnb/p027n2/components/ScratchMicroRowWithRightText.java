package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.ScratchMicroRowWithRightText */
public class ScratchMicroRowWithRightText extends LinearLayout implements DividerView {
    @BindView
    View divider;
    @BindView
    AirTextView textView;
    @BindView
    AirTextView titleView;

    public ScratchMicroRowWithRightText(Context context) {
        super(context);
        init(null);
    }

    public ScratchMicroRowWithRightText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ScratchMicroRowWithRightText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_scratch_micro_row_with_right_text, this);
        ButterKnife.bind((View) this);
        setOrientation(1);
        setupAttributes(attrs);
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_ScratchMicroRowWithRightText, 0, 0);
        String text = a.getString(R.styleable.n2_ScratchMicroRowWithRightText_n2_text);
        String title = a.getString(R.styleable.n2_ScratchMicroRowWithRightText_n2_titleText);
        boolean showDivider = a.getBoolean(R.styleable.n2_ScratchMicroRowWithRightText_n2_showDivider, true);
        setText((CharSequence) text);
        setTitle((CharSequence) title);
        showDivider(showDivider);
        a.recycle();
    }

    public void setText(CharSequence string) {
        this.textView.setText(string);
    }

    public void setText(int stringRes) {
        setText((CharSequence) getResources().getString(stringRes));
    }

    public void setTitle(CharSequence string) {
        this.titleView.setText(string);
    }

    public void setTitle(int stringRes) {
        setTitle((CharSequence) getResources().getString(stringRes));
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }

    public static void mockLong(ScratchMicroRowWithRightText view) {
        view.setTitle((CharSequence) "This is long long long long text wooooo party time untz untz untz go go go");
        view.setText((CharSequence) "This is also veryyyyyyyyyyyyyyyyy looooooooonng text text text");
    }

    public static void mockLongAndShort(ScratchMicroRowWithRightText view) {
        view.setTitle((CharSequence) "This is long long long long text wooooo party time untz untz untz go go go");
        view.setText((CharSequence) "short text");
    }

    public static void mockShortAndLong(ScratchMicroRowWithRightText view) {
        view.setTitle((CharSequence) "Short");
        view.setText((CharSequence) "This is long long long long text wooooo party time untz untz untz go go go");
    }

    public static void mockShort(ScratchMicroRowWithRightText view) {
        view.setTitle((CharSequence) "Two short");
        view.setText((CharSequence) "texts");
    }
}
