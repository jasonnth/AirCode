package com.apollographql.apollo.api.internal;

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

    public <V> Optional<V> transform(Function<? super T, V> function) {
        return new Present(Utils.checkNotNull(function.apply(this.reference), "the Function passed to Optional.transform() must not return null."));
    }

    public T orNull() {
        return this.reference;
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
