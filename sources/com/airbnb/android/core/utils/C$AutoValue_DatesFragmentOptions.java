package com.airbnb.android.core.utils;

import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.views.calendar.CalendarView.Style;

/* renamed from: com.airbnb.android.core.utils.$AutoValue_DatesFragmentOptions reason: invalid class name */
abstract class C$AutoValue_DatesFragmentOptions extends DatesFragmentOptions {
    private final AirDate endDate;
    private final int endDateTitleOverride;
    private final boolean formatWithYear;
    private final Listing listing;
    private final ParcelStrap navigationExtras;
    private final NavigationTag navigationTag;
    private final boolean preventEmptyDates;
    private final int saveButtonTextOverride;
    private final boolean singleDaySelectionMode;
    private final NavigationTag sourceTag;
    private final AirDate startDate;
    private final int startDateTitleOverride;
    private final Style style;

    /* renamed from: com.airbnb.android.core.utils.$AutoValue_DatesFragmentOptions$Builder */
    static final class Builder extends com.airbnb.android.core.utils.DatesFragmentOptions.Builder {
        private AirDate endDate;
        private Integer endDateTitleOverride;
        private Boolean formatWithYear;
        private Listing listing;
        private ParcelStrap navigationExtras;
        private NavigationTag navigationTag;
        private Boolean preventEmptyDates;
        private Integer saveButtonTextOverride;
        private Boolean singleDaySelectionMode;
        private NavigationTag sourceTag;
        private AirDate startDate;
        private Integer startDateTitleOverride;
        private Style style;

        Builder() {
        }

        public com.airbnb.android.core.utils.DatesFragmentOptions.Builder startDate(AirDate startDate2) {
            this.startDate = startDate2;
            return this;
        }

        public com.airbnb.android.core.utils.DatesFragmentOptions.Builder endDate(AirDate endDate2) {
            this.endDate = endDate2;
            return this;
        }

        public com.airbnb.android.core.utils.DatesFragmentOptions.Builder startDateTitleOverride(int startDateTitleOverride2) {
            this.startDateTitleOverride = Integer.valueOf(startDateTitleOverride2);
            return this;
        }

        public com.airbnb.android.core.utils.DatesFragmentOptions.Builder endDateTitleOverride(int endDateTitleOverride2) {
            this.endDateTitleOverride = Integer.valueOf(endDateTitleOverride2);
            return this;
        }

        public com.airbnb.android.core.utils.DatesFragmentOptions.Builder saveButtonTextOverride(int saveButtonTextOverride2) {
            this.saveButtonTextOverride = Integer.valueOf(saveButtonTextOverride2);
            return this;
        }

        public com.airbnb.android.core.utils.DatesFragmentOptions.Builder listing(Listing listing2) {
            this.listing = listing2;
            return this;
        }

        public com.airbnb.android.core.utils.DatesFragmentOptions.Builder navigationTag(NavigationTag navigationTag2) {
            if (navigationTag2 == null) {
                throw new NullPointerException("Null navigationTag");
            }
            this.navigationTag = navigationTag2;
            return this;
        }

        public com.airbnb.android.core.utils.DatesFragmentOptions.Builder sourceTag(NavigationTag sourceTag2) {
            if (sourceTag2 == null) {
                throw new NullPointerException("Null sourceTag");
            }
            this.sourceTag = sourceTag2;
            return this;
        }

        public com.airbnb.android.core.utils.DatesFragmentOptions.Builder navigationExtras(ParcelStrap navigationExtras2) {
            this.navigationExtras = navigationExtras2;
            return this;
        }

        public com.airbnb.android.core.utils.DatesFragmentOptions.Builder style(Style style2) {
            if (style2 == null) {
                throw new NullPointerException("Null style");
            }
            this.style = style2;
            return this;
        }

        public com.airbnb.android.core.utils.DatesFragmentOptions.Builder preventEmptyDates(boolean preventEmptyDates2) {
            this.preventEmptyDates = Boolean.valueOf(preventEmptyDates2);
            return this;
        }

        public com.airbnb.android.core.utils.DatesFragmentOptions.Builder formatWithYear(boolean formatWithYear2) {
            this.formatWithYear = Boolean.valueOf(formatWithYear2);
            return this;
        }

        public com.airbnb.android.core.utils.DatesFragmentOptions.Builder singleDaySelectionMode(boolean singleDaySelectionMode2) {
            this.singleDaySelectionMode = Boolean.valueOf(singleDaySelectionMode2);
            return this;
        }

