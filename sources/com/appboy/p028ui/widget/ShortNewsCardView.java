package com.appboy.p028ui.widget;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import com.appboy.models.cards.ShortNewsCard;
import com.appboy.p028ui.actions.ActionFactory;
import com.appboy.p028ui.actions.IAction;
import com.appboy.ui.R;
import com.facebook.drawee.view.SimpleDraweeView;

/* renamed from: com.appboy.ui.widget.ShortNewsCardView */
public class ShortNewsCardView extends BaseCardView<ShortNewsCard> {
    /* access modifiers changed from: private */
    public static final String TAG = String.format("%s.%s", new Object[]{"Appboy", ShortNewsCardView.class.getName()});
    private final float mAspectRatio;
    /* access modifiers changed from: private */
    public IAction mCardAction;
    private final TextView mDescription;
    private final TextView mDomain;
    private SimpleDraweeView mDrawee;
    private ImageView mImage;
    private final TextView mTitle;

    public ShortNewsCardView(Context context) {
        this(context, null);
    }

    public ShortNewsCardView(Context context, ShortNewsCard card) {
        super(context);
        this.mAspectRatio = 1.0f;
        this.mDescription = (TextView) findViewById(R.id.com_appboy_short_news_card_description);
        this.mTitle = (TextView) findViewById(R.id.com_appboy_short_news_card_title);
        this.mDomain = (TextView) findViewById(R.id.com_appboy_short_news_card_domain);
        if (canUseFresco()) {
            this.mDrawee = (SimpleDraweeView) getProperViewFromInflatedStub(R.id.com_appboy_short_news_card_drawee_stub);
        } else {
            this.mImage = (ImageView) getProperViewFromInflatedStub(R.id.com_appboy_short_news_card_imageview_stub);
            this.mImage.setScaleType(ScaleType.CENTER_CROP);
            this.mImage.setAdjustViewBounds(true);
        }
        if (card != null) {
            setCard(card);
        }
        safeSetBackground(getResources().getDrawable(R.drawable.com_appboy_card_background));
    }

    /* access modifiers changed from: protected */
    public int getLayoutResource() {
        return R.layout.com_appboy_short_news_card;
    }

    public void onSetCard(final ShortNewsCard card) {
        this.mDescription.setText(card.getDescription());
        setOptionalTextView(this.mTitle, card.getTitle());
        setOptionalTextView(this.mDomain, card.getDomain());
        this.mCardAction = ActionFactory.createUriAction(getContext(), card.getUrl());
        setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                BaseCardView.handleCardClick(ShortNewsCardView.this.mContext, card, ShortNewsCardView.this.mCardAction, ShortNewsCardView.TAG);
            }
        });
        if (canUseFresco()) {
            setSimpleDraweeToUrl(this.mDrawee, card.getImageUrl(), 1.0f, true);
        } else {
            setImageViewToUrl(this.mImage, card.getImageUrl(), 1.0f);
        }
    }
}
