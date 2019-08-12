package com.airbnb.p027n2.components;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.BaseSavedState;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.components.IntegerFormatInputView.Listener;
import com.airbnb.p027n2.primitives.AirTextView;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/* renamed from: com.airbnb.n2.components.SheetFormattedIntegerInputText */
public class SheetFormattedIntegerInputText extends LinearLayout {
    @BindView
    IntegerFormatInputView input;
    @BindView
    AirTextView title;

    /* renamed from: com.airbnb.n2.components.SheetFormattedIntegerInputText$SavedState */
    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
        final Parcelable inputParcel;

        SavedState(Parcelable superState, Parcelable inputParcel2) {
            super(superState);
            this.inputParcel = inputParcel2;
        }

        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeParcelable(this.inputParcel, 0);
        }

        private SavedState(Parcel in) {
            super(in);
            this.inputParcel = in.readParcelable(Parcelable.class.getClassLoader());
        }
    }

    public SheetFormattedIntegerInputText(Context context) {
        super(context);
        init();
    }

    public SheetFormattedIntegerInputText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SheetFormattedIntegerInputText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(1);
        inflate(getContext(), R.layout.n2_sheet_formatted_integer_input_text, this);
        ButterKnife.bind((View) this);
    }

    public void setInputListener(Listener listener) {
        this.input.setInputListener(listener);
    }

    public void setTitle(String text) {
        this.title.setText(text);
    }

    public void setCurrency(Currency currency) {
        this.input.setCurrency(currency);
    }

    public void setNumberFormat(NumberFormat numberFormat) {
        this.input.setNumberFormat(numberFormat);
    }

    public void setHint(CharSequence hint) {
        this.input.setHint(hint);
    }

    public void setValue(Integer value) {
        this.input.setValue(value);
    }

    public Integer getValue() {
        return this.input.getValue();
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        this.input.setSaveEnabled(true);
        SavedState savedState = new SavedState(super.onSaveInstanceState(), this.input.onSaveInstanceState());
        this.input.setSaveEnabled(false);
        return savedState;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        this.input.onRestoreInstanceState(ss.inputParcel);
    }

    public static void mock(SheetFormattedIntegerInputText view) {
        view.setTitle("Title");
        view.setCurrency(Currency.getInstance(Locale.US));
        view.setHint("Hint");
        view.setValue(Integer.valueOf(42));
    }
}
