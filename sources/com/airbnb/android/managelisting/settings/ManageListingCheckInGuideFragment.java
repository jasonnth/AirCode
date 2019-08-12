package com.airbnb.android.managelisting.settings;

import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.app.AlertDialog.Builder;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.UnhandledStateException;
import com.airbnb.android.core.enums.CheckInGuideStatus;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.intents.CheckinIntents;
import com.airbnb.android.core.intents.ManageListingIntents;
import com.airbnb.android.core.models.CheckInGuide;
import com.airbnb.android.core.models.CheckInStep;
import com.airbnb.android.core.requests.CreateCheckInGuideRequest;
import com.airbnb.android.core.requests.CreateCheckInStepRequest;
import com.airbnb.android.core.requests.DeleteCheckInStepRequest;
import com.airbnb.android.core.requests.GetCheckInGuideRequest;
import com.airbnb.android.core.requests.UpdateCheckInGuideRequest;
import com.airbnb.android.core.requests.photos.PhotoUpload;
import com.airbnb.android.core.requests.photos.PhotoUploadTarget;
import com.airbnb.android.core.responses.CheckInGuideResponse;
import com.airbnb.android.core.responses.CheckInStepResponse;
import com.airbnb.android.core.responses.PhotoUploadResponse;
import com.airbnb.android.core.utils.AirPhotoPicker;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.SettingDeepLink;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.android.core.views.OptionsMenuFactory;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.managelisting.ManageListingGraph;
import com.airbnb.android.managelisting.analytics.CheckInJitneyLogger;
import com.airbnb.android.photomarkupeditor.fragments.PhotoMarkupEditorFragment;
import com.airbnb.android.photopicker.PhotoPicker;
import com.airbnb.android.photouploadmanager.PhotoUploadListener;
import com.airbnb.android.photouploadmanager.PhotoUploadManager;
import com.airbnb.android.photouploadmanager.PhotoUploadTransaction;
import com.airbnb.android.utils.LocaleUtil;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.CheckInGuideStepCard.LoadingState;
import com.airbnb.p027n2.components.fixed_footers.FixedDualActionFooter;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.UnmodifiableIterator;
import icepick.State;
import java.util.ArrayList;
import java.util.List;
import p032rx.Observer;

