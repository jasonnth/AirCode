package com.google.common.base;

final class Present<T> extends Optional<T> {
    private final T reference;

    Present(T reference2) {
        this.reference = reference2;
    }

    public boolean isPresent() {
        return true;
    }

    public T get() {
        return this.reference;
    }

    /* renamed from: or */
    public T mo41059or(T defaultValue) {
        Preconditions.checkNotNull(defaultValue, "use Optional.orNull() instead of Optional.or(null)");
        return this.reference;
    }

    /* renamed from: or */
    public T mo41058or(Supplier<? extends T> supplier) {
        Preconditions.checkNotNull(supplier);
        return this.reference;
    }

    public T orNull() {
        return this.reference;
    }

    public <V> Optional<V> transform(Function<? super T, V> function) {
        return new Present(Preconditions.checkNotNull(function.apply(this.reference), "the Function passed to Optional.transform() must not return null."));
    }

    public boolean equals(Object object) {
        if (!(object instanceof Present)) {
            return false;
        }
        return this.reference.equals(((Present) object).reference);
    }

    public int hashCode() {
        return 1502476572 + this.reference.hashCode();
    }

    public String toString() {
        return "Optional.of(" + this.reference + ")";
    }
}
