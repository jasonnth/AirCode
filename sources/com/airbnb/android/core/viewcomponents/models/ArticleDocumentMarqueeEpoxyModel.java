package com.airbnb.android.core.viewcomponents.models;

import android.content.res.Resources;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.ArticleDocumentMarquee;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class ArticleDocumentMarqueeEpoxyModel extends AirEpoxyModel<ArticleDocumentMarquee> {
    int captionRes;
    CharSequence captionText;
    int kickerRes;
    CharSequence kickerText;
    int titleRes;
    CharSequence titleText;
    boolean withTopPadding = true;

    public int getDefaultLayout() {
        return this.withTopPadding ? C0716R.layout.n2_view_holder_article_document_marquee : C0716R.layout.n2_view_holder_article_document_marquee_no_top_padding;
    }

    public void bind(ArticleDocumentMarquee marquee) {
        super.bind(marquee);
        Resources resources = marquee.getResources();
        marquee.setTitleText(this.titleRes != 0 ? resources.getString(this.titleRes) : this.titleText);
        marquee.setCaptionText(this.captionRes != 0 ? resources.getString(this.captionRes) : this.captionText);
        marquee.setKickerText(this.kickerRes != 0 ? resources.getString(this.kickerRes) : this.kickerText);
    }

    public ArticleDocumentMarqueeEpoxyModel titleText(CharSequence titleText2) {
        this.titleRes = 0;
        this.titleText = titleText2;
        return this;
    }

    public ArticleDocumentMarqueeEpoxyModel captionText(CharSequence captionText2) {
        this.captionRes = 0;
        this.captionText = captionText2;
        return this;
    }

    public ArticleDocumentMarqueeEpoxyModel kickerText(CharSequence kickerText2) {
        this.kickerRes = 0;
        this.kickerText = kickerText2;
        return this;
    }

    public int getDividerViewType() {
        return 2;
    }
}
