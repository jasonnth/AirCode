package com.airbnb.android.core.data;

final class AutoValue_AffiliateData extends AffiliateData {
    private final int affiliateId;
    private final String campaign;
    private final String localAfClick;

    AutoValue_AffiliateData(String campaign2, int affiliateId2, String localAfClick2) {
        this.campaign = campaign2;
        this.affiliateId = affiliateId2;
        this.localAfClick = localAfClick2;
    }

    public String campaign() {
        return this.campaign;
    }

    public int affiliateId() {
        return this.affiliateId;
    }

    public String localAfClick() {
        return this.localAfClick;
    }

    public String toString() {
        return "AffiliateData{campaign=" + this.campaign + ", affiliateId=" + this.affiliateId + ", localAfClick=" + this.localAfClick + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AffiliateData)) {
            return false;
        }
        AffiliateData that = (AffiliateData) o;
        if (this.campaign != null ? this.campaign.equals(that.campaign()) : that.campaign() == null) {
            if (this.affiliateId == that.affiliateId()) {
                if (this.localAfClick == null) {
                    if (that.localAfClick() == null) {
                        return true;
                    }
                } else if (this.localAfClick.equals(that.localAfClick())) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((1 * 1000003) ^ (this.campaign == null ? 0 : this.campaign.hashCode())) * 1000003) ^ this.affiliateId) * 1000003;
        if (this.localAfClick != null) {
            i = this.localAfClick.hashCode();
        }
        return h ^ i;
    }
}
