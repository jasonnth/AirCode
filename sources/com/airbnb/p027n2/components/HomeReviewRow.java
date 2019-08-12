package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.p000v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.ExpandableTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.HomeReviewRow */
public class HomeReviewRow extends LinearLayout implements DividerView {
    @BindView
    View divider;
    @BindView
    TextView listingName;
    @BindView
    TextView privateComment;
    @BindView
    TextView privateCommentTitle;
    @BindView
    ViewGroup privateCommentsLayout;
    @BindView
    ExpandableTextView publicComment;
    @BindView
    TextView publicResponseComments;
    @BindView
    ViewGroup publicResponseLayout;
    @BindView
    TextView publicResponseTitle;
    @BindView
    RatingBar ratingBar;
    @BindView
    TextView reportLink;
    @BindView
    TextView reviewDate;
    @BindView
    TextView reviewerName;
    @BindView
    AirImageView thumbnail;
    @BindView
    TextView translationDetails;

    public HomeReviewRow(Context context) {
        super(context);
        init(null);
    }

    public HomeReviewRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public HomeReviewRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        setOrientation(1);
        inflate(getContext(), R.layout.n2_home_review_row, this);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_HomeReviewRow, 0, 0);
        showDivider(a.getBoolean(R.styleable.n2_HomeReviewRow_n2_showDivider, true));
        a.recycle();
    }

    public void setReviewerName(CharSequence name) {
        this.reviewerName.setText(name);
    }

    public void setReviewDate(CharSequence date) {
        ViewLibUtils.bindOptionalTextView(this.reviewDate, date);
    }

    public void setListingName(CharSequence name) {
        ViewLibUtils.bindOptionalTextView(this.listingName, name);
    }

    public void setPublicComment(CharSequence comment) {
        this.publicComment.setContentText(comment);
    }

    public void setMaxLines(int maxLines) {
        this.publicComment.setMaxLines(maxLines);
    }

    public void setReadMoreText(int readMoreText) {
        this.publicComment.setReadMoreText(getContext().getString(readMoreText));
    }

    public void setTranslationDetails(CharSequence text) {
        ViewLibUtils.setVisibleIf(this.translationDetails, !TextUtils.isEmpty(text));
        this.translationDetails.setText(text);
    }

    public void setReviewStars(int stars) {
        ViewLibUtils.setVisibleIf(this.ratingBar, stars > 0);
        if (stars > 0) {
            this.ratingBar.setRating((float) stars);
        }
    }

    public void setReportText(int textRes) {
        ViewLibUtils.bindOptionalTextView(this.reportLink, textRes);
    }

    public void setReported(boolean reported) {
        this.reportLink.setTextColor(ContextCompat.getColor(getContext(), reported ? R.color.n2_text_color_main : R.color.n2_text_color_actionable));
    }

    public void setReportLinkClickListener(OnClickListener listener) {
        this.reportLink.setOnClickListener(listener);
    }

    public void setPrivateComment(CharSequence title, CharSequence comment) {
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(comment)) {
            this.privateCommentsLayout.setVisibility(8);
            return;
        }
        this.privateCommentsLayout.setVisibility(0);
        this.privateCommentTitle.setText(title);
        this.privateComment.setText(comment);
    }

    public void setPublicResponse(CharSequence title, CharSequence comment) {
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(comment)) {
            this.publicResponseLayout.setVisibility(8);
            return;
        }
        this.publicResponseLayout.setVisibility(0);
        this.publicResponseTitle.setText(title);
        this.publicResponseComments.setText(comment);
    }

    public void setThumbnailUrl(String url) {
        this.thumbnail.setImageUrl(url);
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }

    public static void mock(HomeReviewRow row) {
        row.setListingName("Listing name");
        row.setPublicComment("Public comment");
        row.setReviewerName("Reviewer name");
        row.setReviewStars(4);
    }
}
