package com.airbnb.android.lib.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.analytics.PsbAnalytics;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.AirFragment.DoneClickListener;
import com.airbnb.android.core.fragments.datepicker.DatePickerDialog;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.interfaces.GuestIdentity.Type;
import com.airbnb.android.core.models.NameCodeItem;
import com.airbnb.android.core.requests.CreateGuestIdentityInformationRequest;
import com.airbnb.android.core.responses.SaveGuestIdentityInformationResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.presenters.GuestIdentityTypePresenter;
import com.airbnb.android.lib.views.EditableCell;
import com.airbnb.android.lib.views.GroupedCell;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.SimpleTextWatcher;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.utils.ClickableLinkUtils;
import icepick.State;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import p032rx.Observer;

public class CreateGuestIdentityFragment extends AirFragment implements DoneClickListener {
    public static final String EXTRA_GUEST_INFO = "extra_result_guest";
    private static final int REQUEST_CODE_DATE_OF_EXPIRY = 1002;
    private static final int REQUEST_CODE_SELECT_ID_TYPE = 1001;
    private static final int REQUEST_CODE_SELECT_NATIONALITY = 1000;
    private static final String STATE_GIVEN_NAME = "saved_state_given_name";
    private static final String STATE_ID_NUMBER = "saved_state_id_number";
    private static final String STATE_SURNAME = "saved_state_surname";
    private SimpleDateFormat dateFormat;
    @State
    AirDate dateOfExpiry;
    @BindView
    GroupedCell dateOfExpiryCell;
    @BindView
    TextView disclaimerTextView;
    @State
    boolean doneButtonEnabled;
    @BindView
    EditableCell givenNameCell;
    @BindView
    EditableCell idNumberCell;
    @BindView
    GroupedCell idTypeCell;
    @State
    Type identityType = null;
    @BindView
    GroupedCell nationalityCell;
    @State
    String nationalityCountryCode;
    @State
    String nationalityCountryString;
    final RequestListener<SaveGuestIdentityInformationResponse> saveIdentityRequestListener = new C0699RL().onResponse(CreateGuestIdentityFragment$$Lambda$1.lambdaFactory$(this)).onError(CreateGuestIdentityFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    EditableCell surnameCell;
    private final SimpleTextWatcher textWatcher = new SimpleTextWatcher() {
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            CreateGuestIdentityFragment.this.checkForValidInput();
        }
    };

