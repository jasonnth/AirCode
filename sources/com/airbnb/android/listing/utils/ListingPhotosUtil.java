package com.airbnb.android.listing.utils;

import android.content.Context;
import com.airbnb.android.core.intents.ManageListingIntents;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Photo;
import com.airbnb.android.core.requests.photos.PhotoUpload;
import com.airbnb.android.core.requests.photos.PhotoUploadTarget;
import com.airbnb.android.core.utils.SettingDeepLink;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import java.util.List;

public class ListingPhotosUtil {
    public static void moveToStart(Listing listing, Photo photo) {
        List<Photo> updatedPhotos = Lists.newArrayList((Iterable<? extends E>) listing.getSortedPhotos());
        updatedPhotos.remove(photo);
        updatedPhotos.add(0, photo);
        setPhotosWithOrder(listing, updatedPhotos);
    }

    public static void remove(Listing listing, Photo photo) {
        List<Photo> updatedPhotos = Lists.newArrayList((Iterable<? extends E>) listing.getSortedPhotos());
        updatedPhotos.remove(photo);
        setPhotosWithOrder(listing, updatedPhotos);
    }

    public static void addPhoto(Listing listing, Photo uploadedPhoto) {
        FluentIterable<Photo> photos = FluentIterable.from((Iterable<E>) listing.getSortedPhotos());
        if (!photos.anyMatch(ListingPhotosUtil$$Lambda$1.lambdaFactory$(uploadedPhoto))) {
            setPhotosWithOrder(listing, photos.append((E[]) new Photo[]{uploadedPhoto}).toList());
        }
    }

    static /* synthetic */ boolean lambda$addPhoto$0(Photo uploadedPhoto, Photo photo) {
        return photo.getId() == uploadedPhoto.getId();
    }

    private static void setPhotosWithOrder(Listing listing, List<Photo> photos) {
        for (int i = 0; i < photos.size(); i++) {
            ((Photo) photos.get(i)).setSortOrder(i + 1);
        }
        listing.setPhotos(photos);
    }

    public static PhotoUpload createPhotoUpload(Context context, Listing listing, String path) {
        return PhotoUpload.builder(listing.getId(), path).galleryId(listing.getId()).uploadTarget(PhotoUploadTarget.ListingPhoto).notificationIntent(ManageListingIntents.intentForExistingListingSetting(context, listing.getId(), SettingDeepLink.Photos)).build();
    }
}
