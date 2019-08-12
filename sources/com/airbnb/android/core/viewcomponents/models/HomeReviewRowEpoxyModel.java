package com.airbnb.android.core.viewcomponents.models;

import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.AttributedTextRange;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.utils.TranslationUtils;
import com.airbnb.android.utils.LanguageUtils;
import com.airbnb.p027n2.components.HomeReviewRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import com.airbnb.p027n2.primitives.fonts.CustomFontSpan;
import com.airbnb.p027n2.primitives.fonts.Font;
import java.text.SimpleDateFormat;
import java.util.List;

public abstract class HomeReviewRowEpoxyModel extends AirEpoxyModel<HomeReviewRow> {
    List<AttributedTextRange> attributedTextRangeList;
    OnClickListener onClickListener;
    OnClickListener reportClickListener;
    int reportString;
    Review review;
    String reviewDateString;
    boolean showListingName;
    boolean showPrivateComment;
    boolean showPublicFeedback;
    boolean showStarRating;
    boolean showTranslation;
    OnClickListener translateClickListener;

    public void bind(HomeReviewRow view) {
        String str;
        super.bind(view);
        SimpleDateFormat dateFormat = new SimpleDateFormat(view.getResources().getString(C0716R.string.month_name_short_format));
        view.setReviewerName(this.review.getAuthor().getFirstName());
        view.setReviewDate(TextUtils.isEmpty(this.reviewDateString) ? this.review.getFormattedCreationDate(dateFormat) : this.reviewDateString);
        view.setReportText(this.reportString);
        view.setReported(this.review.shouldShowFlagged());
        User u = this.review.getAuthor();
        view.setThumbnailUrl(u.getThumbnailUrl() == null ? u.getPictureUrl() : u.getThumbnailUrl());
        view.setReadMoreText(C0716R.string.read_more_lower_cased);
        setPublicComment(view);
        if (this.showPrivateComment) {
            view.setPrivateComment(view.getContext().getString(C0716R.string.review_private_comment_title), this.review.getPrivateFeedback());
        } else {
            view.setPrivateComment(null, null);
        }
        if (this.showPublicFeedback) {
            view.setPublicResponse(view.getContext().getString(C0716R.string.review_public_response_title), this.review.getResponse());
        } else {
            view.setPublicResponse(null, null);
        }
        if (this.showListingName) {
            str = this.review.getReservation().getListing().getName();
        } else {
            str = null;
        }
        view.setListingName(str);
        view.setReviewStars(this.showStarRating ? this.review.getAverageRating().intValue() : -1);
        if (this.onClickListener != null) {
            view.setOnClickListener(this.onClickListener);
        }
        view.setReportLinkClickListener(this.reportClickListener);
    }

    public void unbind(HomeReviewRow view) {
        super.unbind(view);
        view.setOnClickListener(null);
        view.setPublicComment(null);
    }

    private void setPublicComment(HomeReviewRow view) {
        boolean showTranslate;
        SpannableStringBuilder commentSpannable = new SpannableStringBuilder();
        String reviewLanguage = this.review.getLanguage();
        String translationDetails = "";
        if (reviewLanguage == null || this.translateClickListener == null || LanguageUtils.getLanguage().equals(reviewLanguage)) {
            showTranslate = false;
        } else {
            showTranslate = true;
        }
        if (showTranslate) {
            commentSpannable.append(TranslationUtils.getTranslateLink(view.getContext(), reviewLanguage, this.showTranslation, C0716R.color.n2_text_color_actionable, this.translateClickListener));
            if (this.showTranslation) {
                translationDetails = view.getResources().getString(C0716R.string.translated_via_google_translate, new Object[]{LanguageUtils.getDisplayLanguage()});
            }
        }
        commentSpannable.append((!this.review.hasTranslation() || !this.showTranslation) ? this.review.getPublicFeedback().trim() : this.review.getTranslation());
        if (!showTranslate && !this.review.hasTranslation() && this.attributedTextRangeList != null) {
            for (AttributedTextRange range : this.attributedTextRangeList) {
                commentSpannable.setSpan(new CustomFontSpan(view.getContext(), Font.CircularBold), range.getStart(), range.getStart() + range.getLength(), 33);
            }
        }
        view.setPublicComment(commentSpannable);
        view.setTranslationDetails(translationDetails);
    }

    public int getDividerViewType() {
        return 0;
    }
}
