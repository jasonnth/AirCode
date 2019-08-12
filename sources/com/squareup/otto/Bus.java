package com.squareup.otto;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class Bus {
    private final ThreadEnforcer enforcer;
    private final ThreadLocal<ConcurrentLinkedQueue<EventWithHandler>> eventsToDispatch;
    private final ConcurrentMap<Class<?>, Set<Class<?>>> flattenHierarchyCache;
    private final HandlerFinder handlerFinder;
    private final ConcurrentMap<Class<?>, Set<EventHandler>> handlersByType;
    private final String identifier;
    private final ThreadLocal<Boolean> isDispatching;
    private final ConcurrentMap<Class<?>, EventProducer> producersByType;

    static class EventWithHandler {
        final Object event;
        final EventHandler handler;

        public EventWithHandler(Object event2, EventHandler handler2) {
            this.event = event2;
            this.handler = handler2;
        }
    }

    public Bus() {
        this("default");
    }

    public Bus(String identifier2) {
        this(ThreadEnforcer.MAIN, identifier2);
    }

    public Bus(ThreadEnforcer enforcer2, String identifier2) {
        this(enforcer2, identifier2, HandlerFinder.ANNOTATED);
    }

    Bus(ThreadEnforcer enforcer2, String identifier2, HandlerFinder handlerFinder2) {
        this.handlersByType = new ConcurrentHashMap();
        this.producersByType = new ConcurrentHashMap();
        this.eventsToDispatch = new ThreadLocal<ConcurrentLinkedQueue<EventWithHandler>>() {
            /* access modifiers changed from: protected */
            public ConcurrentLinkedQueue<EventWithHandler> initialValue() {
                return new ConcurrentLinkedQueue<>();
            }
        };
        this.isDispatching = new ThreadLocal<Boolean>() {
            /* access modifiers changed from: protected */
            public Boolean initialValue() {
                return Boolean.valueOf(false);
            }
        };
        this.flattenHierarchyCache = new ConcurrentHashMap();
        this.enforcer = enforcer2;
        this.identifier = identifier2;
        this.handlerFinder = handlerFinder2;
    }

    public String toString() {
        return "[Bus \"" + this.identifier + "\"]";
    }

    public void register(Object object) {
        if (object == null) {
            throw new NullPointerException("Object to register must not be null.");
        }
        this.enforcer.enforce(this);
        Map<Class<?>, EventProducer> foundProducers = this.handlerFinder.findAllProducers(object);
        for (Class<?> type : foundProducers.keySet()) {
            EventProducer producer = (EventProducer) foundProducers.get(type);
            EventProducer previousProducer = (EventProducer) this.producersByType.putIfAbsent(type, producer);
            if (previousProducer != null) {
                throw new IllegalArgumentException("Producer method for type " + type + " found on type " + producer.target.getClass() + ", but already registered by type " + previousProducer.target.getClass() + ".");
            }
            Set<EventHandler> handlers = (Set) this.handlersByType.get(type);
            if (handlers != null && !handlers.isEmpty()) {
                for (EventHandler handler : handlers) {
                    dispatchProducerResultToHandler(handler, producer);
                }
            }
        }
        Map<Class<?>, Set<EventHandler>> foundHandlersMap = this.handlerFinder.findAllSubscribers(object);
        for (Class<?> type2 : foundHandlersMap.keySet()) {
            Set<EventHandler> handlers2 = (Set) this.handlersByType.get(type2);
            if (handlers2 == null) {
                Set<EventHandler> handlersCreation = new CopyOnWriteArraySet<>();
                handlers2 = (Set) this.handlersByType.putIfAbsent(type2, handlersCreation);
                if (handlers2 == null) {
                    handlers2 = handlersCreation;
                }
            }
            if (!handlers2.addAll((Set) foundHandlersMap.get(type2))) {
                throw new IllegalArgumentException("Object already registered.");
            }
        }
        for (Entry<Class<?>, Set<EventHandler>> entry : foundHandlersMap.entrySet()) {
            EventProducer producer2 = (EventProducer) this.producersByType.get((Class) entry.getKey());
            if (producer2 != null && producer2.isValid()) {
                for (EventHandler foundHandler : (Set) entry.getValue()) {
                    if (!producer2.isValid()) {
                        break;
                    } else if (foundHandler.isValid()) {
                        dispatchProducerResultToHandler(foundHandler, producer2);
                    }
                }
            }
        }
    }

    private void dispatchProducerResultToHandler(EventHandler handler, EventProducer producer) {
        Object event = null;
        try {
            event = producer.produceEvent();
        } catch (InvocationTargetException e) {
            throwRuntimeException("Producer " + producer + " threw an exception.", e);
        }
        if (event != null) {
            dispatch(event, handler);
        }
    }

    public void unregister(Object object) {
        if (object == null) {
            throw new NullPointerException("Object to unregister must not be null.");
        }
        this.enforcer.enforce(this);
        for (Entry<Class<?>, EventProducer> entry : this.handlerFinder.findAllProducers(object).entrySet()) {
            Class<?> key = (Class) entry.getKey();
            EventProducer producer = getProducerForEventType(key);
            EventProducer value = (EventProducer) entry.getValue();
            if (value == null || !value.equals(producer)) {
                throw new IllegalArgumentException("Missing event producer for an annotated method. Is " + object.getClass() + " registered?");
            }
            ((EventProducer) this.producersByType.remove(key)).invalidate();
        }
        for (Entry<Class<?>, Set<EventHandler>> entry2 : this.handlerFinder.findAllSubscribers(object).entrySet()) {
            Set<EventHandler> currentHandlers = getHandlersForEventType((Class) entry2.getKey());
            Collection<EventHandler> eventMethodsInListener = (Collection) entry2.getValue();
            if (currentHandlers == null || !currentHandlers.containsAll(eventMethodsInListener)) {
                throw new IllegalArgumentException("Missing event handler for an annotated method. Is " + object.getClass() + " registered?");
            }
            for (EventHandler handler : currentHandlers) {
                if (eventMethodsInListener.contains(handler)) {
                    handler.invalidate();
                }
            }
            currentHandlers.removeAll(eventMethodsInListener);
        }
    }

    public void post(Object event) {
        if (event == null) {
            throw new NullPointerException("Event to post must not be null.");
        }
        this.enforcer.enforce(this);
        boolean dispatched = false;
        for (Class<?> eventType : flattenHierarchy(event.getClass())) {
            Set<EventHandler> wrappers = getHandlersForEventType(eventType);
            if (wrappers != null && !wrappers.isEmpty()) {
                dispatched = true;
                for (EventHandler wrapper : wrappers) {
                    enqueueEvent(event, wrapper);
                }
            }
        }
        if (!dispatched && !(event instanceof DeadEvent)) {
            post(new DeadEvent(this, event));
        }
        dispatchQueuedEvents();
    }

    /* access modifiers changed from: protected */
    public void enqueueEvent(Object event, EventHandler handler) {
        ((ConcurrentLinkedQueue) this.eventsToDispatch.get()).offer(new EventWithHandler(event, handler));
    }

    /* access modifiers changed from: protected */
    public void dispatchQueuedEvents() {
        if (!((Boolean) this.isDispatching.get()).booleanValue()) {
            this.isDispatching.set(Boolean.valueOf(true));
            while (true) {
                try {
                    EventWithHandler eventWithHandler = (EventWithHandler) ((ConcurrentLinkedQueue) this.eventsToDispatch.get()).poll();
                    if (eventWithHandler != null) {
                        if (eventWithHandler.handler.isValid()) {
                            dispatch(eventWithHandler.event, eventWithHandler.handler);
                        }
                    } else {
                        return;
                    }
                } finally {
                    this.isDispatching.set(Boolean.valueOf(false));
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void dispatch(Object event, EventHandler wrapper) {
        try {
            wrapper.handleEvent(event);
        } catch (InvocationTargetException e) {
            throwRuntimeException("Could not dispatch event: " + event.getClass() + " to handler " + wrapper, e);
        }
    }

    /* access modifiers changed from: 0000 */
    public EventProducer getProducerForEventType(Class<?> type) {
        return (EventProducer) this.producersByType.get(type);
    }

    /* access modifiers changed from: 0000 */
    public Set<EventHandler> getHandlersForEventType(Class<?> type) {
        return (Set) this.handlersByType.get(type);
    }

    /* access modifiers changed from: 0000 */
    public Set<Class<?>> flattenHierarchy(Class<?> concreteClass) {
        Set<Class<?>> classes = (Set) this.flattenHierarchyCache.get(concreteClass);
        if (classes != null) {
            return classes;
        }
        Set<Class<?>> classesCreation = getClassesFor(concreteClass);
        Set<Class<?>> classes2 = (Set) this.flattenHierarchyCache.putIfAbsent(concreteClass, classesCreation);
        if (classes2 == null) {
            return classesCreation;
        }
        return classes2;
    }

    private Set<Class<?>> getClassesFor(Class<?> concreteClass) {
        List<Class<?>> parents = new LinkedList<>();
        Set<Class<?>> classes = new HashSet<>();
        parents.add(concreteClass);
        while (!parents.isEmpty()) {
            Class<?> clazz = (Class) parents.remove(0);
            classes.add(clazz);
            Class<?> parent = clazz.getSuperclass();
            if (parent != null) {
                parents.add(parent);
            }
        }
        return classes;
    }

    private static void throwRuntimeException(String msg, InvocationTargetException e) {
        Throwable cause = e.getCause();
        if (cause != null) {
            throw new RuntimeException(msg + ": " + cause.getMessage(), cause);
        }
        throw new RuntimeException(msg + ": " + e.getMessage(), e);
    }
}
