package com.airbnb.android.core.models;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.enums.SpaceType;
import com.airbnb.android.core.models.generated.GenNestedListing;
import com.airbnb.android.core.utils.ImageUtils;
import com.google.common.collect.FluentIterable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class NestedListing extends GenNestedListing {
    public static final Comparator<NestedListing> COMPARE_BY_NAME = NestedListing$$Lambda$9.lambdaFactory$();
    public static final Comparator<NestedListing> COMPARE_BY_TYPE = NestedListing$$Lambda$10.lambdaFactory$();
    public static final Creator<NestedListing> CREATOR = new Creator<NestedListing>() {
        public NestedListing[] newArray(int size) {
            return new NestedListing[size];
        }

        public NestedListing createFromParcel(Parcel source) {
            NestedListing object = new NestedListing();
            object.readFromParcel(source);
            return object;
        }
    };

    public enum NestedAction {
        UnlinkParent,
        ChooseChildren,
        ChooseParent
    }

    private static Comparator<NestedListing> getComparatorByTypeAndName(boolean entireHomeFirst) {
        return NestedListing$$Lambda$1.lambdaFactory$(entireHomeFirst);
    }

    static /* synthetic */ int lambda$getComparatorByTypeAndName$2(boolean entireHomeFirst, NestedListing listing1, NestedListing listing2) {
        int result;
        if (entireHomeFirst) {
            result = COMPARE_BY_TYPE.compare(listing1, listing2);
        } else {
            result = COMPARE_BY_TYPE.compare(listing2, listing1);
        }
        if (result == 0) {
            return listing1.getName().compareTo(listing2.getName());
        }
        return result;
    }

    private static Comparator<NestedListing> getComparatorByStatusTypeName(boolean entireHomeFirst) {
        return NestedListing$$Lambda$2.lambdaFactory$(getComparatorByTypeAndName(entireHomeFirst));
    }

    static /* synthetic */ int lambda$getComparatorByStatusTypeName$3(Comparator compareByTypeAndName, NestedListing listing1, NestedListing listing2) {
        int result;
        if (listing1.isActive() == listing2.isActive()) {
            result = 0;
        } else if (listing1.isActive()) {
            result = -1;
        } else {
            result = 1;
        }
        if (result == 0) {
            return compareByTypeAndName.compare(listing1, listing2);
        }
        return result;
    }

    private static Comparator<NestedListing> getComparatorByZipTypeName(String zip, boolean entireHomeFirst) {
        return NestedListing$$Lambda$3.lambdaFactory$(zip, getComparatorByTypeAndName(entireHomeFirst));
    }

    static /* synthetic */ int lambda$getComparatorByZipTypeName$4(String zip, Comparator compareByTypeAndName, NestedListing listing1, NestedListing listing2) {
        int result;
        if (listing1.getZipCode().equals(zip) == listing2.getZipCode().equals(zip)) {
            result = 0;
        } else if (listing1.getZipCode().equals(zip)) {
            result = -1;
        } else {
            result = 1;
        }
        if (result == 0) {
            return compareByTypeAndName.compare(listing1, listing2);
        }
        return result;
    }

    private static Comparator<NestedListing> getComparatorByStatusZipTypeName(String zip, boolean entireHomeFirst) {
        return NestedListing$$Lambda$4.lambdaFactory$(getComparatorByZipTypeName(zip, entireHomeFirst));
    }

    static /* synthetic */ int lambda$getComparatorByStatusZipTypeName$5(Comparator compareByZipTypeName, NestedListing listing1, NestedListing listing2) {
        int result;
        if (listing1.isActive() == listing2.isActive()) {
            result = 0;
        } else if (listing1.isActive()) {
            result = -1;
        } else {
            result = 1;
        }
        if (result == 0) {
            return compareByZipTypeName.compare(listing1, listing2);
        }
        return result;
    }

    public static NestedAction getAvailableAction(long listingId, HashMap<Long, NestedListing> nestedListingsById) {
        NestedListing thisNestedListing = (NestedListing) nestedListingsById.get(Long.valueOf(listingId));
        if (nestedListingsById.size() < 2 || thisNestedListing == null) {
            return null;
        }
        if (thisNestedListing.hasParent()) {
            return NestedAction.UnlinkParent;
        }
        if (thisNestedListing.getNumberOfChildren() > 0) {
            return NestedAction.ChooseChildren;
        }
        int numOtherUnlinkedListings = 0;
        int numExistingParentListings = 0;
        for (NestedListing nestedListing : nestedListingsById.values()) {
            if (nestedListing.getId() != thisNestedListing.getId()) {
                if (nestedListing.isUnlinked()) {
                    numOtherUnlinkedListings++;
                } else if (nestedListing.isParent()) {
                    numExistingParentListings++;
                }
            }
        }
        if (numOtherUnlinkedListings > 0) {
            return NestedAction.ChooseChildren;
        }
        if (numExistingParentListings > 0 || numOtherUnlinkedListings > 0) {
            return NestedAction.ChooseParent;
        }
        BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Unknown nested action for listingId=" + listingId));
        return null;
    }

    private static ArrayList<NestedListing> getCandidateNestedChildren(long listingId, HashMap<Long, NestedListing> nestedListingsById) {
        ArrayList<NestedListing> nestedListings = new ArrayList<>();
        for (NestedListing nestedListing : nestedListingsById.values()) {
            if (nestedListing.getId() != listingId && (nestedListing.hasThisParent(listingId) || nestedListing.isUnlinked())) {
                nestedListings.add(nestedListing);
            }
        }
        return nestedListings;
    }

    public static ArrayList<NestedListing> getCandidateListings(NestedAction action, long listingId, HashMap<Long, NestedListing> nestedListingsById) {
        ArrayList<NestedListing> candidates = new ArrayList<>();
        NestedListing listing = (NestedListing) nestedListingsById.get(Long.valueOf(listingId));
        if (listing == null) {
            return candidates;
        }
        switch (action) {
            case ChooseChildren:
                return getCandidateNestedChildren(listing.getId(), nestedListingsById);
            case UnlinkParent:
                if (listing.getParentListingId() == null) {
                    return candidates;
                }
                NestedListing parent = (NestedListing) nestedListingsById.get(listing.getParentListingId());
                if (parent == null) {
                    return candidates;
                }
                candidates.add(parent);
                return candidates;
            default:
                return candidates;
        }
    }

    public static List<NestedListing> getChildrenCandidates(long parentId, Collection<NestedListing> nestedListings) {
        return FluentIterable.from((Iterable<E>) nestedListings).filter(NestedListing$$Lambda$5.lambdaFactory$(parentId)).toSortedList(COMPARE_BY_NAME);
    }

    public static boolean isPossibleParentChild(long parentId, NestedListing child) {
        return child.getId() != parentId && (child.hasThisParent(parentId) || child.isUnlinked());
    }

    public static List<NestedListing> getParentListings(Collection<NestedListing> nestedListings) {
        return FluentIterable.from((Iterable<E>) nestedListings).filter(NestedListing$$Lambda$6.lambdaFactory$()).toSortedList(COMPARE_BY_NAME);
    }

    public boolean hasParent() {
        return getParentListingId() != null;
    }

    public boolean hasThisParent(long listingId) {
        return hasParent() && getParentListingId().longValue() == listingId;
    }

    public boolean isParent() {
        return getNumberOfChildren() > 0;
    }

    public int getNumberOfChildren() {
        List<Long> childListingIds = getChildListingIds();
        if (childListingIds == null) {
            return 0;
        }
        return childListingIds.size();
    }

    public boolean isUnlinked() {
        return !hasParent() && getNumberOfChildren() == 0;
    }

    public static List<NestedListing> getUnlinkedListings(Collection<NestedListing> nestedlistings) {
        return FluentIterable.from((Iterable<E>) nestedlistings).filter(NestedListing$$Lambda$7.lambdaFactory$()).toSortedList(COMPARE_BY_NAME);
    }

    public static boolean canLinkMore(Collection<NestedListing> nestedlistings) {
        return getUnlinkedListings(nestedlistings).size() >= 2;
    }

    public SpaceType getSpaceType() {
        return SpaceType.getTypeFromKey(getRoomType());
    }

    public String getRoomTypeForSubtitle(Context context) {
        SpaceType type = getSpaceType();
        if (type != null) {
            return context.getString(type.titleId);
        }
        return "";
    }

    public String getThumbnailUrl() {
        return ImageUtils.parseListingThumbnailUrl(this.mThumbnailUrl);
    }

    public static List<NestedListing> sortByStatusAndType(Collection<NestedListing> nestedListings, boolean entireHomeFirst) {
        return FluentIterable.from((Iterable<E>) nestedListings).toSortedList(getComparatorByStatusTypeName(entireHomeFirst));
    }

    public static List<NestedListing> sortByStatusTypeAndZip(Collection<NestedListing> nestedListings, String zipCode, boolean entireHomeFirst) {
        return FluentIterable.from((Iterable<E>) nestedListings).toSortedList(getComparatorByStatusZipTypeName(zipCode, entireHomeFirst));
    }

    public static HashMap<Long, NestedListing> listToHashById(List<NestedListing> nestedListings) {
        return new HashMap<>(FluentIterable.from((Iterable<E>) nestedListings).uniqueIndex(NestedListing$$Lambda$8.lambdaFactory$()));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NestedListing ").append(getId()).append(" ").append(getName()).append("\n");
        sb.append("parentListingId ").append(getParentListingId() == null ? "null" : getParentListingId()).append("\n");
        sb.append("childListingIds ");
        if (getChildListingIds() != null) {
            for (Long childListingId : getChildListingIds()) {
                sb.append(childListingId).append(", ");
            }
        }
        sb.append("\n");
        return sb.toString();
    }
}
