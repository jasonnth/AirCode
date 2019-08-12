package com.airbnb.android.sharing.p033ui;

import com.airbnb.p027n2.epoxy.AirEpoxyModel;

/* renamed from: com.airbnb.android.sharing.ui.SharePreviewEpoxyModel */
public abstract class SharePreviewEpoxyModel extends AirEpoxyModel<SharePreview> {
    String imagePath;
    String imageUrl;
    String title;

    public void bind(SharePreview view) {
        super.bind(view);
        if (this.imagePath != null) {
            view.setImagePath(this.imagePath);
            return;
        }
        view.setImageUrl(this.imageUrl);
        view.setTitle(this.title);
    }
}
