package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.components.MicroSectionHeader */
public class MicroSectionHeader extends SectionHeader {
    public MicroSectionHeader(Context context) {
        super(context);
    }

    public MicroSectionHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MicroSectionHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Deprecated
    public void setTitleMaxLines(int maxLines) {
        this.titleView.setMaxLines(maxLines);
    }

    @Deprecated
    public void invertColors(boolean invertColors) {
        this.titleView.setHasInvertedColors(invertColors);
        this.descriptionView.setHasInvertedColors(invertColors);
        this.button.setHasInvertedColors(invertColors);
    }

    public static void mock(MicroSectionHeader header) {
        header.setTitle((CharSequence) "First micro section header");
    }

    public static void mockPlus(MicroSectionHeader header) {
        header.setTitle((CharSequence) "First micro section header +");
        header.setDescription((CharSequence) "Optionally the quick brown fox jumped over the neighbors dog.");
        header.setButtonText((CharSequence) "See all");
        header.setButtonOnClickListener(MicroSectionHeader$$Lambda$1.lambdaFactory$(header));
    }

    public static void mockPlusLongTitle(MicroSectionHeader header) {
        header.setTitle((CharSequence) "First micro section header with a long title");
        header.setDescription((CharSequence) "Optionally the quick brown fox jumped over the neighbors dog.");
        header.setButtonText((CharSequence) "See all");
        header.setButtonOnClickListener(MicroSectionHeader$$Lambda$2.lambdaFactory$(header));
    }

    public static void mockBabuButton(MicroSectionHeader header) {
        header.setTitle((CharSequence) "First micro section header +");
        header.setButtonText((CharSequence) "See all");
        Paris.style(header).applyFirstBabuLink();
        header.setButtonOnClickListener(MicroSectionHeader$$Lambda$3.lambdaFactory$(header));
    }

    public static void mockSecondary(MicroSectionHeader header) {
        header.setTitle((CharSequence) "Micro section header");
        Paris.style(header).applySecondary();
    }

    public static void mockSecondaryPlus(MicroSectionHeader header) {
        header.setTitle((CharSequence) "Micro section header +");
        header.setDescription((CharSequence) "Optionally the quick brown fox jumped over the neighbors dog.");
        header.setButtonText((CharSequence) "See all");
        Paris.style(header).applySecondary();
        header.setButtonOnClickListener(MicroSectionHeader$$Lambda$4.lambdaFactory$(header));
    }
}
