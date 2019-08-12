package com.airbnb.android.core.viewcomponents.models;

import android.content.res.Resources;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.EntryMarquee;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class EntryMarqueeEpoxyModel extends AirEpoxyModel<EntryMarquee> {
    int captionRes;
    CharSequence captionText;
    int titleRes;
    CharSequence titleText;
    boolean topPadding = true;

    public int getDefaultLayout() {
        if (this.topPadding) {
            return C0716R.layout.n2_view_holder_entry_marquee;
        }
        return C0716R.layout.view_holder_entry_marquee_no_top_padding;
    }

    public void bind(EntryMarquee marquee) {
        super.bind(marquee);
        Resources resources = marquee.getResources();
        marquee.setTitle(this.titleRes != 0 ? resources.getString(this.titleRes) : this.titleText);
        marquee.setCaption(this.captionRes != 0 ? resources.getString(this.captionRes) : this.captionText);
    }

    public EntryMarqueeEpoxyModel title(CharSequence string) {
        this.titleRes = 0;
        this.titleText = string;
        return this;
    }

    public EntryMarqueeEpoxyModel title(int stringRes) {
        this.titleText = null;
        this.titleRes = stringRes;
        return this;
    }

    public EntryMarqueeEpoxyModel caption(CharSequence string) {
        this.captionRes = 0;
        this.captionText = string;
        return this;
    }

    public EntryMarqueeEpoxyModel caption(int stringRes) {
        this.captionText = null;
        this.captionRes = stringRes;
        return this;
    }

    public int getDividerViewType() {
        return 2;
    }
}
