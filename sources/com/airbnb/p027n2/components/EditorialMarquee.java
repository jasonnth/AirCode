package com.airbnb.p027n2.components;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.collections.AirVideoView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.components.EditorialMarquee */
public class EditorialMarquee extends LinearLayout {
    @BindView
    AirTextView descriptionTextView;
    @BindView
    AirImageView imageView;
    @BindView
    AirTextView kickerTextView;
    @BindView
    AirTextView titleTextView;
    @BindView
    AirVideoView videoView;

    public EditorialMarquee(Context context) {
        super(context);
        init(null);
    }

    public EditorialMarquee(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public EditorialMarquee(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_editorial_marquee, this);
        ButterKnife.bind((View) this);
        Paris.style(this).apply(attrs);
        setOrientation(1);
        this.videoView.setReleaseOnDetachFromWindow(false);
    }

    public void setImage(Drawable image) {
        this.imageView.setImageDrawable(image);
    }

    public void setImageUrl(String imageUrl) {
        this.imageView.setImageUrl(imageUrl);
    }

    public void hideImage(boolean hideImage) {
        ViewLibUtils.setGoneIf(this.imageView, hideImage);
    }

    public void setImageBackgroundColor(int color) {
        this.imageView.setBackgroundColor(color);
    }

    public void setVideoUrlWithPosition(String videoUrl, int videoPosition) {
        boolean hasVideoUrl = !TextUtils.isEmpty(videoUrl);
        ViewLibUtils.setVisibleIf(this.videoView, hasVideoUrl);
        ViewLibUtils.setGoneIf(this.imageView, hasVideoUrl);
        if (hasVideoUrl) {
            this.videoView.setSrc(videoUrl);
            this.videoView.setOnPreparedListener(EditorialMarquee$$Lambda$1.lambdaFactory$(this, videoPosition));
        }
    }

    static /* synthetic */ void lambda$setVideoUrlWithPosition$0(EditorialMarquee editorialMarquee, int videoPosition) {
        editorialMarquee.videoView.seekTo(videoPosition);
        editorialMarquee.videoView.start();
    }

    public void setKicker(CharSequence kicker) {
        ViewLibUtils.bindOptionalTextView((TextView) this.kickerTextView, kicker);
    }

    public void setTitle(CharSequence title) {
        this.titleTextView.setText(title);
    }

    public void setTitle(int title) {
        this.titleTextView.setText(getResources().getString(title));
    }

    public void setDescription(CharSequence description) {
        ViewLibUtils.bindOptionalTextView((TextView) this.descriptionTextView, description);
    }

    public void setScrimEnabled(boolean enabled) {
        this.imageView.setScrimForText(enabled);
    }

    public static void mock(EditorialMarquee marquee) {
        marquee.setTitle((CharSequence) "Editorial Marquee");
    }

    public static void mockPlusDescription(EditorialMarquee marquee) {
        marquee.setTitle((CharSequence) "Editorial Marquee");
        marquee.setDescription("Description: America's early beginnings are etched into the earth of Boston, a traditional New England city.");
    }

    public static void mockPlusDescriptionPlusKicker(EditorialMarquee marquee) {
        marquee.setKicker("KICKER");
        marquee.setTitle((CharSequence) "Editorial Marquee");
        marquee.setDescription("Description: America's early beginnings are etched into the earth of Boston, a traditional New England city.");
    }
}
