package com.airbnb.p027n2.components;

import android.support.p002v7.widget.CardView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.ListingInfoCardRow_ViewBinding */
public class ListingInfoCardRow_ViewBinding implements Unbinder {
    private ListingInfoCardRow target;

    public ListingInfoCardRow_ViewBinding(ListingInfoCardRow target2, View source) {
        this.target = target2;
        target2.cardView = (CardView) Utils.findRequiredViewAsType(source, R.id.card_view, "field 'cardView'", CardView.class);
        target2.titleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.card_title, "field 'titleText'", AirTextView.class);
        target2.subTitleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.card_subtitle, "field 'subTitleText'", AirTextView.class);
        target2.imageView = (AirImageView) Utils.findRequiredViewAsType(source, R.id.card_image, "field 'imageView'", AirImageView.class);
    }

    public void unbind() {
        ListingInfoCardRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.cardView = null;
        target2.titleText = null;
        target2.subTitleText = null;
        target2.imageView = null;
    }
}
