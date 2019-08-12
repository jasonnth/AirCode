package com.airbnb.android.core.dls;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.BaseLogger;
import com.airbnb.jitney.event.logging.DLS.p077v1.DLSVisualComponentDisplayEvent.Builder;
import com.airbnb.p027n2.internal.Component;
import java.util.List;

public class DLSJitneyLogger extends BaseLogger {
    public DLSJitneyLogger(LoggingContextFactory loggingContextFactory) {
        super(loggingContextFactory);
    }

    public void componentsDisplayed(List<Component> components) {
        for (Component component : components) {
            componentDisplayed(component);
        }
    }

    public void componentDisplayed(Component component) {
        publish(new Builder(this.loggingContextFactory.newInstance(), component.name(), VisualComponentDlsTypeUtils.from(component.type())));
    }
}
