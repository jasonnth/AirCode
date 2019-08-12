package com.airbnb.android.listyourspacedls.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.Photo;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.requests.photos.PhotoUploadTarget;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.listing.LYSStep;
import com.airbnb.android.listing.adapters.PhotoManagerAdapter;
import com.airbnb.android.listing.utils.ListingPhotoPickerUtil;
import com.airbnb.android.listing.utils.ListingPhotosUtil;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.android.listyourspacedls.LYSJitneyLogger;
import com.airbnb.android.listyourspacedls.ListYourSpaceDLSGraph;
import com.airbnb.android.photopicker.PhotoPicker;
import com.airbnb.android.photouploadmanager.PhotoUploadListener;
import com.airbnb.android.photouploadmanager.PhotoUploadListenerUtil;
import com.airbnb.android.photouploadmanager.PhotoUploadManager;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.photorearranger.PhotoRearranger;
import com.airbnb.p027n2.components.photorearranger.PhotoRearrangerController;
import com.airbnb.p027n2.components.photorearranger.PhotoRearrangerController.Mode;
import com.airbnb.p027n2.primitives.AirButton;
import icepick.State;
import java.util.ArrayList;
import p032rx.Observer;

public class LYSPhotoManagerFragment extends LYSBaseFragment {
    private static final int REQUEST_CODE_SELECT_PICTURE = 12;
    private PhotoManagerAdapter adapter;
    private MenuItem addPhotosMenuItem;
    LYSJitneyLogger jitneyLogger;
    @State
    Mode mode;
    private PhotoRearrangerController photoController;
    private final PhotoUploadListener photoUploadListener = PhotoUploadListenerUtil.createCatchAllListener(LYSPhotoManagerFragment$$Lambda$1.lambdaFactory$(this));
    PhotoUploadManager photoUploadManager;
    @BindView
    RecyclerView recyclerView;
    private MenuItem saveAndExitMenuItem;
    @BindView
    AirButton saveRearrangingButton;
    @BindView
    AirButton skipButton;
    private MenuItem startReorderMenuItem;
    @BindView
    AirToolbar toolbar;
    final RequestListener<SimpleListingResponse> updatePhotoOrderListener = new C0699RL().onResponse(LYSPhotoManagerFragment$$Lambda$2.lambdaFactory$(this)).onError(LYSPhotoManagerFragment$$Lambda$3.lambdaFactory$(this)).build();
    @State
    ArrayList<Photo> uploadingPhotos;

    public static LYSPhotoManagerFragment newInstance() {
        return new LYSPhotoManagerFragment();
    }

    static /* synthetic */ void lambda$new$0(LYSPhotoManagerFragment lYSPhotoManagerFragment) {
        lYSPhotoManagerFragment.updateMenuViewState();
        lYSPhotoManagerFragment.updateNextButton();
    }

    static /* synthetic */ void lambda$new$1(LYSPhotoManagerFragment lYSPhotoManagerFragment, SimpleListingResponse response) {
        lYSPhotoManagerFragment.controller.setListing(response.listing);
        lYSPhotoManagerFragment.setMode(Mode.NonRearranging);
    }

