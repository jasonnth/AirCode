package com.airbnb.android.core.viewcomponents.models;

import android.content.Context;
import android.text.TextUtils;
import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.utils.TranslationUtils;
import com.airbnb.p027n2.components.ListingDescription;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class ListingDescriptionEpoxyModel extends AirEpoxyModel<ListingDescription> {
    String descriptionLocale;
    boolean isTranslatable;
    OnClickListener readMoreClickListener;
    String space;
    String summary;
    String summaryHighlight;
    OnClickListener translateClickListener;
    String translatedText;

    public void bind(ListingDescription view) {
        super.bind(view);
        Context context = view.getContext();
        boolean hasTranslation = !TextUtils.isEmpty(this.translatedText);
        if (this.isTranslatable) {
            view.setTranslateText(TranslationUtils.getTranslateLink(view.getContext(), this.descriptionLocale, hasTranslation, C0716R.color.c_babu, this.translateClickListener));
            view.setTranslateClickListener(this.translateClickListener);
        } else {
            view.setTranslateText(null);
            view.setTranslateClickListener(null);
        }
        CharSequence readMoreText = view.getResources().getString(C0716R.string.read_more_lower_cased);
        if (hasTranslation) {
            view.setSummaryText(null, null);
            view.setSpaceDescriptionText(null, this.translatedText, readMoreText);
        } else {
            view.setSummaryText(context.getString(C0716R.string.lys_about_this_home_title), this.summary);
            view.setSpaceDescriptionText(context.getString(C0716R.string.lys_the_space), this.space, readMoreText);
        }
        view.setSummaryHighLight(this.summaryHighlight);
        view.setOnClickListener(this.readMoreClickListener);
    }

    public void unbind(ListingDescription view) {
        view.setOnClickListener(null);
        view.setTranslateClickListener(null);
    }

    public int getDividerViewType() {
        return 0;
    }
}
