package com.airbnb.android.lib.adapters;

import android.content.Context;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.app.FragmentStatePagerAdapter;
import android.text.TextUtils;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Photo;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.lib.controller.PropertyImagesPagerPreloader.PropertyImagesAdapter;
import com.airbnb.android.lib.fragments.ImageFragment;
import java.util.List;

public class PropertyImagesViewPagerFragmentAdapter extends FragmentStatePagerAdapter implements PropertyImagesAdapter {
    private final int defaultImageRes = 0;
    private final int fragmentLayoutRes = 0;
    private final Listing listing;
    private final boolean showCaption;
    private final boolean useLargeImages;

    public PropertyImagesViewPagerFragmentAdapter(Context context, FragmentManager fm, Listing listing2, boolean showCaption2) {
        super(fm);
        this.listing = listing2;
        this.showCaption = showCaption2;
        this.useLargeImages = MiscUtils.isTabletScreen(context);
    }

    public Fragment getItem(int position) {
        boolean hasCaption;
        String imageUrl = getPictureUrlForAdapterPosition(position);
        String imageCaption = null;
        if (this.showCaption) {
            List<Photo> photos = this.listing.getPhotos();
            if (photos == null || photos.size() <= position || TextUtils.isEmpty(((Photo) photos.get(position)).getCaption())) {
                hasCaption = false;
            } else {
                hasCaption = true;
            }
            imageCaption = hasCaption ? ((Photo) photos.get(position)).getCaption() : null;
        }
        if (this.fragmentLayoutRes > 0) {
            return ImageFragment.newInstance(imageUrl, imageCaption, this.fragmentLayoutRes, this.defaultImageRes);
        }
        return ImageFragment.newInstance(imageUrl, imageCaption, false);
    }

    public int getCount() {
        if (this.listing == null) {
            return 0;
        }
        return getTotalPictures();
    }

    public String getPictureUrlForAdapterPosition(int position) {
        if (this.useLargeImages && this.listing.getXlPictureUrls() != null) {
            return (String) this.listing.getXlPictureUrls().get(position);
        }
        if (this.listing.getPictureUrls() != null) {
            return (String) this.listing.getPictureUrls().get(position);
        }
        return null;
    }

    private int getTotalPictures() {
        if (this.useLargeImages && this.listing.getXlPictureUrls() != null) {
            return this.listing.getXlPictureUrls().size();
        }
        if (this.listing.getPictureUrls() != null) {
            return this.listing.getPictureUrls().size();
        }
        return 0;
    }
}
