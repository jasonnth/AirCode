package com.facebook.react.views.textinput;

import android.graphics.PorterDuff.Mode;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.imagehelper.ResourceDrawableIdHelper;
import com.facebook.react.views.text.DefaultStyleValuesUtil;
import com.facebook.react.views.text.ReactTextUpdate;
import com.facebook.react.views.text.TextInlineImageSpan;
import com.facebook.yoga.YogaConstants;
import java.util.LinkedList;
import java.util.Map;
import org.spongycastle.crypto.tls.CipherSuite;

@ReactModule(name = "AndroidTextInput")
public class ReactTextInputManager extends BaseViewManager<ReactEditText, LayoutShadowNode> {
    private static final int BLUR_TEXT_INPUT = 2;
    private static final InputFilter[] EMPTY_FILTERS = new InputFilter[0];
    private static final int FOCUS_TEXT_INPUT = 1;
    private static final int IME_ACTION_ID = 1648;
    private static final int INPUT_TYPE_KEYBOARD_NUMBERED = 12290;
    private static final String KEYBOARD_TYPE_EMAIL_ADDRESS = "email-address";
    private static final String KEYBOARD_TYPE_NUMERIC = "numeric";
    private static final String KEYBOARD_TYPE_PHONE_PAD = "phone-pad";
    protected static final String REACT_CLASS = "AndroidTextInput";
    private static final int[] SPACING_TYPES = {8, 0, 2, 1, 3};
    private static final int UNSET = -1;

    private class ReactContentSizeWatcher implements ContentSizeWatcher {
        private ReactEditText mEditText;
        private EventDispatcher mEventDispatcher;
        private int mPreviousContentHeight = 0;
        private int mPreviousContentWidth = 0;

