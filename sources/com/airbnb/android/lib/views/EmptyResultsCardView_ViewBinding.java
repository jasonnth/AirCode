package com.airbnb.android.lib.views;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class EmptyResultsCardView_ViewBinding implements Unbinder {
    private EmptyResultsCardView target;

    public EmptyResultsCardView_ViewBinding(EmptyResultsCardView target2) {
        this(target2, target2);
    }

    public EmptyResultsCardView_ViewBinding(EmptyResultsCardView target2, View source) {
        this.target = target2;
        target2.backgroundImg = (ImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.img_bg, "field 'backgroundImg'", ImageView.class);
        target2.topTitle = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.top_title, "field 'topTitle'", TextView.class);
        target2.cardTitle = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.card_title, "field 'cardTitle'", TextView.class);
        target2.cardSubtitle = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.card_subtitle, "field 'cardSubtitle'", TextView.class);
        target2.actionButton = (Button) Utils.findRequiredViewAsType(source, C0880R.C0882id.action_button, "field 'actionButton'", Button.class);
    }

    public void unbind() {
        EmptyResultsCardView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.backgroundImg = null;
        target2.topTitle = null;
        target2.cardTitle = null;
        target2.cardSubtitle = null;
        target2.actionButton = null;
    }
}
