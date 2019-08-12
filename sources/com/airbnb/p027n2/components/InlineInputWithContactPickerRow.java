package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.TouchDelegate;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirEditTextView;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.InlineInputWithContactPickerRow */
public class InlineInputWithContactPickerRow extends LinearLayout {
    @BindView
    ImageButton addButton;
    @BindInt
    int animationDuration;
    @BindView
    AirEditTextView editText;
    private OnFocusChangeListener focusChangedlistener;
    /* access modifiers changed from: private */
    public TextWatcher textWatcher;
    private final TextWatcher textWatcherWrapper = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            if (InlineInputWithContactPickerRow.this.textWatcher != null) {
                InlineInputWithContactPickerRow.this.textWatcher.beforeTextChanged(s, start, count, after);
            }
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (InlineInputWithContactPickerRow.this.textWatcher != null) {
                InlineInputWithContactPickerRow.this.textWatcher.onTextChanged(s, start, before, count);
            }
        }

        public void afterTextChanged(Editable s) {
            if (InlineInputWithContactPickerRow.this.textWatcher != null) {
                InlineInputWithContactPickerRow.this.textWatcher.afterTextChanged(s);
            }
        }
    };
    @BindView
    AirTextView titleText;

    public InlineInputWithContactPickerRow(Context context) {
        super(context);
        init(context, null);
    }

    public InlineInputWithContactPickerRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public InlineInputWithContactPickerRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.n2_inline_input_with_contact_picker_row, this);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
        setOrientation(1);
        this.editText.setOnFocusChangeListener(InlineInputWithContactPickerRow$$Lambda$1.lambdaFactory$(this));
        this.editText.addTextChangedListener(this.textWatcherWrapper);
    }

    static /* synthetic */ void lambda$init$0(InlineInputWithContactPickerRow inlineInputWithContactPickerRow, View v, boolean hasFocus) {
        if (inlineInputWithContactPickerRow.focusChangedlistener != null) {
            inlineInputWithContactPickerRow.focusChangedlistener.onFocusChange(v, hasFocus);
        }
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_InlineInputWithContactPickerRow, 0, 0);
        String titleText2 = a.getString(R.styleable.n2_InlineInputWithContactPickerRow_n2_titleText);
        String inputText = a.getString(R.styleable.n2_InlineInputWithContactPickerRow_n2_inputText);
        setTitle((CharSequence) titleText2);
        setInputText((CharSequence) inputText);
        a.recycle();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (hasOnClickListeners()) {
            setTouchDelegate(null);
        } else {
            setTouchDelegate(new TouchDelegate(new Rect(0, 0, getWidth(), getHeight()), this.editText));
        }
    }

    public void setAddButtonClicklistener(OnClickListener listener) {
        this.addButton.setOnClickListener(listener);
    }

    public void setOnFocusChangeListener(OnFocusChangeListener focusChangedlistener2) {
        this.focusChangedlistener = focusChangedlistener2;
    }

    public void setTitle(CharSequence text) {
        this.titleText.setText(text);
    }

    public void setTitle(int stringId) {
        setTitle((CharSequence) getResources().getString(stringId));
    }

    public void setInputText(CharSequence text) {
        this.editText.setText(text);
        this.editText.setSelection(this.editText.length());
    }

    public void setInputText(int stringId) {
        setInputText((CharSequence) getResources().getString(stringId));
    }

    public void setOnTextChangedListener(TextWatcher watcher) {
        this.textWatcher = watcher;
    }

    public void setInputType(int type) {
        this.editText.setInputType(type);
    }

    public String getInputText() {
        return this.editText.getText().toString();
    }

    public EditText getEditText() {
        return this.editText;
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        this.titleText.setEnabled(enabled);
        this.editText.setEnabled(enabled);
        this.editText.setCursorVisible(enabled);
        this.editText.setFocusableInTouchMode(enabled);
        this.editText.setFocusable(enabled);
    }

    public static void mock(InlineInputWithContactPickerRow row) {
        row.setTitle((CharSequence) "Label Row");
    }

    public static void mockPlusInputtedText(InlineInputWithContactPickerRow row) {
        row.setTitle((CharSequence) "Label Row");
        row.setInputText((CharSequence) "Inputted Text");
    }

    public static void mockPlusInputtedTextWraps(InlineInputWithContactPickerRow row) {
        row.setTitle((CharSequence) "Label Row");
        row.setInputText((CharSequence) "Inputted Text can allow text to wrap to two lines");
    }
}
