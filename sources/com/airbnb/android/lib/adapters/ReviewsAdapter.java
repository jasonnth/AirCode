package com.airbnb.android.lib.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.p000v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.intents.P3ActivityIntents;
import com.airbnb.android.core.intents.UserProfileIntents;
import com.airbnb.android.core.interfaces.TranslateInterface;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.core.requests.AirRequestFactory.Consumer;
import com.airbnb.android.core.requests.TranslateReviewsRequest;
import com.airbnb.android.core.responses.TranslateReviewsResponse;
import com.airbnb.android.core.responses.TranslatedReview;
import com.airbnb.android.core.utils.ImageUtils;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.presenters.ReviewPresenter;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import p032rx.Observer;

public class ReviewsAdapter extends ArrayAdapter<Review> implements Consumer<Review> {
    private HashMap<Long, String> dateCache;
    /* access modifiers changed from: private */
    public final FragmentManager fragmentManager;
    private final SimpleDateFormat monthYearSdf;
    private OnClickListener onClickListener;
    private final RequestManager requestManager;
    /* access modifiers changed from: private */
    public final TranslateInterface translateInterface;

    class ReviewsViewHolder {
        @BindView
        TextView listingTitle;
        @BindView
        TextView privateFeedback;
        @BindView
        View privateFeedbackSection;
        @BindView
        TextView reviewComment;
        @BindView
        TextView reviewDate;
        @BindView
        TextView reviewResponseText;
        @BindView
        TextView reviewResponseTitle;
        @BindView
        TextView reviewerName;
        @BindView
        HaloImageView thumbnail;

        /* access modifiers changed from: 0000 */
        @OnClick
        public void showPrivateFeedbackTooltip() {
            ZenDialog.createSingleButtonDialog(C0880R.string.private_feedback_tooltip_info, C0880R.string.okay).show(ReviewsAdapter.this.fragmentManager, (String) null);
        }

        ReviewsViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public class ReviewsViewHolder_ViewBinding implements Unbinder {
        private ReviewsViewHolder target;
        private View view2131756948;

        public ReviewsViewHolder_ViewBinding(final ReviewsViewHolder target2, View source) {
            this.target = target2;
            target2.reviewComment = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.text_review_comments, "field 'reviewComment'", TextView.class);
            target2.reviewerName = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.text_reviewer_name, "field 'reviewerName'", TextView.class);
            target2.reviewDate = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.text_review_stay_date, "field 'reviewDate'", TextView.class);
            target2.reviewResponseTitle = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.text_review_response_title, "field 'reviewResponseTitle'", TextView.class);
            target2.reviewResponseText = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.text_review_response, "field 'reviewResponseText'", TextView.class);
            target2.privateFeedbackSection = Utils.findRequiredView(source, C0880R.C0882id.private_feedback_layout, "field 'privateFeedbackSection'");
            target2.privateFeedback = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.text_private_feedback, "field 'privateFeedback'", TextView.class);
            target2.thumbnail = (HaloImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.reviewer_photo, "field 'thumbnail'", HaloImageView.class);
            target2.listingTitle = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.reviews_listing_name, "field 'listingTitle'", TextView.class);
            View view = Utils.findRequiredView(source, C0880R.C0882id.grouped_cell_tooltip, "method 'showPrivateFeedbackTooltip'");
            this.view2131756948 = view;
            view.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View p0) {
                    target2.showPrivateFeedbackTooltip();
                }
            });
        }

        public void unbind() {
            ReviewsViewHolder target2 = this.target;
            if (target2 == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            target2.reviewComment = null;
            target2.reviewerName = null;
            target2.reviewDate = null;
            target2.reviewResponseTitle = null;
            target2.reviewResponseText = null;
            target2.privateFeedbackSection = null;
            target2.privateFeedback = null;
            target2.thumbnail = null;
            target2.listingTitle = null;
            this.view2131756948.setOnClickListener(null);
            this.view2131756948 = null;
        }
    }

    @SuppressLint({"SimpleDateFormat"})
    public ReviewsAdapter(Context context, TranslateInterface translateInterface2, FragmentManager fragmentManager2, RequestManager requestManager2) {
        super(context, C0880R.layout.ro_review_list_item, new ArrayList());
        this.requestManager = requestManager2;
        this.monthYearSdf = new SimpleDateFormat(context.getString(C0880R.string.month_name_format));
        this.translateInterface = translateInterface2;
        this.fragmentManager = fragmentManager2;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(C0880R.layout.ro_review_list_item, parent, false);
            convertView.setTag(new ReviewsViewHolder(convertView));
        }
        Review review = (Review) getItem(position);
        boolean useTranslation = this.translateInterface.shouldShowTranslations() && review.hasTranslation();
        ReviewsViewHolder holder = (ReviewsViewHolder) convertView.getTag();
        holder.reviewComment.setText(useTranslation ? review.getTranslation() : review.getPublicFeedback().trim());
        boolean hasResponse = !TextUtils.isEmpty(review.getResponse());
        ViewUtils.setVisibleIf((View) holder.reviewResponseTitle, hasResponse);
        ViewUtils.setVisibleIf((View) holder.reviewResponseText, hasResponse);
        if (hasResponse) {
            holder.reviewResponseTitle.setText(convertView.getContext().getString(C0880R.string.review_response_from_name, new Object[]{review.getRecipient().getFirstName()}));
            holder.reviewResponseText.setText(review.getResponse());
        }
        holder.reviewerName.setText(review.getAuthor().getFirstName());
        StringBuilder sb = new StringBuilder(getDateFormatted(review));
        if (useTranslation) {
            sb.append(getContext().getString(C0880R.string.bullet_with_space));
            sb.append(getContext().getString(C0880R.string.translated));
        }
        holder.reviewDate.setText(sb.toString());
        ImageUtils.setImageUrlForUser(holder.thumbnail, review.getAuthor());
        holder.thumbnail.setTag(holder.thumbnail.getId(), Long.valueOf(review.getAuthorId()));
        holder.thumbnail.setOnClickListener(getClickListener());
        TextView listing = holder.listingTitle;
        boolean showListing = ReviewPresenter.shouldShowListing(review);
        ViewUtils.setVisibleIf((View) listing, showListing);
        if (showListing) {
            listing.setText(review.getListingName());
            listing.setTag(listing.getId(), Long.valueOf(review.getListingId()));
            listing.setOnClickListener(getClickListener());
        }
        boolean hasPrivateFeedback = !TextUtils.isEmpty(review.getPrivateFeedback());
        ViewUtils.setVisibleIf(holder.privateFeedbackSection, hasPrivateFeedback);
        if (hasPrivateFeedback) {
            holder.privateFeedback.setText(review.getPrivateFeedback().trim());
        }
        return convertView;
    }

    public void addAll(Collection<? extends Review> collection) {
        for (Review review : collection) {
            remove(review);
            add(review);
        }
        notifyDataSetChanged();
        requestTranslations(collection);
    }

    private OnClickListener getClickListener() {
        if (this.onClickListener == null) {
            this.onClickListener = ReviewsAdapter$$Lambda$1.lambdaFactory$(this);
        }
        return this.onClickListener;
    }

    static /* synthetic */ void lambda$getClickListener$0(ReviewsAdapter reviewsAdapter, View v) {
        long listingOrUserId = ((Long) v.getTag(v.getId())).longValue();
        int viewId = v.getId();
        if (viewId == C0880R.C0882id.reviewer_photo) {
            reviewsAdapter.getContext().startActivity(UserProfileIntents.intentForUserId(reviewsAdapter.getContext(), listingOrUserId));
        } else if (viewId == C0880R.C0882id.reviews_listing_name) {
            reviewsAdapter.getContext().startActivity(P3ActivityIntents.withListingId(reviewsAdapter.getContext(), listingOrUserId));
        } else {
            throw new IllegalStateException("reviews adapter, unrecognized View ID, check that the tags are being set for the corresponding views");
        }
    }

    private void requestTranslations(Collection<? extends Review> collection) {
        if (!MiscUtils.shouldDisableTranslations()) {
            List<Long> reviewIds = new ArrayList<>();
            for (Review r : collection) {
                reviewIds.add(Long.valueOf(r.getId()));
            }
            new TranslateReviewsRequest(reviewIds).withListener((Observer) new NonResubscribableRequestListener<TranslateReviewsResponse>() {
                public void onResponse(TranslateReviewsResponse response) {
                    HashMap<Long, TranslatedReview> hashMap = response.reviewsAsHashMap();
                    boolean reviewHasTranslation = false;
                    for (int i = 0; i < ReviewsAdapter.this.getCount(); i++) {
                        Review review = (Review) ReviewsAdapter.this.getItem(i);
                        review.setTranslation((TranslatedReview) hashMap.get(Long.valueOf(review.getId())));
                        if (review.hasTranslation()) {
                            reviewHasTranslation = true;
                        }
                    }
                    if (reviewHasTranslation) {
                        ReviewsAdapter.this.translateInterface.translationsAreAvailable();
                        if (ReviewsAdapter.this.translateInterface.shouldShowTranslations()) {
                            ReviewsAdapter.this.notifyDataSetChanged();
                        }
                    }
                }

                public void onErrorResponse(AirRequestNetworkException error) {
                }
            }).execute(this.requestManager);
        }
    }

    private String getDateFormatted(Review review) {
        if (this.dateCache == null) {
            this.dateCache = new HashMap<>();
        }
        String result = (String) this.dateCache.get(Long.valueOf(review.getId()));
        if (result != null) {
            return result;
        }
        String result2 = review.getFormattedCreationDate(this.monthYearSdf);
        this.dateCache.put(Long.valueOf(review.getId()), result2);
        return result2;
    }
}
