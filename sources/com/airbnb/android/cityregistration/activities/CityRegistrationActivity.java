package com.airbnb.android.cityregistration.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.cityregistration.C5630R;
import com.airbnb.android.cityregistration.controller.CityRegistrationController;
import com.airbnb.android.cityregistration.executor.CityRegistrationActionExecutor;
import com.airbnb.android.cityregistration.fragments.CityRegistrationApplicationFragment;
import com.airbnb.android.cityregistration.fragments.CityRegistrationDocReviewFragment;
import com.airbnb.android.cityregistration.fragments.CityRegistrationDocTypeSelectionFragment;
import com.airbnb.android.cityregistration.fragments.CityRegistrationExemptionFragment;
import com.airbnb.android.cityregistration.fragments.CityRegistrationInputGroupFragment;
import com.airbnb.android.cityregistration.fragments.CityRegistrationNextStepsFragment;
import com.airbnb.android.cityregistration.fragments.CityRegistrationOverviewFragment;
import com.airbnb.android.cityregistration.fragments.CityRegistrationReviewFragment;
import com.airbnb.android.cityregistration.fragments.CityRegistrationTermsAndConditionsFragment;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.enums.ListingRegistrationInputType;
import com.airbnb.android.core.enums.ListingRegistrationStatus;
import com.airbnb.android.core.intents.CityRegistrationIntents;
import com.airbnb.android.core.models.AirAddress;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingRegistration;
import com.airbnb.android.core.models.ListingRegistrationFileAnswer;
import com.airbnb.android.core.models.ListingRegistrationProcess;
import com.airbnb.android.core.models.ListingRegistrationProcessInputGroup;
import com.airbnb.android.core.models.ListingRegistrationQuestion;
import com.airbnb.android.core.models.ListingRegistrationSubmission;
import com.airbnb.android.core.requests.cityregistration.UpdateListingRegistrationOpenSubmissionRequest;
import com.airbnb.android.core.responses.ListingRegistrationOpenSubmissionResponse;
import com.airbnb.android.core.utils.AirAddressUtil;
import com.airbnb.android.core.utils.AirPhotoPicker;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.PhotoCompressor;
import com.airbnb.android.core.utils.PhotoCompressor.PhotoCompressionCallback;
import com.airbnb.android.utils.IOUtils;
import com.airbnb.p027n2.components.RefreshLoader;
import com.airbnb.p027n2.components.SheetProgressBar;
import icepick.State;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.net.util.Base64;
import p032rx.Observer;

