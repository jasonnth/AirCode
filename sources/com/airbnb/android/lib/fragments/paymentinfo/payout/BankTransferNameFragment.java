package com.airbnb.android.lib.fragments.paymentinfo.payout;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.SimpleTextWatcher;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.utils.SnackbarWrapper;

public class BankTransferNameFragment extends BasePaymentInfoFragment {
    private static final String ENGLISH_ONLY_REGEX = "[a-zA-Z ]+";
    @BindView
    SheetInputText nameInputText;
    @BindView
    AirButton nextButton;
    private final SimpleTextWatcher textWatcher = new SimpleTextWatcher() {
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            BankTransferNameFragment.this.nextButton.setEnabled(!TextUtils.isEmpty(BankTransferNameFragment.this.nameInputText.getText().toString()));
        }
    };
    @BindView
    AirToolbar toolbar;

    public static BankTransferNameFragment newInstance() {
        return new BankTransferNameFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_bank_transfer_name, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.nextButton.setEnabled(false);
        this.nameInputText.addTextChangedListener(this.textWatcher);
        return view;
    }

    public void onDestroyView() {
        this.nameInputText.removeTextChangedListener(this.textWatcher);
        super.onDestroyView();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onNextButtonClick() {
        String name = this.nameInputText.getText().toString();
        if (validateName(name)) {
            getPaymentInfoActivity().setName(name);
            getNavigationController().doneWithBankTransferName();
        }
    }

    private boolean validateName(String name) {
        if (name.split(" ").length < 2) {
            new SnackbarWrapper().view(getView()).title(C0880R.string.error, true).body(C0880R.string.payout_bank_transfer_error_name_space).duration(-1).buildAndShow();
            return false;
        } else if (name.matches(ENGLISH_ONLY_REGEX)) {
            return true;
        } else {
            new SnackbarWrapper().view(getView()).title(C0880R.string.error, true).body(C0880R.string.payout_bank_transfer_error_english_letters).duration(-1).buildAndShow();
            return false;
        }
    }
}
