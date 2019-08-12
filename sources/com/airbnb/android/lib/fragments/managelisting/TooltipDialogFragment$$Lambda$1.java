package com.airbnb.android.lib.fragments.managelisting;

import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.p027n2.interfaces.LinkOnClickListener;

final /* synthetic */ class TooltipDialogFragment$$Lambda$1 implements LinkOnClickListener {
    private final TooltipDialogFragment arg$1;
    private final String arg$2;

    private TooltipDialogFragment$$Lambda$1(TooltipDialogFragment tooltipDialogFragment, String str) {
        this.arg$1 = tooltipDialogFragment;
        this.arg$2 = str;
    }

    public static LinkOnClickListener lambdaFactory$(TooltipDialogFragment tooltipDialogFragment, String str) {
        return new TooltipDialogFragment$$Lambda$1(tooltipDialogFragment, str);
    }

    public void onClickLink(int i) {
        WebViewIntentBuilder.startMobileWebActivity(this.arg$1.getActivity(), this.arg$2);
    }
}
