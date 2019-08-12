package com.airbnb.android.contentframework;

import com.airbnb.android.contentframework.data.StoryPublishArguments;
import com.airbnb.android.contentframework.fragments.StoryFeedFragment.Mode;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.Article;
import com.airbnb.android.core.models.ArticleComment;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.StoryFeedTopTile;
import com.airbnb.android.core.models.StoryProductLinkDetails;
import com.airbnb.android.core.requests.UpdateReviewRequest;
import com.airbnb.android.core.wishlists.WishListManager;
import com.airbnb.android.utils.Strap;
import com.facebook.react.uimanager.ViewProps;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class ContentFrameworkAnalytics extends BaseAnalytics {
    public static final String ARTICLE_BODY = "body";
    public static final String ARTICLE_CARD = "article_card";
    private static final String ARTICLE_ID = "article_id";
    private static final String CLEAR_PLACE_TAG = "clear_place_tag";
    private static final String COMMENT_ID = "comment_id";
    private static final String COMPOSER_BUTTON = "composer_button";
    private static final String DELETE_IMAGE = "delete_image";
    public static final String DESTINATION_CARD = "destination_card";
    private static final String EVENT_NAME = "content_framework";
    static final int FIFTY = 50;
    public static final String FOOTER = "footer";
    public static final String IMAGE = "image";
    private static final String IMAGE_OPTION = "image_option";
    private static final String INFO_BUTTON = "info_button";
    public static final String LINK = "link";
    public static final String LISTING_CARD = "listing_card";
    static final int ONE_HUNDRED = 100;
    private static final String PLACE_TAG_PILL = "place_tag";
    public static final String PRODUCT_LINK = "product_link";
    public static final String PRODUCT_LINK_WISHLIST = "product_link_wishlist";
    private static final String RESERVATION = "reservation";
    private static final String SEE_MORE = "see_more";
    static final int SEVENTY_FIVE = 75;
    public static final String SIMPLE_ARTICLE = "simple_article";
    private static final String TARGET_ID = "target_id";
    private static final String TEMPLATE_TYPE = "template_type";
    private static final String TIME_SINCE_IMPRESSION_MS = "time_since_impression_ms";
    static final int TWENTY_FIVE = 25;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ArticleSection {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ClickTarget {
    }

    enum Page {
        StoryFeed(ContentFrameworkUtil.STORY_FEED),
        StorySearchResult("story_search_result"),
        GuestHome(ContentFrameworkUtil.GUEST_HOME),
        Article("article"),
        Comments(UpdateReviewRequest.KEY_PUBLIC_FEEDBACK),
        StoryTripPicker("story_trip_picker"),
        StoryImagePicker("story_image_picker"),
        StoryComposer("story_composer"),
        StoryBackgroundPublisher(ContentFrameworkUtil.STORY_BACKGROUND_PUBLISHER);
        
        /* access modifiers changed from: private */
        public final String key;

        private Page(String key2) {
            this.key = key2;
        }
    }

    public enum PublishErrorType {
        CreateStoryError("create_story_error"),
        ProcessPhotoError("process_photo_error"),
        UploadPhotoError("upload_photo_error"),
        EditStoryError("edit_story_error");
        
        final String loggingName;

        private PublishErrorType(String loggingName2) {
            this.loggingName = loggingName2;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TemplateType {
    }

    public static void trackSeeMoreClickedOnTripPicker() {
        trackSimpleClickEvent(Page.StoryTripPicker, SEE_MORE);
    }

    public static void trackReservationSelectedOnTripPicker(Reservation reservation) {
        track(Page.StoryTripPicker, "click", Strap.make().mo11639kv(BaseAnalytics.TARGET, "reservation").mo11639kv(TARGET_ID, reservation.getConfirmationCode()));
    }

    public static void trackEmptyReservationViewOnTripPicker() {
        track(Page.StoryTripPicker, "view_empty_trip_messaage", Strap.make());
    }

    public static void trackImagePickerDone(int selectedImageCount) {
        track(Page.StoryImagePicker, "click", Strap.make().mo11639kv(BaseAnalytics.TARGET, "done").mo11637kv("selected_image_count", selectedImageCount));
    }

    public static void trackAddImage(int selectedImageCount) {
        track(Page.StoryComposer, "click", Strap.make().mo11639kv(BaseAnalytics.TARGET, "add_image").mo11637kv("selected_image_count", selectedImageCount));
    }

    public static void trackImageOptionClicked() {
        trackSimpleClickEvent(Page.StoryComposer, IMAGE_OPTION);
    }

    public static void trackImageDeleted() {
        trackSimpleClickEvent(Page.StoryComposer, DELETE_IMAGE);
    }

    public static void trackPlageTagPillClicked() {
        trackSimpleClickEvent(Page.StoryComposer, PLACE_TAG_PILL);
    }

    public static void trackPlageTagCleared(String googlePlaceId) {
        track(Page.StoryComposer, "click", Strap.make().mo11639kv(BaseAnalytics.TARGET, CLEAR_PLACE_TAG).mo11639kv("google_place_id", googlePlaceId));
    }

    public static void trackComposerInfoButtonClicked() {
        trackSimpleClickEvent(Page.StoryComposer, INFO_BUTTON);
    }

    public static void trackPublishButtonClicked(StoryPublishArguments arguments) {
        track(Page.StoryComposer, "click", Strap.make().mo11639kv(BaseAnalytics.TARGET, "publish").mix(arguments.getLoggingStrap()));
    }

    public static void trackPublishError(StoryPublishArguments arguments, PublishErrorType errorType) {
        track(Page.StoryBackgroundPublisher, "error", Strap.make().mo11639kv("error_type", errorType.loggingName).mix(arguments.getLoggingStrap()));
    }

    public static void trackPublishSuccess(StoryPublishArguments arguments) {
        track(Page.StoryBackgroundPublisher, "success", arguments.getLoggingStrap());
    }

    public static void trackStoryComposerPillClicked() {
        track(Page.StoryFeed, "click", Strap.make().mo11639kv(BaseAnalytics.TARGET, COMPOSER_BUTTON));
    }

    public static void trackStoryFeedImpressionStart(Mode mode) {
        track(mode.navigationTag, "impression", Strap.make());
    }

    public static void trackStoryFeedImpressionEnd(long timeSinceImpressionMs, Mode mode) {
        track(mode.navigationTag, "impression_end", Strap.make().mo11638kv(TIME_SINCE_IMPRESSION_MS, timeSinceImpressionMs));
    }

    public static void trackStoryFeedItemClicked(Article article, int pos, Mode mode) {
        track(mode.navigationTag, "click", Strap.make().mo11639kv(BaseAnalytics.TARGET, ARTICLE_CARD).mo11638kv(TARGET_ID, article.getId()).mo11637kv(ViewProps.POSITION, pos));
    }

    public static void trackPullToRefresh(int loadedArticlesCount, Mode mode) {
        track(mode.navigationTag, "refresh", Strap.make().mo11637kv("loaded_articles_count", loadedArticlesCount));
    }

    public static void trackLoadMoreStories(int totalArticlesCount, Mode mode) {
        track(mode.navigationTag, "load_more", Strap.make().mo11637kv("total_articles_count", totalArticlesCount));
    }

    public static void trackSearchButtonClick() {
        track(Page.StoryFeed, "click", Strap.make().mo11639kv(BaseAnalytics.TARGET, "search_button"));
    }

    public static void trackTopTileClicked(StoryFeedTopTile topTile) {
        track(Page.StoryFeed, "click", Strap.make().mo11639kv(BaseAnalytics.TARGET, "top_tile").mix(topTile.getLoggingParams()));
    }

    public static void trackDeleteStory(long articleId) {
        track(Page.Article, "click", Strap.make().mo11639kv(BaseAnalytics.TARGET, "delete_story").mo11638kv(TARGET_ID, articleId));
    }

    public static void trackReportStory(long articleId) {
        track(Page.Article, "click", Strap.make().mo11639kv(BaseAnalytics.TARGET, "report_story").mo11638kv(TARGET_ID, articleId));
    }

    public static void trackReportComment(long articleId, long commentId) {
        track(Page.Article, "click", Strap.make().mo11639kv(BaseAnalytics.TARGET, "report_comment").mo11638kv(COMMENT_ID, commentId).mo11638kv(ARTICLE_ID, articleId));
    }

    public static void trackCommentAuthorClicked(long articleId, long authorId, long commentId) {
        track(Page.Comments, "click", Strap.make().mo11639kv(BaseAnalytics.TARGET, "comment_author").mo11638kv(TARGET_ID, authorId).mo11638kv(ARTICLE_ID, articleId).mo11638kv(COMMENT_ID, commentId));
    }

    public static void trackParentCommentAuthorClicked(long articleId, long authorId, long commentId) {
        track(Page.Comments, "click", Strap.make().mo11639kv(BaseAnalytics.TARGET, "parent_comment_author").mo11638kv(TARGET_ID, authorId).mo11638kv(ARTICLE_ID, articleId).mo11638kv(COMMENT_ID, commentId));
    }

    public static void trackAuthorRowClicked(long articleId, long authorId) {
        track(Page.Article, "click", Strap.make().mo11639kv(BaseAnalytics.TARGET, "story_author").mo11638kv(TARGET_ID, authorId).mo11638kv(ARTICLE_ID, articleId));
    }

    public static void trackReadArticleImpression(long articleId, String referrer, String templateType) {
        track(Page.Article, "impression", Strap.make().mo11638kv(ARTICLE_ID, articleId).mo11639kv(TEMPLATE_TYPE, templateType).mo11639kv("referrer", referrer));
    }

    public static void trackLeaveArticle(long articleId, String templateType, long timeSinceImpressionMs) {
        track(Page.Article, "close_article", Strap.make().mo11638kv(ARTICLE_ID, articleId).mo11639kv(TEMPLATE_TYPE, templateType).mo11638kv(TIME_SINCE_IMPRESSION_MS, timeSinceImpressionMs));
    }

    public static void trackShareArticle(long articleId) {
        track(Page.Article, "click", Strap.make().mo11638kv(ARTICLE_ID, articleId).mo11639kv(BaseAnalytics.TARGET, "share_button"));
    }

    public static void trackArticleLike(long articleId, boolean liked) {
        track(Page.Article, liked ? "unlike_article" : "like_article", Strap.make().mo11638kv(ARTICLE_ID, articleId));
    }

    public static void trackHottestCommentLike(long articleId, long commentId, boolean liked) {
        trackCommentLikeActions(Page.Article, articleId, commentId, liked);
    }

    public static void trackCommentLike(long articleId, long commentId, boolean liked) {
        trackCommentLikeActions(Page.Comments, articleId, commentId, liked);
    }

    public static void trackAddComment(long articleId, ArticleComment parentComment) {
        track(Page.Article, "post_comment", Strap.make().mo11638kv(ARTICLE_ID, articleId).mo11638kv("parent_comment_id", parentComment != null ? parentComment.getId() : 0));
    }

    public static void trackDeleteComment(long articleId, long commentId) {
        track(Page.Article, "delete_comment", Strap.make().mo11638kv(ARTICLE_ID, articleId).mo11638kv(COMMENT_ID, commentId));
    }

    public static void trackImageClick(long articleId, String imageIdentifier, String templateType) {
        trackFooterSectionClick(articleId, IMAGE, imageIdentifier, templateType);
    }

    public static void trackRelatedArticleClick(long existingArticleId, long clickedArticleId, String templateType) {
        trackFooterSectionClick(existingArticleId, ARTICLE_CARD, String.valueOf(clickedArticleId), templateType);
    }

    public static void trackSeeAllRelatedTileClicked(long articleId, String searchTerm) {
        track(Page.Article, "click", Strap.make().mo11639kv(BaseAnalytics.TARGET, "see_all_related_stories").mo11638kv(ARTICLE_ID, articleId).mo11639kv("search_term", searchTerm));
    }

    public static void trackListingClick(long articleId, long listingId, String templateType) {
        trackFooterSectionClick(articleId, LISTING_CARD, String.valueOf(listingId), templateType);
    }

    public static void trackDestinationClick(long articleId, String destinationIdentifier, String templateType) {
        trackFooterSectionClick(articleId, DESTINATION_CARD, destinationIdentifier, templateType);
    }

    public static void trackProductLinkClick(long articleId, StoryProductLinkDetails details) {
        long j = articleId;
        trackClick(j, "simple_article", "body", PRODUCT_LINK, String.valueOf(details.getObjectId()), Strap.make().mo11639kv("object_type", details.getObjectTypeString()));
    }

    public static void trackProductLinkWishlistClick(long articleId, StoryProductLinkDetails details, WishListManager wishListManager) {
        long j = articleId;
        trackClick(j, "simple_article", "body", PRODUCT_LINK_WISHLIST, String.valueOf(details.getObjectId()), Strap.make().mo11639kv("object_type", details.getObjectTypeString()).mo11640kv("wishlisted", details.isWishlisted(wishListManager)));
    }

    private static void trackCommentLikeActions(Page page, long articleId, long commentId, boolean liked) {
        track(page, liked ? "like_comment" : "unlike_comment", Strap.make().mo11638kv(ARTICLE_ID, articleId).mo11638kv(COMMENT_ID, commentId));
    }

    private static void trackFooterSectionClick(long articleId, String target, String targetIdentifier, String templateType) {
        trackClick(articleId, templateType, FOOTER, target, targetIdentifier, null);
    }

    private static void trackClick(long articleId, String templateType, String section, String target, String targetIdentifier, Strap extraParams) {
        String targetObjectKey;
        char c = 65535;
        switch (target.hashCode()) {
            case -396205798:
                if (target.equals(PRODUCT_LINK_WISHLIST)) {
                    c = 6;
                    break;
                }
                break;
            case -394784583:
                if (target.equals(ARTICLE_CARD)) {
                    c = 2;
                    break;
                }
                break;
            case 3321850:
                if (target.equals("link")) {
                    c = 4;
                    break;
                }
                break;
            case 100313435:
                if (target.equals(IMAGE)) {
                    c = 3;
                    break;
                }
                break;
            case 1014323530:
                if (target.equals(PRODUCT_LINK)) {
                    c = 5;
                    break;
                }
                break;
            case 1204897953:
                if (target.equals(DESTINATION_CARD)) {
                    c = 1;
                    break;
                }
                break;
            case 1211395051:
                if (target.equals(LISTING_CARD)) {
                    c = 0;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                targetObjectKey = "target_listing_id";
                break;
            case 1:
                targetObjectKey = "target_destination";
                break;
            case 2:
                targetObjectKey = "target_article_id";
                break;
            case 3:
                targetObjectKey = "target_image_name";
                break;
            case 4:
                targetObjectKey = "target_link_url";
                break;
            case 5:
            case 6:
                targetObjectKey = "object_id";
                break;
            default:
                targetObjectKey = "unknown";
                break;
        }
        track(Page.Article, "click", Strap.make().mo11639kv(BaseAnalytics.SECTION, section).mo11639kv(TEMPLATE_TYPE, templateType).mo11639kv(BaseAnalytics.TARGET, target).mo11638kv(ARTICLE_ID, articleId).mo11639kv(targetObjectKey, targetIdentifier).mix(extraParams));
    }

    static void trackArticleReadProgress(long articleId, int percentageViewed, long timeSinceImpressionMillis, String templateType) {
        track(Page.Article, "read_article", Strap.make().mo11638kv(ARTICLE_ID, articleId).mo11639kv(TEMPLATE_TYPE, templateType).mo11637kv("percentage_viewed", percentageViewed).mo11638kv(TIME_SINCE_IMPRESSION_MS, timeSinceImpressionMillis));
    }

    private static void track(Page page, String operation, Strap strap) {
        track(page.key, operation, strap);
    }

    private static void track(NavigationTag navigationTag, String operation, Strap strap) {
        track(navigationTag.trackingName, operation, strap);
    }

    private static void track(String pageName, String operation, Strap strap) {
        AirbnbEventLogger.track("content_framework", strap.mo11639kv("page", pageName).mo11639kv(BaseAnalytics.OPERATION, operation).mo11639kv("datadog_key", "content_framework." + operation).mo11639kv("datadog_tags", String.format("page:%s,platform:android", new Object[]{pageName})));
    }

    private static void trackSimpleClickEvent(Page page, String target) {
        track(page, "click", Strap.make().mo11639kv(BaseAnalytics.TARGET, target));
    }
}
