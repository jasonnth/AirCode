package com.airbnb.android.lib.views;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class MapScale_ViewBinding implements Unbinder {
    private MapScale target;

    public MapScale_ViewBinding(MapScale target2) {
        this(target2, target2);
    }

    public MapScale_ViewBinding(MapScale target2, View source) {
        this.target = target2;
        target2.mMilesBar = Utils.findRequiredView(source, C0880R.C0882id.mapscale_miles_bar, "field 'mMilesBar'");
        target2.mKmBar = Utils.findRequiredView(source, C0880R.C0882id.mapscale_km_bar, "field 'mKmBar'");
        target2.mMilesText = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.mapscale_miles_text, "field 'mMilesText'", TextView.class);
        target2.mKmText = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.mapscale_km_text, "field 'mKmText'", TextView.class);
    }

    public void unbind() {
        MapScale target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mMilesBar = null;
        target2.mKmBar = null;
        target2.mMilesText = null;
        target2.mKmText = null;
    }
}
