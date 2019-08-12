package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.p000v4.content.res.ResourcesCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.TouchDelegate;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.components.IntegerFormatInputView.Listener;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;
import java.text.NumberFormat;

/* renamed from: com.airbnb.n2.components.InlineFormattedIntegerInputRow */
public class InlineFormattedIntegerInputRow extends LinearLayout {
    @BindView
    IntegerFormatInputView editPrice;
    private OnFocusChangeListener focusChangedlistener;
    private CharSequence hintText;
    private Listener inputListener;
    private final Listener inputListenerWrapper;
    private boolean removeHintOnFocus;
    private boolean showingError;
    @BindView
    AirTextView subTitleText;
    @BindView
    AirTextView tip;
    private Integer tipAmount;
    private OnClickListener tipClickListener;
    private final OnClickListener tipClickListenerWrapper;
    @BindView
    AirTextView titleText;

    public InlineFormattedIntegerInputRow(Context context) {
        super(context);
        this.tipClickListenerWrapper = InlineFormattedIntegerInputRow$$Lambda$1.lambdaFactory$(this);
        this.inputListenerWrapper = InlineFormattedIntegerInputRow$$Lambda$2.lambdaFactory$(this);
        init(null);
    }

    public InlineFormattedIntegerInputRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.tipClickListenerWrapper = InlineFormattedIntegerInputRow$$Lambda$3.lambdaFactory$(this);
        this.inputListenerWrapper = InlineFormattedIntegerInputRow$$Lambda$4.lambdaFactory$(this);
        init(attrs);
    }

    public InlineFormattedIntegerInputRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.tipClickListenerWrapper = InlineFormattedIntegerInputRow$$Lambda$5.lambdaFactory$(this);
        this.inputListenerWrapper = InlineFormattedIntegerInputRow$$Lambda$6.lambdaFactory$(this);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_inline_formatted_integer_input_row, this);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
        setOrientation(1);
        this.editPrice.setOnFocusChangeListener(InlineFormattedIntegerInputRow$$Lambda$7.lambdaFactory$(this));
        this.editPrice.setInputListener(this.inputListenerWrapper);
        this.tip.setOnClickListener(this.tipClickListenerWrapper);
    }

    static /* synthetic */ void lambda$init$0(InlineFormattedIntegerInputRow inlineFormattedIntegerInputRow, View v, boolean hasFocus) {
        if (inlineFormattedIntegerInputRow.removeHintOnFocus) {
            inlineFormattedIntegerInputRow.editPrice.setHint(hasFocus ? "" : inlineFormattedIntegerInputRow.hintText);
        }
        if (inlineFormattedIntegerInputRow.focusChangedlistener != null) {
            inlineFormattedIntegerInputRow.focusChangedlistener.onFocusChange(v, hasFocus);
        }
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_InlineFormattedIntegerInputRow, 0, 0);
        String titleText2 = a.getString(R.styleable.n2_InlineFormattedIntegerInputRow_n2_titleText);
        this.hintText = a.getString(R.styleable.n2_InlineFormattedIntegerInputRow_n2_hintText);
        Integer inputAmount = null;
        if (a.hasValue(R.styleable.n2_InlineFormattedIntegerInputRow_n2_inputAmount)) {
            inputAmount = Integer.valueOf(a.getInt(R.styleable.n2_InlineFormattedIntegerInputRow_n2_inputAmount, 0));
        }
        boolean removeHintOnFocus2 = a.getBoolean(R.styleable.n2_InlineFormattedIntegerInputRow_n2_removeHintOnFocus, false);
        setTitle(titleText2);
        setHint(this.hintText);
        setAmount(inputAmount);
        setRemoveHintOnFocus(removeHintOnFocus2);
        a.recycle();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        setTouchDelegate(new TouchDelegate(new Rect(0, 0, getWidth(), getHeight()), this.editPrice));
    }

    public void setTitle(CharSequence text) {
        this.titleText.setText(text);
    }

    public void setSubTitleText(CharSequence text) {
        ViewLibUtils.setVisibleIf(this.subTitleText, !TextUtils.isEmpty(text));
        this.subTitleText.setText(text);
    }

    public void setHint(CharSequence text) {
        this.hintText = text;
        this.editPrice.setHint(text);
    }

    public void setNumberFormat(NumberFormat format) {
        this.editPrice.setNumberFormat(format);
    }

    public void setAmount(Integer inputAmount) {
        this.editPrice.setValue(inputAmount);
    }

    public Integer getAmount() {
        return this.editPrice.getValue();
    }

    public void setInputListener(Listener listener) {
        this.inputListener = listener;
    }

    public void setTip(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.tip, text);
    }

    public void setOnTipClickListener(OnClickListener tipClickListener2) {
        this.tipClickListener = tipClickListener2;
    }

    public void setTipAmount(Integer tipAmount2) {
        this.tipAmount = tipAmount2;
    }

    public void setRemoveHintOnFocus(boolean enabled) {
        this.removeHintOnFocus = enabled;
    }

    public void setOnFocusChangeListener(OnFocusChangeListener focusChangedlistener2) {
        this.focusChangedlistener = focusChangedlistener2;
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        this.titleText.setEnabled(enabled);
        this.subTitleText.setEnabled(enabled);
        this.tip.setEnabled(enabled);
        this.editPrice.setEnabled(enabled);
        this.editPrice.setFocusable(enabled);
        this.editPrice.setFocusableInTouchMode(enabled);
        updateEditTextCompoundDrawable();
    }

    public void showError(boolean showError) {
        if (this.showingError != showError) {
            this.showingError = showError;
            int colorRes = showError ? R.color.n2_arches : R.color.n2_hof;
            this.titleText.setTextColor(ResourcesCompat.getColor(getResources(), colorRes, null));
            this.editPrice.setTextColor(ResourcesCompat.getColor(getResources(), colorRes, null));
            updateEditTextCompoundDrawable();
        }
    }

    private void updateEditTextCompoundDrawable() {
        int compoundDrawable = 0;
        if (isEnabled()) {
            compoundDrawable = this.showingError ? R.drawable.n2_inline_input_error_background_drawable : R.drawable.n2_inline_input_background_drawable;
        }
        this.editPrice.setCompoundDrawablesWithIntrinsicBounds(0, 0, compoundDrawable, 0);
    }

    static /* synthetic */ void lambda$new$1(InlineFormattedIntegerInputRow inlineFormattedIntegerInputRow, View v) {
        inlineFormattedIntegerInputRow.editPrice.setValue(inlineFormattedIntegerInputRow.tipAmount);
        inlineFormattedIntegerInputRow.editPrice.setSelection(inlineFormattedIntegerInputRow.editPrice.length());
        if (inlineFormattedIntegerInputRow.tipClickListener != null) {
            inlineFormattedIntegerInputRow.tipClickListener.onClick(v);
        }
    }

    static /* synthetic */ void lambda$new$2(InlineFormattedIntegerInputRow inlineFormattedIntegerInputRow, Integer inputAmount) {
        if (inlineFormattedIntegerInputRow.showingError) {
            inlineFormattedIntegerInputRow.showError(false);
        }
        if (inlineFormattedIntegerInputRow.inputListener != null) {
            inlineFormattedIntegerInputRow.inputListener.amountChanged(inputAmount);
        }
    }

    public static void mock(InlineFormattedIntegerInputRow row) {
        row.setTitle("Title");
        row.setSubTitleText("Subtitle");
        row.setAmount(Integer.valueOf(42));
    }
}