    @SuppressLint({"SimpleDateFormat"})
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_create_guest_identity, container, false);
        bindViews(view);
        this.dateFormat = new SimpleDateFormat(getString(C0880R.string.mdy_short_with_full_year));
        this.surnameCell.setInputType(8192);
        this.givenNameCell.setInputType(8192);
        String privacyLink = getString(C0880R.string.sign_up_agreement_html_link, getString(C0880R.string.privacy_policy));
        ClickableLinkUtils.setupClickableTextView(this.disclaimerTextView, getString(C0880R.string.create_guest_identity_disclaimer, privacyLink), C0880R.color.canonical_press_darken, CreateGuestIdentityFragment$$Lambda$3.lambdaFactory$(this));
        setActionBarTitle(C0880R.string.add_guest_information);
        if (savedInstanceState != null) {
            PsbAnalytics.trackCreateIdentificationAction("impression");
            this.surnameCell.setValue(savedInstanceState.getString(STATE_SURNAME));
            this.givenNameCell.setValue(savedInstanceState.getString(STATE_GIVEN_NAME));
            this.idNumberCell.setValue(savedInstanceState.getString(STATE_ID_NUMBER));
            this.nationalityCell.setContent((CharSequence) this.nationalityCountryString);
            if (this.identityType != null) {
                this.idTypeCell.setContent(GuestIdentityTypePresenter.getStringResId(this.identityType));
            }
            if (this.dateOfExpiry != null) {
                this.dateOfExpiryCell.setContent((CharSequence) this.dateOfExpiry.formatDate((DateFormat) this.dateFormat));
            }
        }
        this.surnameCell.addTextWatcher(this.textWatcher);
        this.givenNameCell.addTextWatcher(this.textWatcher);
        this.idNumberCell.addTextWatcher(this.textWatcher);
        refreshFieldVisibilityAndCheckForValidInput();
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(CreateGuestIdentityFragment createGuestIdentityFragment, int linkIndex) {
        PsbAnalytics.trackP4Action("tos_click");
        WebViewIntentBuilder.startMobileWebActivity(createGuestIdentityFragment.getActivity(), createGuestIdentityFragment.getString(C0880R.string.tos_url_privacy));
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_ID_NUMBER, this.idNumberCell.getText());
        outState.putString(STATE_SURNAME, this.surnameCell.getText());
        outState.putString(STATE_GIVEN_NAME, this.givenNameCell.getText());
    }

    public void onDestroyView() {
        this.surnameCell.removeTextWatcher(this.textWatcher);
        this.givenNameCell.removeTextWatcher(this.textWatcher);
        this.idNumberCell.removeTextWatcher(this.textWatcher);
        KeyboardUtils.dismissSoftKeyboard((View) this.surnameCell);
        super.onDestroyView();
    }

    @OnClick
    public void onEditableCellClick(EditableCell cell) {
        cell.requestFocus();
        KeyboardUtils.showSoftKeyboard(cell);
    }

    @OnClick
    public void onNationalityClick() {
        PsbAnalytics.trackCreateIdentificationAction("click_nationality");
        CountryPickerDialogFragment f = CountryPickerDialogFragment.newInstance(getString(C0880R.string.select_nationality), null);
        f.setTargetFragment(this, 1000);
        f.show(getFragmentManager(), (String) null);
    }

    @OnClick
    public void onIdentityTypeClick() {
        PsbAnalytics.trackCreateIdentificationAction("click_identity");
        IdentityTypeSelectorDialogFragment.newInstance(1001).show(getFragmentManager(), (String) null);
    }

    @OnClick
    public void onDateOfExpiryClick() {
        PsbAnalytics.trackCreateIdentificationAction("click_date_of_expiry");
        DatePickerDialog.newInstance(this.dateOfExpiry, false, this, C0880R.string.select_expiry_date, AirDate.today(), null, 1002).show(getFragmentManager(), (String) null);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1000:
                if (resultCode == -1) {
                    NameCodeItem nameCodeItem = (NameCodeItem) data.getParcelableExtra("country");
                    this.nationalityCountryString = nameCodeItem.getName();
                    this.nationalityCell.setContent((CharSequence) this.nationalityCountryString);
                    this.nationalityCountryCode = nameCodeItem.getCode();
                    if (this.identityType == null && !this.nationalityCountryCode.equals(AirbnbConstants.COUNTRY_CODE_CHINA)) {
                        setIdentityType(Type.Passport);
                        break;
                    }
                }
                break;
            case 1001:
                if (resultCode == -1) {
                    setIdentityType((Type) data.getSerializableExtra(IdentityTypeSelectorDialogFragment.RESULT_SELECTED_IDENTITY_TYPE));
                    break;
                }
                break;
            case 1002:
                if (resultCode == -1) {
                    this.dateOfExpiry = (AirDate) data.getParcelableExtra("date");
                    this.dateOfExpiryCell.setContent((CharSequence) this.dateOfExpiry.formatDate((DateFormat) this.dateFormat));
                    break;
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
        refreshFieldVisibilityAndCheckForValidInput();
    }

    private void setIdentityType(Type type) {
        this.identityType = type;
        this.idTypeCell.setContent((type == null ? null : Integer.valueOf(GuestIdentityTypePresenter.getStringResId(this.identityType))).intValue());
    }

    private void refreshFieldVisibilityAndCheckForValidInput() {
        boolean isChineseNationalIdentityCard;
        boolean noCountryEnteredOrChineseIdentityCard;
        boolean z;
        boolean z2 = true;
        boolean isChineseUser = AirbnbConstants.COUNTRY_CODE_CHINA.equals(this.nationalityCountryCode);
        if (!isChineseUser || Type.ChineseNationalID != this.identityType) {
            isChineseNationalIdentityCard = false;
        } else {
            isChineseNationalIdentityCard = true;
        }
        ViewUtils.setVisibleIf((View) this.idTypeCell, isChineseUser);
        ViewUtils.setGoneIf(this.idNumberCell, TextUtils.isEmpty(this.nationalityCountryCode));
        this.idNumberCell.setTitle(isChineseNationalIdentityCard ? C0880R.string.guest_identification_number : C0880R.string.passport_number);
        if (TextUtils.isEmpty(this.nationalityCountryCode) || isChineseNationalIdentityCard) {
            noCountryEnteredOrChineseIdentityCard = true;
        } else {
            noCountryEnteredOrChineseIdentityCard = false;
        }
        ViewUtils.setGoneIf(this.dateOfExpiryCell, noCountryEnteredOrChineseIdentityCard);
        this.nationalityCell.showRightCaret(TextUtils.isEmpty(this.nationalityCountryCode));
        GroupedCell groupedCell = this.idTypeCell;
        if (this.identityType == null) {
            z = true;
        } else {
            z = false;
        }
        groupedCell.showRightCaret(z);
        GroupedCell groupedCell2 = this.dateOfExpiryCell;
        if (this.dateOfExpiry != null) {
            z2 = false;
        }
        groupedCell2.showRightCaret(z2);
        checkForValidInput();
    }

    /* access modifiers changed from: private */
    public boolean checkForValidInput() {
        boolean valid;
        boolean z = true;
        if (!hasText(this.surnameCell.getText()) || !hasText(this.givenNameCell.getText()) || !hasText(this.nationalityCountryCode) || !hasText(this.idNumberCell.getText())) {
            valid = false;
        } else {
            valid = true;
        }
        if (Type.ChineseNationalID != this.identityType) {
            if (this.dateOfExpiry == null) {
                z = false;
            }
            valid &= z;
        }
        if (valid) {
            enableCustomActionBarUpButton(C0880R.layout.editor_actionbar_layout, C0880R.C0882id.confirm_button, C0880R.string.done, this);
        } else {
            disableCustomActionBarUpButton();
        }
        return valid;
    }

    private boolean hasText(String text) {
        return text != null && !TextUtils.isEmpty(text.trim());
    }

    static /* synthetic */ void lambda$new$1(CreateGuestIdentityFragment createGuestIdentityFragment, SaveGuestIdentityInformationResponse data) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_GUEST_INFO, data.getIdentity());
        createGuestIdentityFragment.getActivity().setResult(-1, intent);
        Toast.makeText(createGuestIdentityFragment.getContext(), C0880R.string.saved, 0).show();
        createGuestIdentityFragment.getActivity().finish();
    }

    static /* synthetic */ void lambda$new$2(CreateGuestIdentityFragment createGuestIdentityFragment, AirRequestNetworkException e) {
        createGuestIdentityFragment.showLoader(false);
        NetworkUtil.toastGenericNetworkError(createGuestIdentityFragment.getActivity());
    }

    public void onDoneClick() {
        if (!checkForValidInput()) {
            Toast.makeText(getActivity(), C0880R.string.guest_identity_invalid_input, 0).show();
            return;
        }
        PsbAnalytics.trackCreateIdentificationAction("save_identity_click");
        showLoader(true);
        new CreateGuestIdentityInformationRequest(this.surnameCell.getText(), this.givenNameCell.getText(), this.nationalityCountryCode, this.identityType, this.idNumberCell.getText(), this.dateOfExpiry).withListener((Observer) this.saveIdentityRequestListener).execute(this.requestManager);
    }
}
