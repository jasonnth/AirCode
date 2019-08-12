package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.models.Article;
import com.airbnb.android.core.models.ArticleTag;
import com.airbnb.android.core.models.StoryElement;
import com.airbnb.android.core.models.StorySeeAllTile;
import com.airbnb.android.core.models.TravelDestination;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.models.UserFlag;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenArticle implements Parcelable {
    @JsonProperty("additional_cover_images")
    protected List<String> mAdditionalCoverImages;
    @JsonProperty("appendix")
    protected StoryElement mAppendix;
    @JsonProperty("article_category")
    protected String mArticleCategory;
    @JsonProperty("article_category_bg_color")
    protected String mArticleCategoryBgColor;
    @JsonProperty("author")
    protected String mAuthor;
    @JsonProperty("author_object")
    protected User mAuthorObject;
    @JsonProperty("comment_count")
    protected int mCommentCount;
    @JsonProperty("cover_image_preview_encoded_png")
    protected String mCoverImagePreview;
    @JsonProperty("cover_image_ratio")
    protected double mCoverImageRatio;
    @JsonProperty("xxl_cover_image_url")
    protected String mCoverImageUrl;
    @JsonProperty("destinations")
    protected List<TravelDestination> mDestinations;
    @JsonProperty("elements")
    protected List<StoryElement> mElements;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("like_count")
    protected int mLikeCount;
    @JsonProperty("liked")
    protected boolean mLiked;
    @JsonProperty("localized_article_location")
    protected String mLocalizedArticleLocation;
    @JsonProperty("published_at")
    protected AirDateTime mPublishedAt;
    @JsonProperty("published_at_human_readable")
    protected String mPublishedAtHumanReadable;
    @JsonProperty("related_articles")
    protected List<Article> mRelatedArticles;
    @JsonProperty("related_articles_see_all")
    protected StorySeeAllTile mRelatedArticlesSeeAll;
    @JsonProperty("subtitle")
    protected String mSubtitle;
    @JsonProperty("tags")
    protected List<ArticleTag> mTags;
    @JsonProperty("title")
    protected String mTitle;
    @JsonProperty("type")
    protected String mTypeStr;
    @JsonProperty("user_flag")
    protected UserFlag mUserFlag;

    protected GenArticle(AirDateTime publishedAt, List<Article> relatedArticles, List<ArticleTag> tags, List<StoryElement> elements, List<String> additionalCoverImages, List<TravelDestination> destinations, StoryElement appendix, StorySeeAllTile relatedArticlesSeeAll, String title, String subtitle, String author, String coverImageUrl, String coverImagePreview, String typeStr, String publishedAtHumanReadable, String localizedArticleLocation, String articleCategory, String articleCategoryBgColor, User authorObject, UserFlag userFlag, boolean liked, double coverImageRatio, int commentCount, int likeCount, long id) {
        this();
        this.mPublishedAt = publishedAt;
        this.mRelatedArticles = relatedArticles;
        this.mTags = tags;
        this.mElements = elements;
        this.mAdditionalCoverImages = additionalCoverImages;
        this.mDestinations = destinations;
        this.mAppendix = appendix;
        this.mRelatedArticlesSeeAll = relatedArticlesSeeAll;
        this.mTitle = title;
        this.mSubtitle = subtitle;
        this.mAuthor = author;
        this.mCoverImageUrl = coverImageUrl;
        this.mCoverImagePreview = coverImagePreview;
        this.mTypeStr = typeStr;
        this.mPublishedAtHumanReadable = publishedAtHumanReadable;
        this.mLocalizedArticleLocation = localizedArticleLocation;
        this.mArticleCategory = articleCategory;
        this.mArticleCategoryBgColor = articleCategoryBgColor;
        this.mAuthorObject = authorObject;
        this.mUserFlag = userFlag;
        this.mLiked = liked;
        this.mCoverImageRatio = coverImageRatio;
        this.mCommentCount = commentCount;
        this.mLikeCount = likeCount;
        this.mId = id;
    }

    protected GenArticle() {
    }

    public AirDateTime getPublishedAt() {
        return this.mPublishedAt;
    }

    @JsonProperty("published_at")
    public void setPublishedAt(AirDateTime value) {
        this.mPublishedAt = value;
    }

    public List<Article> getRelatedArticles() {
        return this.mRelatedArticles;
    }

    @JsonProperty("related_articles")
    public void setRelatedArticles(List<Article> value) {
        this.mRelatedArticles = value;
    }

    public List<ArticleTag> getTags() {
        return this.mTags;
    }

    @JsonProperty("tags")
    public void setTags(List<ArticleTag> value) {
        this.mTags = value;
    }

    public List<StoryElement> getElements() {
        return this.mElements;
    }

    @JsonProperty("elements")
    public void setElements(List<StoryElement> value) {
        this.mElements = value;
    }

    public List<String> getAdditionalCoverImages() {
        return this.mAdditionalCoverImages;
    }

    @JsonProperty("additional_cover_images")
    public void setAdditionalCoverImages(List<String> value) {
        this.mAdditionalCoverImages = value;
    }

    public List<TravelDestination> getDestinations() {
        return this.mDestinations;
    }

    @JsonProperty("destinations")
    public void setDestinations(List<TravelDestination> value) {
        this.mDestinations = value;
    }

    public StoryElement getAppendix() {
        return this.mAppendix;
    }

    @JsonProperty("appendix")
    public void setAppendix(StoryElement value) {
        this.mAppendix = value;
    }

    public StorySeeAllTile getRelatedArticlesSeeAll() {
        return this.mRelatedArticlesSeeAll;
    }

    @JsonProperty("related_articles_see_all")
    public void setRelatedArticlesSeeAll(StorySeeAllTile value) {
        this.mRelatedArticlesSeeAll = value;
    }

    public String getTitle() {
        return this.mTitle;
    }

    @JsonProperty("title")
    public void setTitle(String value) {
        this.mTitle = value;
    }

    public String getSubtitle() {
        return this.mSubtitle;
    }

    @JsonProperty("subtitle")
    public void setSubtitle(String value) {
        this.mSubtitle = value;
    }

    public String getAuthor() {
        return this.mAuthor;
    }

    @JsonProperty("author")
    public void setAuthor(String value) {
        this.mAuthor = value;
    }

    public String getCoverImageUrl() {
        return this.mCoverImageUrl;
    }

    @JsonProperty("xxl_cover_image_url")
    public void setCoverImageUrl(String value) {
        this.mCoverImageUrl = value;
    }

    public String getCoverImagePreview() {
        return this.mCoverImagePreview;
    }

    @JsonProperty("cover_image_preview_encoded_png")
    public void setCoverImagePreview(String value) {
        this.mCoverImagePreview = value;
    }

    public String getTypeStr() {
        return this.mTypeStr;
    }

    @JsonProperty("type")
    public void setTypeStr(String value) {
        this.mTypeStr = value;
    }

    public String getPublishedAtHumanReadable() {
        return this.mPublishedAtHumanReadable;
    }

    @JsonProperty("published_at_human_readable")
    public void setPublishedAtHumanReadable(String value) {
        this.mPublishedAtHumanReadable = value;
    }

    public String getLocalizedArticleLocation() {
        return this.mLocalizedArticleLocation;
    }

    @JsonProperty("localized_article_location")
    public void setLocalizedArticleLocation(String value) {
        this.mLocalizedArticleLocation = value;
    }

    public String getArticleCategory() {
        return this.mArticleCategory;
    }

    @JsonProperty("article_category")
    public void setArticleCategory(String value) {
        this.mArticleCategory = value;
    }

    public String getArticleCategoryBgColor() {
        return this.mArticleCategoryBgColor;
    }

    @JsonProperty("article_category_bg_color")
    public void setArticleCategoryBgColor(String value) {
        this.mArticleCategoryBgColor = value;
    }

    public User getAuthorObject() {
        return this.mAuthorObject;
    }

    @JsonProperty("author_object")
    public void setAuthorObject(User value) {
        this.mAuthorObject = value;
    }

    public UserFlag getUserFlag() {
        return this.mUserFlag;
    }

    @JsonProperty("user_flag")
    public void setUserFlag(UserFlag value) {
        this.mUserFlag = value;
    }

    public boolean isLiked() {
        return this.mLiked;
    }

    @JsonProperty("liked")
    public void setLiked(boolean value) {
        this.mLiked = value;
    }

    public double getCoverImageRatio() {
        return this.mCoverImageRatio;
    }

    @JsonProperty("cover_image_ratio")
    public void setCoverImageRatio(double value) {
        this.mCoverImageRatio = value;
    }

    public int getCommentCount() {
        return this.mCommentCount;
    }

    @JsonProperty("comment_count")
    public void setCommentCount(int value) {
        this.mCommentCount = value;
    }

    public int getLikeCount() {
        return this.mLikeCount;
    }

    @JsonProperty("like_count")
    public void setLikeCount(int value) {
        this.mLikeCount = value;
    }

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mPublishedAt, 0);
        parcel.writeTypedList(this.mRelatedArticles);
        parcel.writeTypedList(this.mTags);
        parcel.writeTypedList(this.mElements);
        parcel.writeStringList(this.mAdditionalCoverImages);
        parcel.writeTypedList(this.mDestinations);
        parcel.writeParcelable(this.mAppendix, 0);
        parcel.writeParcelable(this.mRelatedArticlesSeeAll, 0);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mSubtitle);
        parcel.writeString(this.mAuthor);
        parcel.writeString(this.mCoverImageUrl);
        parcel.writeString(this.mCoverImagePreview);
        parcel.writeString(this.mTypeStr);
        parcel.writeString(this.mPublishedAtHumanReadable);
        parcel.writeString(this.mLocalizedArticleLocation);
        parcel.writeString(this.mArticleCategory);
        parcel.writeString(this.mArticleCategoryBgColor);
        parcel.writeParcelable(this.mAuthorObject, 0);
        parcel.writeParcelable(this.mUserFlag, 0);
        parcel.writeBooleanArray(new boolean[]{this.mLiked});
        parcel.writeDouble(this.mCoverImageRatio);
        parcel.writeInt(this.mCommentCount);
        parcel.writeInt(this.mLikeCount);
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mPublishedAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mRelatedArticles = source.createTypedArrayList(Article.CREATOR);
        this.mTags = source.createTypedArrayList(ArticleTag.CREATOR);
        this.mElements = source.createTypedArrayList(StoryElement.CREATOR);
        this.mAdditionalCoverImages = source.createStringArrayList();
        this.mDestinations = source.createTypedArrayList(TravelDestination.CREATOR);
        this.mAppendix = (StoryElement) source.readParcelable(StoryElement.class.getClassLoader());
        this.mRelatedArticlesSeeAll = (StorySeeAllTile) source.readParcelable(StorySeeAllTile.class.getClassLoader());
        this.mTitle = source.readString();
        this.mSubtitle = source.readString();
        this.mAuthor = source.readString();
        this.mCoverImageUrl = source.readString();
        this.mCoverImagePreview = source.readString();
        this.mTypeStr = source.readString();
        this.mPublishedAtHumanReadable = source.readString();
        this.mLocalizedArticleLocation = source.readString();
        this.mArticleCategory = source.readString();
        this.mArticleCategoryBgColor = source.readString();
        this.mAuthorObject = (User) source.readParcelable(User.class.getClassLoader());
        this.mUserFlag = (UserFlag) source.readParcelable(UserFlag.class.getClassLoader());
        this.mLiked = source.createBooleanArray()[0];
        this.mCoverImageRatio = source.readDouble();
        this.mCommentCount = source.readInt();
        this.mLikeCount = source.readInt();
        this.mId = source.readLong();
    }
}
