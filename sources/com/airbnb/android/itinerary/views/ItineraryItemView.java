package com.airbnb.android.itinerary.views;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.support.p000v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.itinerary.C5755R;
import com.airbnb.android.itinerary.data.models.FlightEntryPointItem;
import com.airbnb.android.itinerary.data.models.SecondaryActionButtonType;
import com.airbnb.android.itinerary.utils.ItineraryUtils;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.p027n2.components.RecommendationRow;
import com.airbnb.p027n2.components.RecommendationRow.Recommendation;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.airbnb.paris.Paris;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ItineraryItemView extends LinearLayout {
    @BindView
    RecommendationRow bottomRecommendationRow;
    @BindDimen
    int cardMargin;
    @BindViews
    List<TripCardView> cards;
    @BindView
    FrameLayout cardsContainer;
    @BindView
    AirTextView contentTextContainer;
    @BindView
    FlightEntryPointCardView flightEntryPointContainer;
    @BindView
    AirTextView header;
    @BindView
    LinearLayout headerContainer;
    private TripCardView mainCard;
    @BindView
    LottieAnimationView nowIndicator;
    @BindView
    AirTextView subheader;
    @BindView
    FrameLayout suggestionsContainer;
    @BindView
    AirImageView timelineIndicator;
    @BindView
    View timelineLineBottom;
    @BindView
    View timelineLineFaded;
    @BindView
    View timelineLineTop;
    @BindView
    RecommendationRow topRecommendationRow;

    public enum HeaderPaddingType {
        NO_PADDING,
        NORMAL_PADDING,
        EXTRA_PADDING
    }

    public ItineraryItemView(Context context) {
        super(context);
        init(null);
    }

    public ItineraryItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ItineraryItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), C5755R.layout.itinerary_item_view, this);
        setOrientation(1);
        ButterKnife.bind((View) this);
        this.mainCard = (TripCardView) this.cards.get(0);
    }

    public void setHeader(CharSequence text) {
        ViewLibUtils.setVisibleIf(this.header, !TextUtils.isEmpty(text));
        this.header.setText(text);
    }

    public void setSubheader(CharSequence text) {
        setSubheader(text, false);
    }

    public void setSubheader(CharSequence text, boolean headerStyle) {
        ViewLibUtils.setVisibleIf(this.subheader, !TextUtils.isEmpty(text));
        this.subheader.setText(text);
        if (headerStyle) {
            Paris.style(this.subheader).applyTitle3PlusPlus();
        } else {
            Paris.style(this.subheader).applySmall();
        }
    }

    public void setImageUrl(String url, boolean isCheckoutCard) {
        setImageUrls(Arrays.asList(new String[]{url}), isCheckoutCard);
    }

    public void setImageUrls(List<String> url) {
        setImageUrls(url, false);
    }

    private void setImageUrls(List<String> urls, boolean isCheckoutCard) {
        boolean z;
        this.cardsContainer.setVisibility(0);
        this.suggestionsContainer.setVisibility(8);
        this.contentTextContainer.setVisibility(8);
        this.flightEntryPointContainer.setVisibility(8);
        if (!ListUtils.isEmpty((Collection<?>) urls)) {
            for (int i = 0; i < Math.min(urls.size(), this.cards.size()); i++) {
                TripCardView card = (TripCardView) this.cards.get(i);
                String url = (String) urls.get(i);
                card.setImageUrl(url);
                MarginLayoutParams layoutParams = (MarginLayoutParams) card.getLayoutParams();
                int margin = ((urls.size() - 1) - i) * this.cardMargin;
                if (margin < 0) {
                    margin = layoutParams.topMargin;
                }
                layoutParams.topMargin = margin;
                card.setLayoutParams(layoutParams);
                if (!TextUtils.isEmpty(url)) {
                    z = true;
                } else {
                    z = false;
                }
                ViewLibUtils.setVisibleIf(card, z);
            }
        } else if (!isCheckoutCard) {
            this.mainCard.setVisibility(0);
        }
    }

    public void clear() {
        for (TripCardView card : this.cards) {
            card.clear();
            card.setVisibility(8);
        }
    }

    public void setCardTitle(CharSequence text) {
        this.mainCard.setTitle(text);
    }

    public void setCardSubtitle(CharSequence text) {
        this.mainCard.setSubtitle(text);
    }

    public void setCardSubtitleCopyToast(int resId) {
        this.mainCard.setCardSubtitleCopyToast(resId);
    }

    public void setCardClickListener(OnClickListener listener) {
        this.mainCard.setOnClickListener(listener);
    }

    public void setSecondaryActionTitle(CharSequence text) {
        this.mainCard.setSecondaryActionTitle(text);
    }

    public void setSecondaryActionButtonText(String text, SecondaryActionButtonType buttonType) {
        this.mainCard.setSecondaryActionButtonText(text, buttonType);
    }

    public void setSecondaryActionButtonClickListener(OnClickListener clickListener) {
        this.mainCard.setSecondaryActionButtonClickListener(clickListener);
    }

    public void setTimelineLineColorAndIndicator(boolean isUpcoming, boolean isNextUpcoming) {
        this.timelineLineTop.setBackgroundColor(ItineraryUtils.getTimelineColor(getContext(), isUpcoming && !isNextUpcoming));
        this.timelineLineBottom.setBackgroundColor(ItineraryUtils.getTimelineColor(getContext(), isUpcoming));
        if (!isNextUpcoming) {
            this.timelineIndicator.setImageResource(isUpcoming ? C5755R.C5756drawable.itinerary_dot_grey : C5755R.C5756drawable.itinerary_dot_babu);
            this.nowIndicator.setVisibility(4);
            this.nowIndicator.cancelAnimation();
            return;
        }
        this.timelineIndicator.setColorFilter(-1);
        this.nowIndicator.setVisibility(0);
        this.nowIndicator.playAnimation();
    }

    public void setTimelineLineFadedColor(int color) {
        boolean z;
        View view = this.timelineLineFaded;
        if (color != -1) {
            z = true;
        } else {
            z = false;
        }
        ViewLibUtils.setVisibleIf(view, z);
        if (color != -1) {
            this.timelineLineFaded.setBackground(new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{color, 0}));
        }
    }

    public void setSuggestions(List<Recommendation> topRecommendations, List<Recommendation> bottomRecommendations) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5 = true;
        ViewLibUtils.setGoneIf(this.cardsContainer, !ListUtils.isEmpty((Collection<?>) topRecommendations));
        AirTextView airTextView = this.contentTextContainer;
        if (!ListUtils.isEmpty((Collection<?>) topRecommendations)) {
            z = true;
        } else {
            z = false;
        }
        ViewLibUtils.setGoneIf(airTextView, z);
        FlightEntryPointCardView flightEntryPointCardView = this.flightEntryPointContainer;
        if (!ListUtils.isEmpty((Collection<?>) topRecommendations)) {
            z2 = true;
        } else {
            z2 = false;
        }
        ViewLibUtils.setGoneIf(flightEntryPointCardView, z2);
        FrameLayout frameLayout = this.suggestionsContainer;
        if (!ListUtils.isEmpty((Collection<?>) topRecommendations)) {
            z3 = true;
        } else {
            z3 = false;
        }
        ViewLibUtils.setVisibleIf(frameLayout, z3);
        RecommendationRow recommendationRow = this.topRecommendationRow;
        if (!ListUtils.isEmpty((Collection<?>) topRecommendations)) {
            z4 = true;
        } else {
            z4 = false;
        }
        ViewLibUtils.setVisibleIf(recommendationRow, z4);
        RecommendationRow recommendationRow2 = this.bottomRecommendationRow;
        if (ListUtils.isEmpty((Collection<?>) bottomRecommendations)) {
            z5 = false;
        }
        ViewLibUtils.setVisibleIf(recommendationRow2, z5);
        if (!ListUtils.isEmpty((Collection<?>) topRecommendations)) {
            this.topRecommendationRow.setup(topRecommendations);
            if (!ListUtils.isEmpty((Collection<?>) bottomRecommendations)) {
                this.bottomRecommendationRow.setup(bottomRecommendations);
                this.bottomRecommendationRow.showBottomSpace(false);
                return;
            }
            this.topRecommendationRow.showBottomSpace(false);
        }
    }

    public void setContentText(CharSequence text) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4 = true;
        AirTextView airTextView = this.contentTextContainer;
        if (!TextUtils.isEmpty(text)) {
            z = true;
        } else {
            z = false;
        }
        ViewLibUtils.setVisibleIf(airTextView, z);
        FrameLayout frameLayout = this.suggestionsContainer;
        if (!TextUtils.isEmpty(text)) {
            z2 = true;
        } else {
            z2 = false;
        }
        ViewLibUtils.setGoneIf(frameLayout, z2);
        FrameLayout frameLayout2 = this.cardsContainer;
        if (!TextUtils.isEmpty(text)) {
            z3 = true;
        } else {
            z3 = false;
        }
        ViewLibUtils.setGoneIf(frameLayout2, z3);
        FlightEntryPointCardView flightEntryPointCardView = this.flightEntryPointContainer;
        if (TextUtils.isEmpty(text)) {
            z4 = false;
        }
        ViewLibUtils.setGoneIf(flightEntryPointCardView, z4);
        this.contentTextContainer.setText(text);
        Drawable chevron = ContextCompat.getDrawable(getContext(), C5755R.C5756drawable.n2_ic_chevron_pill);
        chevron.setColorFilter(ContextCompat.getColor(getContext(), C5755R.color.n2_babu), Mode.SRC_ATOP);
        this.contentTextContainer.setCompoundDrawablesWithIntrinsicBounds(null, null, chevron, null);
    }

    public void setContentTextClickListener(OnClickListener listener) {
        this.contentTextContainer.setOnClickListener(listener);
    }

    public void setHeaderContainerPadding(HeaderPaddingType headerPaddingType) {
        int topPadding = this.headerContainer.getPaddingTop();
        switch (headerPaddingType) {
            case NORMAL_PADDING:
                topPadding = getResources().getDimensionPixelOffset(C5755R.dimen.itinerary_header_padding_top);
                break;
            case EXTRA_PADDING:
                if (!MiscUtils.isTabletScreen(getContext())) {
                    topPadding = ViewUtils.getScreenHeight(getContext()) / 3;
                    break;
                } else {
                    topPadding = ViewUtils.getScreenHeight(getContext()) / 2;
                    break;
                }
        }
        this.headerContainer.setPadding(this.headerContainer.getPaddingLeft(), topPadding, this.headerContainer.getPaddingRight(), this.headerContainer.getPaddingBottom());
    }

    public void setFlightEntryPointItem(FlightEntryPointItem flightEntryPointItem, OnClickListener acceptClickListener, OnClickListener dismissClickListener) {
        boolean z;
        boolean z2;
        boolean z3 = true;
        ViewLibUtils.setVisibleIf(this.flightEntryPointContainer, flightEntryPointItem != null);
        FrameLayout frameLayout = this.suggestionsContainer;
        if (flightEntryPointItem != null) {
            z = true;
        } else {
            z = false;
        }
        ViewLibUtils.setGoneIf(frameLayout, z);
        FrameLayout frameLayout2 = this.cardsContainer;
        if (flightEntryPointItem != null) {
            z2 = true;
        } else {
            z2 = false;
        }
        ViewLibUtils.setGoneIf(frameLayout2, z2);
        AirTextView airTextView = this.contentTextContainer;
        if (flightEntryPointItem == null) {
            z3 = false;
        }
        ViewLibUtils.setGoneIf(airTextView, z3);
        this.flightEntryPointContainer.setTitle(flightEntryPointItem.title());
        this.flightEntryPointContainer.setAcceptText(flightEntryPointItem.acceptText());
        this.flightEntryPointContainer.setDismissText(flightEntryPointItem.dismissText());
        this.flightEntryPointContainer.setAcceptClickListener(acceptClickListener);
        this.flightEntryPointContainer.setDismissClickListener(dismissClickListener);
    }
}
