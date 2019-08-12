package com.airbnb.android.contentframework.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.core.models.StorySeeAllTile;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;

public class SeeAllStoriesCard extends FrameLayout {
    @BindView
    HaloImageView photoView;
    @BindView
    AirTextView searchTerm;
    @BindView
    AirTextView seeAllLabel;

    public SeeAllStoriesCard(Context context) {
        super(context);
        init(context);
    }

    public SeeAllStoriesCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SeeAllStoriesCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, C5709R.layout.see_all_stories_card, this);
        ButterKnife.bind((View) this);
    }

    public void bindData(StorySeeAllTile storySeeAllTile) {
        this.photoView.setImageUrl(storySeeAllTile.getPhotoUrl());
        this.searchTerm.setText(storySeeAllTile.getSearchTerm());
        this.seeAllLabel.setText(storySeeAllTile.getSeeAllText());
    }
}
