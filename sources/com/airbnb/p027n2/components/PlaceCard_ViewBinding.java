package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.WishListIconView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.PlaceCard_ViewBinding */
public class PlaceCard_ViewBinding implements Unbinder {
    private PlaceCard target;

    public PlaceCard_ViewBinding(PlaceCard target2, View source) {
        this.target = target2;
        target2.imageView = (AirImageView) Utils.findRequiredViewAsType(source, R.id.image, "field 'imageView'", AirImageView.class);
        target2.iconVisibilityGradient = Utils.findRequiredView(source, R.id.icon_visibility_gradient, "field 'iconVisibilityGradient'");
        target2.titleTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title_text, "field 'titleTextView'", AirTextView.class);
        target2.wishListIcon = (WishListIconView) Utils.findRequiredViewAsType(source, R.id.wish_list_heart, "field 'wishListIcon'", WishListIconView.class);
        target2.selectionHighlight = Utils.findRequiredView(source, R.id.selection_highlight, "field 'selectionHighlight'");
        target2.bottomSpace = Utils.findRequiredView(source, R.id.bottom_space, "field 'bottomSpace'");
        target2.cardDetails = Utils.findRequiredView(source, R.id.card_details, "field 'cardDetails'");
        target2.cardTag = (AirTextView) Utils.findRequiredViewAsType(source, R.id.card_tag, "field 'cardTag'", AirTextView.class);
        target2.cardTitle = (AirTextView) Utils.findRequiredViewAsType(source, R.id.card_title, "field 'cardTitle'", AirTextView.class);
    }

    public void unbind() {
        PlaceCard target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.imageView = null;
        target2.iconVisibilityGradient = null;
        target2.titleTextView = null;
        target2.wishListIcon = null;
        target2.selectionHighlight = null;
        target2.bottomSpace = null;
        target2.cardDetails = null;
        target2.cardTag = null;
        target2.cardTitle = null;
    }
}
