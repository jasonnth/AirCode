package com.airbnb.android.managelisting.settings.photos;

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
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.models.Photo;
import com.airbnb.android.core.requests.DeleteListingPhotoRequest;
import com.airbnb.android.core.requests.UpdateListingPhotoRequest;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.listing.adapters.PhotoAdapter;
import com.airbnb.android.listing.utils.ListingPhotosUtil;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.managelisting.settings.ManageListingBaseFragment;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import com.google.common.collect.FluentIterable;
import java.util.List;
import p032rx.Observer;

public class PhotoFragment extends ManageListingBaseFragment {
    private static final String PARAM_PHOTO_ID = "photo_id";
    private PhotoAdapter adapter;
    final RequestListener<BaseResponse> deleteListingPhotoListener = new C0699RL().onResponse(PhotoFragment$$Lambda$3.lambdaFactory$(this)).onError(PhotoFragment$$Lambda$4.lambdaFactory$(this)).build();
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirButton saveButton;
    @BindView
    AirToolbar toolbar;
    final RequestListener<BaseResponse> updateListingPhotoListener = new C0699RL().onResponse(PhotoFragment$$Lambda$1.lambdaFactory$(this)).onError(PhotoFragment$$Lambda$2.lambdaFactory$(this)).build();

    public static PhotoFragment create(long photoId) {
        return (PhotoFragment) ((FragmentBundleBuilder) FragmentBundler.make(new PhotoFragment()).putLong(PARAM_PHOTO_ID, photoId)).build();
    }

    static /* synthetic */ void lambda$new$0(PhotoFragment photoFragment, BaseResponse response) {
        UpdateListingPhotoRequest request = (UpdateListingPhotoRequest) response.metadata.request();
        Photo photo = photoFragment.getPhoto();
        photo.setCaption(request.caption);
        if (request.makeCoverPhoto) {
            ListingPhotosUtil.moveToStart(photoFragment.controller.getListing(), photo);
        }
        photoFragment.handleSuccess();
    }

    static /* synthetic */ void lambda$new$1(PhotoFragment photoFragment, BaseResponse response) {
        ListingPhotosUtil.remove(photoFragment.controller.getListing(), photoFragment.getPhoto());
        photoFragment.handleSuccess();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.adapter = new PhotoAdapter(getPhoto(), savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_listing_recycler_view_with_save, container, false);
        bindViews(view);
        this.toolbar.setTitle((CharSequence) calculateTitle());
        this.toolbar.setNavigationIcon(2);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.adapter);
        setHasOptionsMenu(true);
        return view;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(C7368R.C7371menu.listing_manage_photo, menu);
        menu.findItem(C7368R.C7370id.make_cover_photo).setVisible(!this.adapter.isCoverPhoto());
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == C7368R.C7370id.delete_photo) {
            deletePhoto();
            return true;
        } else if (itemId != C7368R.C7370id.make_cover_photo) {
            return super.onOptionsItemSelected(item);
        } else {
            this.adapter.setCoverPhoto();
            if (getView() == null) {
                return true;
            }
            getView().post(PhotoFragment$$Lambda$5.lambdaFactory$(item));
            return true;
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.adapter.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return this.adapter.hasChanged(getPhoto());
    }

    /* access modifiers changed from: protected */
    @OnClick
    public void saveClicked() {
        if (!canSaveChanges()) {
            this.saveButton.setState(State.Success);
            getFragmentManager().popBackStack();
            return;
        }
        setLoading();
        UpdateListingPhotoRequest.forCaption(this.controller.getListing().getId(), getPhoto().getId(), this.adapter.getCaption(), this.adapter.getMakeCoverPhoto(getPhoto())).withListener((Observer) this.updateListingPhotoListener).execute(this.requestManager);
    }

    private void deletePhoto() {
        setLoading();
        new DeleteListingPhotoRequest(this.controller.getListing().getId(), getPhoto().getId()).withListener((Observer) this.deleteListingPhotoListener).execute(this.requestManager);
    }

    private String calculateTitle() {
        List<Photo> photos = this.controller.getListing().getSortedPhotos();
        int photoPosition = photos.indexOf(getPhoto()) + 1;
        return getContext().getString(C7368R.string.manage_listing_photo_title, new Object[]{Integer.valueOf(photoPosition), Integer.valueOf(photos.size())});
    }

    private Photo getPhoto() {
        return (Photo) FluentIterable.from((Iterable<E>) this.controller.getListing().getPhotos()).firstMatch(PhotoFragment$$Lambda$6.lambdaFactory$(getArguments().getLong(PARAM_PHOTO_ID))).orNull();
    }

    static /* synthetic */ boolean lambda$getPhoto$3(long photoId, Photo photo) {
        return photo.getId() == photoId;
    }

    private void setLoading() {
        this.saveButton.setState(State.Loading);
        this.adapter.setEnabled(false);
    }

    private void handleSuccess() {
        this.saveButton.setState(State.Success);
        this.controller.setListing(this.controller.getListing());
        getFragmentManager().popBackStack();
    }

    /* access modifiers changed from: private */
    public void handleError(NetworkException e) {
        this.adapter.setEnabled(true);
        this.saveButton.setState(State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(getView(), e);
    }
}
