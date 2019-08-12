package com.airbnb.android.core.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.p000v4.app.FragmentManager;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.CoreGraph;
import com.airbnb.android.core.analytics.SignUpLoginAnalytics;
import com.airbnb.android.core.fragments.CallingCodeDialogFragment;
import com.airbnb.android.core.interfaces.CallingCodeSelectedListener;
import com.airbnb.android.core.models.AirPhone;
import com.airbnb.android.core.presenters.CountryCodeItem;
import com.airbnb.android.core.utils.PhoneUtil;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.android.utils.AndroidVersion;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.SimpleTextWatcher;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirEditTextView;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import java.util.Locale;

public class PhoneNumberInputSheet extends LinearLayout {
    @BindView
    TextView callingCodeButton;
    private final CallingCodeSelectedListener callingCodeListener;
    /* access modifiers changed from: private */
    public String countryCode;
    @BindView
    LinearLayout editTextContainer;
    @BindView
    TextView hintText;
    private TextWatcher phoneFormattingTextWatcher;
    /* access modifiers changed from: private */
    public PhoneNumber phoneNumber;
    @BindView
    EditText phoneNumberEditText;
    /* access modifiers changed from: private */
    public PhoneNumberInputViewDelegate phoneNumberInputViewDelegate;
    PhoneNumberUtil phoneNumberUtil;
    private final SimpleTextWatcher phoneNumberWatcher;
    PhoneUtil phoneUtil;

    public interface PhoneNumberInputViewDelegate {
        void afterPhoneNumberTextChanged(AirPhone airPhone);

        @Deprecated
        void afterPhoneNumberTextChanged(String str, String str2);

        FragmentManager getFragmentManager();
    }

    public enum Style {
        BABU(R.style.n2_SheetInputTextHintLabel, R.style.n2_PhoneNumberCountrySelector, R.style.n2_SheetInputTextEditText_PhoneInput, R.drawable.n2_white_cursor_drawable, C0716R.C0717drawable.n2_sheet_input_text_background),
        WHITE(R.style.n2_SmallText, R.style.n2_SmallText, C0716R.C0719style.n2_TitleText3, R.drawable.n2_black_cursor_drawable, C0716R.C0717drawable.n2_input_text_white_background);
        
        final int backgroundRes;
        final int callingCodeStyleRes;
        final int cursorDrawableRes;
        final int hintStyleRes;
        final int phoneNumberStyleRes;

        private Style(int hintStyleRes2, int callingCodeStyleRes2, int phoneNumberStyleRes2, int cursorDrawableRes2, int backgroundRes2) {
            this.hintStyleRes = hintStyleRes2;
            this.callingCodeStyleRes = callingCodeStyleRes2;
            this.phoneNumberStyleRes = phoneNumberStyleRes2;
            this.cursorDrawableRes = cursorDrawableRes2;
            this.backgroundRes = backgroundRes2;
        }

        public void setStyle(PhoneNumberInputSheet phoneNumberInput) {
            Context context = phoneNumberInput.getContext();
            phoneNumberInput.hintText.setTextAppearance(context, this.hintStyleRes);
            phoneNumberInput.callingCodeButton.setTextAppearance(context, this.callingCodeStyleRes);
            phoneNumberInput.phoneNumberEditText.setTextAppearance(context, this.phoneNumberStyleRes);
            if (phoneNumberInput.phoneNumberEditText instanceof AirEditTextView) {
                ((AirEditTextView) phoneNumberInput.phoneNumberEditText).setCursorDrawableRes(this.cursorDrawableRes);
            }
            phoneNumberInput.editTextContainer.setBackgroundResource(this.backgroundRes);
        }
    }

    public PhoneNumberInputSheet(Context context) {
        super(context);
        this.callingCodeListener = PhoneNumberInputSheet$$Lambda$1.lambdaFactory$(this);
        this.phoneNumberWatcher = new SimpleTextWatcher() {
            public void afterTextChanged(Editable s) {
                try {
                    PhoneNumberInputSheet.this.phoneNumber = PhoneNumberInputSheet.this.phoneNumberUtil.parse(s.toString(), PhoneNumberInputSheet.this.countryCode);
                    String phoneNumberText = PhoneNumberInputSheet.this.phoneNumberUtil.format(PhoneNumberInputSheet.this.phoneNumber, PhoneNumberFormat.E164);
                    String phoneNumberTextDisplay = PhoneNumberInputSheet.this.formatNumber(PhoneNumberInputSheet.this.phoneNumber);
                    String rawPhoneInputText = String.valueOf(PhoneNumberInputSheet.this.phoneNumber.getNationalNumber());
                    if (TextUtils.isEmpty(phoneNumberText)) {
                        phoneNumberText = s.toString();
                    }
                    String phoneNumberText2 = PhoneUtil.removeLeadingPlusSignIfNecessary(phoneNumberText);
                    AirPhone airPhone = AirPhone.builder().phoneInputText(rawPhoneInputText).formattedPhone(phoneNumberText2).phoneDisplayText(phoneNumberTextDisplay).build();
                    PhoneNumberInputSheet.this.phoneNumberInputViewDelegate.afterPhoneNumberTextChanged(phoneNumberText2, phoneNumberTextDisplay);
                    PhoneNumberInputSheet.this.phoneNumberInputViewDelegate.afterPhoneNumberTextChanged(airPhone);
                } catch (NumberParseException e) {
                }
            }
        };
        init(context, null);
    }

