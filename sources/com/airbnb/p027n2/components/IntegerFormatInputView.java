package com.airbnb.p027n2.components;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import com.airbnb.p027n2.primitives.AirEditTextView;
import java.text.NumberFormat;
import java.util.Currency;

/* renamed from: com.airbnb.n2.components.IntegerFormatInputView */
public class IntegerFormatInputView extends AirEditTextView {
    private NumberFormat formatter;
    private final InputFilter inputFilterWrapper = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            return IntegerFormatInputView.this.maxLengthInputFilter.filter(source, start, end, dest, dstart, dend);
        }
    };
    /* access modifiers changed from: private */
    public Listener listener;
    /* access modifiers changed from: private */
    public InputFilter maxLengthInputFilter;
    /* access modifiers changed from: private */
    public Integer previousValue;
    private final TextWatcher textWatcher = new TextWatcher() {
        public void afterTextChanged(Editable s) {
            Integer currentValue = IntegerFormatInputView.this.getValue();
            boolean valueChanged = !IntegerFormatInputView.objectsAreEqual(currentValue, IntegerFormatInputView.this.previousValue);
            IntegerFormatInputView.this.previousValue = currentValue;
            if (valueChanged && IntegerFormatInputView.this.listener != null) {
                IntegerFormatInputView.this.listener.amountChanged(currentValue);
            }
            IntegerFormatInputView.this.ensureTextFormatted();
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
    };

    /* renamed from: com.airbnb.n2.components.IntegerFormatInputView$Listener */
    public interface Listener {
        void amountChanged(Integer num);
    }

    public IntegerFormatInputView(Context context) {
        super(context);
        init();
    }

    public IntegerFormatInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IntegerFormatInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setInputType(2);
        addTextChangedListener(this.textWatcher);
        setNumberFormat(NumberFormat.getIntegerInstance());
        setFilters(new InputFilter[]{this.inputFilterWrapper});
    }

    public void setInputListener(Listener listener2) {
        this.listener = listener2;
    }

    public void setCurrency(Currency currency) {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        format.setCurrency(currency);
        setNumberFormat(format);
    }

    public void setNumberFormat(NumberFormat formatter2) {
        if (!objectsAreEqual(formatter2, this.formatter)) {
            this.formatter = formatter2;
            this.maxLengthInputFilter = new LengthFilter(getMaxFormattedLength(formatter2));
            ensureTextFormatted();
        }
    }

    public void setValue(Integer value) {
        if (!objectsAreEqual(value, this.previousValue)) {
            setText(formatValue(value));
        }
    }

    public Integer getValue() {
        Integer num = null;
        String digits = getText().toString().replaceAll("[^0-9]", "");
        if (digits.length() < 1) {
            return num;
        }
        try {
            return Integer.valueOf(Integer.parseInt(digits));
        } catch (NumberFormatException e) {
            Log.e("FormattedIntegerInput", e.toString());
            return num;
        }
    }

    /* access modifiers changed from: protected */
    public void onSelectionChanged(int selStart, int selEnd) {
        if (ensureValidSelection(selStart, selEnd)) {
            super.onSelectionChanged(selStart, selEnd);
        }
    }

    private boolean ensureValidSelection(int selStart, int selEnd) {
        boolean selectionValid = true;
        if (!isEmpty()) {
            int firstValidSelection = getIndexOfFirstDigit(getText());
            int lastValidSelection = getIndexOfLastDigit(getText()) + 1;
            if (selStart < firstValidSelection || selEnd > lastValidSelection) {
                selectionValid = false;
            }
            if (!selectionValid) {
                int correctedStart = Math.min(lastValidSelection, Math.max(selStart, firstValidSelection));
                setSelection(correctedStart, Math.max(correctedStart, Math.min(selEnd, lastValidSelection)));
            }
        }
        return selectionValid;
    }

    /* access modifiers changed from: private */
    public void ensureTextFormatted() {
        if (this.formatter != null) {
            String newValue = formatValue(getValue());
            if (!objectsAreEqual(newValue, getText().toString())) {
                int newSelectionIndex = getIndexOfDigit(newValue, getDigitPosition(getText().toString(), getSelectionStart()));
                setText(newValue);
                setSelection(newSelectionIndex);
            }
        }
    }

    private String formatValue(Integer value) {
        if (value == null) {
            return "";
        }
        if (this.formatter == null) {
            return value.toString();
        }
        return this.formatter.format(value);
    }

    /* access modifiers changed from: private */
    public static boolean objectsAreEqual(Object lhs, Object rhs) {
        return (lhs == null && rhs == null) || (lhs != null && lhs.equals(rhs));
    }

    private static int getDigitPosition(String input, int position) {
        int digitIndex = 0;
        int i = 0;
        while (i < position && i < input.length()) {
            if (Character.isDigit(input.charAt(i))) {
                digitIndex++;
            }
            i++;
        }
        return digitIndex;
    }

    private static int getIndexOfDigit(String input, int digitIndex) {
        int charIndex = 0;
        while (charIndex < input.length() && digitIndex > 0) {
            if (Character.isDigit(input.charAt(charIndex))) {
                digitIndex--;
            }
            charIndex++;
        }
        return charIndex;
    }

    private static int getIndexOfLastDigit(Editable input) {
        for (int i = input.length() - 1; i >= 0; i--) {
            if (Character.isDigit(input.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

    private static int getIndexOfFirstDigit(Editable input) {
        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

    private int getMaxFormattedLength(NumberFormat formatter2) {
        return formatter2.format(Math.pow(10.0d, (double) Math.min(9, formatter2.getMaximumIntegerDigits())) - 1.0d).length();
    }

    public static void mock(IntegerFormatInputView view) {
        view.setValue(Integer.valueOf(42));
    }
}
