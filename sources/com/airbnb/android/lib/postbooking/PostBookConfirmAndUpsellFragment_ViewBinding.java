package com.airbnb.android.lib.postbooking;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.collections.Carousel;
import com.airbnb.p027n2.components.DocumentMarquee;

public class PostBookConfirmAndUpsellFragment_ViewBinding implements Unbinder {
    private PostBookConfirmAndUpsellFragment target;
    private View view2131756035;

    public PostBookConfirmAndUpsellFragment_ViewBinding(final PostBookConfirmAndUpsellFragment target2, View source) {
        this.target = target2;
        target2.headerMarquee = (DocumentMarquee) Utils.findRequiredViewAsType(source, C0880R.C0882id.header_marquee, "field 'headerMarquee'", DocumentMarquee.class);
        target2.tripsCarousel = (Carousel) Utils.findRequiredViewAsType(source, C0880R.C0882id.trips_carousel, "field 'tripsCarousel'", Carousel.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.next_button, "method 'onSkip'");
        this.view2131756035 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onSkip();
            }
        });
    }

    public void unbind() {
        PostBookConfirmAndUpsellFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.headerMarquee = null;
        target2.tripsCarousel = null;
        this.view2131756035.setOnClickListener(null);
        this.view2131756035 = null;
    }
}
