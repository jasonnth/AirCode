package com.airbnb.android.contentframework.viewcomponents.viewmodels;

import android.support.p000v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.core.interfaces.Parallaxable;
import com.airbnb.android.core.models.StoryImageDetails;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.transitions.TransitionName;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.facebook.internal.AnalyticsEvents;

public abstract class ArticleImageEpoxyModel extends AirEpoxyModel<AirImageView> implements Parallaxable {
    private static final double PARALLAX_PERCENTAGE = 0.1d;
    long articleId;
    ArticleImageClickListener callback;
    private final StoryImageDetails details;
    boolean hasSubElement;
    private AirImageView imageView;
    private int parallaxPixels;
    int photoIdx;
    private int screenWidth;

    public interface ArticleImageClickListener {
        void onImageClick(StoryImageDetails storyImageDetails, View view, int i);
    }

    public ArticleImageEpoxyModel(StoryImageDetails details2) {
        this.details = details2;
    }

    public void bind(AirImageView imageView2) {
        super.bind(imageView2);
        this.imageView = imageView2;
        updateViewForSubElementState();
        updateImageSize(this.details.getImageRatio());
        if (this.details.getImagePreview() != null) {
            imageView2.setImageUrlWithBlurredPreview(this.details.getImageUrl(), this.details.getImagePreview());
        } else {
            imageView2.setImageUrl(this.details.getImageUrl());
        }
        imageView2.setOnClickListener(ArticleImageEpoxyModel$$Lambda$1.lambdaFactory$(this));
        ViewCompat.setTransitionName(imageView2, TransitionName.toString("article", this.articleId, AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_PHOTO, (long) this.photoIdx));
    }

    private void updateImageSize(double imageRatio) {
        if (imageRatio > 0.0d) {
            if (this.screenWidth < 1) {
                this.screenWidth = ViewLibUtils.getScreenWidth(this.imageView.getContext());
            }
            MarginLayoutParams layoutParams = (MarginLayoutParams) this.imageView.getLayoutParams();
            layoutParams.width = (this.screenWidth - layoutParams.leftMargin) - layoutParams.rightMargin;
            int imageHeightBeforeParallaxAdjustment = (int) (((double) layoutParams.width) / imageRatio);
            this.parallaxPixels = (int) (((double) imageHeightBeforeParallaxAdjustment) * PARALLAX_PERCENTAGE);
            layoutParams.height = imageHeightBeforeParallaxAdjustment - this.parallaxPixels;
        }
    }

    public void unbind(AirImageView view) {
        view.setOnClickListener(null);
        this.imageView = null;
    }

    public void updateParallax() {
        if (this.imageView != null) {
            this.imageView.updateParallax(true, this.parallaxPixels);
        }
    }

    private void updateViewForSubElementState() {
        MarginLayoutParams layoutParams = (MarginLayoutParams) this.imageView.getLayoutParams();
        if (this.hasSubElement) {
            layoutParams.bottomMargin = 0;
        } else {
            layoutParams.bottomMargin = this.imageView.getResources().getDimensionPixelSize(C5709R.dimen.story_element_image_vertical_padding);
        }
    }
}
