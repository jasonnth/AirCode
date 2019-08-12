package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import com.airbnb.android.core.interfaces.GuestIdentity.Type;
import com.airbnb.android.lib.presenters.GuestIdentityTypePresenter;
import com.airbnb.android.utils.BundleBuilder;
import java.util.Arrays;
import java.util.List;

public class IdentityTypeSelectorDialogFragment extends OptionSelectionDialog<Type> {
    public static final String RESULT_SELECTED_IDENTITY_TYPE = "identity_type";

    public static IdentityTypeSelectorDialogFragment newInstance(int requestCodeOk) {
        return (IdentityTypeSelectorDialogFragment) newInstance(new IdentityTypeSelectorDialogFragment(), requestCodeOk);
    }

    /* access modifiers changed from: protected */
    public List<Type> getItems() {
        return Arrays.asList(Type.values());
    }

    /* access modifiers changed from: protected */
    public String getDisplayString(Type item) {
        return getString(GuestIdentityTypePresenter.getStringResId(item));
    }

    /* access modifiers changed from: protected */
    public Bundle getResultBundle(Type item) {
        return ((BundleBuilder) new BundleBuilder().putSerializable(RESULT_SELECTED_IDENTITY_TYPE, item)).toBundle();
    }
}
