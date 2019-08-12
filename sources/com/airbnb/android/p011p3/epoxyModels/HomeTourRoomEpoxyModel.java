package com.airbnb.android.p011p3.epoxyModels;

import android.view.View;
import android.view.View.OnClickListener;
import butterknife.BindView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.p011p3.C7532R;
import com.airbnb.p027n2.epoxy.AirEpoxyModelWithHolder;
import com.airbnb.p027n2.epoxy.AirViewHolder;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.primitives.imaging.Image;

/* renamed from: com.airbnb.android.p3.epoxyModels.HomeTourRoomEpoxyModel */
public abstract class HomeTourRoomEpoxyModel extends AirEpoxyModelWithHolder<RoomViewHolder> {
    Image image;
    String name;
    OnClickListener onImageClickListener;
    String subtitle;

    /* renamed from: com.airbnb.android.p3.epoxyModels.HomeTourRoomEpoxyModel$RoomViewHolder */
    static final class RoomViewHolder extends AirViewHolder {
        @BindView
        AirImageView roomImageView;
        @BindView
        AirTextView roomSubtitleTextView;
        @BindView
        AirTextView roomTitleTextView;

        RoomViewHolder() {
        }
    }

    /* renamed from: com.airbnb.android.p3.epoxyModels.HomeTourRoomEpoxyModel$RoomViewHolder_ViewBinding */
    public final class RoomViewHolder_ViewBinding implements Unbinder {
        private RoomViewHolder target;

        public RoomViewHolder_ViewBinding(RoomViewHolder target2, View source) {
            this.target = target2;
            target2.roomImageView = (AirImageView) Utils.findRequiredViewAsType(source, C7532R.C7534id.room_image_imageview, "field 'roomImageView'", AirImageView.class);
            target2.roomTitleTextView = (AirTextView) Utils.findRequiredViewAsType(source, C7532R.C7534id.room_title_textview, "field 'roomTitleTextView'", AirTextView.class);
            target2.roomSubtitleTextView = (AirTextView) Utils.findRequiredViewAsType(source, C7532R.C7534id.room_subtitle_textview, "field 'roomSubtitleTextView'", AirTextView.class);
        }

        public void unbind() {
            RoomViewHolder target2 = this.target;
            if (target2 == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            target2.roomImageView = null;
            target2.roomTitleTextView = null;
            target2.roomSubtitleTextView = null;
        }
    }

    public void bind(RoomViewHolder holder) {
        super.bind(holder);
        holder.roomImageView.setImage(this.image);
        holder.roomImageView.setOnClickListener(this.onImageClickListener);
        holder.roomTitleTextView.setText(this.name);
        holder.roomSubtitleTextView.setText(this.subtitle);
    }

    public void unbind(RoomViewHolder holder) {
        super.unbind(holder);
        holder.roomImageView.clear();
        holder.roomImageView.setOnClickListener(null);
    }
}
