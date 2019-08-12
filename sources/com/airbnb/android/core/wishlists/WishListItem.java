package com.airbnb.android.core.wishlists;

import android.os.Parcelable;
import java.util.List;

public interface WishListItem extends Parcelable {
    List<Long> getDownVotes();

    long getId();

    long getItemId();

    WishListableType getItemType();

    List<Long> getUpVotes();

    boolean isAvailable();
}
