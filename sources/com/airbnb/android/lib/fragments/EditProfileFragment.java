package com.airbnb.android.lib.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.content.ContextCompat;
import android.support.p002v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequest;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.ErrorResponse;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.businesstravel.BusinessTravelEmployeeFetchedEvent;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.analytics.AccountVerificationAnalytics;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.analytics.IdentityJitneyLogger;
import com.airbnb.android.core.businesstravel.BusinessTravelAccountManager;
import com.airbnb.android.core.businesstravel.BusinessTravelAnalytics;
import com.airbnb.android.core.businesstravel.WorkEmailLaunchSource;
import com.airbnb.android.core.businesstravel.models.BusinessTravelEmployee;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.enums.WorkEmailStatus;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.events.ProfileUpdatedEvent;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.ProgressDialogFragment;
import com.airbnb.android.core.fragments.ProgressDialogFragment.ProgressDialogListener;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.fragments.datepicker.DatePickerDialog;
import com.airbnb.android.core.identity.IdentityVerificationUtil;
import com.airbnb.android.core.intents.BusinessTravelIntents;
import com.airbnb.android.core.interfaces.EditProfileInterface;
import com.airbnb.android.core.interfaces.EditProfileInterface.Gender;
import com.airbnb.android.core.interfaces.EditProfileInterface.ProfileSection;
import com.airbnb.android.core.models.SpokenLanguage;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.requests.DeleteManualVerificationRequest;
import com.airbnb.android.core.requests.EditProfileRequest;
import com.airbnb.android.core.requests.SetProfilePhotoRequest;
import com.airbnb.android.core.requests.UserRequest;
import com.airbnb.android.core.responses.DeleteManualVerificationResponse;
import com.airbnb.android.core.responses.UserResponse;
import com.airbnb.android.core.responses.UserWrapperResponse;
import com.airbnb.android.core.utils.AirPhotoPicker;
import com.airbnb.android.core.utils.ImageUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.PhotoCompressor;
import com.airbnb.android.core.utils.PhotoCompressor.SimpleCompressionCallback;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.EditProfileActivity;
import com.airbnb.android.lib.adapters.EditProfileDetailsAdapter;
import com.airbnb.android.lib.analytics.EditProfileAnalytics;
import com.airbnb.android.lib.businesstravel.IntentPredictionConstants;
import com.airbnb.android.lib.utils.DialogUtils;
import com.airbnb.android.lib.utils.UserProfileUtils;
import com.airbnb.android.lib.views.LinearListView;
import com.airbnb.android.photopicker.PhotoPicker;
import com.airbnb.android.utils.CropUtil;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.facebook.internal.AnalyticsEvents;
import com.squareup.otto.Subscribe;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImage.ActivityResult;
import icepick.State;
import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import p032rx.Observer;

