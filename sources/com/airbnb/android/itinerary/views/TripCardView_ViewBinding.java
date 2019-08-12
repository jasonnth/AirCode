package com.airbnb.android.itinerary.views;

import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.itinerary.C5755R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

public class TripCardView_ViewBinding implements Unbinder {
    private TripCardView target;

    public TripCardView_ViewBinding(TripCardView target2) {
        this(target2, target2);
    }

    public TripCardView_ViewBinding(TripCardView target2, View source) {
        this.target = target2;
        target2.imageView = (AirImageView) Utils.findRequiredViewAsType(source, C5755R.C5757id.image, "field 'imageView'", AirImageView.class);
        target2.titleLayout = (LinearLayout) Utils.findRequiredViewAsType(source, C5755R.C5757id.title_layout, "field 'titleLayout'", LinearLayout.class);
        target2.title = (AirTextView) Utils.findRequiredViewAsType(source, C5755R.C5757id.title, "field 'title'", AirTextView.class);
        target2.subtitle = (AirTextView) Utils.findRequiredViewAsType(source, C5755R.C5757id.subtitle, "field 'subtitle'", AirTextView.class);
        target2.divider = Utils.findRequiredView(source, C5755R.C5757id.divider, "field 'divider'");
        target2.actionLayout = (LinearLayout) Utils.findRequiredViewAsType(source, C5755R.C5757id.action_layout, "field 'actionLayout'", LinearLayout.class);
        target2.actionTitle = (AirTextView) Utils.findRequiredViewAsType(source, C5755R.C5757id.action_title, "field 'actionTitle'", AirTextView.class);
        target2.actionButton = (AirTextView) Utils.findRequiredViewAsType(source, C5755R.C5757id.action_button, "field 'actionButton'", AirTextView.class);
    }

    public void unbind() {
        TripCardView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.imageView = null;
        target2.titleLayout = null;
        target2.title = null;
        target2.subtitle = null;
        target2.divider = null;
        target2.actionLayout = null;
        target2.actionTitle = null;
        target2.actionButton = null;
    }
}
