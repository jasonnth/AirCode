package com.airbnb.android.mythbusters;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.AirToolbar;

public class MythbustersQuestionFragment_ViewBinding implements Unbinder {
    private MythbustersQuestionFragment target;

    public MythbustersQuestionFragment_ViewBinding(MythbustersQuestionFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7485R.C7487id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C7485R.C7487id.recycler_view, "field 'recyclerView'", RecyclerView.class);
    }

    public void unbind() {
        MythbustersQuestionFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.recyclerView = null;
    }
}
