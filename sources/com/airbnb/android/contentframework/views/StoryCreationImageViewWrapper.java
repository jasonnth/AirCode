package com.airbnb.android.contentframework.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.FrameLayout;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.contentframework.data.StoryCreationImage;
import com.airbnb.android.contentframework.data.StoryCreationImage.PhotoType;

public class StoryCreationImageViewWrapper extends FrameLayout {
    @BindDimen
    int cardPadding;
    private StoryCreationImage image;
    @BindView
    StoryCreationImageView imageView;
    private OnOptionsSelectedListener listener;
    @BindView
    View optionsView;

    public interface OnOptionsSelectedListener {
        void onImageSelected(StoryCreationImage storyCreationImage, View view);
    }

    public StoryCreationImageViewWrapper(Context context) {
        super(context);
        init(context);
    }

    public StoryCreationImageViewWrapper(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public StoryCreationImageViewWrapper(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, C5709R.layout.story_creation_image_view_wrapper, this);
        ButterKnife.bind((View) this);
    }

    public void loadImage(StoryCreationImage image2) {
        this.image = image2;
        this.imageView.loadImage(image2);
    }

    public void setOnOptionsSelectedListener(OnOptionsSelectedListener listener2) {
        this.listener = listener2;
    }

    @OnClick
    public void onImageOptionsClicked() {
        if (this.listener != null) {
            this.listener.onImageSelected(this.image, this);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int topMargin = 0;
        if (this.image.photoType() == PhotoType.Portrait) {
            setMeasuredDimension(this.imageView.getMeasuredWidth() + (this.cardPadding * 2), getMeasuredHeight());
        } else if (this.image.photoType() == PhotoType.Landscape) {
            topMargin = ((MarginLayoutParams) this.imageView.getLayoutParams()).topMargin;
        }
        MarginLayoutParams optionsViewLayoutParams = (MarginLayoutParams) this.optionsView.getLayoutParams();
        optionsViewLayoutParams.topMargin = topMargin;
        this.optionsView.setLayoutParams(optionsViewLayoutParams);
    }
}
