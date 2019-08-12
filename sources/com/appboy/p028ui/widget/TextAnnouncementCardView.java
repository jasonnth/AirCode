package com.appboy.p028ui.widget;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.appboy.models.cards.TextAnnouncementCard;
import com.appboy.p028ui.actions.ActionFactory;
import com.appboy.p028ui.actions.IAction;
import com.appboy.ui.R;

/* renamed from: com.appboy.ui.widget.TextAnnouncementCardView */
public class TextAnnouncementCardView extends BaseCardView<TextAnnouncementCard> {
    /* access modifiers changed from: private */
    public static final String TAG = String.format("%s.%s", new Object[]{"Appboy", TextAnnouncementCardView.class.getName()});
    /* access modifiers changed from: private */
    public IAction mCardAction;
    private final TextView mDescription;
    private final TextView mDomain;
    private final TextView mTitle;

    public TextAnnouncementCardView(Context context) {
        this(context, null);
    }

    public TextAnnouncementCardView(Context context, TextAnnouncementCard card) {
        super(context);
        this.mTitle = (TextView) findViewById(R.id.com_appboy_text_announcement_card_title);
        this.mDescription = (TextView) findViewById(R.id.com_appboy_text_announcement_card_description);
        this.mDomain = (TextView) findViewById(R.id.com_appboy_text_announcement_card_domain);
        if (card != null) {
            setCard(card);
        }
        safeSetBackground(getResources().getDrawable(R.drawable.com_appboy_card_background));
    }

    /* access modifiers changed from: protected */
    public int getLayoutResource() {
        return R.layout.com_appboy_text_announcement_card;
    }

    public void onSetCard(final TextAnnouncementCard card) {
        this.mTitle.setText(card.getTitle());
        this.mDescription.setText(card.getDescription());
        setOptionalTextView(this.mDomain, card.getDomain());
        this.mCardAction = ActionFactory.createUriAction(getContext(), card.getUrl());
        setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                BaseCardView.handleCardClick(TextAnnouncementCardView.this.mContext, card, TextAnnouncementCardView.this.mCardAction, TextAnnouncementCardView.TAG);
            }
        });
    }
}
