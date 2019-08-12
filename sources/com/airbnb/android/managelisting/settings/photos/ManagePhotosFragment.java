package com.airbnb.android.managelisting.settings.photos;

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
import com.airbnb.android.core.models.Photo;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.listing.adapters.PhotoManagerAdapter;
import com.airbnb.android.listing.utils.ListingPhotoPickerUtil;
import com.airbnb.android.listing.utils.ListingPhotosUtil;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.managelisting.ManageListingGraph;
import com.airbnb.android.managelisting.settings.ManageListingBaseFragment;
import com.airbnb.android.photopicker.PhotoPicker;
import com.airbnb.android.photouploadmanager.PhotoUploadManager;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.photorearranger.PhotoRearranger;
import com.airbnb.p027n2.components.photorearranger.PhotoRearrangerController;
import com.airbnb.p027n2.components.photorearranger.PhotoRearrangerController.Mode;
import com.airbnb.p027n2.primitives.AirButton;
import icepick.State;
import java.util.ArrayList;
import p032rx.Observer;

public class ManagePhotosFragment extends ManageListingBaseFragment {
    private static final int REQUEST_CODE_SELECT_PICTURE = 12;
    private PhotoManagerAdapter adapter;
    private MenuItem addPhotosMenuItem;
    @State
    Mode mode;
    private PhotoRearrangerController photoController;
    PhotoUploadManager photoUploadManager;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirButton saveButton;
    private MenuItem startReorderMenuItem;
    @BindView
    AirToolbar toolbar;
    final RequestListener<SimpleListingResponse> updatePhotoOrderListener = new C0699RL().onResponse(ManagePhotosFragment$$Lambda$1.lambdaFactory$(this)).onError(ManagePhotosFragment$$Lambda$2.lambdaFactory$(this)).build();
    @State
    ArrayList<Photo> uploadingPhotos;

    public static ManagePhotosFragment create() {
        return new ManagePhotosFragment();
    }

    static /* synthetic */ void lambda$new$0(ManagePhotosFragment managePhotosFragment, SimpleListingResponse response) {
        managePhotosFragment.controller.setListing(response.listing);
        managePhotosFragment.setMode(Mode.NonRearranging);
    }

    static /* synthetic */ void lambda$new$1(ManagePhotosFragment managePhotosFragment, AirRequestNetworkException e) {
        NetworkUtil.tryShowErrorWithSnackbar(managePhotosFragment.getView(), e);
        managePhotosFragment.setMode(Mode.Rearranging);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ManageListingGraph) CoreApplication.instance(getContext()).component()).inject(this);
        if (savedInstanceState == null) {
            this.mode = Mode.NonRearranging;
            this.uploadingPhotos = new ArrayList<>();
        }
        this.adapter = new PhotoManagerAdapter(getContext(), this.controller.getListing(), this.photoUploadManager, ManagePhotosFragment$$Lambda$3.lambdaFactory$(this));
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_manage_photos, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        this.photoController = PhotoRearranger.init(this.recyclerView, this.adapter, this.mode);
        setMode(this.mode);
        return view;
    }

    public void onDestroy() {
        super.onDestroy();
        this.adapter.destroy();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(C7368R.C7371menu.photo_manager, menu);
        this.startReorderMenuItem = menu.findItem(C7368R.C7370id.change_order);
        this.addPhotosMenuItem = menu.findItem(C7368R.C7370id.add_photos);
        updateMenuViewState();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item == this.startReorderMenuItem) {
            setMode(Mode.Rearranging);
            return true;
        } else if (item != this.addPhotosMenuItem) {
            return super.onOptionsItemSelected(item);
        } else {
            startActivityForResult(ListingPhotoPickerUtil.createPickerIntent(getContext()), 12);
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }

    public void dataUpdated() {
        super.dataUpdated();
        updateAdapterPhotos();
        updateMenuViewState();
    }

    @OnClick
    public void saveButtonClicked() {
        setMode(Mode.RearrangingLocked);
        UpdateListingRequest.forPhotoSortOrder(this.controller.getListing().getId(), this.adapter.getPhotoOrder()).withListener((Observer) this.updatePhotoOrderListener).execute(this.requestManager);
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
        this.mode = mode2;
        this.photoController.setMode(mode2);
        if (getView() != null) {
            getView().post(ManagePhotosFragment$$Lambda$4.lambdaFactory$(this));
        }
        this.saveButton.setState(mode2 == Mode.RearrangingLocked ? AirButton.State.Loading : AirButton.State.Normal);
        this.saveButton.setVisibility(mode2 == Mode.NonRearranging ? 8 : 0);
        this.toolbar.setNavigationIcon(getNavigationIconForMode(mode2));
    }

    /* access modifiers changed from: private */
    public void updateMenuViewState() {
        boolean z = true;
        if (this.startReorderMenuItem != null) {
            this.startReorderMenuItem.setVisible(this.mode == Mode.NonRearranging && this.controller.getListing().getPhotos().size() > 1);
        }
        if (this.addPhotosMenuItem != null) {
            MenuItem menuItem = this.addPhotosMenuItem;
            if (this.mode != Mode.NonRearranging) {
                z = false;
            }
            menuItem.setVisible(z);
        }
    }

    private void updateAdapterPhotos() {
        this.adapter.updatePhotos(this.controller.getListing());
    }

    /* access modifiers changed from: private */
    public void photoSelected(long photoId) {
        if (this.photoController.getMode() == Mode.NonRearranging) {
            this.controller.actionExecutor.photo(photoId);
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
}
