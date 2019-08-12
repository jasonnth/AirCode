package com.airbnb.android.core.events;

import com.airbnb.android.core.models.Listing;

public class ListingEvent {
    public final Listing listing;

    public static class ListingUpdateFailedEvent extends ListingEvent {
        public ListingUpdateFailedEvent(Listing listing) {
            super(listing);
        }
    }

    public static class ListingUpdatedEvent extends ListingEvent {
        public ListingUpdatedEvent(Listing listing) {
            super(listing);
        }
    }

    public static class ListingCreatedEvent extends ListingEvent {
        public ListingCreatedEvent(Listing listing) {
            super(listing);
        }
    }

    public static class ListingDeletedEvent extends ListingEvent {
        public ListingDeletedEvent(Listing listing) {
            super(listing);
        }
    }

    public static class ListingListedEvent extends ListingEvent {
        public ListingListedEvent(Listing listing) {
            super(listing);
        }
    }

    public ListingEvent(Listing listing2) {
        this.listing = listing2;
    }
}
