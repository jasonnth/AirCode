package com.airbnb.android.core.arguments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import com.airbnb.android.utils.BundleBuilder;

public abstract class Arguments implements Parcelable {
    public abstract Class<?> getIntentClass();

    public Bundle toBundle() {
        return ((BundleBuilder) new BundleBuilder().putParcelable(getClass().getSimpleName(), this)).toBundle();
    }

    public static <T extends Arguments> T fromBundle(Class<T> klass, Bundle bundle) {
        return (Arguments) bundle.getParcelable("AutoValue_" + klass.getSimpleName());
    }

    public Intent toIntent(Context context) {
        return new Intent(context, getIntentClass()).putExtras(toBundle());
    }

    public static <T extends Arguments> T fromIntent(Class<T> klass, Intent intent) {
        return fromBundle(klass, intent.getExtras());
    }
}
