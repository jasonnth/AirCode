package com.appboy.p028ui.widget;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.appboy.models.cards.BannerImageCard;
import com.appboy.p028ui.actions.ActionFactory;
import com.appboy.p028ui.actions.IAction;
import com.appboy.ui.R;
import com.facebook.drawee.view.SimpleDraweeView;

/* renamed from: com.appboy.ui.widget.BannerImageCardView */
public class BannerImageCardView extends BaseCardView<BannerImageCard> {
    /* access modifiers changed from: private */
    public static final String TAG = String.format("%s.%s", new Object[]{"Appboy", BannerImageCardView.class.getName()});
    private float mAspectRatio;
    /* access modifiers changed from: private */
    public IAction mCardAction;
    private SimpleDraweeView mDrawee;
    private ImageView mImage;

    public BannerImageCardView(Context context) {
        this(context, null);
    }

    public BannerImageCardView(Context context, BannerImageCard card) {
        super(context);
        this.mAspectRatio = 6.0f;
        if (canUseFresco()) {
            this.mDrawee = (SimpleDraweeView) getProperViewFromInflatedStub(R.id.com_appboy_banner_image_card_drawee_stub);
        } else {
            this.mImage = (ImageView) getProperViewFromInflatedStub(R.id.com_appboy_banner_image_card_imageview_stub);
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
        return R.layout.com_appboy_banner_image_card;
    }

    public void onSetCard(final BannerImageCard card) {
        boolean respectAspectRatio = false;
        if (card.getAspectRatio() != 0.0f) {
            this.mAspectRatio = card.getAspectRatio();
            respectAspectRatio = true;
        }
        if (canUseFresco()) {
            setSimpleDraweeToUrl(this.mDrawee, card.getImageUrl(), this.mAspectRatio, respectAspectRatio);
        } else {
            setImageViewToUrl(this.mImage, card.getImageUrl(), this.mAspectRatio, respectAspectRatio);
        }
        this.mCardAction = ActionFactory.createUriAction(getContext(), card.getUrl());
        setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                BaseCardView.handleCardClick(BannerImageCardView.this.mContext, card, BannerImageCardView.this.mCardAction, BannerImageCardView.TAG, false);
            }
        });
    }
}
