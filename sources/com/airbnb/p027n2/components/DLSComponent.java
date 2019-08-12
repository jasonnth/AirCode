package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.airbnb.p027n2.DLSComponentType;
import com.airbnb.p027n2.DLSMockAdapter;
import com.airbnb.p027n2.DLSStyleWrapper;
import com.airbnb.p027n2.TeamOwner;

/* renamed from: com.airbnb.n2.components.DLSComponent */
public abstract class DLSComponent<T extends View> {
    private final String documentation;
    private final int layout;
    private final String name;
    private final TeamOwner teamOwner;
    private final DLSComponentType type;
    private final Class<T> viewClass;

    public abstract T createView(Context context, AttributeSet attributeSet);

    public abstract DLSMockAdapter<T> mockAdapter();

    DLSComponent(Class<T> viewClass2, DLSComponentType type2, String name2, String documentation2, TeamOwner teamOwner2, int layout2) {
        this.viewClass = viewClass2;
        this.type = type2;
        this.name = name2;
        this.documentation = documentation2;
        this.teamOwner = teamOwner2;
        this.layout = layout2;
    }

    public Class<T> viewClass() {
        return this.viewClass;
    }

    public DLSComponentType type() {
        return this.type;
    }

    public String name() {
        return this.name;
    }

    public String documentation() {
        return this.documentation;
    }

    public T createViewWithDefaultStyle(Context context, ViewGroup root) {
        return LayoutInflater.from(context).inflate(this.layout, root, false);
    }

    public T createDefaultMockView(Context context, ViewGroup root) {
        T view = createViewWithDefaultStyle(context, root);
        DLSMockAdapter<T> adapter = mockAdapter();
        adapter.bindView(view, adapter.getDefaultPosition());
        return view;
    }

    public DLSStyleWrapper getDefaultStyle() {
        DLSMockAdapter<T> adapter = mockAdapter();
        return adapter.getStyle(adapter.getDefaultPosition());
    }
}
