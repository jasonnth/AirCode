package com.airbnb.android.core.utils;

import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.enums.SpaceType;
import com.airbnb.jitney.event.logging.CancelPolicy.p046v1.C1885CancelPolicy;
import com.airbnb.jitney.event.logging.Direction.p012v1.C0940Direction;
import com.airbnb.jitney.event.logging.RoomType.p239v1.C2680RoomType;
import com.airbnb.jitney.event.logging.SearchContext.p249v1.C2731SearchContext;
import com.airbnb.jitney.event.logging.SearchContext.p249v1.C2731SearchContext.Builder;
import com.airbnb.p027n2.utils.ScrollDirectionListener;
import java.util.ArrayList;
import java.util.List;

public class SearchJitneyUtils {
    private SearchJitneyUtils() {
    }

    public static C0940Direction getJitneyDirectionForScrollType(String direction) {
        char c = 65535;
        switch (direction.hashCode()) {
            case -397214166:
                if (direction.equals(ScrollDirectionListener.SCROLL_RIGHT)) {
                    c = 3;
                    break;
                }
                break;
            case 417791309:
                if (direction.equals(ScrollDirectionListener.SCROLL_UP)) {
                    c = 0;
                    break;
                }
                break;
            case 2064985812:
                if (direction.equals(ScrollDirectionListener.SCROLL_DOWN)) {
                    c = 1;
                    break;
                }
                break;
            case 2065214009:
                if (direction.equals(ScrollDirectionListener.SCROLL_LEFT)) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return C0940Direction.Up;
            case 1:
                return C0940Direction.Down;
            case 2:
                return C0940Direction.Left;
            case 3:
                return C0940Direction.Right;
            default:
                BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalArgumentException("Unknown scroll direction: " + direction));
                return C0940Direction.Right;
        }
    }

    public static List<Long> intArrayToLongList(int[] arr) {
        List<Long> longList = new ArrayList<>(arr.length);
        for (int member : arr) {
            longList.add(Long.valueOf((long) member));
        }
        return longList;
    }

    public static C2680RoomType spaceTypeToRoomType(SpaceType spaceType) {
        switch (spaceType) {
            case PrivateRoom:
                return C2680RoomType.PrivateRoom;
            case EntireHome:
                return C2680RoomType.EntireHome;
            case SharedSpace:
                return C2680RoomType.SharedRoom;
            default:
                IllegalStateException e = new IllegalStateException("Listing doesn't have proper room type: " + spaceType);
                BugsnagWrapper.notify((Throwable) e);
                throw e;
        }
    }

    public static C1885CancelPolicy getCancellationPolicyFromString(String cancellationPolicy) {
        char c = 65535;
        switch (cancellationPolicy.hashCode()) {
            case -1875662038:
                if (cancellationPolicy.equals("strict_new")) {
                    c = 9;
                    break;
                }
                break;
            case -891986231:
                if (cancellationPolicy.equals("strict")) {
                    c = 2;
                    break;
                }
                break;
            case -626156444:
                if (cancellationPolicy.equals("moderate_new")) {
                    c = 8;
                    break;
                }
                break;
            case -618857213:
                if (cancellationPolicy.equals("moderate")) {
                    c = 1;
                    break;
                }
                break;
            case -584019664:
                if (cancellationPolicy.equals("super_strict_30_new")) {
                    c = 10;
                    break;
                }
                break;
            case -498132211:
                if (cancellationPolicy.equals("super_strict_60_new")) {
                    c = 11;
                    break;
                }
                break;
            case -476956721:
                if (cancellationPolicy.equals("super_strict_30")) {
                    c = 3;
                    break;
                }
                break;
            case -476956628:
                if (cancellationPolicy.equals("super_strict_60")) {
                    c = 4;
                    break;
                }
                break;
            case -132710992:
                if (cancellationPolicy.equals("long_term_new")) {
                    c = 12;
                    break;
                }
                break;
            case 128115791:
                if (cancellationPolicy.equals("long_term")) {
                    c = 6;
                    break;
                }
                break;
            case 243168125:
                if (cancellationPolicy.equals("no_refunds")) {
                    c = 5;
                    break;
                }
                break;
            case 1540788012:
                if (cancellationPolicy.equals("flexible_new")) {
                    c = 7;
                    break;
                }
                break;
            case 1744737227:
                if (cancellationPolicy.equals("flexible")) {
                    c = 0;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return C1885CancelPolicy.CANCEL_FLEXIBLE;
            case 1:
                return C1885CancelPolicy.CANCEL_MODERATE;
            case 2:
                return C1885CancelPolicy.CANCEL_STRICT;
            case 3:
                return C1885CancelPolicy.CANCEL_SUPER_STRICT;
            case 4:
                return C1885CancelPolicy.CANCEL_SUPER_STRICT_60;
            case 5:
                return C1885CancelPolicy.CANCEL_NO_REFUNDS;
            case 6:
                return C1885CancelPolicy.CANCEL_LONG_TERM;
            case 7:
                return C1885CancelPolicy.CANCEL_FLEXIBLE_NEW;
            case 8:
                return C1885CancelPolicy.CANCEL_MODERATE_NEW;
            case 9:
                return C1885CancelPolicy.CANCEL_STRICT_NEW;
            case 10:
                return C1885CancelPolicy.CANCEL_SUPER_STRICT_30_NEW;
            case 11:
                return C1885CancelPolicy.CANCEL_SUPER_STRICT_60_NEW;
            case 12:
                return C1885CancelPolicy.CANCEL_LONG_TERM_NEW;
            default:
                BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalArgumentException("unknown value: " + cancellationPolicy));
                return null;
        }
    }

    public static C2731SearchContext searchContext(String searchId, String searchSessionId) {
        return new Builder(SanitizeUtils.emptyIfNull(searchId), SanitizeUtils.emptyIfNull(searchSessionId)).build();
    }
}
