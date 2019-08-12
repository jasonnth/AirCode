package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.models.User;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.MessageItemEpoxyModel_;
import com.airbnb.android.listing.C7213R;
import com.airbnb.epoxy.EpoxyModel;

public class ManageListingPreBookingPreviewAdapter extends ManageListingAdapter {
    private DocumentMarqueeEpoxyModel_ header = new DocumentMarqueeEpoxyModel_();
    private MessageItemEpoxyModel_ messageThread = new MessageItemEpoxyModel_().receiverNoTail();

    ManageListingPreBookingPreviewAdapter(ManageListingDataController controller) {
        super(controller);
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.header, this.messageThread});
    }

    public void dataUpdated() {
    }

    public void dataLoading(boolean loading) {
    }

    public void updateUserAndMessage(User user, String message) {
        this.header.titleRes(C7213R.string.manage_listing_prebooking_preview_title).captionRes(C7213R.string.manage_listing_prebooking_preview_subtitle);
        this.messageThread.profileImageUrl(user.getPictureUrlForThumbnail()).messageText(message);
    }
}
