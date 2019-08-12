package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.ArticleSummaryRow */
public class ArticleSummaryRow extends RelativeLayout implements DividerView {
    @BindView
    AirTextView commentCount;
    @BindView
    AirImageView commentIcon;
    @BindView
    AirImageView coverImage;
    @BindView
    View divider;
    @BindView
    AirTextView likeCount;
    @BindView
    AirImageView likeIcon;
    @BindView
    AirTextView tag;
    @BindView
    AirTextView title;

    public ArticleSummaryRow(Context context) {
        super(context);
        init(null);
    }

    public ArticleSummaryRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ArticleSummaryRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_article_summary_row, this);
        ButterKnife.bind((View) this);
    }

    public void setTitle(CharSequence titleText) {
        this.title.setText(titleText);
    }

    public void setTag(CharSequence tagText) {
        this.tag.setText(tagText);
    }

    public void setCommentCount(int comments) {
        this.commentCount.setText(Integer.toString(comments));
    }

    public void setLikeCount(int likes) {
        this.likeCount.setText(Integer.toString(likes));
    }

    public void setCoverImageUrl(String url) {
        this.coverImage.setImageUrl(url);
    }

    public void showTag(boolean show) {
        ViewLibUtils.setVisibleIf(this.tag, show);
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }

    public static void mock(ArticleSummaryRow view) {
        view.setTitle("Test");
        view.setTag("Tag");
        view.setCommentCount(10);
        view.setLikeCount(232);
        view.setCoverImageUrl("https://a0.muscache.com/im/pictures/40527001/725cf38d_original.jpg?aki_policy=x_large");
    }
}