public class CityRegistrationActivity extends AirActivity implements CityRegistrationController, CityRegistrationActionExecutor {
    @State
    HashMap answers;
    @State
    int currentInputGroupIndex;
    @State
    boolean isLYS;
    @State
    Listing listing;
    @State
    ListingRegistrationProcess listingRegistrationProcess;
    @State
    int numModalsLaunched = 0;
    private Map<String, Boolean> pagesEditedInReview;
    private PhotoCompressor photoCompressor;
    @State
    Float progress;
    @BindView
    SheetProgressBar progressBar;
    @BindView
    RefreshLoader refreshLoader;
    @State
    boolean standalonePage = false;
    final RequestListener<ListingRegistrationOpenSubmissionResponse> updateListingRegistrationOpenSubmissionListener = new C0699RL().onResponse(CityRegistrationActivity$$Lambda$1.lambdaFactory$(this)).onError(CityRegistrationActivity$$Lambda$2.lambdaFactory$(this)).build();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C5630R.layout.activity_city_registration);
        ButterKnife.bind((Activity) this);
        this.photoCompressor = new PhotoCompressor(this);
        this.pagesEditedInReview = new HashMap();
        if (savedInstanceState == null) {
            this.listing = (Listing) getIntent().getParcelableExtra("listing");
            this.listingRegistrationProcess = (ListingRegistrationProcess) getIntent().getParcelableExtra(CityRegistrationIntents.INTENT_EXTRA_LISTING_REGISTRATION_PROCESS);
            this.isLYS = getIntent().getBooleanExtra(CityRegistrationIntents.INTENT_EXTRA_IS_LYS, false);
            this.progress = (Float) getIntent().getSerializableExtra("progress");
        }
        if (this.progress != null) {
            this.progressBar.setVisibility(0);
            this.progressBar.setProgress(this.progress.floatValue());
        }
        setOnHomePressedListener(CityRegistrationActivity$$Lambda$3.lambdaFactory$(this));
        if (savedInstanceState == null) {
            cityRegistration();
        }
    }

    public void onBackPressed() {
        if (this.numModalsLaunched > 0) {
            closeAllModals();
        } else if (!getSupportFragmentManager().popBackStackImmediate()) {
            finishCancel();
        } else if (this.currentInputGroupIndex > 0) {
            this.currentInputGroupIndex--;
        }
    }

    private void closeAllModals() {
        for (int i = 0; i < this.numModalsLaunched; i++) {
            getSupportFragmentManager().popBackStackImmediate();
        }
        this.numModalsLaunched = 0;
        if (this.standalonePage) {
            this.standalonePage = false;
        }
    }

    public Listing getListing() {
        return this.listing;
    }

    public void setListing(Listing listing2) {
        this.listing = listing2;
    }

    public ListingRegistrationProcess getListingRegistrationProcess() {
        return this.listingRegistrationProcess;
    }

    public void setListingRegistrationProcess(ListingRegistrationProcess listingRegistrationProcess2) {
        this.listingRegistrationProcess = listingRegistrationProcess2;
    }

    public CityRegistrationActionExecutor getActionExecutor() {
        return this;
    }

    public void finishOk() {
        setResult(-1, getIntentData());
        finish();
    }

    public void finishCancel() {
        setResult(0, getIntentData());
        finish();
    }

    public void finishSaveAndExit() {
        setResult(100, getIntentData());
        finish();
    }

    public void finishEdit(boolean hasEdits, String editedGroupId) {
        this.standalonePage = false;
        this.numModalsLaunched = 0;
        getSupportFragmentManager().popBackStack();
        if (hasEdits) {
            ((CityRegistrationApplicationFragment) getSupportFragmentManager().findFragmentById(C5630R.C5632id.content_container)).updateAdapter();
            this.pagesEditedInReview.put(editedGroupId, Boolean.valueOf(true));
        }
    }

    public ListingRegistrationProcessInputGroup getInputGroupFromIndex(int index) {
        return this.listingRegistrationProcess.getInputGroupFromIndex(index);
    }

    public AirAddress getAddress(ListingRegistrationProcessInputGroup group) {
        AirAddress savedAddress = (group == null || !group.hasAddressInput()) ? null : this.listingRegistrationProcess.getOpenSubmission().getAddressAnswer(group.getAddressInput().getInputKey());
        return savedAddress == null ? AirAddressUtil.fromListing(this.listing) : savedAddress;
    }

    private Intent getIntentData() {
        Intent data = new Intent();
        data.putExtra("listing", this.listing);
        data.putExtra(CityRegistrationIntents.INTENT_EXTRA_LISTING_REGISTRATION_PROCESS, this.listingRegistrationProcess);
        return data;
    }

    public boolean isLYS() {
        return this.isLYS;
    }

    private void showFragment(Fragment fragment) {
        showFragmentInContainer(fragment, C5630R.C5632id.content_container);
    }

    private void showFragmentInContainer(Fragment fragment, int fragmentContainerId) {
        showFragment(fragment, fragmentContainerId, FragmentTransitionType.SlideInFromSide, true, fragment.getClass().getCanonicalName());
    }

    public void cityRegistration() {
        ListingRegistrationProcess listingRegistrationProcess2 = getListingRegistrationProcess();
        if (listingRegistrationProcess2 == null || !listingRegistrationProcess2.isRegulatoryBodySupported()) {
            finishSaveAndExit();
            return;
        }
        setSavedAnwers();
        ListingRegistration listingRegistration = listingRegistrationProcess2.getListingRegistration();
        if (listingRegistration == null || listingRegistration.getStatus() == ListingRegistrationStatus.Unregistered) {
            showFragment(CityRegistrationOverviewFragment.create());
        } else if (listingRegistration.getStatus() == ListingRegistrationStatus.Exempted) {
            showFragment(CityRegistrationExemptionFragment.create());
        } else if (listingRegistration.getStatus().hasBeenSubmitted()) {
            showFragment(CityRegistrationReviewFragment.create());
        }
    }

    private void setSavedAnwers() {
        ListingRegistrationSubmission openSubmission = this.listingRegistrationProcess.getOpenSubmission();
        for (ListingRegistrationProcessInputGroup inputGroup : this.listingRegistrationProcess.getInputGroups()) {
            for (ListingRegistrationQuestion question : inputGroup.getQuestions()) {
                if (openSubmission.hasAnswer(question.getInputKey())) {
                    if (question.getInputType().isTextInputType()) {
                        question.setInputAnswer(openSubmission.getStringAnswer(question.getInputKey()));
                    } else if (question.getInputType() == ListingRegistrationInputType.FileUpload) {
                        ListingRegistrationFileAnswer fileAnswer = this.listingRegistrationProcess.getOpenSubmission().getFileAnswer(question.getInputKey());
                        if (fileAnswer != null) {
                            question.setInputAnswer(fileAnswer.getValue());
                        }
                    } else if (question.getInputType() == ListingRegistrationInputType.Checkbox) {
                        question.setInputAnswer(this.listingRegistrationProcess.getOpenSubmission().getListAnswerString(question.getInputKey()));
                    }
                }
            }
        }
    }

    public void cityRegistrationExemption() {
        showFragment(CityRegistrationExemptionFragment.create());
    }

    public void cityRegistrationInputGroup(int groupIndex) {
        AirAddress savedAddress = null;
        if (this.listingRegistrationProcess == null) {
            cityRegistrationApplication();
            return;
        }
        this.currentInputGroupIndex = groupIndex;
        ListingRegistrationProcessInputGroup inputGroup = getInputGroupFromIndex(groupIndex);
        if (inputGroup != null) {
            if (inputGroup.hasAddressInput()) {
                savedAddress = this.listingRegistrationProcess.getOpenSubmission().getAddressAnswer(inputGroup.getAddressInput().getInputKey());
            }
            if (savedAddress == null) {
                AirAddress fromListing = AirAddressUtil.fromListing(this.listing);
            } else {
                AirAddress address = savedAddress;
            }
            showFragment(CityRegistrationInputGroupFragment.create(groupIndex, groupIndex + 1, false));
        } else if (this.standalonePage) {
            finishEdit(false, null);
        } else {
            cityRegistrationApplication();
        }
    }

    public void cityRegistrationApplication() {
        showFragment(CityRegistrationApplicationFragment.create());
    }

    public void cityRegistrationTermsAndConditions() {
        showFragment(CityRegistrationTermsAndConditionsFragment.create());
    }

    public void cityRegistrationNextSteps() {
        showFragment(CityRegistrationNextStepsFragment.create());
    }

    public void showModal(Fragment fragment) {
        this.numModalsLaunched++;
        showModal(fragment, C5630R.C5632id.content_container, C5630R.C5632id.modal_container, true);
    }

    public String getDocFileUrl(String inputKey) {
        ListingRegistrationFileAnswer answer;
        if (this.listingRegistrationProcess.getOpenSubmission() == null) {
            answer = null;
        } else {
            answer = this.listingRegistrationProcess.getOpenSubmission().getFileAnswer(inputKey);
        }
        if (answer == null) {
            return null;
        }
        return answer.getUrl();
    }

    static /* synthetic */ void lambda$new$1(CityRegistrationActivity cityRegistrationActivity, ListingRegistrationOpenSubmissionResponse data) {
        CityRegistrationInputGroupFragment fragment = cityRegistrationActivity.getCurrentInputGroupFragment();
        fragment.hideLoader();
        cityRegistrationActivity.listingRegistrationProcess.getOpenSubmission().setAnswers(data.openSubmission.getAnswers());
        fragment.notifyDataSetChanged();
    }

    static /* synthetic */ void lambda$new$3(CityRegistrationActivity cityRegistrationActivity, AirRequestNetworkException e) {
        cityRegistrationActivity.getCurrentInputGroupFragment().hideLoader();
        NetworkUtil.tryShowRetryableErrorWithSnackbar(cityRegistrationActivity.getCurrentFocus(), (NetworkException) e, CityRegistrationActivity$$Lambda$4.lambdaFactory$(cityRegistrationActivity));
    }

    public void saveDoc(ListingRegistrationQuestion question, String filePath) {
        if (filePath == null) {
            closeAllModals();
            return;
        }
        updateQuestion(question);
        this.listingRegistrationProcess.getOpenSubmission().putFileAnswer(question.getInputKey(), new String(Base64.encodeBase64(IOUtils.readBytesFromFile(new File(filePath)))), IOUtils.getContentType(filePath).toString(), question.getInputAnswer());
        this.answers = new HashMap();
        this.answers.put(question.getInputKey(), this.listingRegistrationProcess.getOpenSubmission().getAnswerMap(question.getInputKey()));
        updateListingRegistrationOpenSubmission(this.answers);
    }

    public void saveAddress(ListingRegistrationQuestion question, AirAddress address) {
        if (this.listingRegistrationProcess.getOpenSubmission().putAddressAnswer(question.getInputKey(), address)) {
            this.answers = new HashMap();
            this.answers.put(question.getInputKey(), this.listingRegistrationProcess.getOpenSubmission().getAnswerMap(question.getInputKey()));
            updateListingRegistrationOpenSubmission(this.answers);
            return;
        }
        getCurrentInputGroupFragment().finish();
    }

    public void saveTextAnswers(ListingRegistrationProcessInputGroup inputGroup) {
        boolean hasAnswersToSave;
        boolean hasAnswersToSave2 = false;
        this.answers = new HashMap();
        for (ListingRegistrationQuestion question : inputGroup.getQuestions()) {
            if (question.getInputType().isTextInputType()) {
                if (this.listingRegistrationProcess.getOpenSubmission().putStringAnswer(question.getInputKey(), question.getInputAnswer()) || hasAnswersToSave2) {
                    hasAnswersToSave2 = true;
                } else {
                    hasAnswersToSave2 = false;
                }
                this.answers.put(question.getInputKey(), question.getInputAnswer());
            } else if (question.getInputType() == ListingRegistrationInputType.Checkbox) {
                if (this.listingRegistrationProcess.getOpenSubmission().putListAnswer(question.getInputKey(), question.getInputAnswer()) || hasAnswersToSave2) {
                    hasAnswersToSave = true;
                } else {
                    hasAnswersToSave = false;
                }
                this.answers.put(question.getInputKey(), this.listingRegistrationProcess.getOpenSubmission().getAnswerStringList(question.getInputKey()));
            }
        }
        if (hasAnswersToSave2) {
            updateListingRegistrationOpenSubmission(this.answers);
        } else {
            getCurrentInputGroupFragment().finish();
        }
    }

    private CityRegistrationInputGroupFragment getCurrentInputGroupFragment() {
        return (CityRegistrationInputGroupFragment) getSupportFragmentManager().findFragmentById(this.standalonePage ? C5630R.C5632id.modal_container : C5630R.C5632id.content_container);
    }

    /* access modifiers changed from: private */
    public void updateListingRegistrationOpenSubmission(HashMap answers2) {
        getCurrentInputGroupFragment().showLoader();
        new UpdateListingRegistrationOpenSubmissionRequest(this.listingRegistrationProcess, answers2).withListener((Observer) this.updateListingRegistrationOpenSubmissionListener).execute(this.requestManager);
    }

    public void deleteDoc(ListingRegistrationQuestion question) {
        this.listingRegistrationProcess.getOpenSubmission().removeAnswer(question.getInputKey());
        question.setInputAnswer(null);
        updateQuestion(question);
        this.answers = new HashMap();
        this.answers.put(question.getInputKey(), null);
        updateListingRegistrationOpenSubmission(this.answers);
    }

    private void updateQuestion(ListingRegistrationQuestion question) {
        ListingRegistrationProcessInputGroup inputGroup = getInputGroupFromIndex(this.currentInputGroupIndex);
        int index = 0;
        Iterator it = inputGroup.getQuestions().iterator();
        while (it.hasNext() && !((ListingRegistrationQuestion) it.next()).getInputKey().equals(question.getInputKey())) {
            index++;
        }
        inputGroup.getQuestions().set(index, question);
        closeAllModals();
        ((CityRegistrationInputGroupFragment) getSupportFragmentManager().findFragmentById(C5630R.C5632id.content_container)).setQuestionInputGroup(inputGroup);
    }

    public void showDocTypeSelection(ListingRegistrationQuestion question) {
        showModal(CityRegistrationDocTypeSelectionFragment.create(question));
    }

    public void showDocUploadReviewWithFilePath(ListingRegistrationQuestion question, String filePath) {
        showModal(CityRegistrationDocReviewFragment.createWithFilePath(question, filePath));
    }

    public void showDocUploadReviewWithUrl(ListingRegistrationQuestion question, String url) {
        showModal(CityRegistrationDocReviewFragment.createWithUrl(question, url));
    }

    public void getDocPhoto(String selectedDocType) {
        startActivityForResult(AirPhotoPicker.builder().targetOutputDimensions(2048, 2048).create(this), CityRegistrationController.RC_CHOOSE_PHOTO);
    }

    public void handleImageCapture(Uri source, final ListingRegistrationQuestion question, String docType) {
        this.photoCompressor.compressPhoto(source, new PhotoCompressionCallback() {
            public void onPhotoCompressed(String filePath) {
                CityRegistrationActivity.this.showDocUploadReviewWithFilePath(question, filePath);
            }

            public void onCompressionFailure() {
            }
        });
    }

    public void showInputGroupModal(ListingRegistrationProcessInputGroup group) {
        if (group != null) {
            this.standalonePage = true;
            showModal(CityRegistrationInputGroupFragment.create(this.listingRegistrationProcess.indexOfInputGroup(group.getGroupId()), this.listingRegistrationProcess.getInputGroups().size(), true));
        }
    }

    public boolean shouldUpdateInputGroup(String inputGroupId) {
        if (this.standalonePage || inputGroupId == null || this.pagesEditedInReview.get(inputGroupId) == null) {
            return false;
        }
        this.pagesEditedInReview.remove(inputGroupId);
        return true;
    }
}
