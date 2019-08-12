package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.p000v4.content.ContextCompat;
import android.support.p002v7.content.res.AppCompatResources;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView.OnEditorActionListener;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirAutoCompleteTextView;
import com.airbnb.p027n2.primitives.AirEditTextView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ColorizedDrawable;
import com.airbnb.p027n2.utils.KeyboardUtils;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.google.common.base.Preconditions;
import java.util.List;

/* renamed from: com.airbnb.n2.components.SheetInputText */
public class SheetInputText extends LinearLayout {
    public static final int AUTO_COMPLETE = 2;
    public static final int NONE = 0;
    public static final int NON_EDITABLE = 3;
    public static final int PASSWORD = 1;
    /* access modifiers changed from: private */
    public AirTextView actionText;
    /* access modifiers changed from: private */
    public EditText editText;
    /* access modifiers changed from: private */
    public LinearLayout editTextContainer;
    /* access modifiers changed from: private */
    public int errorTintColor;
    /* access modifiers changed from: private */
    public AirTextView hintLabel;
    /* access modifiers changed from: private */
    public AirTextView hintLabelShowPassword;
    private int inputTextMode;
    private OnShowPasswordToggleListener onShowPasswordToggleListener;
    private boolean showingDots;
    /* access modifiers changed from: private */
    public int validTintColor;

    /* renamed from: com.airbnb.n2.components.SheetInputText$DummyTransformationMethod */
    static class DummyTransformationMethod implements TransformationMethod {
        DummyTransformationMethod() {
        }

        public CharSequence getTransformation(CharSequence source, View view) {
            return source;
        }

        public void onFocusChanged(View view, CharSequence sourceText, boolean focused, int direction, Rect previouslyFocusedRect) {
        }
    }

    /* renamed from: com.airbnb.n2.components.SheetInputText$OnShowPasswordToggleListener */
    public interface OnShowPasswordToggleListener {
        void onToggled(boolean z);
    }

    /* renamed from: com.airbnb.n2.components.SheetInputText$SavedState */
    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
        int selEnd;
        int selStart;
        String text;

