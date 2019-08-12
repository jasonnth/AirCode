package com.airbnb.android.superhero.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.superhero.SuperHeroAction;

final /* synthetic */ class SuperHeroThreadAdapter$$Lambda$1 implements OnClickListener {
    private final SuperHeroThreadAdapter arg$1;
    private final SuperHeroAction arg$2;

    private SuperHeroThreadAdapter$$Lambda$1(SuperHeroThreadAdapter superHeroThreadAdapter, SuperHeroAction superHeroAction) {
        this.arg$1 = superHeroThreadAdapter;
        this.arg$2 = superHeroAction;
    }

    public static OnClickListener lambdaFactory$(SuperHeroThreadAdapter superHeroThreadAdapter, SuperHeroAction superHeroAction) {
        return new SuperHeroThreadAdapter$$Lambda$1(superHeroThreadAdapter, superHeroAction);
    }

    public void onClick(View view) {
        this.arg$1.dataController.onSuperHeroAction(this.arg$2);
    }
}
