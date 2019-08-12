package com.airbnb.android.lib.tripassistant.amenities;

import android.view.View.OnClickListener;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.tripassistant.HelpThreadPhoto;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

abstract class PhotoModel extends AirEpoxyModel<HTPhotoView> {
    OnClickListener deleteListener;
    HelpThreadPhoto photo;
    boolean showLoader;

    PhotoModel() {
    }

    public void bind(HTPhotoView view) {
        super.bind(view);
        view.setPhoto(this.photo);
        view.showLoader(this.showLoader);
        view.setDeleteButtonClickListener(this.deleteListener);
    }

    public void unbind(HTPhotoView view) {
        super.unbind(view);
        view.clear();
        view.setDeleteButtonClickListener(null);
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0880R.layout.view_model_help_thread_photo_picker_photo;
    }

    public boolean hasPhoto(HelpThreadPhoto photo2) {
        return this.photo.equals(photo2);
    }
}