    static /* synthetic */ void lambda$new$2(LYSPhotoManagerFragment lYSPhotoManagerFragment, AirRequestNetworkException e) {
        NetworkUtil.tryShowErrorWithSnackbar(lYSPhotoManagerFragment.getView(), e);
        lYSPhotoManagerFragment.setMode(Mode.Rearranging);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ListYourSpaceDLSGraph) CoreApplication.instance(getContext()).component()).inject(this);
        if (savedInstanceState == null) {
            this.mode = Mode.NonRearranging;
            this.uploadingPhotos = new ArrayList<>();
        }
        this.photoUploadManager.addListenerForPhotoUploadTarget(this.controller.getListing().getId(), PhotoUploadTarget.ListingPhoto, this.photoUploadListener);
        this.adapter = new PhotoManagerAdapter(getContext(), this.controller.getListing(), this.photoUploadManager, LYSPhotoManagerFragment$$Lambda$4.lambdaFactory$(this));
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        ((ListYourSpaceDLSGraph) CoreApplication.instance(getContext()).component()).inject(this);
        View view = inflater.inflate(C7251R.layout.lys_dls_photo_manager, parent, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        this.photoController = PhotoRearranger.init(this.recyclerView, this.adapter, this.mode);
        setMode(this.mode);
        updateNextButton();
        return view;
    }

    public void onDestroy() {
        this.photoUploadManager.removeListenerForPhotoUploadTarget(this.controller.getListing().getId(), PhotoUploadTarget.ListingPhoto, this.photoUploadListener);
        this.adapter.destroy();
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onSaveActionPressed() {
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(C7251R.C7254menu.lys_photo_manager, menu);
        this.startReorderMenuItem = menu.findItem(C7251R.C7253id.change_order);
        this.addPhotosMenuItem = menu.findItem(C7251R.C7253id.add_photos);
        this.saveAndExitMenuItem = menu.findItem(C7251R.C7253id.save_and_exit);
        menu.findItem(C7251R.C7253id.done).setVisible(false);
        updateMenuViewState();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item == this.startReorderMenuItem) {
            setMode(Mode.Rearranging);
            return true;
        } else if (item == this.addPhotosMenuItem) {
            startActivityForResult(ListingPhotoPickerUtil.createPickerIntent(getContext()), 12);
            return true;
        } else if (item != this.saveAndExitMenuItem) {
            return super.onOptionsItemSelected(item);
        } else {
            this.userAction = UserAction.SaveAndExit;
            navigateInFlow(LYSStep.PhotoManager);
            return true;
        }
    }

    public void dataUpdated() {
        super.dataUpdated();
        updateAdapterPhotos();
    }

    @OnClick
    public void onClickDone() {
        this.jitneyLogger.logReorderPhotos(Long.valueOf(this.controller.getListing().getId()));
        setMode(Mode.RearrangingLocked);
        UpdateListingRequest.forPhotoSortOrderLYS(this.controller.getListing().getId(), this.adapter.getPhotoOrder(), this.controller.getMaxReachedStep().stepId).withListener((Observer) this.updatePhotoOrderListener).execute(this.requestManager);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 12 && resultCode == -1) {
            startPhotoUpload(data.getStringExtra(PhotoPicker.EXTRA_PHOTO_PATH));
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void startPhotoUpload(String path) {
        this.photoUploadManager.uploadImage(ListingPhotosUtil.createPhotoUpload(getContext(), this.controller.getListing(), path));
        updateAdapterPhotos();
        maybeScrollToBottom();
    }

    private void updateNextButton() {
        boolean hasUploadedPhotos;
        boolean hasUploadingPhotos;
        boolean hasPhotos;
        boolean z = true;
        if (!this.controller.getListing().getPhotos().isEmpty()) {
            hasUploadedPhotos = true;
        } else {
            hasUploadedPhotos = false;
        }
        if (!this.photoUploadManager.getPendingUploadQueue().isEmpty()) {
            hasUploadingPhotos = true;
        } else {
            hasUploadingPhotos = false;
        }
        if (hasUploadedPhotos || hasUploadingPhotos) {
            hasPhotos = true;
        } else {
            hasPhotos = false;
        }
        ViewUtils.setVisibleIf((View) this.nextButton, hasPhotos);
        AirButton airButton = this.skipButton;
        if (hasPhotos) {
            z = false;
        }
        ViewUtils.setVisibleIf((View) airButton, z);
    }

    /* access modifiers changed from: protected */
    public boolean onBackPressed() {
        switch (this.photoController.getMode()) {
            case Rearranging:
                setMode(Mode.NonRearranging);
                updateAdapterPhotos();
                return true;
            case RearrangingLocked:
                return true;
            default:
                return super.onBackPressed();
        }
    }

    private void setMode(Mode mode2) {
        boolean z;
        boolean z2;
        boolean z3 = true;
        this.mode = mode2;
        this.photoController.setMode(mode2);
        if (getView() != null) {
            getView().post(LYSPhotoManagerFragment$$Lambda$5.lambdaFactory$(this));
        }
        this.saveRearrangingButton.setState(mode2 == Mode.RearrangingLocked ? AirButton.State.Loading : AirButton.State.Normal);
        AirButton airButton = this.nextButton;
        if (mode2 == Mode.NonRearranging) {
            z = true;
        } else {
            z = false;
        }
        animateButtonVisibility(airButton, z);
        AirButton airButton2 = this.previewButton;
        if (mode2 == Mode.NonRearranging) {
            z2 = true;
        } else {
            z2 = false;
        }
        animateButtonVisibility(airButton2, z2);
        AirButton airButton3 = this.saveRearrangingButton;
        if (mode2 == Mode.NonRearranging) {
            z3 = false;
        }
        animateButtonVisibility(airButton3, z3);
        this.toolbar.setNavigationIcon(getNavigationIconForMode(mode2));
    }

    /* access modifiers changed from: private */
    public void updateMenuViewState() {
        boolean z;
        boolean z2 = true;
        if (this.saveAndExitMenuItem != null) {
            this.saveAndExitMenuItem.setVisible(this.mode == Mode.NonRearranging);
        }
        if (this.startReorderMenuItem != null) {
            MenuItem menuItem = this.startReorderMenuItem;
            if (this.mode != Mode.NonRearranging || this.controller.getListing().getPhotos().size() <= 1) {
                z = false;
            } else {
                z = true;
            }
            menuItem.setVisible(z);
        }
        if (this.addPhotosMenuItem != null) {
            MenuItem menuItem2 = this.addPhotosMenuItem;
            if (this.mode != Mode.NonRearranging) {
                z2 = false;
            }
            menuItem2.setVisible(z2);
        }
    }

    private void updateAdapterPhotos() {
        this.adapter.updatePhotos(this.controller.getListing());
    }

    /* access modifiers changed from: private */
    public void photoSelected(long photoId) {
        if (this.photoController.getMode() == Mode.NonRearranging) {
            this.controller.showPhotoDetail(photoId);
        }
    }

    private void maybeScrollToBottom() {
        if (this.recyclerView != null) {
            this.recyclerView.smoothScrollToPosition(this.adapter.getItemCount() - 1);
        }
    }

    private static int getNavigationIconForMode(Mode mode2) {
        switch (mode2) {
            case Rearranging:
                return 2;
            case RearrangingLocked:
                return 0;
            default:
                return 1;
        }
    }

    @OnClick
    public void onClickNext() {
        this.userAction = UserAction.GoToNext;
        navigateInFlow(LYSStep.PhotoManager);
    }

    @OnClick
    public void onClickPreview() {
        this.jitneyLogger.logPreviewPhotos(Long.valueOf(this.controller.getListing().getId()));
        this.userAction = UserAction.Preview;
        navigateInFlow(LYSStep.PhotoManager);
    }

    @OnClick
    public void onClickSkip() {
        this.userAction = UserAction.GoToNext;
        navigateInFlow(LYSStep.PhotoManager);
    }

    private static void animateButtonVisibility(AirButton button, boolean show) {
        if ((button.getVisibility() == 0) != show) {
            button.animate().alpha(show ? 1.0f : 0.0f).setDuration(150).withStartAction(LYSPhotoManagerFragment$$Lambda$6.lambdaFactory$(button)).withEndAction(LYSPhotoManagerFragment$$Lambda$7.lambdaFactory$(button, show)).start();
        }
    }

    static /* synthetic */ void lambda$animateButtonVisibility$4(AirButton button, boolean show) {
        button.setVisibility(show ? 0 : 8);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.LYSPhotos;
    }
}
