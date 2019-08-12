package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.p000v4.content.ContextCompat;
import android.support.p002v7.content.res.AppCompatResources;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirEditTextView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.PaymentInputLayout */
public class PaymentInputLayout extends LinearLayout {
    @BindView
    View divider;
    @BindView
    AirEditTextView inputText;
    @BindView
    AirImageView paymentLogo;
    @BindView
    AirTextView titleText;

    /* renamed from: com.airbnb.n2.components.PaymentInputLayout$SavedState */
    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
        int selectionEnd;
        int selectionStart;
        String text;

        SavedState(Parcelable superState) {
            super(superState);
        }

        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(this.selectionStart);
            dest.writeInt(this.selectionEnd);
            dest.writeString(this.text);
        }

        private SavedState(Parcel in) {
            super(in);
            this.selectionStart = in.readInt();
            this.selectionEnd = in.readInt();
            this.text = in.readString();
        }
    }

    public PaymentInputLayout(Context context) {
        super(context);
        init(null);
    }

    public PaymentInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public PaymentInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_payment_input_layout, this);
        setOrientation(1);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
    }

    public void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_PaymentInput, 0, 0);
        String titleString = a.getString(R.styleable.n2_PaymentInput_n2_titleText);
        String hintString = a.getString(R.styleable.n2_PaymentInput_n2_hintText);
        setTitle((CharSequence) titleString);
        setHint((CharSequence) hintString);
        a.recycle();
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState ss = new SavedState(super.onSaveInstanceState());
        ss.selectionStart = this.inputText.getSelectionStart();
        ss.selectionEnd = this.inputText.getSelectionEnd();
        ss.text = this.inputText.getText().toString();
        return ss;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        setText((CharSequence) ss.text);
        this.inputText.setSelection(ss.selectionStart, ss.selectionEnd);
    }

    /* access modifiers changed from: protected */
    public void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        super.dispatchFreezeSelfOnly(container);
    }

    /* access modifiers changed from: protected */
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        super.dispatchThawSelfOnly(container);
    }

    public void setTitle(CharSequence string) {
        this.titleText.setText(string);
    }

    public void setTitle(int stringRes) {
        setTitle((CharSequence) getResources().getString(stringRes));
    }

    public void setText(CharSequence string) {
        this.inputText.setText(string);
    }

    public void setText(int stringRes) {
        setText((CharSequence) getResources().getString(stringRes));
    }

    public Editable getText() {
        return this.inputText.getText();
    }

    public void setSelection(int index) {
        this.inputText.setSelection(index);
    }

    public void setHint(CharSequence string) {
        this.inputText.setHint(string);
    }

    public void setHint(int stringRes) {
        setHint((CharSequence) getResources().getString(stringRes));
    }

    public void addTextChangedListener(TextWatcher textWatcher) {
        this.inputText.addTextChangedListener(textWatcher);
    }

    public void setInputMaxLength(int maxLength) {
        this.inputText.setFilters(new InputFilter[]{new LengthFilter(maxLength)});
    }

    public void showError() {
        this.titleText.setTextColor(ContextCompat.getColor(getContext(), R.color.n2_arches));
        this.inputText.setTextColor(ContextCompat.getColor(getContext(), R.color.n2_arches));
    }

    public void hideError() {
        this.titleText.setTextColor(ContextCompat.getColor(getContext(), R.color.n2_text_color_main));
        this.inputText.setTextColor(ContextCompat.getColor(getContext(), R.color.n2_text_color_main));
    }

    public void makeInputTextClickableOnly(OnClickListener clickListener) {
        this.inputText.setInputType(0);
        this.inputText.setFocusable(false);
        this.inputText.setCursorVisible(false);
        this.inputText.setClickable(true);
        this.inputText.setOnClickListener(clickListener);
    }

    public void setInputTypeToText() {
        this.inputText.setInputType(96);
    }

    public void showPaymentLogo(int res) {
        this.paymentLogo.setVisibility(0);
        this.paymentLogo.setImageDrawable(AppCompatResources.getDrawable(getContext(), res));
    }

    public boolean isEmpty() {
        return this.inputText.isEmpty();
    }

    public static void mock(PaymentInputLayout view) {
        view.setTitle((CharSequence) "Title");
        view.setHint((CharSequence) "Hint");
    }
}
