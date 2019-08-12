package com.facebook.react.views.textinput;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build.VERSION;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.text.method.QwertyKeyListener;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.views.text.CustomStyleSpan;
import com.facebook.react.views.text.ReactTagSpan;
import com.facebook.react.views.text.ReactTextUpdate;
import com.facebook.react.views.text.TextInlineImageSpan;
import com.facebook.react.views.view.ReactViewBackgroundDrawable;
import com.jumio.p311nv.data.NVStrings;
import java.util.ArrayList;
import java.util.Iterator;

public class ReactEditText extends EditText {
    /* access modifiers changed from: private */
    public static final KeyListener sKeyListener = QwertyKeyListener.getInstanceForFullKeyboard();
    private boolean mBlurOnSubmit;
    private boolean mContainsImages;
    private ContentSizeWatcher mContentSizeWatcher;
    private int mDefaultGravityHorizontal;
    private int mDefaultGravityVertical;
    private boolean mDetectScrollMovement = false;
    private boolean mDisableFullscreen;
    private final InputMethodManager mInputMethodManager;
    private boolean mIsJSSettingFocus;
    /* access modifiers changed from: private */
    public boolean mIsSettingTextFromJS;
    private final InternalKeyListener mKeyListener;
    /* access modifiers changed from: private */
    public ArrayList<TextWatcher> mListeners;
    private int mMostRecentEventCount;
    private int mNativeEventCount;
    private ReactViewBackgroundDrawable mReactBackgroundDrawable;
    private String mReturnKeyType;
    private SelectionWatcher mSelectionWatcher;
    private int mStagedInputType;
    private TextWatcherDelegator mTextWatcherDelegator;

    private static class InternalKeyListener implements KeyListener {
        private int mInputType = 0;

        public void setInputType(int inputType) {
            this.mInputType = inputType;
        }

        public int getInputType() {
            return this.mInputType;
        }

        public boolean onKeyDown(View view, Editable text, int keyCode, KeyEvent event) {
            return ReactEditText.sKeyListener.onKeyDown(view, text, keyCode, event);
        }

        public boolean onKeyUp(View view, Editable text, int keyCode, KeyEvent event) {
            return ReactEditText.sKeyListener.onKeyUp(view, text, keyCode, event);
        }

        public boolean onKeyOther(View view, Editable text, KeyEvent event) {
            return ReactEditText.sKeyListener.onKeyOther(view, text, event);
        }

        public void clearMetaKeyState(View view, Editable content, int states) {
            ReactEditText.sKeyListener.clearMetaKeyState(view, content, states);
        }
    }

    private class TextWatcherDelegator implements TextWatcher {
        private TextWatcherDelegator() {
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            if (!ReactEditText.this.mIsSettingTextFromJS && ReactEditText.this.mListeners != null) {
                Iterator it = ReactEditText.this.mListeners.iterator();
                while (it.hasNext()) {
                    ((TextWatcher) it.next()).beforeTextChanged(s, start, count, after);
                }
            }
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!ReactEditText.this.mIsSettingTextFromJS && ReactEditText.this.mListeners != null) {
                Iterator it = ReactEditText.this.mListeners.iterator();
                while (it.hasNext()) {
                    ((TextWatcher) it.next()).onTextChanged(s, start, before, count);
                }
            }
        }

