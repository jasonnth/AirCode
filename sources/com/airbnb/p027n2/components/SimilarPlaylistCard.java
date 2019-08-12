package com.airbnb.p027n2.components;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.SimilarPlaylistCard */
public class SimilarPlaylistCard extends LinearLayout {
    @BindView
    View frame;
    @BindView
    AirImageView imageView;
    @BindView
    AirTextView subText;
    @BindView
    AirTextView titleTextView;

    public SimilarPlaylistCard(Context context) {
        super(context);
        init();
    }

    public SimilarPlaylistCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SimilarPlaylistCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(0);
        inflate(getContext(), R.layout.n2_similar_playlist_card, this);
        ButterKnife.bind((View) this);
    }

    public void setImageUrl(String url) {
        this.imageView.setImageUrl(url);
        this.frame.invalidate();
    }

    public void setupTitle(String title) {
        ViewLibUtils.setVisibleIf(this.titleTextView, !TextUtils.isEmpty(title));
        this.titleTextView.setText(title);
    }

    public void setupSubtext(String subtext) {
        ViewLibUtils.setVisibleIf(this.subText, !TextUtils.isEmpty(subtext));
        this.subText.setText(subtext);
    }

    public void clearImage() {
        this.imageView.clear();
    }

    public static void mock(SimilarPlaylistCard card) {
        card.setupTitle("No-frills dumplings at Dim Sum Club");
        card.setupSubtext("Food & Drink");
    }
}
