package com.airbnb.android.core.wishlists;

import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.enums.HelpCenterArticle;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.jitney.event.logging.WishlistSource.p295v3.C2813WishlistSource;

/* renamed from: com.airbnb.android.core.wishlists.$AutoValue_WishListableData reason: invalid class name */
abstract class C$AutoValue_WishListableData extends WishListableData {
    private final boolean allowAutoAdd;
    private final AirDate checkIn;
    private final AirDate checkOut;
    private final GuestDetails guestDetails;
    private final long itemId;
    private final String searchSessionId;
    private final C2813WishlistSource source;
    private final String suggestedWishListName;
    private final WishListableType type;

    /* renamed from: com.airbnb.android.core.wishlists.$AutoValue_WishListableData$Builder */
    static final class Builder extends com.airbnb.android.core.wishlists.WishListableData.Builder {
        private Boolean allowAutoAdd;
        private AirDate checkIn;
        private AirDate checkOut;
        private GuestDetails guestDetails;
        private Long itemId;
        private String searchSessionId;
        private C2813WishlistSource source;
        private String suggestedWishListName;
        private WishListableType type;

        Builder() {
        }

        /* access modifiers changed from: protected */
        public com.airbnb.android.core.wishlists.WishListableData.Builder type(WishListableType type2) {
            if (type2 == null) {
                throw new NullPointerException("Null type");
            }
            this.type = type2;
            return this;
        }

        /* access modifiers changed from: protected */
        public com.airbnb.android.core.wishlists.WishListableData.Builder itemId(long itemId2) {
            this.itemId = Long.valueOf(itemId2);
            return this;
        }

        public com.airbnb.android.core.wishlists.WishListableData.Builder suggestedWishListName(String suggestedWishListName2) {
            this.suggestedWishListName = suggestedWishListName2;
            return this;
        }

        public com.airbnb.android.core.wishlists.WishListableData.Builder guestDetails(GuestDetails guestDetails2) {
            this.guestDetails = guestDetails2;
            return this;
        }

        public com.airbnb.android.core.wishlists.WishListableData.Builder checkIn(AirDate checkIn2) {
            this.checkIn = checkIn2;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public AirDate checkIn() {
            return this.checkIn;
        }

        public com.airbnb.android.core.wishlists.WishListableData.Builder checkOut(AirDate checkOut2) {
            this.checkOut = checkOut2;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public AirDate checkOut() {
            return this.checkOut;
        }

        public com.airbnb.android.core.wishlists.WishListableData.Builder searchSessionId(String searchSessionId2) {
            this.searchSessionId = searchSessionId2;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public String searchSessionId() {
            return this.searchSessionId;
        }

        public com.airbnb.android.core.wishlists.WishListableData.Builder source(C2813WishlistSource source2) {
            if (source2 == null) {
                throw new NullPointerException("Null source");
            }
            this.source = source2;
            return this;
        }

        public com.airbnb.android.core.wishlists.WishListableData.Builder allowAutoAdd(boolean allowAutoAdd2) {
            this.allowAutoAdd = Boolean.valueOf(allowAutoAdd2);
            return this;
        }

        /* access modifiers changed from: 0000 */
        public WishListableData autoBuild() {
            String missing = "";
            if (this.type == null) {
                missing = missing + " type";
            }
            if (this.itemId == null) {
                missing = missing + " itemId";
            }
            if (this.source == null) {
                missing = missing + " source";
            }
            if (this.allowAutoAdd == null) {
                missing = missing + " allowAutoAdd";
            }
            if (missing.isEmpty()) {
                return new AutoValue_WishListableData(this.type, this.itemId.longValue(), this.suggestedWishListName, this.guestDetails, this.checkIn, this.checkOut, this.searchSessionId, this.source, this.allowAutoAdd.booleanValue());
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_WishListableData(WishListableType type2, long itemId2, String suggestedWishListName2, GuestDetails guestDetails2, AirDate checkIn2, AirDate checkOut2, String searchSessionId2, C2813WishlistSource source2, boolean allowAutoAdd2) {
        if (type2 == null) {
            throw new NullPointerException("Null type");
        }
        this.type = type2;
        this.itemId = itemId2;
        this.suggestedWishListName = suggestedWishListName2;
        this.guestDetails = guestDetails2;
        this.checkIn = checkIn2;
        this.checkOut = checkOut2;
        this.searchSessionId = searchSessionId2;
        if (source2 == null) {
            throw new NullPointerException("Null source");
        }
        this.source = source2;
        this.allowAutoAdd = allowAutoAdd2;
    }

    public WishListableType type() {
        return this.type;
    }

    public long itemId() {
        return this.itemId;
    }

    public String suggestedWishListName() {
        return this.suggestedWishListName;
    }

    public GuestDetails guestDetails() {
        return this.guestDetails;
    }

    public AirDate checkIn() {
        return this.checkIn;
    }

    public AirDate checkOut() {
        return this.checkOut;
    }

    public String searchSessionId() {
        return this.searchSessionId;
    }

    public C2813WishlistSource source() {
        return this.source;
    }

    public boolean allowAutoAdd() {
        return this.allowAutoAdd;
    }

    public String toString() {
        return "WishListableData{type=" + this.type + ", itemId=" + this.itemId + ", suggestedWishListName=" + this.suggestedWishListName + ", guestDetails=" + this.guestDetails + ", checkIn=" + this.checkIn + ", checkOut=" + this.checkOut + ", searchSessionId=" + this.searchSessionId + ", source=" + this.source + ", allowAutoAdd=" + this.allowAutoAdd + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof WishListableData)) {
            return false;
        }
        WishListableData that = (WishListableData) o;
        if (!this.type.equals(that.type()) || this.itemId != that.itemId() || (this.suggestedWishListName != null ? !this.suggestedWishListName.equals(that.suggestedWishListName()) : that.suggestedWishListName() != null) || (this.guestDetails != null ? !this.guestDetails.equals(that.guestDetails()) : that.guestDetails() != null) || (this.checkIn != null ? !this.checkIn.equals(that.checkIn()) : that.checkIn() != null) || (this.checkOut != null ? !this.checkOut.equals(that.checkOut()) : that.checkOut() != null) || (this.searchSessionId != null ? !this.searchSessionId.equals(that.searchSessionId()) : that.searchSessionId() != null) || !this.source.equals(that.source()) || this.allowAutoAdd != that.allowAutoAdd()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((((int) (((long) (((1 * 1000003) ^ this.type.hashCode()) * 1000003)) ^ ((this.itemId >>> 32) ^ this.itemId))) * 1000003) ^ (this.suggestedWishListName == null ? 0 : this.suggestedWishListName.hashCode())) * 1000003) ^ (this.guestDetails == null ? 0 : this.guestDetails.hashCode())) * 1000003) ^ (this.checkIn == null ? 0 : this.checkIn.hashCode())) * 1000003) ^ (this.checkOut == null ? 0 : this.checkOut.hashCode())) * 1000003;
        if (this.searchSessionId != null) {
            i = this.searchSessionId.hashCode();
        }
        return ((((h ^ i) * 1000003) ^ this.source.hashCode()) * 1000003) ^ (this.allowAutoAdd ? 1231 : HelpCenterArticle.VERIFIED_ID_LEARN_MORE);
    }
}
