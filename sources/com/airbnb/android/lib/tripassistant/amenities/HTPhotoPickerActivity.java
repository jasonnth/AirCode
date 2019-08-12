package com.airbnb.android.lib.tripassistant.amenities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.models.Attachment;
import com.airbnb.android.core.models.HelpThreadIssue;
import com.airbnb.android.core.responses.AttachmentResponse;
import com.airbnb.android.core.utils.AirPhotoPicker;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.LayoutManagerUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.ToolbarSpacerEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.tripassistant.HelpThreadPhoto;
import com.airbnb.android.photopicker.PhotoPicker;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.SimpleEpoxyModel;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import icepick.State;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p032rx.Observer;

public class HTPhotoPickerActivity extends AirActivity {
    private static final String EXTRA_EXISTING_PHOTOS = "extra_existing_photos";
    public static final String EXTRA_ISSUE = "extra_issue";
    public static final String EXTRA_SELECTED_PHOTOS = "extra_selected_photos";
    private static final int GRID_SPAN_COUNT = 2;
    private static final int REQUEST_CODE_CONFIRM_CANCEL = 8691;
    private static final int REQUEST_CODE_PICK_PHOTO = 8690;
    /* access modifiers changed from: private */
    public PhotoAdapter adapter;
    final RequestListener<AttachmentResponse> deletePhotoListener = new C0699RL().onError(HTPhotoPickerActivity$$Lambda$1.lambdaFactory$(this)).build();
    @State
    ArrayList<HelpThreadPhoto> deletedPhotos = new ArrayList<>();
    private HelpThreadIssue issue;
    @State
    ArrayList<HelpThreadPhoto> photos = new ArrayList<>();
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirButton saveButton;
    @BindView
    AirToolbar toolbar;
    final RequestListener<AttachmentResponse> uploadPhotoListener = new RequestListener<AttachmentResponse>() {
        public void onResponse(AttachmentResponse data) {
            HTUploadPhotoRequest request = (HTUploadPhotoRequest) data.metadata.request();
            for (int i = 0; i < HTPhotoPickerActivity.this.photos.size(); i++) {
                HelpThreadPhoto photo = (HelpThreadPhoto) HTPhotoPickerActivity.this.photos.get(i);
                if (photo.hasPath(request.getPhotoPath())) {
                    HTPhotoPickerActivity.this.photos.set(i, photo.toBuilder().attachment(data.attachment).build());
                    return;
                }
            }
            BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Help Thread Photo: Did not find local file match"));
        }

        public void onErrorResponse(AirRequestNetworkException e) {
            HTUploadPhotoRequest request = (HTUploadPhotoRequest) e.request();
            Iterator it = HTPhotoPickerActivity.this.photos.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                HelpThreadPhoto photo = (HelpThreadPhoto) it.next();
                if (photo.hasPath(request.getPhotoPath())) {
                    HTPhotoPickerActivity.this.adapter.removePhoto(photo);
                    break;
                }
            }
            new SnackbarWrapper().view(HTPhotoPickerActivity.this.recyclerView).title(C0880R.string.help_thread_photo_upload_failed_error_title, true).body(C0880R.string.help_thread_photo_upload_failed_error_body).duration(0).buildAndShow();
        }

