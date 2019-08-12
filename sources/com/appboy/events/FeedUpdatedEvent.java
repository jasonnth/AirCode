package com.appboy.events;

import com.appboy.enums.CardCategory;
import com.appboy.models.cards.Card;
import com.appboy.support.AppboyLogger;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

public final class FeedUpdatedEvent {

    /* renamed from: a */
    private static final String f2779a = AppboyLogger.getAppboyLogTag(FeedUpdatedEvent.class);

    /* renamed from: b */
    private final List<Card> f2780b;

    /* renamed from: c */
    private final String f2781c;

    /* renamed from: d */
    private final boolean f2782d;

    /* renamed from: e */
    private final long f2783e;

    public FeedUpdatedEvent(List<Card> feedCards, String userId, boolean isFromOfflineStorage, long timestamp) {
        this.f2781c = userId;
        this.f2782d = isFromOfflineStorage;
        if (feedCards == null) {
            throw new NullPointerException();
        }
        this.f2780b = feedCards;
        this.f2783e = timestamp;
    }

    public boolean isFromOfflineStorage() {
        return this.f2782d;
    }

    public List<Card> getFeedCards(EnumSet<CardCategory> categories) {
        if (categories == null) {
            try {
                AppboyLogger.m1737i(f2779a, "The categories passed to getFeedCards are null, FeedUpdatedEvent is going to return all the cards in cache.");
                categories = CardCategory.getAllCategories();
            } catch (Exception e) {
                AppboyLogger.m1740w(f2779a, String.format("Unable to get cards with categories[%s]. Ignoring.", new Object[]{categories}), e);
                return null;
            }
        }
        if (categories.isEmpty()) {
            AppboyLogger.m1739w(f2779a, "The parameter passed into categories is not valid, Appboy is returning an empty card list.Please pass in a non-empty EnumSet of CardCategory for getFeedCards().");
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (Card card : this.f2780b) {
            if (card.isInCategorySet(categories) && !card.isExpired()) {
                arrayList.add(card);
            }
        }
        return arrayList;
    }

    public int getCardCount(EnumSet<CardCategory> categories) {
        if (categories == null) {
            AppboyLogger.m1737i(f2779a, "The categories passed into getCardCount are null, FeedUpdatedEvent is going to return the count of all the cards in cache.");
            return this.f2780b.size();
        } else if (!categories.isEmpty()) {
            return getFeedCards(categories).size();
        } else {
            AppboyLogger.m1739w(f2779a, "The parameters passed into categories are not valid, Appboy is returning 0 in getCardCount().Please pass in a non-empty EnumSet of CardCategory.");
            return 0;
        }
    }

    public int getUnreadCardCount(EnumSet<CardCategory> categories) {
        int i = 0;
        if (categories == null) {
            AppboyLogger.m1739w(f2779a, "The categories passed to getUnreadCardCount are null, FeedUpdatedEvent is going to return the count of all the unread cards in cache.");
            return getUnreadCardCount(CardCategory.getAllCategories());
        } else if (categories.isEmpty()) {
            AppboyLogger.m1739w(f2779a, "The parameters passed into categories are Empty, Appboy is returning 0 in getUnreadCardCount().Please pass in a non-empty EnumSet of CardCategory.");
            return 0;
        } else {
            Iterator it = this.f2780b.iterator();
            while (true) {
                int i2 = i;
                if (!it.hasNext()) {
                    return i2;
                }
                Card card = (Card) it.next();
                if (card.isInCategorySet(categories) && !card.getViewed() && !card.isExpired()) {
                    i2++;
                }
                i = i2;
            }
        }
    }

    public long lastUpdatedInSecondsFromEpoch() {
        return this.f2783e;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("FeedUpdatedEvent{");
        sb.append("mFeedCards=").append(this.f2780b);
        sb.append(", mUserId='").append(this.f2781c).append('\'');
        sb.append(", mFromOfflineStorage=").append(this.f2782d);
        sb.append(", mTimestamp=").append(this.f2783e);
        sb.append('}');
        return sb.toString();
    }
}
