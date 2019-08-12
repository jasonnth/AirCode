package com.airbnb.android.core.wishlists;

import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.BaseLogger;
import com.airbnb.android.core.models.WishList;
import com.airbnb.android.core.utils.SearchJitneyUtils;
import com.airbnb.jitney.event.logging.ExploreSubtab.p098v1.C2139ExploreSubtab;
import com.airbnb.jitney.event.logging.Saved.p241v1.SavedClickCardListViewEvent;
import com.airbnb.jitney.event.logging.Saved.p241v1.SavedClickCollaboratorsEvent;
import com.airbnb.jitney.event.logging.Saved.p241v1.SavedClickMapCloseEvent;
import com.airbnb.jitney.event.logging.Saved.p241v1.SavedClickMapMarkerEvent;
import com.airbnb.jitney.event.logging.Saved.p241v1.SavedClickSavedMapViewEvent;
import com.airbnb.jitney.event.logging.Saved.p241v1.SavedClickShareButtonEvent;
import com.airbnb.jitney.event.logging.Saved.p241v1.SavedClickSubtabEvent;
import com.airbnb.jitney.event.logging.Saved.p241v1.SavedClickWishlistIndexViewEvent;
import com.airbnb.jitney.event.logging.Saved.p241v1.SavedSwipeCarouselMapViewEvent;
import com.airbnb.jitney.event.logging.Saved.p242v2.SavedClickCardMapViewEvent;
import com.airbnb.jitney.event.logging.Saved.p242v2.SavedClickInviteCollaboratorsEvent;
import com.airbnb.jitney.event.logging.Saved.p243v3.SavedClickDoneInCreateWishlistEvent;
import com.airbnb.jitney.event.logging.Saved.p243v3.SavedClickToWishlistEvent.Builder;
import com.airbnb.jitney.event.logging.Saved.p243v3.SavedClickVoteWishlistEvent;
import com.airbnb.jitney.event.logging.Saved.p243v3.SavedClickWishlistInWishlistPickerEvent;
import com.airbnb.jitney.event.logging.VoteMethod.p289v1.C2806VoteMethod;
import com.airbnb.jitney.event.logging.WishlistMethod.p291v1.C2809WishlistMethod;
import com.airbnb.jitney.event.logging.WishlistPrivacy.p292v1.C2810WishlistPrivacy;
import com.airbnb.jitney.event.logging.WishlistView.p296v1.C2814WishlistView;
import com.airbnb.jitney.event.logging.WishlistedItemType.p297v1.C2815WishlistedItemType;

public final class WishListLogger extends BaseLogger {
    private long maxMapCarouselScrollPosition = 0;

    public WishListLogger(LoggingContextFactory loggingContextFactory) {
        super(loggingContextFactory);
    }

    private static C2815WishlistedItemType getTypeForLogging(WishListableData data) {
        return getTypeForLogging(data.type());
    }

    private static C2815WishlistedItemType getTypeForLogging(WishListItem item) {
        return getTypeForLogging(item.getItemType());
    }

    private static C2815WishlistedItemType getTypeForLogging(WishListableType type) {
        switch (type) {
            case Home:
                return C2815WishlistedItemType.Home;
            case Place:
                return C2815WishlistedItemType.Place;
            case PlaceActivity:
                return C2815WishlistedItemType.Activity;
            case Trip:
                return C2815WishlistedItemType.Experience;
            default:
                BugsnagWrapper.notify((Throwable) new IllegalStateException("Unknown type: " + type));
                return C2815WishlistedItemType.Home;
        }
    }

    public void heartClickedToAdd(WishListableData data) {
        clickToWishListEvent(data, C2809WishlistMethod.Add);
    }

    public void heartClickedToRemove(WishListableData data) {
        clickToWishListEvent(data, C2809WishlistMethod.Remove);
    }

    private void clickToWishListEvent(WishListableData data, C2809WishlistMethod method) {
        publish(new Builder(context(), method, data.source(), getTypeForLogging(data), Long.valueOf(data.itemId()), data.searchSessionId()));
    }

