package com.airbnb.android.core.viewcomponents.models;

import android.content.res.Resources;
import android.view.View.OnClickListener;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class DocumentMarqueeEpoxyModel extends AirEpoxyModel<DocumentMarquee> {
    int captionRes;
    CharSequence captionText;
    OnClickListener clickListener;
    OnClickListener linkClickListener;
    int linkRes;
    CharSequence linkText;
    int titleRes;
    CharSequence titleText;
    String userImageUrl;

    public void bind(DocumentMarquee marquee) {
        super.bind(marquee);
        Resources resources = marquee.getResources();
        marquee.setTitle(this.titleRes != 0 ? resources.getString(this.titleRes) : this.titleText);
        marquee.setCaption(this.captionRes != 0 ? resources.getString(this.captionRes) : this.captionText);
        marquee.setLinkText(this.linkRes != 0 ? resources.getString(this.linkRes) : this.linkText);
        marquee.setLinkClickListener(this.linkClickListener);
        marquee.setOnClickListener(this.clickListener);
        marquee.setUserImageUrl(this.userImageUrl);
    }

    public DocumentMarqueeEpoxyModel titleText(CharSequence titleText2) {
        this.titleRes = 0;
        this.titleText = titleText2;
        return this;
    }

    public DocumentMarqueeEpoxyModel captionText(CharSequence captionText2) {
        this.captionRes = 0;
        this.captionText = captionText2;
        return this;
    }

    public int getDividerViewType() {
        return 2;
    }
}
