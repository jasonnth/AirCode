package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.GuideImageMarquee */
public class GuideImageMarquee extends LinearLayout {
    @BindView
    AirImageView imageView;

    public GuideImageMarquee(Context context) {
        super(context);
        init();
    }

    public GuideImageMarquee(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GuideImageMarquee(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.n2_guide_image_marquee, this);
        ButterKnife.bind((View) this);
        setOrientation(1);
    }

    public void setImage(int resId) {
        this.imageView.setImageResource(resId);
    }

    public void setImageUrl(String imageUrl) {
        this.imageView.setImageUrl(imageUrl);
    }

    public static void mock(GuideImageMarquee view) {
        view.setImageUrl("https://a2.muscache.com/im/pictures/9b0f6c02-f5c1-40e0-892a-7272aa05437b.jpg?aki_policy=large");
    }

    public static void mockDrawable(GuideImageMarquee view) {
        view.setImage(R.drawable.n2_ic_camera);
    }

    public static void mockNoImage(GuideImageMarquee view) {
    }
}