    public PhoneNumberInputSheet(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.callingCodeListener = PhoneNumberInputSheet$$Lambda$2.lambdaFactory$(this);
        this.phoneNumberWatcher = new SimpleTextWatcher() {
            public void afterTextChanged(Editable s) {
                try {
                    PhoneNumberInputSheet.this.phoneNumber = PhoneNumberInputSheet.this.phoneNumberUtil.parse(s.toString(), PhoneNumberInputSheet.this.countryCode);
                    String phoneNumberText = PhoneNumberInputSheet.this.phoneNumberUtil.format(PhoneNumberInputSheet.this.phoneNumber, PhoneNumberFormat.E164);
                    String phoneNumberTextDisplay = PhoneNumberInputSheet.this.formatNumber(PhoneNumberInputSheet.this.phoneNumber);
                    String rawPhoneInputText = String.valueOf(PhoneNumberInputSheet.this.phoneNumber.getNationalNumber());
                    if (TextUtils.isEmpty(phoneNumberText)) {
                        phoneNumberText = s.toString();
                    }
                    String phoneNumberText2 = PhoneUtil.removeLeadingPlusSignIfNecessary(phoneNumberText);
                    AirPhone airPhone = AirPhone.builder().phoneInputText(rawPhoneInputText).formattedPhone(phoneNumberText2).phoneDisplayText(phoneNumberTextDisplay).build();
                    PhoneNumberInputSheet.this.phoneNumberInputViewDelegate.afterPhoneNumberTextChanged(phoneNumberText2, phoneNumberTextDisplay);
                    PhoneNumberInputSheet.this.phoneNumberInputViewDelegate.afterPhoneNumberTextChanged(airPhone);
                } catch (NumberParseException e) {
                }
            }
        };
        init(context, attrs);
    }

    public PhoneNumberInputSheet(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.callingCodeListener = PhoneNumberInputSheet$$Lambda$3.lambdaFactory$(this);
        this.phoneNumberWatcher = new SimpleTextWatcher() {
            public void afterTextChanged(Editable s) {
                try {
                    PhoneNumberInputSheet.this.phoneNumber = PhoneNumberInputSheet.this.phoneNumberUtil.parse(s.toString(), PhoneNumberInputSheet.this.countryCode);
                    String phoneNumberText = PhoneNumberInputSheet.this.phoneNumberUtil.format(PhoneNumberInputSheet.this.phoneNumber, PhoneNumberFormat.E164);
                    String phoneNumberTextDisplay = PhoneNumberInputSheet.this.formatNumber(PhoneNumberInputSheet.this.phoneNumber);
                    String rawPhoneInputText = String.valueOf(PhoneNumberInputSheet.this.phoneNumber.getNationalNumber());
                    if (TextUtils.isEmpty(phoneNumberText)) {
                        phoneNumberText = s.toString();
                    }
                    String phoneNumberText2 = PhoneUtil.removeLeadingPlusSignIfNecessary(phoneNumberText);
                    AirPhone airPhone = AirPhone.builder().phoneInputText(rawPhoneInputText).formattedPhone(phoneNumberText2).phoneDisplayText(phoneNumberTextDisplay).build();
                    PhoneNumberInputSheet.this.phoneNumberInputViewDelegate.afterPhoneNumberTextChanged(phoneNumberText2, phoneNumberTextDisplay);
                    PhoneNumberInputSheet.this.phoneNumberInputViewDelegate.afterPhoneNumberTextChanged(airPhone);
                } catch (NumberParseException e) {
                }
            }
        };
        init(context, attrs);
    }

    public String getInputText() {
        return this.phoneNumberEditText.getText().toString();
    }

