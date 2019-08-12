package com.airbnb.android.sharing.models;

/* renamed from: com.airbnb.android.sharing.models.$AutoValue_ShareArguments reason: invalid class name */
abstract class C$AutoValue_ShareArguments extends ShareArguments {
    private final String category;
    private final String creatorName;
    private final String creatorOccupation;
    private final String detourAbout;
    private final String detourDescription;
    private final String detourTips;
    private final String detourTitle;
    private final String entryPoint;
    private final String experienceDescription;
    private final String experienceTitle;
    private final String fbShareUrl;
    private final String guidebookTitle;
    private final String hostName;

    /* renamed from: id */
    private final Long f1848id;
    private final String location;
    private final Integer numPlaces;
    private final String placeName;
    private final String placeType;
    private final String primaryImageUrl;
    private final Integer productType;
    private final String url;

    /* renamed from: com.airbnb.android.sharing.models.$AutoValue_ShareArguments$Builder */
    static final class Builder extends com.airbnb.android.sharing.models.ShareArguments.Builder {
        private String category;
        private String creatorName;
        private String creatorOccupation;
        private String detourAbout;
        private String detourDescription;
        private String detourTips;
        private String detourTitle;
        private String entryPoint;
        private String experienceDescription;
        private String experienceTitle;
        private String fbShareUrl;
        private String guidebookTitle;
        private String hostName;

        /* renamed from: id */
        private Long f1849id;
        private String location;
        private Integer numPlaces;
        private String placeName;
        private String placeType;
        private String primaryImageUrl;
        private Integer productType;
        private String url;

        Builder() {
        }

        public com.airbnb.android.sharing.models.ShareArguments.Builder entryPoint(String entryPoint2) {
            if (entryPoint2 == null) {
                throw new NullPointerException("Null entryPoint");
            }
            this.entryPoint = entryPoint2;
            return this;
        }

        /* renamed from: id */
        public com.airbnb.android.sharing.models.ShareArguments.Builder mo11490id(Long id) {
            if (id == null) {
                throw new NullPointerException("Null id");
            }
            this.f1849id = id;
            return this;
        }

        public com.airbnb.android.sharing.models.ShareArguments.Builder url(String url2) {
            if (url2 == null) {
                throw new NullPointerException("Null url");
            }
            this.url = url2;
            return this;
        }

        public com.airbnb.android.sharing.models.ShareArguments.Builder primaryImageUrl(String primaryImageUrl2) {
            if (primaryImageUrl2 == null) {
                throw new NullPointerException("Null primaryImageUrl");
            }
            this.primaryImageUrl = primaryImageUrl2;
            return this;
        }

        public com.airbnb.android.sharing.models.ShareArguments.Builder fbShareUrl(String fbShareUrl2) {
            this.fbShareUrl = fbShareUrl2;
            return this;
        }

        public com.airbnb.android.sharing.models.ShareArguments.Builder productType(Integer productType2) {
            this.productType = productType2;
            return this;
        }

        public com.airbnb.android.sharing.models.ShareArguments.Builder location(String location2) {
            this.location = location2;
            return this;
        }

        public com.airbnb.android.sharing.models.ShareArguments.Builder experienceTitle(String experienceTitle2) {
            this.experienceTitle = experienceTitle2;
            return this;
        }

        public com.airbnb.android.sharing.models.ShareArguments.Builder experienceDescription(String experienceDescription2) {
            this.experienceDescription = experienceDescription2;
            return this;
        }

        public com.airbnb.android.sharing.models.ShareArguments.Builder hostName(String hostName2) {
            this.hostName = hostName2;
            return this;
        }

        public com.airbnb.android.sharing.models.ShareArguments.Builder guidebookTitle(String guidebookTitle2) {
            this.guidebookTitle = guidebookTitle2;
            return this;
        }

        public com.airbnb.android.sharing.models.ShareArguments.Builder creatorOccupation(String creatorOccupation2) {
            this.creatorOccupation = creatorOccupation2;
            return this;
        }

        public com.airbnb.android.sharing.models.ShareArguments.Builder creatorName(String creatorName2) {
            this.creatorName = creatorName2;
            return this;
        }

        public com.airbnb.android.sharing.models.ShareArguments.Builder numPlaces(Integer numPlaces2) {
            this.numPlaces = numPlaces2;
            return this;
        }

        public com.airbnb.android.sharing.models.ShareArguments.Builder placeName(String placeName2) {
            this.placeName = placeName2;
            return this;
        }

        public com.airbnb.android.sharing.models.ShareArguments.Builder placeType(String placeType2) {
            this.placeType = placeType2;
            return this;
        }

        public com.airbnb.android.sharing.models.ShareArguments.Builder category(String category2) {
            this.category = category2;
            return this;
        }

