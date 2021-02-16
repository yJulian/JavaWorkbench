package de.yjulian.tt;

import java.util.Objects;

public class Pair<T, G> {

    protected final T first;
    protected final G second;

    public Pair(T first, G second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public G getSecond() {
        return second;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Pair<?, ?> pair = (Pair<?, ?>) object;
        return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), first, second);
    }

}
