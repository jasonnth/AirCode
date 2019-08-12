package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.Interstitial_ViewBinding */
public class Interstitial_ViewBinding implements Unbinder {
    private Interstitial target;

    public Interstitial_ViewBinding(Interstitial target2, View source) {
        this.target = target2;
        target2.jellyfishView = (JellyfishView) Utils.findRequiredViewAsType(source, R.id.jellyfish_view, "field 'jellyfishView'", JellyfishView.class);
        target2.textView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.text, "field 'textView'", AirTextView.class);
        target2.captionView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.caption, "field 'captionView'", AirTextView.class);
        target2.button = (AirButton) Utils.findRequiredViewAsType(source, R.id.button, "field 'button'", AirButton.class);
        target2.topSpace = Utils.findRequiredView(source, R.id.space_top, "field 'topSpace'");
        target2.bottomSpace = Utils.findRequiredView(source, R.id.space_bottom, "field 'bottomSpace'");
    }

    public void unbind() {
        Interstitial target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.jellyfishView = null;
        target2.textView = null;
        target2.captionView = null;
        target2.button = null;
        target2.topSpace = null;
        target2.bottomSpace = null;
    }
}
