package com.airbnb.android.cityregistration.adapters;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.airbnb.android.cityregistration.C5630R;
import com.airbnb.android.core.models.ListingRegistrationQuestion;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.FullImageRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import java.io.File;

public class CityRegistrationDocReviewAdapter extends AirEpoxyAdapter {
    Context context;
    Listener listener;
    ListingRegistrationQuestion question;

    public interface Listener {
        void getDocPhoto(ListingRegistrationQuestion listingRegistrationQuestion);

        void showDocumentTypeSelection(ListingRegistrationQuestion listingRegistrationQuestion);
    }

    public CityRegistrationDocReviewAdapter(Context context2, Listener listener2, ListingRegistrationQuestion question2, String filePath, String url, Bundle savedInstanceState) {
        onRestoreInstanceState(savedInstanceState);
        this.context = context2;
        this.listener = listener2;
        this.question = question2;
        StandardRowEpoxyModel_ standardRow = new StandardRowEpoxyModel_().title((CharSequence) question2.getAnswer(question2.getInputAnswer()).getText()).actionText(C5630R.string.cityregistration_change_doc_type).clickListener(CityRegistrationDocReviewAdapter$$Lambda$1.lambdaFactory$(listener2, question2));
        standardRow.showDivider(true);
        addModel(standardRow);
        FullImageRowEpoxyModel_ fullImageRowEpoxyModel_ = new FullImageRowEpoxyModel_();
        if (url == null) {
            url = Uri.fromFile(new File(filePath)).toString();
        }
        addModel(fullImageRowEpoxyModel_.imageUrl(url).onClickListener(CityRegistrationDocReviewAdapter$$Lambda$2.lambdaFactory$(listener2, question2)));
    }
}
