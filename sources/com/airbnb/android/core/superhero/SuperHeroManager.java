package com.airbnb.android.core.superhero;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public interface SuperHeroManager {
    public static final String SUPER_HERO_FRAGMENT_TAG = "hero_fragment";

    void clickedHeroAction(long j);

    void dismiss();

    void fetchAndShowSuperHeroMessages();

    void fetchSuperHeroMessage(long j);

    void handleReceiverIntent(Context context, Intent intent);

    void markMessageAsPresented(long j);

    void markMessageAsTriggered(long j);

    void presentFull();

    void removeInterfaceIfSet(SuperHeroInterface superHeroInterface);

    void setInterface(SuperHeroInterface superHeroInterface);

    void setupLocationListener(Context context);

    void show(Bundle bundle);

    void showTest();
}
