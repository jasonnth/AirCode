package com.appboy.p028ui.widget;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import com.appboy.models.cards.CaptionedImageCard;
import com.appboy.p028ui.actions.ActionFactory;
import com.appboy.p028ui.actions.IAction;
import com.appboy.ui.R;
import com.facebook.drawee.view.SimpleDraweeView;

/* renamed from: com.appboy.ui.widget.CaptionedImageCardView */
public class CaptionedImageCardView extends BaseCardView<CaptionedImageCard> {
    /* access modifiers changed from: private */
    public static final String TAG = String.format("%s.%s", new Object[]{"Appboy", CaptionedImageCardView.class.getName()});
    private float mAspectRatio;
    /* access modifiers changed from: private */
    public IAction mCardAction;
    private final TextView mDescription;
    private final TextView mDomain;
    private SimpleDraweeView mDrawee;
    private ImageView mImage;
    private final TextView mTitle;

    public CaptionedImageCardView(Context context) {
        this(context, null);
    }

    public CaptionedImageCardView(Context context, CaptionedImageCard card) {
        super(context);
        this.mAspectRatio = 1.3333334f;
        if (canUseFresco()) {
            this.mDrawee = (SimpleDraweeView) getProperViewFromInflatedStub(R.id.com_appboy_captioned_image_card_drawee_stub);
        } else {
            this.mImage = (ImageView) getProperViewFromInflatedStub(R.id.com_appboy_captioned_image_card_imageview_stub);
            this.mImage.setScaleType(ScaleType.CENTER_CROP);
            this.mImage.setAdjustViewBounds(true);
        }
        this.mTitle = (TextView) findViewById(R.id.com_appboy_captioned_image_title);
        this.mDescription = (TextView) findViewById(R.id.com_appboy_captioned_image_description);
        this.mDomain = (TextView) findViewById(R.id.com_appboy_captioned_image_card_domain);
        if (card != null) {
            setCard(card);
        }
        safeSetBackground(getResources().getDrawable(R.drawable.com_appboy_card_background));
    }

    /* access modifiers changed from: protected */
    public int getLayoutResource() {
        return R.layout.com_appboy_captioned_image_card;
    }

    public void onSetCard(final CaptionedImageCard card) {
        this.mTitle.setText(card.getTitle());
        this.mDescription.setText(card.getDescription());
        setOptionalTextView(this.mDomain, card.getDomain());
        this.mCardAction = ActionFactory.createUriAction(getContext(), card.getUrl());
        boolean respectAspectRatio = false;
        if (card.getAspectRatio() != 0.0f) {
            this.mAspectRatio = card.getAspectRatio();
            respectAspectRatio = true;
        }
        setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                BaseCardView.handleCardClick(CaptionedImageCardView.this.mContext, card, CaptionedImageCardView.this.mCardAction, CaptionedImageCardView.TAG);
            }
        });
        if (canUseFresco()) {
            setSimpleDraweeToUrl(this.mDrawee, card.getImageUrl(), this.mAspectRatio, respectAspectRatio);
        } else {
            setImageViewToUrl(this.mImage, card.getImageUrl(), this.mAspectRatio, respectAspectRatio);
        }
    }
}