public class ManageListingCheckInGuideFragment extends ManageListingBaseFragment {
    private static final boolean DONE_LOADING = false;
    private static final boolean IS_LOADING = true;
    private static final int MIN_CARDS_TO_SHOW = 3;
    private static final int REQUEST_CODE_CAPTURE_IMAGE = 100;
    private static final int REQUEST_CODE_EDIT_STEP_PHOTO = 300;
    private static final int REQUEST_CODE_PHOTO_MARKUP = 200;
    final RequestListener<CheckInGuideResponse> createGuideListener = new C0699RL().onResponse(ManageListingCheckInGuideFragment$$Lambda$3.lambdaFactory$(this)).onError(ManageListingCheckInGuideFragment$$Lambda$4.lambdaFactory$(this)).build();
    final RequestListener<CheckInStepResponse> createStepForPhotoListener = new C0699RL().onResponse(ManageListingCheckInGuideFragment$$Lambda$9.lambdaFactory$(this)).onError(ManageListingCheckInGuideFragment$$Lambda$10.lambdaFactory$(this)).onComplete(ManageListingCheckInGuideFragment$$Lambda$11.lambdaFactory$(this)).build();
    @State
    long currentStepId;
    final RequestListener<CheckInStepResponse> deleteStepListener = new C0699RL().onResponse(ManageListingCheckInGuideFragment$$Lambda$7.lambdaFactory$(this)).onError(ManageListingCheckInGuideFragment$$Lambda$8.lambdaFactory$(this)).build();
    /* access modifiers changed from: private */
    public ManageListingCheckInGuideController epoxyController;
    @State
    String errorMessage;
    @BindView
    FixedDualActionFooter footer;
    final RequestListener<CheckInGuideResponse> getGuideListener = new C0699RL().onResponse(ManageListingCheckInGuideFragment$$Lambda$1.lambdaFactory$(this)).onError(ManageListingCheckInGuideFragment$$Lambda$2.lambdaFactory$(this)).build();
    @State
    String imagePath;
    @State
    boolean isPublishing;
    CheckInJitneyLogger jitneyLogger;
    private final Listener listener = new Listener() {
        public void editStep(int stepNumber, long stepId) {
            if (!ManageListingCheckInGuideFragment.this.epoxyController.hasPendingImageUpload(stepId)) {
                ManageListingCheckInGuideFragment.this.currentStepId = stepId;
                ManageListingCheckInGuideFragment.this.showEditStepOptions(stepNumber, stepId);
            }
        }

        public void editPhoto(int stepNumber, long stepId) {
            ManageListingCheckInGuideFragment.this.currentStepId = stepId;
            ManageListingCheckInGuideFragment.this.startActivityForResult(PhotoMarkupEditorFragment.intentForCheckInGuideEdit(ManageListingCheckInGuideFragment.this.getContext(), ManageListingCheckInGuideFragment.this.currentStep().getPictureUrl()), 300);
        }

        public void addPhoto(int stepNumber, long stepId) {
            if (stepNumber > ManageListingCheckInGuideFragment.this.numActualSteps) {
                ManageListingCheckInGuideFragment.this.showEditStepSnackbar();
                return;
            }
            ManageListingCheckInGuideFragment.this.currentStepId = stepId;
            ManageListingCheckInGuideFragment.this.launchPhotoActivity(0);
        }

        public void addNote(int stepNumber, long stepId) {
            if (stepNumber > ManageListingCheckInGuideFragment.this.numActualSteps) {
                ManageListingCheckInGuideFragment.this.showEditStepSnackbar();
                return;
            }
            ManageListingCheckInGuideFragment.this.currentStepId = stepId;
            ManageListingCheckInGuideFragment.this.controller.actionExecutor.checkInStep(stepNumber, stepId);
        }

        public void addEmptyStepCard() {
            if (ManageListingCheckInGuideFragment.this.numCardsToDisplay > ManageListingCheckInGuideFragment.this.numActualSteps) {
                ManageListingCheckInGuideFragment.this.showEditStepSnackbar();
                return;
            }
            ManageListingCheckInGuideFragment.this.numCardsToDisplay++;
            ManageListingCheckInGuideFragment.this.epoxyController.setStepCards(ManageListingCheckInGuideFragment.this.stepsForDisplay(ManageListingCheckInGuideFragment.this.controller.getCheckInGuide()));
        }
    };
    @BindView
    LoaderFrame loader;
    @State
    int numActualSteps;
    @State
    int numCardsToDisplay;
    private final PhotoUploadListener photoUploadListener = new PhotoUploadListener() {
        public void uploadPending(long offlineId, PhotoUpload photoUpload) {
            ManageListingCheckInGuideFragment.this.epoxyController.setImageLoadingForStepId(photoUpload.uploadRequestId(), photoUpload.path(), LoadingState.Loading);
        }

        public void uploadFailed(long offlineId, PhotoUpload photoUpload) {
            ManageListingCheckInGuideFragment.this.epoxyController.setImageLoadingForStepId(photoUpload.uploadRequestId(), photoUpload.path(), LoadingState.Failed);
        }

        public void uploadSucceded(long offlineId, PhotoUpload photoUpload, PhotoUploadResponse response) {
            ManageListingCheckInGuideFragment.this.controller.updateStep(response.step);
            ManageListingCheckInGuideFragment.this.epoxyController.setImageLoadingForStepId(response.step.getId(), LoadingState.None);
        }

        public void uploadRemoved(long offlineId, PhotoUpload photoUpload) {
            ManageListingCheckInGuideFragment.this.epoxyController.setImageLoadingForStepId(photoUpload.uploadRequestId(), LoadingState.None);
        }

        public void retryAllUploads(long galleryID) {
            ManageListingCheckInGuideFragment.this.updateImageLoadingStatesForSteps();
        }
    };
    PhotoUploadManager photoUploadManager;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;
    final RequestListener<CheckInGuideResponse> updateGuideListener = new C0699RL().onResponse(ManageListingCheckInGuideFragment$$Lambda$5.lambdaFactory$(this)).onError(ManageListingCheckInGuideFragment$$Lambda$6.lambdaFactory$(this)).build();

