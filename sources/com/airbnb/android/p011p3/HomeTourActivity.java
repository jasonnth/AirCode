package com.airbnb.android.p011p3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.p011p3.models.HomeLayout;

/* renamed from: com.airbnb.android.p3.HomeTourActivity */
public class HomeTourActivity extends AirActivity implements HomeTourController {
    private static final String ARG_HOME_LAYOUT = "home_layout";
    private static final String ARG_SELECTED_ROOM_ID = "selected_room_id";
    private HomeLayout homeLayout;
    private long selectedRoomId = -1;

    public static Intent intent(Context context, HomeLayout homeLayout2) {
        return intentWithRoomId(context, homeLayout2, -1);
    }

    public static Intent intentWithRoomId(Context context, HomeLayout homeLayout2, long roomId) {
        return new Intent(context, HomeTourActivity.class).putExtra(ARG_HOME_LAYOUT, homeLayout2).putExtra(ARG_SELECTED_ROOM_ID, roomId);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C7532R.layout.activity_home_tour);
        this.homeLayout = (HomeLayout) getIntent().getParcelableExtra(ARG_HOME_LAYOUT);
        this.selectedRoomId = getIntent().getLongExtra(ARG_SELECTED_ROOM_ID, -1);
        if (savedInstanceState == null) {
            showFragment(HomeTourGalleryFragment.newInstance(), C7532R.C7534id.content_container, FragmentTransitionType.FadeInAndOut, true);
        }
    }

    public void onImageClicked(int position) {
        showFragment(HomeTourFragment.newInstance(position), C7532R.C7534id.content_container, FragmentTransitionType.FadeInAndOut, true);
    }

    public HomeLayout getHomeLayout() {
        return this.homeLayout;
    }

    public long getSelectedRoomId() {
        return this.selectedRoomId;
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return true;
    }
}
