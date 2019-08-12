package com.airbnb.android.superhero;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class SuperHeroThreadFragment_ViewBinding implements Unbinder {
    private SuperHeroThreadFragment target;

    public SuperHeroThreadFragment_ViewBinding(SuperHeroThreadFragment target2, View source) {
        this.target = target2;
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C1713R.C1715id.recycler_view, "field 'recyclerView'", RecyclerView.class);
    }

    public void unbind() {
        SuperHeroThreadFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.recyclerView = null;
    }
}
