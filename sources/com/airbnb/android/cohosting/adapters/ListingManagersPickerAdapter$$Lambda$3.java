package com.airbnb.android.cohosting.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.CohostInvitation;

final /* synthetic */ class ListingManagersPickerAdapter$$Lambda$3 implements OnClickListener {
    private final ListingManagersPickerAdapter arg$1;
    private final CohostInvitation arg$2;

    private ListingManagersPickerAdapter$$Lambda$3(ListingManagersPickerAdapter listingManagersPickerAdapter, CohostInvitation cohostInvitation) {
        this.arg$1 = listingManagersPickerAdapter;
        this.arg$2 = cohostInvitation;
    }

    public static OnClickListener lambdaFactory$(ListingManagersPickerAdapter listingManagersPickerAdapter, CohostInvitation cohostInvitation) {
        return new ListingManagersPickerAdapter$$Lambda$3(listingManagersPickerAdapter, cohostInvitation);
    }

    public void onClick(View view) {
        ListingManagersPickerAdapter.lambda$addInvitationModels$2(this.arg$1, this.arg$2, view);
    }
}
