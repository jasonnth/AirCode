package com.airbnb.android.lib.fragments.price_breakdown;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Price;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.ParcelStrap;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.adapters.PriceBreakdownAdapter;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import icepick.State;

public class PriceBreakdownFragment extends AirFragment {
    private static final String ARG_FOR_HOST = "arg_for_host";
    private static final String ARG_IS_FROM_BOOKING = "arg_is_booking";
    private static final String ARG_LISTING = "arg_listing";
    private static final String ARG_NAVIGATION_TAG = "arg_navigation_tag";
    private static final String ARG_NAVIGATION_TAG_PARAMS = "arg_navigation_tag_params";
    private static final String ARG_PRICE = "arg_price";
    private static final String ARG_RESERVATION = "arg_reservation";
    private static final String ARG_STAY_DURATION = "arg_stayDuration";
    @State
    Listing listing;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static PriceBreakdownFragment forHost(Price price, Listing listing2) {
        return (PriceBreakdownFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new PriceBreakdownFragment()).putParcelable(ARG_PRICE, price)).putParcelable(ARG_LISTING, listing2)).putBoolean(ARG_FOR_HOST, true)).build();
    }

    public static PriceBreakdownFragment forGuestReservation(Reservation reservation) {
        return (PriceBreakdownFragment) ((FragmentBundleBuilder) FragmentBundler.make(new PriceBreakdownFragment()).putParcelable("arg_reservation", reservation)).build();
    }

    public static PriceBreakdownFragment forReservationBooking(Reservation reservation, Listing listing2) {
        return (PriceBreakdownFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new PriceBreakdownFragment()).putParcelable("arg_reservation", reservation)).putParcelable(ARG_LISTING, listing2)).build();
    }

    public static PriceBreakdownFragment forReservationBookingV2(Price price, Listing listing2, int stayDuration, boolean isFromBooking, NavigationTag navigationTag, ParcelStrap params) {
        return (PriceBreakdownFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new PriceBreakdownFragment()).putParcelable(ARG_PRICE, price)).putParcelable(ARG_LISTING, listing2)).putInt(ARG_STAY_DURATION, stayDuration)).putBoolean(ARG_IS_FROM_BOOKING, isFromBooking)).putSerializable(ARG_NAVIGATION_TAG, navigationTag)).putParcelable(ARG_NAVIGATION_TAG_PARAMS, params)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_price_breakdown, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        boolean forHost = getArguments().getBoolean(ARG_FOR_HOST, false);
        int stayDuration = getArguments().getInt(ARG_STAY_DURATION);
        Price price = (Price) getArguments().getParcelable(ARG_PRICE);
        Reservation reservation = (Reservation) getArguments().getParcelable("arg_reservation");
        this.listing = (Listing) Check.notNull(getArguments().containsKey(ARG_LISTING) ? (Listing) getArguments().getParcelable(ARG_LISTING) : reservation.getListing());
        boolean isFromBooking = getArguments().getBoolean(ARG_IS_FROM_BOOKING, false);
        int titleRes = forHost ? C0880R.string.payout_breakdown_title : C0880R.string.p4_payment_breakdown_title;
        String caption = stayDuration > 0 ? getResources().getQuantityString(C0880R.plurals.x_nights_in_city, stayDuration, new Object[]{Integer.valueOf(stayDuration), this.listing.getCity()}) : "";
        if (price == null) {
            price = forHost ? reservation.getPricingQuote().getHostPayoutBreakdown() : reservation.getPricingQuote().getPrice();
        }
        this.recyclerView.setAdapter(new PriceBreakdownAdapter(getContext(), titleRes, caption, isFromBooking, price));
        return view;
    }

    public Strap getNavigationTrackingParams() {
        if (getArguments().containsKey(ARG_NAVIGATION_TAG_PARAMS)) {
            return ((ParcelStrap) getArguments().getParcelable(ARG_NAVIGATION_TAG_PARAMS)).mo9944kv("listing_id", (double) this.listing.getId()).internal();
        }
        return super.getNavigationTrackingParams().mo11638kv("listing_id", this.listing.getId());
    }

    public NavigationTag getNavigationTrackingTag() {
        if (getArguments().containsKey(ARG_NAVIGATION_TAG)) {
            return (NavigationTag) getArguments().get(ARG_NAVIGATION_TAG);
        }
        return getArguments().getBoolean(ARG_FOR_HOST, false) ? NavigationTag.PayoutBreakdown : NavigationTag.PaymentBreakdown;
    }
}
