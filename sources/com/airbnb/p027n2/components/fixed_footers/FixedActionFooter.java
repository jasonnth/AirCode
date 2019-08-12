package com.airbnb.p027n2.components.fixed_footers;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.components.fixed_footers.FixedActionFooter */
public final class FixedActionFooter extends LinearLayout {
    @BindView
    AirButton button;
    @BindView
    View divider;

    public FixedActionFooter(Context context) {
        super(context);
        init(null);
    }

    public FixedActionFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public FixedActionFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_fixed_action_footer, this);
        setOrientation(1);
        ButterKnife.bind((View) this);
        Paris.style(this).apply(attrs);
        this.button.enableClickWhenLoading(true);
    }

    public void setButtonText(int stringRes) {
        this.button.setText(stringRes);
    }

    public void setButtonText(CharSequence buttonText) {
        this.button.setText(buttonText);
    }

    @Deprecated
    public void setText(CharSequence buttonText) {
        this.button.setText(buttonText);
    }

    public void setButtonLoading(boolean loading) {
        this.button.setState(loading ? State.Loading : State.Normal, this.button.getCurrentTextColor());
    }

    public void setButtonOnClickListener(OnClickListener listener) {
        this.button.setOnClickListener(listener);
    }

    @Deprecated
    public void setOnClickListener(OnClickListener listener) {
        setButtonOnClickListener(listener);
    }

    public void setButtonEnabled(boolean enabled) {
        this.button.setEnabled(enabled);
    }

    @Deprecated
    public void setEnabled(boolean enabled) {
        setButtonEnabled(enabled);
    }

    public static void mockRauschWaiting(FixedActionFooter button2) {
        button2.setButtonText((CharSequence) "Primary Button");
        button2.setButtonOnClickListener(FixedActionFooter$$Lambda$1.lambdaFactory$());
        button2.setButtonLoading(true);
    }

    static /* synthetic */ void lambda$mockRauschWaiting$0(View view) {
    }

    public static void mockRausch(FixedActionFooter button2) {
        button2.setButtonText((CharSequence) "Primary Button");
        button2.setButtonOnClickListener(FixedActionFooter$$Lambda$2.lambdaFactory$());
    }

    static /* synthetic */ void lambda$mockRausch$1(View view) {
    }

    public static void mockRauschLongButtonText(FixedActionFooter button2) {
        button2.setButtonText((CharSequence) "A button that goes really really really really really really really really long");
        button2.setButtonOnClickListener(FixedActionFooter$$Lambda$3.lambdaFactory$());
    }

    static /* synthetic */ void lambda$mockRauschLongButtonText$2(View view) {
    }

    public static void mockRauschDisabled(FixedActionFooter button2) {
        button2.setButtonText((CharSequence) "Disabled Primary Button");
        button2.setButtonOnClickListener(FixedActionFooter$$Lambda$4.lambdaFactory$());
        button2.setButtonEnabled(false);
    }

    static /* synthetic */ void lambda$mockRauschDisabled$3(View view) {
    }

    public static void mockBabuWaiting(FixedActionFooter button2) {
        button2.setButtonText((CharSequence) "Primary Button");
        Paris.style(button2).applyBabu();
        button2.setButtonOnClickListener(FixedActionFooter$$Lambda$5.lambdaFactory$());
        button2.setButtonLoading(true);
    }

    static /* synthetic */ void lambda$mockBabuWaiting$4(View view) {
    }

    public static void mockBabu(FixedActionFooter button2) {
        button2.setButtonText((CharSequence) "Primary Button");
        Paris.style(button2).applyBabu();
        button2.setButtonOnClickListener(FixedActionFooter$$Lambda$6.lambdaFactory$());
    }

    static /* synthetic */ void lambda$mockBabu$5(View view) {
    }

    public static void mockBabuDisabled(FixedActionFooter button2) {
        button2.setButtonText((CharSequence) "Disabled Primary Button");
        Paris.style(button2).applyBabu();
        button2.setButtonOnClickListener(FixedActionFooter$$Lambda$7.lambdaFactory$());
        button2.setButtonEnabled(false);
    }

    static /* synthetic */ void lambda$mockBabuDisabled$6(View view) {
    }

    public static void mockJellyfishWaiting(FixedActionFooter button2) {
        button2.setButtonText((CharSequence) "Primary Button");
        Paris.style(button2).applyWhite();
        button2.setButtonOnClickListener(FixedActionFooter$$Lambda$8.lambdaFactory$());
        button2.setButtonLoading(true);
    }

    static /* synthetic */ void lambda$mockJellyfishWaiting$7(View view) {
    }

    public static void mockJellyfish(FixedActionFooter button2) {
        button2.setButtonText((CharSequence) "Primary Button");
        Paris.style(button2).applyWhite();
        button2.setButtonOnClickListener(FixedActionFooter$$Lambda$9.lambdaFactory$());
    }

    static /* synthetic */ void lambda$mockJellyfish$8(View view) {
    }

    public static void mockJellyfishDisabled(FixedActionFooter button2) {
        button2.setButtonText((CharSequence) "Disabled Primary Button");
        Paris.style(button2).applyWhite();
        button2.setButtonOnClickListener(FixedActionFooter$$Lambda$10.lambdaFactory$());
        button2.setButtonEnabled(false);
    }

    static /* synthetic */ void lambda$mockJellyfishDisabled$9(View view) {
    }
}
