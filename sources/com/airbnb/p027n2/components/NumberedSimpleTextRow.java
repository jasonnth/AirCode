package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.p000v4.content.ContextCompat;
import android.util.AttributeSet;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import java.util.Locale;

/* renamed from: com.airbnb.n2.components.NumberedSimpleTextRow */
public class NumberedSimpleTextRow extends BaseDividerComponent {
    @BindView
    AirTextView content;
    @BindView
    AirTextView stepNumber;

    public NumberedSimpleTextRow(Context context) {
        super(context);
    }

    public NumberedSimpleTextRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NumberedSimpleTextRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_numbered_simple_text_row;
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        super.init(attrs);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_NumberedSimpleTextRow);
        setStepNumber(Integer.valueOf(a.getInt(R.styleable.n2_NumberedSimpleTextRow_n2_stepNumber, 1)));
        setContent((CharSequence) a.getString(R.styleable.n2_NumberedSimpleTextRow_n2_contentText));
        setStepNumberColor(a.getColor(R.styleable.n2_NumberedSimpleTextRow_n2_stepColor, ContextCompat.getColor(getContext(), R.color.n2_babu)));
        showDivider(a.getBoolean(R.styleable.n2_NumberedSimpleTextRow_n2_showDivider, true));
        a.recycle();
    }

    public void setStepNumber(Integer i) {
        this.stepNumber.setText(String.format(Locale.getDefault(), "%d.", new Object[]{i}));
    }

    public void setContent(CharSequence text) {
        this.content.setText(text);
    }

    public void setContent(int stringId) {
        setContent((CharSequence) getResources().getString(stringId));
    }

    public void setStepNumberColor(int color) {
        this.stepNumber.setTextColor(color);
    }

    public void setStepNumberColorRes(int colorRes) {
        this.stepNumber.setTextColor(ContextCompat.getColor(getContext(), colorRes));
    }

    public static void mock(NumberedSimpleTextRow view) {
        view.setContent((CharSequence) "A text row with a number");
        view.showDivider(true);
    }

    public static void mockWithLongText(NumberedSimpleTextRow view) {
        view.setStepNumber(Integer.valueOf(2));
        view.setContent((CharSequence) "A simple text row with a number and some very long text to test line wrapping");
        view.showDivider(true);
    }

    public static void mockWithNoPadding(NumberedSimpleTextRow view) {
        view.setStepNumber(Integer.valueOf(3));
        view.setContent((CharSequence) "A simple text row with a number and some very long text to test line wrapping but no padding");
        view.setPaddingTop(0);
        view.showDivider(true);
    }

    public static void mockWithColoredStep(NumberedSimpleTextRow view) {
        view.setStepNumber(Integer.valueOf(4));
        view.setContent((CharSequence) "A simple text row with a colored step number");
        view.setStepNumberColorRes(R.color.n2_rausch);
        view.showDivider(true);
    }
}
