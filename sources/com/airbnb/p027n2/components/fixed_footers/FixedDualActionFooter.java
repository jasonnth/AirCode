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
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.airbnb.paris.LayoutParamsStyleApplier.Option;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.components.fixed_footers.FixedDualActionFooter */
public class FixedDualActionFooter extends ConstraintLayout {
    @BindView
    View divider;
    @BindView
    AirButton primaryButton;
    @BindView
    AirButton secondaryButton;

    public FixedDualActionFooter(Context context) {
        super(context);
        init(null);
    }

    public FixedDualActionFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public FixedDualActionFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_fixed_dual_action_footer, this);
        ButterKnife.bind((View) this);
        Paris.style(this).apply(attrs);
        this.primaryButton.enableClickWhenLoading(true);
    }

    /* access modifiers changed from: 0000 */
    public void setButtonStyle(int styleRes) {
        ((AirButtonStyleApplier) Paris.style(this.primaryButton).addOption(Option.IgnoreLayoutWidthAndHeight)).apply(styleRes);
    }

    public void setButtonText(int stringRes) {
        ViewLibUtils.bindOptionalTextView((TextView) this.primaryButton, stringRes);
    }

    public void setButtonText(CharSequence buttonText) {
        ViewLibUtils.bindOptionalTextView((TextView) this.primaryButton, buttonText);
    }

    public void setButtonLoading(boolean loading) {
        this.primaryButton.setState(loading ? State.Loading : State.Normal, this.primaryButton.getCurrentTextColor());
    }

    public void setSecondaryButtonLoading(boolean loading) {
        this.secondaryButton.setState(loading ? State.Loading : State.Normal, this.secondaryButton.getCurrentTextColor());
    }

    public void setButtonOnClickListener(OnClickListener listener) {
        this.primaryButton.setOnClickListener(listener);
    }

    public void setButtonEnabled(boolean enabled) {
        this.primaryButton.setEnabled(enabled);
    }

    public void setSecondaryButtonEnabled(boolean enabled) {
        this.secondaryButton.setEnabled(enabled);
    }

    /* access modifiers changed from: 0000 */
    public void setSecondaryButtonStyle(int styleRes) {
        ((AirButtonStyleApplier) Paris.style(this.secondaryButton).addOption(Option.IgnoreLayoutWidthAndHeight)).apply(styleRes);
    }

    public void setSecondaryButtonText(int stringRes) {
        ViewLibUtils.bindOptionalTextView((TextView) this.secondaryButton, stringRes);
    }

    public void setSecondaryButtonText(CharSequence buttonText) {
        ViewLibUtils.bindOptionalTextView((TextView) this.secondaryButton, buttonText);
    }

    public void setSecondaryButtonOnClickListener(OnClickListener listener) {
        this.secondaryButton.setOnClickListener(listener);
    }

    private static void mockDefault(FixedDualActionFooter button) {
        button.setButtonText((CharSequence) "Action");
        button.setButtonOnClickListener(FixedDualActionFooter$$Lambda$1.lambdaFactory$());
        button.setSecondaryButtonText((CharSequence) "Secondary Action");
        button.setSecondaryButtonOnClickListener(FixedDualActionFooter$$Lambda$2.lambdaFactory$());
    }

    static /* synthetic */ void lambda$mockDefault$0(View view) {
    }

    static /* synthetic */ void lambda$mockDefault$1(View view) {
    }

    public static void mockRausch(FixedDualActionFooter button) {
        mockDefault(button);
    }

    public static void mockRauschLongPrimaryButtonText(FixedDualActionFooter button) {
        mockDefault(button);
        button.setButtonText((CharSequence) "A button that goes really really really really long");
    }

    public static void mockRauschLongSecondaryButtonText(FixedDualActionFooter button) {
        mockDefault(button);
        button.setSecondaryButtonText((CharSequence) "A button that goes really really really really long");
    }

    public static void mockRauschNoSecondaryAction(FixedDualActionFooter button) {
        button.setButtonText((CharSequence) "Action");
        button.setButtonOnClickListener(FixedDualActionFooter$$Lambda$3.lambdaFactory$());
    }

    static /* synthetic */ void lambda$mockRauschNoSecondaryAction$2(View view) {
    }

    public static void mockRauschNoPrimaryAction(FixedDualActionFooter button) {
        button.setSecondaryButtonText((CharSequence) "Secondary Action");
        button.setSecondaryButtonOnClickListener(FixedDualActionFooter$$Lambda$4.lambdaFactory$());
    }

    static /* synthetic */ void lambda$mockRauschNoPrimaryAction$3(View view) {
    }

    public static void mockRauschDisabled(FixedDualActionFooter button) {
        mockDefault(button);
        button.setButtonText((CharSequence) "Disabled Action");
        button.setButtonEnabled(false);
    }

    public static void mockRauschWaiting(FixedDualActionFooter button) {
        mockDefault(button);
        button.setButtonLoading(true);
    }

    public static void mockBabu(FixedDualActionFooter button) {
        mockDefault(button);
        Paris.style(button).applyBabu();
    }

    public static void mockBabuDisabled(FixedDualActionFooter button) {
        mockDefault(button);
        button.setButtonText((CharSequence) "Disabled Action");
        Paris.style(button).applyBabu();
        button.setButtonEnabled(false);
    }

    public static void mockBabuWaiting(FixedDualActionFooter button) {
        mockDefault(button);
        Paris.style(button).applyBabu();
        button.setButtonLoading(true);
    }

    public static void mockJellyfish(FixedDualActionFooter button) {
        mockDefault(button);
        Paris.style(button).applyWhite();
    }

    public static void mockJellyfishDisabled(FixedDualActionFooter button) {
        mockDefault(button);
        button.setButtonText((CharSequence) "Disabled Action");
        Paris.style(button).applyWhite();
        button.setButtonEnabled(false);
    }

    public static void mockJellyfishWaiting(FixedDualActionFooter button) {
        mockDefault(button);
        Paris.style(button).applyWhite();
        button.setButtonLoading(true);
    }
}
