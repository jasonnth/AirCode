package com.airbnb.android.contentframework;

import com.airbnb.android.contentframework.ContentFrameworkComponent.Builder;
import com.airbnb.android.core.apprater.GraphBindings;
import javax.inject.Provider;

public interface ContentFrameworkBindings extends GraphBindings {
    Provider<Builder> contentFrameworkComponentProvider();
}
