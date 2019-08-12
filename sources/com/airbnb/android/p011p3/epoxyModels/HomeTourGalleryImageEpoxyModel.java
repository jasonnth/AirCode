package com.airbnb.android.p011p3.epoxyModels;

import android.view.View;
import butterknife.BindView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.p011p3.C7532R;
import com.airbnb.android.p011p3.HomeTourController;
import com.airbnb.p027n2.epoxy.AirEpoxyModelWithHolder;
import com.airbnb.p027n2.epoxy.AirViewHolder;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.primitives.imaging.Image;

/* renamed from: com.airbnb.android.p3.epoxyModels.HomeTourGalleryImageEpoxyModel */
public abstract class HomeTourGalleryImageEpoxyModel extends AirEpoxyModelWithHolder<GalleryImageHolder> {
    HomeTourController controller;
    Image image;
    int imagePosition;

    /* renamed from: com.airbnb.android.p3.epoxyModels.HomeTourGalleryImageEpoxyModel$GalleryImageHolder */
    static final class GalleryImageHolder extends AirViewHolder {
        @BindView
        AirImageView roomImage;

        GalleryImageHolder() {
        }
    }

    /* renamed from: com.airbnb.android.p3.epoxyModels.HomeTourGalleryImageEpoxyModel$GalleryImageHolder_ViewBinding */
    public final class GalleryImageHolder_ViewBinding implements Unbinder {
        private GalleryImageHolder target;

        public GalleryImageHolder_ViewBinding(GalleryImageHolder target2, View source) {
            this.target = target2;
            target2.roomImage = (AirImageView) Utils.findRequiredViewAsType(source, C7532R.C7534id.room_image, "field 'roomImage'", AirImageView.class);
        }

        public void unbind() {
            GalleryImageHolder target2 = this.target;
            if (target2 == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            target2.roomImage = null;
        }
    }

    public void bind(GalleryImageHolder holder) {
        super.bind(holder);
        holder.roomImage.setImage(this.image);
        holder.roomImage.setOnClickListener(HomeTourGalleryImageEpoxyModel$$Lambda$1.lambdaFactory$(this));
    }

    public void unbind(GalleryImageHolder holder) {
        super.unbind(holder);
        holder.roomImage.clear();
        holder.roomImage.setOnClickListener(null);
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return 1;
    }
}