    private enum EditStepAction {
        EditPhoto(C7368R.string.manage_listing_check_in_guide_edit_photo_option),
        RetryPhotoUpload(C7368R.string.manage_listing_check_in_guide_retry_photo_option),
        EditNote(C7368R.string.manage_listing_check_in_guide_edit_note_option),
        SelectPhoto(C7368R.string.manage_listing_check_in_guide_select_photo_option),
        TakePhoto(C7368R.string.manage_listing_check_in_guide_take_new_photo_option),
        DeleteStep(C7368R.string.manage_listing_check_in_guide_delete_step_option);
        
        /* access modifiers changed from: private */
        public final int titleRes;

        private EditStepAction(int titleRes2) {
            this.titleRes = titleRes2;
        }
    }

    public static ManageListingCheckInGuideFragment create() {
        return new ManageListingCheckInGuideFragment();
    }

    /* access modifiers changed from: private */
    public void launchPhotoActivity(int imageSource) {
        startActivityForResult(AirPhotoPicker.builder().setSource(imageSource).targetOutputDimensions(2048, 2048).create(getContext()), 100);
    }

    static /* synthetic */ void lambda$new$0(ManageListingCheckInGuideFragment manageListingCheckInGuideFragment, CheckInGuideResponse response) {
        manageListingCheckInGuideFragment.controller.setCheckInGuide(response.guide);
        manageListingCheckInGuideFragment.getActivity().invalidateOptionsMenu();
    }

    static /* synthetic */ void lambda$new$1(ManageListingCheckInGuideFragment manageListingCheckInGuideFragment, CheckInGuideResponse response) {
        manageListingCheckInGuideFragment.controller.setCheckInGuide(response.guide);
        manageListingCheckInGuideFragment.getActivity().invalidateOptionsMenu();
        manageListingCheckInGuideFragment.controller.actionExecutor.invalidateData();
    }

    static /* synthetic */ void lambda$new$2(ManageListingCheckInGuideFragment manageListingCheckInGuideFragment, CheckInGuideResponse response) {
        manageListingCheckInGuideFragment.controller.setCheckInGuide(response.guide);
        manageListingCheckInGuideFragment.footer.setButtonLoading(false);
        if (manageListingCheckInGuideFragment.isPublishing) {
            manageListingCheckInGuideFragment.controller.actionExecutor.publishedGuideConfirmation();
        } else {
            manageListingCheckInGuideFragment.controller.actionExecutor.popToHome();
        }
    }

    static /* synthetic */ void lambda$new$3(ManageListingCheckInGuideFragment manageListingCheckInGuideFragment, AirRequestNetworkException error) {
        manageListingCheckInGuideFragment.footer.setButtonLoading(false);
        manageListingCheckInGuideFragment.footer.setSecondaryButtonEnabled(IS_LOADING);
        NetworkUtil.tryShowErrorWithSnackbar(manageListingCheckInGuideFragment.getView(), error);
    }

    static /* synthetic */ void lambda$new$4(ManageListingCheckInGuideFragment manageListingCheckInGuideFragment, CheckInStepResponse response) {
        manageListingCheckInGuideFragment.controller.removeStep(response.step);
        manageListingCheckInGuideFragment.getActivity().invalidateOptionsMenu();
    }

    static /* synthetic */ void lambda$new$6(ManageListingCheckInGuideFragment manageListingCheckInGuideFragment, CheckInStepResponse response) {
        manageListingCheckInGuideFragment.controller.updateStep(response.step);
        manageListingCheckInGuideFragment.getActivity().invalidateOptionsMenu();
        manageListingCheckInGuideFragment.updateStepWithImagePath(response.step.getId());
    }

