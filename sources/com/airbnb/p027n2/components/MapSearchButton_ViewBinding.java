package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.MapSearchButton_ViewBinding */
public class MapSearchButton_ViewBinding implements Unbinder {
    private MapSearchButton target;

    public MapSearchButton_ViewBinding(MapSearchButton target2, View source) {
        this.target = target2;
        target2.loadingView = (LoadingView) Utils.findRequiredViewAsType(source, R.id.loading_view, "field 'loadingView'", LoadingView.class);
        target2.textView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.text, "field 'textView'", AirTextView.class);
    }

    public void unbind() {
        MapSearchButton target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.loadingView = null;
        target2.textView = null;
    }
}
