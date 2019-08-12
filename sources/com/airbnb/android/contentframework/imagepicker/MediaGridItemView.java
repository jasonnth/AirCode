package com.airbnb.android.contentframework.imagepicker;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.bumptech.glide.Glide;

public class MediaGridItemView extends FrameLayout {
    private static final float ALPHA_DISABLED = 0.6f;
    private static final float ALPHA_ENABLED = 1.0f;
    private static int itemWidth;
    @BindView
    CheckView checkView;
    @BindDimen
    float gridItemInnerPadding;
    private boolean isThumbnailLongPressed;
    private OnMediaItemClickListener listener;
    @BindView
    AirImageView thumbnail;
    private Uri uri;

    public interface OnMediaItemClickListener {
        void onThumbnailClicked(Uri uri);

        void onThumbnailLongPressed(AirImageView airImageView, Uri uri);
    }

    public MediaGridItemView(Context context) {
        super(context);
        init(context);
    }

    public MediaGridItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MediaGridItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void setMediaUri(Uri uri2) {
        this.uri = uri2;
        loadImageFromUri(this.thumbnail, uri2);
    }

    public void setEnabled(boolean enabled) {
        this.thumbnail.setAlpha(enabled ? 1.0f : ALPHA_DISABLED);
    }

    public void setListener(OnMediaItemClickListener listener2) {
        this.listener = listener2;
    }

    public void setCheckedNum(int checkedNum) {
        this.checkView.setCheckedNum(checkedNum);
        ViewLibUtils.setVisibleIf(this.checkView, checkedNum > 0);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    private void init(Context context) {
        inflate(context, C5709R.layout.story_creation_media_grid_item_view, this);
        ButterKnife.bind((View) this);
        if (itemWidth == 0) {
            itemWidth = (int) ((((float) ViewLibUtils.getScreenWidth(context)) - (this.gridItemInnerPadding * 3.0f)) / 4.0f);
        }
        this.thumbnail.setOnClickListener(MediaGridItemView$$Lambda$1.lambdaFactory$(this));
        this.thumbnail.setOnLongClickListener(MediaGridItemView$$Lambda$2.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$init$0(MediaGridItemView mediaGridItemView, View v) {
        if (mediaGridItemView.listener != null && mediaGridItemView.uri != null) {
            mediaGridItemView.listener.onThumbnailClicked(mediaGridItemView.uri);
        }
    }

    static /* synthetic */ boolean lambda$init$1(MediaGridItemView mediaGridItemView, View v) {
        if (mediaGridItemView.listener == null || mediaGridItemView.uri == null) {
            return false;
        }
        mediaGridItemView.isThumbnailLongPressed = true;
        mediaGridItemView.listener.onThumbnailLongPressed(mediaGridItemView.thumbnail, mediaGridItemView.uri);
        return true;
    }

    private void loadImageFromUri(AirImageView imageView, Uri uri2) {
        Glide.with(getContext()).load(uri2).asBitmap().placeholder(C5709R.color.n2_loading_background).override(itemWidth, itemWidth).centerCrop().into(imageView);
    }
}