    public void dataUpdated() {
        if (this.controller.getCheckInGuide() != null) {
            int newStepCount = this.controller.getCheckInGuide().getSteps().size();
            if (newStepCount != this.numActualSteps) {
                this.numCardsToDisplay = newStepCount;
            }
            this.numCardsToDisplay = Math.max(3, this.numCardsToDisplay);
            this.numActualSteps = newStepCount;
            showGuide();
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.epoxyController = new ManageListingCheckInGuideController(getContext(), this.listener);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((ManageListingGraph) CoreApplication.instance(getContext()).component()).inject(this);
        View view = inflater.inflate(C7368R.layout.fragment_listing_recycler_view_with_footer_bar, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(IS_LOADING);
        this.recyclerView.setAdapter(this.epoxyController.getAdapter());
        if (!TextUtils.isEmpty(this.errorMessage)) {
            NetworkUtil.tryShowRetryableErrorWithSnackbar(view, this.errorMessage, ManageListingCheckInGuideFragment$$Lambda$12.lambdaFactory$(this));
        } else if (!this.requestManager.hasRequest((BaseRequestListener<T>) this.getGuideListener, GetCheckInGuideRequest.class) && !this.requestManager.hasRequest((BaseRequestListener<T>) this.createGuideListener, CreateCheckInGuideRequest.class)) {
            this.epoxyController.setLoading(IS_LOADING);
            getOrCreateGuide();
        }
        return view;
    }

    public void onResume() {
        super.onResume();
        if (getCheckInGuideId() != null) {
            updateImageLoadingStatesForSteps();
            this.photoUploadManager.addListenerForPhotoUploadTarget(getCheckInGuideId().longValue(), PhotoUploadTarget.CheckInGuide, this.photoUploadListener);
        }
    }

    public void onPause() {
        super.onPause();
        if (getCheckInGuideId() != null) {
            this.photoUploadManager.removeListenerForPhotoUploadTarget(getCheckInGuideId().longValue(), PhotoUploadTarget.CheckInGuide, this.photoUploadListener);
        }
    }

    public void onDestroyView() {
        this.recyclerView.setAdapter(null);
        super.onDestroyView();
    }

    /* access modifiers changed from: private */
    public void updateImageLoadingStatesForSteps() {
        if (getCheckInGuideId() != null) {
            UnmodifiableIterator it = this.photoUploadManager.getOrderedOutgoingItems(getCheckInGuideId().longValue(), PhotoUploadTarget.CheckInGuide).iterator();
            while (it.hasNext()) {
                PhotoUpload photoUpload = ((PhotoUploadTransaction) it.next()).getPhotoUpload();
                switch (((PhotoUploadTransaction) it.next()).getState()) {
                    case Pending:
                        this.epoxyController.setImageLoadingForStepId(photoUpload.uploadRequestId(), photoUpload.path(), LoadingState.Loading);
                        break;
                    case Failed:
                        this.epoxyController.setImageLoadingForStepId(photoUpload.uploadRequestId(), photoUpload.path(), LoadingState.Failed);
                        break;
                    default:
                        this.epoxyController.setImageLoadingForStepId(photoUpload.uploadRequestId(), LoadingState.None);
                        break;
                }
            }
        }
    }

    private void setupFooter() {
        this.footer.setVisibility(0);
        if (guideIsEmpty()) {
            this.footer.setButtonText((CharSequence) "");
            this.footer.setButtonOnClickListener(null);
            this.footer.setSecondaryButtonText(C7368R.string.manage_listing_check_in_guide_see_example);
            this.footer.setSecondaryButtonOnClickListener(ManageListingCheckInGuideFragment$$Lambda$13.lambdaFactory$(this));
        } else if (isPublished()) {
            this.footer.setButtonText(C7368R.string.preview);
            this.footer.setButtonOnClickListener(ManageListingCheckInGuideFragment$$Lambda$14.lambdaFactory$(this));
            this.footer.setSecondaryButtonText((CharSequence) "");
            this.footer.setSecondaryButtonOnClickListener(null);
        } else {
            this.footer.setButtonText(C7368R.string.manage_listing_check_in_guide_publish_button);
            this.footer.setButtonOnClickListener(ManageListingCheckInGuideFragment$$Lambda$15.lambdaFactory$(this));
            this.footer.setSecondaryButtonText(C7368R.string.preview);
            this.footer.setSecondaryButtonOnClickListener(ManageListingCheckInGuideFragment$$Lambda$16.lambdaFactory$(this));
        }
    }

    private void showGuide() {
        this.epoxyController.setStepCards(stepsForDisplay(this.controller.getCheckInGuide()));
        this.epoxyController.setLoading(false);
        setupFooter();
    }

    /* access modifiers changed from: private */
    public void showOrSaveNetworkError(NetworkException e) {
        if (getView() == null) {
            this.errorMessage = NetworkUtil.getErrorMessage(getContext(), e);
        } else {
            NetworkUtil.tryShowRetryableErrorWithSnackbar(getView(), e, ManageListingCheckInGuideFragment$$Lambda$17.lambdaFactory$(this));
        }
        this.epoxyController.setLoading(false);
    }

    static /* synthetic */ void lambda$showOrSaveNetworkError$14(ManageListingCheckInGuideFragment manageListingCheckInGuideFragment, View v) {
        if (manageListingCheckInGuideFragment.controller != null) {
            manageListingCheckInGuideFragment.getOrCreateGuide();
        }
    }

    /* access modifiers changed from: private */
    public void getOrCreateGuide() {
        this.errorMessage = null;
        if (this.controller.getListing().getCheckInGuideStatusEnum() == CheckInGuideStatus.NotCreated) {
            new CreateCheckInGuideRequest(this.controller.getListingId(), this.controller.getListing().getDescriptionLocale()).withListener((Observer) this.createGuideListener).execute(this.requestManager);
            this.jitneyLogger.logCheckinGuideCreateEvent(getListingId());
            return;
        }
        fetchGuide();
        this.jitneyLogger.logCheckinGuideFetchEvent(getListingId());
    }

    private void fetchGuide() {
        GetCheckInGuideRequest.forListingId(this.controller.getListingId(), LocaleUtil.getDeviceLanguage(getContext())).withListener((Observer) this.getGuideListener).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void getAnExample() {
        startActivity(CheckinIntents.intentForSample(getContext(), this.controller.getListingId()));
    }

    /* access modifiers changed from: private */
    public void publishGuide() {
        this.footer.setButtonLoading(IS_LOADING);
        this.footer.setSecondaryButtonEnabled(false);
        this.isPublishing = IS_LOADING;
        UpdateCheckInGuideRequest.forPublish(this.controller.getListingId()).withListener((Observer) this.updateGuideListener).execute(this.requestManager);
        this.jitneyLogger.logCheckinGuideToolbarPublishEvent(getListingId());
    }

    /* access modifiers changed from: private */
    public void preview() {
        this.jitneyLogger.logCheckinGuideToolbarPreviewEvent(getListingId());
        startActivity(CheckinIntents.intentForListingAndStep(getContext(), this.controller.getListingId(), this.epoxyController.getCurrentStepIndex()));
    }

    private void editCheckInMethod() {
        this.controller.actionExecutor.checkInMethod();
    }

    private void reorderSteps() {
        this.controller.actionExecutor.reorderCheckInSteps();
    }

    private void deleteSteps() {
        notYetImplemented();
    }

    /* access modifiers changed from: private */
    public void unpublish() {
        this.footer.setButtonLoading(IS_LOADING);
        this.footer.setSecondaryButtonEnabled(false);
        this.isPublishing = false;
        UpdateCheckInGuideRequest.forUnpublish(this.controller.getListingId()).withListener((Observer) this.updateGuideListener).execute(this.requestManager);
        this.jitneyLogger.logCheckinGuideToolbarUnpublishEvent(getListingId());
    }

    /* access modifiers changed from: private */
    public void showEditStepOptions(int stepNumber, long stepId) {
        ArrayList<EditStepAction> editStepActions = Lists.newArrayList((E[]) EditStepAction.values());
        if (currentStep().getPictureUrl() == null) {
            editStepActions.remove(EditStepAction.EditPhoto);
        }
        if (this.epoxyController.hasFailedImageUpload(stepId)) {
            editStepActions.remove(EditStepAction.EditPhoto);
        } else {
            editStepActions.remove(EditStepAction.RetryPhotoUpload);
        }
        OptionsMenuFactory.forItems(getContext(), (List<T>) editStepActions).setTitleResTransformer(ManageListingCheckInGuideFragment$$Lambda$18.lambdaFactory$()).setListener(ManageListingCheckInGuideFragment$$Lambda$19.lambdaFactory$(this, stepNumber, stepId)).buildAndShow();
    }

    static /* synthetic */ void lambda$showEditStepOptions$16(ManageListingCheckInGuideFragment manageListingCheckInGuideFragment, int stepNumber, long stepId, EditStepAction action) {
        switch (action) {
            case EditPhoto:
                manageListingCheckInGuideFragment.listener.editPhoto(stepNumber, stepId);
                return;
            case RetryPhotoUpload:
                manageListingCheckInGuideFragment.photoUploadManager.retryFailedUpload(stepId, PhotoUploadTarget.CheckInGuide);
                return;
            case EditNote:
                manageListingCheckInGuideFragment.listener.addNote(stepNumber, stepId);
                return;
            case TakePhoto:
                manageListingCheckInGuideFragment.launchPhotoActivity(1);
                return;
            case SelectPhoto:
                manageListingCheckInGuideFragment.launchPhotoActivity(2);
                return;
            case DeleteStep:
                manageListingCheckInGuideFragment.photoUploadManager.cancelFailedPhotoUpload(stepId, PhotoUploadTarget.CheckInGuide);
                new DeleteCheckInStepRequest(stepId).withListener((Observer) manageListingCheckInGuideFragment.deleteStepListener).execute(manageListingCheckInGuideFragment.requestManager);
                manageListingCheckInGuideFragment.jitneyLogger.logCheckinGuideDeleteStepEvent(stepId, manageListingCheckInGuideFragment.getListingId());
                return;
            default:
                BugsnagWrapper.throwOrNotify((RuntimeException) new UnhandledStateException(action));
                return;
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(C7368R.C7371menu.check_in_guide_options, menu);
    }

    public void onPrepareOptionsMenu(Menu menu) {
        boolean haveGuide;
        boolean z;
        boolean z2;
        boolean z3 = IS_LOADING;
        super.onPrepareOptionsMenu(menu);
        if (this.controller.getCheckInGuide() != null) {
            haveGuide = true;
        } else {
            haveGuide = false;
        }
        MenuItem findItem = menu.findItem(C7368R.C7370id.reorder_steps_button);
        if (!haveGuide || this.numActualSteps <= 1) {
            z = false;
        } else {
            z = true;
        }
        findItem.setVisible(z);
        MenuItem findItem2 = menu.findItem(C7368R.C7370id.unpublish_button);
        if (!haveGuide || !isPublished()) {
            z2 = false;
        } else {
            z2 = true;
        }
        findItem2.setVisible(z2);
        menu.findItem(C7368R.C7370id.delete_steps_button).setVisible(false);
        menu.findItem(C7368R.C7370id.check_in_methods_button).setVisible(IS_LOADING);
        MenuItem findItem3 = menu.findItem(C7368R.C7370id.see_example_button);
        if (!haveGuide || guideIsEmpty()) {
            z3 = false;
        }
        findItem3.setVisible(z3);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        long itemId = (long) item.getItemId();
        if (itemId == ((long) C7368R.C7370id.reorder_steps_button)) {
            reorderSteps();
        } else if (itemId == ((long) C7368R.C7370id.unpublish_button)) {
            showUnpublishFrictionDialog();
        } else if (itemId == ((long) C7368R.C7370id.see_example_button)) {
            getAnExample();
        } else if (itemId == ((long) C7368R.C7370id.delete_steps_button)) {
            deleteSteps();
        } else if (itemId == ((long) C7368R.C7370id.check_in_methods_button)) {
            editCheckInMethod();
            this.jitneyLogger.logCheckinGuideToolbarEditMethodsEvent(getListingId());
        } else {
            BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalArgumentException("Unknown menu option: " + item.getTitle()));
            return super.onOptionsItemSelected(item);
        }
        return IS_LOADING;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
            switch (requestCode) {
                case 100:
                    startActivityForResult(PhotoMarkupEditorFragment.intentForCheckInGuide(getContext(), data.getStringExtra(PhotoPicker.EXTRA_PHOTO_PATH)), 200);
                    break;
                case 200:
                    this.imagePath = data.getStringExtra(PhotoMarkupEditorFragment.EXTRA_EDITED_IMAGE_PATH);
                    if (currentStep() != null) {
                        long stepId = currentStep().getId();
                        updateStepWithImagePath(stepId);
                        this.jitneyLogger.logCheckinGuideUpdateStepPhotoEvent(stepId, getListingId());
                        break;
                    } else {
                        showFullscreenLoader(IS_LOADING);
                        CreateCheckInStepRequest.forGuideId(this.controller.getCheckInGuide().getId()).withListener((Observer) this.createStepForPhotoListener).execute(this.requestManager);
                        this.jitneyLogger.logCheckinGuideCreateStepPhotoEvent(getListingId());
                        break;
                    }
                case 300:
                    this.imagePath = data.getStringExtra(PhotoMarkupEditorFragment.EXTRA_EDITED_IMAGE_PATH);
                    updateStepWithImagePath(currentStep().getId());
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void updateStepWithImagePath(long stepId) {
        this.photoUploadManager.cancelFailedPhotoUpload(stepId, PhotoUploadTarget.CheckInGuide);
        this.photoUploadManager.uploadImage(PhotoUpload.builder(stepId, this.imagePath).galleryId(this.controller.getCheckInGuide().getId()).uploadTarget(PhotoUploadTarget.CheckInGuide).shouldDeleteFileOnComplete(false).notificationIntent(ManageListingIntents.intentForExistingListingSetting(getContext(), getListingId(), SettingDeepLink.CheckInGuide)).build());
    }

    private boolean isPublished() {
        if (this.controller.getCheckInGuide().getPublishedStatus() == CheckInGuideStatus.Published) {
            return IS_LOADING;
        }
        return false;
    }

    private boolean guideIsEmpty() {
        return getStepsOrEmptyList(this.controller.getCheckInGuide()).isEmpty();
    }

    private Long getCheckInGuideId() {
        if (this.controller.checkInGuide != null) {
            return Long.valueOf(this.controller.checkInGuide.getId());
        }
        return null;
    }

    /* access modifiers changed from: private */
    public List<CheckInStep> stepsForDisplay(CheckInGuide guide) {
        return addStepsToNumCards(getStepsOrEmptyList(guide), this.numCardsToDisplay);
    }

    private static ArrayList<CheckInStep> getStepsOrEmptyList(CheckInGuide guide) {
        return guide == null ? new ArrayList<>() : new ArrayList<>(guide.getSteps());
    }

    private static ArrayList<CheckInStep> addStepsToNumCards(ArrayList<CheckInStep> steps, int numCardsToDisplay2) {
        while (steps.size() < numCardsToDisplay2) {
            steps.add(new CheckInStep());
        }
        return steps;
    }

    /* access modifiers changed from: private */
    public void showEditStepSnackbar() {
        new SnackbarWrapper().view(getView()).title(C7368R.string.manage_listing_check_in_guide_unfinished_action_title, (boolean) IS_LOADING).duration(0).body(getContext().getString(C7368R.string.manage_listing_check_in_guide_unfinished_action_message, new Object[]{Integer.valueOf(this.numActualSteps + 1)})).buildAndShow(1);
    }

    /* access modifiers changed from: private */
    public CheckInStep currentStep() {
        return this.controller.getCheckInStepById(this.currentStepId);
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        if (this.controller.getCheckInGuide() != null && !guideIsEmpty() && !isPublished()) {
            return IS_LOADING;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void showBackButtonDialog() {
        new Builder(getContext(), C7368R.C7373style.Theme_Airbnb_Dialog_Babu).setTitle(C7368R.string.manage_listing_check_in_guide_unpublished_guide_alert_title).setMessage(C7368R.string.manage_listing_check_in_guide_unpublished_guide_alert_message).setPositiveButton(C7368R.string.f10091xc943ab83, ManageListingCheckInGuideFragment$$Lambda$20.lambdaFactory$(this)).setNegativeButton(C7368R.string.f10090x931785a8, ManageListingCheckInGuideFragment$$Lambda$21.lambdaFactory$(this)).show();
    }

    private void showUnpublishFrictionDialog() {
        new Builder(getContext(), C7368R.C7373style.Theme_Airbnb_Dialog_Babu).setTitle(C7368R.string.manage_listing_check_in_guide_unpublish_friction_alert_title).setMessage(C7368R.string.manage_listing_check_in_guide_unpublish_friction_alert_message).setPositiveButton(C7368R.string.f10088x1923675, (OnClickListener) null).setNegativeButton(C7368R.string.f10089xf78b27a9, ManageListingCheckInGuideFragment$$Lambda$22.lambdaFactory$(this)).show();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingCheckinGuide;
    }

    /* access modifiers changed from: private */
    public void showFullscreenLoader(boolean show) {
        if (show) {
            this.loader.startAnimation();
        } else {
            this.loader.finish();
        }
    }

    private void notYetImplemented() {
        Toast.makeText(getContext(), "Under construction", 0).show();
    }
}
