package com.appboy.p028ui.feed.listeners;

import android.content.Context;
import com.appboy.models.cards.Card;
import com.appboy.p028ui.actions.IAction;

/* renamed from: com.appboy.ui.feed.listeners.AppboyDefaultFeedClickActionListener */
public class AppboyDefaultFeedClickActionListener implements IFeedClickActionListener {
    public boolean onFeedCardClicked(Context context, Card card, IAction cardAction) {
        return false;
    }
}
