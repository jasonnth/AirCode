package com.airbnb.android.core;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.p027n2.collections.Carousel;

public abstract class DisplayOptions {

    public enum DisplayType {
        Vertical,
        Grid,
        Horizontal,
        Magazine,
        Pill,
        Unknown
    }

    public abstract float cardsPerRow();

    public abstract DisplayType displayType();

    static DisplayOptions create(DisplayType displayType, float cardsPerRow) {
        if (displayType == DisplayType.Grid || displayType == DisplayType.Magazine) {
            displayType = DisplayType.Vertical;
        }
        return new AutoValue_DisplayOptions(displayType, cardsPerRow);
    }

    public boolean isGrid() {
        return displayType() == DisplayType.Vertical && cardsPerRow() > 1.0f;
    }

    public boolean isCarousel() {
        return displayType() == DisplayType.Horizontal;
    }

    public boolean isList() {
        return !isCarousel() && !isGrid();
    }

    public static DisplayOptions forPosterCard(Context context, DisplayType displayType) {
        return forTallCard(context, displayType);
    }

    public static DisplayOptions forDestinationCard(Context context, DisplayType displayType) {
        int cardsPerRow;
        boolean isTabletScreen = MiscUtils.isTabletScreen(context);
        boolean isWideTabletScreen = MiscUtils.isWideTabletScreen(context);
        switch (displayType) {
            case Vertical:
            case Grid:
                if (!isTabletScreen) {
                    cardsPerRow = 3;
                    break;
                } else {
                    cardsPerRow = 6;
                    break;
                }
            case Horizontal:
                if (isTabletScreen) {
                    if (!isWideTabletScreen) {
                        cardsPerRow = 4;
                        break;
                    } else {
                        cardsPerRow = 5;
                        break;
                    }
                } else {
                    cardsPerRow = 3;
                    break;
                }
            default:
                cardsPerRow = 3;
                break;
        }
        return create(displayType, (float) cardsPerRow);
    }

    private static DisplayOptions forTallCard(Context context, DisplayType displayType) {
        int cardsPerRow;
        boolean isTabletScreen = MiscUtils.isTabletScreen(context);
        boolean isWideTabletScreen = MiscUtils.isWideTabletScreen(context);
        switch (displayType) {
            case Vertical:
            case Grid:
            case Horizontal:
                if (isTabletScreen) {
                    if (!isWideTabletScreen) {
                        cardsPerRow = 3;
                        break;
                    } else {
                        cardsPerRow = 4;
                        break;
                    }
                } else {
                    cardsPerRow = 2;
                    break;
                }
            default:
                cardsPerRow = 2;
                break;
        }
        return create(displayType, (float) cardsPerRow);
    }

    public static DisplayOptions forHomeCard(Context context, DisplayType displayType) {
        return forWideCard(context, displayType);
    }

    public static DisplayOptions forMosaicCard(Context context, DisplayType displayType) {
        return forWideCard(context, displayType);
    }

    public static DisplayOptions forArticleCard(Context context, DisplayType displayType) {
        return forWideCard(context, displayType);
    }

    public static DisplayOptions forStoryCard(Context context, DisplayType displayType) {
        return forSquareCard(context, displayType);
    }

    public static DisplayOptions forAddToPlanButton(Context context, DisplayType displayType) {
        return forRecommendationCard(context, displayType);
    }

    public static DisplayOptions forCheckInStepCard(Context context) {
        return create(DisplayType.Horizontal, 1.2f);
    }

    public static DisplayOptions forTrayCard(Context context) {
        boolean isTabletScreen = MiscUtils.isTabletScreen(context);
        boolean isWideTabletScreen = MiscUtils.isWideTabletScreen(context);
        int cardsPerRow = 2;
        if (isTabletScreen) {
            cardsPerRow = isWideTabletScreen ? 5 : 4;
        }
        return create(DisplayType.Horizontal, (float) cardsPerRow);
    }

    public static DisplayOptions forGiftCardPromoCard(Context context, DisplayType displayType) {
        return forWideCard(context, displayType);
    }

