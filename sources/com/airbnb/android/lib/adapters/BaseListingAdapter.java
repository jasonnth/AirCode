package com.airbnb.android.lib.adapters;

import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.BaseAdapter;
import com.airbnb.android.core.models.Listing;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseListingAdapter extends BaseAdapter {
    private static final String TAG = BaseListingAdapter.class.getSimpleName();
    protected List<Listing> mListings = new ArrayList();
    private boolean mRemainingData;

    public abstract void cancelAdditionalLoading();

    /* access modifiers changed from: protected */
    @SuppressLint({"NewApi"})
    public abstract void loadMoreListings();

    public BaseListingAdapter(boolean allowPagination) {
        this.mRemainingData = allowPagination;
    }

    public int getCount() {
        return (this.mRemainingData ? 1 : 0) + this.mListings.size();
    }

    public Object getItem(int position) {
        return this.mListings.get(position);
    }

    public Listing getItemAsListing(int position) {
        return (Listing) getItem(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public void setData(List<Listing> listings) {
        if (listings == null) {
            this.mListings.clear();
        } else {
            this.mListings = listings;
        }
        notifyDataSetChanged();
    }

    /* access modifiers changed from: protected */
    public void addData(List<Listing> listings) {
        if (listings.removeAll(this.mListings)) {
            Log.d(TAG, "Removed duplicated listing(s) from adapter");
        }
        this.mListings.addAll(listings);
        notifyDataSetChanged();
    }

    /* access modifiers changed from: protected */
    public boolean hasRemainingData() {
        return this.mRemainingData;
    }

    public void setRemainingData(boolean remainingData) {
        this.mRemainingData = remainingData;
        notifyDataSetChanged();
    }

    public boolean updateListing(Listing listing) {
        for (int i = 0; i < this.mListings.size(); i++) {
            if (((Listing) this.mListings.get(i)).getId() == listing.getId()) {
                this.mListings.set(i, listing);
                notifyDataSetChanged();
                return true;
            }
        }
        return false;
    }

    public void addListing(Listing listing) {
        this.mListings.add(listing);
        notifyDataSetChanged();
    }

    public void addListings(List<Listing> listings) {
        this.mListings.removeAll(listings);
        this.mListings.addAll(listings);
        notifyDataSetChanged();
    }

    public boolean removeListing(long listingId) {
        for (int i = 0; i < this.mListings.size(); i++) {
            if (((Listing) this.mListings.get(i)).getId() == listingId) {
                this.mListings.remove(i);
                notifyDataSetChanged();
                return true;
            }
        }
        return false;
    }

    public void clearListings() {
        this.mListings.clear();
        notifyDataSetChanged();
    }

    public boolean isEnabled(int position) {
        if (position >= this.mListings.size()) {
            return false;
        }
        return true;
    }
}
