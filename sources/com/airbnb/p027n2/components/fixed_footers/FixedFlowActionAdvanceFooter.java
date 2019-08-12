package com.airbnb.p027n2.components.fixed_footers;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.components.fixed_footers.FixedFlowActionAdvanceFooter */
public final class FixedFlowActionAdvanceFooter extends FixedFlowActionFooter {
    public FixedFlowActionAdvanceFooter(Context context) {
        super(context);
    }

    public FixedFlowActionAdvanceFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FixedFlowActionAdvanceFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void collapse(boolean collapse) {
        boolean z = true;
        ViewLibUtils.setVisibleIf(this.title, !collapse);
        AirTextView airTextView = this.subtitle;
        if (collapse) {
            z = false;
        }
        ViewLibUtils.setVisibleIf(airTextView, z);
        Paris.style(this.button).apply(collapse ? R.style.n2_Internal_Button_FixedFlowActionFooter_Collapsed : R.style.n2_Internal_Button_FixedFlowActionFooter);
    }

    public static void mockDefault(FixedFlowActionAdvanceFooter button) {
        button.setTitle((CharSequence) "Optional Title");
        button.setSubtitle((CharSequence) "Optional Subtitle / Action");
        button.setButtonText((CharSequence) "Action");
        button.setButtonOnClickListener(FixedFlowActionAdvanceFooter$$Lambda$1.lambdaFactory$());
    }

    static /* synthetic */ void lambda$mockDefault$0(View view) {
    }

    public static void mockRausch(FixedFlowActionAdvanceFooter button) {
        mockDefault(button);
    }

    public static void mockRauschLongTitles(FixedFlowActionAdvanceFooter button) {
        mockDefault(button);
        button.setTitle((CharSequence) "Optional title that goes very very very long");
        button.setSubtitle((CharSequence) "Optional subtitle / action long longer");
    }

    public static void mockRauschLongButtonText(FixedFlowActionAdvanceFooter button) {
        mockDefault(button);
        button.setButtonText((CharSequence) "A button that goes really really really really long");
    }

    public static void mockRauschNoSubtitle(FixedFlowActionAdvanceFooter button) {
        button.setTitle((CharSequence) "Optional Title");
        button.setButtonText((CharSequence) "Action");
        button.setButtonOnClickListener(FixedFlowActionAdvanceFooter$$Lambda$2.lambdaFactory$());
    }

    static /* synthetic */ void lambda$mockRauschNoSubtitle$1(View view) {
    }

    public static void mockRauschNoTitles(FixedFlowActionAdvanceFooter button) {
        button.setButtonText((CharSequence) "Action");
        button.setButtonOnClickListener(FixedFlowActionAdvanceFooter$$Lambda$3.lambdaFactory$());
    }

    static /* synthetic */ void lambda$mockRauschNoTitles$2(View view) {
    }

    public static void mockRauschDisabled(FixedFlowActionAdvanceFooter button) {
        mockDefault(button);
        button.setButtonText((CharSequence) "Disabled Action");
        button.setButtonEnabled(false);
    }

    public static void mockRauschWaiting(FixedFlowActionAdvanceFooter button) {
        mockDefault(button);
        button.setButtonLoading(true);
    }

    public static void mockRauschCollapsed(FixedFlowActionAdvanceFooter button) {
        mockDefault(button);
        Paris.style(button).applyCollapsedRausch();
    }

    public static void mockBabu(FixedFlowActionAdvanceFooter button) {
        mockDefault(button);
        Paris.style(button).applyBabu();
    }

    public static void mockBabuDisabled(FixedFlowActionAdvanceFooter button) {
        mockDefault(button);
        Paris.style(button).applyBabu();
        button.setButtonEnabled(false);
    }

    public static void mockBabuWaiting(FixedFlowActionAdvanceFooter button) {
        mockDefault(button);
        Paris.style(button).applyBabu();
        button.setButtonLoading(true);
    }

    public static void mockBabuCollapsed(FixedFlowActionAdvanceFooter button) {
        mockDefault(button);
        Paris.style(button).applyCollapsedBabu();
    }

    public static void mockJellyfish(FixedFlowActionAdvanceFooter button) {
        mockDefault(button);
        Paris.style(button).applyWhite();
    }

    public static void mockJellyfishDisabled(FixedFlowActionAdvanceFooter button) {
        mockDefault(button);
        Paris.style(button).applyWhite();
        button.setButtonEnabled(false);
    }

    public static void mockJellyfishWaiting(FixedFlowActionAdvanceFooter button) {
        mockDefault(button);
        Paris.style(button).applyWhite();
        button.setButtonLoading(true);
    }

    public static void mockJellyfishCollapsed(FixedFlowActionAdvanceFooter button) {
        mockDefault(button);
        Paris.style(button).applyCollapsedWhite();
    }
}