        public ReactContentSizeWatcher(ReactEditText editText) {
            this.mEditText = editText;
            this.mEventDispatcher = ((UIManagerModule) ((ReactContext) editText.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher();
        }

        public void onLayout() {
            int contentWidth = this.mEditText.getWidth();
            int contentHeight = this.mEditText.getHeight();
            if (this.mEditText.getLayout() != null) {
                contentWidth = this.mEditText.getCompoundPaddingLeft() + this.mEditText.getLayout().getWidth() + this.mEditText.getCompoundPaddingRight();
                contentHeight = this.mEditText.getCompoundPaddingTop() + this.mEditText.getLayout().getHeight() + this.mEditText.getCompoundPaddingBottom();
            }
            if (contentWidth != this.mPreviousContentWidth || contentHeight != this.mPreviousContentHeight) {
                this.mPreviousContentHeight = contentHeight;
                this.mPreviousContentWidth = contentWidth;
                this.mEventDispatcher.dispatchEvent(new ReactContentSizeChangedEvent(this.mEditText.getId(), PixelUtil.toDIPFromPixel((float) contentWidth), PixelUtil.toDIPFromPixel((float) contentHeight)));
            }
        }
    }

    private class ReactSelectionWatcher implements SelectionWatcher {
        private EventDispatcher mEventDispatcher;
        private int mPreviousSelectionEnd;
        private int mPreviousSelectionStart;
        private ReactEditText mReactEditText;

        public ReactSelectionWatcher(ReactEditText editText) {
            this.mReactEditText = editText;
            this.mEventDispatcher = ((UIManagerModule) ((ReactContext) editText.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher();
        }

        public void onSelectionChanged(int start, int end) {
            if (this.mPreviousSelectionStart != start || this.mPreviousSelectionEnd != end) {
                this.mEventDispatcher.dispatchEvent(new ReactTextInputSelectionEvent(this.mReactEditText.getId(), start, end));
                this.mPreviousSelectionStart = start;
                this.mPreviousSelectionEnd = end;
            }
        }
    }

    private class ReactTextInputTextWatcher implements TextWatcher {
        private ReactEditText mEditText;
        private EventDispatcher mEventDispatcher;
        private String mPreviousText = null;

        public ReactTextInputTextWatcher(ReactContext reactContext, ReactEditText editText) {
            this.mEventDispatcher = ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher();
            this.mEditText = editText;
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            this.mPreviousText = s.toString();
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (count != 0 || before != 0) {
                Assertions.assertNotNull(this.mPreviousText);
                String newText = s.toString().substring(start, start + count);
                String oldText = this.mPreviousText.substring(start, start + before);
                if (count != before || !newText.equals(oldText)) {
                    int contentWidth = this.mEditText.getWidth();
                    int contentHeight = this.mEditText.getHeight();
                    if (this.mEditText.getLayout() != null) {
                        contentWidth = this.mEditText.getCompoundPaddingLeft() + this.mEditText.getLayout().getWidth() + this.mEditText.getCompoundPaddingRight();
                        contentHeight = this.mEditText.getCompoundPaddingTop() + this.mEditText.getLayout().getHeight() + this.mEditText.getCompoundPaddingTop();
                    }
                    this.mEventDispatcher.dispatchEvent(new ReactTextChangedEvent(this.mEditText.getId(), s.toString(), PixelUtil.toDIPFromPixel((float) contentWidth), PixelUtil.toDIPFromPixel((float) contentHeight), this.mEditText.incrementAndGetEventCounter()));
                    this.mEventDispatcher.dispatchEvent(new ReactTextInputEvent(this.mEditText.getId(), newText, oldText, start, start + before));
                }
            }
        }

        public void afterTextChanged(Editable s) {
        }
    }

    public String getName() {
        return REACT_CLASS;
    }

    public ReactEditText createViewInstance(ThemedReactContext context) {
        ReactEditText editText = new ReactEditText(context);
        editText.setInputType(-131073 & editText.getInputType());
        editText.setReturnKeyType("done");
        editText.setTextSize(0, (float) ((int) Math.ceil((double) PixelUtil.toPixelFromSP(14.0f))));
        return editText;
    }

    public LayoutShadowNode createShadowNodeInstance() {
        return new ReactTextInputShadowNode();
    }

    public Class<? extends LayoutShadowNode> getShadowNodeClass() {
        return ReactTextInputShadowNode.class;
    }

    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder().put("topSubmitEditing", MapBuilder.m1882of("phasedRegistrationNames", MapBuilder.m1883of("bubbled", "onSubmitEditing", "captured", "onSubmitEditingCapture"))).put("topEndEditing", MapBuilder.m1882of("phasedRegistrationNames", MapBuilder.m1883of("bubbled", "onEndEditing", "captured", "onEndEditingCapture"))).put(ReactTextInputEvent.EVENT_NAME, MapBuilder.m1882of("phasedRegistrationNames", MapBuilder.m1883of("bubbled", "onTextInput", "captured", "onTextInputCapture"))).put("topFocus", MapBuilder.m1882of("phasedRegistrationNames", MapBuilder.m1883of("bubbled", "onFocus", "captured", "onFocusCapture"))).put("topBlur", MapBuilder.m1882of("phasedRegistrationNames", MapBuilder.m1883of("bubbled", "onBlur", "captured", "onBlurCapture"))).build();
    }

    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.m1883of("focusTextInput", Integer.valueOf(1), "blurTextInput", Integer.valueOf(2));
    }

    public void receiveCommand(ReactEditText reactEditText, int commandId, ReadableArray args) {
        switch (commandId) {
            case 1:
                reactEditText.requestFocusFromJS();
                return;
            case 2:
                reactEditText.clearFocusFromJS();
                return;
            default:
                return;
        }
    }

    public void updateExtraData(ReactEditText view, Object extraData) {
        if (extraData instanceof float[]) {
            float[] padding = (float[]) extraData;
            view.setPadding((int) Math.floor((double) padding[0]), (int) Math.floor((double) padding[1]), (int) Math.floor((double) padding[2]), (int) Math.floor((double) padding[3]));
        } else if (extraData instanceof ReactTextUpdate) {
            ReactTextUpdate update = (ReactTextUpdate) extraData;
            if (update.containsImages()) {
                TextInlineImageSpan.possiblyUpdateInlineImageSpans(update.getText(), view);
            }
            view.maybeSetText(update);
        }
    }

    @ReactProp(defaultFloat = 14.0f, name = "fontSize")
    public void setFontSize(ReactEditText view, float fontSize) {
        view.setTextSize(0, (float) ((int) Math.ceil((double) PixelUtil.toPixelFromSP(fontSize))));
    }

    @ReactProp(name = "fontFamily")
    public void setFontFamily(ReactEditText view, String fontFamily) {
        int style = 0;
        if (view.getTypeface() != null) {
            style = view.getTypeface().getStyle();
        }
        view.setTypeface(Typeface.create(fontFamily, style));
    }

    @ReactProp(name = "fontWeight")
    public void setFontWeight(ReactEditText view, String fontWeightString) {
        int fontWeightNumeric;
        if (fontWeightString != null) {
            fontWeightNumeric = parseNumericFontWeight(fontWeightString);
        } else {
            fontWeightNumeric = -1;
        }
        int fontWeight = -1;
        if (fontWeightNumeric >= 500 || "bold".equals(fontWeightString)) {
            fontWeight = 1;
        } else if ("normal".equals(fontWeightString) || (fontWeightNumeric != -1 && fontWeightNumeric < 500)) {
            fontWeight = 0;
        }
        Typeface currentTypeface = view.getTypeface();
        if (currentTypeface == null) {
            currentTypeface = Typeface.DEFAULT;
        }
        if (fontWeight != currentTypeface.getStyle()) {
            view.setTypeface(currentTypeface, fontWeight);
        }
    }

    @ReactProp(name = "fontStyle")
    public void setFontStyle(ReactEditText view, String fontStyleString) {
        int fontStyle = -1;
        if ("italic".equals(fontStyleString)) {
            fontStyle = 2;
        } else if ("normal".equals(fontStyleString)) {
            fontStyle = 0;
        }
        Typeface currentTypeface = view.getTypeface();
        if (currentTypeface == null) {
            currentTypeface = Typeface.DEFAULT;
        }
        if (fontStyle != currentTypeface.getStyle()) {
            view.setTypeface(currentTypeface, fontStyle);
        }
    }

    @ReactProp(name = "selection")
    public void setSelection(ReactEditText view, ReadableMap selection) {
        if (selection != null && selection.hasKey("start") && selection.hasKey("end")) {
            view.setSelection(selection.getInt("start"), selection.getInt("end"));
        }
    }

    @ReactProp(defaultBoolean = false, name = "onSelectionChange")
    public void setOnSelectionChange(ReactEditText view, boolean onSelectionChange) {
        if (onSelectionChange) {
            view.setSelectionWatcher(new ReactSelectionWatcher(view));
        } else {
            view.setSelectionWatcher(null);
        }
    }

    @ReactProp(defaultBoolean = true, name = "blurOnSubmit")
    public void setBlurOnSubmit(ReactEditText view, boolean blurOnSubmit) {
        view.setBlurOnSubmit(blurOnSubmit);
    }

    @ReactProp(defaultBoolean = false, name = "onContentSizeChange")
    public void setOnContentSizeChange(ReactEditText view, boolean onContentSizeChange) {
        if (onContentSizeChange) {
            view.setContentSizeWatcher(new ReactContentSizeWatcher(view));
        } else {
            view.setContentSizeWatcher(null);
        }
    }

    @ReactProp(name = "placeholder")
    public void setPlaceholder(ReactEditText view, String placeholder) {
        view.setHint(placeholder);
    }

    @ReactProp(customType = "Color", name = "placeholderTextColor")
    public void setPlaceholderTextColor(ReactEditText view, Integer color) {
        if (color == null) {
            view.setHintTextColor(DefaultStyleValuesUtil.getDefaultTextColorHint(view.getContext()));
        } else {
            view.setHintTextColor(color.intValue());
        }
    }

    @ReactProp(customType = "Color", name = "selectionColor")
    public void setSelectionColor(ReactEditText view, Integer color) {
        if (color == null) {
            view.setHighlightColor(DefaultStyleValuesUtil.getDefaultTextColorHighlight(view.getContext()));
        } else {
            view.setHighlightColor(color.intValue());
        }
    }

    @ReactProp(defaultBoolean = false, name = "selectTextOnFocus")
    public void setSelectTextOnFocus(ReactEditText view, boolean selectTextOnFocus) {
        view.setSelectAllOnFocus(selectTextOnFocus);
    }

    @ReactProp(customType = "Color", name = "color")
    public void setColor(ReactEditText view, Integer color) {
        if (color == null) {
            view.setTextColor(DefaultStyleValuesUtil.getDefaultTextColor(view.getContext()));
        } else {
            view.setTextColor(color.intValue());
        }
    }

    @ReactProp(customType = "Color", name = "underlineColorAndroid")
    public void setUnderlineColor(ReactEditText view, Integer underlineColor) {
        if (underlineColor == null) {
            view.getBackground().clearColorFilter();
        } else {
            view.getBackground().setColorFilter(underlineColor.intValue(), Mode.SRC_IN);
        }
    }

    @ReactProp(name = "textAlign")
    public void setTextAlign(ReactEditText view, String textAlign) {
        if (textAlign == null || "auto".equals(textAlign)) {
            view.setGravityHorizontal(0);
        } else if (ViewProps.LEFT.equals(textAlign)) {
            view.setGravityHorizontal(3);
        } else if (ViewProps.RIGHT.equals(textAlign)) {
            view.setGravityHorizontal(5);
        } else if ("center".equals(textAlign)) {
            view.setGravityHorizontal(1);
        } else if ("justify".equals(textAlign)) {
            view.setGravityHorizontal(3);
        } else {
            throw new JSApplicationIllegalArgumentException("Invalid textAlign: " + textAlign);
        }
    }

    @ReactProp(name = "textAlignVertical")
    public void setTextAlignVertical(ReactEditText view, String textAlignVertical) {
        if (textAlignVertical == null || "auto".equals(textAlignVertical)) {
            view.setGravityVertical(0);
        } else if (ViewProps.TOP.equals(textAlignVertical)) {
            view.setGravityVertical(48);
        } else if (ViewProps.BOTTOM.equals(textAlignVertical)) {
            view.setGravityVertical(80);
        } else if ("center".equals(textAlignVertical)) {
            view.setGravityVertical(16);
        } else {
            throw new JSApplicationIllegalArgumentException("Invalid textAlignVertical: " + textAlignVertical);
        }
    }

    @ReactProp(name = "inlineImageLeft")
    public void setInlineImageLeft(ReactEditText view, String resource) {
        view.setCompoundDrawablesWithIntrinsicBounds(ResourceDrawableIdHelper.getInstance().getResourceDrawableId(view.getContext(), resource), 0, 0, 0);
    }

    @ReactProp(name = "inlineImagePadding")
    public void setInlineImagePadding(ReactEditText view, int padding) {
        view.setCompoundDrawablePadding(padding);
    }

    @ReactProp(defaultBoolean = true, name = "editable")
    public void setEditable(ReactEditText view, boolean editable) {
        view.setEnabled(editable);
    }

    @ReactProp(defaultInt = 1, name = "numberOfLines")
    public void setNumLines(ReactEditText view, int numLines) {
        view.setLines(numLines);
    }

    @ReactProp(name = "maxLength")
    public void setMaxLength(ReactEditText view, Integer maxLength) {
        InputFilter[] currentFilters = view.getFilters();
        InputFilter[] newFilters = EMPTY_FILTERS;
        if (maxLength == null) {
            if (currentFilters.length > 0) {
                LinkedList<InputFilter> list = new LinkedList<>();
                for (int i = 0; i < currentFilters.length; i++) {
                    if (!(currentFilters[i] instanceof LengthFilter)) {
                        list.add(currentFilters[i]);
                    }
                }
                if (!list.isEmpty()) {
                    newFilters = (InputFilter[]) list.toArray(new InputFilter[list.size()]);
                }
            }
        } else if (currentFilters.length > 0) {
            newFilters = currentFilters;
            boolean replaced = false;
            for (int i2 = 0; i2 < currentFilters.length; i2++) {
                if (currentFilters[i2] instanceof LengthFilter) {
                    currentFilters[i2] = new LengthFilter(maxLength.intValue());
                    replaced = true;
                }
            }
            if (!replaced) {
                newFilters = new InputFilter[(currentFilters.length + 1)];
                System.arraycopy(currentFilters, 0, newFilters, 0, currentFilters.length);
                currentFilters[currentFilters.length] = new LengthFilter(maxLength.intValue());
            }
        } else {
            newFilters = new InputFilter[]{new LengthFilter(maxLength.intValue())};
        }
        view.setFilters(newFilters);
    }

    @ReactProp(name = "autoCorrect")
    public void setAutoCorrect(ReactEditText view, Boolean autoCorrect) {
        int i = autoCorrect != null ? autoCorrect.booleanValue() ? 32768 : 524288 : 0;
        updateStagedInputTypeFlag(view, 557056, i);
    }

    @ReactProp(defaultBoolean = false, name = "multiline")
    public void setMultiline(ReactEditText view, boolean multiline) {
        int i;
        int i2 = 131072;
        if (multiline) {
            i = 0;
        } else {
            i = 131072;
        }
        if (!multiline) {
            i2 = 0;
        }
        updateStagedInputTypeFlag(view, i, i2);
    }

    @ReactProp(defaultBoolean = false, name = "secureTextEntry")
    public void setSecureTextEntry(ReactEditText view, boolean password) {
        int i = 0;
        int i2 = password ? 0 : CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA;
        if (password) {
            i = 128;
        }
        updateStagedInputTypeFlag(view, i2, i);
        checkPasswordType(view);
    }

    @ReactProp(name = "autoCapitalize")
    public void setAutoCapitalize(ReactEditText view, int autoCapitalize) {
        updateStagedInputTypeFlag(view, 28672, autoCapitalize);
    }

    @ReactProp(name = "keyboardType")
    public void setKeyboardType(ReactEditText view, String keyboardType) {
        int flagsToSet = 1;
        if (KEYBOARD_TYPE_NUMERIC.equalsIgnoreCase(keyboardType)) {
            flagsToSet = INPUT_TYPE_KEYBOARD_NUMBERED;
        } else if (KEYBOARD_TYPE_EMAIL_ADDRESS.equalsIgnoreCase(keyboardType)) {
            flagsToSet = 33;
        } else if (KEYBOARD_TYPE_PHONE_PAD.equalsIgnoreCase(keyboardType)) {
            flagsToSet = 3;
        }
        updateStagedInputTypeFlag(view, 12323, flagsToSet);
        checkPasswordType(view);
    }

    @ReactProp(name = "returnKeyType")
    public void setReturnKeyType(ReactEditText view, String returnKeyType) {
        view.setReturnKeyType(returnKeyType);
    }

    @ReactProp(defaultBoolean = false, name = "disableFullscreenUI")
    public void setDisableFullscreenUI(ReactEditText view, boolean disableFullscreenUI) {
        view.setDisableFullscreenUI(disableFullscreenUI);
    }

    @ReactProp(name = "returnKeyLabel")
    public void setReturnKeyLabel(ReactEditText view, String returnKeyLabel) {
        view.setImeActionLabel(returnKeyLabel, IME_ACTION_ID);
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderRadius", "borderTopLeftRadius", "borderTopRightRadius", "borderBottomRightRadius", "borderBottomLeftRadius"})
    public void setBorderRadius(ReactEditText view, int index, float borderRadius) {
        if (!YogaConstants.isUndefined(borderRadius)) {
            borderRadius = PixelUtil.toPixelFromDIP(borderRadius);
        }
        if (index == 0) {
            view.setBorderRadius(borderRadius);
        } else {
            view.setBorderRadius(borderRadius, index - 1);
        }
    }

    @ReactProp(name = "borderStyle")
    public void setBorderStyle(ReactEditText view, String borderStyle) {
        view.setBorderStyle(borderStyle);
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderWidth", "borderLeftWidth", "borderRightWidth", "borderTopWidth", "borderBottomWidth"})
    public void setBorderWidth(ReactEditText view, int index, float width) {
        if (!YogaConstants.isUndefined(width)) {
            width = PixelUtil.toPixelFromDIP(width);
        }
        view.setBorderWidth(SPACING_TYPES[index], width);
    }

    @ReactPropGroup(customType = "Color", names = {"borderColor", "borderLeftColor", "borderRightColor", "borderTopColor", "borderBottomColor"})
    public void setBorderColor(ReactEditText view, int index, Integer color) {
        float alphaComponent = Float.NaN;
        float rgbComponent = color == null ? Float.NaN : (float) (color.intValue() & 16777215);
        if (color != null) {
            alphaComponent = (float) (color.intValue() >>> 24);
        }
        view.setBorderColor(SPACING_TYPES[index], rgbComponent, alphaComponent);
    }

    /* access modifiers changed from: protected */
    public void onAfterUpdateTransaction(ReactEditText view) {
        super.onAfterUpdateTransaction(view);
        view.commitStagedInputType();
    }

    private static void checkPasswordType(ReactEditText view) {
        if ((view.getStagedInputType() & INPUT_TYPE_KEYBOARD_NUMBERED) != 0 && (view.getStagedInputType() & 128) != 0) {
            updateStagedInputTypeFlag(view, 128, 16);
        }
    }

    private static int parseNumericFontWeight(String fontWeightString) {
        if (fontWeightString.length() != 3 || !fontWeightString.endsWith("00") || fontWeightString.charAt(0) > '9' || fontWeightString.charAt(0) < '1') {
            return -1;
        }
        return (fontWeightString.charAt(0) - '0') * 100;
    }

    private static void updateStagedInputTypeFlag(ReactEditText view, int flagsToUnset, int flagsToSet) {
        view.setStagedInputType((view.getStagedInputType() & (flagsToUnset ^ -1)) | flagsToSet);
    }

    /* access modifiers changed from: protected */
    public void addEventEmitters(final ThemedReactContext reactContext, final ReactEditText editText) {
        editText.addTextChangedListener(new ReactTextInputTextWatcher(reactContext, editText));
        editText.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                EventDispatcher eventDispatcher = ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher();
                if (hasFocus) {
                    eventDispatcher.dispatchEvent(new ReactTextInputFocusEvent(editText.getId()));
                    return;
                }
                eventDispatcher.dispatchEvent(new ReactTextInputBlurEvent(editText.getId()));
                eventDispatcher.dispatchEvent(new ReactTextInputEndEditingEvent(editText.getId(), editText.getText().toString()));
            }
        });
        editText.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent keyEvent) {
                if ((actionId & 255) > 0 || actionId == 0) {
                    ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(new ReactTextInputSubmitEditingEvent(editText.getId(), editText.getText().toString()));
                }
                if (actionId != 5 && actionId != 7) {
                    return !editText.getBlurOnSubmit();
                }
                if (editText.getBlurOnSubmit()) {
                    editText.clearFocus();
                }
                return true;
            }
        });
    }

    public Map getExportedViewConstants() {
        return MapBuilder.m1882of("AutoCapitalizationType", MapBuilder.m1885of("none", Integer.valueOf(0), "characters", Integer.valueOf(4096), "words", Integer.valueOf(8192), "sentences", Integer.valueOf(16384)));
    }
}
