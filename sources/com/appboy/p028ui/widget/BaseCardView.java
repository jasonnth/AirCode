package com.appboy.p028ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;
import com.appboy.Appboy;
import com.appboy.Constants;
import com.appboy.configuration.AppboyConfigurationProvider;
import com.appboy.models.cards.Card;
import com.appboy.p028ui.actions.IAction;
import com.appboy.p028ui.feed.AppboyFeedManager;
import com.appboy.p028ui.feed.AppboyImageSwitcher;
import com.appboy.p028ui.support.FrescoLibraryUtils;
import com.appboy.p028ui.support.ViewUtils;
import com.appboy.support.AppboyLogger;
import com.appboy.ui.R;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.Observable;
import java.util.Observer;

/* renamed from: com.appboy.ui.widget.BaseCardView */
public abstract class BaseCardView<T extends Card> extends RelativeLayout implements Observer {
    private static final String TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, BaseCardView.class.getName()});
    private static Boolean unreadCardVisualIndicatorOn;
    private final boolean mCanUseFresco;
    protected T mCard;
    protected final Context mContext;
    protected AppboyImageSwitcher mImageSwitcher = ((AppboyImageSwitcher) findViewById(R.id.com_appboy_newsfeed_item_read_indicator_image_switcher));

    /* access modifiers changed from: protected */
    public abstract int getLayoutResource();

    /* access modifiers changed from: protected */
    public abstract void onSetCard(T t);

    public BaseCardView(Context context) {
        super(context);
        this.mCanUseFresco = FrescoLibraryUtils.canUseFresco(context);
        this.mContext = context;
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(getLayoutResource(), this);
        if (this.mImageSwitcher != null) {
            this.mImageSwitcher.setFactory(new ViewFactory() {
                public View makeView() {
                    return new ImageView(BaseCardView.this.mContext.getApplicationContext());
                }
            });
        }
        if (unreadCardVisualIndicatorOn == null) {
            unreadCardVisualIndicatorOn = Boolean.valueOf(new AppboyConfigurationProvider(context).getIsNewsfeedVisualIndicatorOn());
        }
        if (!unreadCardVisualIndicatorOn.booleanValue() && this.mImageSwitcher != null) {
            this.mImageSwitcher.setVisibility(8);
        }
    }

    public void update(Observable observable, Object data) {
        setCardViewedIndicator();
    }

    private void setCardViewedIndicator() {
        if (getCard() == null) {
            AppboyLogger.m1733d(TAG, "The card is null.");
        } else if (this.mImageSwitcher != null) {
            AppboyLogger.m1733d(TAG, "Setting the read/unread indicator for the card.");
            if (getCard().isRead()) {
                if (this.mImageSwitcher.getReadIcon() != null) {
                    this.mImageSwitcher.setImageDrawable(this.mImageSwitcher.getReadIcon());
                } else {
                    this.mImageSwitcher.setImageResource(R.drawable.icon_read);
                }
                this.mImageSwitcher.setTag("icon_read");
            } else if (this.mImageSwitcher.getUnReadIcon() != null) {
                this.mImageSwitcher.setImageDrawable(this.mImageSwitcher.getUnReadIcon());
            } else {
                this.mImageSwitcher.setImageResource(R.drawable.icon_unread);
                this.mImageSwitcher.setTag("icon_unread");
            }
        }
    }

    public void setCard(T card) {
        this.mCard = card;
        onSetCard(card);
        card.addObserver(this);
        setCardViewedIndicator();
    }

    public Card getCard() {
        return this.mCard;
    }

    /* access modifiers changed from: 0000 */
    public void setOptionalTextView(TextView view, String value) {
        if (value == null || value.trim().equals("")) {
            view.setText("");
            view.setVisibility(8);
            return;
        }
        view.setText(value);
        view.setVisibility(0);
    }

    /* access modifiers changed from: 0000 */
    public void safeSetBackground(Drawable background) {
        if (VERSION.SDK_INT < 16) {
            setBackgroundDrawable(background);
        } else {
            setBackgroundNew(background);
        }
    }

    @TargetApi(16)
    private void setBackgroundNew(Drawable background) {
        setBackground(background);
    }

    /* access modifiers changed from: 0000 */
    public void setImageViewToUrl(ImageView imageView, String imageUrl, float aspectRatio) {
        setImageViewToUrl(imageView, imageUrl, aspectRatio, true);
    }

    /* access modifiers changed from: 0000 */
    public void setImageViewToUrl(final ImageView imageView, String imageUrl, final float aspectRatio, boolean respectAspectRatio) {
        if (imageUrl == null) {
            AppboyLogger.m1739w(TAG, "The image url to render is null. Not setting the card image.");
        } else if (aspectRatio == 0.0f) {
            AppboyLogger.m1739w(TAG, "The image aspect ratio is 0. Not setting the card image.");
        } else if (!imageUrl.equals(imageView.getTag())) {
            if (aspectRatio != 1.0f) {
                ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
                if (viewTreeObserver.isAlive()) {
                    viewTreeObserver.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                        public void onGlobalLayout() {
                            int width = imageView.getWidth();
                            imageView.setLayoutParams(new LayoutParams(width, (int) (((float) width) / aspectRatio)));
                            ViewUtils.removeOnGlobalLayoutListenerSafe(imageView.getViewTreeObserver(), this);
                        }
                    });
                }
            }
            imageView.setImageResource(17170445);
            Appboy.getInstance(getContext()).fetchAndRenderImage(imageUrl, imageView, respectAspectRatio);
            imageView.setTag(imageUrl);
        }
    }

    /* access modifiers changed from: 0000 */
    public void setSimpleDraweeToUrl(SimpleDraweeView simpleDraweeView, String imageUrl, float aspectRatio, boolean respectAspectRatio) {
        if (imageUrl == null) {
            AppboyLogger.m1739w(TAG, "The image url to render is null. Not setting the card image.");
        } else {
            FrescoLibraryUtils.setDraweeControllerHelper(simpleDraweeView, imageUrl, aspectRatio, respectAspectRatio);
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean canUseFresco() {
        return this.mCanUseFresco;
    }

    protected static void handleCardClick(Context context, Card card, IAction cardAction, String tag) {
        handleCardClick(context, card, cardAction, tag, true);
    }

    protected static void handleCardClick(Context context, Card card, IAction cardAction, String tag, boolean markAsRead) {
        if (markAsRead) {
            card.setIsRead(true);
        }
        if (cardAction != null) {
            if (card.logClick()) {
                AppboyLogger.m1733d(tag, String.format("Logged click for card %s", new Object[]{card.getId()}));
            } else {
                AppboyLogger.m1733d(tag, String.format("Logging click failed for card %s", new Object[]{card.getId()}));
            }
            if (!AppboyFeedManager.getInstance().getFeedCardClickActionListener().onFeedCardClicked(context, card, cardAction)) {
                cardAction.execute(context);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public View getProperViewFromInflatedStub(int stubLayoutId) {
        ((ViewStub) findViewById(stubLayoutId)).inflate();
        if (this.mCanUseFresco) {
            return findViewById(R.id.com_appboy_stubbed_feed_drawee_view);
        }
        return findViewById(R.id.com_appboy_stubbed_feed_image_view);
    }
}
