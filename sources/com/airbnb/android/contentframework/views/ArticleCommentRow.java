package com.airbnb.android.contentframework.views;

import android.content.Context;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

public class ArticleCommentRow extends LinearLayout implements DividerView {
    @BindView
    TextView authorName;
    @BindView
    TextView commentContent;
    @BindView
    TextView commentDate;
    @BindView
    View divider;
    @BindView
    AirTextView likeCount;
    @BindView
    AirImageView likeIcon;
    @BindView
    LinearLayout likeOverlay;
    @BindView
    AirImageView thumbnail;

    public interface Delegate {
        void onUserProfileClicked(long j);
    }

    public ArticleCommentRow(Context context) {
        super(context);
        init(null);
    }

    public ArticleCommentRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ArticleCommentRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        setOrientation(1);
        inflate(getContext(), C5709R.layout.article_comment_row, this);
        ButterKnife.bind((View) this);
    }

    public void setCommentAuthorLabel(Spannable spannable) {
        this.authorName.setText(spannable);
        this.authorName.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void setCommentDate(CharSequence date) {
        this.commentDate.setText(date);
    }

    public void setCommentContent(CharSequence comment) {
        this.commentContent.setText(comment);
    }

    public void setThumbnailUrl(String url) {
        this.thumbnail.setImageUrl(url);
    }

    public void setMaxLines(int maxLines) {
        this.commentContent.setMaxLines(maxLines);
    }

    public void setLiked(boolean liked) {
        this.likeIcon.setImageResource(liked ? C5709R.C5710drawable.ic_like_filled : C5709R.C5710drawable.ic_like);
    }

    public void setLikeCount(int likes) {
        this.likeCount.setText(Integer.toString(likes));
    }

    public void setLikeClickListener(OnClickListener likeClickListener) {
        this.likeOverlay.setOnClickListener(likeClickListener);
    }

    public void setProfileClickListener(OnClickListener profileClickListener) {
        this.thumbnail.setOnClickListener(profileClickListener);
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }
}
