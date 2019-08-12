package com.airbnb.android.wishlists;

import com.airbnb.android.core.viewcomponents.models.EpoxyModelMixer;
import com.airbnb.android.core.wishlists.WishListItem;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

class WLVotingWrapperModel extends EpoxyModelMixer {
    WLVotingWrapperModel(WishListItem item, WLVotingRowModel_ votingRowModel, AirEpoxyModel<?> cardModel) {
        super(0, cardModel, votingRowModel);
        switch (item.getItemType()) {
            case Home:
                layout(C1758R.layout.model_wl_voting_wrapper_home);
                return;
            case Place:
            case PlaceActivity:
                votingRowModel.gridMode(true);
                layout(C1758R.layout.model_wl_voting_wrapper_place);
                return;
            case Trip:
                votingRowModel.gridMode(true);
                layout(C1758R.layout.model_wl_voting_wrapper_trip);
                return;
            default:
                throw new IllegalStateException("Unknown type: " + item.getItemType());
        }
    }
}
