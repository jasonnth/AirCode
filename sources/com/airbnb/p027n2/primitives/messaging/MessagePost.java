package com.airbnb.p027n2.primitives.messaging;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.primitives.messaging.MessagePost */
public abstract class MessagePost extends RelativeLayout {
    protected HaloImageView profileImage;
    protected AirTextView reportTextView;
    protected boolean reported;
    protected AirTextView status;
    protected AirTextView textProfilePhotoPlaceholder;

    /* access modifiers changed from: protected */
    public abstract void init(AttributeSet attributeSet);

    public MessagePost(Context context) {
        super(context);
        init(null);
    }

    public MessagePost(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MessagePost(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void setStatusText(CharSequence s) {
        if (s == null || s.length() <= 0) {
            this.status.setVisibility(8);
            return;
        }
        this.status.setVisibility(0);
        this.status.setText(s);
    }

    public void setProfileUrl(String url) {
        boolean hasUrl = url != null && !url.isEmpty();
        ViewLibUtils.setVisibleIf(this.profileImage, hasUrl);
        if (hasUrl) {
            this.profileImage.setImageUrl(url);
        }
    }

    public void setProfileDrawable(int profileImageRes) {
        this.profileImage.setImageDrawableCompat(profileImageRes);
        ViewLibUtils.setVisibleIf(this.profileImage, true);
    }

    public void setPlaceholderText(CharSequence text) {
        boolean hasText = !TextUtils.isEmpty(text);
        ViewLibUtils.setVisibleIf(this.textProfilePhotoPlaceholder, hasText);
        if (hasText) {
            this.textProfilePhotoPlaceholder.setText(text);
        }
    }

    public void setProfileClickLink(OnClickListener listener) {
        this.profileImage.setOnClickListener(listener);
    }

    public void setReportClickLink(OnClickListener listener) {
        this.reportTextView.setOnClickListener(listener);
    }
}