        SavedState(Parcelable superState) {
            super(superState);
        }

        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(this.selStart);
            dest.writeInt(this.selEnd);
            dest.writeString(this.text);
        }

        private SavedState(Parcel in) {
            super(in);
            this.selStart = in.readInt();
            this.selEnd = in.readInt();
            this.text = in.readString();
        }
    }

    /* renamed from: com.airbnb.n2.components.SheetInputText$State */
    public enum State {
        Normal(-1),
        Loading(-1),
        Valid(R.drawable.n2_ic_check_babu),
        Error(R.drawable.n2_icon_exclamation);
        
        public final int iconDrawable;

        private State(int iconDrawable2) {
            this.iconDrawable = iconDrawable2;
        }
    }

    /* renamed from: com.airbnb.n2.components.SheetInputText$Style */
    public enum Style {
        BABU(R.style.n2_SmallText_Inverse, R.style.n2_SmallText_Inverse, R.style.n2_TitleText3_Inverse, R.style.n2_SmallText_Inverse, R.drawable.n2_white_cursor_drawable, R.drawable.n2_sheet_input_text_background, R.color.n2_white_60, R.color.n2_white_60),
        WHITE(R.style.n2_SmallText, R.style.n2_SmallText, R.style.n2_TitleText3, R.style.n2_SmallText, R.drawable.n2_black_cursor_drawable, R.drawable.n2_input_text_white_background, R.color.n2_babu, R.color.n2_error_color);
        
        final int actionStyle;
        final int backgroundRes;
        final int cursorDrawableRes;
        final int errorTintColorRes;
        final int hintShowPasswordStyleRes;
        final int hintStyleRes;
        final int textStyleRes;
        final int validTintColorRes;

        private Style(int hintStyleRes2, int actionStyle2, int textStyleRes2, int hintShowPasswordStyleRes2, int cursorDrawableRes2, int backgroundRes2, int validTintColorRes2, int errorTintColorRes2) {
            this.hintStyleRes = hintStyleRes2;
            this.actionStyle = actionStyle2;
            this.textStyleRes = textStyleRes2;
            this.hintShowPasswordStyleRes = hintShowPasswordStyleRes2;
            this.cursorDrawableRes = cursorDrawableRes2;
            this.backgroundRes = backgroundRes2;
            this.validTintColorRes = validTintColorRes2;
            this.errorTintColorRes = errorTintColorRes2;
        }

        public void setStyle(SheetInputText inputText) {
            Context context = inputText.getContext();
            inputText.hintLabel.setTextAppearance(context, this.hintStyleRes);
            inputText.actionText.setTextAppearance(context, this.actionStyle);
            inputText.editText.setTextAppearance(context, this.textStyleRes);
            inputText.hintLabelShowPassword.setTextAppearance(context, this.hintShowPasswordStyleRes);
            if (inputText.editText instanceof AirEditTextView) {
                ((AirEditTextView) inputText.editText).setCursorDrawableRes(this.cursorDrawableRes);
            }
            inputText.editTextContainer.setBackgroundResource(this.backgroundRes);
            inputText.validTintColor = ContextCompat.getColor(context, this.validTintColorRes);
            inputText.errorTintColor = ContextCompat.getColor(context, this.errorTintColorRes);
        }
    }

    public SheetInputText(Context context) {
        super(context);
        init(null);
    }

    public SheetInputText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SheetInputText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_sheet_input_text, this);
        setOrientation(1);
        setupAttributes(attrs);
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_SheetInputText);
        selectMode(a.getInt(R.styleable.n2_SheetInputText_n2_inputTextMode, 0));
        boolean isInlineHint = a.getBoolean(R.styleable.n2_SheetInputText_n2_inlineHint, false);
        this.hintLabel = (AirTextView) ViewLibUtils.findById(this, R.id.sheet_input_text_hint);
        String hintText = a.getString(R.styleable.n2_SheetInputText_n2_hintText);
        if (isInlineHint) {
            setInlineHint(hintText);
        } else {
            setHint(hintText);
        }
        this.actionText = (AirTextView) ViewLibUtils.findById(this, R.id.sheet_input_text_action);
        setActionText(a.getString(R.styleable.n2_SheetInputText_n2_actionText));
        this.editText.setImeOptions(a.getInteger(R.styleable.n2_SheetInputText_android_imeOptions, 0));
        this.editText.setInputType(a.getInteger(R.styleable.n2_SheetInputText_android_inputType, 1));
        this.editTextContainer = (LinearLayout) ViewLibUtils.findById(this, R.id.sheet_input_text_edit_text_container);
        Style.BABU.setStyle(this);
        a.recycle();
    }

    public void setInlineHint(String hint) {
        this.hintLabel.setVisibility(8);
        this.editText.setHint(hint);
    }

    public void setHint(String hint) {
        this.hintLabel.setText(hint);
    }

    public void setActionText(String actionTextString) {
        this.actionText.setText(actionTextString);
    }

    public void setHint(int resId) {
        this.hintLabel.setText(resId);
    }

    private void selectMode(int mode) {
        this.inputTextMode = mode;
        this.editText = (EditText) ViewLibUtils.findById(this, R.id.sheet_input_text);
        this.hintLabelShowPassword = (AirTextView) ViewLibUtils.findById(this, R.id.sheet_input_text_show_password);
        switch (mode) {
            case 0:
                break;
            case 1:
                this.editText.setInputType(129);
                if (getPositiveActionLabel() > 0 && getNegativeActionLabel() > 0) {
                    this.hintLabelShowPassword.setText(getPositiveActionLabel());
                    this.hintLabelShowPassword.setVisibility(0);
                    this.showingDots = true;
                }
                this.hintLabelShowPassword.setOnClickListener(SheetInputText$$Lambda$1.lambdaFactory$(this));
                break;
            case 2:
                this.editText = (EditText) ViewLibUtils.findById(this, R.id.sheet_input_text_auto_complete);
                break;
            case 3:
                this.editText.setKeyListener(null);
                this.editText.setFocusable(false);
                this.editText.setCursorVisible(false);
                this.editText.setFocusableInTouchMode(false);
                TypedValue outValue = new TypedValue();
                getContext().getTheme().resolveAttribute(16843534, outValue, true);
                this.editText.setBackgroundResource(outValue.resourceId);
                break;
            default:
                throw new IllegalStateException("Setting SheetInputText with invalid mode :" + mode);
        }
        this.editText.setVisibility(0);
    }

    public void showPassword(boolean show) {
        if (this.showingDots == show) {
            toggleShowPasswordButton();
        }
    }

    private void toggleShowPasswordButton() {
        int start = this.editText.getSelectionStart();
        int end = this.editText.getSelectionEnd();
        if (this.showingDots) {
            this.editText.setTransformationMethod(new DummyTransformationMethod());
            this.hintLabelShowPassword.setText(getNegativeActionLabel());
        } else {
            this.editText.setTransformationMethod(new PasswordTransformationMethod());
            this.hintLabelShowPassword.setText(getPositiveActionLabel());
        }
        this.showingDots = !this.showingDots;
        this.editText.setSelection(start, end);
    }

    public void setOnShowPasswordToggleListener(OnShowPasswordToggleListener onShowPasswordToggleListener2) {
        this.onShowPasswordToggleListener = onShowPasswordToggleListener2;
    }

    /* access modifiers changed from: private */
    public void onShowPasswordButtonClicked() {
        if (this.onShowPasswordToggleListener != null) {
            this.onShowPasswordToggleListener.onToggled(this.showingDots);
        }
        toggleShowPasswordButton();
    }

    /* access modifiers changed from: protected */
    public int getPositiveActionLabel() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public int getNegativeActionLabel() {
        return 0;
    }

    public void setAutoCompleteTextView(List<String> autoCompleteOptions) {
        Preconditions.checkState(this.inputTextMode == 2);
        ((AirAutoCompleteTextView) this.editText).setAdapter(new ArrayAdapter(getContext(), 17367050, autoCompleteOptions));
    }

    public void setOnClickListener(OnClickListener l) {
        super.setOnClickListener(l);
        this.editText.setOnClickListener(l);
    }

    public void setOnFocusChangeListener(OnFocusChangeListener l) {
        super.setOnFocusChangeListener(l);
        this.editText.setOnFocusChangeListener(l);
    }

    public void setActionOnClickListener(OnClickListener l) {
        this.actionText.setOnClickListener(l);
    }

    /* access modifiers changed from: protected */
    public void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        dispatchFreezeSelfOnly(container);
    }

    /* access modifiers changed from: protected */
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        dispatchThawSelfOnly(container);
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState ss = new SavedState(super.onSaveInstanceState());
        ss.selStart = this.editText.getSelectionStart();
        ss.selEnd = this.editText.getSelectionEnd();
        ss.text = this.editText.getText().toString();
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
        setText(ss.text);
        this.editText.setSelection(ss.selStart, ss.selEnd);
    }

    public void setSelection(int index) {
        this.editText.setSelection(index);
    }

    public void setText(String text) {
        this.editText.setText(text);
    }

    public Editable getText() {
        return this.editText.getText();
    }

    public void setHintText(String text) {
        ((AirTextView) ViewLibUtils.findById(this, R.id.sheet_input_text_hint)).setText(text);
    }

    public void addTextChangedListener(TextWatcher watcher) {
        this.editText.addTextChangedListener(watcher);
    }

    public void removeTextChangedListener(TextWatcher watcher) {
        this.editText.removeTextChangedListener(watcher);
    }

    public void setOnEditorActionListener(OnEditorActionListener onEditorActionListener) {
        this.editText.setOnEditorActionListener(onEditorActionListener);
    }

    public void setState(State state) {
        switch (state) {
            case Loading:
                this.editText.setCompoundDrawables(null, null, null, null);
                this.editText.setEnabled(false);
                return;
            case Normal:
                this.editText.setEnabled(true);
                this.editText.setCompoundDrawables(null, null, null, null);
                return;
            default:
                this.editText.setEnabled(true);
                this.editText.setCompoundDrawablesWithIntrinsicBounds(null, null, ColorizedDrawable.mutateDrawableWithColor(AppCompatResources.getDrawable(getContext(), state.iconDrawable), state == State.Error ? this.errorTintColor : this.validTintColor), null);
                return;
        }
    }

    public void showSoftKeyboard() {
        KeyboardUtils.showSoftKeyboard(getContext(), this.editText);
    }

    public void hideSoftKeyboard() {
        KeyboardUtils.hideSoftKeyboard(getContext(), this.editText);
    }

    public void setMaxLength(int maxLength) {
        this.editText.setFilters(new InputFilter[]{new LengthFilter(maxLength)});
    }

    public void setEnabled(boolean enabled) {
        this.editText.setEnabled(enabled);
    }

    public static void mock(SheetInputText view) {
        view.setHint("Hint");
    }
}
