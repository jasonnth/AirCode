package com.airbnb.android.p011p3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.android.core.adapters.ListingTrayCarouselAdapter;
import com.airbnb.android.core.adapters.ListingTrayCarouselAdapter.ListingTrayItem;
import com.airbnb.android.core.models.SimilarListing;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.jitney.event.logging.WishlistSource.p295v3.C2813WishlistSource;
import com.airbnb.p027n2.collections.Carousel;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.primitives.AirButton;
import icepick.State;
import java.util.List;

/* renamed from: com.airbnb.android.p3.PostInquiryIBUpsellFragment */
public class PostInquiryIBUpsellFragment extends P3BaseFragment {
    private static final String ARG_IS_RTB = "is_rtb";
    @BindView
    AirButton button;
    @BindView
    DocumentMarquee documentMarquee;
    @State
    boolean isRTB;
    @BindView
    Carousel listingsCarousel;
    @BindView
    AirToolbar toolbar;

    public static PostInquiryIBUpsellFragment newInstance(boolean isRTB2) {
        return (PostInquiryIBUpsellFragment) ((FragmentBundleBuilder) FragmentBundler.make(new PostInquiryIBUpsellFragment()).putBoolean(ARG_IS_RTB, isRTB2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7532R.layout.fragment_post_inquiry_similar_homes, container, false);
        ButterKnife.bind(this, view);
        setToolbar(this.toolbar);
        if (savedInstanceState == null) {
            this.isRTB = getArguments().getBoolean(ARG_IS_RTB);
        }
        populateLayout();
        return view;
    }

    @OnClick
    public void onViewHomesClicked() {
        if (this.isRTB) {
            this.controller.launchInstantBookOnlyP2();
        } else {
            getFragmentManager().popBackStack();
        }
    }

    private void populateLayout() {
        String caption = this.isRTB ? getString(C7532R.string.similar_homes_instant_book_upsell) : getString(C7532R.string.instant_book_post_inquiry_upsell);
        this.documentMarquee.setCaption((CharSequence) caption);
        this.documentMarquee.setContentDescription(caption);
        this.button.setText(this.isRTB ? getString(C7532R.string.homes_search_cta) : getString(C7532R.string.close));
        if (this.isRTB) {
            this.listingsCarousel.setVisibility(0);
            setupSimilarListings();
        }
    }

    private void setupSimilarListings() {
        List<SimilarListing> similarListings = this.controller.getState().similarListings();
        Check.notNull(similarListings);
        ListingTrayCarouselAdapter adapter = new ListingTrayCarouselAdapter(PostInquiryIBUpsellFragment$$Lambda$1.lambdaFactory$(this), getContext());
        adapter.setItems(ListingTrayItem.fromSimilarListings(similarListings, C2813WishlistSource.HomeDetail));
        this.listingsCarousel.setAdapter(adapter);
    }

    static /* synthetic */ void lambda$setupSimilarListings$0(PostInquiryIBUpsellFragment postInquiryIBUpsellFragment, View view, ListingTrayItem item) {
        postInquiryIBUpsellFragment.controller.launchP3ForSimilarListing(view, item.listing, item.price);
        postInquiryIBUpsellFragment.controller.getAnalytics().trackSimilarListingsClick(item.listing.getId());
    }
}
