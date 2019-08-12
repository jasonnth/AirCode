package com.airbnb.android.referrals.views;

import android.text.TextUtils;
import android.view.View;
import butterknife.BindView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.referrals.C1532R;
import com.airbnb.p027n2.epoxy.AirEpoxyModelWithHolder;
import com.airbnb.p027n2.epoxy.AirViewHolder;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;

public abstract class PendingReferralRow extends AirEpoxyModelWithHolder<Holder> {
    String imageUrl;
    String status;

    static final class Holder extends AirViewHolder {
        @BindView
        HaloImageView image;
        @BindView
        AirTextView status;

        Holder() {
        }

        public void setImageUrl(String imageUrl) {
            if (TextUtils.isEmpty(imageUrl)) {
                this.image.setImageDrawableCompat(C1532R.C1533drawable.n2_empty_profile_halo_large_kazan);
            } else {
                this.image.setImageUrl(imageUrl);
            }
        }
    }

    public final class Holder_ViewBinding implements Unbinder {
        private Holder target;

        public Holder_ViewBinding(Holder target2, View source) {
            this.target = target2;
            target2.image = (HaloImageView) Utils.findRequiredViewAsType(source, C1532R.C1534id.image, "field 'image'", HaloImageView.class);
            target2.status = (AirTextView) Utils.findRequiredViewAsType(source, C1532R.C1534id.status, "field 'status'", AirTextView.class);
        }

        public void unbind() {
            Holder target2 = this.target;
            if (target2 == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            target2.image = null;
            target2.status = null;
        }
    }

    public void bind(Holder viewHolder) {
        super.bind(viewHolder);
        viewHolder.setImageUrl(this.imageUrl);
        viewHolder.status.setText(this.status);
    }
}
