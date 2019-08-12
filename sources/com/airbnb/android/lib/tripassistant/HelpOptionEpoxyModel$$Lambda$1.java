package com.airbnb.android.lib.tripassistant;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class HelpOptionEpoxyModel$$Lambda$1 implements OnClickListener {
    private final HelpOptionEpoxyModel arg$1;

    private HelpOptionEpoxyModel$$Lambda$1(HelpOptionEpoxyModel helpOptionEpoxyModel) {
        this.arg$1 = helpOptionEpoxyModel;
    }

    public static OnClickListener lambdaFactory$(HelpOptionEpoxyModel helpOptionEpoxyModel) {
        return new HelpOptionEpoxyModel$$Lambda$1(helpOptionEpoxyModel);
    }

    public void onClick(View view) {
        this.arg$1.helpOptionClickListener.onOptionSelected(NodeSelection.builder().issue(this.arg$1.issue).node(this.arg$1.node).option(this.arg$1.option).build());
    }
}
