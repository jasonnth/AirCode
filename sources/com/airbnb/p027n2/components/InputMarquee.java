package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.p000v4.view.ViewCompat;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.LinearLayout;
import android.widget.TextView.OnEditorActionListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirEditTextView;
import com.airbnb.p027n2.utils.KeyboardUtils;

/* renamed from: com.airbnb.n2.components.InputMarquee */
public class InputMarquee extends LinearLayout {
    @BindView
    AirEditTextView editTextView;
    private OnClickListener onClickListener;
    private boolean showKeyboardOnFocus;
    private final Runnable touchDelegateRunnable;

    public InputMarquee(Context context) {
        super(context);
        this.touchDelegateRunnable = InputMarquee$$Lambda$1.lambdaFactory$(this);
        init(null);
    }

    public InputMarquee(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.touchDelegateRunnable = InputMarquee$$Lambda$2.lambdaFactory$(this);
        init(attrs);
    }

    public InputMarquee(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.touchDelegateRunnable = InputMarquee$$Lambda$3.lambdaFactory$(this);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.n2_input_marquee, this);
        ButterKnife.bind((View) this);
        setOrientation(1);
        setupAttributes(attrs);
        ViewCompat.setTransitionName(this, "input_marquee");
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_InputMarquee, 0, 0);
        String text = a.getString(R.styleable.n2_InputMarquee_n2_text);
        String hintText = a.getString(R.styleable.n2_InputMarquee_n2_hintText);
        boolean forSearch = a.getBoolean(R.styleable.n2_InputMarquee_n2_forSearch, false);
        this.showKeyboardOnFocus = a.getBoolean(R.styleable.n2_InputMarquee_n2_showKeyboardOnFocus, false);
        setText((CharSequence) text);
        setHint((CharSequence) hintText);
        setForSearch(forSearch);
        a.recycle();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        post(this.touchDelegateRunnable);
    }

    public void setOnEditorActionListener(OnEditorActionListener listener) {
        this.editTextView.setOnEditorActionListener(listener);
    }

    public void addTextWatcher(TextWatcher textWatcher) {
        this.editTextView.addTextChangedListener(textWatcher);
    }

    public void removeTextWatcher(TextWatcher textWatcher) {
        this.editTextView.removeTextChangedListener(textWatcher);
    }

    public void setHint(CharSequence text) {
        this.editTextView.setHint(text);
    }

    public void setHint(int textRes) {
        setHint((CharSequence) getResources().getString(textRes));
    }

    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        return this.editTextView.onCreateInputConnection(outAttrs);
    }

    public void setText(CharSequence text) {
        this.editTextView.setText(text);
    }

    public String getText() {
        return this.editTextView.getText().toString();
    }

    public void setText(int textRes) {
        setText((CharSequence) getResources().getString(textRes));
    }

    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.onClickListener == null || event.getAction() != 1) {
            return false;
        }
        this.onClickListener.onClick(this);
        return true;
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (this.onClickListener == null || event.getAction() != 1) {
            return super.onTouchEvent(event);
        }
        this.onClickListener.onClick(this);
        return true;
    }

    public void setMarqueeOnClickListener(OnClickListener listener) {
        this.onClickListener = listener;
    }

    public void setForSearch(boolean setForSearch) {
        this.editTextView.setImeOptions(setForSearch ? 3 : 6);
    }

    public void setEditable(boolean editable) {
        this.editTextView.setClickable(editable);
        this.editTextView.setFocusable(editable);
    }

    public void invertColors(boolean invertColors) {
        setBackgroundColor(invertColors ? 0 : -1);
        this.editTextView.invertColors(invertColors);
        this.editTextView.setCursorDrawableRes(invertColors ? R.drawable.n2_white_cursor_drawable : R.drawable.n2_babu_cursor_drawable);
    }

    public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
        if (this.showKeyboardOnFocus) {
            KeyboardUtils.showSoftKeyboard(getContext(), this.editTextView);
            this.editTextView.selectAll();
        }
        return super.requestFocus(direction, previouslyFocusedRect);
    }

    public void setShowKeyboardOnFocus(boolean showKeyboardOnFocus2) {
        this.showKeyboardOnFocus = showKeyboardOnFocus2;
    }

    @Deprecated
    public void setLeftDrawable(int drawableRes) {
        this.editTextView.setCompoundDrawablesWithIntrinsicBounds(drawableRes == 0 ? null : getResources().getDrawable(drawableRes), null, null, null);
        this.editTextView.setCompoundDrawablePadding(getResources().getDimensionPixelSize(R.dimen.n2_vertical_padding_tiny));
    }

    public static void mock(InputMarquee marquee) {
        marquee.setHint((CharSequence) "InputMarquee");
    }

    public static void mockPlusInputtedText(InputMarquee marquee) {
        marquee.setText((CharSequence) "Inputted Text");
    }

    public static void mockInverse(InputMarquee marquee) {
        marquee.setHint((CharSequence) "InputMarquee");
        marquee.invertColors(true);
    }
}
