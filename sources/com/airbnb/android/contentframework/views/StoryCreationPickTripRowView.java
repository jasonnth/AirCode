package com.airbnb.android.contentframework.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

public class StoryCreationPickTripRowView extends RelativeLayout {
    @BindView
    AirImageView image;
    @BindView
    AirTextView subtitle;
    @BindView
    AirTextView title;

    public StoryCreationPickTripRowView(Context context) {
        super(context);
        init(context);
    }

    public StoryCreationPickTripRowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public StoryCreationPickTripRowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, C5709R.layout.story_creation_generic_list_row, this);
        ButterKnife.bind((View) this);
    }

    public void setImageUrl(String url) {
        this.image.setImageUrl(url);
    }

    public void setTitle(String titleString) {
        this.title.setText(titleString);
    }

    public void setSubtitle(String subtitleString) {
        this.subtitle.setText(subtitleString);
    }
}
