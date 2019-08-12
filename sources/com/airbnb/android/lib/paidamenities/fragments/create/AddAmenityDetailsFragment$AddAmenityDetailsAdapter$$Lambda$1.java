package com.airbnb.android.lib.paidamenities.fragments.create;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.lib.paidamenities.fragments.create.AddAmenityDetailsFragment.AddAmenityDetailsAdapter;

final /* synthetic */ class AddAmenityDetailsFragment$AddAmenityDetailsAdapter$$Lambda$1 implements OnClickListener {
    private final AddAmenityDetailsAdapter arg$1;

    private AddAmenityDetailsFragment$AddAmenityDetailsAdapter$$Lambda$1(AddAmenityDetailsAdapter addAmenityDetailsAdapter) {
        this.arg$1 = addAmenityDetailsAdapter;
    }

    public static OnClickListener lambdaFactory$(AddAmenityDetailsAdapter addAmenityDetailsAdapter) {
        return new AddAmenityDetailsFragment$AddAmenityDetailsAdapter$$Lambda$1(addAmenityDetailsAdapter);
    }

    public void onClick(View view) {
        AddAmenityDetailsFragment.this.navigationController.displayAddDescription(AddAmenityDetailsFragment.this.amenityDescription, AddAmenityDetailsFragment.this.getString(AddAmenityDetailsFragment.this.paidAmenityCategory.getAmenityServerType().getHintBodyTextRes()));
    }
}
