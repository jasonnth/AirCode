package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.ArticleDocumentMarquee_ViewBinding */
public class ArticleDocumentMarquee_ViewBinding implements Unbinder {
    private ArticleDocumentMarquee target;

    public ArticleDocumentMarquee_ViewBinding(ArticleDocumentMarquee target2, View source) {
        this.target = target2;
        target2.kickerTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.kicker, "field 'kickerTextView'", AirTextView.class);
        target2.titleTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title_text, "field 'titleTextView'", AirTextView.class);
        target2.captionTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.caption_text, "field 'captionTextView'", AirTextView.class);
        target2.linkTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.link_text, "field 'linkTextView'", AirTextView.class);
        target2.divider = Utils.findRequiredView(source, R.id.divider, "field 'divider'");
    }

    public void unbind() {
        ArticleDocumentMarquee target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.kickerTextView = null;
        target2.titleTextView = null;
        target2.captionTextView = null;
        target2.linkTextView = null;
        target2.divider = null;
    }
}
