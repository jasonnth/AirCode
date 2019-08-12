package com.airbnb.android.core.viewcomponents.models;

import android.text.TextUtils;
import com.airbnb.p027n2.components.photorearranger.RearrangableLabeledPhotoCell;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class RearrangableLabeledPhotoEpoxyModel extends AirEpoxyModel<RearrangableLabeledPhotoCell> {
    CharSequence imageUrl;
    CharSequence label;
    CharSequence placeHolderText;

    public void bind(RearrangableLabeledPhotoCell view) {
        super.bind(view);
        view.setImage(this.imageUrl);
        view.setLabel(this.label);
        view.setPlaceholderText(TextUtils.isEmpty(this.imageUrl) ? this.placeHolderText : "");
    }

    public int getDividerViewType() {
        return -1;
    }

    public boolean isRearrangeable() {
        return true;
    }

    public void onDraggingStateChanged(RearrangableLabeledPhotoCell view, boolean isDragging) {
        if (isDragging) {
            view.onDrag();
        } else {
            view.onDrop();
        }
    }
}
