package com.airbnb.android.lib.identity.psb;

import android.content.Context;
import android.util.AttributeSet;
import com.airbnb.android.core.interfaces.GuestIdentity.Type;
import com.airbnb.p027n2.collections.BaseSelectionView;

public class IdentificationTypeSelectionView extends BaseSelectionView<Type> {
    public IdentificationTypeSelectionView(Context context) {
        super(context);
        init();
    }

    public IdentificationTypeSelectionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IdentificationTypeSelectionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setItems((T[]) Type.values());
    }

    public void setSelectedItem(Type type) {
        super.setSelectedItem(type);
    }

    public Type getSelectedItem() {
        return (Type) super.getSelectedItem();
    }
}
