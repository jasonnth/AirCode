package com.airbnb.p027n2.components;

import android.animation.Animator;
import android.animation.LayoutTransition;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.p000v4.view.ViewCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import butterknife.BindInt;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirEditTextView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ObjectAnimatorFactory;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.airbnb.paris.Paris;
import com.google.common.base.Objects;

/* renamed from: com.airbnb.n2.components.InlineInputRow */
public class InlineInputRow extends BaseComponent {
    @BindInt
    int animationDuration;
    private boolean autoHideTipOnInputChange;
    int defaultDrawable;
    @BindView
    View divider;
    @BindView
    AirEditTextView editText;
    int eraseDrawable;
    private final OnClickListener eraseListener;
    @BindView
    AirTextView error;
    /* access modifiers changed from: private */
    public ErrorDismissalMode errorDismissal;
    int errorDrawable;
    int errorStyle;
    private OnFocusChangeListener focusChangedlistener;
    private CharSequence hint;
    @BindView
    ImageView iconView;
    /* access modifiers changed from: private */
    public OnInputChangedListener inputChangedListener;
    int normalStyle;
    /* access modifiers changed from: private */
    public String previousValue;
    private boolean removeHintOnFocus;
    /* access modifiers changed from: private */
    public boolean showingError;
    @BindView
    AirTextView subTitleText;
    @BindView
    AirTextView tip;
    private CharSequence tipValue;
    @BindView
    AirTextView titleText;

    /* renamed from: com.airbnb.n2.components.InlineInputRow$ErrorDismissalMode */
    public enum ErrorDismissalMode {
        MANUAL,
        ON_EDIT,
        ON_UNFOCUS
    }

    /* renamed from: com.airbnb.n2.components.InlineInputRow$OnInputChangedListener */
    public interface OnInputChangedListener {
        void onInputChanged(String str);
    }

    /* renamed from: com.airbnb.n2.components.InlineInputRow$SavedState */
    private static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
        Parcelable editTextState;