        public void onRequestCompleted(boolean successful) {
            HTPhotoPickerActivity.this.adapter.rebuildModels();
        }
    };

    private class PhotoAdapter extends AirEpoxyAdapter {
        private final List<EpoxyModel<?>> headerModels = ImmutableList.m1287of(new ToolbarSpacerEpoxyModel_(), new SimpleEpoxyModel(C0880R.layout.view_model_help_thread_photo_picker_title).span(2), new SimpleEpoxyModel(C0880R.layout.view_help_thread_add_photo).onClick(HTPhotoPickerActivity$PhotoAdapter$$Lambda$1.lambdaFactory$(this)));

        public PhotoAdapter() {
            enableDiffing();
            rebuildModels();
        }

        public void rebuildModels() {
            this.models.clear();
            this.models.addAll(this.headerModels);
            Iterator it = HTPhotoPickerActivity.this.photos.iterator();
            while (it.hasNext()) {
                this.models.add(buildPhotoModel((HelpThreadPhoto) it.next()));
            }
            notifyModelsChanged();
            HTPhotoPickerActivity.this.updatePrimaryButtonState();
        }

        private EpoxyModel<?> buildPhotoModel(HelpThreadPhoto photo) {
            return new PhotoModel_().photo(photo).showLoader(HTPhotoPickerActivity.this.isPhotoUploading(photo)).deleteListener(HTPhotoPickerActivity$PhotoAdapter$$Lambda$2.lambdaFactory$(this, photo)).m6083id((long) photo.hashCode());
        }

        public void addPhoto(HelpThreadPhoto photo) {
            HTPhotoPickerActivity.this.photos.add(photo);
            addModels((EpoxyModel<?>[]) new EpoxyModel[]{buildPhotoModel(photo)});
            HTPhotoPickerActivity.this.updatePrimaryButtonState();
        }

        public void removePhoto(HelpThreadPhoto photo) {
            HTPhotoPickerActivity.this.photos.remove(photo);
            Iterator it = this.models.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                EpoxyModel<?> model = (EpoxyModel) it.next();
                if ((model instanceof PhotoModel) && ((PhotoModel) model).hasPhoto(photo)) {
                    removeModel(model);
                    break;
                }
            }
            HTPhotoPickerActivity.this.updatePrimaryButtonState();
        }
    }

    public static Intent newInstance(Context context, HelpThreadIssue issue2, ArrayList<Attachment> existingPhotos) {
        return new Intent(context, HTPhotoPickerActivity.class).putExtra(EXTRA_ISSUE, issue2).putParcelableArrayListExtra(EXTRA_EXISTING_PHOTOS, existingPhotos);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.fragment_help_thread_photo_picker);
        ButterKnife.bind((Activity) this);
        setToolbar(this.toolbar);
        Intent intent = getIntent();
        this.issue = (HelpThreadIssue) intent.getParcelableExtra(EXTRA_ISSUE);
        setUpRecyclerView();
        if (savedInstanceState == null) {
            addInitialPhotos(intent);
            launchAddPhotoFlow();
        } else {
            this.adapter.rebuildModels();
        }
        overridePendingTransition(C0880R.anim.enter_bottom, C0880R.anim.stay);
    }

    private void setUpRecyclerView() {
        this.adapter = new PhotoAdapter();
        LayoutManagerUtils.setGridLayout((AirEpoxyAdapter) this.adapter, this.recyclerView, 2);
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setHasFixedSize(true);
    }

    private void addInitialPhotos(Intent intent) {
        Iterator it = intent.getParcelableArrayListExtra(EXTRA_EXISTING_PHOTOS).iterator();
        while (it.hasNext()) {
            this.adapter.addPhoto(HelpThreadPhoto.create((Attachment) it.next()));
        }
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onSubmitClicked() {
        finishAndReturnPhotos(true);
    }

    private void finishAndReturnPhotos(boolean submitted) {
        setResult(submitted ? -1 : 0, new Intent().putParcelableArrayListExtra(EXTRA_SELECTED_PHOTOS, new ArrayList<>(FluentIterable.from((Iterable<E>) this.photos).filter(HTPhotoPickerActivity$$Lambda$2.lambdaFactory$()).transform(HTPhotoPickerActivity$$Lambda$3.lambdaFactory$()).toList())).putExtra(EXTRA_ISSUE, this.issue));
        finish();
    }

    public void finish() {
        super.finish();
        overridePendingTransition(C0880R.anim.stay, C0880R.anim.exit_bottom);
    }

    /* access modifiers changed from: protected */
    public boolean hasCustomEnterTransition() {
        return true;
    }

    public void onBackPressed() {
        if (!this.photos.isEmpty()) {
            showCancelationConfirmDialog();
        } else {
            finishAndReturnPhotos(false);
        }
    }

    /* access modifiers changed from: protected */
    public void onHomeActionPressed() {
        onBackPressed();
    }

    private void showCancelationConfirmDialog() {
        ZenDialog.builder().withBodyText(C0880R.string.help_thread_photo_confirm_cancel_dialog_body).withDualButton(C0880R.string.f1211no, 0, C0880R.string.yes, REQUEST_CODE_CONFIRM_CANCEL).create().show(getSupportFragmentManager(), (String) null);
    }

    /* access modifiers changed from: private */
    public void updatePrimaryButtonState() {
        this.saveButton.setEnabled(!this.photos.isEmpty() && !isUploadingPhotos());
    }

    /* access modifiers changed from: private */
    public void launchAddPhotoFlow() {
        startActivityForResult(AirPhotoPicker.builder().setSource(0).targetOutputDimensions(2048, 2048).create(this), REQUEST_CODE_PICK_PHOTO);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_PICK_PHOTO /*8690*/:
                handlePhotoPickerResult(resultCode, data);
                return;
            case REQUEST_CODE_CONFIRM_CANCEL /*8691*/:
                finishAndReturnPhotos(false);
                return;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                return;
        }
    }

    private void handlePhotoPickerResult(int resultCode, Intent data) {
        if (resultCode == -1) {
            String photoPath = data.getStringExtra(PhotoPicker.EXTRA_PHOTO_PATH);
            HelpThreadPhoto photo = HelpThreadPhoto.create(photoPath);
            this.requestManager.executeWithTag(new HTUploadPhotoRequest(this.issue, photoPath).withListener((Observer) this.uploadPhotoListener), getUploadTag(photo));
            this.adapter.addPhoto(photo);
        }
    }

    private static String getUploadTag(HelpThreadPhoto photo) {
        Check.notNull(photo.localPath(), "Can't upload a photo that doesn't have a path");
        return "upload" + photo.localPath();
    }

    private boolean isUploadingPhotos() {
        return FluentIterable.from((Iterable<E>) this.photos).anyMatch(HTPhotoPickerActivity$$Lambda$4.lambdaFactory$(this));
    }

    /* access modifiers changed from: private */
    public boolean isPhotoUploading(HelpThreadPhoto photo) {
        return photo.hasPath() && this.requestManager.hasRequest((BaseRequestListener<?>) this.uploadPhotoListener, getUploadTag(photo));
    }

    /* access modifiers changed from: private */
    public void deletePhoto(HelpThreadPhoto photo) {
        this.deletedPhotos.add(photo);
        this.requestManager.executeWithTag(new HTDeletePhotoRequest(photo).withListener((Observer) this.deletePhotoListener), getDeleteTag(photo));
        this.adapter.removePhoto(photo);
    }

    static /* synthetic */ void lambda$new$0(HTPhotoPickerActivity hTPhotoPickerActivity, AirRequestNetworkException e) {
        HelpThreadPhoto photo = ((HTDeletePhotoRequest) e.request()).getPhoto();
        hTPhotoPickerActivity.deletedPhotos.remove(photo);
        hTPhotoPickerActivity.adapter.addPhoto(photo);
        NetworkUtil.tryShowErrorWithSnackbar(hTPhotoPickerActivity.recyclerView, e);
    }

    private static String getDeleteTag(HelpThreadPhoto photo) {
        Check.notNull(photo.attachment(), "Can't delete a photo that hasn't been uploaded yet");
        return "delete" + photo.attachment().getId();
    }
}
