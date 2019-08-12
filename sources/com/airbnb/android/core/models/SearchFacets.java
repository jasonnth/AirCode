package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenSearchFacets;
import com.google.common.collect.Maps;
import java.util.HashMap;
import java.util.Map;

public class SearchFacets extends GenSearchFacets {
    public static final Creator<SearchFacets> CREATOR = new Creator<SearchFacets>() {
        public SearchFacets[] newArray(int size) {
            return new SearchFacets[size];
        }

        public SearchFacets createFromParcel(Parcel source) {
            SearchFacets object = new SearchFacets();
            object.readFromParcel(source);
            return object;
        }
    };
    private Map<RoomAvailabilityType, Integer> roomAvailabilityTypeFacet;
    private Map<C6120RoomType, Integer> roomTypeFacet;

    public boolean facetGreaterThanZero(C6120RoomType roomType) {
        if (this.mRoomType == null) {
            return true;
        }
        Integer facetCount = (Integer) getRoomTypeFacet().get(roomType);
        if (facetCount == null || facetCount.intValue() <= 0) {
            return false;
        }
        return true;
    }

    private Map<C6120RoomType, Integer> getRoomTypeFacet() {
        if (this.roomTypeFacet == null) {
            this.roomTypeFacet = new HashMap(C6120RoomType.values().length);
            for (SearchFacetWithStringKey facet : this.mRoomType) {
                this.roomTypeFacet.put(C6120RoomType.fromKey(facet.getKey()), Integer.valueOf(facet.getCount()));
            }
        }
        return this.roomTypeFacet;
    }

    public int getBusinessTravelReadyAvailabilityFacetCount() {
        if (getRoomAvailabilityTypeFacet().containsKey(RoomAvailabilityType.BusinessTravelReady)) {
            return ((Integer) this.roomAvailabilityTypeFacet.get(RoomAvailabilityType.BusinessTravelReady)).intValue();
        }
        return -1;
    }

    private Map<RoomAvailabilityType, Integer> getRoomAvailabilityTypeFacet() {
        if (this.roomAvailabilityTypeFacet == null) {
            this.roomAvailabilityTypeFacet = Maps.newHashMap();
            if (this.mAvailability != null) {
                for (SearchFacetWithStringKey facet : this.mAvailability) {
                    RoomAvailabilityType availabilityType = RoomAvailabilityType.fromKey(facet.getKey());
                    if (availabilityType != null) {
                        this.roomAvailabilityTypeFacet.put(availabilityType, Integer.valueOf(facet.getCount()));
                    }
                }
            }
        }
        return this.roomAvailabilityTypeFacet;
    }
}
