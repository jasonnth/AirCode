package com.airbnb.p027n2.components;

import android.view.View;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.BookingNavigationView_ViewBinding */
public class BookingNavigationView_ViewBinding implements Unbinder {
    private BookingNavigationView target;

    public BookingNavigationView_ViewBinding(BookingNavigationView target2, View source) {
        this.target = target2;
        target2.container = (RelativeLayout) Utils.findRequiredViewAsType(source, R.id.container, "field 'container'", RelativeLayout.class);
        target2.button = (AirButton) Utils.findRequiredViewAsType(source, R.id.button, "field 'button'", AirButton.class);
        target2.priceDetails = (AirTextView) Utils.findRequiredViewAsType(source, R.id.price_details, "field 'priceDetails'", AirTextView.class);
        target2.seePriceDetails = (AirTextView) Utils.findRequiredViewAsType(source, R.id.see_price_details, "field 'seePriceDetails'", AirTextView.class);
        target2.loader = (LoadingView) Utils.findRequiredViewAsType(source, R.id.loader_view, "field 'loader'", LoadingView.class);
    }

    public void unbind() {
        BookingNavigationView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.container = null;
        target2.button = null;
        target2.priceDetails = null;
        target2.seePriceDetails = null;
        target2.loader = null;
    }
}
