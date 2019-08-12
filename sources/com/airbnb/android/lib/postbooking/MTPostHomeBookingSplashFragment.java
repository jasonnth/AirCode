package com.airbnb.android.lib.postbooking;

import android.view.View;
import butterknife.BindView;
import com.airbnb.android.core.responses.PostHomeBookingResponse.PHB;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

public class MTPostHomeBookingSplashFragment extends MTBasePostHomeBookingFragment {
    @BindView
    AirImageView imageView;
    @BindView
    DocumentMarquee marquee;

    public static MTPostHomeBookingSplashFragment newInstance(String reservationId) {
        return (MTPostHomeBookingSplashFragment) ((FragmentBundleBuilder) FragmentBundler.make(new MTPostHomeBookingSplashFragment()).putString("arg_reservation_id", reservationId)).build();
    }

    /* access modifiers changed from: protected */
    public void initViews(View view) {
    }

    /* access modifiers changed from: protected */
    public int getLayoutRes() {
        return C0880R.layout.fragment_mt_phb_full_screen;
    }

    /* access modifiers changed from: protected */
    public void onResponseLoaded(PHB phb) {
        this.marquee.setTitle((CharSequence) phb.title);
        this.marquee.setCaption((CharSequence) phb.subtitle);
        this.imageView.setImageUrl(phb.getImageUrl());
    }
}