        SavedState(Parcelable superState) {
            super(superState);
        }

        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeParcelable(this.editTextState, 0);
        }

        @SuppressLint({"ParcelClassLoader"})
        private SavedState(Parcel in) {
            super(in);
            this.editTextState = in.readParcelable(null);
        }
    }

    public InlineInputRow(Context context) {
        super(context);
        this.errorDismissal = ErrorDismissalMode.ON_EDIT;
        this.eraseListener = InlineInputRow$$Lambda$1.lambdaFactory$(this);
    }

    public InlineInputRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.errorDismissal = ErrorDismissalMode.ON_EDIT;
        this.eraseListener = InlineInputRow$$Lambda$2.lambdaFactory$(this);
    }

    public InlineInputRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.errorDismissal = ErrorDismissalMode.ON_EDIT;
        this.eraseListener = InlineInputRow$$Lambda$3.lambdaFactory$(this);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_inline_input_row;
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        super.init(attrs);
        Paris.style(this).apply(attrs);
        updateIcon();
        this.editText.setOnFocusChangeListener(InlineInputRow$$Lambda$4.lambdaFactory$(this));
        this.editText.addTextChangedListener(getTextWatcherWrapper());
        setupLayoutTransition();
    }

    static /* synthetic */ void lambda$init$0(InlineInputRow inlineInputRow, View v, boolean hasFocus) {
        inlineInputRow.updateIcon();
        if (inlineInputRow.removeHintOnFocus) {
            inlineInputRow.editText.setHint(hasFocus ? "" : inlineInputRow.hint);
        }
        if (!hasFocus && inlineInputRow.showingError && inlineInputRow.errorDismissal == ErrorDismissalMode.ON_UNFOCUS) {
            inlineInputRow.showError(false);
        }
        if (inlineInputRow.focusChangedlistener != null) {
            inlineInputRow.focusChangedlistener.onFocusChange(v, hasFocus);
        }
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

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return hasOnClickListeners() || super.onInterceptTouchEvent(ev);
    }

    public void setOnClickListener(OnClickListener l) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4 = true;
        super.setOnClickListener(l);
        this.editText.setClickable(!hasOnClickListeners());
        AirEditTextView airEditTextView = this.editText;
        if (!isEnabled() || hasOnClickListeners()) {
            z = false;
        } else {
            z = true;
        }
        airEditTextView.setCursorVisible(z);
        AirEditTextView airEditTextView2 = this.editText;
        if (!isEnabled() || hasOnClickListeners()) {
            z2 = false;
        } else {
            z2 = true;
        }
        airEditTextView2.setFocusableInTouchMode(z2);
        AirEditTextView airEditTextView3 = this.editText;
        if (!isEnabled() || hasOnClickListeners()) {
            z3 = false;
        } else {
            z3 = true;
        }
        airEditTextView3.setFocusable(z3);
        AirEditTextView airEditTextView4 = this.editText;
        if (hasOnClickListeners()) {
            z4 = false;
        }
        airEditTextView4.setLongClickable(z4);
        requestLayout();
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

    public void setSubTitleText(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.subTitleText, text);
    }

    public void setHint(CharSequence text) {
        this.hint = text;
        this.editText.setHint(text);
    }

    public void setInputText(CharSequence text) {
        this.editText.setText(text);
        this.editText.setSelection(this.editText.length());
    }

    public void setInputText(int stringId) {
        setInputText((CharSequence) getResources().getString(stringId));
    }

    public void setMaxLines(int maxLines) {
        int inputType;
        boolean z = true;
        if (maxLines == 0) {
            maxLines = Integer.MAX_VALUE;
        }
        int inputType2 = this.editText.getInputType();
        if (maxLines == 1) {
            inputType = inputType2 & -131073;
        } else {
            inputType = inputType2 | 131072;
        }
        this.editText.setInputType(inputType);
        AirEditTextView airEditTextView = this.editText;
        if (maxLines != 1) {
            z = false;
        }
        airEditTextView.setSingleLine(z);
        this.editText.setMaxLines(maxLines);
    }

    public void setError(CharSequence errorText) {
        setTip(null);
        ViewLibUtils.setText((TextView) this.error, errorText);
    }

    public void setError(int stringRes) {
        setError((CharSequence) getContext().getString(stringRes));
    }

    public void setErrorDismissal(ErrorDismissalMode mode) {
        this.errorDismissal = mode;
    }

    /* access modifiers changed from: 0000 */
    public void setErrorDismissal(int index) {
        this.errorDismissal = ErrorDismissalMode.values()[index];
    }

    /* access modifiers changed from: 0000 */
    public void setNormalStyle(int styleRes) {
        if (this.normalStyle != styleRes) {
            boolean wasSet = this.normalStyle != 0;
            this.normalStyle = styleRes;
            if (wasSet && !this.showingError) {
                Paris.style(this).apply(styleRes);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void setErrorStyle(int styleRes) {
        if (this.errorStyle != styleRes) {
            boolean wasSet = this.errorStyle != 0;
            this.errorStyle = styleRes;
            if (wasSet && this.showingError) {
                Paris.style(this).apply(styleRes);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void setDefaultDrawable(int drawableRes) {
        this.defaultDrawable = drawableRes;
        updateIcon();
    }

    /* access modifiers changed from: 0000 */
    public void setEraseDrawable(int drawableRes) {
        this.eraseDrawable = drawableRes;
        updateIcon();
    }

    /* access modifiers changed from: 0000 */
    public void setErrorDrawable(int drawableRes) {
        this.errorDrawable = drawableRes;
        updateIcon();
    }

    @Deprecated
    public void setTip(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.tip, text);
    }

    @Deprecated
    public void setTipMaxLine(int tipMaxLine) {
        boolean z = true;
        AirTextView airTextView = this.tip;
        if (tipMaxLine != 1) {
            z = false;
        }
        airTextView.setSingleLine(z);
        this.tip.setMaxLines(tipMaxLine);
    }

    @Deprecated
    public void setTipValue(CharSequence value) {
        this.tipValue = value;
        this.tip.setOnClickListener(InlineInputRow$$Lambda$5.lambdaFactory$(this));
        maybeUpdateTipVisibility();
    }

    static /* synthetic */ void lambda$setTipValue$1(InlineInputRow inlineInputRow, View v) {
        inlineInputRow.editText.setText(inlineInputRow.tipValue);
        inlineInputRow.editText.setSelection(inlineInputRow.editText.length());
    }

    public void setOnInputChangedListener(OnInputChangedListener listener) {
        this.inputChangedListener = listener;
    }

    public void setInputType(int type) {
        this.editText.setInputType(type);
    }

    public String getInputText() {
        return this.editText.getText().toString();
    }

    public void setRemoveHintOnFocus(boolean enabled) {
        this.removeHintOnFocus = enabled;
    }

    @Deprecated
    public void setAutoHideTipOnInputChange(boolean autoHideTipOnInputChange2) {
        this.autoHideTipOnInputChange = autoHideTipOnInputChange2;
        maybeUpdateTipVisibility();
    }

    public EditText getEditText() {
        return this.editText;
    }

    public void setEnabled(boolean enabled) {
        boolean z;
        boolean z2 = true;
        super.setEnabled(enabled);
        this.titleText.setEnabled(enabled);
        this.subTitleText.setEnabled(enabled);
        this.editText.setEnabled(enabled);
        this.editText.setCursorVisible(enabled && !hasOnClickListeners());
        AirEditTextView airEditTextView = this.editText;
        if (!enabled || hasOnClickListeners()) {
            z = false;
        } else {
            z = true;
        }
        airEditTextView.setFocusableInTouchMode(z);
        AirEditTextView airEditTextView2 = this.editText;
        if (!enabled || hasOnClickListeners()) {
            z2 = false;
        }
        airEditTextView2.setFocusable(z2);
        this.tip.setEnabled(enabled);
        updateIcon();
    }

    /* access modifiers changed from: private */
    public void maybeUpdateTipVisibility() {
        boolean isCurrentlyVisible;
        if (this.autoHideTipOnInputChange && this.tipValue != null) {
            boolean setVisible = !TextUtils.equals(this.editText.getText(), this.tipValue);
            if (this.tip.getVisibility() == 0) {
                isCurrentlyVisible = true;
            } else {
                isCurrentlyVisible = false;
            }
            if (setVisible != isCurrentlyVisible) {
                if (setVisible) {
                    fadeTipViewIn();
                } else {
                    fadeTipViewOut();
                }
            }
        }
    }

    private void updateIcon() {
        boolean z = false;
        this.iconView.setOnClickListener(null);
        this.iconView.setClickable(false);
        int iconRes = 0;
        if (isEnabled()) {
            if (this.showingError) {
                iconRes = this.errorDrawable;
            } else if (this.editText.hasFocus()) {
                iconRes = this.eraseDrawable;
                this.iconView.setOnClickListener(this.eraseListener);
            } else {
                iconRes = this.defaultDrawable;
            }
        }
        ImageView imageView = this.iconView;
        if (iconRes != 0) {
            z = true;
        }
        ViewLibUtils.setVisibleIf(imageView, z);
        this.iconView.setImageResource(iconRes);
    }

    private void setupLayoutTransition() {
        LayoutTransition layoutTransition = new LayoutTransition();
        layoutTransition.disableTransitionType(2);
        layoutTransition.disableTransitionType(3);
        layoutTransition.setStartDelay(1, 0);
        layoutTransition.setDuration((long) this.animationDuration);
        setLayoutTransition(layoutTransition);
    }

    private void fadeTipViewIn() {
        if (!ViewCompat.isAttachedToWindow(this)) {
            this.tip.setVisibility(0);
        } else {
            ObjectAnimatorFactory.fadeIn(this.tip).setDuration(this.animationDuration).setStartDelay(this.animationDuration).onStartStep(InlineInputRow$$Lambda$6.lambdaFactory$(this)).buildAndStart();
        }
    }

    static /* synthetic */ void lambda$fadeTipViewIn$3(InlineInputRow inlineInputRow, Animator animator) {
        inlineInputRow.tip.setAlpha(0.0f);
        inlineInputRow.tip.setVisibility(0);
        if (inlineInputRow.getParent() instanceof ViewGroup) {
            inlineInputRow.getLayoutTransition().addChild((ViewGroup) inlineInputRow.getParent(), inlineInputRow);
        }
    }

    private void fadeTipViewOut() {
        if (!ViewCompat.isAttachedToWindow(this)) {
            this.tip.setVisibility(8);
        } else {
            ObjectAnimatorFactory.fadeOut(this.tip).setDuration(this.animationDuration).onStartStep(InlineInputRow$$Lambda$7.lambdaFactory$(this)).onEndStep(InlineInputRow$$Lambda$8.lambdaFactory$(this)).buildAndStart();
        }
    }

    static /* synthetic */ void lambda$fadeTipViewOut$5(InlineInputRow inlineInputRow, Animator animator) {
        inlineInputRow.tip.setVisibility(8);
        if (inlineInputRow.getParent() instanceof ViewGroup) {
            inlineInputRow.getLayoutTransition().removeChild((ViewGroup) inlineInputRow.getParent(), inlineInputRow);
        }
    }

    public void setOnEditorActionListener(OnEditorActionListener listener) {
        this.editText.setOnEditorActionListener(listener);
    }

    public void setDoneAction() {
        this.editText.setImeOptions(6);
    }

    public void showError(boolean showError) {
        if (this.showingError != showError) {
            this.showingError = showError;
            Paris.style(this).apply(showError ? this.errorStyle : this.normalStyle);
            ViewLibUtils.setVisibleIf(this.error, showError);
            updateIcon();
        }
    }

    private TextWatcher getTextWatcherWrapper() {
        return new TextWatcher() {
            public void beforeTextChanged(CharSequence editable, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence editable, int start, int before, int count) {
            }

            public void afterTextChanged(Editable editable) {
                boolean valueChanged;
                String currentValue = editable.toString();
                if (!Objects.equal(currentValue, InlineInputRow.this.previousValue)) {
                    valueChanged = true;
                } else {
                    valueChanged = false;
                }
                InlineInputRow.this.previousValue = currentValue;
                if (valueChanged) {
                    if (InlineInputRow.this.showingError && InlineInputRow.this.errorDismissal == ErrorDismissalMode.ON_EDIT) {
                        InlineInputRow.this.showError(false);
                    }
                    InlineInputRow.this.maybeUpdateTipVisibility();
                    if (InlineInputRow.this.inputChangedListener != null) {
                        InlineInputRow.this.inputChangedListener.onInputChanged(currentValue);
                    }
                }
            }
        };
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState ss = new SavedState(super.onSaveInstanceState());
        ss.editTextState = this.editText.onSaveInstanceState();
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
        this.editText.onRestoreInstanceState(ss.editTextState);
    }

    /* access modifiers changed from: protected */
    public void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        dispatchFreezeSelfOnly(container);
    }

    /* access modifiers changed from: protected */
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        dispatchThawSelfOnly(container);
    }

    public static void mock(InlineInputRow row) {
        row.setTitle((CharSequence) "Label row");
        row.setHint("Placeholder text");
    }

    public static void mockInputtedText(InlineInputRow row) {
        row.setTitle((CharSequence) "Label row");
        row.setInputText((CharSequence) "Inputted text");
    }

    public static void mockInputtedTextWraps(InlineInputRow row) {
        row.setTitle((CharSequence) "Label row");
        row.setInputText((CharSequence) "Inputted text can allow text to wrap to two lines");
        row.setMaxLines(2);
    }

    public static void mockError(InlineInputRow row) {
        row.setTitle((CharSequence) "Label row");
        row.setInputText((CharSequence) "Inputted text");
        row.showError(true);
        row.setError((CharSequence) "This is an error message");
        row.setErrorDismissal(ErrorDismissalMode.MANUAL);
    }

    public static void mockSubtitle(InlineInputRow row) {
        row.setTitle((CharSequence) "Label row");
        row.setSubTitleText("Subtitle to talk about some legal copy about stuff that needs to be shown");
        row.setInputText((CharSequence) "Inputted text");
    }
}
