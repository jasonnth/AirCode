package com.airbnb.android.core.views;

import android.content.Context;
import android.support.p000v4.content.ContextCompat;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.View;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.utils.TextWatcherUtils;
import com.airbnb.p027n2.collections.VerboseScrollView;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.primitives.AirEditTextView;
import com.airbnb.p027n2.primitives.AirTextView;

public class AirEditTextPageView extends VerboseScrollView {
    private static final int INPUT_TYPE_MULTI_LINE = 245761;
    private static final int INPUT_TYPE_SINGLE_LINE = 114689;
    public static final int MAX_LENGTH_NONE = -1;
    public static final int MIN_LENGTH_NONE = 0;
    public static final int MIN_LENGTH_NOT_EMPTY = 1;
    @BindDimen
    int bottomBarPaddingPx;
    @BindView
    AirTextView characterCountView;
    private CharSequence hintText;
    private Listener listener;
    private int maxLength = -1;
    private int minLength = 0;
    @BindView
    AirEditTextView textView;
    @BindView
    DocumentMarquee titleView;
    private boolean validLength = true;
    @BindDimen
    int withCountBottomSpacerPx;
    @BindDimen
    int withoutCountBottomSpacerPx;

    public interface Listener {
        void validityChanged(boolean z);
    }

    public AirEditTextPageView(Context context) {
        super(context);
        init();
    }

    public AirEditTextPageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AirEditTextPageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), C0716R.layout.edit_text_page_view, this);
        ButterKnife.bind((View) this);
        this.textView.addTextChangedListener(TextWatcherUtils.create(AirEditTextPageView$$Lambda$1.lambdaFactory$(this)));
        setSingleLine(false);
        this.textView.setPadding(this.textView.getPaddingLeft(), this.textView.getPaddingTop(), this.textView.getPaddingRight(), this.textView.getPaddingBottom());
    }

    public void setTitle(int titleRes) {
        this.titleView.setTitle(titleRes);
    }

    public void setTitle(CharSequence title) {
        this.titleView.setTitle(title);
    }

    public void setCaption(int captionRes) {
        this.titleView.setCaption(captionRes);
    }

    public void setCaption(CharSequence caption) {
        this.titleView.setCaption(caption);
    }

    public void setMaxLength(int maxLength2) {
        this.maxLength = maxLength2;
        validateTextLengthUpdateHint();
    }

    public void setMinLength(int minLength2) {
        this.minLength = minLength2;
        validateTextLengthUpdateHint();
    }

    public void setText(CharSequence text) {
        this.textView.setText(text);
        validateTextLengthUpdateHint();
    }

    public Editable getText() {
        return this.textView.getText();
    }

    public boolean isEmpty() {
        return this.textView.isEmpty();
    }

    public int length() {
        return this.textView.length();
    }

    @OnClick
    public void requestFocusAndKeyboard() {
        this.textView.requestFocus();
        this.textView.setSelection(this.textView.length());
        post(AirEditTextPageView$$Lambda$2.lambdaFactory$(this));
    }

    public void setHint(CharSequence hintText2) {
        this.hintText = hintText2;
        validateTextLengthUpdateHint();
    }

    public void setHint(int hintRes) {
        this.hintText = getContext().getString(hintRes);
        validateTextLengthUpdateHint();
    }

    public void setSingleLine(boolean singleLine) {
        this.textView.setInputType(singleLine ? INPUT_TYPE_SINGLE_LINE : INPUT_TYPE_MULTI_LINE);
        this.textView.setImeOptions(singleLine ? 6 : 1);
        this.textView.setSingleLine(singleLine);
        this.textView.setHorizontallyScrolling(false);
        this.textView.setMaxLines(Integer.MAX_VALUE);
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        this.textView.setEnabled(enabled);
    }

    public void setListener(Listener listener2) {
        this.listener = listener2;
    }

    public boolean isValid() {
        return this.validLength;
    }

    /* access modifiers changed from: private */
    public void validateTextLengthUpdateHint() {
        boolean hasChangedValidity;
        boolean showLengthError;
        this.textView.setHint(length() > 0 ? null : this.hintText);
        int inputLength = length();
        boolean validLength2 = isValidLength(inputLength, this.minLength, this.maxLength);
        if (hasMaxLength(this.maxLength)) {
            int remainingChars = this.maxLength - inputLength;
            if (inputLength <= 0 || validLength2) {
                showLengthError = false;
            } else {
                showLengthError = true;
            }
            this.characterCountView.setTextColor(ContextCompat.getColor(getContext(), showLengthError ? C0716R.color.n2_arches : C0716R.color.n2_text_color_muted));
            this.characterCountView.setText(String.valueOf(remainingChars));
            this.characterCountView.setVisibility(0);
            setViewBottomPadding(this.textView, this.withCountBottomSpacerPx + this.bottomBarPaddingPx);
        } else {
            this.characterCountView.setVisibility(4);
            setViewBottomPadding(this.textView, this.withoutCountBottomSpacerPx + this.bottomBarPaddingPx);
        }
        if (this.validLength != validLength2) {
            hasChangedValidity = true;
        } else {
            hasChangedValidity = false;
        }
        this.validLength = validLength2;
        if (hasChangedValidity && this.listener != null) {
            this.listener.validityChanged(validLength2);
        }
    }

    private static boolean isValidLength(int length, int minLength2, int maxLength2) {
        return length >= minLength2 && (!hasMaxLength(maxLength2) || length <= maxLength2);
    }

    private static boolean hasMaxLength(int maxLength2) {
        return maxLength2 != -1;
    }

    private static void setViewBottomPadding(View view, int paddingBottomPx) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), paddingBottomPx);
    }
}