        public void afterTextChanged(Editable s) {
            if (!ReactEditText.this.mIsSettingTextFromJS && ReactEditText.this.mListeners != null) {
                Iterator it = ReactEditText.this.mListeners.iterator();
                while (it.hasNext()) {
                    ((TextWatcher) it.next()).afterTextChanged(s);
                }
            }
        }
    }

    public ReactEditText(Context context) {
        super(context);
        setFocusableInTouchMode(false);
        this.mInputMethodManager = (InputMethodManager) Assertions.assertNotNull(getContext().getSystemService("input_method"));
        this.mDefaultGravityHorizontal = getGravity() & 8388615;
        this.mDefaultGravityVertical = getGravity() & 112;
        this.mNativeEventCount = 0;
        this.mMostRecentEventCount = 0;
        this.mIsSettingTextFromJS = false;
        this.mIsJSSettingFocus = false;
        this.mBlurOnSubmit = true;
        this.mDisableFullscreen = false;
        this.mListeners = null;
        this.mTextWatcherDelegator = null;
        this.mStagedInputType = getInputType();
        this.mKeyListener = new InternalKeyListener();
    }

    public boolean isLayoutRequested() {
        if (this.mContentSizeWatcher != null) {
            return isMultiline();
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (this.mContentSizeWatcher != null) {
            this.mContentSizeWatcher.onLayout();
        }
    }

    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case 0:
                this.mDetectScrollMovement = true;
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case 2:
                if (this.mDetectScrollMovement) {
                    if (!canScrollVertically(-1) && !canScrollVertically(1) && !canScrollHorizontally(-1) && !canScrollHorizontally(1)) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }
                    this.mDetectScrollMovement = false;
                    break;
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode != 66 || isMultiline()) {
            return super.onKeyUp(keyCode, event);
        }
        hideSoftKeyboard();
        return true;
    }

    public void clearFocus() {
        setFocusableInTouchMode(false);
        super.clearFocus();
        hideSoftKeyboard();
    }

    public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
        if (isFocused()) {
            return true;
        }
        if (!this.mIsJSSettingFocus) {
            return false;
        }
        setFocusableInTouchMode(true);
        boolean requestFocus = super.requestFocus(direction, previouslyFocusedRect);
        showSoftKeyboard();
        return requestFocus;
    }

    public void addTextChangedListener(TextWatcher watcher) {
        if (this.mListeners == null) {
            this.mListeners = new ArrayList<>();
            super.addTextChangedListener(getTextWatcherDelegator());
        }
        this.mListeners.add(watcher);
    }

    public void removeTextChangedListener(TextWatcher watcher) {
        if (this.mListeners != null) {
            this.mListeners.remove(watcher);
            if (this.mListeners.isEmpty()) {
                this.mListeners = null;
                super.removeTextChangedListener(getTextWatcherDelegator());
            }
        }
    }

    public void setContentSizeWatcher(ContentSizeWatcher contentSizeWatcher) {
        this.mContentSizeWatcher = contentSizeWatcher;
    }

    public void setSelection(int start, int end) {
        if (this.mMostRecentEventCount >= this.mNativeEventCount) {
            super.setSelection(start, end);
        }
    }

    /* access modifiers changed from: protected */
    public void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        if (this.mSelectionWatcher != null && hasFocus()) {
            this.mSelectionWatcher.onSelectionChanged(selStart, selEnd);
        }
    }

    /* access modifiers changed from: protected */
    public void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused && this.mSelectionWatcher != null) {
            this.mSelectionWatcher.onSelectionChanged(getSelectionStart(), getSelectionEnd());
        }
    }

    public void setSelectionWatcher(SelectionWatcher selectionWatcher) {
        this.mSelectionWatcher = selectionWatcher;
    }

    public void setBlurOnSubmit(boolean blurOnSubmit) {
        this.mBlurOnSubmit = blurOnSubmit;
    }

    public boolean getBlurOnSubmit() {
        return this.mBlurOnSubmit;
    }

    public void setDisableFullscreenUI(boolean disableFullscreenUI) {
        this.mDisableFullscreen = disableFullscreenUI;
        updateImeOptions();
    }

    public boolean getDisableFullscreenUI() {
        return this.mDisableFullscreen;
    }

    public void setReturnKeyType(String returnKeyType) {
        this.mReturnKeyType = returnKeyType;
        updateImeOptions();
    }

    public String getReturnKeyType() {
        return this.mReturnKeyType;
    }

    /* access modifiers changed from: 0000 */
    public int getStagedInputType() {
        return this.mStagedInputType;
    }

    /* access modifiers changed from: 0000 */
    public void setStagedInputType(int stagedInputType) {
        this.mStagedInputType = stagedInputType;
    }

    /* access modifiers changed from: 0000 */
    public void commitStagedInputType() {
        if (getInputType() != this.mStagedInputType) {
            setInputType(this.mStagedInputType);
        }
    }

    public void setInputType(int type) {
        Typeface tf = super.getTypeface();
        super.setInputType(type);
        this.mStagedInputType = type;
        super.setTypeface(tf);
        this.mKeyListener.setInputType(type);
        setKeyListener(this.mKeyListener);
    }

    public void requestFocusFromJS() {
        this.mIsJSSettingFocus = true;
        requestFocus();
        this.mIsJSSettingFocus = false;
    }

    /* access modifiers changed from: 0000 */
    public void clearFocusFromJS() {
        clearFocus();
    }

    public int incrementAndGetEventCounter() {
        int i = this.mNativeEventCount + 1;
        this.mNativeEventCount = i;
        return i;
    }

    public void maybeSetText(ReactTextUpdate reactTextUpdate) {
        this.mMostRecentEventCount = reactTextUpdate.getJsEventCounter();
        if (this.mMostRecentEventCount >= this.mNativeEventCount) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(reactTextUpdate.getText());
            manageSpans(spannableStringBuilder);
            this.mContainsImages = reactTextUpdate.containsImages();
            this.mIsSettingTextFromJS = true;
            getText().replace(0, length(), spannableStringBuilder);
            this.mIsSettingTextFromJS = false;
            if (VERSION.SDK_INT >= 23 && getBreakStrategy() != reactTextUpdate.getTextBreakStrategy()) {
                setBreakStrategy(reactTextUpdate.getTextBreakStrategy());
            }
        }
    }

    private void manageSpans(SpannableStringBuilder spannableStringBuilder) {
        Object[] spans = getText().getSpans(0, length(), Object.class);
        for (int spanIdx = 0; spanIdx < spans.length; spanIdx++) {
            if (ForegroundColorSpan.class.isInstance(spans[spanIdx]) || BackgroundColorSpan.class.isInstance(spans[spanIdx]) || AbsoluteSizeSpan.class.isInstance(spans[spanIdx]) || CustomStyleSpan.class.isInstance(spans[spanIdx]) || ReactTagSpan.class.isInstance(spans[spanIdx])) {
                getText().removeSpan(spans[spanIdx]);
            }
            if ((getText().getSpanFlags(spans[spanIdx]) & 33) == 33) {
                Object span = spans[spanIdx];
                int spanStart = getText().getSpanStart(spans[spanIdx]);
                int spanEnd = getText().getSpanEnd(spans[spanIdx]);
                int spanFlags = getText().getSpanFlags(spans[spanIdx]);
                getText().removeSpan(spans[spanIdx]);
                if (sameTextForSpan(getText(), spannableStringBuilder, spanStart, spanEnd)) {
                    spannableStringBuilder.setSpan(span, spanStart, spanEnd, spanFlags);
                }
            }
        }
    }

    private static boolean sameTextForSpan(Editable oldText, SpannableStringBuilder newText, int start, int end) {
        if (start > newText.length() || end > newText.length()) {
            return false;
        }
        for (int charIdx = start; charIdx < end; charIdx++) {
            if (oldText.charAt(charIdx) != newText.charAt(charIdx)) {
                return false;
            }
        }
        return true;
    }

    private boolean showSoftKeyboard() {
        return this.mInputMethodManager.showSoftInput(this, 0);
    }

    private void hideSoftKeyboard() {
        this.mInputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
    }

    private TextWatcherDelegator getTextWatcherDelegator() {
        if (this.mTextWatcherDelegator == null) {
            this.mTextWatcherDelegator = new TextWatcherDelegator();
        }
        return this.mTextWatcherDelegator;
    }

    private boolean isMultiline() {
        return (getInputType() & 131072) != 0;
    }

    /* access modifiers changed from: 0000 */
    public void setGravityHorizontal(int gravityHorizontal) {
        if (gravityHorizontal == 0) {
            gravityHorizontal = this.mDefaultGravityHorizontal;
        }
        setGravity((getGravity() & -8 & -8388616) | gravityHorizontal);
    }

    /* access modifiers changed from: 0000 */
    public void setGravityVertical(int gravityVertical) {
        if (gravityVertical == 0) {
            gravityVertical = this.mDefaultGravityVertical;
        }
        setGravity((getGravity() & -113) | gravityVertical);
    }

    private void updateImeOptions() {
        int returnKeyFlag = 6;
        if (this.mReturnKeyType != null) {
            String str = this.mReturnKeyType;
            char c = 65535;
            switch (str.hashCode()) {
                case -1273775369:
                    if (str.equals("previous")) {
                        c = 3;
                        break;
                    }
                    break;
                case -906336856:
                    if (str.equals(NVStrings.SEARCH)) {
                        c = 4;
                        break;
                    }
                    break;
                case 3304:
                    if (str.equals("go")) {
                        c = 0;
                        break;
                    }
                    break;
                case 3089282:
                    if (str.equals("done")) {
                        c = 6;
                        break;
                    }
                    break;
                case 3377907:
                    if (str.equals("next")) {
                        c = 1;
                        break;
                    }
                    break;
                case 3387192:
                    if (str.equals("none")) {
                        c = 2;
                        break;
                    }
                    break;
                case 3526536:
                    if (str.equals("send")) {
                        c = 5;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    returnKeyFlag = 2;
                    break;
                case 1:
                    returnKeyFlag = 5;
                    break;
                case 2:
                    returnKeyFlag = 1;
                    break;
                case 3:
                    returnKeyFlag = 7;
                    break;
                case 4:
                    returnKeyFlag = 3;
                    break;
                case 5:
                    returnKeyFlag = 4;
                    break;
                case 6:
                    returnKeyFlag = 6;
                    break;
            }
        }
        if (this.mDisableFullscreen) {
            setImeOptions(33554432 | returnKeyFlag);
        } else {
            setImeOptions(returnKeyFlag);
        }
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned text = getText();
            for (TextInlineImageSpan span : (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class)) {
                if (span.getDrawable() == drawable) {
                    return true;
                }
            }
        }
        return super.verifyDrawable(drawable);
    }

    public void invalidateDrawable(Drawable drawable) {
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned text = getText();
            for (TextInlineImageSpan span : (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class)) {
                if (span.getDrawable() == drawable) {
                    invalidate();
                }
            }
        }
        super.invalidateDrawable(drawable);
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned text = getText();
            for (TextInlineImageSpan span : (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class)) {
                span.onDetachedFromWindow();
            }
        }
    }

    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned text = getText();
            for (TextInlineImageSpan span : (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class)) {
                span.onStartTemporaryDetach();
            }
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned text = getText();
            for (TextInlineImageSpan span : (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class)) {
                span.onAttachedToWindow();
            }
        }
    }

    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned text = getText();
            for (TextInlineImageSpan span : (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class)) {
                span.onFinishTemporaryDetach();
            }
        }
    }

    public void setBackgroundColor(int color) {
        if (color != 0 || this.mReactBackgroundDrawable != null) {
            getOrCreateReactViewBackground().setColor(color);
        }
    }

    public void setBorderWidth(int position, float width) {
        getOrCreateReactViewBackground().setBorderWidth(position, width);
    }

    public void setBorderColor(int position, float color, float alpha) {
        getOrCreateReactViewBackground().setBorderColor(position, color, alpha);
    }

    public void setBorderRadius(float borderRadius) {
        getOrCreateReactViewBackground().setRadius(borderRadius);
    }

    public void setBorderRadius(float borderRadius, int position) {
        getOrCreateReactViewBackground().setRadius(borderRadius, position);
    }

    public void setBorderStyle(String style) {
        getOrCreateReactViewBackground().setBorderStyle(style);
    }

    private ReactViewBackgroundDrawable getOrCreateReactViewBackground() {
        if (this.mReactBackgroundDrawable == null) {
            this.mReactBackgroundDrawable = new ReactViewBackgroundDrawable();
            Drawable backgroundDrawable = getBackground();
            super.setBackground(null);
            if (backgroundDrawable == null) {
                super.setBackground(this.mReactBackgroundDrawable);
            } else {
                super.setBackground(new LayerDrawable(new Drawable[]{this.mReactBackgroundDrawable, backgroundDrawable}));
            }
        }
        return this.mReactBackgroundDrawable;
    }
}
