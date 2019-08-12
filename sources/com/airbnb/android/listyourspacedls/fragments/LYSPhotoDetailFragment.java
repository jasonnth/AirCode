package com.airbnb.android.listyourspacedls.fragments;

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
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.Photo;
import com.airbnb.android.core.requests.DeleteListingPhotoRequest;
import com.airbnb.android.core.requests.UpdateListingPhotoRequest;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.listing.adapters.PhotoAdapter;
import com.airbnb.android.listing.utils.ListingPhotosUtil;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.android.listyourspacedls.LYSJitneyLogger;
import com.airbnb.android.listyourspacedls.ListYourSpaceDLSGraph;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import com.google.common.collect.FluentIterable;
import java.util.List;
import p032rx.Observer;

public class LYSPhotoDetailFragment extends LYSBaseFragment {
    private static final String PARAM_PHOTO_ID = "photo_id";
    private PhotoAdapter adapter;
    final RequestListener<BaseResponse> deleteListingPhotoListener = new C0699RL().onResponse(LYSPhotoDetailFragment$$Lambda$3.lambdaFactory$(this)).onError(LYSPhotoDetailFragment$$Lambda$4.lambdaFactory$(this)).build();
    LYSJitneyLogger jitneyLogger;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirButton saveButton;
    @BindView
    AirToolbar toolbar;
    final RequestListener<BaseResponse> updateListingPhotoListener = new C0699RL().onResponse(LYSPhotoDetailFragment$$Lambda$1.lambdaFactory$(this)).onError(LYSPhotoDetailFragment$$Lambda$2.lambdaFactory$(this)).build();

    public static LYSPhotoDetailFragment create(long photoId) {
        return (LYSPhotoDetailFragment) ((FragmentBundleBuilder) FragmentBundler.make(new LYSPhotoDetailFragment()).putLong(PARAM_PHOTO_ID, photoId)).build();
    }

    static /* synthetic */ void lambda$new$0(LYSPhotoDetailFragment lYSPhotoDetailFragment, BaseResponse response) {
        UpdateListingPhotoRequest request = (UpdateListingPhotoRequest) response.metadata.request();
        Photo photo = lYSPhotoDetailFragment.getPhoto();
        photo.setCaption(request.caption);
        lYSPhotoDetailFragment.jitneyLogger.logUpdatePhotoCaption(Long.valueOf(lYSPhotoDetailFragment.controller.getListing().getId()));
        if (request.makeCoverPhoto) {
            ListingPhotosUtil.moveToStart(lYSPhotoDetailFragment.controller.getListing(), photo);
        }
        lYSPhotoDetailFragment.handleSuccess();
    }

    static /* synthetic */ void lambda$new$1(LYSPhotoDetailFragment lYSPhotoDetailFragment, BaseResponse response) {
        ListingPhotosUtil.remove(lYSPhotoDetailFragment.controller.getListing(), lYSPhotoDetailFragment.getPhoto());
        lYSPhotoDetailFragment.handleSuccess();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.showSaveAndExit = false;
        this.adapter = new PhotoAdapter(getPhoto(), savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((ListYourSpaceDLSGraph) CoreApplication.instance(getContext()).component()).inject(this);
        View view = inflater.inflate(C7251R.layout.fragment_listing_recycler_view_with_save, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.toolbar.setTitle((CharSequence) calculateTitle());
        this.toolbar.setNavigationIcon(2);
        this.recyclerView.setAdapter(this.adapter);
        setHasOptionsMenu(true);
        showTip(C7251R.string.lys_dls_photo_caption_tip, LYSPhotoDetailFragment$$Lambda$5.lambdaFactory$(this));
        return view;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(C7251R.C7254menu.listing_manage_photo, menu);
        menu.findItem(C7251R.C7253id.make_cover_photo).setVisible(!this.adapter.isCoverPhoto());
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == C7251R.C7253id.delete_photo) {
            this.jitneyLogger.logDeletePhoto(Long.valueOf(this.controller.getListing().getId()));
            deletePhoto();
            return true;
        } else if (itemId != C7251R.C7253id.make_cover_photo) {
            return super.onOptionsItemSelected(item);
        } else {
            this.jitneyLogger.logSetToCoverPhoto(Long.valueOf(this.controller.getListing().getId()));
            this.adapter.setCoverPhoto();
            if (getView() == null) {
                return true;
            }
            getView().post(LYSPhotoDetailFragment$$Lambda$6.lambdaFactory$(item));
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
    public void onSaveActionPressed() {
        makePhotoRequestIfChanged();
    }

    /* access modifiers changed from: protected */
    @OnClick
    public void saveClicked() {
        makePhotoRequestIfChanged();
    }

    /* access modifiers changed from: private */
    public void showTipDialog() {
        this.controller.showTipModal(C7251R.string.lys_dls_photo_caption_tip_title, C7251R.string.lys_dls_photo_caption_tip_text, NavigationTag.LYSPhotoDetailsTip);
    }

    private void makePhotoRequestIfChanged() {
        if (!canSaveChanges()) {
            this.saveButton.setState(State.Success);
            getFragmentManager().popBackStack();
            return;
        }
        this.saveButton.setState(State.Loading);
        this.adapter.setEnabled(false);
        UpdateListingPhotoRequest.forCaption(this.controller.getListing().getId(), getPhoto().getId(), this.adapter.getCaption(), this.adapter.getMakeCoverPhoto(getPhoto())).withListener((Observer) this.updateListingPhotoListener).execute(this.requestManager);
    }

    private void deletePhoto() {
        this.adapter.setEnabled(false);
        new DeleteListingPhotoRequest(this.controller.getListing().getId(), getPhoto().getId()).withListener((Observer) this.deleteListingPhotoListener).execute(this.requestManager);
    }

    private String calculateTitle() {
        List<Photo> photos = this.controller.getListing().getSortedPhotos();
        int photoPosition = photos.indexOf(getPhoto()) + 1;
        return getContext().getString(C7251R.string.manage_listing_photo_title, new Object[]{Integer.valueOf(photoPosition), Integer.valueOf(photos.size())});
    }

    private Photo getPhoto() {
        return (Photo) FluentIterable.from((Iterable<E>) this.controller.getListing().getPhotos()).firstMatch(LYSPhotoDetailFragment$$Lambda$7.lambdaFactory$(getArguments().getLong(PARAM_PHOTO_ID))).orNull();
    }

    static /* synthetic */ boolean lambda$getPhoto$4(long photoId, Photo photo) {
        return photo.getId() == photoId;
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

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.LYSPhotoDetails;
    }
}
