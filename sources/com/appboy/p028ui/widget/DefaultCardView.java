package com.appboy.p028ui.widget;

import android.content.Context;
import com.appboy.Constants;
import com.appboy.models.cards.Card;
import com.appboy.support.AppboyLogger;
import com.appboy.ui.R;

/* renamed from: com.appboy.ui.widget.DefaultCardView */
public class DefaultCardView extends BaseCardView<Card> {
    private static final String TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, DefaultCardView.class.getName()});

    public DefaultCardView(Context context) {
        this(context, null);
    }

    public DefaultCardView(Context context, Card card) {
        super(context);
        if (card != null) {
            setCard(card);
        }
    }

    /* access modifiers changed from: protected */
    public int getLayoutResource() {
        return R.layout.com_appboy_default_card;
    }

    public void onSetCard(Card card) {
        AppboyLogger.m1739w(TAG, "onSetCard called for blank view with: " + card.toString());
    }
}
