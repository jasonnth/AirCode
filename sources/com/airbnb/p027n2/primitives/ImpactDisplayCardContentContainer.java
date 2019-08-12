package com.airbnb.p027n2.primitives;

import android.content.Context;
import android.support.percent.PercentFrameLayout.LayoutParams;
import android.support.percent.PercentLayoutHelper.PercentLayoutInfo;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.airbnb.n2.R;
import com.airbnb.p027n2.collections.Carousel;

/* renamed from: com.airbnb.n2.primitives.ImpactDisplayCardContentContainer */
public class ImpactDisplayCardContentContainer extends FrameLayout {
    public ImpactDisplayCardContentContainer(Context context) {
        super(context);
    }

    public ImpactDisplayCardContentContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImpactDisplayCardContentContainer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setStyle(int style) {
        updateLayoutParamsForStyle(style);
    }

    private void updateLayoutParamsForStyle(int style) {
        switch (style) {
            case 1:
            case 5:
                updateLayoutParamsForNormalStyle();
                return;
            case 2:
                updateLayoutParamsForCarouselStyle();
                return;
            case 3:
                updateLayoutParamsForCarouselDoubleStyle();
                return;
            case 4:
                updateLayoutParamsForCarouselSquareStyle();
                return;
            default:
                return;
        }
    }

    private void updateLayoutParamsForNormalStyle() {
        updateLayoutParams(0, 0, 1.0f, 1.5f);
    }

    private void updateLayoutParamsForCarouselStyle() {
        int cardWidth = Carousel.getCarouselCardWidth(getContext());
        updateLayoutParams(cardWidth, (int) (((float) cardWidth) * 0.6666667f), -1.0f, -1.0f);
    }

    private void updateLayoutParamsForCarouselDoubleStyle() {
        int cardWidth = Carousel.getCarouselCardWidth(getContext());
        updateLayoutParams(cardWidth, getDoubleHeightForWidth(cardWidth), -1.0f, -1.0f);
    }

    private void updateLayoutParamsForCarouselSquareStyle() {
        int size = getDoubleHeightForWidth(Carousel.getCarouselCardWidth(getContext()));
        updateLayoutParams(size, size, -1.0f, -1.0f);
    }

    private void updateLayoutParams(int width, int height, float widthPercent, float aspectRatio) {
        LayoutParams params = getPercentLayoutParams();
        PercentLayoutInfo percentInfo = params.getPercentLayoutInfo();
        params.width = width;
        params.height = height;
        percentInfo.widthPercent = widthPercent;
        percentInfo.aspectRatio = aspectRatio;
        setLayoutParams(params);
    }

    private LayoutParams getPercentLayoutParams() {
        return (LayoutParams) getLayoutParams();
    }

    private int getDoubleHeightForWidth(int width) {
        return (int) ((((float) width) * 0.6666667f * 2.0f) + ((float) (getResources().getDimensionPixelSize(R.dimen.n2_carousel_card_padding) * 2)));
    }
}