public class EditProfileFragment extends AirFragment {
    private static final String ARG_PROFILE_USER = "profile_user";
    public static final int EXECUTING_UPDATE_PHOTO = 1;
    public static final int EXECUTING_UPDATE_PROFILE = 2;
    public static final int IDLE = 0;
    private static final int REQUEST_CODE_SELECT_PICTURE = 703;
    private static final int REQUEST_CODE_WORK_EMAIL = 500;
    private static final int REQUEST_PROCEED_UPDATE_EMAIL = 489;
    private static final int REQUEST_REMOVE_MANUAL_VERIFICATION = 123;
    private static final int REQUEST_SPOKEN_LANGUAGES_APPLY = 502;
    private static final int REQUEST_SPOKEN_LANGUAGES_CANCEL = 501;
    private static final String TAG_SPOKEN_LANGUAGES = "spoken_languages";
    BusinessTravelAccountManager businessTravelAccountManager;
    @BindView
    View businessTravelContainer;
    @BindView
    View businessTravelSection;
    @State
    Uri croppedPhotoUri;
    @State
    int currentRequestState;
    @State
    ProfileSection currentSection;
    final RequestListener<DeleteManualVerificationResponse> deleteManualVerificationRequestListener = new C0699RL().onResponse(EditProfileFragment$$Lambda$3.lambdaFactory$(this)).onError(EditProfileFragment$$Lambda$4.lambdaFactory$(this)).build();
    IdentityJitneyLogger identityJitneyLogger;
    ImageUtils imageUtils;
    @BindView
    View mBtnEditAboutMe;
    @BindView
    View mBtnEditName;
    private EditProfileInterface mCallback;
    @BindView
    LinearListView mOptionalSections;
    /* access modifiers changed from: private */
    public EditProfileDetailsAdapter mOptionalSectionsAdapter;
    @BindView
    LinearListView mPrivateSections;
    /* access modifiers changed from: private */
    public EditProfileDetailsAdapter mPrivateSectionsAdapter;
    @BindView
    AirImageView mProfileImage;
    @BindView
    ScrollView mScrollView;
    @BindView
    TextView mTxtAboutMe;
    @BindView
    AirTextView mUserNameTextView;
    PhotoCompressor photoCompressor;
    ProgressDialogFragment progressDialog;
    @State
    int scrollValue;
    final RequestListener<UserResponse> updateVerificationsListener = new C0699RL().onResponse(EditProfileFragment$$Lambda$1.lambdaFactory$(this)).onError(EditProfileFragment$$Lambda$2.lambdaFactory$(this)).build();
    @State
    String updatedValue;
    final RequestListener<UserResponse> userRequestListener = new C0699RL().onResponse(EditProfileFragment$$Lambda$5.lambdaFactory$(this)).onError(EditProfileFragment$$Lambda$6.lambdaFactory$(this)).build();
    @BindView
    TextView workEmail;
    @BindView
    TextView workEmailStatus;
    @BindView
    TextView workEmailStatusDetails;

    @Retention(RetentionPolicy.SOURCE)
    public @interface RequestState {
    }

    static /* synthetic */ void lambda$new$0(EditProfileFragment editProfileFragment, UserResponse response) {
        editProfileFragment.showLoader(false);
        User currentUser = editProfileFragment.mAccountManager.getCurrentUser();
        User updatedUser = response.user;
        currentUser.setVerifications(updatedUser.getVerifications());
        currentUser.setVerificationLabels(updatedUser.getVerificationLabels());
        editProfileFragment.mPrivateSectionsAdapter.notifyDataSetChanged();
    }

    static /* synthetic */ void lambda$new$1(EditProfileFragment editProfileFragment, AirRequestNetworkException e) {
        editProfileFragment.showLoader(false);
        NetworkUtil.toastGenericNetworkError(editProfileFragment.getActivity());
    }

    static /* synthetic */ void lambda$new$2(EditProfileFragment editProfileFragment, DeleteManualVerificationResponse data) {
        editProfileFragment.progressDialog.onProgressComplete(C0880R.string.edit_profile_remove_manual_verification_success_header, 0, C0880R.C0881drawable.icon_complete, 0);
        ZenDialog.createNoButtonDialog(C0880R.string.edit_profile_remove_manual_verification_success_header, data.message).show(editProfileFragment.getFragmentManager(), (String) null);
        editProfileFragment.showLoader(true);
        new UserRequest(editProfileFragment.mAccountManager.getCurrentUser().getId(), (BaseRequestListener<UserResponse>) editProfileFragment.updateVerificationsListener).execute(editProfileFragment.requestManager);
    }

    static /* synthetic */ void lambda$new$3(EditProfileFragment editProfileFragment, AirRequestNetworkException e) {
        editProfileFragment.progressDialog.dismissAllowingStateLoss();
        ZenDialog.createNoButtonDialog(C0880R.string.edit_profile_remove_manual_verification_error_header, ((ErrorResponse) e.errorResponse()).errorMessage).show(editProfileFragment.getFragmentManager(), (String) null);
    }

