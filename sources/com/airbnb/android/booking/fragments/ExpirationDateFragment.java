package com.airbnb.android.booking.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.booking.C0704R;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.utils.SimpleTextWatcher;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.SheetInputText.State;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;
import com.facebook.appevents.AppEventsConstants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ExpirationDateFragment extends CreditCardBaseFragment {
    /* access modifiers changed from: private */
    public final Calendar expirationDate = Calendar.getInstance();
    /* access modifiers changed from: private */
    public final SimpleDateFormat formatter = new SimpleDateFormat("MM / yy", Locale.US);
    private final TextWatcher formattingTextWatcher = new SimpleTextWatcher() {
        private final int MAX_INPUT_LENGTH = (" / ".length() + 4);
        private String previousInput = "";
        private final String separator = " / ";

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            this.previousInput = s.toString();
        }

        public void afterTextChanged(Editable s) {
            boolean didDelete = true;
            ExpirationDateFragment.this.dismissError();
            ExpirationDateFragment.this.sheetInput.setState(State.Normal);
            ExpirationDateFragment.this.nextButton.setEnabled(false);
            String input = s.toString();
            if (this.previousInput.length() <= input.length()) {
                didDelete = false;
            }
            if (input.length() > this.MAX_INPUT_LENGTH) {
                setText(input.substring(0, input.length() - 1));
            }
            try {
                ExpirationDateFragment.this.expirationDate.setTime(ExpirationDateFragment.this.formatter.parse(input));
                ExpirationDateFragment.this.month = ExpirationDateFragment.this.expirationDate.get(2) + 1;
                ExpirationDateFragment.this.year = ExpirationDateFragment.this.expirationDate.get(1);
                if (input.length() == this.MAX_INPUT_LENGTH) {
                    ExpirationDateFragment.this.validateDate();
                }
            } catch (ParseException e) {
                String formattedInput = formattedInput(input, didDelete);
                if (!formattedInput.equals(input)) {
                    setText(formattedInput);
                }
            }
        }

        private String formattedInput(String input, boolean didDelete) {
            String formattedInput = input;
            if (didDelete) {
                if (input.endsWith(" / ")) {
                    return input.split(" / ")[0];
                }
                if (input.endsWith(" /")) {
                    return input.split(" /")[0];
                }
                return formattedInput;
            } else if (input.length() == 1) {
                if (Integer.parseInt(input) > 1) {
                    return AppEventsConstants.EVENT_PARAM_VALUE_NO + input + " / ";
                }
                return formattedInput;
            } else if (input.length() == 2) {
                return input + " / ";
            } else {
                if (input.length() == 3) {
                    return input.substring(0, 2) + " / " + input.substring(input.length() - 1);
                }
                return formattedInput;
            }
        }

        private void setText(String text) {
            ExpirationDateFragment.this.sheetInput.setText(text);
            ExpirationDateFragment.this.sheetInput.setSelection(text.length());
        }
    };
    @BindView
    JellyfishView jellyfishView;
    @icepick.State
    int month;
    @BindView
    AirButton nextButton;
    @BindView
    SheetInputText sheetInput;
    @icepick.State
    int year;

    public static ExpirationDateFragment newInstance() {
        return new ExpirationDateFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0704R.layout.fragment_expiration_date, container, false);
        bindViews(view);
        setUpSheetInput();
        return view;
    }

    private void setUpSheetInput() {
        this.sheetInput.requestFocus();
        this.sheetInput.post(ExpirationDateFragment$$Lambda$1.lambdaFactory$(this));
        this.sheetInput.addTextChangedListener(this.formattingTextWatcher);
    }

    public void onDestroyView() {
        dismissError();
        this.sheetInput.removeTextChangedListener(this.formattingTextWatcher);
        super.onDestroyView();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onClickNextButton() {
        getCreditCardActivity().updateExpirationDate(String.valueOf(this.month), String.valueOf(this.year));
        if (getCreditCardActivity().isQuickPay()) {
            this.quickPayJitneyLogger.paymentCcExpiration();
        }
    }

    /* access modifiers changed from: private */
    public void validateDate() {
        if (AirDate.today().isBefore(AirDate.fromCalendar(this.expirationDate).plusMonths(1))) {
            dismissError();
            this.sheetInput.setState(State.Valid);
            this.nextButton.setEnabled(true);
            return;
        }
        displayError(getString(C0704R.string.p4_error_card_expired));
        this.sheetInput.setState(State.Error);
        this.nextButton.setEnabled(false);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.AddPaymentExpiration;
    }
}