    private static DisplayOptions forWideCard(Context context, DisplayType displayType) {
        float cardsPerRow = 2.0f;
        boolean isTabletScreen = MiscUtils.isTabletScreen(context);
        boolean isWideTabletScreen = MiscUtils.isWideTabletScreen(context);
        switch (displayType) {
            case Vertical:
            case Grid:
            case Magazine:
                if (!isTabletScreen) {
                    cardsPerRow = 1.0f;
                    break;
                }
                break;
            case Horizontal:
                if (isTabletScreen) {
                    if (isWideTabletScreen) {
                        cardsPerRow = 3.0f;
                        break;
                    }
                } else {
                    cardsPerRow = 1.45f;
                    break;
                }
                break;
            default:
                cardsPerRow = 1.0f;
                break;
        }
        return create(displayType, cardsPerRow);
    }

    public static DisplayOptions forPlaceCard(Context context, DisplayType displayType) {
        return forSquareCard(context, displayType);
    }

    public static DisplayOptions forRecommendationCard(Context context, DisplayType displayType) {
        int cardsPerRow;
        boolean isTabletScreen = MiscUtils.isTabletScreen(context);
        boolean isWideTabletScreen = MiscUtils.isWideTabletScreen(context);
        switch (displayType) {
            case Vertical:
            case Grid:
            case Horizontal:
                if (isTabletScreen) {
                    if (!isWideTabletScreen) {
                        cardsPerRow = 4;
                        break;
                    } else {
                        cardsPerRow = 6;
                        break;
                    }
                } else {
                    cardsPerRow = 3;
                    break;
                }
            default:
                cardsPerRow = 1;
                break;
        }
        return create(displayType, (float) cardsPerRow);
    }

    private static DisplayOptions forSquareCard(Context context, DisplayType displayType) {
        int cardsPerRow = 4;
        boolean isTabletScreen = MiscUtils.isTabletScreen(context);
        boolean isWideTabletScreen = MiscUtils.isWideTabletScreen(context);
        switch (displayType) {
            case Vertical:
            case Grid:
                if (isTabletScreen) {
                    if (!isWideTabletScreen) {
                        cardsPerRow = 3;
                        break;
                    }
                } else {
                    cardsPerRow = 2;
                    break;
                }
                break;
            case Horizontal:
                if (isTabletScreen) {
                    if (!isWideTabletScreen) {
                        cardsPerRow = 3;
                        break;
                    }
                } else {
                    cardsPerRow = 2;
                    break;
                }
                break;
            default:
                cardsPerRow = 1;
                break;
        }
        return create(displayType, (float) cardsPerRow);
    }

    public static DisplayOptions forRecommendationRow(Context context, DisplayType displayType) {
        return forWideCard(context, displayType);
    }

    public void adjustLayoutParams(View view) {
        if (isCarousel()) {
            LayoutParams params = view.getLayoutParams();
            params.width = Carousel.getCarouselCardWidth(view.getContext(), cardsPerRow(), getAverageMargin(params));
            view.setLayoutParams(params);
        }
    }

    public void adjustLayoutParams(View view, int carouselPadding) {
        if (isCarousel()) {
            LayoutParams params = view.getLayoutParams();
            params.width = Carousel.getCarouselCardWidth(view.getContext(), cardsPerRow(), getAverageMargin(params), carouselPadding);
            view.setLayoutParams(params);
        }
    }

    private int getAverageMargin(LayoutParams params) {
        if (!(params instanceof MarginLayoutParams)) {
            return 0;
        }
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) params;
        return (marginLayoutParams.leftMargin + marginLayoutParams.rightMargin) / 2;
    }

    public int getSpanSize(int totalSpanCount) {
        int gridCardsPerRow = Math.round(cardsPerRow());
        if (!isGrid()) {
            return totalSpanCount;
        }
        if (totalSpanCount % gridCardsPerRow == 0) {
            return totalSpanCount / gridCardsPerRow;
        }
        throw new IllegalStateException("Total Span Count of : " + totalSpanCount + " can not evenly fit: " + cardsPerRow() + " cards per row");
    }
}
