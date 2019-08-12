package com.airbnb.android.contentframework.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.contentframework.fragments.StoryFeedFragment;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.enums.FragmentTransitionType;

public class StorySearchResultActivity extends AirActivity {
    private static final String SEARCH_TERM_PARAM = "searchText";

    public static Intent intentForSearchTerm(Context context, String searchTerm) {
        Intent intent = new Intent(context, StorySearchResultActivity.class);
        intent.putExtra(SEARCH_TERM_PARAM, searchTerm);
        return intent;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C5709R.layout.activity_story_search_result);
        showFragment(StoryFeedFragment.instanceForSearchTerm(getIntent().getStringExtra(SEARCH_TERM_PARAM)), C5709R.C5711id.fragment_container, FragmentTransitionType.None, false);
    }
}
