package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.EventScheduleInterstitial_ViewBinding */
public class EventScheduleInterstitial_ViewBinding implements Unbinder {
    private EventScheduleInterstitial target;

    public EventScheduleInterstitial_ViewBinding(EventScheduleInterstitial target2, View source) {
        this.target = target2;
        target2.jellyfishView = (JellyfishView) Utils.findRequiredViewAsType(source, R.id.jellyfish_view, "field 'jellyfishView'", JellyfishView.class);
        target2.headerText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.headerText, "field 'headerText'", AirTextView.class);
        target2.titleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.titleText, "field 'titleText'", AirTextView.class);
        target2.dateTimeText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.datetime_text, "field 'dateTimeText'", AirTextView.class);
        target2.addressText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.address_text, "field 'addressText'", AirTextView.class);
        target2.button = (AirButton) Utils.findRequiredViewAsType(source, R.id.button, "field 'button'", AirButton.class);
    }

    public void unbind() {
        EventScheduleInterstitial target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.jellyfishView = null;
        target2.headerText = null;
        target2.titleText = null;
        target2.dateTimeText = null;
        target2.addressText = null;
        target2.button = null;
    }
}