        public DatesFragmentOptions build() {
            String missing = "";
            if (this.startDateTitleOverride == null) {
                missing = missing + " startDateTitleOverride";
            }
            if (this.endDateTitleOverride == null) {
                missing = missing + " endDateTitleOverride";
            }
            if (this.saveButtonTextOverride == null) {
                missing = missing + " saveButtonTextOverride";
            }
            if (this.navigationTag == null) {
                missing = missing + " navigationTag";
            }
            if (this.sourceTag == null) {
                missing = missing + " sourceTag";
            }
            if (this.style == null) {
                missing = missing + " style";
            }
            if (this.preventEmptyDates == null) {
                missing = missing + " preventEmptyDates";
            }
            if (this.formatWithYear == null) {
                missing = missing + " formatWithYear";
            }
            if (this.singleDaySelectionMode == null) {
                missing = missing + " singleDaySelectionMode";
            }
            if (missing.isEmpty()) {
                return new AutoValue_DatesFragmentOptions(this.startDate, this.endDate, this.startDateTitleOverride.intValue(), this.endDateTitleOverride.intValue(), this.saveButtonTextOverride.intValue(), this.listing, this.navigationTag, this.sourceTag, this.navigationExtras, this.style, this.preventEmptyDates.booleanValue(), this.formatWithYear.booleanValue(), this.singleDaySelectionMode.booleanValue());
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_DatesFragmentOptions(AirDate startDate2, AirDate endDate2, int startDateTitleOverride2, int endDateTitleOverride2, int saveButtonTextOverride2, Listing listing2, NavigationTag navigationTag2, NavigationTag sourceTag2, ParcelStrap navigationExtras2, Style style2, boolean preventEmptyDates2, boolean formatWithYear2, boolean singleDaySelectionMode2) {
        this.startDate = startDate2;
        this.endDate = endDate2;
        this.startDateTitleOverride = startDateTitleOverride2;
        this.endDateTitleOverride = endDateTitleOverride2;
        this.saveButtonTextOverride = saveButtonTextOverride2;
        this.listing = listing2;
        if (navigationTag2 == null) {
            throw new NullPointerException("Null navigationTag");
        }
        this.navigationTag = navigationTag2;
        if (sourceTag2 == null) {
            throw new NullPointerException("Null sourceTag");
        }
        this.sourceTag = sourceTag2;
        this.navigationExtras = navigationExtras2;
        if (style2 == null) {
            throw new NullPointerException("Null style");
        }
        this.style = style2;
        this.preventEmptyDates = preventEmptyDates2;
        this.formatWithYear = formatWithYear2;
        this.singleDaySelectionMode = singleDaySelectionMode2;
    }

    public AirDate startDate() {
        return this.startDate;
    }

    public AirDate endDate() {
        return this.endDate;
    }

    public int startDateTitleOverride() {
        return this.startDateTitleOverride;
    }

    public int endDateTitleOverride() {
        return this.endDateTitleOverride;
    }

    public int saveButtonTextOverride() {
        return this.saveButtonTextOverride;
    }

    public Listing listing() {
        return this.listing;
    }

    public NavigationTag navigationTag() {
        return this.navigationTag;
    }

    public NavigationTag sourceTag() {
        return this.sourceTag;
    }

    public ParcelStrap navigationExtras() {
        return this.navigationExtras;
    }

    public Style style() {
        return this.style;
    }

    public boolean preventEmptyDates() {
        return this.preventEmptyDates;
    }

    public boolean formatWithYear() {
        return this.formatWithYear;
    }

    public boolean singleDaySelectionMode() {
        return this.singleDaySelectionMode;
    }

    public String toString() {
        return "DatesFragmentOptions{startDate=" + this.startDate + ", endDate=" + this.endDate + ", startDateTitleOverride=" + this.startDateTitleOverride + ", endDateTitleOverride=" + this.endDateTitleOverride + ", saveButtonTextOverride=" + this.saveButtonTextOverride + ", listing=" + this.listing + ", navigationTag=" + this.navigationTag + ", sourceTag=" + this.sourceTag + ", navigationExtras=" + this.navigationExtras + ", style=" + this.style + ", preventEmptyDates=" + this.preventEmptyDates + ", formatWithYear=" + this.formatWithYear + ", singleDaySelectionMode=" + this.singleDaySelectionMode + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof DatesFragmentOptions)) {
            return false;
        }
        DatesFragmentOptions that = (DatesFragmentOptions) o;
        if (this.startDate != null ? this.startDate.equals(that.startDate()) : that.startDate() == null) {
            if (this.endDate != null ? this.endDate.equals(that.endDate()) : that.endDate() == null) {
                if (this.startDateTitleOverride == that.startDateTitleOverride() && this.endDateTitleOverride == that.endDateTitleOverride() && this.saveButtonTextOverride == that.saveButtonTextOverride() && (this.listing != null ? this.listing.equals(that.listing()) : that.listing() == null) && this.navigationTag.equals(that.navigationTag()) && this.sourceTag.equals(that.sourceTag()) && (this.navigationExtras != null ? this.navigationExtras.equals(that.navigationExtras()) : that.navigationExtras() == null) && this.style.equals(that.style()) && this.preventEmptyDates == that.preventEmptyDates() && this.formatWithYear == that.formatWithYear() && this.singleDaySelectionMode == that.singleDaySelectionMode()) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int i;
        int i2;
        int i3 = 1231;
        int i4 = 0;
        int h = ((((((((((((((((1 * 1000003) ^ (this.startDate == null ? 0 : this.startDate.hashCode())) * 1000003) ^ (this.endDate == null ? 0 : this.endDate.hashCode())) * 1000003) ^ this.startDateTitleOverride) * 1000003) ^ this.endDateTitleOverride) * 1000003) ^ this.saveButtonTextOverride) * 1000003) ^ (this.listing == null ? 0 : this.listing.hashCode())) * 1000003) ^ this.navigationTag.hashCode()) * 1000003) ^ this.sourceTag.hashCode()) * 1000003;
        if (this.navigationExtras != null) {
            i4 = this.navigationExtras.hashCode();
        }
        int h2 = (((h ^ i4) * 1000003) ^ this.style.hashCode()) * 1000003;
        if (this.preventEmptyDates) {
            i = 1231;
        } else {
            i = 1237;
        }
        int h3 = (h2 ^ i) * 1000003;
        if (this.formatWithYear) {
            i2 = 1231;
        } else {
            i2 = 1237;
        }
        int h4 = (h3 ^ i2) * 1000003;
        if (!this.singleDaySelectionMode) {
            i3 = 1237;
        }
        return h4 ^ i3;
    }
}
