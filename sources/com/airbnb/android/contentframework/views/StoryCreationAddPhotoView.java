package com.airbnb.android.contentframework.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;

public class StoryCreationAddPhotoView extends FrameLayout {
    public StoryCreationAddPhotoView(Context context) {
        super(context);
    }

    public StoryCreationAddPhotoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StoryCreationAddPhotoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec), 1073741824);
        super.onMeasure(measureSpec, measureSpec);
    }
}
