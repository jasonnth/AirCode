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

/* renamed from: com.airbnb.android.p3.epoxyModels.HomeTourHighlightEpoxyModel */
public abstract class HomeTourHighlightEpoxyModel extends AirEpoxyModelWithHolder<HighlightViewHolder> {
    Image image;
    OnClickListener onImageClickListener;
    String subtitle;

    /* renamed from: com.airbnb.android.p3.epoxyModels.HomeTourHighlightEpoxyModel$HighlightViewHolder */
    static class HighlightViewHolder extends AirViewHolder {
        @BindView
        AirImageView imageView;
        @BindView
        AirTextView textView;

        HighlightViewHolder() {
        }
    }

    /* renamed from: com.airbnb.android.p3.epoxyModels.HomeTourHighlightEpoxyModel$HighlightViewHolder_ViewBinding */
    public class HighlightViewHolder_ViewBinding implements Unbinder {
        private HighlightViewHolder target;

        public HighlightViewHolder_ViewBinding(HighlightViewHolder target2, View source) {
            this.target = target2;
            target2.imageView = (AirImageView) Utils.findRequiredViewAsType(source, C7532R.C7534id.highlight_imageview, "field 'imageView'", AirImageView.class);
            target2.textView = (AirTextView) Utils.findRequiredViewAsType(source, C7532R.C7534id.highlight_textview, "field 'textView'", AirTextView.class);
        }

        public void unbind() {
            HighlightViewHolder target2 = this.target;
            if (target2 == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            target2.imageView = null;
            target2.textView = null;
        }
    }

    public void bind(HighlightViewHolder holder) {
        super.bind(holder);
        holder.imageView.setImage(this.image);
        holder.imageView.setOnClickListener(this.onImageClickListener);
        holder.textView.setText(this.subtitle);
    }

    public void unbind(HighlightViewHolder holder) {
        super.unbind(holder);
        holder.imageView.clear();
        holder.imageView.setOnClickListener(null);
    }
}