    public void clickWishListInPicker(WishListableData data, WishList wishList) {
        publish(new SavedClickWishlistInWishlistPickerEvent.Builder(context(), data.source(), getTypeForLogging(data), Long.valueOf(data.itemId()), Long.valueOf(wishList.getId()), data.searchSessionId()));
    }

    public void clickCreateWishList(WishListableData data, WishList wishList) {
        publish(new SavedClickDoneInCreateWishlistEvent.Builder(context(), data.source(), wishList.getName(), wishList.isPrivateWishList() ? C2810WishlistPrivacy.InviteOnly : C2810WishlistPrivacy.Everyone, data.searchSessionId()));
    }

    public void clickDetailsSubTab(WishList wishList, C2139ExploreSubtab previousTab, C2139ExploreSubtab newTab) {
        publish(new SavedClickSubtabEvent.Builder(context(), Long.valueOf(wishList.getId()), newTab, previousTab));
    }

    public void clickWishListFromIndex(WishList wishList) {
        publish(new SavedClickWishlistIndexViewEvent.Builder(context(), Long.valueOf(wishList.getId())));
    }

    public void clickWishListedItem(WishList wishList, WishListItem item, C2139ExploreSubtab currentTab) {
        publish(new SavedClickCardListViewEvent.Builder(context(), currentTab, getTypeForLogging(item), Long.valueOf(item.getItemId()), Long.valueOf(wishList.getId())));
    }

    public void clickInviteCollaborators(WishList wishList) {
        publish(new SavedClickInviteCollaboratorsEvent.Builder(context(), Long.valueOf(wishList.getId())));
    }

    public void clickShareButton(WishList wishList) {
        publish(new SavedClickShareButtonEvent.Builder(context(), Long.valueOf(wishList.getId())));
    }

    public void clickCollaborators(WishList wishList) {
        publish(new SavedClickCollaboratorsEvent.Builder(context(), Long.valueOf(wishList.getId())));
    }

    public void clickVoteOnItem(WishList wishList, C2806VoteMethod voteMethod, WishListItem item) {
        publish(new SavedClickVoteWishlistEvent.Builder(context(), C2814WishlistView.WishlistDetail, voteMethod, getTypeForLogging(item), Long.valueOf(item.getItemId()), Long.valueOf(wishList.getId())));
    }

    public void clickGoToMap(WishList wishList) {
        publish(new SavedClickSavedMapViewEvent.Builder(context(), C2139ExploreSubtab.Homes, Long.valueOf(wishList.getId())));
    }

    public void clickCloseMap(WishList wishList) {
        publish(new SavedClickMapCloseEvent.Builder(context(), C2139ExploreSubtab.Homes, Long.valueOf(wishList.getId())));
    }

    public void clickMapHomeCard(WishList wishList, WishListItem item) {
        publish(new SavedClickCardMapViewEvent.Builder(context(), C2139ExploreSubtab.Homes, getTypeForLogging(item), Long.valueOf(item.getItemId()), Long.valueOf(wishList.getId())));
    }

    public void clickMapMarker(WishList wishList, WishListItem item) {
        publish(new SavedClickMapMarkerEvent.Builder(context(), C2139ExploreSubtab.Homes, getTypeForLogging(item), Long.valueOf(item.getItemId()), Long.valueOf(wishList.getId())));
    }

    public void swipeMapCarousel(WishList wishList, String scrollType, int selectedPosition) {
        this.maxMapCarouselScrollPosition = Math.max((long) selectedPosition, this.maxMapCarouselScrollPosition);
        publish(new SavedSwipeCarouselMapViewEvent.Builder(context(), C2139ExploreSubtab.Homes, SearchJitneyUtils.getJitneyDirectionForScrollType(scrollType), Long.valueOf(wishList.getId()), Long.valueOf(this.maxMapCarouselScrollPosition)));
    }
}
