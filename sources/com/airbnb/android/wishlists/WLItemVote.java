package com.airbnb.android.wishlists;

import com.airbnb.android.core.models.User;
import com.airbnb.android.core.wishlists.WishListItem;

public enum WLItemVote {
    Up,
    Down,
    None;

    public static WLItemVote getVote(WishListItem item, User user) {
        if (item.getUpVotes().contains(Long.valueOf(user.getId()))) {
            return Up;
        }
        if (item.getDownVotes().contains(Long.valueOf(user.getId()))) {
            return Down;
        }
        return None;
    }

    public static void setVoteForUser(WishListItem item, WLItemVote vote, User user) {
        long userId = user.getId();
        item.getUpVotes().remove(Long.valueOf(userId));
        item.getDownVotes().remove(Long.valueOf(userId));
        if (vote == Down) {
            item.getDownVotes().add(Long.valueOf(userId));
        } else if (vote == Up) {
            item.getUpVotes().add(Long.valueOf(userId));
        }
    }
}
