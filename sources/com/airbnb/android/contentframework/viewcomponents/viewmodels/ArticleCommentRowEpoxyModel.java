package com.airbnb.android.contentframework.viewcomponents.viewmodels;

import android.content.Context;
import android.support.p000v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.View;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.contentframework.CommentActionListener;
import com.airbnb.android.contentframework.views.ArticleCommentRow;
import com.airbnb.android.core.models.ArticleComment;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import com.airbnb.p027n2.utils.TextUtil;

public abstract class ArticleCommentRowEpoxyModel extends AirEpoxyModel<ArticleCommentRow> {
    ArticleComment comment;
    CommentActionListener commentActionListener;

    private static abstract class UserNameSpan extends ClickableSpan {
        private final int color;

        public UserNameSpan(int color2) {
            this.color = color2;
        }

        public void updateDrawState(TextPaint ds) {
            ds.setColor(this.color);
            ds.setUnderlineText(false);
        }
    }

    public void bind(ArticleCommentRow view) {
        super.bind(view);
        view.setCommentContent(this.comment.getContent());
        Context context = view.getContext();
        int userNameColor = ContextCompat.getColor(context, C5709R.color.n2_text_color_main);
        String authorName = this.comment.getAuthor().getFirstName();
        ClickableSpan authorClickableSpan = new UserNameSpan(userNameColor) {
            public void onClick(View widget) {
                ArticleCommentRowEpoxyModel.this.commentActionListener.onAuthorProfileClicked(ArticleCommentRowEpoxyModel.this.comment);
            }
        };
        SpannableStringBuilder builder = new SpannableStringBuilder(authorName);
        builder.setSpan(authorClickableSpan, 0, builder.length(), 17);
        if (this.comment.getParentComment() != null && !TextUtils.isEmpty(this.comment.getParentComment().getAuthor().getFirstName())) {
            builder.append(" ").append(TextUtil.makeColored(context, C5709R.color.n2_foggy, context.getString(C5709R.string.story_comment_label_reply_to))).append(" ");
            ClickableSpan parentCommentAuthorClickableSpan = new UserNameSpan(userNameColor) {
                public void onClick(View widget) {
                    ArticleCommentRowEpoxyModel.this.commentActionListener.onParentCommentAuthorProfileClicked(ArticleCommentRowEpoxyModel.this.comment);
                }
            };
            int start = builder.length();
            builder.append(this.comment.getParentComment().getAuthor().getFirstName());
            builder.setSpan(parentCommentAuthorClickableSpan, start, builder.length(), 17);
        }
        view.setCommentAuthorLabel(builder);
        view.setCommentDate(this.comment.getPublishedAtHumanReadable());
        view.setThumbnailUrl(this.comment.getAuthor().getPictureUrl());
        view.setLiked(this.comment.isLiked());
        view.setLikeCount(this.comment.getLikeCount().intValue());
        view.setLikeClickListener(ArticleCommentRowEpoxyModel$$Lambda$1.lambdaFactory$(this));
        view.setProfileClickListener(ArticleCommentRowEpoxyModel$$Lambda$2.lambdaFactory$(this));
        view.setOnClickListener(ArticleCommentRowEpoxyModel$$Lambda$3.lambdaFactory$(this));
    }

    public void unbind(ArticleCommentRow view) {
        super.unbind(view);
        view.setLikeClickListener(null);
        view.setProfileClickListener(null);
        view.setOnClickListener(null);
        view.setCommentAuthorLabel(null);
    }

    public int getDividerViewType() {
        return 0;
    }
}
