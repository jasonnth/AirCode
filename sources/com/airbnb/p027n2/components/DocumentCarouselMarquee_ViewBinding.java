package com.airbnb.p027n2.components;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.DocumentCarouselMarquee_ViewBinding */
public class DocumentCarouselMarquee_ViewBinding implements Unbinder {
    private DocumentCarouselMarquee target;

    public DocumentCarouselMarquee_ViewBinding(DocumentCarouselMarquee target2, View source) {
        this.target = target2;
        target2.titleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title_text, "field 'titleText'", AirTextView.class);
        target2.carousel = (RecyclerView) Utils.findRequiredViewAsType(source, R.id.carousel, "field 'carousel'", RecyclerView.class);
        target2.loadingView = (LoadingView) Utils.findRequiredViewAsType(source, R.id.loader, "field 'loadingView'", LoadingView.class);
    }

    public void unbind() {
        DocumentCarouselMarquee target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleText = null;
        target2.carousel = null;
        target2.loadingView = null;
    }
}
