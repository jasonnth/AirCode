package com.airbnb.android.checkin.data;

import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.models.CheckInGuide;

/* renamed from: com.airbnb.android.checkin.data.$AutoValue_CheckInGuideData reason: invalid class name */
abstract class C$AutoValue_CheckInGuideData extends CheckInGuideData {
    private final CheckInGuide check_in_guide;
    private final long listing_id;
    private final AirDateTime updated_at;

    C$AutoValue_CheckInGuideData(long listing_id2, AirDateTime updated_at2, CheckInGuide check_in_guide2) {
        this.listing_id = listing_id2;
        this.updated_at = updated_at2;
        if (check_in_guide2 == null) {
            throw new NullPointerException("Null check_in_guide");
        }
        this.check_in_guide = check_in_guide2;
    }

    public long listing_id() {
        return this.listing_id;
    }

    public AirDateTime updated_at() {
        return this.updated_at;
    }

    public CheckInGuide check_in_guide() {
        return this.check_in_guide;
    }

    public String toString() {
        return "CheckInGuideData{listing_id=" + this.listing_id + ", updated_at=" + this.updated_at + ", check_in_guide=" + this.check_in_guide + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CheckInGuideData)) {
            return false;
        }
        CheckInGuideData that = (CheckInGuideData) o;
        if (this.listing_id != that.listing_id() || (this.updated_at != null ? !this.updated_at.equals(that.updated_at()) : that.updated_at() != null) || !this.check_in_guide.equals(that.check_in_guide())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((int) (((long) (1 * 1000003)) ^ ((this.listing_id >>> 32) ^ this.listing_id))) * 1000003) ^ (this.updated_at == null ? 0 : this.updated_at.hashCode())) * 1000003) ^ this.check_in_guide.hashCode();
    }
}
