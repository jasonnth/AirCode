package com.airbnb.android.contentframework.data;

import com.airbnb.android.core.models.StoryCreationPlaceTag;
import java.util.List;

/* renamed from: com.airbnb.android.contentframework.data.$AutoValue_StoryPublishArguments reason: invalid class name */
abstract class C$AutoValue_StoryPublishArguments extends StoryPublishArguments {
    private final StoryCreationListingAppendix appendix;
    private final String body;
    private final List<StoryCreationImage> imageList;
    private final StoryCreationPlaceTag placeTag;
    private final String title;

    C$AutoValue_StoryPublishArguments(String title2, String body2, List<StoryCreationImage> imageList2, StoryCreationListingAppendix appendix2, StoryCreationPlaceTag placeTag2) {
        if (title2 == null) {
            throw new NullPointerException("Null title");
        }
        this.title = title2;
        if (body2 == null) {
            throw new NullPointerException("Null body");
        }
        this.body = body2;
        if (imageList2 == null) {
            throw new NullPointerException("Null imageList");
        }
        this.imageList = imageList2;
        if (appendix2 == null) {
            throw new NullPointerException("Null appendix");
        }
        this.appendix = appendix2;
        this.placeTag = placeTag2;
    }

    public String title() {
        return this.title;
    }

    public String body() {
        return this.body;
    }

    public List<StoryCreationImage> imageList() {
        return this.imageList;
    }

    public StoryCreationListingAppendix appendix() {
        return this.appendix;
    }

    public StoryCreationPlaceTag placeTag() {
        return this.placeTag;
    }

    public String toString() {
        return "StoryPublishArguments{title=" + this.title + ", body=" + this.body + ", imageList=" + this.imageList + ", appendix=" + this.appendix + ", placeTag=" + this.placeTag + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof StoryPublishArguments)) {
            return false;
        }
        StoryPublishArguments that = (StoryPublishArguments) o;
        if (this.title.equals(that.title()) && this.body.equals(that.body()) && this.imageList.equals(that.imageList()) && this.appendix.equals(that.appendix())) {
            if (this.placeTag == null) {
                if (that.placeTag() == null) {
                    return true;
                }
            } else if (this.placeTag.equals(that.placeTag())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (((((((((1 * 1000003) ^ this.title.hashCode()) * 1000003) ^ this.body.hashCode()) * 1000003) ^ this.imageList.hashCode()) * 1000003) ^ this.appendix.hashCode()) * 1000003) ^ (this.placeTag == null ? 0 : this.placeTag.hashCode());
    }
}
