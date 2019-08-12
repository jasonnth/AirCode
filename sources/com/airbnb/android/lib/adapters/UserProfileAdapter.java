package com.airbnb.android.lib.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View.OnClickListener;
import com.airbnb.android.contentframework.viewcomponents.viewmodels.StoryCardFeedItemEpoxyModel_;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.android.core.DisplayOptions.DisplayType;
import com.airbnb.android.core.enums.WorkEmailStatus;
import com.airbnb.android.core.identity.IdentityVerificationUtil;
import com.airbnb.android.core.models.Article;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.models.UserFlag;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.CarouselModel_;
import com.airbnb.android.core.viewcomponents.models.DeprecatedCarouselEpoxyModel;
import com.airbnb.android.core.viewcomponents.models.DeprecatedCarouselEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.EditorialMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.HomeCardEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.HomeReviewRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.InlineTipRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.TextRowEpoxyModel_;
import com.airbnb.android.core.wishlists.WishListableData;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.erf.Experiments;
import com.airbnb.jitney.event.logging.WishlistSource.p295v3.C2813WishlistSource;
import com.airbnb.p027n2.components.ProfileLinkRowModel_;
import com.airbnb.p027n2.utils.TextUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class UserProfileAdapter extends AirEpoxyAdapter {
    private final TextRowEpoxyModel_ aboutModel = new TextRowEpoxyModel_().hide();
    private final ClickListener clickListener;
    private final StandardRowEpoxyModel_ connectedAccountsModel = new StandardRowEpoxyModel_().title(C0880R.string.section_header_connected_accounts).hide();
    private final Context context;
    private final StandardRowEpoxyModel_ languagesModel = new StandardRowEpoxyModel_().title(C0880R.string.spoken_languages).hide();
    private final SectionHeaderEpoxyModel_ listingsHeaderModel = new SectionHeaderEpoxyModel_().hide();
    private final CarouselModel_ listingsModel = new CarouselModel_().hide();
    private final LoadingRowEpoxyModel_ loadingModel = new LoadingRowEpoxyModel_();
    private final EditorialMarqueeEpoxyModel_ marqueeEpoxyModel = new EditorialMarqueeEpoxyModel_();
    private final SimpleDateFormat monthYearSdf;
    private final StandardRowEpoxyModel_ reportUserModel = new StandardRowEpoxyModel_();
    private final StandardRowEpoxyModel_ responseRateModel = new StandardRowEpoxyModel_().hide();
    private final StandardRowEpoxyModel_ reviewCountAndVerifiedBadge = new StandardRowEpoxyModel_().hide();
    private final SectionHeaderEpoxyModel_ reviewsHeaderModel = new SectionHeaderEpoxyModel_().hide();
    private final HomeReviewRowEpoxyModel_ reviewsModel = new HomeReviewRowEpoxyModel_().hide();
    private final StandardRowEpoxyModel_ schoolModel = new StandardRowEpoxyModel_().title(C0880R.string.school).hide();
    private final LinkActionRowEpoxyModel_ seeAllReviewsModel = new LinkActionRowEpoxyModel_().textRes(C0880R.string.see_all_reviews).hide();
    private final LinkActionRowEpoxyModel_ seeAllStoriesModelCNOnly = new LinkActionRowEpoxyModel_().hide();
    private final InlineTipRowEpoxyModel_ seeProfilePhotoAfterAccept = new InlineTipRowEpoxyModel_().hide();
    private final boolean shouldHideReviews;
    private final SectionHeaderEpoxyModel_ storiesHeaderModelCNOnly = new SectionHeaderEpoxyModel_().hide();
    private final DeprecatedCarouselEpoxyModel<StoriesAdapterCNOnly> storiesModelCNOnly = new DeprecatedCarouselEpoxyModel_().noBottomPadding(true).showDivider(true).hide();
    private final StandardRowEpoxyModel_ userSinceModel = new StandardRowEpoxyModel_().hide();
    private final ProfileLinkRowModel_ verificationsModel = new ProfileLinkRowModel_().title(C0880R.string.section_header_verified_info).linkText(C0880R.string.verified_id_learn_more_sentance_case).hide();
    private final ProfileLinkRowModel_ workModel = new ProfileLinkRowModel_().title(C0880R.string.work).hide();

    public interface ClickListener {
        void onAddOrVerifyWorkEmail(WorkEmailStatus workEmailStatus);

        void onIdentityLearnMore();

        void onListingClicked(Listing listing);

        void onReportUser(UserFlag userFlag);

        void onSeeAllReviewsClicked();

        void onSeeAllStoriesClicked();

        void onStoryClicked(Article article);

        void onVerifyMyIdentity();

        void onViewVerificationsClicked(User user);
    }

    private static final class StoriesAdapterCNOnly extends AirEpoxyAdapter {
        private final ClickListener clickListener;
        private final Context context;

        public StoriesAdapterCNOnly(Context context2, ClickListener clickListener2) {
            this.context = context2;
            this.clickListener = clickListener2;
        }

        /* access modifiers changed from: 0000 */
        public void setStories(List<Article> stories) {
            this.models.clear();
            for (Article article : stories) {
                this.models.add(new StoryCardFeedItemEpoxyModel_().article(article).displayOptions(DisplayOptions.forStoryCard(this.context, DisplayType.Horizontal)).onClickListener(UserProfileAdapter$StoriesAdapterCNOnly$$Lambda$1.lambdaFactory$(this, article)).withNoBottomPaddingLayout());
            }
        }
    }

    public UserProfileAdapter(Context context2, ClickListener clickListener2, boolean shouldHideReviews2) {
        super(true);
        this.context = context2;
        this.clickListener = clickListener2;
        this.shouldHideReviews = shouldHideReviews2;
        this.monthYearSdf = new SimpleDateFormat(context2.getString(C0880R.string.month_name_format), Locale.getDefault());
        this.storiesModelCNOnly.adapter(new StoriesAdapterCNOnly(context2, clickListener2));
        if (Experiments.shouldPromoteLanguage()) {
            addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.marqueeEpoxyModel, this.seeProfilePhotoAfterAccept, this.reviewCountAndVerifiedBadge, this.aboutModel, this.responseRateModel, this.languagesModel, this.userSinceModel, this.workModel, this.schoolModel, this.reviewsHeaderModel, this.reviewsModel, this.seeAllReviewsModel, this.storiesHeaderModelCNOnly, this.storiesModelCNOnly, this.seeAllStoriesModelCNOnly, this.verificationsModel, this.connectedAccountsModel, this.listingsHeaderModel, this.listingsModel, this.reportUserModel, this.loadingModel});
            return;
        }
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.marqueeEpoxyModel, this.seeProfilePhotoAfterAccept, this.reviewCountAndVerifiedBadge, this.aboutModel, this.userSinceModel, this.workModel, this.schoolModel, this.languagesModel, this.responseRateModel, this.reviewsHeaderModel, this.reviewsModel, this.seeAllReviewsModel, this.storiesHeaderModelCNOnly, this.storiesModelCNOnly, this.seeAllStoriesModelCNOnly, this.verificationsModel, this.connectedAccountsModel, this.listingsHeaderModel, this.listingsModel, this.reportUserModel, this.loadingModel});
    }

    public void updateForUser(User user, List<Listing> listings, boolean isSelf, boolean hideProfilePhoto) {
        boolean hasUser;
        boolean z = true;
        if (user != null) {
            hasUser = true;
        } else {
            hasUser = false;
        }
        for (EpoxyModel<?> model : this.models) {
            model.show(hasUser);
        }
        this.marqueeEpoxyModel.show();
        LoadingRowEpoxyModel_ loadingRowEpoxyModel_ = this.loadingModel;
        if (hasUser) {
            z = false;
        }
        loadingRowEpoxyModel_.show(z);
        if (hasUser) {
            updateModels(user, listings, isSelf, hideProfilePhoto);
        }
        notifyDataSetChanged();
    }

    private void updateModels(User user, List<Listing> listings, boolean isSelf, boolean hideProfilePhoto) {
        String string;
        this.marqueeEpoxyModel.imageUrl(user.getPictureUrlLarge()).hideImage(hideProfilePhoto).title(user.getName()).description(user.getLocation()).show();
        this.seeProfilePhotoAfterAccept.title(this.context.getString(C0880R.string.see_photo_after_accepted_reservation)).show(hideProfilePhoto);
        int reviewCount = user.getRevieweeCount();
        StandardRowEpoxyModel_ standardRowEpoxyModel_ = this.reviewCountAndVerifiedBadge;
        if (reviewCount <= 0 || this.shouldHideReviews) {
            string = this.context.getString(C0880R.string.user_profile_verified);
        } else {
            string = this.context.getResources().getQuantityString(C0880R.plurals.reviews_verified, reviewCount, new Object[]{Integer.valueOf(reviewCount)});
        }
        standardRowEpoxyModel_.title((CharSequence) string).rowDrawableRes(C0880R.C0881drawable.user_profile_verified_id).show(user.isVerifiedId());
        UserFlag flag = user.getUserFlag();
        if (flag == null || flag.isRedacted()) {
            this.reportUserModel.title(C0880R.string.report_user).disclosure().clickListener(UserProfileAdapter$$Lambda$1.lambdaFactory$(this, flag)).show();
        } else {
            this.reportUserModel.title(C0880R.string.user_reported).rowDrawable((Drawable) null).clickListener((OnClickListener) null).show();
        }
        this.aboutModel.text(user.getAbout()).show(!TextUtils.isEmpty(user.getAbout()));
        if (Experiments.shouldPromoteLanguage()) {
            this.aboutModel.maxLines(5);
        }
        if (user.getCreatedAt() == null) {
            this.userSinceModel.hide();
        } else {
            this.userSinceModel.title((CharSequence) this.context.getString(C0880R.string.member_since_date, new Object[]{user.getCreatedAt().format(this.monthYearSdf)})).show();
        }
        this.workModel.subtitle(user.getWork()).show(!TextUtils.isEmpty(user.getWork()));
        this.schoolModel.subtitle((CharSequence) user.getSchool()).show(!TextUtils.isEmpty(user.getSchool()));
        String languages = ListUtils.isEmpty((Collection<?>) user.getLanguages()) ? null : TextUtil.join(user.getLanguages());
        this.languagesModel.subtitle((CharSequence) languages).show(languages != null);
        this.responseRateModel.title((CharSequence) this.context.getString(C0880R.string.response_rate_inline, new Object[]{user.getResponseRate()})).show(user.getListingsCount() > 0);
        this.reviewsHeaderModel.title(this.context.getResources().getQuantityString(C0880R.plurals.reviews, reviewCount, new Object[]{Integer.valueOf(reviewCount)})).show(reviewCount > 0 && !this.shouldHideReviews);
        this.reviewsModel.review(user.getRecentReview()).show(user.getRecentReview() != null && !this.shouldHideReviews);
        this.seeAllReviewsModel.clickListener(UserProfileAdapter$$Lambda$2.lambdaFactory$(this)).show(reviewCount > 0 && !this.shouldHideReviews);
        if (user.getContentFrameworkArticlesCount() <= 0 || user.getContentFrameworkArticles() == null || user.getContentFrameworkArticles().isEmpty()) {
            this.storiesHeaderModelCNOnly.hide();
            this.storiesModelCNOnly.hide();
            this.seeAllStoriesModelCNOnly.hide();
        } else {
            this.storiesHeaderModelCNOnly.title(this.context.getString(C0880R.string.story_many, new Object[]{Integer.valueOf(user.getContentFrameworkArticlesCount())}));
            ((StoriesAdapterCNOnly) this.storiesModelCNOnly.adapter()).setStories(user.getContentFrameworkArticles());
            this.seeAllStoriesModelCNOnly.text(this.context.getString(C0880R.string.story_see_all_stories_of_user, new Object[]{Integer.valueOf(user.getContentFrameworkArticlesCount())})).clickListener(UserProfileAdapter$$Lambda$3.lambdaFactory$(this)).show(user.getContentFrameworkArticlesCount() > user.getContentFrameworkArticles().size());
        }
        Set<String> verifications = IdentityVerificationUtil.getUserVerifications(user);
        Set<String> connectedAccounts = IdentityVerificationUtil.getConnectedVerifications(user);
        if (!verifications.isEmpty()) {
            this.verificationsModel.subtitle(TextUtil.join(new ArrayList(verifications))).show();
            if (!isSelf || user.isVerifiedId()) {
                this.verificationsModel.linkText(C0880R.string.verified_id_learn_more_sentance_case).onClickLinkListener(UserProfileAdapter$$Lambda$5.lambdaFactory$(this)).show();
            } else {
                this.verificationsModel.linkText(C0880R.string.account_verifications_provide_id).onClickLinkListener(UserProfileAdapter$$Lambda$4.lambdaFactory$(this)).show();
            }
        } else {
            this.verificationsModel.hide();
        }
        if (!connectedAccounts.isEmpty()) {
            this.connectedAccountsModel.subtitle((CharSequence) TextUtil.join(new ArrayList(connectedAccounts))).show();
        } else {
            this.connectedAccountsModel.hide();
        }
        if (listings == null || listings.size() <= 0) {
            this.listingsHeaderModel.hide();
            this.listingsModel.hide();
        } else {
            this.listingsHeaderModel.title(this.context.getResources().getQuantityString(C0880R.plurals.x_homes, user.getListingsCount(), new Object[]{Integer.valueOf(user.getListingsCount())})).show();
            List<HomeCardEpoxyModel_> homeModels = new ArrayList<>(listings.size());
            for (Listing listing : listings) {
                homeModels.add(new HomeCardEpoxyModel_().listing(listing).wishListableData(WishListableData.forHome(listing).source(C2813WishlistSource.UserProfile).build()).displayOptions(DisplayOptions.forHomeCard(this.context, DisplayType.Horizontal)).clickListener(UserProfileAdapter$$Lambda$6.lambdaFactory$(this, listing)));
            }
            this.listingsModel.show().models(homeModels);
        }
        this.loadingModel.show(user.getListingsCount() > 0 && listings == null);
    }

    public void updateWorkModel(WorkEmailStatus workEmailStatus, boolean isSelf) {
        if (isSelf) {
            switch (workEmailStatus) {
                case None:
                    this.workModel.linkText(C0880R.string.add_work_email).onClickLinkListener(UserProfileAdapter$$Lambda$7.lambdaFactory$(this, workEmailStatus)).show();
                    break;
                case Pending:
                    this.workModel.linkText(C0880R.string.verify_work_email).onClickLinkListener(UserProfileAdapter$$Lambda$8.lambdaFactory$(this, workEmailStatus)).show();
                    break;
            }
            notifyModelChanged(this.workModel);
        }
    }
}
