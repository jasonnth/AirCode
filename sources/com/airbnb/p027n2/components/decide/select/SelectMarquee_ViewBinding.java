package com.airbnb.p027n2.components.decide.select;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.decide.select.SelectMarquee_ViewBinding */
public class SelectMarquee_ViewBinding implements Unbinder {
    private SelectMarquee target;

    public SelectMarquee_ViewBinding(SelectMarquee target2, View source) {
        this.target = target2;
        target2.imageView = (AirImageView) Utils.findRequiredViewAsType(source, R.id.image_view, "field 'imageView'", AirImageView.class);
        target2.titleView = (TextView) Utils.findRequiredViewAsType(source, R.id.title_view, "field 'titleView'", TextView.class);
        target2.homeTourButtonTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.home_tour_expanding_button_text, "field 'homeTourButtonTextView'", AirTextView.class);
        target2.homeTourButton = (LinearLayout) Utils.findRequiredViewAsType(source, R.id.home_tour_expanding_button, "field 'homeTourButton'", LinearLayout.class);
    }

    public void unbind() {
        SelectMarquee target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.imageView = null;
        target2.titleView = null;
        target2.homeTourButtonTextView = null;
        target2.homeTourButton = null;
    }
}
