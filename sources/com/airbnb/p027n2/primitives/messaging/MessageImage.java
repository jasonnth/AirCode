package com.airbnb.p027n2.primitives.messaging;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.RelativeLayout.LayoutParams;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageListener;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.google.common.base.Strings;

/* renamed from: com.airbnb.n2.primitives.messaging.MessageImage */
public abstract class MessageImage extends MessagePost {
    private View failedIcon;
    /* access modifiers changed from: private */
    public View gradientView;
    private int imageFileSize;
    /* access modifiers changed from: private */
    public AirImageView imageView;
    private MessageImageOnLoadedListener onLoadedListener;
    private long postId;

    /* renamed from: com.airbnb.n2.primitives.messaging.MessageImage$MessageImageOnLoadedListener */
    public interface MessageImageOnLoadedListener {
        void onImageLoaded(long j, boolean z, long j2, long j3);
    }

    /* access modifiers changed from: 0000 */
    public abstract int getLayoutId();

    public MessageImage(Context context) {
        super(context);
    }

    public MessageImage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MessageImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(AttributeSet attrs) {
        inflate(getContext(), getLayoutId(), this);
        bindViews();
        setMessageState(false);
    }

    private void bindViews() {
        this.status = (AirTextView) ViewLibUtils.findById(this, R.id.text_status);
        this.profileImage = (HaloImageView) ViewLibUtils.findById(this, R.id.image_thumbnail);
        this.failedIcon = ViewLibUtils.findById(this, R.id.error_icon);
        this.imageView = (AirImageView) ViewLibUtils.findById(this, R.id.img_attachment);
        this.gradientView = ViewLibUtils.findById(this, R.id.img_gradient);
        this.reportTextView = (AirTextView) ViewLibUtils.findById(this, R.id.report_text);
        this.textProfilePhotoPlaceholder = (AirTextView) ViewLibUtils.findById(this, R.id.thumbnail_text_placeholder);
    }

    public void setMessageState(boolean sendFailed) {
        ViewLibUtils.setVisibleIf(this.failedIcon, sendFailed);
    }

    public void setImageAttachmentView(String url, OnClickListener imageAttachmentClickListener, OnLongClickListener imageAttachmentLongClickListener, int widthPx) {
        if (Strings.isNullOrEmpty(url)) {
            showImage(false);
        } else {
            displayImage(url, imageAttachmentClickListener, imageAttachmentLongClickListener, widthPx);
        }
    }

    public void setOnImageLoadedListener(MessageImageOnLoadedListener listener) {
        this.onLoadedListener = listener;
    }

    public void setImageFileSize(int size) {
        this.imageFileSize = size;
    }

    public void setPostId(long postId2) {
        this.postId = postId2;
    }

    public void setReported(boolean reported) {
        this.reported = reported;
        showImage(!reported);
    }

    public void setReportedText(CharSequence text) {
        this.reportTextView.setText(text);
    }

    public void showReported(boolean show) {
        ViewLibUtils.setVisibleIf(this.reportTextView, show);
    }

    private void showImage(boolean show) {
        ViewLibUtils.setVisibleIf(this.imageView, show);
        ViewLibUtils.setVisibleIf(this.gradientView, show);
        if (!show) {
            this.imageView.setImageUrl(null);
        }
    }

    private void displayImage(String url, OnClickListener imageAttachmentClickListener, OnLongClickListener imageAttachmentLongClickListener, int widthPx) {
        this.imageView.setImageResource(17170445);
        this.imageView.setVisibility(0);
        this.imageView.setAdjustViewBounds(true);
        this.imageView.setLayoutParams(new LayoutParams(widthPx, -2));
        fetchImage(url, imageAttachmentClickListener, imageAttachmentLongClickListener);
    }

    private void fetchImage(String url, OnClickListener imageAttachmentClickListener, OnLongClickListener imageAttachmentLongClickListener) {
        final long startTime = SystemClock.currentThreadTimeMillis();
        final OnClickListener onClickListener = imageAttachmentClickListener;
        final OnLongClickListener onLongClickListener = imageAttachmentLongClickListener;
        AirImageView.getImage(getContext(), url, new AirImageListener() {
            public void onResponse(Bitmap response, boolean isImmediate) {
                MessageImage.this.imageView.setImageBitmap(response);
                MessageImage.this.imageView.setOnClickListener(onClickListener);
                MessageImage.this.imageView.setOnLongClickListener(onLongClickListener);
                MessageImage.this.gradientView.setVisibility(0);
                MessageImage.this.logImageLoadingTime(true, startTime);
            }

            public void onErrorResponse(Exception exception) {
                MessageImage.this.logImageLoadingTime(false, startTime);
            }
        });
    }

    /* access modifiers changed from: private */
    public void logImageLoadingTime(boolean isSuccessful, long startTime) {
        this.onLoadedListener.onImageLoaded(this.postId, isSuccessful, (long) this.imageFileSize, SystemClock.currentThreadTimeMillis() - startTime);
    }
}
