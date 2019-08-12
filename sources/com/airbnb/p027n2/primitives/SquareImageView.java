package com.airbnb.p027n2.primitives;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.primitives.SquareImageView */
public class SquareImageView extends AirImageView {
    public SquareImageView(Context context) {
        super(context);
    }

    public SquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        boolean isMatchHorizontal;
        boolean isMatchVertical;
        int size;
        if (getLayoutParams().width == -1) {
            isMatchHorizontal = true;
        } else {
            isMatchHorizontal = false;
        }
        if (getLayoutParams().height == -1) {
            isMatchVertical = true;
        } else {
            isMatchVertical = false;
        }
        if (isMatchHorizontal) {
            size = MeasureSpec.getSize(widthMeasureSpec);
        } else if (isMatchVertical) {
            size = MeasureSpec.getSize(heightMeasureSpec);
        } else {
            throw new IllegalArgumentException("SquareImageView must have match_parent either height or width");
        }
        int measureSpec = MeasureSpec.makeMeasureSpec(size, 1073741824);
        super.onMeasure(measureSpec, measureSpec);
    }
}
