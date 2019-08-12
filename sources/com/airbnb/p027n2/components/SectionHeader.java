package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import android.widget.TextView;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.components.SectionHeader */
public class SectionHeader extends BaseDividerComponent {
    @BindView
    AirTextView button;
    @BindView
    AirTextView descriptionView;
    @BindView
    AirTextView titleView;

    public SectionHeader(Context context) {
        super(context);
    }

    public SectionHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SectionHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_section_header;
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        super.init(attrs);
        Paris.style(this).apply(attrs);
        super.showDivider(false);
    }

    public void setTitle(int stringRes) {
        setTitle((CharSequence) getContext().getString(stringRes));
    }

    public void setTitle(CharSequence title) {
        this.titleView.setText(title);
    }

    public void setDescription(int stringRes) {
        setDescription((CharSequence) getContext().getString(stringRes));
    }

    public void setDescription(CharSequence description) {
        ViewLibUtils.bindOptionalTextView((TextView) this.descriptionView, description, true);
    }

    public void setButtonText(int stringRes) {
        setButtonText((CharSequence) getContext().getString(stringRes));
    }

    public void setButtonText(CharSequence buttonText) {
        ViewLibUtils.bindOptionalTextView((TextView) this.button, buttonText);
    }

    public void setButtonOnClickListener(OnClickListener listener) {
        this.button.setOnClickListener(listener);
    }

    public void setButtonVisible(boolean visible) {
        ViewLibUtils.setVisibleIf(this.button, visible);
    }

    public void showDivider(boolean showDivider) {
        if (showDivider) {
            Paris.style(this).apply(R.style.n2_Internal_SectionHeader_Secondary);
        } else {
            Paris.style(this).apply(R.style.n2_Internal_SectionHeader_First);
        }
    }

    public static void mock(SectionHeader header) {
        header.setTitle((CharSequence) "First section header");
    }

    public static void mockPlus(SectionHeader header) {
        header.setTitle((CharSequence) "First section header +");
        header.setDescription((CharSequence) "Optionally the quick brown fox jumped over the neighbors dog.");
        header.setButtonText((CharSequence) "See all");
        header.setButtonOnClickListener(SectionHeader$$Lambda$1.lambdaFactory$(header));
    }

    public static void mockPlusLongTitle(SectionHeader header) {
        header.setTitle((CharSequence) "First section header with a long title");
        header.setDescription((CharSequence) "Optionally the quick brown fox jumped over the neighbors dog.");
        header.setButtonText((CharSequence) "See all");
        header.setButtonOnClickListener(SectionHeader$$Lambda$2.lambdaFactory$(header));
    }

    public static void mockBabuButton(SectionHeader header) {
        header.setTitle((CharSequence) "First section header +");
        header.setButtonText((CharSequence) "See all");
        Paris.style(header).applyFirstBabuLink();
        header.setButtonOnClickListener(SectionHeader$$Lambda$3.lambdaFactory$(header));
    }

    public static void mockSecondary(SectionHeader header) {
        header.setTitle((CharSequence) "Section header");
        Paris.style(header).applySecondary();
    }

    public static void mockSecondaryPlus(SectionHeader header) {
        header.setTitle((CharSequence) "Section header +");
        header.setDescription((CharSequence) "Optionally the quick brown fox jumped over the neighbors dog.");
        header.setButtonText((CharSequence) "See all");
        Paris.style(header).applySecondary();
        header.setButtonOnClickListener(SectionHeader$$Lambda$4.lambdaFactory$(header));
    }
}
