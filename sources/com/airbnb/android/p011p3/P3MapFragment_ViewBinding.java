package com.airbnb.android.p011p3;

import android.content.Context;
import android.content.res.Resources;
import android.support.p000v4.content.ContextCompat;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.AirbnbMapView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.StandardRow;

/* renamed from: com.airbnb.android.p3.P3MapFragment_ViewBinding */
public class P3MapFragment_ViewBinding implements Unbinder {
    private P3MapFragment target;

    public P3MapFragment_ViewBinding(P3MapFragment target2, View source) {
        this.target = target2;
        target2.airMapView = (AirbnbMapView) Utils.findRequiredViewAsType(source, C7532R.C7534id.map_view, "field 'airMapView'", AirbnbMapView.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7532R.C7534id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.addressAndHood = (StandardRow) Utils.findRequiredViewAsType(source, C7532R.C7534id.address_hood, "field 'addressAndHood'", StandardRow.class);
        Context context = source.getContext();
        Resources res = context.getResources();
        target2.circleBorderColor = ContextCompat.getColor(context, C7532R.color.n2_babu);
        target2.circleFillColor = ContextCompat.getColor(context, C7532R.color.n2_babu_15);
        target2.circleStrokeWidth = res.getDimensionPixelSize(C7532R.dimen.map_circle_stroke_width);
    }

    public void unbind() {
        P3MapFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.airMapView = null;
        target2.toolbar = null;
        target2.addressAndHood = null;
    }
}
