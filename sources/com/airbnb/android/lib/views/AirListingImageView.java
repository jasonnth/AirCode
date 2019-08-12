package com.airbnb.android.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

public class AirListingImageView extends AirImageView {
    public AirListingImageView(Context context) {
        super(context);
    }

    public AirListingImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AirListingImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        boolean isMatchHorizontal = getLayoutParams().width == -1;
        boolean isMatchVertical = getLayoutParams().height == -1;
        if (isMatchHorizontal) {
            super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec((int) (((float) MeasureSpec.getSize(widthMeasureSpec)) * 0.6666667f), 1073741824));
        } else if (isMatchVertical) {
            super.onMeasure(MeasureSpec.makeMeasureSpec((int) (((float) MeasureSpec.getSize(heightMeasureSpec)) / 0.6666667f), 1073741824), heightMeasureSpec);
        } else {
            throw new IllegalArgumentException("AirListingImageView must have match_parent either height or width");
        }
    }
}
