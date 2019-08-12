package com.airbnb.android.contentframework.views;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

public class StoryHeaderRowView extends LinearLayout {
    @BindView
    AirTextView affiliateText;
    @BindView
    HaloImageView authorImage;
    @BindView
    AirTextView authorName;

    public StoryHeaderRowView(Context context) {
        super(context);
        init(context);
    }

    public StoryHeaderRowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public StoryHeaderRowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(0);
        inflate(context, C5709R.layout.story_header_row_view, this);
        ButterKnife.bind((View) this);
    }

    public void setAuthorImageUrl(String url) {
        this.authorImage.setImageUrl(url);
    }

    public void setAuthorName(String name) {
        ViewLibUtils.setVisibleIf(this.authorName, !TextUtils.isEmpty(name));
        this.authorName.setText(name);
    }

    public void setAffiliateLabel(String tag, String timestamp) {
        this.affiliateText.setText(tag + " " + timestamp);
    }
}
