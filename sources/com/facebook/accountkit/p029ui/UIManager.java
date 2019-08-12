package com.facebook.accountkit.p029ui;

/* renamed from: com.facebook.accountkit.ui.UIManager */
public interface UIManager extends UIManagerStub {

    /* renamed from: com.facebook.accountkit.ui.UIManager$UIManagerListener */
    public interface UIManagerListener {
        void onBack();

        void onCancel();
    }

    int getThemeId();

    void setThemeId(int i);

    void setUIManagerListener(UIManagerListener uIManagerListener);
}
