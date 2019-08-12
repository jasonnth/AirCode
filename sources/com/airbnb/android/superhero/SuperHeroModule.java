package com.airbnb.android.superhero;

import android.content.Context;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.superhero.SuperHeroManager;

public class SuperHeroModule {
    public SuperHeroTableOpenHelper provideSuperHeroTableOpenHelper(Context context) {
        return new SuperHeroTableOpenHelper(context);
    }

    public SuperHeroManager provideSuperHeroManager(Context context, SuperHeroTableOpenHelper openHelper, LoggingContextFactory loggingContextFactory) {
        return _provideSuperHeroManager(context, openHelper, loggingContextFactory);
    }

    /* access modifiers changed from: protected */
    public SuperHeroManager _provideSuperHeroManager(Context context, SuperHeroTableOpenHelper openHelper, LoggingContextFactory loggingContextFactory) {
        MessageDiff messageDiff = new MessageDiff(openHelper);
        SuperHeroJitneyLogger superHeroJitneyLogger = new SuperHeroJitneyLogger(loggingContextFactory);
        return new SuperHeroManagerImpl(new SuperHeroScheduler(context, messageDiff, openHelper, superHeroJitneyLogger), openHelper, superHeroJitneyLogger);
    }
}
