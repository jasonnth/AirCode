package dagger.internal;

import dagger.Lazy;

public final class InstanceFactory<T> implements Lazy<T>, Factory<T> {
    private static final InstanceFactory<Object> NULL_INSTANCE_FACTORY = new InstanceFactory<>(null);
    private final T instance;

    public static <T> Factory<T> create(T instance2) {
        return new InstanceFactory(Preconditions.checkNotNull(instance2, "instance cannot be null"));
    }

    private InstanceFactory(T instance2) {
        this.instance = instance2;
    }

    public T get() {
        return this.instance;
    }
}
