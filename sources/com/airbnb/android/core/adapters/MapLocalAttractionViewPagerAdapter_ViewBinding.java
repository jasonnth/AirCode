package com.airbnb.android.core.adapters;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

public class MapLocalAttractionViewPagerAdapter_ViewBinding implements Unbinder {
    private MapLocalAttractionViewPagerAdapter target;

    public MapLocalAttractionViewPagerAdapter_ViewBinding(MapLocalAttractionViewPagerAdapter target2, View source) {
        this.target = target2;
        target2.mLocalAttractionThumbnail = (AirImageView) Utils.findRequiredViewAsType(source, C0716R.C0718id.local_attraction_thumbnail, "field 'mLocalAttractionThumbnail'", AirImageView.class);
        target2.mLocalAttractionName = (AirTextView) Utils.findRequiredViewAsType(source, C0716R.C0718id.local_attraction_name, "field 'mLocalAttractionName'", AirTextView.class);
        target2.mLocalAttractionDescription = (AirTextView) Utils.findRequiredViewAsType(source, C0716R.C0718id.local_attraction_description, "field 'mLocalAttractionDescription'", AirTextView.class);
    }

    public void unbind() {
        MapLocalAttractionViewPagerAdapter target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mLocalAttractionThumbnail = null;
        target2.mLocalAttractionName = null;
        target2.mLocalAttractionDescription = null;
    }
}
