package com.airbnb.android.contentframework;

import com.airbnb.android.contentframework.fragments.StoryCreationComposerFragment;
import com.airbnb.android.contentframework.fragments.StoryCreationPickTripFragment;
import com.airbnb.android.contentframework.fragments.StoryDetailViewFragment;
import com.airbnb.android.core.explore.ChildScope;

@ChildScope
public interface ContentFrameworkComponent {

    public interface Builder {
        ContentFrameworkComponent build();
    }

    void inject(StoryCreationComposerFragment storyCreationComposerFragment);

    void inject(StoryCreationPickTripFragment storyCreationPickTripFragment);

    void inject(StoryDetailViewFragment storyDetailViewFragment);
}
