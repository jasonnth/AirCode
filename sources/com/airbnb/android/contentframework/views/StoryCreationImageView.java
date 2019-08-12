package com.airbnb.android.contentframework.views;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.contentframework.data.StoryCreationImage;
import com.airbnb.android.contentframework.data.StoryCreationImage.PhotoType;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.bumptech.glide.Glide;

public class StoryCreationImageView extends AirImageView {
    private PhotoType type;

    public StoryCreationImageView(Context context) {
        super(context);
    }

    public StoryCreationImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StoryCreationImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /* access modifiers changed from: 0000 */
    public void loadImage(StoryCreationImage image) {
        this.type = image.photoType();
        requestLayout();
        post(StoryCreationImageView$$Lambda$1.lambdaFactory$(this, image));
    }

    /* access modifiers changed from: private */
    public void loadImageFromUri(Uri uri) {
        Glide.with(getContext()).load(uri).asBitmap().override(getMeasuredWidth(), getMeasuredHeight()).placeholder(C5709R.color.n2_loading_background).centerCrop().into(this);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int fixedSize = MeasureSpec.getSize(widthMeasureSpec);
        int topMargin = 0;
        if (this.type == PhotoType.Portrait) {
            setMeasuredDimension((int) (((float) fixedSize) * this.type.aspectRatio), fixedSize);
        } else {
            int height = (int) (((float) fixedSize) / this.type.aspectRatio);
            setMeasuredDimension(fixedSize, height);
            if (this.type == PhotoType.Landscape) {
                topMargin = (fixedSize - height) / 2;
            }
        }
        MarginLayoutParams layoutParams = (MarginLayoutParams) getLayoutParams();
        layoutParams.topMargin = topMargin;
        setLayoutParams(layoutParams);
    }
}
