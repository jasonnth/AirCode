package com.airbnb.android.places;

import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.Photo;
import com.airbnb.android.core.models.RestaurantAvailability;
import java.util.List;

/* renamed from: com.airbnb.android.places.$AutoValue_ResyState reason: invalid class name */
abstract class C$AutoValue_ResyState extends ResyState {
    private final long activityId;
    private final Photo coverImage;
    private final AirDate date;
    private final int guests;
    private final boolean isLoading;
    private final String placeName;
    private final RestaurantAvailability selectedTime;
    private final boolean showResy;
    private final List<RestaurantAvailability> timeSlots;

    /* renamed from: com.airbnb.android.places.$AutoValue_ResyState$Builder */
    static final class Builder extends com.airbnb.android.places.ResyState.Builder {
        private Long activityId;
        private Photo coverImage;
        private AirDate date;
        private Integer guests;
        private Boolean isLoading;
        private String placeName;
        private RestaurantAvailability selectedTime;
        private Boolean showResy;
        private List<RestaurantAvailability> timeSlots;

        Builder() {
        }

        private Builder(ResyState source) {
            this.activityId = Long.valueOf(source.activityId());
            this.showResy = Boolean.valueOf(source.showResy());
            this.guests = Integer.valueOf(source.guests());
            this.date = source.date();
            this.isLoading = Boolean.valueOf(source.isLoading());
            this.timeSlots = source.timeSlots();
            this.coverImage = source.coverImage();
            this.placeName = source.placeName();
            this.selectedTime = source.selectedTime();
        }

        public com.airbnb.android.places.ResyState.Builder activityId(long activityId2) {
            this.activityId = Long.valueOf(activityId2);
            return this;
        }

        public com.airbnb.android.places.ResyState.Builder showResy(boolean showResy2) {
            this.showResy = Boolean.valueOf(showResy2);
            return this;
        }

        public com.airbnb.android.places.ResyState.Builder guests(int guests2) {
            this.guests = Integer.valueOf(guests2);
            return this;
        }

        public com.airbnb.android.places.ResyState.Builder date(AirDate date2) {
            if (date2 == null) {
                throw new NullPointerException("Null date");
            }
            this.date = date2;
            return this;
        }

        public com.airbnb.android.places.ResyState.Builder isLoading(boolean isLoading2) {
            this.isLoading = Boolean.valueOf(isLoading2);
            return this;
        }

        public com.airbnb.android.places.ResyState.Builder timeSlots(List<RestaurantAvailability> timeSlots2) {
            if (timeSlots2 == null) {
                throw new NullPointerException("Null timeSlots");
            }
            this.timeSlots = timeSlots2;
            return this;
        }

        public com.airbnb.android.places.ResyState.Builder coverImage(Photo coverImage2) {
            this.coverImage = coverImage2;
            return this;
        }

        public com.airbnb.android.places.ResyState.Builder placeName(String placeName2) {
            this.placeName = placeName2;
            return this;
        }

        public com.airbnb.android.places.ResyState.Builder selectedTime(RestaurantAvailability selectedTime2) {
            this.selectedTime = selectedTime2;
            return this;
        }

        public ResyState build() {
            String missing = "";
            if (this.activityId == null) {
                missing = missing + " activityId";
            }
            if (this.showResy == null) {
                missing = missing + " showResy";
            }
            if (this.guests == null) {
                missing = missing + " guests";
            }
            if (this.date == null) {
                missing = missing + " date";
            }
            if (this.isLoading == null) {
                missing = missing + " isLoading";
            }
            if (this.timeSlots == null) {
                missing = missing + " timeSlots";
            }
            if (missing.isEmpty()) {
                return new AutoValue_ResyState(this.activityId.longValue(), this.showResy.booleanValue(), this.guests.intValue(), this.date, this.isLoading.booleanValue(), this.timeSlots, this.coverImage, this.placeName, this.selectedTime);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_ResyState(long activityId2, boolean showResy2, int guests2, AirDate date2, boolean isLoading2, List<RestaurantAvailability> timeSlots2, Photo coverImage2, String placeName2, RestaurantAvailability selectedTime2) {
        this.activityId = activityId2;
        this.showResy = showResy2;
        this.guests = guests2;
        if (date2 == null) {
            throw new NullPointerException("Null date");
        }
        this.date = date2;
        this.isLoading = isLoading2;
        if (timeSlots2 == null) {
            throw new NullPointerException("Null timeSlots");
        }
        this.timeSlots = timeSlots2;
        this.coverImage = coverImage2;
        this.placeName = placeName2;
        this.selectedTime = selectedTime2;
    }

    public long activityId() {
        return this.activityId;
    }

    public boolean showResy() {
        return this.showResy;
    }

    public int guests() {
        return this.guests;
    }

    public AirDate date() {
        return this.date;
    }

    public boolean isLoading() {
        return this.isLoading;
    }

    public List<RestaurantAvailability> timeSlots() {
        return this.timeSlots;
    }

    public Photo coverImage() {
        return this.coverImage;
    }

    public String placeName() {
        return this.placeName;
    }

    public RestaurantAvailability selectedTime() {
        return this.selectedTime;
    }

    public String toString() {
        return "ResyState{activityId=" + this.activityId + ", showResy=" + this.showResy + ", guests=" + this.guests + ", date=" + this.date + ", isLoading=" + this.isLoading + ", timeSlots=" + this.timeSlots + ", coverImage=" + this.coverImage + ", placeName=" + this.placeName + ", selectedTime=" + this.selectedTime + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ResyState)) {
            return false;
        }
        ResyState that = (ResyState) o;
        if (this.activityId == that.activityId() && this.showResy == that.showResy() && this.guests == that.guests() && this.date.equals(that.date()) && this.isLoading == that.isLoading() && this.timeSlots.equals(that.timeSlots()) && (this.coverImage != null ? this.coverImage.equals(that.coverImage()) : that.coverImage() == null) && (this.placeName != null ? this.placeName.equals(that.placeName()) : that.placeName() == null)) {
            if (this.selectedTime == null) {
                if (that.selectedTime() == null) {
                    return true;
                }
            } else if (this.selectedTime.equals(that.selectedTime())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 1231;
        int i2 = 0;
        int h = ((((((((int) (((long) (1 * 1000003)) ^ ((this.activityId >>> 32) ^ this.activityId))) * 1000003) ^ (this.showResy ? 1231 : 1237)) * 1000003) ^ this.guests) * 1000003) ^ this.date.hashCode()) * 1000003;
        if (!this.isLoading) {
            i = 1237;
        }
        int h2 = (((((((h ^ i) * 1000003) ^ this.timeSlots.hashCode()) * 1000003) ^ (this.coverImage == null ? 0 : this.coverImage.hashCode())) * 1000003) ^ (this.placeName == null ? 0 : this.placeName.hashCode())) * 1000003;
        if (this.selectedTime != null) {
            i2 = this.selectedTime.hashCode();
        }
        return h2 ^ i2;
    }

    public com.airbnb.android.places.ResyState.Builder toBuilder() {
        return new Builder(this);
    }
}
