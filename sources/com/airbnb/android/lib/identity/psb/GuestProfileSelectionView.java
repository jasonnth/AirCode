package com.airbnb.android.lib.identity.psb;

import android.content.Context;
import android.util.AttributeSet;
import com.airbnb.android.core.interfaces.GuestIdentity;
import com.airbnb.p027n2.collections.BaseSelectionView;
import com.airbnb.p027n2.collections.BaseSelectionView.ItemEnabledCallback;
import com.google.common.collect.FluentIterable;
import java.util.List;

public class GuestProfileSelectionView extends BaseSelectionView<GuestIdentity> implements ItemEnabledCallback<GuestIdentity> {
    public GuestProfileSelectionView(Context context) {
        super(context);
        init();
    }

    public GuestProfileSelectionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GuestProfileSelectionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setItemEnabledCallback(this);
    }

    public void addIdentities(List<? extends GuestIdentity> identifications) {
        addItems(identifications);
    }

    public void addIdentity(GuestIdentity newIdentity) {
        addItem(newIdentity);
    }

    public GuestIdentity getSelectedItem() {
        return (GuestIdentity) super.getSelectedItem();
    }

    public boolean isItemEnabled(GuestIdentity item) {
        return !item.isSelected();
    }

    static /* synthetic */ boolean lambda$areAllIdentitiesSelected$0(GuestIdentity input) {
        return input != null && !input.isSelected();
    }

    /* access modifiers changed from: 0000 */
    public boolean areAllIdentitiesSelected() {
        return FluentIterable.from((Iterable<E>) getItems()).filter(GuestProfileSelectionView$$Lambda$1.lambdaFactory$()).toList().isEmpty();
    }
}
