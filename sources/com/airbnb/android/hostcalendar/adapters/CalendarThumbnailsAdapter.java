package com.airbnb.android.hostcalendar.adapters;

import android.support.p000v4.util.LongSparseArray;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.hostcalendar.viewmodels.CalendarMiniThumbnailEpoxyModel;
import com.airbnb.epoxy.EpoxyModel;
import java.util.ArrayList;
import java.util.Collection;

public class CalendarThumbnailsAdapter extends AirEpoxyAdapter {
    private final CalendarThumbnailClickListener listener;
    private final LongSparseArray<ArrayList<Reservation>> reservationsByListingId = new LongSparseArray<>();
    private boolean showListingImage;

    public interface CalendarThumbnailClickListener {
        void onClick(Listing listing);
    }

    public CalendarThumbnailsAdapter(CalendarThumbnailClickListener listener2) {
        this.listener = listener2;
    }

    public void clear() {
        this.reservationsByListingId.clear();
        this.models.clear();
        notifyDataSetChanged();
    }

    public void init(Collection<Listing> listings, Collection<Reservation> reservations, AirDate startDate, boolean showListingImage2) {
        this.showListingImage = showListingImage2;
        storeReservations(reservations);
        this.models.clear();
        addListings(listings, startDate);
    }

    public void storeReservations(Collection<Reservation> reservations) {
        for (Reservation reservation : reservations) {
            long listingId = reservation.getListing().getId();
            if (this.reservationsByListingId.get(listingId) == null) {
                this.reservationsByListingId.put(listingId, new ArrayList());
            }
            ((ArrayList) this.reservationsByListingId.get(listingId)).add(reservation);
        }
    }

    public void addListings(Collection<Listing> listings, AirDate startDate) {
        AirDate today = AirDate.today();
        for (Listing listing : listings) {
            this.models.add(makeCalendarMiniThumbnailEpoxyModel(listing, startDate, today, (ArrayList) this.reservationsByListingId.get(listing.getId())).mo11716id(listing.getId()));
        }
        notifyDataSetChanged();
    }

    public int size() {
        return this.models.size();
    }

    private EpoxyModel<?> makeCalendarMiniThumbnailEpoxyModel(Listing listing, AirDate startDate, AirDate today, ArrayList<Reservation> reservations) {
        return new CalendarMiniThumbnailEpoxyModel().listing(listing).startDate(startDate).today(today).reservations(reservations).showListingImage(this.showListingImage).clickListener(CalendarThumbnailsAdapter$$Lambda$1.lambdaFactory$(this, listing));
    }
}
