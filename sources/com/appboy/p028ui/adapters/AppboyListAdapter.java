package com.appboy.p028ui.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.appboy.Constants;
import com.appboy.models.cards.BannerImageCard;
import com.appboy.models.cards.CaptionedImageCard;
import com.appboy.models.cards.Card;
import com.appboy.models.cards.CrossPromotionSmallCard;
import com.appboy.models.cards.ShortNewsCard;
import com.appboy.models.cards.TextAnnouncementCard;
import com.appboy.p028ui.widget.BannerImageCardView;
import com.appboy.p028ui.widget.BaseCardView;
import com.appboy.p028ui.widget.CaptionedImageCardView;
import com.appboy.p028ui.widget.CrossPromotionSmallCardView;
import com.appboy.p028ui.widget.DefaultCardView;
import com.appboy.p028ui.widget.ShortNewsCardView;
import com.appboy.p028ui.widget.TextAnnouncementCardView;
import com.appboy.support.AppboyLogger;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* renamed from: com.appboy.ui.adapters.AppboyListAdapter */
public class AppboyListAdapter extends ArrayAdapter<Card> {
    private static final String TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, AppboyListAdapter.class.getName()});
    private final Set<String> mCardIdImpressions = new HashSet();
    private final Context mContext;

    public AppboyListAdapter(Context context, int layoutResourceId, List<Card> cards) {
        super(context, layoutResourceId, cards);
        this.mContext = context;
    }

    public int getViewTypeCount() {
        return 8;
    }

    public int getItemViewType(int position) {
        Card card = (Card) getItem(position);
        if (card instanceof BannerImageCard) {
            return 1;
        }
        if (card instanceof CaptionedImageCard) {
            return 2;
        }
        if (card instanceof CrossPromotionSmallCard) {
            return 3;
        }
        if (card instanceof ShortNewsCard) {
            return 4;
        }
        if (card instanceof TextAnnouncementCard) {
            return 5;
        }
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        BaseCardView view;
        Card card = (Card) getItem(position);
        if (convertView != null) {
            AppboyLogger.m1733d(TAG, "Reusing convertView for rendering of item " + position);
            view = (BaseCardView) convertView;
        } else if (card instanceof BannerImageCard) {
            view = new BannerImageCardView(this.mContext);
        } else if (card instanceof CaptionedImageCard) {
            view = new CaptionedImageCardView(this.mContext);
        } else if (card instanceof CrossPromotionSmallCard) {
            view = new CrossPromotionSmallCardView(this.mContext);
        } else if (card instanceof ShortNewsCard) {
            view = new ShortNewsCardView(this.mContext);
        } else if (card instanceof TextAnnouncementCard) {
            view = new TextAnnouncementCardView(this.mContext);
        } else {
            view = new DefaultCardView(this.mContext);
        }
        AppboyLogger.m1733d(TAG, String.format("Using view of type: %s for card at position %d: %s", new Object[]{view.getClass().getName(), Integer.valueOf(position), card.toString()}));
        view.setCard(card);
        logCardImpression(card);
        return view;
    }

    public synchronized void replaceFeed(List<Card> cards) {
        setNotifyOnChange(false);
        if (cards == null) {
            clear();
            notifyDataSetChanged();
        } else {
            AppboyLogger.m1733d(TAG, String.format("Replacing existing feed of %d cards with new feed containing %d cards.", new Object[]{Integer.valueOf(getCount()), Integer.valueOf(cards.size())}));
            int i = 0;
            int j = 0;
            int newFeedSize = cards.size();
            while (i < getCount()) {
                Card existingCard = (Card) getItem(i);
                Card newCard = null;
                if (j < newFeedSize) {
                    newCard = (Card) cards.get(j);
                }
                if (newCard == null || !newCard.isEqualToCard(existingCard)) {
                    remove(existingCard);
                } else {
                    i++;
                    j++;
                }
            }
            if (VERSION.SDK_INT < 11) {
                while (j < newFeedSize) {
                    add((Card) cards.get(j));
                    j++;
                }
            } else {
                addAllBatch(cards.subList(j, newFeedSize));
            }
            notifyDataSetChanged();
        }
    }

    public synchronized void add(Card card) {
        super.add(card);
    }

    @TargetApi(11)
    private synchronized void addAllBatch(Collection<Card> cards) {
        super.addAll(cards);
    }

    public void resetCardImpressionTracker() {
        this.mCardIdImpressions.clear();
    }

    private void logCardImpression(Card card) {
        String cardId = card.getId();
        if (!this.mCardIdImpressions.contains(cardId)) {
            this.mCardIdImpressions.add(cardId);
            card.logImpression();
            AppboyLogger.m1733d(TAG, String.format("Logged impression for card %s", new Object[]{cardId}));
        } else {
            AppboyLogger.m1733d(TAG, String.format("Already counted impression for card %s", new Object[]{cardId}));
        }
        if (!card.getViewed()) {
            card.setViewed(true);
        }
    }

    public void batchSetCardsToRead(int startIndex, int endIndex) {
        if (getCount() == 0) {
            AppboyLogger.m1733d(TAG, "mAdapter is empty in setting some cards to viewed.");
            return;
        }
        int startIndex2 = Math.max(0, startIndex);
        int endIndex2 = Math.min(getCount(), endIndex);
        for (int traversalIndex = startIndex2; traversalIndex < endIndex2; traversalIndex++) {
            Card card = (Card) getItem(traversalIndex);
            if (card == null) {
                AppboyLogger.m1733d(TAG, "Card was null in setting some cards to viewed.");
                return;
            }
            if (!card.isRead()) {
                card.setIsRead(true);
            }
        }
    }
}
