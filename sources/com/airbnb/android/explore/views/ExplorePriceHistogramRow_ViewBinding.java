package com.airbnb.android.explore.views;

import android.view.View;
import android.widget.FrameLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.explore.C0857R;
import com.airbnb.p027n2.primitives.AirTextView;

public class ExplorePriceHistogramRow_ViewBinding implements Unbinder {
    private ExplorePriceHistogramRow target;

    public ExplorePriceHistogramRow_ViewBinding(ExplorePriceHistogramRow target2) {
        this(target2, target2);
    }

    public ExplorePriceHistogramRow_ViewBinding(ExplorePriceHistogramRow target2, View source) {
        this.target = target2;
        target2.priceTextView = (AirTextView) Utils.findRequiredViewAsType(source, C0857R.C0859id.price_text, "field 'priceTextView'", AirTextView.class);
        target2.averagePriceTextView = (AirTextView) Utils.findRequiredViewAsType(source, C0857R.C0859id.avg_price_text, "field 'averagePriceTextView'", AirTextView.class);
        target2.histogramContainer = (FrameLayout) Utils.findRequiredViewAsType(source, C0857R.C0859id.histogram_container, "field 'histogramContainer'", FrameLayout.class);
    }

    public void unbind() {
        ExplorePriceHistogramRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.priceTextView = null;
        target2.averagePriceTextView = null;
        target2.histogramContainer = null;
    }
}