    public boolean isPhoneNumberValid() {
        return this.phoneNumber != null && this.phoneNumberUtil.isValidNumberForRegion(this.phoneNumber, this.countryCode);
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    private void init(Context context, AttributeSet attrs) {
        setOrientation(1);
        ((CoreGraph) CoreApplication.instance(getContext()).component()).inject(this);
        setupLayout(context, attrs);
    }

    /* access modifiers changed from: protected */
    public void setupLayout(Context context, AttributeSet attrs) {
        View view = inflate(context, C0716R.layout.phone_number_input_sheet, this);
        setOrientation(1);
        ButterKnife.bind(this, view);
        setupAttributes(attrs);
    }

    public void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, C0716R.styleable.n2_PhoneNumberInputSheet, 0, 0);
        ViewUtils.setVisibleIf((View) this.callingCodeButton, a.getBoolean(C0716R.styleable.n2_PhoneNumberInputSheet_n2_showCountrySelection, true));
        Style.BABU.setStyle(this);
        a.recycle();
    }

    public void onNewCountryCodeSelected(CountryCodeItem item) {
        if (item != null) {
            String newCountryCode = item.getCountryCode();
            if (!newCountryCode.equals(this.countryCode)) {
                this.countryCode = newCountryCode;
                handleCallingCodeChange(item.getCallingCode());
            }
        }
    }

    private void setUpPhoneNumberFields() {
        setUpCountryCode();
        setUpPhoneNumberEditText();
    }

    public void initPhoneNumberInputView(PhoneNumberInputViewDelegate delegate) {
        this.phoneNumberInputViewDelegate = delegate;
        setUpPhoneNumberFields();
        setUpCallingCodeButton();
        setUpCallingCodeListener();
    }

    public void setPhoneNumberEditText(CharSequence phoneNumber2) {
        this.phoneNumberEditText.setText(phoneNumber2);
    }

    public void setPhoneNumberEditText(AirPhone airPhone) {
        if (airPhone != null && !TextUtils.isEmpty(airPhone.phoneInputText())) {
            this.phoneNumberEditText.setText(airPhone.phoneInputText());
        }
    }

    private void setUpCallingCodeListener() {
        CallingCodeDialogFragment fragment = (CallingCodeDialogFragment) this.phoneNumberInputViewDelegate.getFragmentManager().findFragmentByTag(CallingCodeDialogFragment.TAG);
        if (fragment != null) {
            fragment.setListener(this.callingCodeListener);
        }
    }

    private void setUpCountryCode() {
        this.countryCode = this.phoneUtil.getSimCountryCodeUppercase();
        if (TextUtils.isEmpty(this.countryCode)) {
            String possibleCountry = Locale.getDefault().getCountry();
            if (TextUtils.isEmpty(possibleCountry)) {
                possibleCountry = AirbnbConstants.COUNTRY_CODE_CHINA;
            }
            this.countryCode = possibleCountry;
        }
    }

    private void setUpCallingCodeButton() {
        String callingCodeText;
        String callingCode = this.phoneUtil.getCallingCode(this.countryCode);
        if (callingCode == null) {
            callingCodeText = this.phoneUtil.getCallingCode(AirbnbConstants.COUNTRY_CODE_CHINA);
        } else {
            callingCodeText = callingCode;
        }
        this.callingCodeButton.setText(PhoneUtil.addLeadingPlusSignIfNecessary(callingCodeText));
        this.callingCodeButton.setOnClickListener(PhoneNumberInputSheet$$Lambda$4.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$setUpCallingCodeButton$1(PhoneNumberInputSheet phoneNumberInputSheet, View v) {
        SignUpLoginAnalytics.trackLandingAction("calling_code_button_click");
        CallingCodeDialogFragment.newInstance(phoneNumberInputSheet.callingCodeListener).show(phoneNumberInputSheet.phoneNumberInputViewDelegate.getFragmentManager(), CallingCodeDialogFragment.TAG);
    }

    public void setCountryCodeSelectionButtonClickListener(OnClickListener listener) {
        this.callingCodeButton.setOnClickListener(listener);
    }

    private void handleCallingCodeChange(String callingCode) {
        changeEditTextForI18n();
        this.phoneNumber = null;
        this.phoneNumberEditText.setText("");
        this.callingCodeButton.setText(PhoneUtil.addLeadingPlusSignIfNecessary(callingCode));
        KeyboardUtils.showSoftKeyboard(this.phoneNumberEditText);
    }

    private void setUpPhoneNumberEditText() {
        this.phoneNumberEditText.addTextChangedListener(this.phoneNumberWatcher);
        if (this.phoneNumber == null) {
            this.phoneNumberEditText.requestFocus();
        } else {
            this.phoneNumberEditText.setText(formatNumber(this.phoneNumber));
        }
        changeEditTextForI18n();
    }

    @SuppressLint({"NewApi"})
    private void changeEditTextForI18n() {
        if (AndroidVersion.isAtLeastLollipop()) {
            if (this.phoneFormattingTextWatcher != null) {
                this.phoneNumberEditText.removeTextChangedListener(this.phoneFormattingTextWatcher);
            }
            this.phoneFormattingTextWatcher = new PhoneNumberFormattingTextWatcher(this.countryCode);
            this.phoneNumberEditText.addTextChangedListener(this.phoneFormattingTextWatcher);
        }
    }

    /* access modifiers changed from: private */
    public String formatNumber(PhoneNumber phoneNumber2) {
        if (phoneNumber2 == null) {
            return "";
        }
        return this.phoneNumberUtil.format(phoneNumber2, PhoneNumberFormat.NATIONAL);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.phoneNumberEditText != null) {
            this.phoneNumberEditText.removeTextChangedListener(this.phoneFormattingTextWatcher);
            this.phoneNumberEditText.removeTextChangedListener(this.phoneNumberWatcher);
        }
        this.phoneNumberInputViewDelegate = null;
    }

    public void showSoftKeyboard() {
        KeyboardUtils.showSoftKeyboard(getContext(), this.phoneNumberEditText);
    }
}
