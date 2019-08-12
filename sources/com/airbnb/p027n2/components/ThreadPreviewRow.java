package com.airbnb.p027n2.components;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.CircleCollageImageView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.SwipeLayout.ShowMode;
import java.util.List;

/* renamed from: com.airbnb.n2.components.ThreadPreviewRow */
public class ThreadPreviewRow extends FrameLayout {
    @BindView
    AirButton actionButton;
    @BindView
    AirTextView fourthRowText;
    @BindView
    CircleCollageImageView imageView;
    @BindView
    AirImageView squareImageView;
    @BindView
    AirTextView subtitleText;
    @BindView
    SwipeLayout swipeLayout;
    @BindView
    TextView swipeTextView;
    @BindView
    AirTextView textProfilePhotoPlaceholder;
    @BindView
    AirTextView thirdRowText;
    @BindView
    View threadPreviewView;
    @BindView
    AirTextView timeAgoText;
    @BindView
    AirTextView titleText;
    @BindView
    ImageView unreadIndicator;

    public ThreadPreviewRow(Context context) {
        super(context);
        init();
    }

    public ThreadPreviewRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ThreadPreviewRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.n2_thread_preview_row, this);
        ButterKnife.bind((View) this);
        disableSwipe();
    }

    public void disableSwipe() {
        this.swipeLayout.setSwipeEnabled(false);
    }

    public void enableSwipe() {
        this.swipeLayout.setSwipeEnabled(true);
        this.swipeLayout.setShowMode(ShowMode.PullOut);
        this.swipeLayout.close();
    }

    public void setSwipeText(int textRes) {
        this.swipeTextView.setText(textRes);
    }

    public void setSwipeListener(SimpleSwipeListener listener) {
        this.swipeLayout.removeAllSwipeListener();
        this.swipeLayout.addSwipeListener(listener);
    }

    public void setClickListenerOnThreadPreview(OnClickListener listener) {
        this.threadPreviewView.setOnClickListener(listener);
    }

    public void setImageUrls(List<String> urls) {
        setImageUrls(urls, false);
    }

    public void setImageUrls(List<String> urls, boolean shouldShowSquareImage) {
        ViewLibUtils.setVisibleIf(this.squareImageView, shouldShowSquareImage);
        ViewLibUtils.setVisibleIf(this.imageView, !shouldShowSquareImage);
        if (shouldShowSquareImage) {
            this.squareImageView.setImageUrl((urls == null || urls.size() <= 0) ? null : (String) urls.get(0));
        } else {
            this.imageView.setImages(urls);
        }
    }

    public void setImageRes(int imageRes) {
        this.imageView.setImageRes(imageRes);
    }

    public void setPlaceholderText(CharSequence text) {
        boolean hasText = !TextUtils.isEmpty(text);
        ViewLibUtils.setVisibleIf(this.textProfilePhotoPlaceholder, hasText);
        if (hasText) {
            this.textProfilePhotoPlaceholder.setText(text);
        }
    }

    public void setUnreadIndicatorVisible(boolean visible) {
        this.unreadIndicator.setVisibility(visible ? 0 : 8);
    }

    public void setTimeAgoText(CharSequence text) {
        this.timeAgoText.setText(text);
    }

    public void setTitleText(CharSequence text) {
        ViewLibUtils.setVisibleIf(this.titleText, !TextUtils.isEmpty(text));
        this.titleText.setText(text);
    }

    public void setSubtitleText(CharSequence text) {
        ViewLibUtils.setVisibleIf(this.subtitleText, !TextUtils.isEmpty(text));
        this.subtitleText.setText(text);
    }

    public void setThirdRowText(CharSequence text) {
        this.thirdRowText.setText(text);
        this.thirdRowText.setVisibility(isEmpty(text) ? 8 : 0);
    }

    public void setFourthRowText(CharSequence text) {
        this.fourthRowText.setText(text);
        this.fourthRowText.setVisibility(isEmpty(text) ? 8 : 0);
    }

    public void setActionButtonText(int text) {
        this.actionButton.setText(text);
    }

    public void setActionButtonText(CharSequence text) {
        this.actionButton.setText(text);
    }

    public void setActionButtonListener(OnClickListener onClickListener) {
        this.actionButton.setOnClickListener(onClickListener);
    }

    public void setActionButtonVisible(boolean visible) {
        this.actionButton.setVisibility(visible ? 0 : 8);
    }

    public void setShowMultiline(boolean multiline) {
        int maxLines;
        boolean singleLine = true;
        if (multiline) {
            maxLines = Integer.MAX_VALUE;
        } else {
            maxLines = 1;
        }
        if (multiline) {
            singleLine = false;
        }
        this.titleText.setSingleLine(singleLine);
        this.titleText.setMaxLines(maxLines);
        this.subtitleText.setSingleLine(singleLine);
        this.subtitleText.setMaxLines(maxLines);
    }

    private boolean isEmpty(CharSequence s) {
        return s == null || s.length() == 0;
    }

    public static void mock(ThreadPreviewRow view) {
        view.setTitleText("Title");
        view.setSubtitleText("Sutitle");
        view.setActionButtonText((CharSequence) "Action button");
    }
}