    static /* synthetic */ void lambda$new$4(EditProfileFragment editProfileFragment, UserResponse response) {
        editProfileFragment.showLoader(false);
        editProfileFragment.startActivity(IdentityVerificationUtil.getIntentForVerifiedIdOrIdentity(editProfileFragment.identityJitneyLogger, editProfileFragment.getActivity(), response.user, VerificationFlow.UserProfile));
        AccountVerificationAnalytics.trackProfileEdit(FeatureToggles.replaceVerifiedIdWithIdentity(response.user));
    }

    static /* synthetic */ void lambda$new$5(EditProfileFragment editProfileFragment, AirRequestNetworkException e) {
        editProfileFragment.showLoader(false);
        NetworkUtil.toastGenericNetworkError(editProfileFragment.getActivity());
    }

    public static Fragment newInstance(User profileUser) {
        return ((FragmentBundleBuilder) FragmentBundler.make(new EditProfileFragment()).putParcelable(ARG_PROFILE_USER, profileUser)).build();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof EditProfileActivity)) {
            throw new IllegalArgumentException(context.toString() + " must be " + EditProfileActivity.class.getSimpleName());
        }
        this.mCallback = (EditProfileInterface) context;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        setHasOptionsMenu(true);
        this.progressDialog = ProgressDialogFragment.newInstance(getContext(), C0880R.string.removing, 0);
        this.mBus.register(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        EditProfileDetailsAdapter editProfileDetailsAdapter;
        View view = inflater.inflate(C0880R.layout.fragment_edit_profile, container, false);
        setUpToolbar();
        bindViews(view);
        this.mScrollView.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
            public boolean onPreDraw() {
                if (EditProfileFragment.this.mScrollView != null) {
                    EditProfileFragment.this.mScrollView.scrollTo(0, EditProfileFragment.this.scrollValue);
                    EditProfileFragment.this.mScrollView.getViewTreeObserver().removeOnPreDrawListener(this);
                }
                EditProfileFragment.this.mProfileImage.getLayoutParams().height = (int) (0.6666667f * ((float) EditProfileFragment.this.mProfileImage.getWidth()));
                EditProfileFragment.this.mProfileImage.requestLayout();
                return true;
            }
        });
        setUserProfileImage();
        User user = (User) getArguments().getParcelable(ARG_PROFILE_USER);
        this.mUserNameTextView.setText(user.getName());
        this.mTxtAboutMe.setText(user.getAbout());
        this.mBtnEditAboutMe.setOnClickListener(EditProfileFragment$$Lambda$7.lambdaFactory$(this));
        this.mBtnEditName.setOnClickListener(EditProfileFragment$$Lambda$8.lambdaFactory$(this));
        if (user.getGovernmentIdDob() == null) {
            editProfileDetailsAdapter = new EditProfileDetailsAdapter(getActivity(), true, new ProfileSection[0]);
        } else {
            editProfileDetailsAdapter = new EditProfileDetailsAdapter(getActivity(), true, ProfileSection.BirthDate);
        }
        this.mPrivateSectionsAdapter = editProfileDetailsAdapter;
        this.mPrivateSections.setAdapter(this.mPrivateSectionsAdapter);
        this.mPrivateSections.setOnItemClickListener(EditProfileFragment$$Lambda$9.lambdaFactory$(this));
        this.mOptionalSectionsAdapter = new EditProfileDetailsAdapter(getActivity(), false, new ProfileSection[0]);
        this.mOptionalSections.setAdapter(this.mOptionalSectionsAdapter);
        this.mOptionalSections.setOnItemClickListener(EditProfileFragment$$Lambda$10.lambdaFactory$(this));
        setUpBusinessTravel();
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$6(EditProfileFragment editProfileFragment, View v) {
        EditProfileAnalytics.trackAction("click", "about_me", null);
        editProfileFragment.mCallback.onProfileSectionSelected(ProfileSection.About);
    }

    static /* synthetic */ void lambda$onCreateView$7(EditProfileFragment editProfileFragment, View v) {
        EditProfileAnalytics.trackAction("click", "name", null);
        editProfileFragment.mCallback.onNameSectionSelected();
    }

    static /* synthetic */ void lambda$onCreateView$8(EditProfileFragment editProfileFragment, LinearListView parent, View view1, int position, long id) {
        ProfileSection section = (ProfileSection) editProfileFragment.mPrivateSectionsAdapter.getItem(position);
        switch (section) {
            case Gender:
                editProfileFragment.handleGender();
                return;
            case BirthDate:
                editProfileFragment.handleBirthDate();
                return;
            case Email:
                editProfileFragment.handleEmail();
                return;
            case Phone:
                EditProfileAnalytics.trackAction("click", "phone_number", null);
                editProfileFragment.mCallback.onPhoneNumbersSelected();
                return;
            case GovernmentID:
                editProfileFragment.handleVerifications();
                return;
            default:
                editProfileFragment.mCallback.onProfileSectionSelected(section);
                return;
        }
    }

    static /* synthetic */ void lambda$onCreateView$9(EditProfileFragment editProfileFragment, LinearListView parent, View view1, int position, long id) {
        ProfileSection section = (ProfileSection) editProfileFragment.mOptionalSectionsAdapter.getItem(position);
        if (section == ProfileSection.Languages) {
            editProfileFragment.handleLanguages();
        } else {
            editProfileFragment.mCallback.onProfileSectionSelected(section);
        }
    }

    private void setUpToolbar() {
        Toolbar toolbar = ((EditProfileActivity) getActivity()).getActivityToolbar();
        toolbar.setTitle(C0880R.string.title_edit_profile);
        toolbar.setVisibility(0);
        ((AirActivity) getActivity()).setSupportActionBar(toolbar);
    }

    private void setUpBusinessTravel() {
        if (Trebuchet.launch(TrebuchetKeys.WORK_EMAIL)) {
            this.businessTravelAccountManager.forceFetchBusinessTravelEmployeeInfo();
        } else {
            this.businessTravelSection.setVisibility(8);
        }
    }

    @Subscribe
    public void onFetchedBusinessTravelEmployee(BusinessTravelEmployeeFetchedEvent event) {
        if (isVisible()) {
            setUpWorkEmailSection();
        }
    }

    private void setUpWorkEmailSection() {
        BusinessTravelEmployee employee = this.businessTravelAccountManager.getBusinessTravelEmployee();
        WorkEmailStatus emailStatus = this.businessTravelAccountManager.getWorkEmailStatus();
        this.workEmail.setText((employee == null || TextUtils.isEmpty(employee.getEmail())) ? getString(C0880R.string.bt_profile_work_email_add_hint) : employee.getEmail());
        this.workEmail.setTextColor(ContextCompat.getColor(getActivity(), emailStatus.inputTextColor));
        this.workEmailStatus.setVisibility(emailStatus.statusVisibility);
        this.workEmailStatusDetails.setText(emailStatus.statusDetails);
        if (emailStatus != WorkEmailStatus.None) {
            this.workEmailStatus.setText(emailStatus.statusTitle);
            this.workEmailStatus.setTextColor(ContextCompat.getColor(getActivity(), emailStatus.statusColor));
        }
        this.businessTravelContainer.setOnClickListener(EditProfileFragment$$Lambda$11.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$setUpWorkEmailSection$10(EditProfileFragment editProfileFragment, View v) {
        BusinessTravelAnalytics.trackEvent("user_profile", IntentPredictionConstants.BusinessTravelServerKey, "click", "add_work_email_button", BusinessTravelAnalytics.additionalParams().user(editProfileFragment.mAccountManager).create());
        editProfileFragment.goToAddWorkEmail();
    }

    private void goToAddWorkEmail() {
        getActivity().startActivityForResult(BusinessTravelIntents.intentForWorkEmail(getActivity(), WorkEmailLaunchSource.EditProfile), 500);
    }

    /* access modifiers changed from: private */
    public void setUserProfileImage() {
        User user = this.mAccountManager.getCurrentUser();
        this.mProfileImage.setImageUrl(!TextUtils.isEmpty(user.getPictureUrlLarge()) ? user.getPictureUrlLarge() : user.getPictureUrl());
        this.mProfileImage.setOnClickListener(EditProfileFragment$$Lambda$12.lambdaFactory$(this));
    }

    public void onResume() {
        super.onResume();
        if (this.currentRequestState == 1) {
            updateProfilePhoto(this.croppedPhotoUri);
        } else if (this.currentRequestState == 2) {
            updateProfileField(this.currentSection, this.updatedValue);
        }
    }

    public void onPause() {
        super.onPause();
        DialogUtils.hideProgressDialog(getFragmentManager());
        this.scrollValue = this.mScrollView.getScrollY();
    }

    public void onDestroyView() {
        this.photoCompressor.cancelCompressionJobs();
        super.onDestroyView();
    }

    public void onDestroy() {
        super.onDestroy();
        this.mBus.unregister(this);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
            switch (requestCode) {
                case 123:
                    handleRemoveManualVerification(resultCode);
                    break;
                case 203:
                    handleCrop(resultCode, data);
                    break;
                case REQUEST_PROCEED_UPDATE_EMAIL /*489*/:
                    this.mCallback.onProfileSectionSelected(ProfileSection.Email);
                    break;
                case 500:
                    if (data != null && data.hasExtra(BusinessTravelIntents.RESULT_UPDATE_WORK_EMAIL)) {
                        setUpBusinessTravel();
                        break;
                    }
                case 502:
                    updateSpokenLanguages(data);
                    break;
                case GenderSelectionFragment.REQUEST_CODE_GENDER /*701*/:
                    executeOrReattachRequest(2, getUpdateProfileRequest(ProfileSection.Gender, ((Gender) data.getParcelableExtra(GenderSelectionFragment.EXTRA_NEW_GENDER)).getJsonValue()));
                    EditProfileAnalytics.trackAction(BaseAnalytics.UPDATE, "gender", null);
                    break;
                case REQUEST_CODE_SELECT_PICTURE /*703*/:
                    beginCrop(Uri.fromFile(new File(data.getStringExtra(PhotoPicker.EXTRA_PHOTO_PATH))));
                    break;
                case DatePickerDialog.DATE_PICKER_OK /*2002*/:
                    executeOrReattachRequest(2, getUpdateProfileRequest(ProfileSection.BirthDate, EditProfileRequest.formatBirthdate((AirDate) data.getParcelableExtra("date"))));
                    EditProfileAnalytics.trackAction(BaseAnalytics.UPDATE, "birthdate", null);
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(C0880R.C0883menu.edit_profile, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C0880R.C0882id.menu_camera) {
            return super.onOptionsItemSelected(item);
        }
        startUpdateProfilePicture();
        return true;
    }

    private void handleGender() {
        EditProfileAnalytics.trackAction("click", "gender", null);
        GenderSelectionFragment dialogFragment = GenderSelectionFragment.newInstance(Gender.findGender(this.mAccountManager.getCurrentUser().getGender()));
        dialogFragment.setTargetFragment(this, GenderSelectionFragment.REQUEST_CODE_GENDER);
        dialogFragment.show(getFragmentManager(), (String) null);
    }

    private void handleBirthDate() {
        EditProfileAnalytics.trackAction("click", "birthdate", null);
        User currentUser = this.mAccountManager.getCurrentUser();
        AirDate birthday = AirDate.today();
        if (!(currentUser == null || currentUser.getBirthdate() == null)) {
            birthday = currentUser.getBirthdate();
        }
        DatePickerDialog.newInstance(birthday, true, this, 0).show(getFragmentManager(), (String) null);
    }

    private void handleEmail() {
        ZenDialog.builder().withBodyText(C0880R.string.edit_profile_email_confirm_prompt).withDualButton(C0880R.string.cancel, 0, C0880R.string.continue_text, (int) REQUEST_PROCEED_UPDATE_EMAIL, (Fragment) this).create().show(getFragmentManager(), (String) null);
    }

    private void handleVerifications() {
        User currentUser = this.mAccountManager.getCurrentUser();
        if (currentUser.hasProvidedGovernmentID()) {
            handleRemoveVerification();
            return;
        }
        showLoader(true);
        UserRequest.newRequestForVerifications(currentUser.getId()).withListener((Observer) this.userRequestListener).execute(this.requestManager);
    }

    private void handleRemoveVerification() {
        ZenDialog.builder().withBodyText(C0880R.string.edit_profile_remove_manual_verification_body_text).withDualButton(C0880R.string.cancel, 0, C0880R.string.edit_profile_remove_manual_verification_button, 123, (Fragment) this).create().show(getFragmentManager(), (String) null);
    }

    private void handleLanguages() {
        EditProfileAnalytics.trackAction("click", "languages", null);
        SpokenLanguagesDialogFragment.newInstance(REQUEST_SPOKEN_LANGUAGES_CANCEL, 502, this).show(getFragmentManager(), "spoken_languages");
    }

    private void updateSpokenLanguages(Intent data) {
        List<SpokenLanguage> updatedLanguages = data.getParcelableArrayListExtra("spoken_languages");
        List<Integer> languageIds = new ArrayList<>(updatedLanguages.size());
        for (SpokenLanguage language : updatedLanguages) {
            if (language.isSpoken()) {
                languageIds.add(Integer.valueOf(language.getId()));
            }
        }
        executeOrReattachRequest(2, getUpdateProfileRequest(ProfileSection.Languages, TextUtils.join(",", languageIds)));
        EditProfileAnalytics.trackAction(BaseAnalytics.UPDATE, "languages", Strap.make().mo11637kv("num_languages", languageIds.size()));
    }

    public void onProfileUpdated(ProfileSection section) {
        User currentUser = this.mAccountManager.getCurrentUser();
        if (ProfileSection.PRIVATE_DETAILS.contains(section)) {
            this.mPrivateSectionsAdapter.notifyDataSetChanged();
        } else if (ProfileSection.OPTIONAL_DETAILS.contains(section)) {
            this.mOptionalSectionsAdapter.notifyDataSetChanged();
        } else if (section == ProfileSection.About) {
            this.mTxtAboutMe.setText(currentUser.getAbout());
        } else if (section == ProfileSection.Name) {
            this.mUserNameTextView.setText(currentUser.getName());
        }
    }

    private void updateProfilePhoto(Uri croppedPhotoUri2) {
        this.photoCompressor.compressPhoto(croppedPhotoUri2, new SimpleCompressionCallback(getActivity()) {
            public void onPhotoCompressed(String filePath) {
                EditProfileFragment.this.croppedPhotoUri = null;
                EditProfileFragment.this.executeOrReattachRequest(EditProfileFragment.this.currentRequestState, EditProfileFragment.this.getSetProfilePhotoRequest(filePath));
            }
        });
    }

    private void updateProfileField(ProfileSection currentSection2, String updatedValue2) {
        executeOrReattachRequest(this.currentRequestState, getUpdateProfileRequest(currentSection2, updatedValue2));
    }

    /* access modifiers changed from: private */
    public void startUpdateProfilePicture() {
        EditProfileAnalytics.trackAction("click", AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_PHOTO, null);
        startActivityForResult(AirPhotoPicker.builder().targetOutputDimensions(2048, 2048).create(getActivity()), REQUEST_CODE_SELECT_PICTURE);
    }

    private void beginCrop(Uri source) {
        CropUtil.square(source).start((Context) getActivity(), (Fragment) this);
    }

    private void handleCrop(int resultCode, Intent data) {
        ActivityResult result = CropImage.getActivityResult(data);
        if (resultCode == -1) {
            this.currentRequestState = 1;
            this.croppedPhotoUri = result.getUri();
        } else if (resultCode == 204) {
            Toast.makeText(getActivity(), result.getError().getMessage(), 0).show();
        }
    }

    private void handleRemoveManualVerification(int resultCode) {
        if (resultCode == -1) {
            this.progressDialog.setProgressDialogListener(new ProgressDialogListener() {
                public void onProgressCompleted() {
                    EditProfileFragment.this.getActivity().setResult(-1);
                }

                public void onProgressCancelled() {
                }
            });
            this.progressDialog.show(getFragmentManager(), (String) null);
            new DeleteManualVerificationRequest(this.mAccountManager).withListener((Observer) this.deleteManualVerificationRequestListener).execute(this.requestManager);
        }
    }

    /* access modifiers changed from: private */
    public SetProfilePhotoRequest getSetProfilePhotoRequest(String filePath) {
        return (SetProfilePhotoRequest) new SetProfilePhotoRequest(getContext(), new File(filePath)).withListener(new NonResubscribableRequestListener<UserWrapperResponse>() {
            public void onResponse(UserWrapperResponse response) {
                EditProfileFragment.this.currentRequestState = 0;
                DialogUtils.hideProgressDialog(EditProfileFragment.this.getFragmentManager());
                EditProfileFragment.this.setUserProfileImage();
            }

            public void onErrorResponse(AirRequestNetworkException error) {
                EditProfileFragment.this.onErrorReceived(EditProfileFragment.this.getString(C0880R.string.upload_profile_photo_error));
            }
        });
    }

    private EditProfileRequest getUpdateProfileRequest(final ProfileSection section, String newValue) {
        this.currentSection = section;
        this.updatedValue = newValue;
        return new EditProfileRequest(section, newValue, (BaseRequestListener<UserResponse>) new NonResubscribableRequestListener<UserResponse>() {
            public void onResponse(UserResponse response) {
                EditProfileFragment.this.currentRequestState = 0;
                DialogUtils.hideProgressDialog(EditProfileFragment.this.getFragmentManager());
                if (UserProfileUtils.updateCurrentUser(EditProfileFragment.this.mAccountManager.getCurrentUser(), response)) {
                    if (ProfileSection.PRIVATE_DETAILS.contains(section)) {
                        EditProfileFragment.this.mPrivateSectionsAdapter.notifyDataSetChanged();
                    } else if (ProfileSection.OPTIONAL_DETAILS.contains(section)) {
                        EditProfileFragment.this.mOptionalSectionsAdapter.notifyDataSetChanged();
                    }
                    EditProfileFragment.this.mBus.post(new ProfileUpdatedEvent(section));
                }
            }

            public void onErrorResponse(AirRequestNetworkException error) {
                EditProfileFragment.this.onErrorReceived(EditProfileFragment.this.getString(C0880R.string.edit_profile_failed, EditProfileFragment.this.getString(section.getTitleId())));
            }
        });
    }

    /* access modifiers changed from: private */
    public void onErrorReceived(String errorMessage) {
        this.currentRequestState = 0;
        DialogUtils.hideProgressDialog(getFragmentManager());
        Toast.makeText(getActivity(), errorMessage, 0).show();
    }

    /* access modifiers changed from: private */
    public void executeOrReattachRequest(int state, BaseRequest<?> request) {
        if (this.currentRequestState == 0 || state == this.currentRequestState) {
            this.currentRequestState = state;
            if (request != null) {
                DialogUtils.showProgressDialog(getContext(), getFragmentManager(), C0880R.string.loading, C0880R.string.edit_profile_updating);
                this.requestManager.execute(request);
                return;
            }
            return;
        }
        throw new IllegalStateException("Trying to start a new request state while another is in progress. Current: " + this.currentRequestState + " New: " + state);
    }
}
