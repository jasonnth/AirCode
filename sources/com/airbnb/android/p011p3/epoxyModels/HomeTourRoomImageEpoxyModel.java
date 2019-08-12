package com.airbnb.android.p011p3.epoxyModels;

import android.view.View;
import android.view.View.OnClickListener;
import butterknife.BindView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.p011p3.C7532R;
import com.airbnb.p027n2.epoxy.AirEpoxyModelWithHolder;
import com.airbnb.p027n2.epoxy.AirViewHolder;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.primitives.imaging.Image;

/* renamed from: com.airbnb.android.p3.epoxyModels.HomeTourRoomImageEpoxyModel */
public abstract class HomeTourRoomImageEpoxyModel extends AirEpoxyModelWithHolder<HomeTourRoomImageHolder> {
    Image image;
    OnClickListener onClickListener;

    /* renamed from: com.airbnb.android.p3.epoxyModels.HomeTourRoomImageEpoxyModel$HomeTourRoomImageHolder */
    static final class HomeTourRoomImageHolder extends AirViewHolder {
        @BindView
        AirImageView imageView;

        HomeTourRoomImageHolder() {
        }
    }

    /* renamed from: com.airbnb.android.p3.epoxyModels.HomeTourRoomImageEpoxyModel$HomeTourRoomImageHolder_ViewBinding */
    public final class HomeTourRoomImageHolder_ViewBinding implements Unbinder {
        private HomeTourRoomImageHolder target;

        public HomeTourRoomImageHolder_ViewBinding(HomeTourRoomImageHolder target2, View source) {
            this.target = target2;
            target2.imageView = (AirImageView) Utils.findRequiredViewAsType(source, C7532R.C7534id.home_tour_image, "field 'imageView'", AirImageView.class);
        }

        public void unbind() {
            HomeTourRoomImageHolder target2 = this.target;
            if (target2 == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            target2.imageView = null;
        }
    }

    public void bind(HomeTourRoomImageHolder holder) {
        super.bind(holder);
        holder.imageView.setImage(this.image);
        holder.imageView.setOnClickListener(this.onClickListener);
    }

    public void unbind(HomeTourRoomImageHolder holder) {
        super.unbind(holder);
        holder.imageView.clear();
        holder.imageView.setOnClickListener(null);
    }
}
