package com.airbnb.android.core.viewcomponents.models;

import android.content.Context;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.KickerMarquee;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class KickerMarqueeEpoxyModel extends AirEpoxyModel<KickerMarquee> {
    boolean isSubtitleBold = true;
    int kickerRes;
    String kickerText;
    int subTitleRes;
    String subTitleText;
    int titleRes;
    String titleText;

    public void bind(KickerMarquee marquee) {
        super.bind(marquee);
        Context context = marquee.getContext();
        marquee.setTitle(this.titleRes != 0 ? context.getString(this.titleRes) : this.titleText);
        marquee.setSubtitle(this.subTitleRes != 0 ? context.getString(this.subTitleRes) : this.subTitleText);
        marquee.setKicker((CharSequence) this.kickerRes != 0 ? context.getString(this.kickerRes) : this.kickerText);
    }

    public int getDefaultLayout() {
        return this.isSubtitleBold ? C0716R.layout.view_holder_kicker_marquee_white : C0716R.layout.view_holder_kicker_marquee_whitelite;
    }
}
