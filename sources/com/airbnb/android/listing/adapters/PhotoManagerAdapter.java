package com.airbnb.android.listing.adapters;

import android.content.Context;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Photo;
import com.airbnb.android.core.requests.photos.PhotoUploadTarget;
import com.airbnb.android.listing.C7213R;
import com.airbnb.android.listing.utils.PhotoMenuUtil;
import com.airbnb.android.photouploadmanager.PhotoUploadListener;
import com.airbnb.android.photouploadmanager.PhotoUploadListenerUtil;
import com.airbnb.android.photouploadmanager.PhotoUploadManager;
import com.airbnb.android.photouploadmanager.PhotoUploadTransaction;
import com.airbnb.p027n2.components.photorearranger.PhotoRearrangerAdapter;
import com.airbnb.p027n2.components.photorearranger.PhotoRearrangerItem;
import com.airbnb.p027n2.components.photorearranger.RearrangablePhotoRow.State;
import com.airbnb.p027n2.primitives.imaging.Image;
import com.airbnb.p027n2.primitives.imaging.SimpleImage;
import com.google.common.collect.FluentIterable;
import java.util.List;

public class PhotoManagerAdapter extends PhotoRearrangerAdapter<PhotoRearrangerItem> {
    private final Context context;
    private final Listener listener;
    private final long listingId;
    private List<Photo> orderedListingPhotos;
    private final PhotoUploadManager photoUploadManager;
    private final PhotoUploadListener uploadListener = PhotoUploadListenerUtil.createCatchAllListener(PhotoManagerAdapter$$Lambda$1.lambdaFactory$(this));

    public interface Listener {
        void photoDetailsRequested(long j);
    }

    public PhotoManagerAdapter(Context context2, Listing listing, PhotoUploadManager photoUploadManager2, Listener listener2) {
        this.context = context2;
        this.photoUploadManager = photoUploadManager2;
        this.listener = listener2;
        this.listingId = listing.getId();
        updatePhotos(listing);
        photoUploadManager2.addListenerForPhotoUploadTarget(this.listingId, PhotoUploadTarget.ListingPhoto, this.uploadListener);
        setListener(PhotoManagerAdapter$$Lambda$2.lambdaFactory$(this));
    }

    public void destroy() {
        this.photoUploadManager.removeListenerForPhotoUploadTarget(this.listingId, PhotoUploadTarget.ListingPhoto, this.uploadListener);
    }

    public List<Long> getPhotoOrder() {
        return FluentIterable.from((Iterable<E>) getModels()).filter(PhotoManagerAdapter$$Lambda$3.lambdaFactory$()).transform(PhotoManagerAdapter$$Lambda$4.lambdaFactory$()).toList();
    }

    static /* synthetic */ boolean lambda$getPhotoOrder$0(PhotoRearrangerItem model) {
        return model.state == State.Normal;
    }

    public void updatePhotos(Listing listing) {
        this.orderedListingPhotos = listing.getSortedPhotos();
        updatePhotos();
    }

    /* access modifiers changed from: private */
    public void itemClicked(PhotoRearrangerItem model) {
        switch (model.state) {
            case Normal:
                this.listener.photoDetailsRequested(model.f2705id);
                return;
            case Failed:
                PhotoMenuUtil.showFailedMenu(this.context, this.photoUploadManager, model.f2705id);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    public void updatePhotos() {
        List<PhotoRearrangerItem> outgoingPhotoModels = FluentIterable.from((Iterable<E>) this.photoUploadManager.getOrderedOutgoingItems(this.listingId, PhotoUploadTarget.ListingPhoto)).transform(PhotoManagerAdapter$$Lambda$5.lambdaFactory$()).toList();
        if (this.orderedListingPhotos == null) {
            setModels(outgoingPhotoModels);
        } else {
            setModels(FluentIterable.from((Iterable<E>) this.orderedListingPhotos).transform(PhotoManagerAdapter$$Lambda$6.lambdaFactory$()).append((Iterable<? extends E>) outgoingPhotoModels).toList());
        }
    }

    /* access modifiers changed from: private */
    public static PhotoRearrangerItem createPhotoModel(Photo photo) {
        return createModel(photo.getId(), photo, State.Normal);
    }

    /* access modifiers changed from: private */
    public static PhotoRearrangerItem createPhotoModel(PhotoUploadTransaction photoUpload) {
        return createModel(photoUpload.offlineId, new SimpleImage(photoUpload.getPath()), convertState(photoUpload.getState()));
    }

    private static PhotoRearrangerItem createModel(long id, Image image, State state) {
        return new PhotoRearrangerItem(id, image, state, C7213R.string.manage_listing_photo_label_cover_photo);
    }

    private static State convertState(PhotoUploadTransaction.State uploadState) {
        switch (uploadState) {
            case Pending:
                return State.Sending;
            case Failed:
                return State.Failed;
            default:
                return State.Normal;
        }
    }
}
