package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.google.android.flexbox.FlexboxLayout;

/* renamed from: com.airbnb.n2.components.TagsCollectionRow_ViewBinding */
public class TagsCollectionRow_ViewBinding implements Unbinder {
    private TagsCollectionRow target;

    public TagsCollectionRow_ViewBinding(TagsCollectionRow target2, View source) {
        this.target = target2;
        target2.container = (FlexboxLayout) Utils.findRequiredViewAsType(source, R.id.flexbox, "field 'container'", FlexboxLayout.class);
        target2.sectionHeader = (SectionHeader) Utils.findRequiredViewAsType(source, R.id.section_header, "field 'sectionHeader'", SectionHeader.class);
        target2.divider = Utils.findRequiredView(source, R.id.section_divider, "field 'divider'");
    }

    public void unbind() {
        TagsCollectionRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.container = null;
        target2.sectionHeader = null;
        target2.divider = null;
    }
}
