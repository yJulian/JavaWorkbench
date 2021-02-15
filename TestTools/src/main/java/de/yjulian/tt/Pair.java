package de.yjulian.tt;

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
}
