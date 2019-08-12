package com.airbnb.p027n2.components.image_viewer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.p000v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.primitives.imaging.Image;
import com.bumptech.glide.request.RequestListener;

/* renamed from: com.airbnb.n2.components.image_viewer.ImageViewerView */
public class ImageViewerView extends FrameLayout {
    @BindView
    AirImageView imageView;
    @BindView
    AirTextView textView;

    public ImageViewerView(Context context) {
        super(context);
        init();
    }

    public ImageViewerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ImageViewerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.n2_image_viewer_view, this);
        ButterKnife.bind((View) this);
    }

    public void setPlaceholderDrawable(Drawable drawable) {
        this.imageView.setPlaceholderDrawable(drawable);
    }

    public void setImage(Image image, RequestListener<String, Bitmap> requestListener) {
        this.imageView.setImage(image, null, requestListener);
    }

    public void setImageTransitionName(String transitionName) {
        ViewCompat.setTransitionName(this.imageView, transitionName);
    }

    public void setCaption(CharSequence caption) {
        this.textView.setText(caption);
    }

    public void setCaptionBackgroundDrawable(Drawable drawable) {
        this.textView.setBackground(drawable);
    }

    public void setCaptionBackgroundColor(int color) {
        this.textView.setBackgroundColor(color);
    }

    public void fadeContent(boolean fadeOut) {
        this.textView.animate().alpha(fadeOut ? 0.0f : 1.0f).setDuration(150);
    }
}
