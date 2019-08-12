package com.airbnb.android.lib.tripassistant;

import android.view.View.OnClickListener;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.arguments.P3Arguments;
import com.airbnb.android.core.models.Attachment;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import java.util.List;

public class MessagePhotosAdapter extends AirEpoxyAdapter {
    private final PhotoType photoType;

    private static class PhotoEpoxyModel extends AirEpoxyModel<AirImageView> {
        private final OnClickListener clickListener;
        private final Attachment photo;
        private final PhotoType photoType;

        public PhotoEpoxyModel(PhotoType photoType2, Attachment photo2, OnClickListener clickListener2) {
            this.photo = photo2;
            this.photoType = photoType2;
            this.clickListener = clickListener2;
        }

        public void bind(AirImageView view) {
            super.bind(view);
            view.setImageUrl(this.photo.getUrl());
            if (this.clickListener != null) {
                view.setOnClickListener(this.clickListener);
            }
        }

        public void unbind(AirImageView view) {
            super.unbind(view);
            view.clear();
        }

        /* access modifiers changed from: protected */
        public int getDefaultLayout() {
            switch (this.photoType) {
                case Map:
                    return C0880R.layout.view_model_help_thread_map_photo;
                default:
                    return C0880R.layout.view_model_help_thread_message_photo;
            }
        }
    }

    public enum PhotoType {
        Uploaded("uploaded"),
        Map(P3Arguments.FROM_MAP);
        
        private final String key;

        private PhotoType(String key2) {
            this.key = key2;
        }
    }

    public MessagePhotosAdapter(PhotoType photoType2, List<Attachment> photos, List<OnClickListener> clickListeners) {
        this.photoType = photoType2;
        if (photos.isEmpty()) {
            addModels((EpoxyModel<?>[]) new EpoxyModel[]{new LoadingRowEpoxyModel_().withInverseLayout()});
        }
        if (!(clickListeners == null || clickListeners.size() == photos.size())) {
            BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("inconsistent number for photo (" + photos.size() + ") and clickListener (" + clickListeners.size() + ")"));
        }
        for (int i = 0; i < photos.size(); i++) {
            addModels((EpoxyModel<?>[]) new EpoxyModel[]{new PhotoEpoxyModel(photoType2, (Attachment) photos.get(i), clickListeners == null ? null : (OnClickListener) clickListeners.get(i))});
        }
    }
}
