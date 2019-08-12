package com.airbnb.p027n2.components.fixed_footers;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import com.airbnb.p027n2.primitives.AirButtonStyleApplier;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.AirTextViewStyleApplier;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.airbnb.paris.LayoutParamsStyleApplier.Option;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.components.fixed_footers.FixedFlowActionFooter */
public class FixedFlowActionFooter extends ConstraintLayout {
    @BindView
    AirButton button;
    @BindView
    View divider;
    @BindView
    AirTextView subtitle;
    @BindView
    AirTextView title;

    public FixedFlowActionFooter(Context context) {
        super(context);
        init(null);
    }

    public FixedFlowActionFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public FixedFlowActionFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_fixed_flow_action_footer, this);
        ButterKnife.bind((View) this);
        Paris.style(this).apply(attrs);
        this.button.enableClickWhenLoading(true);
    }

    /* access modifiers changed from: 0000 */
    public void setTitleStyle(int styleRes) {
        ((AirTextViewStyleApplier) Paris.style(this.title).addOption(Option.IgnoreLayoutWidthAndHeight)).apply(styleRes);
    }

    public void setTitle(int stringRes) {
        ViewLibUtils.bindOptionalTextView((TextView) this.title, stringRes);
    }

    public void setTitle(CharSequence title2) {
        ViewLibUtils.bindOptionalTextView((TextView) this.title, title2);
    }

    /* access modifiers changed from: 0000 */
    public void setSubtitleStyle(int styleRes) {
        ((AirTextViewStyleApplier) Paris.style(this.subtitle).addOption(Option.IgnoreLayoutWidthAndHeight)).apply(styleRes);
    }

    public void setSubtitle(int stringRes) {
        ViewLibUtils.bindOptionalTextView((TextView) this.subtitle, stringRes);
    }

    public void setSubtitle(CharSequence subtitle2) {
        ViewLibUtils.bindOptionalTextView((TextView) this.subtitle, subtitle2);
    }

    /* access modifiers changed from: 0000 */
    public void setButtonStyle(int styleRes) {
        ((AirButtonStyleApplier) Paris.style(this.button).addOption(Option.IgnoreLayoutWidthAndHeight)).apply(styleRes);
    }

    public void setButtonText(int stringRes) {
        ViewLibUtils.setText((TextView) this.button, stringRes);
    }

    public void setButtonText(CharSequence buttonText) {
        ViewLibUtils.setText((TextView) this.button, buttonText);
    }

    public void setButtonLoading(boolean loading) {
        this.button.setState(loading ? State.Loading : State.Normal, this.button.getCurrentTextColor());
    }

    public void setButtonOnClickListener(OnClickListener listener) {
        this.button.setOnClickListener(listener);
    }

    public void setButtonEnabled(boolean enabled) {
        this.button.setEnabled(enabled);
    }

    private static void mockDefault(FixedFlowActionFooter button2) {
        button2.setTitle((CharSequence) "Optional Title");
        button2.setSubtitle((CharSequence) "Optional Subtitle / Action");
        button2.setButtonText((CharSequence) "Action");
        button2.setButtonOnClickListener(FixedFlowActionFooter$$Lambda$1.lambdaFactory$());
    }

    static /* synthetic */ void lambda$mockDefault$0(View view) {
    }

    public static void mockRausch(FixedFlowActionFooter button2) {
        mockDefault(button2);
    }

    public static void mockRauschLongTitles(FixedFlowActionFooter button2) {
        mockDefault(button2);
        button2.setTitle((CharSequence) "Optional title that goes very very very long");
        button2.setSubtitle((CharSequence) "Optional subtitle / action long longer");
    }

    public static void mockRauschLongButtonText(FixedFlowActionFooter button2) {
        mockDefault(button2);
        button2.setButtonText((CharSequence) "A button that goes really really really really long");
    }

    public static void mockRauschNoSubtitle(FixedFlowActionFooter button2) {
        button2.setTitle((CharSequence) "Optional Title");
        button2.setButtonText((CharSequence) "Action");
        button2.setButtonOnClickListener(FixedFlowActionFooter$$Lambda$2.lambdaFactory$());
    }

    static /* synthetic */ void lambda$mockRauschNoSubtitle$1(View view) {
    }

    public static void mockRauschNoTitles(FixedFlowActionFooter button2) {
        button2.setButtonText((CharSequence) "Action");
        button2.setButtonOnClickListener(FixedFlowActionFooter$$Lambda$3.lambdaFactory$());
    }

    static /* synthetic */ void lambda$mockRauschNoTitles$2(View view) {
    }

    public static void mockRauschDisabled(FixedFlowActionFooter button2) {
        mockDefault(button2);
        button2.setButtonText((CharSequence) "Disabled Action");
        button2.setButtonEnabled(false);
    }

    public static void mockRauschWaiting(FixedFlowActionFooter button2) {
        mockDefault(button2);
        button2.setButtonLoading(true);
    }

    public static void mockBabu(FixedFlowActionFooter button2) {
        mockDefault(button2);
        Paris.style(button2).applyBabu();
    }

    public static void mockBabuDisabled(FixedFlowActionFooter button2) {
        mockDefault(button2);
        button2.setButtonText((CharSequence) "Disabled Action");
        Paris.style(button2).applyBabu();
        button2.setButtonEnabled(false);
    }

    public static void mockBabuWaiting(FixedFlowActionFooter button2) {
        mockDefault(button2);
        Paris.style(button2).applyBabu();
        button2.setButtonLoading(true);
    }

    public static void mockJellyfish(FixedFlowActionFooter button2) {
        mockDefault(button2);
        Paris.style(button2).applyWhite();
    }

    public static void mockJellyfishDisabled(FixedFlowActionFooter button2) {
        mockDefault(button2);
        button2.setButtonText((CharSequence) "Disabled Action");
        Paris.style(button2).applyWhite();
        button2.setButtonEnabled(false);
    }

    public static void mockJellyfishWaiting(FixedFlowActionFooter button2) {
        mockDefault(button2);
        Paris.style(button2).applyWhite();
        button2.setButtonLoading(true);
    }
}
