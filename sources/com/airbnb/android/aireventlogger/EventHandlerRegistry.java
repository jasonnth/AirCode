package com.airbnb.android.aireventlogger;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class EventHandlerRegistry {
    private final List<EventHandler> handlers = new ArrayList();

    EventHandlerRegistry(EventHandler... handlers2) {
        addHandlers(handlers2);
    }

    private EventHandlerRegistry addHandlers(EventHandler... newHandlers) {
        Collections.addAll(this.handlers, newHandlers);
        return this;
    }

    public <T> EventHandler get(AirEvent<T> event) {
        Type type = event.getEventType();
        for (EventHandler handler : this.handlers) {
            if (handler.supports(event)) {
                return handler;
            }
        }
        throw new IllegalStateException("No EventHandlers registered for type: " + type);
    }

    /* access modifiers changed from: 0000 */
    public List<EventHandler> getAllHandlers() {
        return this.handlers;
    }
}
