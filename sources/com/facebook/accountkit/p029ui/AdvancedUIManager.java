package com.facebook.accountkit.p029ui;

import android.app.Fragment;
import com.facebook.accountkit.p029ui.UIManager.UIManagerListener;

/* renamed from: com.facebook.accountkit.ui.AdvancedUIManager */
public interface AdvancedUIManager extends UIManagerStub {

    @Deprecated
    /* renamed from: com.facebook.accountkit.ui.AdvancedUIManager$AdvancedUIManagerListener */
    public interface AdvancedUIManagerListener extends UIManagerListener {
    }

    Fragment getActionBarFragment(LoginFlowState loginFlowState);

    void setAdvancedUIManagerListener(AdvancedUIManagerListener advancedUIManagerListener);
}
