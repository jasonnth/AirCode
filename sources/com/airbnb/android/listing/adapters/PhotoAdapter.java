package com.airbnb.android.listing.adapters;

import android.os.Bundle;
import com.airbnb.android.core.models.Photo;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.InlineInputRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.RearrangablePhotoRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToolbarSpacerEpoxyModel_;
import com.airbnb.android.listing.C7213R;
import com.airbnb.epoxy.EpoxyModel;
import com.google.common.base.Objects;
import icepick.State;

public class PhotoAdapter extends AirEpoxyAdapter {
    @State
    String caption;
    private final InlineInputRowEpoxyModel_ captionRow = new InlineInputRowEpoxyModel_().titleRes(C7213R.string.manage_listing_photo_caption_input_title).hintRes(C7213R.string.manage_listing_photo_caption_input_hint).inputMaxLines(3);
    @State
    boolean coverPhoto;
    private final RearrangablePhotoRowEpoxyModel_ photoRow = new RearrangablePhotoRowEpoxyModel_();

    public PhotoAdapter(Photo photo, Bundle savedInstanceState) {
        super(true);
        if (savedInstanceState == null) {
            this.caption = photo.getCaption();
            this.coverPhoto = photo.isCoverPhoto();
        } else {
            onRestoreInstanceState(savedInstanceState);
        }
        ToolbarSpacerEpoxyModel_ toolbarSpacer = new ToolbarSpacerEpoxyModel_();
        this.captionRow.input(this.caption);
        this.photoRow.image(photo);
        if (this.coverPhoto) {
            this.photoRow.labelRes(C7213R.string.manage_listing_photo_label_cover_photo);
        }
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{toolbarSpacer, this.captionRow, this.photoRow});
    }

    public void onSaveInstanceState(Bundle outState) {
        this.caption = getCaption();
        super.onSaveInstanceState(outState);
    }

    public boolean hasChanged(Photo originalPhoto) {
        return !Objects.equal(getCaption(), originalPhoto.getCaption()) || originalPhoto.isCoverPhoto() != this.coverPhoto;
    }

    public void setEnabled(boolean enabled) {
        this.captionRow.enabled(enabled);
        notifyModelChanged(this.captionRow);
    }

    public String getCaption() {
        CharSequence caption2 = this.captionRow.input();
        return caption2 != null ? caption2.toString() : "";
    }

    public boolean getMakeCoverPhoto(Photo originalPhoto) {
        return !originalPhoto.isCoverPhoto() && this.coverPhoto;
    }

    public boolean isCoverPhoto() {
        return this.coverPhoto;
    }

    public void setCoverPhoto() {
        this.coverPhoto = true;
        this.photoRow.labelRes(C7213R.string.manage_listing_photo_label_cover_photo);
        notifyModelChanged(this.photoRow);
    }
}
