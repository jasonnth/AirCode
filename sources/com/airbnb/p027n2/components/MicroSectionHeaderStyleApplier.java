package com.airbnb.p027n2.components;

import com.airbnb.n2.R;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;

/* renamed from: com.airbnb.n2.components.MicroSectionHeaderStyleApplier */
public final class MicroSectionHeaderStyleApplier extends StyleApplier<MicroSectionHeaderStyleApplier, MicroSectionHeader> {
    public MicroSectionHeaderStyleApplier(MicroSectionHeader view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new SectionHeaderStyleApplier((SectionHeader) getView()).apply(style);
    }

    public MicroSectionHeaderStyleApplier applyFirstBabuLink() {
        return (MicroSectionHeaderStyleApplier) apply(R.style.n2_MicroSectionHeader_First_BabuLink);
    }

    public MicroSectionHeaderStyleApplier applySecondary() {
        return (MicroSectionHeaderStyleApplier) apply(R.style.n2_MicroSectionHeader_Secondary);
    }
}
