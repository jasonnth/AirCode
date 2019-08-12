package com.facebook.accountkit.p029ui;

import android.app.Activity;
import android.view.View;
import com.facebook.accountkit.p029ui.TitleFragmentFactory.TitleFragment;

/* renamed from: com.facebook.accountkit.ui.ContentController */
interface ContentController {
    ContentFragment getBottomFragment();

    ContentFragment getCenterFragment();

    View getFocusView();

    TitleFragment getFooterFragment();

    TitleFragment getHeaderFragment();

    LoginFlowState getLoginFlowState();

    ContentFragment getTextFragment();

    ContentFragment getTopFragment();

    boolean isTransient();

    void onPause(Activity activity);

    void onResume(Activity activity);

    void setBottomFragment(ContentFragment contentFragment);

    void setCenterFragment(ContentFragment contentFragment);

    void setFooterFragment(TitleFragment titleFragment);

    void setHeaderFragment(TitleFragment titleFragment);

    void setTextFragment(ContentFragment contentFragment);

    void setTopFragment(ContentFragment contentFragment);
}
