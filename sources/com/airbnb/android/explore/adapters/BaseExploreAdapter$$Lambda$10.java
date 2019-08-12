package com.airbnb.android.explore.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.intents.ReactNativeIntents;

final /* synthetic */ class BaseExploreAdapter$$Lambda$10 implements OnClickListener {
    private final BaseExploreAdapter arg$1;

    private BaseExploreAdapter$$Lambda$10(BaseExploreAdapter baseExploreAdapter) {
        this.arg$1 = baseExploreAdapter;
    }

    public static OnClickListener lambdaFactory$(BaseExploreAdapter baseExploreAdapter) {
        return new BaseExploreAdapter$$Lambda$10(baseExploreAdapter);
    }

    public void onClick(View view) {
        this.arg$1.activity.startActivity(ReactNativeIntents.intentForGiftCardsApp(this.arg$1.activity));
    }
}
