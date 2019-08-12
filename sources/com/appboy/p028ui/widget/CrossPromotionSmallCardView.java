package com.appboy.p028ui.widget;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import com.appboy.models.cards.CrossPromotionSmallCard;
import com.appboy.p028ui.actions.GooglePlayAppDetailsAction;
import com.appboy.p028ui.actions.IAction;
import com.appboy.support.StringUtils;
import com.appboy.ui.R;
import com.facebook.drawee.view.SimpleDraweeView;
import java.text.NumberFormat;
import java.util.Locale;

/* renamed from: com.appboy.ui.widget.CrossPromotionSmallCardView */
public class CrossPromotionSmallCardView extends BaseCardView<CrossPromotionSmallCard> {
    /* access modifiers changed from: private */
    public static final String TAG = String.format("%s.%s", new Object[]{"Appboy", CrossPromotionSmallCardView.class.getName()});
    private final float mAspectRatio;
    private final TextView mCaption;
    private SimpleDraweeView mDrawee;
    private ImageView mImage;
    private final Button mPrice;
    /* access modifiers changed from: private */
    public IAction mPriceAction;
    private final TextView mReviewCount;
    private final StarRatingView mStarRating;
    private final TextView mSubtitle;
    private final TextView mTitle;

    public CrossPromotionSmallCardView(Context context) {
        this(context, null);
    }

    public CrossPromotionSmallCardView(Context context, CrossPromotionSmallCard card) {
        super(context);
        this.mAspectRatio = 1.0f;
        this.mTitle = (TextView) findViewById(R.id.com_appboy_cross_promotion_small_card_title);
        this.mSubtitle = (TextView) findViewById(R.id.com_appboy_cross_promotion_small_card_subtitle);
        this.mReviewCount = (TextView) findViewById(R.id.com_appboy_cross_promotion_small_card_review_count);
        this.mCaption = (TextView) findViewById(R.id.com_appboy_cross_promotion_small_card_recommendation_tab);
        this.mStarRating = (StarRatingView) findViewById(R.id.com_appboy_cross_promotion_small_card_star_rating);
        this.mPrice = (Button) findViewById(R.id.com_appboy_cross_promotion_small_card_price);
        if (canUseFresco()) {
            this.mDrawee = (SimpleDraweeView) getProperViewFromInflatedStub(R.id.com_appboy_cross_promotion_small_card_drawee_stub);
        } else {
            this.mImage = (ImageView) getProperViewFromInflatedStub(R.id.com_appboy_cross_promotion_small_card_imageview_stub);
            this.mImage.setScaleType(ScaleType.CENTER_CROP);
            this.mImage.setAdjustViewBounds(true);
        }
        if (card != null) {
            setCard(card);
        }
    }

    /* access modifiers changed from: protected */
    public int getLayoutResource() {
        return R.layout.com_appboy_cross_promotion_small_card;
    }

    public void onSetCard(final CrossPromotionSmallCard card) {
        this.mTitle.setText(card.getTitle());
        if (card.getSubtitle() == null || card.getSubtitle().toUpperCase(Locale.getDefault()).equals("NULL")) {
            this.mSubtitle.setVisibility(8);
        } else {
            this.mSubtitle.setText(card.getSubtitle().toUpperCase(Locale.getDefault()));
        }
        this.mCaption.setText(card.getCaption().toUpperCase(Locale.getDefault()));
        if (card.getRating() <= 0.0d) {
            this.mReviewCount.setVisibility(8);
            this.mStarRating.setVisibility(8);
        } else {
            this.mReviewCount.setText(String.format("(%s)", new Object[]{NumberFormat.getInstance().format((long) card.getReviewCount())}));
            this.mStarRating.setRating((float) card.getRating());
        }
        if (!StringUtils.isNullOrBlank(card.getDisplayPrice())) {
            this.mPrice.setText(card.getDisplayPrice());
        } else {
            this.mPrice.setText(getPriceString(card.getPrice()));
        }
        this.mPriceAction = new GooglePlayAppDetailsAction(card.getPackage(), false, card.getAppStore(), card.getKindleId());
        this.mPrice.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                BaseCardView.handleCardClick(CrossPromotionSmallCardView.this.mContext, card, CrossPromotionSmallCardView.this.mPriceAction, CrossPromotionSmallCardView.TAG);
            }
        });
        if (canUseFresco()) {
            setSimpleDraweeToUrl(this.mDrawee, card.getImageUrl(), 1.0f, true);
        } else {
            setImageViewToUrl(this.mImage, card.getImageUrl(), 1.0f);
        }
    }

    private String getPriceString(double price) {
        if (price == 0.0d) {
            return this.mContext.getString(R.string.com_appboy_recommendation_free);
        }
        return NumberFormat.getCurrencyInstance(Locale.US).format(price);
    }
}
