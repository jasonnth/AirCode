package com.airbnb.android.p011p3;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.p027n2.collections.Carousel;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.primitives.AirButton;

/* renamed from: com.airbnb.android.p3.PostInquiryIBUpsellFragment_ViewBinding */
public class PostInquiryIBUpsellFragment_ViewBinding implements Unbinder {
    private PostInquiryIBUpsellFragment target;
    private View view2131755560;

    public PostInquiryIBUpsellFragment_ViewBinding(final PostInquiryIBUpsellFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7532R.C7534id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.listingsCarousel = (Carousel) Utils.findRequiredViewAsType(source, C7532R.C7534id.listings_carousel, "field 'listingsCarousel'", Carousel.class);
        target2.documentMarquee = (DocumentMarquee) Utils.findRequiredViewAsType(source, C7532R.C7534id.document_marquee, "field 'documentMarquee'", DocumentMarquee.class);
        View view = Utils.findRequiredView(source, C7532R.C7534id.ib_upsell_cta, "field 'button' and method 'onViewHomesClicked'");
        target2.button = (AirButton) Utils.castView(view, C7532R.C7534id.ib_upsell_cta, "field 'button'", AirButton.class);
        this.view2131755560 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onViewHomesClicked();
            }
        });
    }

    public void unbind() {
        PostInquiryIBUpsellFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.listingsCarousel = null;
        target2.documentMarquee = null;
        target2.button = null;
        this.view2131755560.setOnClickListener(null);
        this.view2131755560 = null;
    }
}
