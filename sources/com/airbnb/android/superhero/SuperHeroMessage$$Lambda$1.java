package com.airbnb.android.superhero;

import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.superhero.SuperHeroMessageModel.Creator;
import java.util.ArrayList;

final /* synthetic */ class SuperHeroMessage$$Lambda$1 implements Creator {
    private static final SuperHeroMessage$$Lambda$1 instance = new SuperHeroMessage$$Lambda$1();

    private SuperHeroMessage$$Lambda$1() {
    }

    public static Creator lambdaFactory$() {
        return instance;
    }

    public SuperHeroMessageModel create(long j, AirDateTime airDateTime, AirDateTime airDateTime2, Double d, Double d2, Long l, String str, String str2, ArrayList arrayList, boolean z, ArrayList arrayList2, long j2, Long l2) {
        return new AutoValue_SuperHeroMessage(j, airDateTime, airDateTime2, d, d2, l, str, str2, arrayList, z, arrayList2, j2, l2);
    }
}
