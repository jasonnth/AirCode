package com.airbnb.android.profile_completion.edit_about_me;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import butterknife.ButterKnife;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.profile_completion.C7646R;

public class EditAboutMeActivity extends AirActivity {
    private static final int DIALOG_REQUEST_CONFIRM_EXIT = 101;

    public static Intent newIntent(Context context) {
        return new Intent(context, EditAboutMeActivity.class);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C7646R.layout.activity_edit_about_me);
        ButterKnife.bind((Activity) this);
        if (savedInstanceState == null) {
            showFragment(EditAboutMeFragment.newInstance(), C7646R.C7648id.content_container, FragmentTransitionType.SlideInFromSide, true, EditAboutMeFragment.class.getSimpleName());
        }
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onHomeActionPressed() {
        confirmExit();
    }

    public void onBackPressed() {
        confirmExit();
    }

    private void confirmExit() {
        ZenDialog.builder().withBodyText(C7646R.string.profile_completion_confirm_exit).withDualButton(C7646R.string.cancel, 0, C7646R.string.exit, 101).create().show(getSupportFragmentManager(), (String) null);
    }
}
