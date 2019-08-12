package com.airbnb.p027n2.components;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.StandardsBarContent;

/* renamed from: com.airbnb.n2.components.ProfileCompletionBarRow_ViewBinding */
public final class ProfileCompletionBarRow_ViewBinding implements Unbinder {
    private ProfileCompletionBarRow target;

    public ProfileCompletionBarRow_ViewBinding(ProfileCompletionBarRow target2, View source) {
        this.target = target2;
        target2.title = (TextView) Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
        target2.progressLabel = (TextView) Utils.findRequiredViewAsType(source, R.id.progress_label, "field 'progressLabel'", TextView.class);
        target2.subtitle = (TextView) Utils.findRequiredViewAsType(source, R.id.subtitle, "field 'subtitle'", TextView.class);
        target2.standardsBarContent = (StandardsBarContent) Utils.findRequiredViewAsType(source, R.id.bar, "field 'standardsBarContent'", StandardsBarContent.class);
    }

    public void unbind() {
        ProfileCompletionBarRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.title = null;
        target2.progressLabel = null;
        target2.subtitle = null;
        target2.standardsBarContent = null;
    }
}
