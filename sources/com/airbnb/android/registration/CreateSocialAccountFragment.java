package com.airbnb.android.registration;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.p000v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.ErrorResponse;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.activities.TransparentActionBarActivity;
import com.airbnb.android.core.analytics.RegistrationAnalytics;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.fragments.datepicker.DatePickerDialog;
import com.airbnb.android.core.models.AccountSource;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.android.login.p339ui.LoginActivity;
import com.airbnb.android.registration.models.AccountLoginData;
import com.airbnb.android.registration.models.AccountRegistrationData;
import com.airbnb.android.registration.requests.CreateSocialSignupRequest;
import com.airbnb.android.registration.responses.SocialSignupResponse;
import com.airbnb.android.utils.BundleBuilder;
import com.airbnb.android.utils.SimpleTextWatcher;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.collections.SheetState;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.components.SwitchRow;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import com.airbnb.p027n2.utils.TextUtil;
import icepick.State;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import p032rx.Observer;

public class CreateSocialAccountFragment extends AirFragment {
    private static final String ARG_IS_EMAIL_READONLY = "arg_is_email_read_only";
    private static final String ARG_PREFILLED_DATA = "arg_prefilled_data";
    @BindView
    SheetInputText birthday;
    final RequestListener<SocialSignupResponse> createSocialAccountRequestListener = new C0699RL().onResponse(CreateSocialAccountFragment$$Lambda$1.lambdaFactory$(this)).onError(CreateSocialAccountFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    SheetInputText email;
    @State
    String emailText;
    @BindView
    SheetInputText firstName;
    private final TextWatcher inputWatcher = new SimpleTextWatcher() {
        public void afterTextChanged(Editable s) {
            CreateSocialAccountFragment.this.nextBtn.setEnabled(CreateSocialAccountFragment.this.hasValidInput());
            CreateSocialAccountFragment.this.setSheetState(SheetState.Normal);
            CreateSocialAccountFragment.this.dismissSnackbar();
        }
    };
    @BindView
    SheetInputText lastName;
    @BindView
    LoaderFrame loader;
    @BindView
    AirButton nextBtn;
    private AccountRegistrationData prefilledData;
    @BindView
    SwitchRow promoEmailOptInSwitch;
    @BindView
    FrameLayout rootView;
    @State
    AirDate selectedBirthday;
    @BindView
    SheetMarquee sheetMarquee;
    @State
    SheetState sheetState;
    private Snackbar snackbar;

    public static Intent newIntentWithPrefilledData(Context context, AccountRegistrationData data, boolean isEmailReadOnly) {
        return TransparentActionBarActivity.intentForFragmentWithoutRequiringAccount(context, CreateSocialAccountFragment.class, ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putParcelable(ARG_PREFILLED_DATA, data)).putBoolean(ARG_IS_EMAIL_READONLY, isEmailReadOnly)).toBundle());
    }

    public static Intent newIntentForDebug(Context context) {
        Check.state(BuildHelper.isDevelopmentBuild());
        return TransparentActionBarActivity.intentForFragmentWithoutRequiringAccount(context, CreateSocialAccountFragment.class, ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putParcelable(ARG_PREFILLED_DATA, AccountRegistrationData.builder().accountSource(AccountSource.Facebook).email("test@airbnb.com").build())).putBoolean(ARG_IS_EMAIL_READONLY, false)).toBundle());
    }

    static /* synthetic */ void lambda$new$0(CreateSocialAccountFragment createSocialAccountFragment, SocialSignupResponse data) {
        RegistrationAnalytics.trackRequestResponseSuccess(RegistrationAnalytics.SIGN_UP_RESPONSE, createSocialAccountFragment.prefilledData.getRegistrationServiceForAnalytics(), createSocialAccountFragment.getNavigationTrackingTag(), createSocialAccountFragment.getContext());
        createSocialAccountFragment.loader.finishImmediate();
        createSocialAccountFragment.getActivity().setResult(-1, new Intent());
        createSocialAccountFragment.getActivity().finish();
    }

    static /* synthetic */ void lambda$new$1(CreateSocialAccountFragment createSocialAccountFragment, AirRequestNetworkException e) {
        RegistrationAnalytics.trackRequestResponseFailure(RegistrationAnalytics.SIGN_UP_RESPONSE, createSocialAccountFragment.prefilledData.getRegistrationServiceForAnalytics(), createSocialAccountFragment.getNavigationTrackingTag(), (NetworkException) e);
        createSocialAccountFragment.loader.finishImmediate();
        createSocialAccountFragment.handleNetworkError(e);
    }

    private void handleNetworkError(NetworkException e) {
        ErrorResponse errorResponse = (ErrorResponse) e.errorResponse();
        if (errorResponse == null || !TextUtils.equals(LoginActivity.EMAIL_ALREADY_IN_USE_ERROR, errorResponse.errorType)) {
            NetworkUtil.tryShowErrorWithSnackbar(getView(), e, C1562R.string.sign_up_error, C1562R.string.sign_up_error_message);
            return;
        }
        setSheetState(SheetState.Error);
        this.snackbar = new SnackbarWrapper().view(getView()).body(getString(C1562R.string.registration_email_in_use_desc)).action(getString(C1562R.string.sign_in), CreateSocialAccountFragment$$Lambda$3.lambdaFactory$(this, AccountLoginData.builder(AccountSource.Email).email(this.emailText).build())).buildAndShow();
    }

    static /* synthetic */ void lambda$handleNetworkError$2(CreateSocialAccountFragment createSocialAccountFragment, AccountLoginData data, View v) {
        createSocialAccountFragment.getActivity().setResult(-1, new Intent().putExtra(AccountRegistrationActivity.EXTRA_RESULT_ACCOUNT_SIGN_IN_DATA, data));
        createSocialAccountFragment.getActivity().finish();
    }

    /* access modifiers changed from: private */
    public void setSheetState(SheetState sheetState2) {
        if (sheetState2 != this.sheetState) {
            this.sheetState = sheetState2;
            this.rootView.setBackgroundColor(ContextCompat.getColor(getContext(), sheetState2.backgroundColor));
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.prefilledData = (AccountRegistrationData) getArguments().getParcelable(ARG_PREFILLED_DATA);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C1562R.layout.fragment_create_social_account, container, false);
        bindViews(view);
        boolean isEmailReadOnly = getArguments().getBoolean(ARG_IS_EMAIL_READONLY);
        if (this.prefilledData.isSocialSignupDataEmpty()) {
            this.sheetMarquee.setTitle(C1562R.string.registration_create_social_account_title_empty_data);
            this.sheetMarquee.setSubtitle(C1562R.string.registration_create_social_account_desc_empty_data_);
        }
        if (isEmailReadOnly) {
            this.email.setEnabled(false);
        }
        if (savedInstanceState == null) {
            this.promoEmailOptInSwitch.setChecked(true);
            this.email.setText(this.prefilledData.email());
            this.firstName.setText(this.prefilledData.firstName());
            this.lastName.setText(this.prefilledData.lastName());
        }
        if (this.selectedBirthday == null && !TextUtils.isEmpty(this.prefilledData.birthDateString())) {
            this.selectedBirthday = new AirDate(this.prefilledData.birthDateString());
        }
        updateBirthdayTextView();
        this.email.addTextChangedListener(this.inputWatcher);
        this.firstName.addTextChangedListener(this.inputWatcher);
        this.lastName.addTextChangedListener(this.inputWatcher);
        this.birthday.addTextChangedListener(this.inputWatcher);
        this.nextBtn.setEnabled(hasValidInput());
        this.promoEmailOptInSwitch.setOnCheckedChangeListener(CreateSocialAccountFragment$$Lambda$4.lambdaFactory$(this));
        return view;
    }

    public void onDestroyView() {
        this.email.removeTextChangedListener(this.inputWatcher);
        this.firstName.removeTextChangedListener(this.inputWatcher);
        this.lastName.removeTextChangedListener(this.inputWatcher);
        this.birthday.removeTextChangedListener(this.inputWatcher);
        super.onDestroyView();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1 && requestCode == 2002) {
            this.selectedBirthday = (AirDate) data.getParcelableExtra("date");
            updateBirthdayTextView();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void launchBirthdayPicker() {
        DatePickerDialog.newInstance(this.selectedBirthday == null ? DatePickerDialog.getDefaultBirthdate() : this.selectedBirthday, true, this, C1562R.string.select_birth_date).show(getFragmentManager(), (String) null);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void submit() {
        RegistrationAnalytics.trackClickEvent(RegistrationAnalytics.CONFIRM_DETAILS_BUTTON, this.prefilledData.getRegistrationServiceForAnalytics(), getNavigationTrackingTag());
        this.loader.startAnimation();
        this.emailText = this.email.getText().toString();
        CreateSocialSignupRequest.forCreatingSocialAccount(AccountRegistrationData.builder().accountSource(this.prefilledData.accountSource()).authToken(this.prefilledData.authToken()).email(this.emailText).promoOptIn(this.promoEmailOptInSwitch.isChecked()).firstName(this.firstName.getText().toString()).lastName(this.lastName.getText().toString()).birthDateString(this.selectedBirthday.getIsoDateString()).build()).withListener((Observer) this.createSocialAccountRequestListener).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public boolean hasValidInput() {
        return TextUtil.isValidEmail(this.email.getText()) && !TextUtils.isEmpty(this.firstName.getText()) && !TextUtils.isEmpty(this.lastName.getText()) && this.selectedBirthday != null;
    }

    private void updateBirthdayTextView() {
        if (this.selectedBirthday != null) {
            this.birthday.setText(this.selectedBirthday.formatDate((DateFormat) new SimpleDateFormat(getString(C1562R.string.mdy_format_full))));
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.RegistrationConfirmDetails;
    }

    public Strap getNavigationTrackingParams() {
        return super.getNavigationTrackingParams().mo11639kv("service", this.prefilledData.getRegistrationServiceForAnalytics());
    }

    /* access modifiers changed from: private */
    public void dismissSnackbar() {
        if (this.snackbar != null) {
            this.snackbar.dismiss();
            this.snackbar = null;
        }
    }
}