        public com.airbnb.android.sharing.models.ShareArguments.Builder detourTitle(String detourTitle2) {
            this.detourTitle = detourTitle2;
            return this;
        }

        public com.airbnb.android.sharing.models.ShareArguments.Builder detourAbout(String detourAbout2) {
            this.detourAbout = detourAbout2;
            return this;
        }

        public com.airbnb.android.sharing.models.ShareArguments.Builder detourDescription(String detourDescription2) {
            this.detourDescription = detourDescription2;
            return this;
        }

        public com.airbnb.android.sharing.models.ShareArguments.Builder detourTips(String detourTips2) {
            this.detourTips = detourTips2;
            return this;
        }

        public ShareArguments build() {
            String missing = "";
            if (this.entryPoint == null) {
                missing = missing + " entryPoint";
            }
            if (this.f1849id == null) {
                missing = missing + " id";
            }
            if (this.url == null) {
                missing = missing + " url";
            }
            if (this.primaryImageUrl == null) {
                missing = missing + " primaryImageUrl";
            }
            if (missing.isEmpty()) {
                return new AutoValue_ShareArguments(this.entryPoint, this.f1849id, this.url, this.primaryImageUrl, this.fbShareUrl, this.productType, this.location, this.experienceTitle, this.experienceDescription, this.hostName, this.guidebookTitle, this.creatorOccupation, this.creatorName, this.numPlaces, this.placeName, this.placeType, this.category, this.detourTitle, this.detourAbout, this.detourDescription, this.detourTips);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_ShareArguments(String entryPoint2, Long id, String url2, String primaryImageUrl2, String fbShareUrl2, Integer productType2, String location2, String experienceTitle2, String experienceDescription2, String hostName2, String guidebookTitle2, String creatorOccupation2, String creatorName2, Integer numPlaces2, String placeName2, String placeType2, String category2, String detourTitle2, String detourAbout2, String detourDescription2, String detourTips2) {
        if (entryPoint2 == null) {
            throw new NullPointerException("Null entryPoint");
        }
        this.entryPoint = entryPoint2;
        if (id == null) {
            throw new NullPointerException("Null id");
        }
        this.f1848id = id;
        if (url2 == null) {
            throw new NullPointerException("Null url");
        }
        this.url = url2;
        if (primaryImageUrl2 == null) {
            throw new NullPointerException("Null primaryImageUrl");
        }
        this.primaryImageUrl = primaryImageUrl2;
        this.fbShareUrl = fbShareUrl2;
        this.productType = productType2;
        this.location = location2;
        this.experienceTitle = experienceTitle2;
        this.experienceDescription = experienceDescription2;
        this.hostName = hostName2;
        this.guidebookTitle = guidebookTitle2;
        this.creatorOccupation = creatorOccupation2;
        this.creatorName = creatorName2;
        this.numPlaces = numPlaces2;
        this.placeName = placeName2;
        this.placeType = placeType2;
        this.category = category2;
        this.detourTitle = detourTitle2;
        this.detourAbout = detourAbout2;
        this.detourDescription = detourDescription2;
        this.detourTips = detourTips2;
    }

    public String entryPoint() {
        return this.entryPoint;
    }

    /* renamed from: id */
    public Long mo11468id() {
        return this.f1848id;
    }

    public String url() {
        return this.url;
    }

    public String primaryImageUrl() {
        return this.primaryImageUrl;
    }

    public String fbShareUrl() {
        return this.fbShareUrl;
    }

    public Integer productType() {
        return this.productType;
    }

    public String location() {
        return this.location;
    }

    public String experienceTitle() {
        return this.experienceTitle;
    }

    public String experienceDescription() {
        return this.experienceDescription;
    }

    public String hostName() {
        return this.hostName;
    }

    public String guidebookTitle() {
        return this.guidebookTitle;
    }

    public String creatorOccupation() {
        return this.creatorOccupation;
    }

    public String creatorName() {
        return this.creatorName;
    }

    public Integer numPlaces() {
        return this.numPlaces;
    }

    public String placeName() {
        return this.placeName;
    }

    public String placeType() {
        return this.placeType;
    }

    public String category() {
        return this.category;
    }

    public String detourTitle() {
        return this.detourTitle;
    }

    public String detourAbout() {
        return this.detourAbout;
    }

    public String detourDescription() {
        return this.detourDescription;
    }

    public String detourTips() {
        return this.detourTips;
    }

    public String toString() {
        return "ShareArguments{entryPoint=" + this.entryPoint + ", id=" + this.f1848id + ", url=" + this.url + ", primaryImageUrl=" + this.primaryImageUrl + ", fbShareUrl=" + this.fbShareUrl + ", productType=" + this.productType + ", location=" + this.location + ", experienceTitle=" + this.experienceTitle + ", experienceDescription=" + this.experienceDescription + ", hostName=" + this.hostName + ", guidebookTitle=" + this.guidebookTitle + ", creatorOccupation=" + this.creatorOccupation + ", creatorName=" + this.creatorName + ", numPlaces=" + this.numPlaces + ", placeName=" + this.placeName + ", placeType=" + this.placeType + ", category=" + this.category + ", detourTitle=" + this.detourTitle + ", detourAbout=" + this.detourAbout + ", detourDescription=" + this.detourDescription + ", detourTips=" + this.detourTips + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ShareArguments)) {
            return false;
        }
        ShareArguments that = (ShareArguments) o;
        if (this.entryPoint.equals(that.entryPoint()) && this.f1848id.equals(that.mo11468id()) && this.url.equals(that.url()) && this.primaryImageUrl.equals(that.primaryImageUrl()) && (this.fbShareUrl != null ? this.fbShareUrl.equals(that.fbShareUrl()) : that.fbShareUrl() == null) && (this.productType != null ? this.productType.equals(that.productType()) : that.productType() == null) && (this.location != null ? this.location.equals(that.location()) : that.location() == null) && (this.experienceTitle != null ? this.experienceTitle.equals(that.experienceTitle()) : that.experienceTitle() == null) && (this.experienceDescription != null ? this.experienceDescription.equals(that.experienceDescription()) : that.experienceDescription() == null) && (this.hostName != null ? this.hostName.equals(that.hostName()) : that.hostName() == null) && (this.guidebookTitle != null ? this.guidebookTitle.equals(that.guidebookTitle()) : that.guidebookTitle() == null) && (this.creatorOccupation != null ? this.creatorOccupation.equals(that.creatorOccupation()) : that.creatorOccupation() == null) && (this.creatorName != null ? this.creatorName.equals(that.creatorName()) : that.creatorName() == null) && (this.numPlaces != null ? this.numPlaces.equals(that.numPlaces()) : that.numPlaces() == null) && (this.placeName != null ? this.placeName.equals(that.placeName()) : that.placeName() == null) && (this.placeType != null ? this.placeType.equals(that.placeType()) : that.placeType() == null) && (this.category != null ? this.category.equals(that.category()) : that.category() == null) && (this.detourTitle != null ? this.detourTitle.equals(that.detourTitle()) : that.detourTitle() == null) && (this.detourAbout != null ? this.detourAbout.equals(that.detourAbout()) : that.detourAbout() == null) && (this.detourDescription != null ? this.detourDescription.equals(that.detourDescription()) : that.detourDescription() == null)) {
            if (this.detourTips == null) {
                if (that.detourTips() == null) {
                    return true;
                }
            } else if (this.detourTips.equals(that.detourTips())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((((((((((((((((((((((((((((((((((1 * 1000003) ^ this.entryPoint.hashCode()) * 1000003) ^ this.f1848id.hashCode()) * 1000003) ^ this.url.hashCode()) * 1000003) ^ this.primaryImageUrl.hashCode()) * 1000003) ^ (this.fbShareUrl == null ? 0 : this.fbShareUrl.hashCode())) * 1000003) ^ (this.productType == null ? 0 : this.productType.hashCode())) * 1000003) ^ (this.location == null ? 0 : this.location.hashCode())) * 1000003) ^ (this.experienceTitle == null ? 0 : this.experienceTitle.hashCode())) * 1000003) ^ (this.experienceDescription == null ? 0 : this.experienceDescription.hashCode())) * 1000003) ^ (this.hostName == null ? 0 : this.hostName.hashCode())) * 1000003) ^ (this.guidebookTitle == null ? 0 : this.guidebookTitle.hashCode())) * 1000003) ^ (this.creatorOccupation == null ? 0 : this.creatorOccupation.hashCode())) * 1000003) ^ (this.creatorName == null ? 0 : this.creatorName.hashCode())) * 1000003) ^ (this.numPlaces == null ? 0 : this.numPlaces.hashCode())) * 1000003) ^ (this.placeName == null ? 0 : this.placeName.hashCode())) * 1000003) ^ (this.placeType == null ? 0 : this.placeType.hashCode())) * 1000003) ^ (this.category == null ? 0 : this.category.hashCode())) * 1000003) ^ (this.detourTitle == null ? 0 : this.detourTitle.hashCode())) * 1000003) ^ (this.detourAbout == null ? 0 : this.detourAbout.hashCode())) * 1000003) ^ (this.detourDescription == null ? 0 : this.detourDescription.hashCode())) * 1000003;
        if (this.detourTips != null) {
            i = this.detourTips.hashCode();
        }
        return h ^ i;
    }
}
